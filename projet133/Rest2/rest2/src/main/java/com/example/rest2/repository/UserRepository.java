package com.example.rest2.repository;
import org.springframework.data.repository.CrudRepository;
import com.example.rest2.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}