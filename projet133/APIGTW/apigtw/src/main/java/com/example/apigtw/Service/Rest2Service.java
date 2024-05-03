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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.apigtw.dto.UserDTO;
@Service
public class Rest2Service {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8082";
    
    public Rest2Service() {
        restTemplate = new RestTemplate();
    }

   
    public ResponseEntity<String> createUser(String username, String password) {
        String url = baseUrl + "/addUser"; // Ajustez selon l'API que vous appelez
    
        // Créer un objet DTO pour encapsuler les données d'utilisateur
        
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        params.add("password", password);
    
        try {
            // Effectuer l'appel API et recevoir la réponse
            ResponseEntity<String> response = restTemplate.postForEntity(url, params, String.class);
    
            // Vérifier si la réponse est un succès
            if (response.getStatusCode().is2xxSuccessful()) {
                // Configurer la session en cas de succès
                // ... (ajoutez ici la logique de configuration de la session si nécessaire)
    
                return ResponseEntity.ok("Utilisateur créé avec succès");
            } else {
                // Si le statut n'est pas 2xx, renvoyer le message d'erreur de l'API
                return ResponseEntity.status(response.getStatusCode())
                        .body("Erreur lors de la création de l'utilisateur : " + response.getBody());
            }
        } catch (HttpClientErrorException e) {
            // Gestion des erreurs HTTP spécifiques
            return ResponseEntity.status(e.getStatusCode())
                    .body("Erreur lors de la création de l'utilisateur : " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // Gestion des autres exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne du serveur : " + e.getMessage());
        }
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
    public ResponseEntity<String> getScoresUsers() {
        String url = baseUrl + "/scoreUsers"; // Ajustez selon l'API que vous appelez

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        // {"status":"success", "data": [{...}]} en cas de succès
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null) {
                return ResponseEntity.ok(responseBody);
            }
        }
        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la récupération des questions");
    }


}
