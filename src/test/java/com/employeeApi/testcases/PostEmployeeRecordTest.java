package com.employeeApi.testcases;

import java.util.Map;
import java.util.Map.Entry;

import io.restassured.RestAssured;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeApi.base.TestBase;
import com.employeeApi.utilities.RandomGeneratorUtil;

public class PostEmployeeRecordTest extends TestBase{
	
	String empName;
	String empAge;
	String empSalary;
	
	
	@BeforeClass
	void createEmployee() {
		
		logger.info("*********STARTED createEmployee********************");
		
		empName = RandomGeneratorUtil.getRandomEmpName();
		empSalary = RandomGeneratorUtil.getRandomEmpSal();
		empAge = RandomGeneratorUtil.getRandomEmpAge();
		
//		JSON Object as Request PAYLOAD
		JSONObject payload = new JSONObject();
		payload.put("name", empName);
		payload.put("salary", empSalary);
		payload.put("age", empAge);
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
//		Add Request HEADER
		httpRequest.headers("Content-Type", "application/json");
		httpRequest.body(payload.toString());
		
		response = httpRequest.post("/create");
		
	}
	
	
	@Test
	void checkResponseBody() {
		
		logger.info("*********STARTED checkResponseBody********************");
		
		String responseBodyString = response.getBody().asString();
		logger.info("RESPONSE BODY ----->" + responseBodyString);
		
		Assert.assertEquals(responseBodyString.contains(empName), true);
		Assert.assertEquals(responseBodyString.contains(empAge), true);
		Assert.assertEquals(responseBodyString.contains(empSalary), true);
		
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
		logger.info("********FINISHED GetAllEmployeesTest******************");
	}
	
}






















