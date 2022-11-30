package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.CustomerDto;
import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.security.Feature;
import com.swichfully.chicodespons.oorder.security.SecurityService;
import com.swichfully.chicodespons.oorder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final SecurityService securityService;
    private final UserService userService;


    public UserController(SecurityService securityService, UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public NewCustomerDto createCustomer(@RequestBody NewCustomerDto newCustomerDto) {
        return userService.createNewCustomer(newCustomerDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomers(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.GET_ALL_CUSTOMERS);
        return userService.getAllCustomers();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{email}")
    public CustomerDto getCustomerByEmail(@RequestHeader String authorization, @PathVariable String email){
        securityService.validateAuthorization(authorization, Feature.GET_CUSTOMER_BY_EMAIL);
        return userService.getCustomerByEmail(email);
    }
}
