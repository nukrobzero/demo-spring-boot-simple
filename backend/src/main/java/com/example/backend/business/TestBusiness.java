package com.example.backend.business;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.backend.exception.BaseException;
import com.example.backend.exception.UserException;
import com.example.backend.model.RegisterRequest;

@Service
public class TestBusiness {

    public String register(RegisterRequest request) throws BaseException {
        if (request == null) {
            throw UserException.requestNull();
        }

        // Check validate email
        if (Objects.isNull(request.getEmail())) {
            throw UserException.emailNull();
        }

        // Validate other...

        return "Success";

    }

}
