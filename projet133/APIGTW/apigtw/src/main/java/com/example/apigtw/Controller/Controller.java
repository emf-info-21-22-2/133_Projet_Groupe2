package com.example.apigtw.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigtw.Service.Rest1Service;
import com.example.apigtw.Service.Rest2Service;
import com.example.apigtw.dto.ReponseDTO;

import jakarta.servlet.http.HttpSession;
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
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
    @PostMapping("/login")
public ResponseEntity<String> login(@RequestParam String username,
        @RequestParam String password, HttpSession session) {
    try {
        // Tentez de vous connecter en utilisant le service approprié
        rest2.login(username, password);

        // Si le login est réussi, ajoutez l'utilisateur à la session
        session.setAttribute("username", username);

        // Construire un message avec les détails de la session
        String sessionDetails = "Session ID: " + session.getId() + 
                                ", Utilisateur connecté: " + session.getAttribute("username");

        // Retourne HTTP 200 avec un message de succès et les détails de la session
        return ResponseEntity.ok("Connexion réussie. " + sessionDetails);
    } catch (Exception e) {
        // En cas d'échec de la connexion, retournez HTTP 401 Unauthorized
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Échec de la connexion : " + e.getMessage());
    }
}

    
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
   /*  @PostMapping("/addScoreUser")
public ResponseEntity<String> addScoreUser(@RequestParam Integer point, HttpSession session) {
    
}*/


@PostMapping("/getIdUser")
public ResponseEntity<Integer> getIdUser(@RequestParam String username) {
    try {
        // Appelle la méthode du service
        ResponseEntity<Integer> response = rest2.getIdUser(username);

        // Vérifie si la réponse est réussie (code d'état 200)
        if (response.getStatusCode().is2xxSuccessful()) {
            // Retourne HTTP 200 avec le corps de la réponse en cas de succès
            return ResponseEntity.ok(response.getBody());
        } else {
            // Retourne HTTP 400 avec un message d'erreur en cas d'échec
            return ResponseEntity.badRequest().body(-1); 
        }
    } catch (Exception e) {
        // Retourne HTTP 400 avec un message d'erreur en cas d'exception
        return ResponseEntity.badRequest().body(-1); 
    }
}

@PostMapping("/addScoreUser")
public ResponseEntity<String> addScoreUser(@RequestParam Integer point, HttpSession session) {
    // Vérifier si la session existe en vérifiant la présence du nom d'utilisateur
    String username = (String) session.getAttribute("username");
    if (username == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session inexistante ou expirée");
    }
 
    try {
        // Appeler la méthode pour obtenir l'ID de l'utilisateur
        ResponseEntity<Integer> idResponse = getIdUser(username);
 
        // Vérifier si la réponse est réussie (code d'état 200)
        if (idResponse.getStatusCode().is2xxSuccessful()) {
            // Récupérer l'ID de l'utilisateur à partir de la réponse
            Integer userId = idResponse.getBody();
 
            // Appeler la méthode pour ajouter le score à l'utilisateur
            ResponseEntity<String> response = rest2.addScoreUser(point, userId);
 
            // Vérifier si l'ajout du score est réussi
            if (response.getStatusCode().is2xxSuccessful()) {
                // Configurer la session en cas de succès (si nécessaire)
                // Supposons que vous deviez faire quelque chose avec la session
 
                return ResponseEntity.ok("Score ajouté avec succès");
            } else {
                return ResponseEntity.badRequest().body("Échec de l'ajout du score");
            }
        } else {
            return ResponseEntity.badRequest().body("Échec de l'obtention de l'ID de l'utilisateur");
        }
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
    }
}

    // Methode du REST 1

    @GetMapping("/getQuestions")
    public ResponseEntity<String> getAllQuestions(HttpSession session) {
         String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session inexistante ou expirée");
        } 
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
    
    public ResponseEntity<String> addQuestion(@RequestParam String enoncer, HttpSession session) {
    
        // Vérifier si la session existe en vérifiant la présence du nom d'utilisateur
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session inexistante ou expirée");
        }
     
        try {
            // Ajoute l'utilisateur en utilisant le service approprié
            rest1.createQuestion(enoncer);
            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Question ajoutée avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de la question : " + e.getMessage());
        }
    
    }
    
    @PostMapping("/deleteQuestion")
    
    public ResponseEntity<String> deleteQuestion(@RequestParam Integer id, HttpSession session) {
    
        // Vérifier si la session existe en vérifiant la présence du nom d'utilisateur
        String username = (String) session.getAttribute("username");
    
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session inexistante ou expirée");
        }
     
        try {
    
            // Supprime la question en utilisant le service approprié
            rest1.deleteQuestion(id);
    
            // Retourne HTTP 200 en cas de succès de la suppression de l'utilisateur
            return ResponseEntity.ok("Question supprimée avec succès");
    
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de la suppression de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la suppression de la question : " + e.getMessage());
        }
    
    }
    
    @PostMapping("/addReponse")
    
    public ResponseEntity<String> addReponse(@RequestParam String newReponse, @RequestParam Boolean correcte,
    
            @RequestParam Integer question, HttpSession session) {
        // Vérifier si la session existe en vérifiant la présence du nom d'utilisateur
        String username = (String) session.getAttribute("username");
    
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session inexistante ou expirée");
        }
     
        try {
    
            // Ajoute la réponse en utilisant le service approprié
            rest1.createReponse(newReponse, correcte, question);

            // Retourne HTTP 200 en cas de succès de l'ajout de l'utilisateur
            return ResponseEntity.ok("Réponse ajoutée avec succès");

        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de l'ajout de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de l'ajout de la réponse : " + e.getMessage());
    
        }
    
    }
    @PostMapping("/deleteReponse")
    
    public ResponseEntity<String> deleteReponse(@RequestParam Integer id, HttpSession session) {
    
        // Vérifier si la session existe en vérifiant la présence du nom d'utilisateur
        String username = (String) session.getAttribute("username");
    
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session inexistante ou expirée");
        }
        try {
            // Supprime la réponse en utilisant le service approprié
            rest1.deleteReponse(id);
    
            // Retourne HTTP 200 en cas de succès de la suppression de l'utilisateur
            return ResponseEntity.ok("Réponse supprimée avec succès");
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de la suppression de l'utilisateur
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors de la suppression de la réponse : " + e.getMessage());
        }
    }
    

    @PostMapping("/checkReponse")
    public ResponseEntity<String> checkReponse(@RequestParam Integer idReponse) {
        try {
            // Vérifie la réponse en utilisant le service approprié
            ResponseEntity<String> reponseEntity = rest1.checkReponse(idReponse);
            // Retourne le corps de la réponse de la méthode checkReponse
            return ResponseEntity.ok(reponseEntity.getBody());
        } catch (Exception e) {
            // Retourne HTTP 400 en cas d'erreur lors de la vérification de la réponse
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur lors du check de la reponse : " + e.getMessage());
        }
    }
    

}
