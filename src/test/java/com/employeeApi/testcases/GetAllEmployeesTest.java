package com.employeeApi.testcases;

import java.util.Map;
import java.util.Map.Entry;

import io.restassured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi.base.TestBase;

public class GetAllEmployeesTest extends TestBase{
	
	@BeforeClass
	void getAllEmployees() {
		
		logger.info("*********STARTED GetAllEmployeesTest********************");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.get("/employees");
		
	}
	
	
	@Test
	void checkResponseBody() {
		
		logger.info("*********STARTED checkResponseBody********************");
		
		String responseBodyString = response.getBody().asString();
		
		Assert.assertTrue(responseBodyString!=null);
		
	}
	
	
	@Test
	void checkStatusCode() {
		
		logger.info("*********STARTED checkStatusCode********************");
		
		int statusCode = response.getStatusCode();
		logger.info("STATUS CODE ------>" + statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
	
	@Test
	void checkContentType() {
		
		logger.info("*********STARTED checkContentType********************");
		
		String contentTypeString = response.header("Content-Type");
		logger.info("CONTENT-TYPE ------>" + contentTypeString);
		Assert.assertTrue(contentTypeString.length()>0);
		
	}
	
	
	@Test
	void checkCookies() {
		
		logger.info("*********STARTED checkCookies********************");
		
		Map<String, String> cookiesMap = response.getCookies();
		logger.info("COOKIES MAP ------>" + cookiesMap);
		
		for (Entry<String, String> entry : cookiesMap.entrySet()) {
			logger.info("KEY ------> "+ entry.getKey() + "---- VALUE --->" +entry.getValue());
			
		}
		Assert.assertTrue(cookiesMap.size()>0);
		
	}
	
	
	@AfterClass
	void teardown() {
		logger.info("********FINISHED GetAllEmployeesTest");
	}
	
}






















