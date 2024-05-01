package com.example.rest2.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return "Login réussi : " + user.getUsername();
        } else {
            throw new IllegalArgumentException("Nom d'utilisateur ou mot de passe incorrect");
        }
    }
    
    
    
    
}
