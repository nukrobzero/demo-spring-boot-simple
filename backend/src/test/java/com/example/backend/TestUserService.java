package com.example.backend;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void testCareate() throws BaseException {
        User user = userService.create(TestData.email, TestData.password, TestData.name);

        // check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        // check equals
        Assertions.assertEquals(TestData.email, user.getEmail());
        Assertions.assertEquals(TestData.password, user.getPassword());
        Assertions.assertEquals(TestData.name, user.getName());
    }

    @Order(2)
    @Test
    void testUpdate() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        User updatedUser = userService.updateName(user.getId(), TestUpdateData.name);

        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(TestUpdateData.name, updatedUser.getName());
    }

    @Order(3)
    @Test
    void testDelete() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        userService.deleteUser(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestData.email);
        Assertions.assertTrue((optDelete.isEmpty()));
    }

    /**
     * TestData
     */
    public interface TestData {

        String email = "test@test.com";

        String password = "123455";

        String name = "test";
    }

    public interface TestUpdateData {

        String name = "test1";
    }

}
