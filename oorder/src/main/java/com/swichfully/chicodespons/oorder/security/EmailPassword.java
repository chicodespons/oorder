package com.swichfully.chicodespons.oorder.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class EmailPassword {

    private final String email;

    private final String password;

    public EmailPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
