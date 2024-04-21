package com.example.rest2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.rest2.service.ScoreService;
import com.example.rest2.service.UserService;

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

}
