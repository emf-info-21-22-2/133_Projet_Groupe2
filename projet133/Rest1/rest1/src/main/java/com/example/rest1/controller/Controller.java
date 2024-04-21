package com.example.rest1.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.example.rest1.service.QuestionService;
import com.example.rest1.service.ReponseService;
import com.example.rest1.model.Reponse;
import com.example.rest1.model.Question;
 
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
        return "rien";
    }
 
    @PostMapping(path = "/addQuestion")
    public @ResponseBody String addNewQuestion(@RequestParam String enoncer) {
        return questionService.addNewQuestion(enoncer);
    }
 
    @PostMapping(path = "/modifieQuestion")
    public @ResponseBody String modifieQuestion(@RequestParam int id, @RequestParam String newEnoncer) {
        return questionService.modifyQuestion(id, newEnoncer);
    }

    @PostMapping(path = "/deleteQuestion")
    public @ResponseBody String deleteQuestion(@RequestParam int id) {
        return questionService.deleteQuestion(id);
    }

    @PostMapping(path = "/addQuestion")
    public @ResponseBody String addNewReponse(@RequestParam String newReponse, @RequestParam boolean correcte, @RequestParam Question question) {
        return reponseService.addNewReponse(newReponse, correcte, question);
    }

    @PostMapping(path = "/modifieReponse")
    public @ResponseBody String modifieReponse(@RequestParam int id, @RequestParam String newReponse, @RequestParam boolean correcte) {
        return reponseService.modifyReponse(null, newReponse, correcte);
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
 
