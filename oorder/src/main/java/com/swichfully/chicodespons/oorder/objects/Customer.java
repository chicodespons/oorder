package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.security.Role;

public class Customer extends User {

    private String address;
    private String phoneNumber;

    public Customer(String password, String email, Role role, String address, String phoneNumber) {
        super(password, email, role);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
