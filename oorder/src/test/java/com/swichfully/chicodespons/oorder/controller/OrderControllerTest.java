package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.ItemGroupDto;
import com.swichfully.chicodespons.oorder.dtos.OrderDto;
import com.swichfully.chicodespons.oorder.objects.ItemGroup;
import com.swichfully.chicodespons.oorder.objects.Order;
import com.swichfully.chicodespons.oorder.repository.ItemRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void orderItems_givenOrderData_OrderWithOrderDateIsReturned(){

        ItemGroupDto itemGroupDto1 = new ItemGroupDto(0, 1);
        ItemGroupDto itemGroupDto2 = new ItemGroupDto(1,2);

        List<ItemGroupDto> itemGroupList = new ArrayList<>();
        itemGroupList.add(itemGroupDto1);
        itemGroupList.add(itemGroupDto2);

        OrderDto orderDtoToCreate = new OrderDto(itemGroupList);

        //

        ItemGroup itemGroup1 = new ItemGroup(
                itemRepository.getItem("Playdow"),
                1, 10.99, LocalDate.now().plusDays(1));
        ItemGroup itemGroup2 = new ItemGroup(
                itemRepository.getItem("Bubble blazer"),
                2, 10.50, LocalDate.now().plusDays(1));
        List<ItemGroup> itemGroupList2 = new ArrayList<>();
        itemGroupList2.add(itemGroup1);
        itemGroupList2.add(itemGroup2);


        Order orderToCompare = new Order(
                itemGroupList2, 21.490000000000002, "admin@email.com"
        );

        Order orderGiven = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth().preemptive().basic("admin@email.com", "passwd")
                .when()
                .port(port)
                .body(orderDtoToCreate)
                .post("/orders")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(Order.class);

        assertThat(orderGiven).isEqualTo(orderToCompare);

    }
}