package com.qa.testcases;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RA1_RequestTutorial {
	
	RequestSpecification request;
	Response response;
	
	Logger logger;
	
	
	@BeforeTest
	public void setUp() {
		logger = Logger.getLogger("RestAssuredBasicsTutorial"); 
		PropertyConfigurator.configure("./log4j.properties");
	}
	
	
	@BeforeMethod
	public void setApiUrl() throws IOException {
		
		/*
		RestAssured will take the API URL as the concatenation of the
		the baseURI and basePath.
		*/
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api";
				
	}
	
	
	@Test(priority=1)
	public void createGETRequest() throws ParseException, IOException {
				
		String id = "3";
		logger.info("*********Create Request Object********************");
		request = RestAssured.given();
		
		
		logger.info("*********Send Request to server and get Response********************");
		response = request.get("/users");
		response.prettyPeek();
		
		
		logger.info("*********Send Request with Path Parameters********************");
		response = request.get("/users/{userId}", id);
		response.prettyPeek();
		
		
		logger.info("*********Send Request with Query Parameters********************");
		response = request.queryParam("page", id).get("/users");
		response.prettyPeek();
		
		logger.info("*********Verify Status Code for GET********************");
		Assert.assertEquals(response.getStatusCode(), 200);
				
	}
	
	
	
	@Test(priority=2)
	public void createPOSTRequest() {
		
		logger.info("*********Create Request Headers.********************");
		HashMap<String, String> headersMap = new HashMap<>();
		headersMap.put("Content-Type", "application/json");
		headersMap.put("user", "test@api.com");
		headersMap.put("password", "testapi123");
		headersMap.put("Auth Token", "123456");
		
		
		logger.info("*********Create Request Payload********************");
		JSONObject payload = new JSONObject();
		payload.put("name", "morpheus");
		payload.put("job", "leader");
		
		
		logger.info("*********Post request with created headers and payload********************");
		request = RestAssured.given();
		request.headers(headersMap);
		request.body(payload.toString());
		
		response = request.post("/users");
		response.prettyPeek();
		
		logger.info("*********Verify Status Code for POST********************");
		Assert.assertEquals(response.getStatusCode(), 201);
		
	}
	
	@Test(priority=3)
	public void createPUTRequest() {
		
		String id = "2";
		logger.info("*********Create Request Headers.********************");
		HashMap<String, String> headersMap = new HashMap<>();
		headersMap.put("Content-Type", "application/json");
		
		
		logger.info("*********Create Request Payload********************");
		JSONObject payload = new JSONObject();
		payload.put("name", "morpheus");
		payload.put("job", "zion resident");
		
		
		logger.info("*********Put request with created headers and payload********************");
		request = RestAssured.given();
		request.headers(headersMap);
		request.body(payload.toString());
		
		response = request.put("/users/{id}", id);
		response.prettyPeek();
		
		
		logger.info("*********Verify Status Code for PUT********************");
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void createDELETERequest() {
		
		String id = "2";
		logger.info("*********DELETE request********************");
		request = RestAssured.given();		
		response = request.delete("/users/{id}", id);
		response.prettyPeek();
		
		
		logger.info("*********Verify Status Code for DELETE********************");
		Assert.assertEquals(response.getStatusCode(), 204);
		
	}
	
	

	
	
	


}
