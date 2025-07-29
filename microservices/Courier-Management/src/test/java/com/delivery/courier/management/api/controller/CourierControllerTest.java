package com.delivery.courier.management.api.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourierControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/couriers";
    }

    @Test
    public void shoulReturn201(){
        String requestBody = """
                {
                    "name": "Lucas",
                    "phone": "1193832324"
                }
                """;

        RestAssured.given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
              .when()
                .post()
              .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Lucas"));

    }

}