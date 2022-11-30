package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.AddressDto;
import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createNewCustomer_givenCustomerData_newCustomerDataIsReturned(){
        AddressDto addressDto = new AddressDto("teststreet", 5, 1040, "Etterbeek");
        NewCustomerDto newCustomerDtoToCreate = new NewCustomerDto("passwd",
                "test@email.com",
                addressDto,
                "0457845745",
                "Testy", "Testerson");

        NewCustomerDto newCustomer = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .port(port)
                .body(newCustomerDtoToCreate)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(NewCustomerDto.class);


        assertThat(newCustomer).isEqualTo(newCustomerDtoToCreate);
    }

    @Test
    void createNewCustomer_givenCustomerWrongData_HttpStatusGiven(){

        String jsonBody = """
                {
                    "password": "passwd",
                    "email": "test@email.com",
                    "addressDto": {
                        "streetName": "teststreet",
                        "streetNumber": 5,
                        "postCode": 10000,
                        "city": "Etterbeek"
                    },
                    "phoneNumber": "0457845745",
                    "firstname": "Testy",
                    "lastname": "Testerson"
                }""";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .port(port)
                .body(jsonBody)
                .post("/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

}