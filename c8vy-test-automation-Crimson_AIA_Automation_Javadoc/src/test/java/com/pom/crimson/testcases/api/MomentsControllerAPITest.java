package com.pom.crimson.testcases.api;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.MomentsControllerAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test moments-controller APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class MomentsControllerAPITest extends BaseFixtureForAPI {

	MomentsControllerAPI MC;
	UserProfileControllerAPI UPC;
	LogAssert logAssert;

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass() {
		MC = new MomentsControllerAPI();
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

	@Test(testName = "Content Controller - Get Moments Controller Profile Id", description = "Content Controller - Get Moments Controller Profile Id")
	public void GetMomentsProfileIdAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO,
				"Moments Controller - Get Moments Controller Profile Id API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /moments/{profileId}");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = MC.GetMomentsProfileId(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		//List<String> profileId = resp.jsonPath().getList("id");

		//logAssert.assertTrue(profileId.size() > 0, "Moments retrieved is not empty.");
	}

}
