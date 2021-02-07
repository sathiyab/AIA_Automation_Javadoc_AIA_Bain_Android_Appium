package com.pom.crimson.testcases.explore.medium;

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
* This test class contains test cases for Daily tips to be displayed on Home Page.<br>
* Daily tips are currently displayed for Parent to be and New parent archetypes only.<br>
* For Parent to be : It displays as per due date. <br>
* For New parents : It displays as per Child's DOB. <br> 
* @author Jaspreet Kaur Chagger
*/
public class HomePageCardTest extends BaseFixture {

	HomePage homePage;
	GenericFunctionsAPI genericFunctionsAPI;
	String childPreferredName;
	LoginPage loginPage;
	ExplorePage explorePage;

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

	@Test(enabled = true, priority = 1, testName = "HomePage-Verify that daily tip is visible to New Parent", description = "HomePage-Verify that daily tip is visible to New Parent as per baby's age in days")
	public void verifyDailyTipNewParents_HomePageCardTest() throws Exception {
		String dailyTip="";
		String archetypeName_dailyTip;
		String day="";
		LogAssert logAssert = new LogAssert();

		// Get First Child preferred name
		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}

		// Update DOB of First Child as 30 days
		int ageInDays = 30;
		String DOB = GenericFunctions.generatePastDate(ageInDays, 0, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Change archetype of user to New parents
		String archetype = Constants.ARCHETYPE_NEW_PARENTS;
		String archID = genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		String archeTypeUserID = genericFunctionsAPI.getArchetypeIdOfProfile();

		if (archID.equals(null) || archeTypeUserID.equals(null)) {
			throw new SkipException(
					"Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}

		if (!(archeTypeUserID.equals(archID))) {
			boolean archetypeChanged = genericFunctionsAPI.changeArcheTypeOfUser(archID);
			if (archetypeChanged == false) {
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		// Get Daily Tip from Backend
		Response dailyTipRes = genericFunctionsAPI.getDailyTip();
		String json="";
		if (dailyTipRes.statusCode() == 200) {
			json = dailyTipRes.getBody().asString();
		} 
		try {
		archetypeName_dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.archetype.name").toString();
		day = com.jayway.jsonpath.JsonPath.parse(json).read("$.day").toString();
		dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.body").toString();
		} catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO,"Stack trace: "+e.getMessage());
			ExtentReportManager.getTest().log(Status.INFO, "Received Null response in Daiy tip from Backend");
	
		}

		// Reopen app for data refresh
		getDriver().launchApp();
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

	
		// Check if Daily tip displayed on Home page
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.DAILY_DEVELOPMENT_INSIGHTS),
				"Check if " + Constants.DAILY_DEVELOPMENT_INSIGHTS + " section is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.WHAT_TO_EXPECT),
				"Check if " + Constants.WHAT_TO_EXPECT + " Card is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(dailyTip),
				"Check if daily tip text:" + dailyTip + " is displayed");
		logAssert.assertEquals(Integer.toString(ageInDays), day,
				"Check if daily tip is displayed as per baby's age: " + ageInDays + "days");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 2, testName = "HomePage-Verify that daily tip is visible to Parent to be", description = "HomePage-Verify that daily tip is visible to Parent to be as per due date ,Day tagged in content = 280 - (Due Date – Today)")
	public void verifyDailyTipParentToBe_HomePageCardTest() throws Exception {

		String dailyTip="";
		String archetypeName_dailyTip;
		String day="";
		LogAssert logAssert = new LogAssert();

		// Update Due date of Primary Profile in Next 60 days
		int dueDateInDays = 60;
		String dueDateInDaysSt = GenericFunctions.generateFutureDate(dueDateInDays, 0, 0) + "T00:00:00Z";
		int dailyTipDays = 280 - dueDateInDays;
		genericFunctionsAPI.updateDueDate(dueDateInDaysSt);

		// Change archetype of user to parents to be
		String archetype = Constants.ARCHETYPE_PARENT_TO_BE;
		String archID = genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		String archeTypeUserID = genericFunctionsAPI.getArchetypeIdOfProfile();

		if (archID.equals(null) || archeTypeUserID.equals(null)) {
			throw new SkipException(
					"Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}

		if (!(archeTypeUserID.equals(archID))) {
			boolean archetypeChanged = genericFunctionsAPI.changeArcheTypeOfUser(archID);
			if (archetypeChanged == false) {
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		// Get Daily Tip from Backend
		Response dailyTipRes = genericFunctionsAPI.getDailyTip();
		String json="";
		if (dailyTipRes.statusCode() == 200) {
			json = dailyTipRes.getBody().asString();
		} 
	
		try {
		archetypeName_dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.archetype.name").toString();
		day = com.jayway.jsonpath.JsonPath.parse(json).read("$.day").toString();
		dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.body").toString();

		System.out.println("archetypeName_dailyTip: " + archetypeName_dailyTip);
		System.out.println("day: " + day);
		System.out.println("dailyTip: " + dailyTip);
		} catch(Exception e)
		{
			e.printStackTrace();
			ExtentReportManager.getTest().log(Status.INFO, "Received Null response in Daiy tip from Backend");

		}

		// Reopen app for data refresh
		getDriver().launchApp();
		
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		// Check if Daily tip displayed on Home page
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.DAILY_DEVELOPMENT_INSIGHTS),
				"Check if " + Constants.DAILY_DEVELOPMENT_INSIGHTS + " section is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.WHAT_TO_EXPECT),
				"Check if " + Constants.WHAT_TO_EXPECT + " Card is displayed");
		logAssert.assertTrue(homePage.isElementDisplayed(dailyTip),
				"Check if daily tip text:" + dailyTip + " is displayed");
		logAssert.assertEquals(Integer.toString(dailyTipDays), day,
				"Check if daily tip is displayed as per Due date : " + dailyTipDays + "days");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, 
			testName = "HomePage-Verify that daily tip is not visible to Planning a baby archetype", 
			description = "HomePage-Verify that daily tip is not visible to Planning a baby archetype")
	public void verifyDailyTipPlanningBaby_HomePageCardTest() throws Exception {
		String dailyTip="";
		String day;
		String archetypeName_dailyTip;
		LogAssert logAssert = new LogAssert();

		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}

		// Update DOB of First Child as 30 days
		int ageInDays = 30;
		String DOB = GenericFunctions.generatePastDate(ageInDays, 0, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Change archetype of user to New parents
		String archetype = Constants.ARCHETYPE_PLANNING_FOR_A_BABY;
		String archID = genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		String archeTypeUserID = genericFunctionsAPI.getArchetypeIdOfProfile();

		if (archID.equals(null) || archeTypeUserID.equals(null)) {
			throw new SkipException(
					"Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}

		if (!(archeTypeUserID.equals(archID))) {
			boolean archetypeChanged = genericFunctionsAPI.changeArcheTypeOfUser(archID);
			if (archetypeChanged == false) {
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		// Get Daily Tip from Backend
		Response dailyTipRes = genericFunctionsAPI.getDailyTip();
		String json="";
		if (dailyTipRes.statusCode() == 200) {
			json = dailyTipRes.getBody().asString();
		} 
		
		System.out.println("-------------------------------------");
		System.out.println("Json: "+json);
		System.out.println("--------------------------------------");
		try
		{
		archetypeName_dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.archetype.name").toString();
		day = com.jayway.jsonpath.JsonPath.parse(json).read("$.day").toString();
		dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.body").toString();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			ExtentReportManager.getTest().log(Status.INFO, "Received Null response in Daiy tip from Backend");

		}

		// Reopen app for data refresh
		getDriver().launchApp();
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		
		// Check if Daily tip displayed on Home page
		logAssert.assertFalse(homePage.isElementDisplayed(Constants.DAILY_DEVELOPMENT_INSIGHTS),
				"Check if " + Constants.DAILY_DEVELOPMENT_INSIGHTS + " section is not displayed for archetype: "+Constants.ARCHETYPE_PLANNING_FOR_A_BABY);
		logAssert.assertFalse(homePage.isElementDisplayed(Constants.WHAT_TO_EXPECT),
				"Check if " + Constants.WHAT_TO_EXPECT + " Card is not displayed for Archetype "+Constants.ARCHETYPE_PLANNING_FOR_A_BABY);
		logAssert.assertFalse(homePage.isElementDisplayed(dailyTip),
				"Check if daily tip text:" + dailyTip + " is not displayed for Archetype "+Constants.ARCHETYPE_PLANNING_FOR_A_BABY);
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "HomePage-Verify that daily tip is not visible to Experienced parents", 
			description = "HomePage-Verify that daily tip is not visible to Experienced parents")
	public void verifyDailyTipExperiencedParents_HomePageCardTest() throws Exception {
		String dailyTip="";
		String day="";
		String archetypeName_dailyTip;
		LogAssert logAssert = new LogAssert();

		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}

		// Update DOB of First Child as 30 days
		int ageInDays = 30;
		String DOB = GenericFunctions.generatePastDate(ageInDays, 0, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Change archetype of user to New parents
		String archetype = Constants.ARCHETYPE_EXPERIENCED_PARENTS;
		String archID = genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		String archeTypeUserID = genericFunctionsAPI.getArchetypeIdOfProfile();

		if (archID.equals(null) || archeTypeUserID.equals(null)) {
			throw new SkipException(
					"Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}

		if (!(archeTypeUserID.equals(archID))) {
			boolean archetypeChanged = genericFunctionsAPI.changeArcheTypeOfUser(archID);
			if (archetypeChanged == false) {
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		// Get Daily Tip from Backend
		Response dailyTipRes = genericFunctionsAPI.getDailyTip();
		String json="";
		if (dailyTipRes.statusCode() == 200) {
			json = dailyTipRes.getBody().asString();
		}
		try {
		archetypeName_dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.archetype.name").toString();
		day = com.jayway.jsonpath.JsonPath.parse(json).read("$.day").toString();
		dailyTip = com.jayway.jsonpath.JsonPath.parse(json).read("$.body").toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExtentReportManager.getTest().log(Status.INFO, "Received Null response in Daiy tip from Backend");

		}

		// Reopen app for data refresh
		getDriver().launchApp();
		
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		// Check if Daily tip displayed on Home page
		logAssert.assertFalse(homePage.isElementDisplayed(Constants.DAILY_DEVELOPMENT_INSIGHTS),
				"Check if " + Constants.DAILY_DEVELOPMENT_INSIGHTS + " section is not displayed for archetype: "+Constants.ARCHETYPE_EXPERIENCED_PARENTS);
		logAssert.assertFalse(homePage.isElementDisplayed(Constants.WHAT_TO_EXPECT),
				"Check if " + Constants.WHAT_TO_EXPECT + " Card is not displayed for Archetype "+Constants.ARCHETYPE_EXPERIENCED_PARENTS);
		logAssert.assertFalse(homePage.isElementDisplayed(dailyTip),
				"Check if daily tip text:" + dailyTip + " is not displayed for Archetype "+Constants.ARCHETYPE_EXPERIENCED_PARENTS);
		logAssert.assertAll();
	}
	
	

}
