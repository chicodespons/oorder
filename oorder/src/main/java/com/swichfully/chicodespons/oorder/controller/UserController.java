package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.objects.User;
import com.swichfully.chicodespons.oorder.repository.UserRepository;
import com.swichfully.chicodespons.oorder.security.Feature;
import com.swichfully.chicodespons.oorder.security.SecurityService;
import com.swichfully.chicodespons.oorder.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final SecurityService securityService;
    private final CustomerService customerService;
    private final UserRepository userRepository;

    public UserController(SecurityService securityService, CustomerService customerService, UserRepository userRepository) {
        this.securityService = securityService;
        this.customerService = customerService;
        this.userRepository = userRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public NewCustomerDto createCustomer(@RequestHeader String authorization, @RequestBody NewCustomerDto newCustomerDto) {
        securityService.validateAuthorization(authorization, Feature.CREATE_USER);
        return customerService.createNewCustomer(newCustomerDto);
    }
}
