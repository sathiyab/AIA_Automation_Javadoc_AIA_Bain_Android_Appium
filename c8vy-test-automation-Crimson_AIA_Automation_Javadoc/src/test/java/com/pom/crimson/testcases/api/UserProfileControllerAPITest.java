package com.pom.crimson.testcases.api;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test user-profile-controller APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class UserProfileControllerAPITest extends BaseFixtureForAPI {

	UserProfileControllerAPI UPC;
	LogAssert logAssert;

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass() {
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

	@Test(priority = 1, enabled=true, testName = "User Profile Controller - Get all Profiles", description = "User Profile Controller - Get all Profiles")
	public void verifyGetProfilesAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "User Profile Controller - Get all Profiles API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /profiles");

		Response resp = UPC.GetProfiles();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		String Profile = resp.jsonPath().getString("contactType");
		logAssert.assertNotNull(Profile, "Profile details retrieved is not empty.");

	}

	@Test(priority = 2, enabled=true, testName = "User Profile Controller - Get all Profiles Arche Types", description = "User Profile Controller - Get all Profiles Arche Types")
	public void verifyGetProfilesArcheTypesAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO,
				"User Profile Controller - Get all Profiles Arche Types API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /profiles/{profileid}/archetypes");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = UPC.GetProfilesArcheTypes(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> Profilearchetypes = resp.jsonPath().getList("_aiabase_archetypeid_value");

		logAssert.assertTrue(Profilearchetypes.size() > 0, "Archetype details retrieved is not empty.");
	}

	@Test(priority = 3, enabled=true, testName = "User Profile Controller - Get all Profiles Contact Benefits", description = "User Profile Controller - Get all Profiles Contact Benefits")
	public void verifyGetProfilesContactbenefitsAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO,
				"User Profile Controller - Get all Profiles Contact Benefits API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /profiles/{profileid}/contactbenefits");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = UPC.GetProfilesContactBenefits(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		//List<String> Profilecontactbenefits = resp.jsonPath().getList("");

		//logAssert.assertTrue(Profilecontactbenefits.size() > 0, "Benefit details retrieved is not empty.");
	}

	
	  @Test(priority = 4, enabled=true, testName="User Profile Controller - Get all Profiles Images", description="User Profile Controller - Get all Profiles Images") 
	  public void  verifyGetProfilesImageAPI() throws Exception {
	  ExtentReportManager.getTest().log(Status.INFO,
	  "User Profile Controller - Get all Profiles Images API test");
	  ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /profiles/{profileid}/image");
	  
	  Response respGetProfile = UPC.GetProfiles(); 
	  String ProfileID = respGetProfile.jsonPath().getString("contactid");
	  
	  Response resp = UPC.GetProfilesImage(ProfileID); 
	  logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
	  
	  String ProfileImage = resp.jsonPath().getString("message");
	  logAssert.assertNotNull(ProfileImage,"ProfileId details retrieved is not empty.");
	  
	  }
	 

	@Test(priority = 5, enabled=true, testName = "User Profile Controller - Get all Profiles Interests", description = "User Profile Controller - Get all Profiles Interests")
	public void verifyGetProfilesInterestsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "User Profile Controller - Get all Profiles Interests API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /profiles/{profileid}/interests");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = UPC.GetProfilesInterests(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> ProfilesInterests = resp.jsonPath().getList("_aiabase_interestid_value");

		logAssert.assertTrue(ProfilesInterests.size() > 0, "Interest details retrieved is not empty.");

	}

	@Test(priority = 6, enabled=true, testName = "User Profile Controller - Get all Profiles NPS", description = "User Profile Controller - Get all Profiles NPS")
	public void verifyGetProfilesNpsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "User Profile Controller - Get all Profiles NPS API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /profiles/{profileid}/nps");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = UPC.GetProfilesNps(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		// List<String> ProfilesNPS =
		// resp.jsonPath().getList("_aiabase_contactid_value");

		// logAssert.assertTrue(ProfilesNPS.size() > 0);
	}

	@Test(priority = 7, enabled=true, testName = "User Profile Controller - Get all Profiles Related Persons", description = "User Profile Controller - Get all Profiles Related Persons")
	public void verifyGetProfilesRelatedPersonsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO,
				"User Profiles Controller - Get all Profiles Related Persons API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /profiles/{profileid}/relatedpersons");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = UPC.GetProfilesRelatedPersons(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> ProfilesRelatedPersons = resp.jsonPath().getList("contactsList");

		logAssert.assertTrue(ProfilesRelatedPersons.size() > 0, "Related persons retrieved is not empty.");
	}

}