package com.example.apigtw.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.apigtw.dto.ReponseDTO;

public class Rest1Service {

    private final RestTemplate restTemplate;
    private final String base_url = "http://api-externe.com:8180";

    public Rest1Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public ResponseEntity<String> createQuestion(String enoncer) {
        String url = base_url + "/addQuestion"; // Ajustez selon l'API que vous appelez

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, enoncer, String.class);

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

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, id, String.class);

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

    @Autowired
    public ResponseEntity<String> getQuestions() {
        String url = base_url + "/getQuestions"; // Ajustez selon l'API que vous appelez

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        // {"status":"success", "data": [{...}]} en cas de succès
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {
                return ResponseEntity.ok(responseBody);
            }
        }
        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la récupération des questions");
    }


    @Autowired
    public ResponseEntity<String> createReponse(String reponse, boolean correcte, int question) {
        String url = base_url + "/addReponse"; // Ajustez selon l'API que vous appelez

        // Préparer la requête
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("reponse", reponse);
        credentials.put("correcte", correcte);
        credentials.put("fk_question", question);

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        // {"status":"success"} en cas de succès
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {
                return ResponseEntity.ok("Reponse créé avec succès");
            }
        }
        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la création de la reponse");
    }


    @Autowired
    public ResponseEntity<String> deleteReponse(int id) {
        String url = base_url + "/deleteReponse"; // Ajustez selon l'API que vous appelez

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, id, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        // {"status":"success"} en cas de succès
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {
                return ResponseEntity.ok("Reponse supprimée avec succès");
            }
        }
        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la suppression de la reponse");
    }


    @Autowired
    public ResponseEntity<String> checkReponse(ReponseDTO reponse) {
        String url = base_url + "/checkReponse"; // Ajustez selon l'API que vous appelez

        // Préparer la requête
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("reponse", reponse);

        // Effectuer l'appel API et recevoir la réponse
        ResponseEntity<String> response = restTemplate.postForEntity(url, credentials, String.class);

        // Supposons que l'API renvoie un statut 200 avec un corps contenant
        // {"status":"success"} en cas de succès
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("\"status\":\"success\"")) {
                return ResponseEntity.ok("Reponse check avec succès");
            }
        }
        // Si quelque chose ne va pas, renvoyer une erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors du check de la reponse");
    }


}
