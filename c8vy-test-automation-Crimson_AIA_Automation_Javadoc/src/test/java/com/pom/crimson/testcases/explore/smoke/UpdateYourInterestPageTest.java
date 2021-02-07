package com.pom.crimson.testcases.explore.smoke;

import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;


import com.aventstack.extentreports.Status;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddMoreInterestPage;
import com.pom.crimson.pages.BasedOnYourGroupsPage;
import com.pom.crimson.pages.ContentPage;
import org.openqa.selenium.TakesScreenshot;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.FilteredInterestResultPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.ManageProfilePage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.restassured.response.Response;



/**
 * This test class contains test cases for Your preferred interests page.<br>
 * AddMoreInterestPage or Your preferred interests page can be used to add more interests to profile.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * Go to Home Page<br>Click <b>Content</b> in bottom menu<br>Go to <b>Update your interests</b> section on Content Page <br>Click <b>See all</b> link 
 * <br><br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class UpdateYourInterestPageTest extends BaseFixture {

	
	HomePage homePage;
	ExplorePage explorePage;
	DiscoverTopicsPage discoverTopicsPage;
	FilteredInterestResultPage filteredInterestResultPage;
	GenericFunctionsAPI genericFunctionsAPI;
	LoginPage loginPage;
	UserProfileControllerAPI userProfileController;
	ProfilePage profilePage;
	ManageProfilePage manageProfilePage;
	
	@BeforeMethod()
	public void beforeLocalMethod()
	{
		genericFunctionsAPI=new GenericFunctionsAPI();
		userProfileController= new UserProfileControllerAPI();
		loginPage=new LoginPage(getDriver(),getPlatformName());
		
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		homePage=new HomePage(getDriver(),getPlatformName());
	}
	
	
	@Test(enabled=true,priority=1,testName="UpdateYourInterestPage-TestDataCreation-Associate three interests with User via API",
			description="UpdateYourInterest-TestDataCreation-Associate three interests with User via API and check if interests are displayed on Manage Profile page")
	public void testDataCreationAssociatedTwoInterest_UpdateYourInterestPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		String profileID=genericFunctionsAPI.getProfileId();
		Response resGetAllInterest=userProfileController.getAllInterests();
		List<String> id1,id2,id3, interests;
		
		if (resGetAllInterest.statusCode() == 200) {
			// Get id of Nutrition & Fitness, Financial planning & Preparing for Birth
			String json=resGetAllInterest.getBody().asString();
			id1=com.jayway.jsonpath.JsonPath.parse(json).read("$.[?(@.aiabase_name=='"+Constants.INTERESTS_NUTRITION_FITNESS+"')].aiabase_interestid");
			id2=com.jayway.jsonpath.JsonPath.parse(json).read("$.[?(@.aiabase_name=='"+Constants.INTERESTS_FINANCIAL_PLANNING+"')].aiabase_interestid");
			id3=com.jayway.jsonpath.JsonPath.parse(json).read("$.[?(@.aiabase_name=='"+Constants.INTERESTS_PREPARING_FOR_BIRTH+"')].aiabase_interestid");	
		} else {
			id1 = Collections.emptyList();
			id2 = Collections.emptyList();
			id3 = Collections.emptyList();
		}
		
	//Associate Interest with User Profile	
	Response resAssociateInterest=userProfileController.associateInterestToContact(id1.get(0), id2.get(0), id3.get(0), profileID);
	
	
	if (!(resAssociateInterest.statusCode() == 200)) {
		throw new SkipException("Can't Associate Interest with Profile as status code is: "+resAssociateInterest.getStatusCode()+"\n Response is: "+resAssociateInterest.getBody().asString());
	}
	Thread.sleep(2000);
	
	Response resAssociatedInterest=userProfileController.getInterestsAssociatedWithContact(profileID);
	
	if (resAssociatedInterest.statusCode() == 200) {
		String json=resAssociatedInterest.getBody().asString();
		interests=com.jayway.jsonpath.JsonPath.parse(json).read("$..['int.aiabase_name']");	
	} else {
		interests=Collections.emptyList();
		
	}
	System.out.println("Interest associated with contact: "+interests);
	logAssert.assertTrue(interests.contains(Constants.INTERESTS_NUTRITION_FITNESS),"Check if "+Constants.INTERESTS_NUTRITION_FITNESS+" interest is set for user via API ");
	logAssert.assertTrue(interests.contains(Constants.INTERESTS_FINANCIAL_PLANNING),"Check if "+Constants.INTERESTS_FINANCIAL_PLANNING+" interest is set for user via API");
	logAssert.assertTrue(interests.contains(Constants.INTERESTS_PREPARING_FOR_BIRTH),"Check if "+Constants.INTERESTS_PREPARING_FOR_BIRTH+" interest is set for user via API ");

     profilePage= homePage.goToProfilePage();
     manageProfilePage = profilePage.goToManageProfilepage();
	logAssert.assertTrue(manageProfilePage.isElementDisplayed(Constants.INTERESTS_NUTRITION_FITNESS),"Check if "+Constants.INTERESTS_NUTRITION_FITNESS+" is  displayed");
	logAssert.assertTrue(manageProfilePage.isElementDisplayed(Constants.INTERESTS_FINANCIAL_PLANNING),"Check if "+Constants.INTERESTS_FINANCIAL_PLANNING+" is  displayed");
	logAssert.assertTrue(manageProfilePage.isElementDisplayed(Constants.INTERESTS_PREPARING_FOR_BIRTH),"Check if "+Constants.INTERESTS_PREPARING_FOR_BIRTH+" is  displayed");
	logAssert.assertAll();

	}
	
	@Test(enabled=true,priority=2,
			testName="UpdateYourInterestPage-Verify navigation to Update Interest Page",
			description="UpdateYourInterestPage-Verify functionality when user Taps on See all button on Update your Interests")
	public void verifyNavigationToUpdateInterestPage_UpdateYourInterestPageTest() throws Exception
	{
		// Go to Explore/Content page
		explorePage=homePage.goToExplorePage();
		// Go to Update your interests section and click See all link
		AddMoreInterestPage addMoreInterestPage=explorePage.goToAddMoreInterestPage();
		LogAssert logAssert=new LogAssert();
		logAssert.assertTrue(addMoreInterestPage.isElementDisplayed(Constants.YOUR_PREFERRED_INTERESTS)," Check if "+Constants.YOUR_PREFERRED_INTERESTS+" title is displayed");
		logAssert.assertTrue(addMoreInterestPage.isElementDisplayed(Constants.INTERESTS_FERTILITY),"Check if "+Constants.INTERESTS_FERTILITY+"  Interest is  displayed");
		logAssert.assertAll();
	}
	
	@Test(enabled=true,priority=3,testName="UpdateYourInterestPage-Verify Profile page when when user saves an interest on Add more Interests page ",
			description="UpdateYourInterestPage-Verify functionality on Profile page,  when user saves an interest on Add more Interests page")
	public void verifyUpdateInterestOnProfile_UpdateYourInterestPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		// Go to Explore/Content page
		explorePage=homePage.goToExplorePage();
		// Go to Update your interests section and click See all link
		AddMoreInterestPage addMoreInterestPage=explorePage.goToAddMoreInterestPage();
		// Find Red color value of interest
		int initialRedColor= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABYCARE);
		System.out.println("Initial red color value for "+Constants.INTERESTS_BABYCARE+" :"+initialRedColor);
		if (initialRedColor==238)
			{GenericFunctions.takeScreenshot(getDriver());
			ExtentReportManager.getTest().log(Status.INFO, Constants.INTERESTS_BABYCARE+" interest not previously selected");
			ExtentReportManager.getTest().log(Status.INFO, "Trying to Select "+Constants.INTERESTS_BABYCARE+" interest now");
			// Tap on Interest
			addMoreInterestPage.tapOnElementByText(Constants.INTERESTS_BABYCARE);
			// Tap Save my Interest button
			explorePage=addMoreInterestPage.tapOnSaveMyInterest();
			Thread.sleep(2000);
			// Go to Profile page
			profilePage=explorePage.goToProfilePage();
			// Go to Manage Profile page
			manageProfilePage=profilePage.goToManageProfilepage();
			Thread.sleep(2000);
			// Check if updated Interest is displayed in Manage Profile's Interest section
			logAssert.assertTrue(manageProfilePage.isElementDisplayed(Constants.INTERESTS_BABYCARE),"Check if Interest "+Constants.INTERESTS_BABYCARE+" is displayed on Manage Profile Page");
			}
		else if (initialRedColor==251)
			{GenericFunctions.takeScreenshot(getDriver());
			ExtentReportManager.getTest().log(Status.INFO, Constants.INTERESTS_BABYCARE+" interest already selected");
			ExtentReportManager.getTest().log(Status.INFO, "Unselecting Interest "+Constants.INTERESTS_BABYCARE);
			addMoreInterestPage.tapOnElementByText(Constants.INTERESTS_BABYCARE);
			GenericFunctions.takeScreenshot(getDriver());
			// Tap Save my Interest button
			explorePage=addMoreInterestPage.tapOnSaveMyInterest();
			Thread.sleep(2000);
			// Go to Profile page
			profilePage=explorePage.goToProfilePage();
			// Go to Manage Profile page
			manageProfilePage=profilePage.goToManageProfilepage();
			Thread.sleep(2000);
			// Check if updated Interest is displayed in Manage Profile's Interest section
			logAssert.assertFalse(manageProfilePage.isElementDisplayed(Constants.INTERESTS_BABYCARE),"Check if Interest "+Constants.INTERESTS_BABYCARE+" is not displayed on Manage Profile Page");	
			}
		logAssert.assertAll();
	}
	
	@Test(enabled=true,priority=4,testName="UpdateYourInterestPage-Verify cross button",
			description="UpdateYourInterestPage-Verify when user clicks on cross button")
	public void verifyCrossBtnOnUpdateInterestPage_UpdateYourInterestPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		// Go to Explore/Content page
		explorePage=homePage.goToExplorePage();
		// Go to Update your interests section and click See all link
		AddMoreInterestPage addMoreInterestPage=explorePage.goToAddMoreInterestPage();
		// Tap Cross button on Update your interests page
		explorePage=addMoreInterestPage.tapOnCrossBtn();
		// Scroll to section on Explore/Content page
		explorePage.scrollToSection(Constants.EXPLORE_TITLE);
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.EXPLORE_DISCOVER_TOPICS),"Check if "+Constants.EXPLORE_DISCOVER_TOPICS+" is displayed-Explore Page should be displayed");
		logAssert.assertAll();
	}
	
}
