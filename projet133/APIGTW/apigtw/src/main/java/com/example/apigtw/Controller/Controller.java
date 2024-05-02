package com.example.apigtw.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Service rest2
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // Vérifie si l'utilisateur est connecté
        if (session.getAttribute("username") != null) {
            session.invalidate();
            return ResponseEntity.ok("Déconnexion réussie.");
        } else {
            return ResponseEntity.ok("Aucun utilisateur n'est connecté.");
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam String username,
            @RequestParam String password) {
        try {
            // Ajoute l'utilisateur en utilisant le service approprié
            rest2.createUser(username, password);
            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Utilisateur ajouté avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

}
