package com.qa.testcases;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.hamcrest.core.Is;
import org.testng.annotations.Test;

public class GETapiTestBDD {
	
	/*GET Method*/
	@Test
	public void getMethodTest() {
		
		RestAssured.baseURI = "http://localhost:3000";
		
		given()
				.contentType("application/json")
		.when()
				.get("/posts")
		.then()
				.statusCode(200)
				.and()
				.log().all();
		
	}

}

























