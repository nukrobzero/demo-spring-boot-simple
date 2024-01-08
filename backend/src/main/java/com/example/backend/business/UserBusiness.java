package com.example.backend.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.service.UserService;
import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.exception.FileException;
import com.example.backend.model.RegisterRequest;

@Service
public class UserBusiness {

    private final UserService userService;

    public UserBusiness(UserService userService) {
        this.userService = userService;
    }

    public User register(RegisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        // todo: mapper
        return user;

    }

    public String uploadPicture(MultipartFile file) throws BaseException {
        if (file == null) {
            throw FileException.fileNull();
        }

        if (file.getSize() > 1048576 * 2) {
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw FileException.fileNull();
        }

        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportTypes.contains(contentType)) {
            throw FileException.unSupported();
        }

        // Upload file to Storage (s3,etc..)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
