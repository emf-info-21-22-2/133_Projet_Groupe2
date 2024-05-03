package com.example.rest1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rest1.model.Question;
import com.example.rest1.repository.QuestionRepository;

import jakarta.transaction.Transactional;
@Service
public class QuestionService{
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Transactional
    public ResponseEntity<String> addNewQuestion(String enoncer) {
        Question newQuestion = new Question();
        newQuestion.setEnoncer(enoncer);
        questionRepository.save(newQuestion);
        return ResponseEntity.ok("Question crée avec succès");
    }

@Transactional
public ResponseEntity<String> deleteQuestion(Integer id) {
    Optional<Question> optionalQuestion = questionRepository.findById(id);
    if (optionalQuestion.isPresent()) {
        questionRepository.delete(optionalQuestion.get());
        return ResponseEntity.ok("Question supprimée avec succès");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Question non trouvée");
    }
}

}