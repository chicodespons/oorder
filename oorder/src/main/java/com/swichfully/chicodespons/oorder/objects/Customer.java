package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.exceptions.PhoneNumberException;
import com.swichfully.chicodespons.oorder.security.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Customer extends User {

    private Address address;
    private String phoneNumber;

    private String firstname;

    private String lastname;

    private final Role role;

    public Customer(String password, String email,Address address, String phoneNumber, String firstname, String lastname) {
        super(password, email);
        this.address = address;
        setPhoneNumber(phoneNumber);
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = Role.CUSTOMER;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()<9 || phoneNumber.length()>10) {
            throw new PhoneNumberException("Uncorrect format given for phonenumber!");
        } else
            this.phoneNumber = phoneNumber;
    }


}
