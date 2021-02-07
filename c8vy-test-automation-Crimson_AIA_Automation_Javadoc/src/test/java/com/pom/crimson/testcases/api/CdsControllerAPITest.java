package com.pom.crimson.testcases.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.CdsControllerAPI;
import com.pom.crimson.base.BaseFixtureForAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

import io.restassured.response.Response;

/**
 * This class contains test methods to test cds-controller APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class CdsControllerAPITest extends BaseFixtureForAPI{

	
	CdsControllerAPI CCA;
	LogAssert logAssert;
		
	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */
	@BeforeClass()
	public void beforeClass() {
		CCA = new CdsControllerAPI();
	}

	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}

	@Test(testName="Cds Controller - Get Cds Entity",description="Cds Controller - Get Cds Entity")
	public void verifyCdsEntityAPI() throws Exception
	
	{					
			ExtentReportManager.getTest().log(Status.INFO,
					"Cds Controller - Get Cds Controller API test");
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /cds/{entity}");
			
			Response respGetContact = CCA.getCdsEntity("contacts", "$select=fullname");			
					
			logAssert.assertEquals(respGetContact.statusCode(), 200, "Verify if the Response code is \"200\"");
			
			String Content = respGetContact.jsonPath().getString("value.fullname");			
			logAssert.assertNotNull(Content,"Cds Entity details retrieved should not be empty.");
	} 
		
}