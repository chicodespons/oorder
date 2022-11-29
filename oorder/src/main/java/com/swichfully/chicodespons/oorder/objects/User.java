package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.security.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {

    private String password;

    private String email;


    protected User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public abstract Role getRole();
}
