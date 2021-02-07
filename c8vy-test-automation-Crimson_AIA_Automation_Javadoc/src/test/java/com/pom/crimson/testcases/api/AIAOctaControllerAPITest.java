package com.pom.crimson.testcases.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.crimson.api.*;
import com.pom.crimson.base.BaseFixtureForAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

import io.restassured.response.Response;

import com.aventstack.extentreports.Status;

/**
 * This class contains test methods to test aia-okta-controller APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class AIAOctaControllerAPITest extends BaseFixtureForAPI {

	AIAOctaControllerAPI AOC;
	UserProfileControllerAPI UPC;
	LogAssert logAssert;
		
	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */
	@BeforeClass()
	public void beforeClass()
	{
		AOC = new AIAOctaControllerAPI();
		UPC = new UserProfileControllerAPI();
	}
	
	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}		
	
	@Test(priority = 1, enabled=true, testName="AIA Octa Controller - Get Users",description="AIA Octa Controller - Get Users")
	public void verifyGetUserAPI() throws Exception
	{		
		
			ExtentReportManager.getTest().log(Status.INFO, "AIA Octa Controller - Get Users");
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /users");
					
			Response resp = UPC.GetProfiles();
			String EmailId = resp.jsonPath().getString("isUserCreated");
					
			Response emialId = AOC.GetUsers(EmailId);
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");

			String Content = emialId.jsonPath().getString("description ");
			logAssert.assertNotNull(Content, "User details retrieved should not be empty.");				
	}	
	
	@Test(priority = 2, enabled=false, testName="AIA Octa Controller - Get Users UserId",description="AIA Octa Controller - Get Users UserId")
	public void verifyGetUserUserIdAPI() throws Exception
	{		
		
			ExtentReportManager.getTest().log(Status.INFO, "AIA Octa Controller - Get Users UserId");
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /users/{userid}");
					
			Response resp = UPC.GetProfiles();
			String USERID = resp.jsonPath().getString("userId");
					
			Response UId = AOC.GetUsersUserId(USERID);
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			
			String Content = UId.jsonPath().getString("type");			
			logAssert.assertNotNull(Content, "User details retrieved should not be empty.");				
	}	

	@Test(priority = 3, enabled=false, testName="AIA Octa Controller - Get Logout",description="AIA Octa Controller - Get Logout")
	public void verifyGetLogoutAPI() throws Exception
	{
		
			ExtentReportManager.getTest().log(Status.INFO, "AIA Octa Controller - Get Logout API test");
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /logout");
			
			Response resp = AOC.GetLogout();
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
			String UserId = resp.jsonPath().getString("type");
			logAssert.assertNotNull(UserId,"User should be Logged out successfully.");

	} 
	
}
