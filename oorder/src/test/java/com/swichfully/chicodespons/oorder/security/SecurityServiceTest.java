package com.swichfully.chicodespons.oorder.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    @Test
    @DisplayName("Testing validation to not throw when correct email and password")
    void givenCorrectEmailAndPaswor_whenValidateAuthorization_thenNoErrorCanBeThrown() {
        //given
        String correctInput = "jonny@email.com:passwd";
        byte[] inputEncoded = Base64.getEncoder().encode(correctInput.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
        //
        Feature feature = Feature.TEST;

        //then
        Assertions.assertDoesNotThrow(() -> securityService.validateAuthorization(authorizationString, feature));
    }

}