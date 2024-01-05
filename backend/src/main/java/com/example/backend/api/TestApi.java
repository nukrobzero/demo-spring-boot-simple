package com.example.backend.api;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.TestBusiness;
import com.example.backend.exception.BaseException;
import com.example.backend.model.RegisterRequest;
import com.example.backend.model.TestResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/test")
public class TestApi {

    // Constructor Injection
    private final TestBusiness business;

    public TestApi(TestBusiness business) {
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
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws BaseException {
        String response = business.register(request);
        return ResponseEntity.ok(response);

    } 

}
