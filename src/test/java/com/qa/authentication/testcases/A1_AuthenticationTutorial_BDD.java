package com.qa.authentication.testcases;

import io.restassured.RestAssured;
import org.testng.annotations.Test;


/*
 * "Authentication" means verifying the identity of a user or application attempting to access an API. It basically tries to answer the question, “Who are you?” .
    Common authentication methods include Basic Auth, Digest Auth,  OAUTH, API keys, Bearer tokens, and more.
   
   "Authorization" usually happens after authentication and is the process of granting or denying access based on a user’s or application’s permissions. It basically answers the question, “What are you allowed to do?”.

 * 401 Unauthorised: The client needs to provide valid credentials to proceed.
   403 Forbidden: Client’s authentication credentials are valid, but they don’t have the necessary permissions to access the requested resource.
*/
public class A1_AuthenticationTutorial_BDD {
	
	@Test
	public void basicAuthTest() {
		/*The basic authentication scheme secures web-based applications using a username and password in base64 encoded format. 
		 * When sending API requests, the request header needs to contain the user credentials.
		 */			
		System.out.println("***************** BASIC Authentication");		
		RestAssured
		.given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.log().all();	
		
		/*By default, Rest Assured uses the challenge-response mechanism. This means that it waits for the server to challenge rather than send the credentials directly. 
		 * By using the preemptive directives we can avoid that additional call that the server makes.
		 */
		System.out.println("***************** PREEMPTIVE BASIC Authentication");
		RestAssured
		.given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.log().all();	
	}
	
	@Test
	public void digestAuthTest() {
		/*It is somewhat similar to challenge-based Basic authentication but is more secure as it uses a digestive key in subsequent requests.
		 */		
		System.out.println("***************** DIGEST Authentication");		
		RestAssured
		.given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/digest-auth")
		.then()
			.statusCode(200)
			.log().all();	
		
	}
	
	@Test
	public void bearerTokenAuthTest() {
				
		System.out.println("***************** Bearer Token Authentication");		
		
		String bearerToken = "Usually retrieved by hitting initial API";
		
		RestAssured
		.given()
			.header("Authorization", "Bearer" + bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();	
		
	}
	
	@Test
	public void OAuth1Test() {
				
		System.out.println("***************** OAuth1 Authentication");		
		
		RestAssured
		.given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();	
		
	}
	
	@Test
	public void OAuth2Test() {
				
		System.out.println("***************** OAuth2 Authentication");		
		
		RestAssured
		.given()
			.auth().oauth2("accessToken")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();	
		
	}

}
