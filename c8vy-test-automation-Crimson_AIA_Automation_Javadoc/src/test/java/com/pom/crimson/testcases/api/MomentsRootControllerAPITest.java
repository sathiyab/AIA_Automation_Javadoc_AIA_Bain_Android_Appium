package com.pom.crimson.testcases.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.MomentsRootControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test moments-root API.
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class MomentsRootControllerAPITest extends BaseFixtureForAPI {

	MomentsRootControllerAPI MRC;
	LogAssert logAssert;

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass() {
		MRC = new MomentsRootControllerAPI();
	}

	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}

	@Test(testName = "Moments Root Controller - Get Moments Root Controller", description = "Moments Root Controller - Get Moments Root Controller")
	public void verifyGetMomentsRootControllerAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO,
				"Moments Root Controller - Get Moments Root Controller API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /root");

		Response resp = MRC.GetMomentsRootController();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		System.out.println(resp.asString());
		// List<String> Rootcontroller = resp.jsonPath().getList("Welcome to Crimson
		// Content Service");

		// logAssert.assertTrue(Rootcontroller.size() > 0);
	}

}
