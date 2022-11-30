package com.swichfully.chicodespons.oorder.service;

import com.swichfully.chicodespons.oorder.dtos.AddressDto;
import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.objects.Address;
import com.swichfully.chicodespons.oorder.objects.Customer;
import com.swichfully.chicodespons.oorder.objects.User;
import com.swichfully.chicodespons.oorder.repository.UserRepository;
import com.swichfully.chicodespons.oorder.security.Role;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void createNewCustomer_whenGivenNewCustomerDto_checkIfCustomerIsAddedInRepository() {
        NewCustomerDto newCustomerDto = new NewCustomerDto(
                "abc", "thisisantestemail@email.com",
                new AddressDto("testy", 5, 1020, "testcity"),
                "0458965874", "lala", "lola");

        Customer customer = new Customer("abc", "thisisantestemail@email.com",
                new Address("testy", 5, 1020, "testcity"),
                "0458965874", "lala", "lola");

        userService.createNewCustomer(newCustomerDto);

        assertThat(customer).isIn(userRepository.getAllUsers());
    }

}