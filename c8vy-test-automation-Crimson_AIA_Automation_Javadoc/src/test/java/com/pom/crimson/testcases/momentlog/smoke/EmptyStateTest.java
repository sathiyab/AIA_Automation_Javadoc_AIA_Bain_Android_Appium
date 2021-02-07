package com.pom.crimson.testcases.momentlog.smoke;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddNewMomentsPage;
import com.pom.crimson.pages.GrowthLogPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.VaccineLogPage;
import com.pom.crimson.util.Constants;

import io.restassured.response.Response;

/**
 * This test class contains test cases for Empty state of Moments log, Vaccine
 * log, Growth log
 * 
 * @author Jaspreet Kaur Chagger
 */
public class EmptyStateTest extends BaseFixture {

	HomePage homePage;
	MomentsLogPage momentsLogPage;
	AddNewMomentsPage addNewMomentsPage;
	VaccineLogPage vaccineLogPage;
	GrowthLogPage growthLogPage;
	LoginPage loginPage;
	GenericFunctionsAPI genericFunctionsAPI;

	@BeforeMethod()
	public void beforeMethodLocal() throws Exception {
		loginPage = new LoginPage(getDriver(), getPlatformName());

		try {
			loginPage.loginAllPlatforms();
		} catch (Exception e) {
			e.printStackTrace();
		}

		homePage = new HomePage(getDriver(), getPlatformName());
		genericFunctionsAPI = new GenericFunctionsAPI();
	}

	@Test(enabled = true, priority = 1, testName = "EmptyStateMomentsLog-Verify that empty state of Moments log page", description = "Check Add your first moment button is displayed,View growth log and View vaccine log links should not be displayed")
	public void verifyEmptyStateMomentsLogPage_EmptyStateTest() throws Exception {
		// GenericFunctionsAPI genericFunctionsAPI=new GenericFunctionsAPI();

		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		if (noOfMomentsInitial > 0) {
			// If no. of moments greater than 0, delete all moments
			genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
		}
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();

		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT),
				"Check if " + Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT + "button is displayed");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE + " button is displayed");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.VIEW_GROWTH_LOG_LINK),
				"Check if " + Constants.VIEW_GROWTH_LOG_LINK + " link is displayed" + Constants.VIEW_GROWTH_LOG_LINK
						+ " should not be displayed");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.VIEW_VACCINE_LOG_LINK),
				"Check if " + Constants.VIEW_VACCINE_LOG_LINK + " link is displayed" + Constants.VIEW_VACCINE_LOG_LINK
						+ " should not be displayed");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 2, testName = "EmptyStateMomentsLog-Verify Add your first moment button ", description = "Verify on clicking Add your first moment button ,user lands on New Moment page")
	public void verifyAddYourFirstMomentButton_EmptyStateTest() throws Exception {
		// GenericFunctionsAPI genericFunctionsAPI=new GenericFunctionsAPI();

		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		if (noOfMomentsInitial > 0) {
			// if no. of moments greater than 0, delete all moments
			genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
		}
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();

		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT),
				"Check if " + Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT + " button is displayed");
		addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_TITLE),
				"Check if " + "Add your first moment Page is displayed as by verifying if "
						+ Constants.ADDNEWMOMENT_TITLE + " title is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "EmptyStateVaccineLog-Verify empty state of Vaccine log page", description = "Verify empty state of Vaccine log page and check if"
			+ Constants.EMPTYSTATE_VACCINE_LOG_MESSAGE + " message is displayed")
	public void verifyEmptyStateVaccineLogPage_EmptyStateTest() throws Exception {

		// Create two Child profiles before running this test case

		// GenericFunctionsAPI genericFunctionsAPI=new GenericFunctionsAPI();
		String childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		String note = GenericFunctions.generateRandomAlphanumericString();

		if (childPreferredName.equals("")) {
			// Create first child profile if no preferred name is returned
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();

			// Restart app for data refresh
			getDriver().launchApp();

			// login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		LogAssert logAssert = new LogAssert();

		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		if (noOfMomentsInitial > 0) {
			// if no. of moments greater than 0, delete all moments
			genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
		}
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		// Tap Add your first moment button
		addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		// Create moment with note
		momentsLogPage = addNewMomentsPage.createMomentWithNote(note, Constants.ADDNEWMOMENT_MYSELF_TAB);
		// Go to Vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		logAssert.assertTrue(vaccineLogPage.verifyEmptyStateMessage(),
				"Check for Empty State Vaccine log description:  " + Constants.EMPTYSTATE_VACCINE_LOG_MESSAGE);
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "EmptyStateGrowthLog-Verify empty state of Growth log page", description = "Verify empty state of Grwoth log page and check if"
			+ Constants.EMPTYSTATE_GROWTH_LOG_MESSAGE + " message is displayed")
	public void verifyEmptyStateGrowthLogPage_EmptyStateTest() throws Exception {
		// GenericFunctionsAPI genericFunctionsAPI=new GenericFunctionsAPI();
		// Get First Child Preferred name
		String childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		System.out.println("Child preferred name is: " + childPreferredName);
		String note = GenericFunctions.generateRandomAlphanumericString();
		System.out.println("This was executed--------------------------------------------------------");

		if (childPreferredName.equals("")) {
			// Create first child profile if first child preferred name is blank
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
			// Restart app for data refresh
			getDriver().launchApp();
			//login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		LogAssert logAssert = new LogAssert();
		
		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		if (noOfMomentsInitial > 0) {
			//If no. of moments greater than 0, delete all moments
			genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
		}
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		// tap Add your first moment button
		addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		// Create a moment for Myself with Note
		momentsLogPage = addNewMomentsPage.createMomentWithNote(note, Constants.ADDNEWMOMENT_MYSELF_TAB);
		// Go to Growth log page 
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		logAssert.assertTrue(growthLogPage.verifyEmptyStateMessage(),
				"Check for Empty State Growth log description:  " + Constants.EMPTYSTATE_GROWTH_LOG_MESSAGE);
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "EmptyStateVaccineLog-Verify Child profile Toggle is present on Vaccine Log empty state", description = "Verify Child profile Toggle is present on  Vaccine Log empty state")
	public void verifyChildProfileToggleVaccineLog_EmptyStateTest() throws Exception {
		// GenericFunctionsAPI genericFunctionsAPI=new GenericFunctionsAPI();
		// Getting Preferred names of all children
		List<String> allChildPreferedName = genericFunctionsAPI.getAllChildPreferredName();
		int size = allChildPreferedName.size();
		String note = GenericFunctions.generateRandomAlphanumericString();
		LogAssert logAssert = new LogAssert();

		if (size == 0) {
			// Create First and Second Child Profile
			genericFunctionsAPI.createFirstChildProfile();
			genericFunctionsAPI.createSecondChildProfile();
			allChildPreferedName = genericFunctionsAPI.getAllChildPreferredName();
			// Restart app for data refresh
			getDriver().launchApp();
			//login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (size == 1) {
			// Create second child profile
			genericFunctionsAPI.createSecondChildProfile();
			allChildPreferedName = genericFunctionsAPI.getAllChildPreferredName();
			// Restart app for data refresh
			getDriver().launchApp();
			//login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		String firstChildPreferredName = allChildPreferedName.get(0);// fetching first child preferred name
		String secondChildPreferredName = allChildPreferedName.get(1);// fetching second child preferred name

		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		if (noOfMomentsInitial > 0) {
			// If no. of moments greater than zero,delete all moments
			genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
		}
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		// Tap Add your first moment button
		addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		// Create moment for myself with Note
		momentsLogPage = addNewMomentsPage.createMomentWithNote(note, Constants.ADDNEWMOMENT_MYSELF_TAB);
		// Go to Vaccine log
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();

		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(firstChildPreferredName),
				"Check if preferred name:" + firstChildPreferredName + " of First Child Profile is displayed");
		logAssert.assertTrue(vaccineLogPage.verifyEmptyStateMessage(),
				"Check for Empty State Vaccine log description:  " + Constants.EMPTYSTATE_VACCINE_LOG_MESSAGE);
		vaccineLogPage.tapOnElementByText(secondChildPreferredName);
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(secondChildPreferredName),
				"Check if preferred name:" + secondChildPreferredName + " of Second Child Profile is displayed");
		logAssert.assertTrue(vaccineLogPage.verifyEmptyStateMessage(),
				"Check for Empty State Vaccine log description:  " + Constants.EMPTYSTATE_VACCINE_LOG_MESSAGE);
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "EmptyStateGrowthLogVerify Child profile Toggle is present on Growth Log empty state", description = "Verify Child profile Toggle is present on Growth Log empty state")
	public void verifyChildProfileToggleGrowthLog_EmptyStateTest() throws Exception {
		// GenericFunctionsAPI genericFunctionsAPI=new GenericFunctionsAPI();
		// Fetching all  preferred name of all children
		List<String> allChildPreferedName = genericFunctionsAPI.getAllChildPreferredName();
		int size = allChildPreferedName.size();
		String note = GenericFunctions.generateRandomAlphanumericString();
		LogAssert logAssert = new LogAssert();

		if (size == 0) {
			// Create First and Second Child Profile
			genericFunctionsAPI.createFirstChildProfile();
			genericFunctionsAPI.createSecondChildProfile();
			allChildPreferedName = genericFunctionsAPI.getAllChildPreferredName();
			// Restart app for data refresh
			getDriver().launchApp();
			
			//login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (size == 1) {
			// Create second child profile
			genericFunctionsAPI.createSecondChildProfile();
			allChildPreferedName = genericFunctionsAPI.getAllChildPreferredName();
			// Restart app for data refresh
			getDriver().launchApp();
			//login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		String firstChildPreferredName = allChildPreferedName.get(0);// fetching first child preferred name
		String secondChildPreferredName = allChildPreferedName.get(1);// fetching second child preferred name
		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		if (noOfMomentsInitial > 0) {
			// If no. of moments greater than 0, delete all moments
			genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
		}
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		// Tap Add your first moment button
		addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		// Create moment for myself with note 
		momentsLogPage = addNewMomentsPage.createMomentWithNote(note, Constants.ADDNEWMOMENT_MYSELF_TAB);
		// Go to Growth Log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		logAssert.assertTrue(growthLogPage.isElementDisplayed(firstChildPreferredName),
				"Check if preferred name:" + firstChildPreferredName + " of First Child Profile is displayed");
		logAssert.assertTrue(growthLogPage.verifyEmptyStateMessage(),
				"Check for Empty State Growth log description:  " + Constants.EMPTYSTATE_GROWTH_LOG_MESSAGE);
		growthLogPage.tapOnElementByText(secondChildPreferredName);
		logAssert.assertTrue(growthLogPage.isElementDisplayed(secondChildPreferredName),
				"Check if preferred name:" + secondChildPreferredName + " of Second Child Profile is displayed");
		logAssert.assertTrue(growthLogPage.verifyEmptyStateMessage(),
				"Check for Empty State Growth log description:  " + Constants.EMPTYSTATE_GROWTH_LOG_MESSAGE);
		logAssert.assertAll();

	}

}
