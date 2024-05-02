package com.example.apigtw.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigtw.Service.Rest1Service;
import com.example.apigtw.Service.Rest2Service;

import jakarta.servlet.http.HttpSession;
@RestController
public class Controller {
    private final Rest1Service rest1;
    private final Rest2Service rest2;

    @Autowired
    public Controller(Rest1Service rest1, Rest2Service rest2) {
        this.rest1 = rest1;
        this.rest2 = rest2;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // Vérifie si l'utilisateur est connecté
        if (session.getAttribute("user") != null) {
            session.invalidate();
            return ResponseEntity.ok("Déconnexion réussie.");
        } else {
            return ResponseEntity.ok("Aucun utilisateur n'est connecté.");
        }
    }




    //SERVICE REST 1

    @GetMapping("/getQuestions")
    public ResponseEntity<String> getAllQuestions() {
        try {
            // Appelle la méthode du service
            ResponseEntity<String> response = rest1.getQuestions();

            // Vérifie si la réponse est réussie (code d'état 200)
            if (response.getStatusCode().is2xxSuccessful()) {
                // Retourne HTTP 200 avec le corps de la réponse en cas de succès
                return ResponseEntity.ok(response.getBody());
            } else {
                // Retourne HTTP 400 avec un message d'erreur en cas d'échec
                return ResponseEntity.badRequest().body("Échec de la récupération des questions");
            }
        } catch (Exception e) {
            // Retourne HTTP 400 avec un message d'erreur en cas d'exception
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }
    
}
