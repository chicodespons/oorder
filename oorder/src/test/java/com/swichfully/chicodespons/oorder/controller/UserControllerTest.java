package com.swichfully.chicodespons.oorder.controller;

import com.swichfully.chicodespons.oorder.dtos.AddressDto;
import com.swichfully.chicodespons.oorder.dtos.CustomerDto;
import com.swichfully.chicodespons.oorder.dtos.NewCustomerDto;
import com.swichfully.chicodespons.oorder.objects.Address;
import com.swichfully.chicodespons.oorder.repository.UserRepository;
import com.swichfully.chicodespons.oorder.security.Role;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void getAllCustomers_whenGetRequestToUrl_getAListOfCustomerDtos(){

        List<CustomerDto> customerDtosList = new ArrayList<>();
        CustomerDto customerDtoToCompare = new CustomerDto("jonny@email.com",
                new Address("jonnystraat", 4,1040,"Etterbeek"),
                "0475487586", "jonny", "jonson", Role.CUSTOMER);
        customerDtosList.add(customerDtoToCompare);

        List<CustomerDto> listOfCustomers = RestAssured.given()
                .with().auth().preemptive().basic("admin@email.com","passwd")
                .when()
                .port(port)
                .get("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getList(".", CustomerDto.class);

        assertThat(customerDtosList).isEqualTo(listOfCustomers);
    }

    @Test
    void getCustomer_whenGivenEmail_returnCustomerDto(){

        CustomerDto customerDtoToCompare = new CustomerDto("jonny@email.com",
                new Address("jonnystraat", 4,1040,"Etterbeek"),
                "0475487586", "jonny", "jonson", Role.CUSTOMER);

        CustomerDto customergiven = RestAssured.given()
                .with().auth().preemptive().basic("admin@email.com", "passwd")
                .when()
                .port(port)
                .get("/users/jonny@email.com")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(CustomerDto.class);

        assertThat(customerDtoToCompare).isEqualTo(customergiven);
    }

}