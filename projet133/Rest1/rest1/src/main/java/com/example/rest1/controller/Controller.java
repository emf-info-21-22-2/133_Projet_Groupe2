package com.example.rest1.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest1.service.QuestionService;
import com.example.rest1.service.ReponseService;
import com.example.rest1.model.Reponse;

@RestController
public class Controller {
    private final QuestionService questionService;
    private final ReponseService reponseService;
 
    @Autowired
    public Controller(QuestionService questionService, ReponseService reponseService) {
        this.questionService = questionService;
        this.reponseService = reponseService;
    }
 
    @GetMapping("/")
    public String getNothing() {
        return "Bonsoir voici la page d'acceuill";
    }

    @GetMapping("/getQuestions")
    public @ResponseBody String getQuestionsReponses() {
        return reponseService.getAllQuestions();
    }
 
    @PostMapping(path = "/addQuestion")
    public @ResponseBody String addNewQuestion(@RequestParam String enoncer) {
        return questionService.addNewQuestion(enoncer);
    }

    @PostMapping(path = "/deleteQuestion")
    public @ResponseBody String deleteQuestion(@RequestParam int id) {
        return questionService.deleteQuestion(id);
    }

    @PostMapping(path = "/addReponse")
    public @ResponseBody String addNewReponse(@RequestParam String newReponse, @RequestParam boolean correcte, @RequestParam int question) {
        return reponseService.addNewReponse(newReponse, correcte, question);
    }

    @PostMapping(path = "/deleteReponse")
    public @ResponseBody String deleteReponse(@RequestParam int id) {
        return reponseService.deleteReponse(id);
    }

    @PostMapping(path = "/checkReponse")
    public @ResponseBody boolean checkReponse(@RequestParam Reponse reponseAChecker) {
        return reponseService.checkReponse(reponseAChecker);
    }


 
}
 
