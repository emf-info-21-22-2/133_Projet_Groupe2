package com.example.rest1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest1.model.Reponse;
import com.example.rest1.model.Question;
import com.example.rest1.repository.ReponseRepository;

import jakarta.transaction.Transactional;
@Service
public class ReponseService {
    private final ReponseRepository reponseRepository;

    @Autowired
    public ReponseService(ReponseRepository reponseRepository) {
        this.reponseRepository = reponseRepository;
    }

    @Transactional
    public String addNewReponse(String reponse, boolean correcte, Question question) {
        Reponse newReponse = new Reponse();
        newReponse.setReponse(reponse);
        newReponse.setCorrecte(correcte);
        newReponse.setQuestion(question);
        reponseRepository.save(newReponse);
        return "Ajout réussi";
    }

    @Transactional
public String modifyReponse(Integer id, String newReponse, boolean correcte) {
    Optional<Reponse> optionalReponse = reponseRepository.findById(id);
    if (optionalReponse.isPresent()) {
        Reponse existingReponse = optionalReponse.get();
        existingReponse.setReponse(newReponse);
        existingReponse.setCorrecte(correcte);
        reponseRepository.save(existingReponse);
        return "Modification réussie";
    } else {
        return "Question non trouvée";
    }
}

@Transactional
public String deleteReponse(Integer id) {
    Optional<Reponse> optionalReponse = reponseRepository.findById(id);
    if (optionalReponse.isPresent()) {
        reponseRepository.delete(optionalReponse.get());
        return "Suppression réussie";
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
}
