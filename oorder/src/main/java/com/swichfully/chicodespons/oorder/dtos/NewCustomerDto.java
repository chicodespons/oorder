package com.swichfully.chicodespons.oorder.dtos;

import lombok.Getter;

@Getter
public class NewCustomerDto {

    private String password;

    private String email;

    private AddressDto addressDto;

    private String phoneNumber;

    private String firstname;

    private String lastname;

    public NewCustomerDto(String password, String email, AddressDto addressDto, String phoneNumber, String firstname, String lastname) {
        this.password = password;
        this.email = email;
        this.addressDto = addressDto;
        this.phoneNumber = phoneNumber;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
