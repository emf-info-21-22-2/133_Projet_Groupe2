package com.example.rest1.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.rest1.model.Reponse;
// This will be AUTO IMPLEMENTED by Spring into a Bean called QuestionRepository
// CRUD refers Create, Read, Update, Delete
public interface ReponseRepository extends CrudRepository<Reponse, Integer>{
    
}