package com.pom.crimson.testcases.api;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.MilestonesControllerAPI;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test milestones-controller APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class MilestonesControllerAPITest extends BaseFixtureForAPI {

	MilestonesControllerAPI MCA;
	LogAssert logAssert;
	
	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass()
	{
		MCA = new MilestonesControllerAPI();		
	}
	
	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}			

	@Test(priority = 1, enabled=true, testName="Moments Controller - Get Milestones Controller",description="Milestones Controller - Get Milestones Controller")
	public void verifyGetMilestonesAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /milestones");

		Response resp = MCA.GetMilestones("MILESTONE");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");

		List<String> Milestone = resp.jsonPath().getList("id");
		logAssert.assertTrue(Milestone.size() > 0, "Milestone list retrieved is not empty.");

	}
	
	@Test(priority = 2, enabled=true, testName="Moments Controller - Get Vaccine Controller",description="Milestones Controller - Get Vaccine Controller")
	public void verifyGetVaccineAPI() throws Exception
	{
		
			ExtentReportManager.getTest().log(Status.INFO, "Moments Controller - Get Vaccine Controller API test");
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /milestones");
			
						
			Response resp = MCA.GetMilestones("VACCINE");
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");		
					
			//List<String> Vaccine = resp.jsonPath().getList("id");
            //logAssert.assertTrue(Vaccine.size() > 0, "Vaccine list retrieved is not empty.");
			System.out.println(resp.asString());
		
	}
	
}
