package com.pom.crimson.testcases.momentlog.smoke;

import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.ManageProfilePage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.VaccineLogPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.response.Response;

/**
 * This test class contains test cases for New moment page.<br>
 * AddNewMomentsPage or New moment page can be used to add moments for primary
 * profile or child profiles. <br>
 * User can navigate to this page by going to: <br>
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b>
 * widget<br>
 * Click
 * <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b>
 * link <br>
 * <br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class AddNewMomentPageTest extends BaseFixture {

	HomePage homePage;
	MomentsLogPage momentsLogPage;
	AddNewMomentsPage addNewMomentsPage;
	EditMomentPage editMomentPage;
	String milestone = Constants.MILESTONE_VACCINEDAY;
	ManageProfilePage manageProfilePage;
	LoginPage loginPage;
	GenericFunctionsAPI genericFunctionsAPI;
	String childPreferredName = "";

	@BeforeMethod()
	public void beforeLocalMethod() throws IOException {
		// seedImages();
		genericFunctionsAPI = new GenericFunctionsAPI();
		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}
		getDriver().launchApp();//launch app
		loginPage = new LoginPage(getDriver(), getPlatformName());

		//login
		try {
			loginPage.loginAllPlatforms();
		} catch (Exception e) {
			e.printStackTrace();
		}

		homePage = new HomePage(getDriver(), getPlatformName());

	}

	@Test(enabled = true, priority = 1, testName = "Delete all moments-for data clean up", description = "Delete all moments-for data clean up")
	public void deleteAllData_AddNewMomentPageTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}

	@Test(enabled = true, priority = 2, testName = "AddNewMomentPage-Verify navigation to Add new Moment page from Moments log page", description = "AddNewMomentPage-Verify navigation to Add new Moment page from Moments log page")
	public void verifyNavigationToAddNewMomentPage_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_TITLE),
				"Check for " + Constants.ADDNEWMOMENT_TITLE + " title");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "AddNewMomentPage-Verify If user is able to upload single  image and save a moment", description = "AddNewMomentPage-Verify If user is able to upload single image from phone library and save a moment")
	public void selectSingleImageFromGallery_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// create First Moment for Myself with single image
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImage(1, Constants.ADDNEWMOMENT_MYSELF_TAB);

		} else {
			// create Moment for Myself with single image
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImage(1, Constants.ADDNEWMOMENT_MYSELF_TAB);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving imageurlsaved in backend for last moment saved

			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is Saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image is saved in backend ");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE + " is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "AddNewMomentPage-Verify at a time user should be able to save  5 images in a moment", description = "AddNewMomentPage-Verify at a time user should be able to save  5 images in a moment")
	public void selectMultipleImageFromGallery_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// create First Moment for Myself with single image
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImage(5, Constants.ADDNEWMOMENT_MYSELF_TAB);

		} else {
			// create Moment for Myself with single image
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImage(5, Constants.ADDNEWMOMENT_MYSELF_TAB);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving imageurl saved in backend for last moment saved

			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is Saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Images are saved in backend ");

		// Check occurence of pattern "https" in imageURl received from backed. It
		// should be 5
		int noOfImages = GenericFunctions.checkOccurenceOfPatternInString(imageUrl, "https");

		logAssert.assertEquals(noOfImages, 5, "Check if All 5 Images are saved in backend ");

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE + " is displayed-user should land on Moments log page");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "AddNewMomentPage-Verify moment creation for Myself with Note only", description = "AddNewMomentPage-Verify moment creation when user creates a moment for Myself by adding note")
	public void verifyMomentForMyselfWithNote_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String note = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for Myself
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment for Myself

			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);

			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check if Note: " + testNote + "is  displayed on MomentsLog page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 6, testName = "AddNewMomentPage-Verify that user is able to delete a moment", description = "AddNewMomentPage-Verify that user is able to delete a moment")
	public void verifyMomentDeletion_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for Myself
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment for Myself
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
			Thread.sleep(1000);
		}

		// Checking number of moments in backend after moment is created
		int noOfMoments_afterMomentcreation = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Get id of created moment
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		logAssert.assertNotEquals(noOfMoments_afterMomentcreation, noOfMomentsInitial,
				"Check if Moment is saved in backend");

		// Tapping on Moment in Moments Log page by tapping on note
		momentsLogPage.tapOnElementByText(testNote);

		// Tap Edit moment
		editMomentPage = momentsLogPage.tapEditMomentBtn();
		// Tap Delete moment
		editMomentPage.tapDeleteMomentBtn();

		Thread.sleep(3000);

		// Get saved moments for profile from api after moment deletion
		int noOfMoments_afterMomentDeletion = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentDeletion = genericFunctionsAPI.getIDsOfSavedMoment();

		logAssert.assertEquals(noOfMoments_afterMomentDeletion, noOfMomentsInitial,
				"Check if Moment is deleted in backend");
		logAssert.assertFalse(ids_moments_afterMomentDeletion.contains(idCreatedMoment),
				"Check if id of moment is removed from backend list");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(testNote),
				"Check if  Note of moment is not displayed on Moments Log page as moment is deleted");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 7, testName = "AddNewMomentPage-Verify user is able to click cross button on new moment screen", description = "AddNewMomentPage-Verify user is able to click cross button on new moment screen")
	public void verifyCrossBtn_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT)) {
			// Tap Add your first moment button, if Add your first moment button is
			// displayed
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		// Tap cross button on New moment page
		addNewMomentsPage.tapCrossBtn();

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Moments Log page not displayed on clicking cross  button as Moments log title not displayed");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 8, testName = "AddNewMomentPage-Verify moment with camera image", description = "AddNewMomentPage-Verify user should be able to create a moment with camera image")
	public void verifyCameraImageMoment_AddNewMomentPageTest() throws Exception {

		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String imageUrl = "";
		String note = "";
		LogAssert logAssert = new LogAssert();
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();

		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}

		// Take photo and accept
		addNewMomentsPage.takePhotoAndAccept();
		// Add Note
		addNewMomentsPage.addNote(testNote);
		// Tap Done
		momentsLogPage = addNewMomentsPage.tapDone();

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,imageURL saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check if moment displayed on Moments Log Page by verifying note displayed: " + testNote);
		logAssert.assertAll();
	}

}
