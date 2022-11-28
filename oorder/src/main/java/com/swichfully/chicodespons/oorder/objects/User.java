package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.security.Role;

public abstract class User {

    private String password;

    private String email;

    private Role role;

    protected User(String password, String email, Role role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
