package com.qa.schemaValidation.testcases;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaIntroduction {

    static final String POSTS_EP = "https://jsonplaceholder.typicode.com/posts/1";

 
    @Test
    void basicExample() {

        RestAssured.get(POSTS_EP)
                .then()
                .body(matchesJsonSchemaInClasspath("basic_example.json"));
    }
}
