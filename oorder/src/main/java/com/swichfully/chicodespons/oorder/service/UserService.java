package com.swichfully.chicodespons.oorder.service;

import com.swichfully.chicodespons.oorder.dtos.CustomerDto;
import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.mapper.UserMapper;
import com.swichfully.chicodespons.oorder.objects.Customer;
import com.swichfully.chicodespons.oorder.objects.User;
import com.swichfully.chicodespons.oorder.repository.UserRepository;
import com.swichfully.chicodespons.oorder.security.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public NewCustomerDto createNewCustomer(NewCustomerDto newCustomerDto) {
        userRepository.addCustomer(userMapper.fromNewCustomerDtoToCustomer(newCustomerDto));
        return newCustomerDto;
    }

    public List<CustomerDto> getAllCustomers() {

       List<CustomerDto> customers = userRepository.getAllCustomers().stream()
                .map(customer -> mapFromCustomerToCustomerDto(customer))
                .collect(Collectors.toList());

        return customers;
    }

    private CustomerDto mapFromCustomerToCustomerDto(Customer customer){
        return new CustomerDto(customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getRole());

    }

    private List<CustomerDto> mapFromCustomerListToCustomerDtoList(List<Customer> customerList){
        return customerList.stream()
                .map(customer -> mapFromCustomerToCustomerDto(customer))
                .collect(Collectors.toList());
    }

    public CustomerDto  getCustomerByEmail(String email) {
        return mapFromCustomerToCustomerDto(userRepository.getCustomer(email));
    }
}
