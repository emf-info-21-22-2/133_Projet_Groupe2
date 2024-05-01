package com.example.apigtw.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

public class Controller {

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        try {
            // Effectue la déconnexion en invalidant la session
            session.invalidate();
            // Retourne HTTP 200 en cas de succès de la déconnexion
            return ResponseEntity.ok("Déconnexion réussie");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de la déconnexion
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la déconnexion : " + e.getMessage());
        }
    }
}
