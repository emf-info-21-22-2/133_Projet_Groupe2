package com.example.apigtw.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.example.apigtw.dto.ReponseDTO;

@Service
public class Rest1Service {

    private final RestTemplate restTemplate;
    private final String base_url = "http://host.docker.internal:8081";
    
    
    public Rest1Service() {
        restTemplate = new RestTemplate();
    }

    
    public ResponseEntity<String> createQuestion(String enoncer) {
        String url = base_url + "/addQuestion";
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("enoncer", enoncer);
 
        try {
            ResponseEntity<String> reponse = restTemplate.postForEntity(url, params, String.class);
            return ResponseEntity.ok(reponse.getBody()); // Question ajouté avec succès (HTTP 200)
        } catch (HttpClientErrorException.BadRequest ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Échec de l'ajout de la question (HTTP //
                                                                          // 400)
        }
    }

    
    public ResponseEntity<String> deleteQuestion(int id) {
        String url = base_url + "/deleteQuestion"; // Ajustez selon l'API que vous appelez

        LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
        params.add("id", id);
 
        try {
            ResponseEntity<String> reponse = restTemplate.postForEntity(url, params, String.class);
            return ResponseEntity.ok(reponse.getBody()); // Question supprimer  avec succès (HTTP 200)
        } catch (HttpClientErrorException.BadRequest ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Échec de la suppression de la question (HTTP //
                                                                          // 400)
        }
    }
    public ResponseEntity<String> getQuestions() {
        String url = base_url + "/getQuestions"; // Ajustez selon l'API que vous appelez

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


    public ResponseEntity<String> createReponse(String newReponse, boolean correcte, int question) {
        String url = base_url + "/addReponse"; // Ajustez selon l'API que vous appelez

        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("newReponse", newReponse);
        params.add("correcte", correcte);
        params.add("question", question);
 
        try {
            ResponseEntity<String> reponse = restTemplate.postForEntity(url, params, String.class);
            return ResponseEntity.ok(reponse.getBody()); // Reponse ajouté avec succès (HTTP 200)
        } catch (HttpClientErrorException.BadRequest ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Échec de l'ajout de la reponse (HTTP //
                                                                          // 400)
        }
    }


    
    public ResponseEntity<String> deleteReponse(int id) {
        String url = base_url + "/deleteReponse"; // Ajustez selon l'API que vous appelez

        LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
        params.add("id", id);
 
        try {
            ResponseEntity<String> reponse = restTemplate.postForEntity(url, params, String.class);
            return ResponseEntity.ok(reponse.getBody()); // Question supprimer  avec succès (HTTP 200)
        } catch (HttpClientErrorException.BadRequest ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Échec de la suppression de la question (HTTP //
                                                                          // 400)
        }
    }


    
    public ResponseEntity<String> checkReponse(Integer idReponse) {
        String url = base_url + "/checkReponse"; // Ajustez selon l'API que vous appelez
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("idReponse", idReponse);
    
        try {
            ResponseEntity<Boolean> reponseEntity = restTemplate.postForEntity(url, params, Boolean.class);
            Boolean isCorrect = reponseEntity.getBody();
    
            if (isCorrect != null && isCorrect) {
                return ResponseEntity.ok("La réponse est correcte"); // La réponse est correcte (HTTP 200)
            } else {
                return ResponseEntity.ok("La réponse est incorrecte"); // La réponse est incorrecte (HTTP 200)
            }
        } catch (HttpClientErrorException.BadRequest ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Échec du check de la reponse (HTTP 400)
        }
    }
    

}