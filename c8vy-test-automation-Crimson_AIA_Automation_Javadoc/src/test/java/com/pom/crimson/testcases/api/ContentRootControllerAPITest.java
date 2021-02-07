package com.pom.crimson.testcases.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.ContentRootControllerAPI;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test content-root API
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class ContentRootControllerAPITest extends BaseFixtureForAPI {

	ContentRootControllerAPI CRC;
	LogAssert logAssert;

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass() {
		CRC = new ContentRootControllerAPI();
	}

	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}

	@Test(testName = "Content Root Controller - Get Content Root Controller", description = "Content Root Controller - Get Content Root Controller")
	public void verifyGetContentRootControllerAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /root");
		Response resp = CRC.GetContentRootController();
		
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		logAssert.assertTrue(resp.asString().contains("Welcome"), "Welcome message is displayed successfully.");
		logAssert.assertAll();
	}

}
