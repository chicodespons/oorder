package com.swichfully.chicodespons.oorder.dtos;

import com.swichfully.chicodespons.oorder.objects.Address;
import com.swichfully.chicodespons.oorder.security.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private String email;

    private Address address;
    private String phoneNumber;

    private String firstname;

    private String lastname;

    private  Role role;

    public CustomerDto(String email, Address address, String phoneNumber, String firstname, String lastname, Role role) {
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }
}
