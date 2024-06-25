package com.project;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class FifthRequirement {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://192.168.1.189:5000/api";
    }

    @Test
    public void testRegisterUserWithMissingFields() {
        String requestBody = "{\"email\": \"\", \"password\": \"password123\", \"role\": \"admin\"}";

        RestAssured.given()        
        		.contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .statusCode(400);
    }

    @Test
    public void testLoginWithIncorrectCredentials() {
        		RestAssured.given()
                .auth()
                .preemptive()
                .basic("wronguser@example.com", "wrongpassword")
                .when()
                .post("/login")
                .then()
                .statusCode(401);
    }

    @Test
    public void testAccessEndpointWithInvalidToken() {
        		 RestAssured.given()
                 .header("accessToken", "invalidtoken")
                 .when()
                 .get("/employees/all")
                 .then()
                 .statusCode(403);
    }

    @Test
    public void testUpdateNonExistentEmployee() {
        String requestBody = "{\"id\": 4, \"firstname\": \"ola\", \"lastname\": \"User\", \"email\": \"ola1@mail.com\"}";

        		RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/employees")
                .then()
                .statusCode(404);

    }

    @Test
    public void testListEmployeesWithSpecificQueryParameters() {
        		RestAssured.given()
                .queryParam("id", 1)
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("firstname", notNullValue());
    }
}
