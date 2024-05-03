package com.example.apigtw.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigtw.Service.Rest1Service;
import com.example.apigtw.Service.Rest2Service;
import com.example.apigtw.dto.ReponseDTO;

import jakarta.servlet.http.HttpSession;

@RestController
public class Controller {
    private final Rest1Service rest1;
    private final Rest2Service rest2;

    @Autowired
    public Controller() {
        rest1 = new Rest1Service();
        rest2 = new Rest2Service();
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
    if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Le nom d'utilisateur et le mot de passe ne peuvent pas être vides");
    }
    try {
        // Ajoute l'utilisateur en utilisant le service approprié
        rest2.createUser(username, password);
        // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
        return ResponseEntity.ok("Utilisateur ajouté avec succès");
    } catch (Exception e) {
        // Log pour plus de détails
        e.printStackTrace();
        // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
    }
}


    // Methode du REST 1

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

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestParam String enoncer) {
        try {
            // Ajoute l'utilisateur en utilisant le service approprié
            rest1.createQuestion(enoncer);
            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Question ajouté avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de la question : " + e.getMessage());
        }
    }

    @GetMapping("/getScoresUser")
    public ResponseEntity<String> getAllScoresUser() {
        try {
            // Appelle la méthode du service
            ResponseEntity<String> response = rest2.getScoresUsers();

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

    @PostMapping("/deleteQuestion")
    public ResponseEntity<String> deleteQuestion(@RequestParam Integer id) {
        try {
            // Ajoute l'utilisateur en utilisant le service approprié
            rest1.deleteQuestion(id);
            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Question supprimé avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la suppression de la question : " + e.getMessage());
        }
    }

    @PostMapping("/addReponse")
    public ResponseEntity<String> addReponse(@RequestParam String reponse, @RequestParam Boolean correcte,
            @RequestParam Integer fk_question) {
        try {
            // Ajoute l'utilisateur en utilisant le service approprié
            rest1.createReponse(reponse, correcte, fk_question);
            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Reponse ajouté avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de la reponse : " + e.getMessage());
        }
    }

    @PostMapping("/deleteReponse")
    public ResponseEntity<String> deleteReponse(@RequestParam Integer id) {
        try {
            // Ajoute l'utilisateur en utilisant le service approprié
            rest1.deleteReponse(id);
            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Reponse supprimé avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la suppression de la reponse : " + e.getMessage());
        }
    }

    @PostMapping("/checkReponse")
    public ResponseEntity<String> checkReponse(@RequestParam ReponseDTO reponse) {
        try {
            // Ajoute l'utilisateur en utilisant le service approprié
            rest1.checkReponse(reponse);
            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Reponse check avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors du check de la reponse : " + e.getMessage());
        }
    }

}
