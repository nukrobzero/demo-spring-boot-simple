package com.example.backend.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.business.UserBusiness;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.model.RegisterRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserApi {

    // Constructor Injection
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<String>> deleteUser(@PathVariable("id") String id) throws BaseException {
        Optional<String> response = business.deleteUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws BaseException {
        List<User> response = business.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) throws BaseException {
        User response = business.register(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/uploadprofile")
    public ResponseEntity<String> uploadprofilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadPicture(file);
        return ResponseEntity.ok(response);
    }

}
