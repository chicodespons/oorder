package com.swichfully.chicodespons.oorder.repository;

import com.swichfully.chicodespons.oorder.objects.Address;
import com.swichfully.chicodespons.oorder.objects.Admin;
import com.swichfully.chicodespons.oorder.objects.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void getUser_whenGivenCorrectEmail_returnCorrectUser() {
        String emailToSearch = "admin@email.com";
        Admin admin = new Admin("passwd", "admin@email.com");

        assertEquals(userRepository.getUser(emailToSearch), admin);
    }

    @Test
    void addCustomer_whenGivenCustomer_addCustomerToRepo() {
        //given
        Customer customer = new Customer("passwd", "lolitat@email.com",
                new Address("lolitastraat", 4,1040,"Etterbeek"),
                "0475487586", "lola", "lolita");
        //when
        userRepository.addCustomer(customer);
        //then
        assertThat(customer).isIn(userRepository.getAllUsers());

    }

    @Test
    void addCustomer_whenGivenCustomerWithEmailAllreadyInRepo_doNotAddCustomerToRepo() {
        //given
        Customer customerNotToBeAdded = new Customer("passwd", "jonny@email.com",
                new Address("looserstraat", 4,1040,"Losto"),
                "0475487587", "looser", "loosoto");
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> userRepository.addCustomer(customerNotToBeAdded));
    }

}