package com.example.rest2.repository;
import org.springframework.data.repository.CrudRepository;
import com.example.rest2.model.Score;

public interface ScoreRepository extends CrudRepository<Score, Integer> {
    
}
