package com.example.rest2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rest2.model.User;
import com.example.rest2.model.Score;
import com.example.rest2.repository.ScoreRepository;
import com.example.rest2.repository.UserRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import jakarta.transaction.Transactional;

public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository, UserRepository userRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String addScoreUser(Integer point, Integer fk_User) {
        User user = userRepository.findById(fk_User).orElse(null);
        if (user == null) {
            return "User not found";
        }
        LocalDate date = LocalDate.now();

        // Pour formater la date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDate = date.format(formatter);

        Score newScore = new Score();
        newScore.setPoint(point);
        newScore.setDate(formatDate);
        newScore.setUser(user);
        scoreRepository.save(newScore);
        return "Saved";


    }
   
    
    

}
