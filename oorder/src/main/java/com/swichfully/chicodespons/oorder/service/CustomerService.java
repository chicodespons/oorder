package com.swichfully.chicodespons.oorder.service;

import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.mapper.UserMapper;
import com.swichfully.chicodespons.oorder.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public CustomerService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public NewCustomerDto createNewCustomer(NewCustomerDto newCustomerDto) {
        userRepository.addCustomer(userMapper.fromNewCustomerDtoToCustomer(newCustomerDto));
        return newCustomerDto;
    }
}
