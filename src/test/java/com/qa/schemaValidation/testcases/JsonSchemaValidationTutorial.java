package com.qa.schemaValidation.testcases;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;
/* 
 * For Schema Validation "json-schema-validator" java library dependency must be in pom.xml

 * A JSON schema in REST assured can validate JSON objects in two ways:
1. Syntactically -  checks if a JSON object is a valid JSON object. 
2. Semantically - checks if JSON objects include all necessary fields and values.

 * There are two essential arrays that are used in JSON schema in REST assured:
1. Required - This enlists all the keys the JSON object must consist of.
2. Properties - This enlists the metadata about the keys of JSON objects.
 Note: additionalProperties array is used to represent extra data. This is a structure that does not exist in the “Properties” array.

 * Validation of the schema can be done in 2 ways :
1. matchesJsonSchemaInClasspath() - used when JSON schema in REST assured files are stored in the resource folder of your project.
2. matchesJsonSchema() - used when JSON schema in REST assured files are stored at a different location.

*/

public class JsonSchemaValidationTutorial {

     
    @Test
    void matchesJsonSchemaInClasspathVerify() {

        RestAssured
        .given()
        .baseUri("https://reqres.in/api/users")
        .when()
        .get()
        .then()
        .assertThat()
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidationJson/schema1.json"));
    }
    
    @Test
    void matchesJsonSchemaVerify() {
    	
    	File file = new File(System.getProperty("user.dir") + "/src/test/java/com/qa/schemaValidation/testcases/schema2.json");    	
       
    	RestAssured
        .given()
        .baseUri("https://reqres.in/api/users")
        .when()
        .get()
        .then()
        .assertThat()
        .body(JsonSchemaValidator.matchesJsonSchema(file));
    }
}
