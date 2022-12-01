package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.ItemDto;
import com.swichfully.chicodespons.oorder.repository.ItemRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addANewItem_givenItemData_newItemDataIsReturned() {

        ItemDto itemDtoToCreate = new ItemDto("Teensletsen",
                "sletsen waar je je tenen door kan steken",
                25.00,
                20);

        ItemDto itemDtoGiven = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth().preemptive().basic("admin@email.com","passwd")
                .when()
                .port(port)
                .body(itemDtoToCreate)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(ItemDto.class);

        assertThat(itemDtoGiven).isEqualTo(itemDtoToCreate);
    }

    @Test
    void updateItem_whenGivenNameAndItemDto_UpdateItemInRepository(){

        ItemDto itemDtoToCreate = new ItemDto("velo",
                "this is a test description",
                25.00,
                100);

        ItemDto itemDtoGiven = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth().preemptive().basic("admin@email.com","passwd")
                .when()
                .port(port)
                .body(itemDtoToCreate)
                .patch("/items/velo")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .extract()
                .as(ItemDto.class);

        assertThat(itemDtoToCreate).isEqualTo(itemDtoGiven);
        assertThat(itemDtoGiven.getPrice()).isEqualTo(itemRepository.getItem("velo").getPrice());
    }

//    @Test
//    void addANewItem_givenWrongData_newItemDataIsReturned() {
//
//        String jsonBody = """
//                {
//                        "name" : "Playdow",
//                            "description" : "geslepen en klaar om mee te tekenen",
//                            "price" : 15.00,
//                            "stockAmount" : 5
//                    }
//                """;
//
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .auth().preemptive().basic("admin@email.com","passwd")
//                .when()
//                .port(port)
//                .body(jsonBody)
//                .post("/items")
//                .then()
//                .log().all()
//                        .assertThat()
//                                .statusCode(HttpStatus.SC_BAD_REQUEST);
//
//    }


}