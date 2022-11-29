package com.swichfully.chicodespons.oorder.repository;

import com.swichfully.chicodespons.oorder.objects.Admin;
import com.swichfully.chicodespons.oorder.objects.Customer;
import com.swichfully.chicodespons.oorder.objects.User;
import com.swichfully.chicodespons.oorder.security.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class UserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    public UserRepository() {
        mockData();
    }

    private void mockData() {

        User customer1 = new Customer("passwd", "jonny@email.com", Role.CUSTOMER, "jonnystraat 1040 Belgie", "02475487547");
        User admin = new Admin("passwd", "admin@email.com", Role.ADMIN);

        userMap.put(customer1.getEmail(), customer1);
        userMap.put(admin.getEmail(), admin);
    }

    public User getUser(String email) {
        return userMap.get(email);
    }
}
