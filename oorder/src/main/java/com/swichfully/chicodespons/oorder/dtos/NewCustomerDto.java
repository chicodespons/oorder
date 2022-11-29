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


}
