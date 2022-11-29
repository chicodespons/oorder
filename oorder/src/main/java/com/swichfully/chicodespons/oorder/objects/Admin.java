package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.security.Role;

public class Admin extends User {


    public Admin(String password, String email, Role role) {
        super(password, email, role);
    }
}
