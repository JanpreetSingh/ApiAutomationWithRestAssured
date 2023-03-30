package com.qa.testcases;

import static io.restassured.RestAssured.given;

import java.util.Random;

import io.restassured.RestAssured;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class POSTapiTestBDD {
	
	/*POST Method*/
	@Test
	public void postMethodTest() {
		
		RestAssured.baseURI = "http://localhost:3000";
		
		JSONObject jsonObject = new JSONObject();
		int randomId = new Random().nextInt();
		
		jsonObject.put("id", randomId);
		jsonObject.put("title", "title" + randomId);
		jsonObject.put("author", "author" + randomId);
		
		
		given()
				.header("Content-Type", "application/json")
				.and()
				.body(jsonObject.toString()) 
		.when()
				.post("/posts")				
		.then()
				.statusCode(201)
				.and()
				.log().all();
		
	}

}




















