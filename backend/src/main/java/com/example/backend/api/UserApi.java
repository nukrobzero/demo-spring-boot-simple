package com.example.backend.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.business.UserBusiness;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.model.RegisterRequest;
import com.example.backend.model.TestResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("nukrob");
        response.setFood("Pizza");

        return response;
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
