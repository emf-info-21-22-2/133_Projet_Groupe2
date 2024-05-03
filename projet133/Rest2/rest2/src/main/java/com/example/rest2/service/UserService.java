package com.example.rest2.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.rest2.model.User;
import com.example.rest2.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    public Integer getUsernameId(String username) {
        // Utilisez votre repository ou service pour obtenir l'utilisateur par son nom
        User user = userRepository.findByUsername(username);
    
        // Si l'utilisateur n'existe pas, retournez null ou lancez une exception
        
    
        // Sinon, retournez l'ID de l'utilisateur
        return user.getId();
    }
    

    @Transactional
    public String addUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("L'utilisateur existe déjà");
        }
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setIsAdmin(false);
        userRepository.save(user);

        return "User added successfully!";
    }

    @Transactional
    public String deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return "Utilisateur supprimé avec succès!";
        } else {
            return "Utilisateur non trouvé!";
        }
    }

    @Transactional
    public ResponseEntity<String> loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("L'utilisateur n'existe pas");
        }
        String hashedPassword = DigestUtils.sha256Hex(password);
        if (!user.getPassword().equals(hashedPassword)) {
            //throw new IllegalArgumentException("Mot de passe incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Connexion non autorisée");
        }
        // Ici, vous pouvez ajouter la logique pour gérer une session utilisateur ou un
        // token d'authentification
        return ResponseEntity.ok("Connecté");
    }

}
