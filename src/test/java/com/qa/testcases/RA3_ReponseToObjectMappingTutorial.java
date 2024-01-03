package com.qa.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.pojo.errors.LoginError;
import com.qa.pojo.users.UserDetails;
import com.qa.pojo.users.Users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RA3_ReponseToObjectMappingTutorial {

	RequestSpecification request;
	Response response;
	Logger logger;

	@BeforeClass
	public void setUp() {

		logger = Logger.getLogger("RestAssuredBasicsTutorial");
		PropertyConfigurator.configure("./log4j.properties");

		RestAssured.baseURI = "https://reqres.in/api";
		RestAssured.basePath = "";

	}

	@Test
	public void successResponseDeserialization() {

		request = RestAssured.given();
		response = request.get("/users");
		response.prettyPeek();

		logger.info("**********JSON response to Class object conversion*********************** ");
		Users users = response.as(Users.class);
		logger.info("Total Pages : " + users.getTotalPages());
		logger.info("List of Users : " + users.getListOfUsers());

	}

	@Test
	public void errorResponseDeserialization() {

		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body("");
		response = request.post("/login");
		response.prettyPeek();

		logger.info("**********JSON response to Class object conversion*********************** ");
		LoginError loginError = response.as(LoginError.class);
		logger.info("Login Error message : " + loginError.getError());

	}

}











