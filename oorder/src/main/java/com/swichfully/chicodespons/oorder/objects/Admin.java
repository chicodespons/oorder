package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.security.Role;

public class Admin extends User {

    private final Role role;

    public Admin(String password, String email) {
        super(password, email);
        this.role = Role.ADMIN;
    }

    @Override
    public Role getRole() {
        return role;
    }
}
