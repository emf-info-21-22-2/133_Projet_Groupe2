package com.example.apigtw.Service;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class Rest2Service {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080";
    @Autowired
    public Rest2Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

   
    public ResponseEntity<String> createUser(String username, String password) {
        String url = baseUrl + "/addUser"; // Ajustez selon l'API que vous appelez

        // Préparer la requête
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        if (response.getStatusCode().is2xxSuccessful()) {

            // Configurer la session en cas de succès

            return ResponseEntity.ok("Utilisateur créé avec succès");

        }

        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la création de l'utilisateur");
    }

    public ResponseEntity<String> login(String username, String password) {
        String url = baseUrl + "/login"; // Ajustez selon l'API que vous appelez

        // Préparer la requête
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        if (response.getStatusCode().is2xxSuccessful()) {

            // Configurer la session en cas de succès

            return ResponseEntity.ok("Utilisateur créé avec succès");

        }

        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la création de l'utilisateur");
    }

    public ResponseEntity<String> deleteUser(String username) {
        // Appeler votre API Gateway pour gérer la déconnexion
        String url = baseUrl + "/deleteUser";

        // Effectuer la requête POST pour la déconnexion
        ResponseEntity<String> response = restTemplate.postForEntity(url, username, String.class);

        // Retourner la réponse avec le code de statut approprié et un message
        if (response.getStatusCode().is2xxSuccessful()) {
            // Succès (code de statut dans la plage des 2xx)
            return ResponseEntity.ok("User supprimée");
        } else {
            // Erreur (code de statut dans la plage des 4xx)
            return ResponseEntity.badRequest().body("Error durant la suppression");
        }
    }
    public ResponseEntity<String> addScoreUser(Integer point, Integer fk_user) {
        String url = baseUrl + "/addScoreUser"; // Ajustez selon l'API que vous appelez

        // Préparer la requête
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("point", point);
        credentials.put("fk_user", fk_user);

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        if (response.getStatusCode().is2xxSuccessful()) {

            // Configurer la session en cas de succès

            return ResponseEntity.ok("Score ajouté");

        }

        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de l'ajout");
    }


}
