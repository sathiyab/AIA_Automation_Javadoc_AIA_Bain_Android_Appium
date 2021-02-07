package com.pom.crimson.testcases.explore.smoke;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.ContentAPI;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddMoreInterestPage;
import com.pom.crimson.pages.AddNewMomentsPage;
import com.pom.crimson.pages.ContentPage;
import com.pom.crimson.pages.BasedOnYourGroupsPage;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;
import io.restassured.response.Response;

/**
* This test class contains test cases for Child age card displayed on Home Page.<br>
* 
* @author Jaspreet Kaur Chagger
*/
public class HomePageCardTest extends BaseFixture {

	HomePage homePage;
	GenericFunctionsAPI genericFunctionsAPI;
	String childPreferredName;
	LoginPage loginPage;
	ExplorePage explorePage;
	AddNewMomentsPage addNewMomentsPage;

	@BeforeMethod()
	public void beforeLocalMethod() {
		genericFunctionsAPI = new GenericFunctionsAPI();
		loginPage = new LoginPage(getDriver(), getPlatformName());

		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		homePage = new HomePage(getDriver(), getPlatformName());


	}
	
	@Test(enabled = true, priority = 1, 
			testName = "HomePage-Verify child age card displayed on Home page when child's age  less than 30 days", description = "HomePage-Verify child age card displayed on Home page for child of age less than 30 days")
	public void verifyChildAgeCardLessThan30days_HomePageCardTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Get First Child preferred name from backend
		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}

		// Update DOB of First Child as 20 days
		int ageInDays = 20;
		String DOB = GenericFunctions.generatePastDate(ageInDays, 0, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
	
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// Check if Age card displayed on Home page
		logAssert.assertTrue(homePage.isElementDisplayed(childPreferredName + " is"),
				"Check if " + childPreferredName + " is section is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(ageInDays + " days old today!"),
				"Check if baby age: " + ageInDays +" days"+ " is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.ADD_TO_MOMENTS),
				"Check if " + Constants.ADD_TO_MOMENTS + " link is displayed");
		
		addNewMomentsPage=homePage.tapAddToMoments();
		
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_TITLE),
				"Check if " + Constants.ADDNEWMOMENT_TITLE + " link is displayed on New Moment Page");
		
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(childPreferredName),
				"Check if " + childPreferredName + " tab is displayed on New Moment Page");
		logAssert.assertAll();
	}
	
	
	@Test(enabled = true, priority = 2, testName = "HomePage-Verify child age card displayed on Home page when child's age  more than 30 days", description = "HomePage-Verify child age card displayed on Home page for child of age more than 30 days")
	public void verifyChildAgeCardMoreThan30days_HomePageCardTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Get First Child preferred name from backend
		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}

		// Update DOB of First Child as 30 days
		int ageInDays = 20;
		int ageInMonths = 2;

		String DOB = GenericFunctions.generatePastDate(ageInDays, ageInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
	
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// Check if Age card displayed on Home page
		logAssert.assertTrue(homePage.isElementDisplayed(childPreferredName + " is"),
				"Check if " + childPreferredName + " is section is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(ageInMonths+" months, "+ageInDays + " days old today!"),
				"Check if baby age: " +ageInMonths+" months"+ ageInDays + " days"+" is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.ADD_TO_MOMENTS),
				"Check if " + Constants.ADD_TO_MOMENTS + " link is displayed");
		
		addNewMomentsPage=homePage.tapAddToMoments();
		
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_TITLE),
				"Check if " + Constants.ADDNEWMOMENT_TITLE + " link is displayed on New Moment Page");
		
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(childPreferredName),
				"Check if " + childPreferredName + " tab is displayed on New Moment Page");
		logAssert.assertAll();
	}
	

	
	@Test(enabled = true, priority = 3, testName = "HomePage-Verify child age card is not displayed on Home page when child's age  is 366 days", description = "HomePage-Verify child age card displayed on Home page for child of age More than 30 days but less than year")
	public void verifyChildAgeCardMoreThan366days_HomePageCardTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Get First Child preferred name from backend
		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}

		// Update DOB of First Child as 366 days
		int ageInDays = 366;
		String DOB = GenericFunctions.generatePastDate(ageInDays, 0, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
	
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// Check if Age card is not displayed on Home page
		logAssert.assertFalse(homePage.isElementDisplayed(childPreferredName + " is"),
				"Check if " + childPreferredName + " is section is not displayed");
		//logAssert.assertFalse(homePage.isElementDisplayed(Constants.ADD_TO_MOMENTS),
			//	"Check if " + Constants.ADD_TO_MOMENTS + " link is not displayed");
		
		logAssert.assertAll();
	}

	
	
}
