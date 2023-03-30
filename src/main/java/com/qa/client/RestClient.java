package com.qa.client;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class RestClient {
	
	Response httpResponse;
	
	/*GET method without Headers.
	 * Returns Response object*/
	public Response getMethod(String baseURL, String basePath) {
		
		/*RestAssured will take the Final API URL as the concatenation of the
		the baseURI and basePath.
		* Otherwise, the URI can even be specified in the request(get, post .....) method. */
		RestAssured.baseURI = baseURL;
		RestAssured.basePath = basePath;
		
//		Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
//		Response Object
		httpResponse = httpRequest.get();
		
		return httpResponse;
				
	}
	
	
	/*GET method with Headers.
	 * Returns Response object*/
	public Response getMethod(String baseURL, String basePath, HashMap<String, String> headerMap) {
		
		/*RestAssured will take the Final API URL as the concatenation of the
		the baseURI and basePath.
		* Otherwise, the URI can even be specified in the request(get, post .....) method. */
		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "";
		
//		Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			
			String key = entry.getKey();
			String value = entry.getValue();
//			Add HEADER to GET request.
			httpRequest.header(key, value);
		}
		
//		Alternate Syntax to make any request(GET, POST, DELETE ....) to the API.
//		Specify basePath explicitly in the request(get, post .....) method.		
		httpResponse = httpRequest.request(Method.GET, "/api/users");
		
		return httpResponse;
				
	}
	
	
/*	POST method with Headers.
	* Returns Response object */
	public Response postMethod(String baseURL, String basePath, String payload, HashMap<String, String> headerMap) {
		
		/*RestAssured will take the Final API URL as the concatenation of the
		the baseURI and basePath.
		* Otherwise, the URI can even be specified in the request(get, post .....) method. */
		RestAssured.baseURI = baseURL;
		RestAssured.basePath = basePath;
		
//		Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		
		/*for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			
			String key = entry.getKey();
			String value = entry.getValue();
//			Add HEADER to POST request.			
			httpRequest.header(key, value);
		}*/
		
//		Alternate way to add HEADERS		
		httpRequest.headers(headerMap);
		
//		Add body PAYLOAD to POST request. You can also pass the PAYLOAD as JSON Object.
		httpRequest.body(payload);
		
		httpResponse = httpRequest.post();
/*//	Alternate Syntax to make any request(GET, POST, DELETE ....) to the API.  		
		httpResponse = httpRequest.request(Method.POST);
		*/
		
		return httpResponse;
				
	}
	
	
	/*Get response STATUS*/
	public int getResponseStatus() {
		/*Get response STATUS*/
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status Code -----> " + statusCode);
		System.out.println("Status Line ----->" + httpResponse.getStatusLine());
		System.out.println("Status Code -----> " + httpResponse.statusCode());
		System.out.println("Status Line ----->" + httpResponse.statusLine());
		
		return statusCode;
		
	}
	
	/*Get response JSON*/
	public JSONObject getResponseJson() throws ParseException, IOException {
		/*Get response JSON*/
		String responseJsonString = httpResponse.getBody().asString();
		System.out.println("Response body String ------>" + responseJsonString);
		System.out.println("Response body String ------>" + httpResponse.body().asString());
		
		JSONObject responseJson = new JSONObject(responseJsonString);
		System.out.println(" JSON Response from API ----> " + responseJson);
		
		return responseJson;
	}
	
	/*Get response HEADERS*/
	public HashMap<String, String> getResponseHeaders() {
		/*Get response HEADERS*/
		Headers headers = httpResponse.getHeaders();
		System.out.println("Response Headers ----> " + headers);
		System.out.println("Response Headers ----> " + httpResponse.headers());
		
		HashMap<String, String> headersMap = new HashMap<String, String>();
		
		for (Header header : headers) {
			headersMap.put(header.getName(), header.getValue());
		}
		
		System.out.println("All the HEADERS in hashMap ---> " + headersMap);
		
		return headersMap;
	}
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


