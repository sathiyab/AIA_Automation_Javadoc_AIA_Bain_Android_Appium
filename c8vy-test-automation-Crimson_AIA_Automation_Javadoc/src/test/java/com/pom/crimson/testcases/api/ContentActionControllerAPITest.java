package com.pom.crimson.testcases.api;

import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.ContentActionControllerAPI;
import com.pom.crimson.api.ContentControllerAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;

/**
 * This class contains test methods to test content-action-controller APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class ContentActionControllerAPITest extends BaseFixtureForAPI{

	ContentActionControllerAPI CAC;
	UserProfileControllerAPI UPC;
	ContentControllerAPI CC;
	LogAssert logAssert;
	Boolean isHomePageContentAvailalbe; 
	
	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass()
	{
		CAC = new ContentActionControllerAPI();
		UPC = new UserProfileControllerAPI();
		CC = new ContentControllerAPI();
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

	@Test(priority = 1, enabled=true, testName="Content Action Controller - Bookmark a piece of content",description="Content Action Controller - Bookmark a piece of content")
	public void postBookmarkcontent() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /bookmark/{profileId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response respGetContent = CC.GetContentHomePage(ProfileID);					
			List<String> contentID = respGetContent.jsonPath().getList("id");							
			
			Response resp = CAC.PostBookmarkContent(ProfileID, contentID.get(0));	
			logAssert.assertTrue((resp.statusCode()==200 || resp.statusCode()==201), "Verify if the Response code is \"200\" or \"201\"");
			
			String bookmarkId = resp.jsonPath().getString("id");			
			logAssert.assertNotNull(bookmarkId, "Bookmarked content details retrieved in the response should not be empty.");
			
			logAssert.assertAll();
	}
	
	@Test(priority = 2, enabled=true, testName="Content Action Controller - Get Bookmarked content by ProfileId",description="Content Action Controller - Get Bookmarked content by ProfileId")
	public void GetBookmarkProfileId() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /bookmark/{profileId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}			
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response resp = CAC.GetBookmarkProfileId(ProfileID);
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			List<String> bookmarkId = resp.jsonPath().getList("id");
			
			logAssert.assertTrue(bookmarkId.size() > 0, "Bookmarked content list retrieved for the user's profile should not be empty.");
			
			logAssert.assertAll();
	}
	
	@Test(priority = 3, enabled=true, testName="Content Action Controller - Un-bookmark a piece of content",description="Content Action Controller - Un-bookmark a piece of content")
	public void putUnbookmarkProfileId() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /bookmark/{profileId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}			
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response resp = CAC.GetBookmarkProfileId(ProfileID);
			
			if (resp.statusCode()==200) {
				List<String> contentId = resp.jsonPath().getList("contentId");
				
				Response respUnbookmark = CAC.PutBookmarkContent(ProfileID, contentId.get(0));
				logAssert.assertTrue((resp.statusCode()==200 || resp.statusCode()==201), "Verify if the Response code is \"200\" or \"201\"");
				
				String bookmarkId = respUnbookmark.asString();			
				logAssert.assertNotNull(bookmarkId, "Unbookmarked details retrieved in the response should not be empty.");				
			}			
			else {
				ExtentReportManager.getTest().skip("No bookmarked contents available for unbookmarking.");
			}
			
			logAssert.assertAll();
	}	
	
	@Test(priority = 4, enabled=true, testName="Content Action Controller - Record viewing of a piece of content",description="Content Action Controller - Record viewing of a piece of content")
	public void postViewedcontent() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /contentView/{profileId}/{contentId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}			
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response respGetContent = CC.GetContentHomePage(ProfileID);
			List<String> contentID = respGetContent.jsonPath().getList("id");							
			
			Response resp = CAC.PostContentView(ProfileID, contentID.get(0));
			logAssert.assertTrue((resp.statusCode()==200 || resp.statusCode()==201), "Verify if the Response code is \"200\" or \"201\"");
			
			String contentId = resp.jsonPath().getString("contentId");			
			logAssert.assertNotNull(contentId, "Viewed content details retrieved in the response should not be empty.");
			
			logAssert.assertAll();
	}	
	
	@Test(priority = 5, enabled=true, testName="Content Action Controller - Get Content History",description="Content Action Controller - Get Content History")
	public void GetContentHistory() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /contentHistory/{profileId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}						
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response resp = CAC.GetContentHistory(ProfileID);
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			
			List<String> contentId = resp.jsonPath().getList("$");
			logAssert.assertTrue(contentId.size() > 0, "Content History list retrieved for the user should not be empty.");
			
			logAssert.assertAll();
	}
	
	@Test(priority = 6, enabled=true, testName="Content Action Controller - Like a piece of content",description="Content Action Controller - Like a piece of content")
	public void postLikecontent() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - ​/like​/{profileId}​/{contentId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}						
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response respGetContent = CC.GetContentHomePage(ProfileID);
			List<String> contentID = respGetContent.jsonPath().getList("id");							
			
			Response resp = CAC.PostLikeContent(ProfileID, contentID.get(0));	
			logAssert.assertTrue((resp.statusCode()==200 || resp.statusCode()==201), "Verify if the Response code is \"200\" or \"201\"");
			
			String bookmarkId = resp.jsonPath().getString("id");			
			logAssert.assertNotNull(bookmarkId, "Liked content details retrieved in the response should not be empty.");
			
			logAssert.assertAll();
	}	
	
	@Test(priority = 7, enabled=true, testName="Content Action Controller - Get a list of likes for a profile",description="Content Action Controller - Get a list of likes for a profile")
	public void GetLikeProfileId() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /like/{profileId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}						
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response resp = CAC.GetLikeProfileId(ProfileID);
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			List<String> contentId = resp.jsonPath().getList("contentId");
			
			logAssert.assertTrue(contentId.size() > 0, "Liked Content list retrieved from the API should not be empty.");
			logAssert.assertAll();
	}
	
	@Test(priority = 8, enabled=true, testName="Content Action Controller - Un-like a piece of content",description="Content Action Controller - Un-like a piece of content")
	public void putUnlikeContent() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - ​/like​/{profileId}​/{contentId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}						
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response resp = CAC.GetLikeProfileId(ProfileID);
			
			if (resp.statusCode()==200) {
				List<String> contentId = resp.jsonPath().getList("contentId");
				
				Response respUnLike = CAC.PutLikeContent(ProfileID, contentId.get(0));
				logAssert.assertTrue((resp.statusCode()==200 || resp.statusCode()==201), "Verify if the Response code is \"200\" or \"201\"");
				
				String Id = respUnLike.asString();			
				logAssert.assertNotNull(Id, "Unliked content ID details retrieved in the response should not be empty.");				
			}			
			else {
				ExtentReportManager.getTest().skip("No liked contents available for the user.");
			}
			
			logAssert.assertAll();
	}
	
	@Test(priority = 9, enabled=true, testName="Content Action Controller - Record sharing of a piece of content",description="Content Action Controller - Record sharing of a piece of content")
	public void postcontentShare() throws Exception
	{
			ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /share/{profileId}/{contentId}");
			
			if (!isHomePageContentAvailalbe){
				ExtentReportManager.getTest().fail("There are no recommended contents available in the Homepage, so cannot proceed with the test.");
			}						
			
			Response respGetProfile = UPC.GetProfiles();
			String ProfileID = respGetProfile.jsonPath().getString("contactid");
			
			Response respGetContent = CC.GetContentHomePage(ProfileID);
			List<String> contentID = respGetContent.jsonPath().getList("id");							
			
			Response resp = CAC.PostContentSharing(ProfileID, contentID.get(0));
			logAssert.assertTrue((resp.statusCode()==200 || resp.statusCode()==201), "Verify if the Response code is \"200\" or \"201\"");
			
			String contentId = resp.jsonPath().getString("contentId");			
			logAssert.assertNotNull(contentId, "Shared content details retrieved in the response should not be empty.");
			
			logAssert.assertAll();
	}	
	
}
