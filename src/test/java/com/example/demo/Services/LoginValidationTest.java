package com.example.demo.Services;

import com.example.demo.Repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginValidationTest {

    //her tester vi loginvalidation, om brugeren kan logge ind med forkert information
    @Test
    void loginValidation() throws SQLException {
        UserRepository userRepository = new UserRepository();
        //dette er en rigtig bruger i systemet
        String username = "adam123";
        String password = "ad123";

        int test = userRepository.loginValidation(username,password);
        Assertions.assertTrue(test > 0);

    }
}