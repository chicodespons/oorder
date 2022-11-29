package com.swichfully.chicodespons.oorder.security;

import com.swichfully.chicodespons.oorder.exceptions.IncorrectLogInInformationException;
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
    @DisplayName("Testing validation with correct email and password")
    void givenCorrectEmailAndPassword_whenValidateAuthorization_thenNoErrorCanBeThrown() {
        //given
        String correctInput = "jonny@email.com:passwd";
        byte[] inputEncoded = Base64.getEncoder().encode(correctInput.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
        //
        Feature feature = Feature.TEST;

        //then
        Assertions.assertDoesNotThrow(() -> securityService.validateAuthorization(authorizationString, feature));
    }

    @Test
    @DisplayName("Testing validation with wrong email")
    void givenWrongEmail_whenValidateAuthorization_throwException(){
        //given
        String wrongInput = "ronny@email.com:passwd";
        byte[] inputEncoded = Base64.getEncoder().encode(wrongInput.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);

        Feature feature = Feature.TEST;

        //then
        Assertions.assertThrows(IncorrectLogInInformationException.class,() -> securityService.validateAuthorization(authorizationString,feature));
    }

    @Test
    @DisplayName("Testing validation with wrong email")
    void givenWrongPassword_whenValidateAuthorization_throwException(){
        //given
        String wrongInput = "ronny@email.com:thisiswrong";
        byte[] inputEncoded = Base64.getEncoder().encode(wrongInput.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);

        Feature feature = Feature.TEST;

        //then
        Assertions.assertThrows(IncorrectLogInInformationException.class,() -> securityService.validateAuthorization(authorizationString,feature));
    }

    @Test
    @DisplayName("Testing validation with wrong Feature acces")
    void givenWrongFeature_whenAccessingFeatureIsNotAllowd_throwException(){

        //given
        String correctInput = "jonny@email.com:passwd";
        byte[] inputEncoded = Base64.getEncoder().encode(correctInput.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
        //
        Feature feature = Feature.ADMIN_TEST;

        //then
        Assertions.assertThrows(IncorrectLogInInformationException.class, () -> securityService.validateAuthorization(authorizationString, feature));
    }

}