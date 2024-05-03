package com.example.rest1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rest1.model.Question;
import com.example.rest1.model.Reponse;
import com.example.rest1.repository.QuestionRepository;
import com.example.rest1.repository.ReponseRepository;

import jakarta.transaction.Transactional;
@Service
public class ReponseService {
    private final ReponseRepository reponseRepository;
    private final QuestionRepository questionRepository;
    @Autowired
    public ReponseService(ReponseRepository reponseRepository, QuestionRepository questionRepository) {
        this.reponseRepository = reponseRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public ResponseEntity<String> addNewReponse(String reponse, boolean correcte, Integer fk_question) {
        Reponse newReponse = new Reponse();
        Question question = questionRepository.findById(fk_question).orElse(null);
        if (question == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Reponse non créer");
        }
        newReponse.setReponse(reponse);
        newReponse.setCorrecte(correcte);
        newReponse.setQuestion(question);
        reponseRepository.save(newReponse);
        return ResponseEntity.ok("Reponse créer avec succès");
    }

@Transactional
public ResponseEntity<String> deleteReponse(Integer id) {
    Optional<Reponse> optionalQuestion = reponseRepository.findById(id);
    if (optionalQuestion.isPresent()) {
        reponseRepository.delete(optionalQuestion.get());
        return ResponseEntity.ok("Reponse supprimée avec succès");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Reponse non trouvée");
    }
}



public boolean checkReponse(int idReponse){
    Optional<Reponse> rep = reponseRepository.findById(idReponse);
    if(rep.get().getCorrecte()){
        return true;
    }else{
        return false;
    }
}

public String getAllQuestions() {
    List<Reponse> reponses = (List<Reponse>) reponseRepository.findAll();
    Map<String, List<String>> questionReponses = new HashMap<>();

    for (Reponse reponse : reponses) {
        String question = reponse.getQuestion().getEnoncer();
        if (!questionReponses.containsKey(question)) {
            questionReponses.put(question, new ArrayList<>());
        }
        questionReponses.get(question).add(reponse.getReponse());
    }

    JSONObject json = new JSONObject(questionReponses);
    return json.toString();
}
}
