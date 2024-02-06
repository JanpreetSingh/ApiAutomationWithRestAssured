package com.qa.authentication.testcases;

import io.restassured.RestAssured;

import static com.qa.authentication.testcases.ReusableAuth.TOKEN;

import org.testng.annotations.Test;

public class PreemptiveDemo {

    static final String REPOS_EP = "https://api.github.com/user/repos";

    @Test
    void testPreemptive() {
        RestAssured
                .given()
                .auth()
                .preemptive()       // redundant
                .oauth2(TOKEN)
                .get(REPOS_EP)
                .then()
                .statusCode(200);
    }

    @Test
    void basicPreemptive() {
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("postman", "password")
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200);
    }

    @Test
    void basicChallenged() {

        RestAssured
                .given()
                .auth()
                .basic("postman", "password")
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void digest() {

        RestAssured
                .given()
                .auth()
                .digest("postman", "password")
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }
}
