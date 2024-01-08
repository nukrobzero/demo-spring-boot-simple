package com.example.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.backend.entity.User;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, String> {

    // find user by email
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
