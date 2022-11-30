package com.swichfully.chicodespons.oorder.objects;

import com.swichfully.chicodespons.oorder.security.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
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
