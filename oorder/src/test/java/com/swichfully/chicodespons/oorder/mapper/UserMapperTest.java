package com.swichfully.chicodespons.oorder.mapper;

import com.swichfully.chicodespons.oorder.dtos.AddressDto;
import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.objects.Address;
import com.swichfully.chicodespons.oorder.objects.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void fromNewCustomerDtoToCustomer_whenGivenNewCustomerDto_returnCustomer() {

        NewCustomerDto newCustomerDto = new NewCustomerDto(
                "abc", "thisisantestemail@email.com",
                new AddressDto("testy", 5, 1020, "testcity"),
                "0458965874", "lala", "lola");

        Customer customer = new Customer("abc", "thisisantestemail@email.com",
                new Address("testy", 5, 1020, "testcity"),
                "0458965874", "lala", "lola");

        //when, then

        assertEquals(userMapper.fromNewCustomerDtoToCustomer(newCustomerDto), customer);
    }
}