package com.swichfully.chicodespons.oorder.mapper;

import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.objects.Address;
import com.swichfully.chicodespons.oorder.objects.Customer;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Customer fromNewCustomerDtoToCustomer(NewCustomerDto newCustomerDto) {
        Address address = new Address(newCustomerDto.getAddressDto().getStreetName(),
                newCustomerDto.getAddressDto().getStreetNumber(),
                newCustomerDto.getAddressDto().getPostCode(),
                newCustomerDto.getAddressDto().getCity());

        return new Customer(newCustomerDto.getPassword(),
                newCustomerDto.getEmail(),
                address,
                newCustomerDto.getPhoneNumber(),
                newCustomerDto.getFirstname(),
                newCustomerDto.getLastname());
    }
}
