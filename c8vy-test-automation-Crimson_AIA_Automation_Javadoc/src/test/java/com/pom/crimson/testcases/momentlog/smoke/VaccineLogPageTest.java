package com.pom.crimson.testcases.momentlog.smoke;

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
		// seedImages();
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

	@Test(enabled = true, priority = 2, testName = "VaccineLogPage-Verify that View vaccine log link is displayed on Moments Log page", description = "VaccineLogPage-Verify that View vaccine log link is displayed on Moments Log page ")
	public void verifyVaccineLogLinkIsDisplayed_VaccineLogPageTest() throws Exception {

		// LogAssert logAssert = new LogAssert();

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String note = "";
		String milestoneId = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();

		if (noOfMomentsInitial == 0) {
			// Create First moment for First Child
			ExtentReportManager.getTest().log(Status.INFO,
					"Creating a moment for " + childPreferredName + " as noOfMomentsInitial=0");
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);
			Thread.sleep(2000);

			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
			// Getting moment id of last saved moment from backend
			String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
					ids_moments_afterMomentcreation);

			try {
				Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
				// Retrieving note,milestoneID saved in backend for last moment saved

				note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
				milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			} catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO,
						"Exception while fetching moments response from backend" + e.getMessage());

			}

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
			// logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
			// logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		}

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTSLOG_VACCINE_LOG_LINK_DESCRIPTION),
				"Check if " + Constants.MOMENTSLOG_VACCINE_LOG_LINK_DESCRIPTION + " is displayed");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.VIEW_VACCINE_LOG_LINK),
				"Check if " + Constants.VIEW_VACCINE_LOG_LINK + " link title is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "VaccineLogPage-Verify that user can click on View vaccine log link", description = "VaccineLogPage-Verify that user can click on View vaccine log link ")
	public void verifyNavigationToVaccineLogPage_VaccineLogPageTest() throws Exception {
		// LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String note = "";
		String milestoneId = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);

			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
			// Getting moment id of last saved moment from backend
			String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
					ids_moments_afterMomentcreation);

			try {
				Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
				// Retrieving note,milestoneID saved in backend for last moment saved

				note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
				milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			} catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO,
						"Exception while fetching moments response from backend" + e.getMessage());

			}

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
			// logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
			// logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		}
		// Go to Vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(Constants.VACCINE_LOG_TITLE),
				"Check if " + Constants.VACCINE_LOG_TITLE + " title is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "VaccineLogPage-Verify that if user is able to view Add a moment section in vaccine log", description = "VaccineLogPage-Verify that if user is able to view Add a moment section in vaccine log")
	public void verifyAddMomentSectionIsDisplayed_VaccineLogPageTest() throws Exception {

		// LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String note = "";
		String milestoneId = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();

		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);

			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
			// Getting moment id of last saved moment from backend
			String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
					ids_moments_afterMomentcreation);

			try {
				Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
				// Retrieving note,milestoneID saved in backend for last moment saved

				note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
				milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			} catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO,
						"Exception while fetching moments response from backend" + e.getMessage());

			}

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
			// logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
			// logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		}
		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		logAssert.assertTrue(vaccineLogPage.verifyAddNewMomentDescription(),
				" Check if " + Constants.VACCINE_LOG_ADDNEWMOMENT_DESCRIPTION + " is displayed");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(Constants.VACCINE_LOG_ADDNEWMOMENT_LINK),
				"Check if " + Constants.VACCINE_LOG_ADDNEWMOMENT_LINK + " link is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "VaccineLogPage-Verify that if user is able to navigate to  New moment screen from vaccine log", description = "VaccineLogPage-Verify that if user is able to navigate to  New moment screen from vaccine log")
	public void verifyNavgationToAddNewMomentScreenFromVaccineLog_VaccineLogPageTest() throws Exception {
		// LogAssert logAssert = new LogAssert();

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String note = "";
		String milestoneId = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile with Note and Vaccine day milestone
			// from Add First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);

			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
			// Getting moment id of last saved moment from backend
			String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
					ids_moments_afterMomentcreation);

			try {
				Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
				// Retrieving note,milestoneID saved in backend for last moment saved

				note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
				milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			} catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO,
						"Exception while fetching moments response from backend" + e.getMessage());

			}

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
			// logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
			// logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		}
		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// Tap on Add new moment button
		addNewMomentsPage = vaccineLogPage.tapAddNewMomentBtn();

		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(Constants.ADDNEWMOMENT_TITLE),
				"Check if Add new moment page is displayed , Check if " + Constants.ADDNEWMOMENT_TITLE
						+ " Title is visible");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 9, testName = "VaccineLogPage-Verify moment with Image+Heigh+Weight+Milestone( Vaccine day)+Note displayed in vaccine log", description = "VaccineLogPage-Verify moment with Image+Heigh+Weight+Milestone( Vaccine day)+Note displayed in vaccine log")
	public void verifyMomentCreationWithImageMilestoneNoteHeightWeight_VaccineLogPageTest() throws Exception {

		// LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String imageUrl = "";
		String note = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend
			// for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}
		System.out.println("---------------------------------");

		System.out.println("babyweiegt is: " + babyWeight);
		System.out.println("---------------------------------");

		System.out.println("weiegt is: " + weight);
		System.out.println("---------------------------------");

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height to be  displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight to be  displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If Note as saved in Moment,Note to be  displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If Vaccine Day Milestone as saved in Moment,Vaccine Day Milestone to be  displayed on Moments Log Page");

		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// Tap on Child thumbnail image
		vaccineLogPage.tapChildBtn(childPreferredName);
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height to be  displayed on Vaccine Log Page");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight to be  displayed on Vaccine Log Page");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(testNote),
				"Check If Note as saved in Moment,Note to be  displayed on Vaccine Log Page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 10, testName = "VaccineLogPage-Verify if user can tap on back button on vaccine log page", description = "VaccineLogPage-Verify if user can tap on back button on vaccine log page")
	public void verifyBackButton_VaccineLogPageTest() throws Exception {
		// LogAssert logAssert = new LogAssert();

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String note = "";
		String milestoneId = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile with Note and Vaccine day milestone
			// from Add First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);

			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
			// Getting moment id of last saved moment from backend
			String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
					ids_moments_afterMomentcreation);

			try {
				Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
				// Retrieving note,milestoneID saved in backend for last moment saved

				note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
				milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			} catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO,
						"Exception while fetching moments response from backend" + e.getMessage());

			}

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
			logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
			logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		}
		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(Constants.VACCINE_LOG_TITLE),
				"Check if Vaccine log Page displayed,Check " + Constants.VACCINE_LOG_TITLE + " Title is visible");
		// Tap back button
		momentsLogPage = vaccineLogPage.tapBackBtn();
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if Moments log Page  displayed,Check " + Constants.MOMENTS_LOG_TITLE + " Title is visible");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 11, testName = "VaccineLogPage-Verify user can edit moment without image in vaccine log", description = "VaccineLogPage-Verify user can edit moment without image in vaccine log")
	public void verifyEditMomentCardWithoutImage_VaccineLogPageTest() throws Exception {

//		LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile with Height and Milestone from Add
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile with Height and Milestone from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		// Go to Vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		// Tap on moment by note
		vaccineLogPage.tapOnElementByText(testNote);
		// Tap on Edit moment button
		editMomentPage = vaccineLogPage.tapEditMomentBtn();
		logAssert.assertTrue(editMomentPage.isElementDisplayed(Constants.EDITMOMENT_TITLE),
				"Check if " + Constants.EDITMOMENT_TITLE + " title displayed on Edit moment Page");
		logAssert.assertTrue(editMomentPage.isElementDisplayed(Constants.EDITMOMENT_SAVEMOMENT),
				"Check if " + Constants.EDITMOMENT_SAVEMOMENT + " button displayed on Edit moment Page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 12, testName = "VaccineLogPage-Verify user can delete moment without image in vaccine log", description = "VaccineLogPage-Verify user can delete moment without image in vaccine log")
	public void verifyDeleteMomentCardWithoutImage_VaccineLogPageTest() throws Exception {

		// LogAssert logAssert = new LogAssert();

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMoments_afterMomentcreation;
		int noOfMoments_afterMomentDeletion;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);
		} else {
			// Create Moment for First Child Profile

			System.out.println("momentsList_PrimaryProfile size initial:" + noOfMomentsInitial);
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);

			Thread.sleep(1000);
		}

		noOfMoments_afterMomentcreation = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		System.out.println("All ids after moment is created: " + ids_moments_afterMomentcreation);
		System.out.println("id created moment: " + idCreatedMoment);

		logAssert.assertNotEquals(noOfMoments_afterMomentcreation, noOfMomentsInitial,
				"Check if Moment saved in backend");
		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		// Tap on moment in vaccine log by text
		vaccineLogPage.tapOnElementByText(testNote);
		// Tap on edit moment button
		editMomentPage = vaccineLogPage.tapEditMomentBtn();

		// Delete Moment
		editMomentPage.tapDeleteMomentBtn();

		// Check no. of moments after deletion
		noOfMoments_afterMomentDeletion = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentDeletion = genericFunctionsAPI.getIDsOfSavedMoment();
		System.out.println("All ids after moment is deleted: " + ids_moments_afterMomentDeletion);

		logAssert.assertEquals(noOfMoments_afterMomentDeletion, noOfMomentsInitial,
				"Check if Moment deleted in backend");

		logAssert.assertFalse(ids_moments_afterMomentDeletion.contains(idCreatedMoment),
				"Check if Moment deleted at backend by verifying if momentid is present in list");
		logAssert.assertFalse(vaccineLogPage.isElementDisplayed(testNote),
				"Check if Moment deleted from vaccine log page, Note of deleted moment not to be displayed on vaccine log page");
		// Tap on back button
		momentsLogPage = vaccineLogPage.tapBackBtn();
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(testNote),
				"Check if Moment deleted from Moments log page, Note of deleted moment not to be displayed on Moments Log page");

		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 13, testName = "VaccineLogPage-Verify user can share moment without image in other apps in vaccine log", description = "VaccineLogPage-Verify that if user is able to share a moment in vaccine log externally in other apps")
	public void verifyMomentShareExternalAppsWithoutImage_VaccineLogPageTest() throws Exception {

		// LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);

			Thread.sleep(1000);
		}
		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// Tap on moment by note
		vaccineLogPage.tapOnElementByText(testNote);
		// Tap on Share in other apps
		vaccineLogPage.tapShareInOtherAppsBtn();
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(Constants.APP_MESSAGES),
				"Check " + Constants.APP_MESSAGES + " app is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 14, testName = "VaccineLogPage-Verify user can share moment without image in group chat in vaccine log", description = "VaccineLogPage-Verify that if user is able to share a moment in vaccine log in group chat")
	public void verifyMomentWithoutImageShareInGroupChat_VaccineLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetching groups that primary user is part of
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		// If user not part of any group
		if (Groups_User.size() == 0) {
			Response availGroupList = groupAdviceAPI.getAllAvailableGroups();
			String json = availGroupList.getBody().asString();
			String communityGroupTypeId = genericFunctionsAPI.getTypeOfGroupIdForCommunityGroup();
			groupIDs = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.[?(@.aiabase_typeofgroup=='" + communityGroupTypeId + "' )].listid");
			topicIDs = com.jayway.jsonpath.JsonPath.parse(json).read("$.[?(@.aiabase_typeofgroup=='"
					+ communityGroupTypeId + "' )].aiabase_Topic.aiabase_groupmasterid");

			System.out.println("Size of available groups community: " + groupIDs.size());
			if (groupIDs.size() > 0) {
				// Join first available group
				groupID1 = groupIDs.get(0);
				groupType1 = communityGroupTypeId;
				topicID1 = topicIDs.get(0);
				Response resp = groupAdviceAPI.joinGroupV2(groupID1, groupType1, topicID1);
				logAssert.assertEquals(resp.statusCode(), 200, "Check if user was able to join group successfully");
			}

			else {
				throw new SkipException(
						"No available group for user to join.Can't execute test case further.User requires atleast 1 available group to join");
			}

			// Reopen app for data refresh
			getDriver().launchApp();
			// login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// Fetching groups that primary user is part of
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);
		} else {
			// Create Moment for First Child Profile

			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestone(testNote, milestone, childPreferredName);

			Thread.sleep(1000);
		}
		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// Tap on moment in vaccine log by text
		vaccineLogPage.tapOnElementByText(testNote);
		// Tap on share in group chat
		vaccineLogPage.tapShareInGroupChatBtn();

		Thread.sleep(2000);

		for (int i = 0; i < Groups_User.size(); i++) {
			// Check if groups fetched from backend for user are displayed on front end
			logAssert.assertTrue(vaccineLogPage.isGroupDisplayed(Groups_User.get(i)),
					"Check if" + Groups_User.get(i) + " group is displayed");

		}

		logAssert.assertAll();

	}

//Full page Test cases

	@Test(enabled = true, priority = 15, testName = "VaccineLogPage-Verify tap on growth card and view Full page image", description = "VaccineLogPage-Verify tap on growth card and view Full page image, by creating moment with image,height,weight,note,milestone first and than tapping on same")
	public void verifyFullPageImageView_VaccineLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String imageUrl = "";
		String note = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile

			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile with Height and Milestone from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend
			// for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, " Check if Image saved in backend ");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		// Go to Vaccine log
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		// Tap on moment in Vaccine log by note
		vaccineLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		logAssert.assertTrue(fullImageViewPage.verifyFirstIndexCarousalDisplayed(),
				"Check if first Index of carousal is displayed");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(" " + height + " cm"),
				"Check if Height:" + height + " is displayed on Full Image View Page");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(" " + weight + " kg"),
				"Check if Weight:" + height + " is displayed on  on Full Image View  Page");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(testNote),
				"Check if Note:" + testNote + " is displayed on on Full Image View  Page");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(milestone),
				"Check if Milestone: " + milestone + " as saved in Moment is displayed on Full Image View Page");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if" + Constants.MOMENTS_LOG_TITLE + "title displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 16, testName = "VaccineLogPage-Verify tap on More action button on Full Page Image view", description = "VaccineLogPage-Verify tap on More action button on Full Page Image view and user able to view options: Edit Moment,\n"
			+ "Share in group chat,\n" + "Share in other apps\n" + "")
	public void verifyMoreButtonFullPageImageView_VaccineLogPageTestt() throws Exception {

//		LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String imageUrl = "";
		String note = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend
			// for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		// Check if if moment is saved in Backend
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		// Go to Vaccine Log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		// tap on Moment created by tapping on text
		vaccineLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());
		// Tap on ... button on top left corner of moment
		fullImageViewPage.tapThreeDots_MoreBtn();

		// Check if links displayed on tapping More button
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.EDITMOMENT_TITLE), "Check if "
				+ Constants.EDITMOMENT_TITLE + " link is displayed on tapping More button on Full Image View page ");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.SHARE_IN_GROUPCHAT), "Check if "
				+ Constants.SHARE_IN_GROUPCHAT + " link is displayed on tapping More button on Full Image View page ");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.SHARE_IN_OTHERAPPS), "Check if "
				+ Constants.SHARE_IN_OTHERAPPS + " link is displayed on tapping More button on Full Image View page ");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 17, testName = "VaccineLogPage-Verify cross btn on Full Page Image view", description = "VaccineLogPage-Verify on clicking cross btn on Full image view page, user lands on vaccine log page")
	public void verifyCrossBtnFullImageView_VaccineLogPageTest() throws Exception {

		// LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String imageUrl = "";
		String note = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend
			// for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		// Check if if moment is saved in Backend
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		// Go to vaccine Log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		// Tap on moment created by text
		vaccineLogPage.tapOnElementByText(testNote);

		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Cross Btn on Full Image View Page
		fullImageViewPage.tapCrossBtn();

		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(Constants.VACCINE_LOG_TITLE),
				"Check if" + Constants.VACCINE_LOG_TITLE + "Title displayed, Vaccine log page is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 18, testName = "VaccineLogPage-Verify edit moment by tapping on More action button ", description = "VaccineLogPage-Verify edit moment by tapping on More action button, by first creating moment with image and than editing note for same ")
	public void verifyEditMomentFullImageView_VaccineLogPageTest() throws Exception {

//		LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String newImageURLbackend = "", imageUrl = "";
		String note = "", newNoteBackend = "";
		Response MomentsList;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend
			// for last moment saved
			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		// Check if if moment is saved in Backend
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		// Tap on Image with Note
		// Go to Vaccine Log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// vaccineLogPage.tapChildBtn(childPreferredName);
		// Tap on Moment created by tapping on note
		vaccineLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Edit moment
		editMomentPage = fullImageViewPage.tapEditMomentBtn();
		// Append note to existing note
		editMomentPage.appendNote(testNote, "1234");
		// Tap save moment button
		editMomentPage.tapSaveMoment();
		String newNote = testNote + "1234";

		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,imageurl saved in backend for last moment saved

			newNoteBackend = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			newImageURLbackend = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		System.out.println("---------------------------------");
		System.out.println("new note backend: " + newNoteBackend);
		System.out.println("---------------------------------");

		logAssert.assertEquals(newNoteBackend, newNote, "Check if Note saved in backend");
		logAssert.assertNotNull(newImageURLbackend, " Check if Image saved in backend  ");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(Constants.VACCINE_LOG_TITLE),
				"Check if" + Constants.VACCINE_LOG_TITLE + "Title to be displayed,Vaccine log page displayed");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(newNote), "Check if"
				+ "New moment  saved on Front end as Note: " + newNote + " to be displayed on Moments Log page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 19, testName = "VaccineLogPage-Verify share moment to groups by tapping on three dots(More) action button on Full Image View page ", description = "VaccineLogPage-Verify share moment to groups by tapping on three dots(More) action button on Full Image View page, by first creating moment with image and than sharing same ")
	public void verifyShareInGroupChatFullImageView_VaccineLogPageTest() throws Exception {

//		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetching groups that user is part of
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		// If user not part of any group
		if (Groups_User.size() == 0) {
			Response availGroupList = groupAdviceAPI.getAllAvailableGroups();
			String json = availGroupList.getBody().asString();
			String communityGroupTypeId = genericFunctionsAPI.getTypeOfGroupIdForCommunityGroup();
			groupIDs = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.[?(@.aiabase_typeofgroup=='" + communityGroupTypeId + "' )].listid");
			topicIDs = com.jayway.jsonpath.JsonPath.parse(json).read("$.[?(@.aiabase_typeofgroup=='"
					+ communityGroupTypeId + "' )].aiabase_Topic.aiabase_groupmasterid");

			System.out.println("Size of available groups community: " + groupIDs.size());
			if (groupIDs.size() > 0) {
				// Join first available group
				groupID1 = groupIDs.get(0);
				groupType1 = communityGroupTypeId;
				topicID1 = topicIDs.get(0);
				Response resp = groupAdviceAPI.joinGroupV2(groupID1, groupType1, topicID1);
				logAssert.assertEquals(resp.statusCode(), 200, "Check if user was able to join group successfully");
			}

			else {
				throw new SkipException(
						"No available group for user to join.Can't execute test case further.User requires atleast 1 available group to join");
			}

			// Reopen app for data refresh
			getDriver().launchApp();

			// login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// Fetching groups that user is part of
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String imageUrl = "";
		String note = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend
			// for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		// Check if if moment is saved in Backend
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
//		vaccineLogPage.tapChildBtn(childPreferredName);
		// Tap on Image with Note
		vaccineLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap on Share in Group chat
		fullImageViewPage.tapShareInGroupChatBtn();

		Thread.sleep(2000);

		for (int i = 0; i < Groups_User.size(); i++) {
			// Check groups for user fetched from backend are displayed in frontend
			logAssert.assertTrue(fullImageViewPage.isGroupDisplayed(Groups_User.get(i)),
					"Check if " + Groups_User.get(i) + " group is displayed");

		}

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 20, testName = "VaccineLogPage-Verify share moment to external apps by tapping on three dots(More) action button on Full Image View page ", description = "VaccineLogPage-Verify share moment to external apps by tapping on three dots(More) action button on Full Image View page, by first creating moment with image and than sharing same ")
	public void verifyShareInExternalAppsFullImageView_VaccineLogPageTest() throws Exception {

//		LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String imageUrl = "";
		String note = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl ,babyHeight,babyWeight saved in backend
			// for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		// Check if if moment is saved in Backend
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		// Go to vaccine log
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
//		vaccineLogPage.tapChildBtn(childPreferredName);
		// Tap on moment with Note
		vaccineLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Share in Other apps
		fullImageViewPage.tapShareInOtherAppsBtn();
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.APP_MESSAGES),
				"Check if " + Constants.APP_MESSAGES + " app displayed");
		logAssert.assertAll();
	}

}
