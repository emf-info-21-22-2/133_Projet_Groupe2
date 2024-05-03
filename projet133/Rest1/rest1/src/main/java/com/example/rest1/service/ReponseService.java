package com.example.rest1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addNewReponse(String reponse, boolean correcte, Integer fk_question) {
        Reponse newReponse = new Reponse();
        Question question = questionRepository.findById(fk_question).orElse(null);
        if (question == null){
            return "Question pas trouvée";
        }
        newReponse.setReponse(reponse);
        newReponse.setCorrecte(correcte);
        newReponse.setQuestion(question);
        reponseRepository.save(newReponse);
        return "Ajout réponse réussi";
    }

@Transactional
public String deleteReponse(Integer id) {
    Optional<Reponse> optionalReponse = reponseRepository.findById(id);
    if (optionalReponse.isPresent()) {
        reponseRepository.delete(optionalReponse.get());
        return "Suppression reponse réussie";
    } else {
        return "Question non trouvée";
    }
}



public boolean checkReponse(Reponse reponseAChecker){
    boolean resultat = reponseAChecker.getCorrecte();
    if (resultat == true){
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
