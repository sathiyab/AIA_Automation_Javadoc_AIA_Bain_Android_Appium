package com.pom.crimson.testcases.api;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.ContentControllerAPI;
import com.pom.crimson.api.MasterDataControllerAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test content-controller APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class ContentControllerAPITest extends BaseFixtureForAPI {

	ContentControllerAPI CC;
	UserProfileControllerAPI UPC;
	MasterDataControllerAPI MDC;
	LogAssert logAssert;
	Boolean isHomePageContentAvailalbe;	

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass() {
		CC = new ContentControllerAPI();
		UPC = new UserProfileControllerAPI();
		MDC = new MasterDataControllerAPI();
		
		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");
		
		Response respGetContent = CC.GetContentHomePage(ProfileID);
		List<String> contentID = respGetContent.jsonPath().getList("id");
		
		if (contentID.size() > 0) {
			isHomePageContentAvailalbe = true;
		}
		else
		{
			isHomePageContentAvailalbe = false;
		}		
	}

	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}

	@Test(priority = 1, enabled=true, testName = "Content Controller - Gets a specific piece of content based on its Strapi ID", description = "To verify Content Controller - Gets a specific piece of content based on its Strapi ID")
	public void verifyGetContentIdAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /content/{contentId}");
		
		if (!isHomePageContentAvailalbe){
			ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
		}					

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");
		
		Response resp = CC.GetContentHomePage(ProfileID);
		List<String> contentList = resp.jsonPath().getList("id");				

		Response respContent = CC.GetContentId(contentList.get(0));
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		String Content = respContent.jsonPath().getString("id");		
		logAssert.assertNotNull(Content, "Content details retrieved should not be empty.");
		logAssert.assertAll();
	}

	@Test(priority = 2, enabled=true, testName = "Content Controller - Gets a daily tip for the given profile, if one exists", description = "To verify Content Controller - Gets a daily tip for the given profile, if one exists")
	public void verifyGetContentDailyTipProfileIdAPI() throws Exception {
        ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /content/daily-tip/{profileId}");

        Response respGetProfile = UPC.GetProfiles();
        String ProfileID = respGetProfile.jsonPath().getString("contactid");

        Response resp = CC.GetContentDailyTipProfileId(ProfileID);
        if (resp.statusCode() == 404) {
            ExtentReportManager.getTest().log(Status.SKIP, "There are no daily tips available for this profile");
        }
        else {
            logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
            String dailyTip = resp.jsonPath().getString("body");
            logAssert.assertTrue(!dailyTip.isEmpty(), "Daily tip details retrieved should not be empty.");            
        }
		logAssert.assertAll();
    }

	@Test(priority = 3, enabled=true, testName = "Content Controller - Fetch recommended content based on a user's joined groups.", description = "To verify Content Controller - Fetch recommended content based on a user's joined groups.")
	public void verifyGetContentGroupProfileIdAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /content/group/{profileId}");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = CC.GetContentGroupProfileId(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");

		List<String> profileID = resp.jsonPath().getList("id");
		if (profileID.size() > 0) {
			logAssert.assertTrue(profileID.size() > 0, "Content details retrieved is not empty.");			
		}

		logAssert.assertAll();
	}
	
	@Test(priority = 4, enabled=true, testName = "Content Controller - Fetch recommended content for the homepage", description = "To verify Content Controller - Fetch recommended content for the homepage")
	public void verifyGetContentHomePageAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /content/homepage");
		
		if (!isHomePageContentAvailalbe){
			ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
		}							

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = CC.GetContentHomePage(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> Contents = resp.jsonPath().getList("id");
		logAssert.assertTrue(Contents.size() > 0, "Content details retrieved should not be empty.");
		logAssert.assertAll();
	}

	@Test(priority = 5, enabled=true, testName = "Content Controller - Gets a list of content based on given Strapi IDs.", description = "To verify Content Controller - Gets a list of content based on given Strapi IDs.")
	public void verifyGetContentIdsAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /content/ids");
		
		if (!isHomePageContentAvailalbe){
			ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
		}							

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");
		
		Response resp = CC.GetContentHomePage(ProfileID);
		List<String> contentID = resp.jsonPath().getList("id");
		
		Response respContent = CC.GetContentIds(contentID.get(0));
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		String Content = respContent.jsonPath().getString("id");		
		logAssert.assertNotNull(Content, "Content details retrieved should not be empty.");
		logAssert.assertAll();
	}

	@Test(priority = 6, enabled=true, testName = "Content Controller - Fetch recommended content based on a user's interests.", description = "To verify Content Controller - Fetch recommended content based on a user's interests.")
	public void verifyGetContentProfileIdAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /content/profile/{profileId}");

		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");

		Response resp = CC.GetContentProfileId(ProfileID);
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> profileID = resp.jsonPath().getList("id");
		logAssert.assertTrue(profileID.size() > 0, "Content details retrieved should not be empty.");
		logAssert.assertAll();
	}

	@Test(priority = 7, enabled=true, testName = "Content Controller - Fetch recommended content based on a specific interest", description = "To verify Content Controller - Fetch recommended content based on a specific interest")
	public void verifyGetContentInterestNameAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /content/interest/{interestName}");
		
		Response respGetProfile = UPC.GetProfiles();
		String ProfileID = respGetProfile.jsonPath().getString("contactid");		

		Response resp = MDC.GetInterests();
		List<String> Interests = resp.jsonPath().getList("aiabase_name");

		Response respcontent = CC.GetContentInterestName(ProfileID, Interests.get(0));
		logAssert.assertEquals(respcontent.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> contentID = resp.jsonPath().getList("id");
		logAssert.assertTrue(contentID.size() > 0, "Content details retrieved should not be empty.");
		logAssert.assertAll();
	}

}
