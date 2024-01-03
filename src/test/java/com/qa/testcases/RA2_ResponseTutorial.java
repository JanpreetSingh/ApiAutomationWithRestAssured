package com.qa.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RA2_ResponseTutorial {
	
	RequestSpecification request;
	Response response;
	
	Logger logger;
	
	
	@BeforeClass
	public void setUp() {
		logger = Logger.getLogger("RestAssuredBasicsTutorial"); 
		PropertyConfigurator.configure("./log4j.properties");
		
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api";
		
		request = RestAssured.given();
		response = request.get("/users");
		response.prettyPeek();
	}
	
	
		
	
	@Test(priority = 1)
	public void verifyResponseBody() {
		
		logger.info("*********Response BODY********************");
		Assert.assertNotNull(response.getBody().asString());
		
		/*RestAssured provides JSON Path and XML Path to parse the Response.*/
		logger.info("********RESPONSE PARSING****RestAssured provides jsonPath() and xmlPath() to parse the Response******************** ");
		JsonPath responseJson = response.jsonPath();
		
		logger.info(responseJson.getInt("total_pages"));
		logger.info(responseJson.getString("data[0].email"));
		logger.info(responseJson.getList("data"));
		
	}
	
	@Test(priority = 2)
	public void verifyResponseStatus() {

		logger.info("*********STATUS CODE********************");
		logger.info(response.getStatusLine());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority = 3)
	public void verifyResponseHeaders() {
		
		logger.info("*********Get CONTENT-TYPE********************");
		logger.info(response.getContentType());
		
		logger.info("*********Get SPECIFIC HEADER********************");
		logger.info(response.getHeader("X-Powered-By"));
		
		
		logger.info("*********Get ALL HEADERS********************");
		Headers headers = response.getHeaders();

		for (Header header : headers) {
			logger.info("KEY ------> "+ header.getName() + "---- VALUE --->" +header.getValue());
		}
		
		
	}
	
	
	
	@Test(priority = 4)
	void verifyCookies() {
		
		logger.info("*********COOKIES********************");
		
		Map<String, String> cookiesMap = response.getCookies();
		logger.info("COOKIES MAP ------>" + cookiesMap);
		
		for (Entry<String, String> entry : cookiesMap.entrySet()) {
			logger.info("KEY ------> "+ entry.getKey() + "---- VALUE --->" +entry.getValue());
			
		}
		
	}
	


}
