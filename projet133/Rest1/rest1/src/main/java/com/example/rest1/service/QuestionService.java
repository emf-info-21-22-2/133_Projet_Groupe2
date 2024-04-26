package com.example.rest1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String addNewQuestion(String enoncer) {
        Question newQuestion = new Question();
        newQuestion.setEnoncer(enoncer);
        questionRepository.save(newQuestion);
        return "Ajout question réussi";
    }

@Transactional
public String deleteQuestion(Integer id) {
    Optional<Question> optionalQuestion = questionRepository.findById(id);
    if (optionalQuestion.isPresent()) {
        questionRepository.delete(optionalQuestion.get());
        return "Suppression question réussie";
    } else {
        return "Question non trouvée";
    }
}




}