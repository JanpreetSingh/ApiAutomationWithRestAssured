package com.qa.authentication.testcases;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



import org.testng.Assert;
import org.testng.annotations.Test;


public class BasicAuthTestTutorial {
	
	/*Test GET method without Headers.*/
	@Test
	public void basicAuthTest() {
				
		/*RestAssured will take the Final API URL as the concatenation of the
		the baseURI and basePath.
		* Otherwise, the URI can even be specified in the request(get, post .....) method. */
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
		RestAssured.basePath = "";
		
//		PREEMPTIVE BASIC Authentication		
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();

//		BASIC Authentication		
//		BasicAuthScheme autScheme = new BasicAuthScheme();
		
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
		RestAssured.authentication = authScheme;
		
//		Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
//		Response Object
		Response httpResponse = httpRequest.get();
		
		
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status Code -----> " + statusCode);
		
		String responseJsonString = httpResponse.getBody().asString();
		System.out.println("Response body String ------>" + responseJsonString);
		
		Assert.assertEquals(statusCode, 200);
		
		
	}

}
