package com.pom.crimson.testcases.momentlog.medium;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddNewMomentsPage;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.EditMomentPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.FullImageViewPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.VaccineLogPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.response.Response;

/**
 * This test class contains test cases for Vaccine log page.<br>
 * Vaccine log page can be used view moments of Child profiles saved with
 * milestone <b>{@value com.pom.crimson.util.Constants#MILESTONE_VACCINEDAY}</b>
 * . <br>
 * User can navigate to this page by going to: <br>
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget
 * <br>
 * Click <b>{@value com.pom.crimson.util.Constants#VIEW_VACCINE_LOG_LINK}</b>
 * link <br>
 * <br>
 * <br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class VaccineLogPageTest extends BaseFixture {

	HomePage homePage;
	MomentsLogPage momentsLogPage;
	VaccineLogPage vaccineLogPage;
	AddNewMomentsPage addNewMomentsPage;
	EditMomentPage editMomentPage;
	String milestone = Constants.MILESTONE_VACCINEDAY;
	LoginPage loginPage;
	FullImageViewPage fullImageViewPage;
	GroupAdviceAPI groupAdviceAPI;
	GenericFunctionsAPI genericFunctionsAPI;
	String childPreferredName;
	LogAssert logAssert;

	@BeforeMethod()
	public void beforeLocalMethod() throws IOException {
//		seedImages();
		genericFunctionsAPI = new GenericFunctionsAPI();
		logAssert = new LogAssert();
		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}
		getDriver().launchApp();
		loginPage = new LoginPage(getDriver(), getPlatformName());

		try {
			loginPage.loginAllPlatforms();
		} catch (Exception e) {
			e.printStackTrace();
		}

		homePage = new HomePage(getDriver(), getPlatformName());
		groupAdviceAPI = new GroupAdviceAPI();
	}

	@Test(enabled = true, priority = 1, testName = "Delete all moments-for data clean up", description = "Delete all moments-for data clean up")
	public void deleteAllData_VaccineLogTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}

	@Test(enabled = true, priority = 2, testName = "VaccineLogPage-Verify moment with image,milestone,Note displayed in vaccine log", description = "VaccineLogPage-Verify vaccine card displayed when users enters following data:\n"
			+ "Image+Milestone( Vaccine day)+Note")
	public void verifyMomentCreationWithImageNoteMilestone_VaccineLogPageTest() throws Exception {

		// LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String imageUrl = "";
		String note = "";
		String milestoneId = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();

		if (noOfMomentsInitial == 0) {
			// create First Moment for child 
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestone(1, testNote, milestone,
					childPreferredName);
		} else {
			// Create moment for child
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestone(1, testNote, milestone,
					childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend ");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If note of Moment,note is displayed on Moments Log Page");
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(testNote),
				"Check If note of Moment,note is displayed on Vaccine Log page");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "VaccineLogPage-Verify moment with Height+Milestone( Vaccine day) displayed in vaccine log", description = "VaccineLogPage-Verify moment with Height+Milestone( Vaccine day) displayed in vaccine log")
	public void verifyMomentCreationWithMilestoneHeight_VaccineLogPageTest() throws Exception {
		// LogAssert logAssert = new LogAssert();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyHeight = "";
		String milestoneId = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithMilestoneHeight(milestone, height, childPreferredName);

		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithMilestoneHeight(milestone, height, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		// actual,expected
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If Vaccine Day Milestone as saved in Moment,Vaccine Day Milestone to be  displayed on Moments Log Page");
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height to be  displayed on Vaccine Log page");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "VaccineLogPage-Verify moment with Weight+Milestone( Vaccine day) displayed in vaccine log", description = "VaccineLogPage-Verify moment with Weight+Milestone( Vaccine day) displayed in vaccine log")
	public void verifyMomentCreationWithMilestoneWeight_VaccineLogPageTest() throws Exception {
		// LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String milestoneId = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithMilestoneWeight(milestone, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithMilestoneWeight(milestone, weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	        // Retrieving milestoneID,babyWeight saved in backend for last moment saved
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight to be  displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If Vaccine Day Milestone as saved in Moment,Vaccine Day Milestone to be  displayed on Moments Log Page");
		//Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight to be  displayed on Vaccine Log page");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "VaccineLogPage-Verify that Grandparent profile is not present on Vaccine Log Page", description = "VaccineLogPage-Verify that Grandparent profile is not present on Vaccine Log Page")
	public void verifyGrandParentProfileNotPresent_VaccineLogPageTest() throws Exception {
		// LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String milestoneId = "";

		// Check for Grand Parent profiles
		int profiles = genericFunctionsAPI.getNoOfGrandparentProfiles();
		if (profiles == 0) {
			// Create Grand Parent profile
			genericFunctionsAPI.createGrandparentProfile();
		}
		// Getting preferred name of Grand parent
		String preferredNameGrandParent = genericFunctionsAPI.getFirstGrandParentPreferredName();

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithMilestoneWeight(milestone, weight, childPreferredName);
		}
		// Go to Vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();

		logAssert.assertFalse(vaccineLogPage.isElementDisplayed(preferredNameGrandParent),
				"Check If " + preferredNameGrandParent + " Grandparent name is not displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "VaccineLogPage-Verify that parent profile is not present on Vaccine Log Page", description = "VaccineLogPage-Verify that parent profile is not present on Vaccine Log Page")
	public void verifyParentProfileNotPresent_VaccineLogPageTest() throws Exception {
		// LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String milestoneId = "";

		// Check for Parent profiles
		int profiles = genericFunctionsAPI.getNoOfParentProfiles();
		if (profiles == 0) {
		// Create Parent profile
			genericFunctionsAPI.createParentProfile();
		}
		//get preferred name of parent
		String preferredNameParent = genericFunctionsAPI.getFirstParentPreferredName();

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithMilestoneWeight(milestone, weight, childPreferredName);
		}
		// Go to Vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();

		logAssert.assertFalse(vaccineLogPage.isElementDisplayed(preferredNameParent),
				"Check If " + preferredNameParent + " Parent name is not displayed");

		logAssert.assertAll();
	}

}
