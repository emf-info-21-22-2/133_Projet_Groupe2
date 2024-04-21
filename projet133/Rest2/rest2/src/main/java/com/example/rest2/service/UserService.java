package com.example.rest2.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.rest2.model.User;
import com.example.rest2.repository.UserRepository;

import jakarta.transaction.Transactional;

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
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        return "User added successfully!";
        
    }
    public String deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return "Utilisateur supprimé avec succès!";
        } else {
            return "Utilisateur non trouvé!";
        }
    }

    
    
    
    
}
