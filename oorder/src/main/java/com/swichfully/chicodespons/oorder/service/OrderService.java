package com.swichfully.chicodespons.oorder.service;

import com.swichfully.chicodespons.oorder.dtos.OrderDto;
import com.swichfully.chicodespons.oorder.mapper.OrderMapper;
import com.swichfully.chicodespons.oorder.objects.Order;
import com.swichfully.chicodespons.oorder.objects.User;
import com.swichfully.chicodespons.oorder.repository.ItemRepository;
import com.swichfully.chicodespons.oorder.repository.OrderRepository;
import com.swichfully.chicodespons.oorder.repository.UserRepository;
import com.swichfully.chicodespons.oorder.security.EmailPassword;
import com.swichfully.chicodespons.oorder.security.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final SecurityService securityService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderMapper orderMapper, SecurityService securityService, UserRepository userRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderMapper = orderMapper;
        this.securityService = securityService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public Order orderItems(OrderDto orderDto, String authentication) {

        EmailPassword emailPassword = securityService.getEmailPassword(authentication);
        String email = emailPassword.getEmail();
        User user = userRepository.getUser(email);

        Order order = orderMapper.mapFromOrderDtoToOrder(orderDto,user);
        orderRepository.addOrder(order);

        order.getItemGroupList()
                .forEach(itemRepository::updateStock);

        return order;
    }
}
