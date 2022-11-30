package com.swichfully.chicodespons.oorder.repository;

import com.swichfully.chicodespons.oorder.objects.Address;
import com.swichfully.chicodespons.oorder.objects.Admin;
import com.swichfully.chicodespons.oorder.objects.Customer;
import com.swichfully.chicodespons.oorder.objects.User;
import com.swichfully.chicodespons.oorder.security.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    public UserRepository() {
        mockData();
    }

    private void mockData() {

        User customer1 = new Customer("passwd", "jonny@email.com",
                new Address("jonnystraat", 4,1040,"Etterbeek"), "0475487586", "jonny", "jonson");
        User admin = new Admin("passwd", "admin@email.com");

        userMap.put(customer1.getEmail(), customer1);
        userMap.put(admin.getEmail(), admin);
    }

    public User getUser(String email) {
        return userMap.get(email);
    }

    public Customer getCustomer(String email) {
        if (userMap.get(email).getRole().equals(Role.CUSTOMER)){
            return (Customer) userMap.get(email);
        }else
            throw new IllegalArgumentException("User you where looking for is not a Customer");

    }

    public void addCustomer(Customer customer) {
        if (!userMap.containsKey(customer.getEmail())){
            userMap.put(customer.getEmail(), customer);
        } else
            throw new IllegalArgumentException("user allready in repo");

    }

    public List<User> getAllUsers() {
        return userMap.values().stream().collect(Collectors.toList());
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        for (User user : userMap.values()) {
            if (user.getRole().equals(Role.CUSTOMER)){
                customers.add((Customer) user);
            }
        }
        return customers;
    }
}
