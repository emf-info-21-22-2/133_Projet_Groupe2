package com.example.apigtw.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

public class Controller {

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
}
