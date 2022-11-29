package com.swichfully.chicodespons.oorder.dtos;

import com.swichfully.chicodespons.oorder.exceptions.PostCodeException;
import com.swichfully.chicodespons.oorder.exceptions.StreetNumberException;
import lombok.Getter;

@Getter
public class AddressDto {

    private String streetName;
    private int streetNumber;
    private int postCode;
    private String city;

    public AddressDto(String streetName, int streetNumber, int postCode, String city) {
        this.streetName = streetName;
        setStreetNumber(streetNumber);
        setPostCode(postCode);
        this.city = city;
    }

    public void setStreetNumber(int streetNumber) {
        if(streetNumber<=0) {
            throw new StreetNumberException("The streetnumber can't be 0 or negative");
        }else
            this.streetNumber = streetNumber;
    }

    public void setPostCode(int postCode) {
        if(postCode<1000 || postCode>9992) {
            throw new PostCodeException("The postcode must be between 1000 and 9992");
        } else
            this.postCode = postCode;
    }
}
