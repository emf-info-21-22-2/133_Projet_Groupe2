package com.example.rest2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest2.service.ScoreService;
import com.example.rest2.service.UserService;

@RestController
public class Controller {
    private final ScoreService scoreService;
    private final UserService userService;

    @Autowired
    public Controller(ScoreService scoreService, UserService userService) {
        this.scoreService = scoreService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getNothing() {
        return "rien";
    }
    @GetMapping("/test")
    public String getNothing2() {
        return "test";
    }
    @GetMapping("/scoreUsers")
    public @ResponseBody String showScore() {
        return scoreService.getAllScores();
    }

    @PostMapping(path = "/addScoreUser")
    public @ResponseBody String addNewScoreUser(@RequestParam Integer point,
            @RequestParam Integer fk_user) {
        return scoreService.addScoreUser(point, fk_user);
    }

    @PostMapping(path = "/addUser")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String password) {
        return userService.addUser(username, password);
    }

    @PostMapping(path = "/deleteUser")
    public @ResponseBody String deleteUser(@RequestParam String username) {
        return userService.deleteUser(username);
    }

    @PostMapping(path = "/login")
    public @ResponseBody String loginUser(@RequestParam String username, @RequestParam String password ) {
        return userService.login(username, password);
    }
    

}
