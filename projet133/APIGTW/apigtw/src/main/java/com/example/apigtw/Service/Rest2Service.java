package com.example.apigtw.Service;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class Rest2Service {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://api-externe.com";

    public Rest2Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public ResponseEntity<String> createUser(String username, String password) {
        String url = baseUrl + "/addUser"; // Ajustez selon l'API que vous appelez

        // Préparer la requête
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        // {"status":"success"} en cas de succès
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {
                // Configurer la session en cas de succès
                
                
                return ResponseEntity.ok("Utilisateur créé avec succès");
            }
        }

        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la création de l'utilisateur");
    }

}
