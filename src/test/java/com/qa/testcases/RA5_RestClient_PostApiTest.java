package com.qa.testcases;

import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class RA5_RestClient_PostApiTest extends TestBase{
	
	TestBase testBase;
	String baseUrl;
	String basePath;
	SoftAssert softAssert;
	RestClient restClient;
	Response httpResponse;
	
	@BeforeMethod
	public void setUp() throws IOException {
		testBase = new TestBase();
		
		baseUrl= prop.getProperty("baseURLPost");
		basePath= prop.getProperty("basePathPost");
		
		softAssert = new SoftAssert(); 
				
	}
	
	
	
	
	/*Test POST method.*/
	@Test
	public void postApiMethodTest() throws ClientProtocolException, IOException {
		
		RestClient restClient = new RestClient();
		
//		Create Request Headers.
		HashMap<String, String> headersMap = new HashMap<>();
		headersMap.put("Content-Type", "application/json");
		headersMap.put("user", "test@api.com");
		headersMap.put("password", "testapi123");
		headersMap.put("Auth Token", "123456");
		
		String payload = "{ \"id\": 311, \"title\": \"json-server311\", \"author\": \"typicode311\" }" ;
		
		httpResponse = restClient.postMethod(baseUrl, basePath, payload, headersMap);
		
		int statusCode = restClient.getResponseStatus();
		softAssert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);
		
		restClient.getResponseJson();
		restClient.getResponseHeaders();
		
		softAssert.assertAll();
		
		
	}

}



























