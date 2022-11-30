package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.ItemDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    @LocalServerPort
    private int port;

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
    void addANewItem_givenWrongData_newItemDataIsReturned() {

        String jsonBody = """
                {
                        "name" : "Playdow",
                            "description" : "geslepen en klaar om mee te tekenen",
                            "price" : 15.00,
                            "stockAmount" : 5
                    }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth().preemptive().basic("admin@email.com","passwd")
                .when()
                .port(port)
                .body(jsonBody)
                .post("/items")
                .then()
                .log().all()
                        .assertThat()
                                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }


}