package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.OrderDto;
import com.swichfully.chicodespons.oorder.objects.Order;
import com.swichfully.chicodespons.oorder.security.Feature;
import com.swichfully.chicodespons.oorder.security.SecurityService;
import com.swichfully.chicodespons.oorder.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final SecurityService securityService;
    private final OrderService orderService;

    public OrderController(SecurityService securityService, OrderService orderService) {
        this.securityService = securityService;
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Order orderItems(@RequestHeader String authorization, @RequestBody OrderDto orderDto){
        securityService.validateAuthorization(authorization, Feature.ORDER_ITEMS);
        return orderService.orderItems(orderDto, authorization);
    }

}