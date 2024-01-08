package com.example.backend.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.UserException;
import com.example.backend.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo repository;

    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    // delete
    public void deleteUser(String id) {
        repository.deleteById(id);
    }

    // update
    public User update(User user) {

        return repository.save(user);
    }

    public User updateName(String id, String name) throws BaseException {
        Optional<User> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }
        User user = opt.get();
        user.setName(name);

        return repository.save(user);
    }

    // create
    public User create(String email, String password, String name) throws BaseException {
        // validate
        if (Objects.isNull(email)) {
            throw UserException.createEmailNull();
        }

        if (Objects.isNull(password)) {
            throw UserException.createPasswordNull();
        }

        if (Objects.isNull(name)) {
            throw UserException.createNameNull();
        }

        // verify
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailDuplicated();
        }

        // save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setName(name);

        return repository.save(entity);

    }

}
