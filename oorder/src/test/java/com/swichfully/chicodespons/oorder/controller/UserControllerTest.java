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
                .body(newCustomerDtoToCreate)
                .accept(ContentType.JSON)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(NewCustomerDto.class);

        assertThat(newCustomer.getPassword()).isEqualTo(newCustomerDtoToCreate.getPassword());
        assertThat(newCustomer.getEmail()).isEqualTo(newCustomerDtoToCreate.getEmail());
        assertThat(newCustomer.getAddressDto().getStreetName()).isEqualTo(newCustomerDtoToCreate.getAddressDto().getStreetName());
        assertThat(newCustomer.getAddressDto().getStreetNumber()).isEqualTo(newCustomerDtoToCreate.getAddressDto().getStreetNumber());
        assertThat(newCustomer.getAddressDto().getPostCode()).isEqualTo(newCustomerDtoToCreate.getAddressDto().getPostCode());
        assertThat(newCustomer.getAddressDto().getCity()).isEqualTo(newCustomerDtoToCreate.getAddressDto().getCity());
        assertThat(newCustomer.getPhoneNumber()).isEqualTo(newCustomerDtoToCreate.getPhoneNumber());
        assertThat(newCustomer.getFirstname()).isEqualTo(newCustomerDtoToCreate.getFirstname());
        assertThat(newCustomer.getLastname()).isEqualTo(newCustomerDtoToCreate.getLastname());
    }

}