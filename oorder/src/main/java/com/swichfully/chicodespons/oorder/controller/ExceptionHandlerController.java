package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IncorrectLogInInformationException.class)
    protected void incorrectLogInInformationException(IncorrectLogInInformationException ex, HttpServletResponse response)
            throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class,PhoneNumberException.class, StreetNumberException.class, PostCodeException.class, StockAmountException.class})
    protected void illegalArgumentExceptions(IllegalArgumentException ex, HttpServletResponse response)
            throws IOException{
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }


}
