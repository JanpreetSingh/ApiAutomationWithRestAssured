package com.qa.testcases;

import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GETapiTest extends TestBase{
	
	TestBase testBase;
	String baseUrl;
	String basePath;
	SoftAssert softAssert;
	RestClient restClient;
	Response httpResponse;
	
	@BeforeMethod
	public void setUp() throws IOException {
		testBase = new TestBase();
		
		baseUrl= prop.getProperty("baseURL");
		basePath= prop.getProperty("basePath");
		
		softAssert = new SoftAssert(); 
				
	}
	
	
	/*Test GET method without Headers.*/
	@Test(priority=1)
	public void getApiMethodWithoutHeadersTest() throws ParseException, IOException {
				
		RestClient restClient = new RestClient();
		httpResponse = restClient.getMethod(baseUrl, basePath);
		
		int statusCode = restClient.getResponseStatus();
		softAssert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
		
		restClient.getResponseJson();	
		restClient.getResponseHeaders();
		
		/*RestAssured provides JSON Path and XML Path to parse the Response.*/
		System.out.println("*******RESPONSE PARSING********* ");
		System.out.println(httpResponse.jsonPath().get("total_pages"));
		System.out.println(httpResponse.jsonPath().get("data[0].email"));
		System.out.println(httpResponse.jsonPath().get("data"));
		System.out.println(httpResponse.jsonPath().getList("data"));
		
		softAssert.assertAll();
		
	}
	
	
	/*Test GET method with Headers.*/
	@Test(priority=2)
	public void getApiMethodWithHeadersTest() throws ParseException, IOException {
				
		RestClient restClient = new RestClient();
		
//		Create Request Headers.
		HashMap<String, String> headersMap = new HashMap<>();
		headersMap.put("Content-Type", "application/json");
		headersMap.put("user", "test@api.com");
		headersMap.put("password", "testapi123");
		headersMap.put("Authorization", "Bearer abc123456");
		
		httpResponse = restClient.getMethod(baseUrl, basePath, headersMap);
		
		int statusCode = restClient.getResponseStatus();
		softAssert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
		
		restClient.getResponseJson();
		restClient.getResponseHeaders();
		
		/*RestAssured provides JSON Path and XML Path to parse the Response.*/
		System.out.println("*******RESPONSE PARSING********* ");
		System.out.println(httpResponse.jsonPath().get("total_pages"));
		System.out.println(httpResponse.jsonPath().get("data[0].email"));
		System.out.println(httpResponse.jsonPath().get("data"));
		System.out.println(httpResponse.jsonPath().getList("data"));
		
		softAssert.assertAll();
		
		
	}

}



























