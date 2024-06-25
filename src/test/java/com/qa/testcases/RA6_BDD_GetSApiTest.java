package com.qa.testcases;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RA6_BDD_GetSApiTest {
	
	/*GET Method*/
	@Test
	public void getMethodTutorialTest() {
		
		RestAssured.baseURI = "https://reqres.in";
		
		given()
			.contentType("application/json")
		.get("/api/users")
		.then()
				.statusCode(200)
				.header("X-Powered-By", "Express").header("Content-Encoding", "gzip")
				.log().all();
		
	}
	

@Test
public void sugarCodeTutorialTest() {
		
		RestAssured.baseURI = "https://reqres.in";
		
		given()
			.contentType("application/json")
		.when()
			.get("/api/users")
		.then()
			.assertThat()
				.statusCode(200)
				.and()
				.header("X-Powered-By", "Express")
			.and()
				.log().all();
		
	}
/*
 * Hamcrest library provides Matchers for comparison
 */
@Test
public void hamcrestTutorialTest() {
	
	RestAssured.baseURI = "https://reqres.in";
	
	Map<String, String> expectedHeaders = new HashMap<>();
	expectedHeaders.put("Transfer-Encoding", "chunked");
	expectedHeaders.put("Connection", "keep-alive");
	
	
	given()
		.contentType("application/json")
	.when()
		.get("/api/users")
	.then()
		.assertThat()
			.statusCode(200)
			.and()
			.header("X-Powered-By", Matchers.containsString("Express"))
			.header("Etag", Matchers.not(Matchers.isEmptyOrNullString()))
			.header("Etag", Matchers.notNullValue())
			.headers(expectedHeaders)
			.header("Age", Integer::parseInt, Matchers.greaterThan(10))
			.and()
			.body("data[0].id", equalTo(1))
			.body("data[0].email", response -> Matchers.containsString("@reqres.in"))
			.body("data[1].email", response -> Matchers.equalTo(response.body().jsonPath().get("data[0].email")))
			.and()
			.time(Matchers.lessThan(2l), TimeUnit.SECONDS)
			.and()
			.log().all();

	
}


}

























