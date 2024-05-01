package com.example.apigtw.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Rest1Service {
    
    private final RestTemplate restTemplate;
    private final String base_url = "http://api-externe.com:8180";

    public Rest1Service(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Autowired
    public ResponseEntity<String> createQuestion(String enoncer) {
        String url = base_url + "/addQuestion"; // Ajustez selon l'API que vous appelez
 
        // Préparer la requête
        Map<String, String> credentials = new HashMap<>();
        credentials.put("enoncer", enoncer);
 
        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);
 
        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        // {"status":"success"} en cas de succès
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {
                return ResponseEntity.ok("Question créé avec succès");
            }
        }
        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la création de la question");
    }

    @Autowired
public ResponseEntity<String> deleteQuestion(int id) {
    String url = base_url + "/deleteQuestion"; // Ajustez selon l'API que vous appelez

    // Préparer la requête
    Map<String, Integer> credentials = new HashMap<>();
    credentials.put("id", id);
    // Effectuer l'appel API et recevoir la réponse
    ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);

    // Supposons que l'API renvoie un statut 200 avec un corps contenant
    // {"status":"success"} en cas de succès
    if (response.getStatusCode().is2xxSuccessful()) {
        String responseBody = response.getBody();
        if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {
            return ResponseEntity.ok("Question supprimée avec succès");
        }
    }
    // Si quelque chose ne va pas, renvoyer une erreur
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erreur lors de la suppression de la question");
}




}
