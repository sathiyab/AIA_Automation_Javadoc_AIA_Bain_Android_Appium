package com.pom.crimson.testcases.explore.high;

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
			
	@Test(enabled=true,priority=2,testName="UpdateYourInterestPage-Verify  when user saves an interest on Add more Interests page",
			description="when user saves an interest on Add more Interests page")
	public void verifyUpdateInterest_UpdateYourInterestPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Update your interests section and click see all
		AddMoreInterestPage addMoreInterestPage=explorePage.goToAddMoreInterestPage();
		// Get initial red color value for interest
		int initialRedColor= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABYCARE);
		System.out.println("Initial red color value"+initialRedColor);
		if (initialRedColor==238)
			{GenericFunctions.takeScreenshot(getDriver());
			ExtentReportManager.getTest().log(Status.INFO, Constants.INTERESTS_BABYCARE+" interest not previously selected");
			ExtentReportManager.getTest().log(Status.INFO, "Trying to select "+Constants.INTERESTS_BABYCARE+" interest now if it was previously not selected");}
	
		else if (initialRedColor==251)
			{GenericFunctions.takeScreenshot(getDriver());
			ExtentReportManager.getTest().log(Status.INFO, Constants.INTERESTS_BABYCARE+" interest already selected");
			ExtentReportManager.getTest().log(Status.INFO, "Trying to unselect "+Constants.INTERESTS_BABYCARE+" interest  now if it was previously selected");}
		// Tap on Interest
		addMoreInterestPage.tapOnElementByText(Constants.INTERESTS_BABYCARE);
		// Tap on Save my interests button
		explorePage=addMoreInterestPage.tapOnSaveMyInterest();
		// Go to Update your interests section and click see all
		addMoreInterestPage=explorePage.goToAddMoreInterestPage();
		// Find red color value of interest
		int finalRedColor=GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABYCARE);
		System.out.println("Final red color value"+finalRedColor);
		logAssert.assertNotEquals(finalRedColor, initialRedColor,"Check if interest was updated successfully on Add More Interest Page");
		logAssert.assertAll();
	}
		
	//User selects two interest
	@Test(enabled=true,priority=3,testName="UpdateYourInterestPage-Verify user is able to select multiple intrests at a time",
			description="UpdateYourInterestPage-Verify multiple interest selection when user has already selected two interests ")
	public void verifyUpdateMultipleInterest_UpdateYourInterestPageTest() throws Exception
	{
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		Thread.sleep(3000);
		// Go to Update your interests section and click see all
		AddMoreInterestPage addMoreInterestPage=explorePage.goToAddMoreInterestPage();
		// Get inital red color value of interest
		int initialRedColorInterest1= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABYCARE);
		int initialRedColorInterest2= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT);
		
		System.out.println("Initial red color value for "+Constants.INTERESTS_BABYCARE+" : "+initialRedColorInterest1 );
		System.out.println("Initial red color value for "+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+" : "+initialRedColorInterest2);
		
		if (initialRedColorInterest1==238 )
			{GenericFunctions.takeScreenshot(getDriver());
			ExtentReportManager.getTest().log(Status.INFO, "Interest "+Constants.INTERESTS_BABYCARE+" interest not selected");}
		else if (initialRedColorInterest1==251  )
			{GenericFunctions.takeScreenshot(getDriver());
			ExtentReportManager.getTest().log(Status.INFO, "Interest "+Constants.INTERESTS_BABYCARE+" interest  already selected");
			ExtentReportManager.getTest().log(Status.INFO, "Unselecting Interest "+Constants.INTERESTS_BABYCARE+" interest as it was previously selected");
			addMoreInterestPage.tapOnElementByText(Constants.INTERESTS_BABYCARE);
			initialRedColorInterest1= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABYCARE);

			}
		if (initialRedColorInterest2==238 )
		{GenericFunctions.takeScreenshot(getDriver());
		ExtentReportManager.getTest().log(Status.INFO, "Interest "+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+"  not selected");}
	else if (initialRedColorInterest2==251  )
		{GenericFunctions.takeScreenshot(getDriver());
		ExtentReportManager.getTest().log(Status.INFO, "Interest "+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+"  already selected");
		ExtentReportManager.getTest().log(Status.INFO, "Unselecting Interest "+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+" interest as it was previously selected");
		// tap on interest
		addMoreInterestPage.tapOnElementByText(Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT);
		initialRedColorInterest2= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT);
		}
		ExtentReportManager.getTest().log(Status.INFO, "Selecting Interest "+Constants.INTERESTS_BABYCARE+" interest ");
		// tap on interest
		addMoreInterestPage.tapOnElementByText(Constants.INTERESTS_BABYCARE);
		ExtentReportManager.getTest().log(Status.INFO, "Selecting Interest "+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+" interest ");
		// tap on interest
		addMoreInterestPage.tapOnElementByText(Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT);
		// tap on save my interest button
		explorePage=addMoreInterestPage.tapOnSaveMyInterest();
		Thread.sleep(3000);
		// Go to Update your interests section and click see all
		addMoreInterestPage=explorePage.goToAddMoreInterestPage();
		// Get final red color value of interest
		int finalRedColorInterest1=GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABYCARE);
		int finalRedColorInterest2=GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT);
		System.out.println("Final red color value "+Constants.INTERESTS_BABYCARE+" :"+finalRedColorInterest1);
		System.out.println("Final red color value "+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+" :\""+finalRedColorInterest2);
		LogAssert logAssert=new LogAssert();
		logAssert.assertNotEquals(finalRedColorInterest1, initialRedColorInterest1,"Check if "+Constants.INTERESTS_BABYCARE+" is selected successfully on Add More Interest Page");
		logAssert.assertNotEquals(finalRedColorInterest2, initialRedColorInterest2,"Check if "+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+" is selected successfully on Add More Interest Page");
		logAssert.assertAll();
	}
		
}
