package com.pom.crimson.testcases.momentlog.medium;

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
 * This test class contains test cases for this New moment page.<br>
 * AddNewMomentsPage or New moment page can be used to add moments for primary profile or child profiles.
 * <br> 
 * User can navigate to this page by going to:
 * <br>
 * <br> 
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget<br>
 * Click <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b> link
 * <br>
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
	String childPreferredName="";
	@BeforeMethod()
	public void beforeLocalMethod() throws IOException {
//		seedImages();
		genericFunctionsAPI = new GenericFunctionsAPI();
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
	
	}

	@Test(enabled = true, priority = 1, testName = "Delete all moments-for data clean up", 
			description = "Delete all moments-for data clean up")
	public void deleteAllData_AddNewMomentPageTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}
	
	
	@Test(enabled = true,priority = 1, testName = "AddNewMomentPage-Verify moment creation for Myself with Image and Note ", description = "AddNewMomentPage-Verify moment creation when user creates a moment for Myself by adding note and Image")
	public void verifyMomentForMyselfWithImageNote_AddNewMomentsPageTest() throws Exception {
		
		LogAssert logAssert=new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String note = "";
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for Myself
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1,testNote,Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment for Myself

			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1,testNote,Constants.ADDNEWMOMENT_MYSELF_TAB);

			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		 // Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,ids_moments_afterMomentcreation);		
		
		try {
		Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
        // Retrieving note,imageUrl saved in backend for last moment saved
		note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
		imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		}
		catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note is saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image is saved in backend ");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote), "Check if  Note: "+testNote+" is displayed on Moments Log Page");
		logAssert.assertAll();

	}
/*
	@Test(enabled = true, priority = 2,
			testName = "AddNewMomentPage-Verify the functionality for other tabs (like Myself ) when user selects a    ChildA tab", description = "AddNewMomentPage-Verify the functionality for other tabs (like Myself ) when user selects a ChildA tab, Myself tab should grey out on selecting ChildA tab")
	public void verifyMyselfGreyOutOnChildTabSelection_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		String childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		
		if (childPreferredName.equals(null)) {
			throw new SkipException("Please create a child profile for running this test case");
		}
		
		momentsLogPage = homePage.goToMomentsLogPage();
		if (momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT)) {
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}

		addNewMomentsPage.tapOnElementByText(Constants.ADDNEWMOMENT_MYSELF_TAB);
		int initialRedColorMyself= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.ADDNEWMOMENT_MYSELF_TAB);
		int initialRedColorChild= GenericFunctions.findRedColorValueOfElement(getDriver(), childPreferredName);
		addNewMomentsPage.tapOnElementByText(childPreferredName);
		int finalRedColorMyself= GenericFunctions.findRedColorValueOfElement(getDriver(), Constants.ADDNEWMOMENT_MYSELF_TAB);
		int finalRedColorChild= GenericFunctions.findRedColorValueOfElement(getDriver(), childPreferredName);
	
		logAssert.assertNotEquals(finalRedColorMyself, initialRedColorMyself,"Myself tab not highlighted in grey");
		logAssert.assertNotEquals(finalRedColorChild, initialRedColorChild,"Child tab not highlighted in red");
		logAssert.assertAll();
		
	}*/
	
	@Test(enabled = true, priority = 3,
			testName = "AddNewMomentPage-Verify that user is able to save note upto 140 characters", 
			description = "AddNewMomentPage-Verify that user is able to save note upto 140 characters")
	public void verifySaveNote140Chars_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();


		String testNote = GenericFunctions.generateRandomAlphanumericStringLength(140);
		int noOfMomentsInitial=0;
		int noOfMomentsFinal=0;
		String note = "";
		String date="";
		
		// Checking initial number of moments in backend
		 noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> momentIdInitial=genericFunctionsAPI.getIDsOfSavedMoment();
		
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote,
					 Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote,
					 Constants.ADDNEWMOMENT_MYSELF_TAB);
			Thread.sleep(1000);
		}
try {
		 Thread.sleep(2000);
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> momentIdFinal=genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String momentIdlastMoment=genericFunctionsAPI.getMomentIdLastSavedMoment(momentIdInitial, momentIdFinal);		
		Response MomentsList= genericFunctionsAPI.getResponseSavedMoments();
        // Retrieving note,date saved in backend for last moment saved
		note = genericFunctionsAPI.getNoteByMomentId(MomentsList, momentIdlastMoment);
		date=genericFunctionsAPI.getDateByMomentId(MomentsList, momentIdlastMoment);
}
		catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}

		
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note: "+testNote+" is saved in backend");
		logAssert.assertNotNull(date, "Check if date is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If Note as saved in Moment,Note: "+testNote+" to be displayed on Moments Log Page");
		logAssert.assertAll();
	}
	
	@Test(enabled = true, priority = 4,testName = "AddNewMomentPage-Verify that user is  not able to save note greater than 140 characters", 
			description = "AddNewMomentPage-Verify that user is  not able to save note greater than 140 characters, Note is trimmed to 140 characters and saved in moment")
	public void verifySaveNoteMoreThan140Chars_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();

		String testNote = GenericFunctions.generateRandomAlphanumericStringLength(140);
		String testNote_greaterthan140chars = testNote+"1234";

		int noOfMomentsInitial=0;
		int noOfMomentsFinal=0;
		String note = "";
		String date="";

		// Checking initial number of moments in backend
		 noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> momentIdInitial=genericFunctionsAPI.getIDsOfSavedMoment();

		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote_greaterthan140chars,
					 Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote_greaterthan140chars,
					 Constants.ADDNEWMOMENT_MYSELF_TAB);
			Thread.sleep(1000);
		}
		
		try {
			Thread.sleep(2000);
			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			List<String> momentIdFinal=genericFunctionsAPI.getIDsOfSavedMoment();
			 // Getting moment id of last saved moment from backend
			String momentIdlastMoment=genericFunctionsAPI.getMomentIdLastSavedMoment(momentIdInitial, momentIdFinal);		
			Response MomentsList= genericFunctionsAPI.getResponseSavedMoments();
	        // Retrieving note,date saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, momentIdlastMoment);
			date=genericFunctionsAPI.getDateByMomentId(MomentsList, momentIdlastMoment);
		} catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}

		
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertNotEquals(note, testNote_greaterthan140chars, "Check if Note: "+note+" greater than 500 chars should not be in backend");
		logAssert.assertEquals(note, testNote, "Check if Note: "+note+"  equal to 500 chars is saved in backend");
		logAssert.assertNotNull(date, "Check if date is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If Note as saved in Moment,Note: "+testNote+" to be displayed on Moments Log Page");
	
		logAssert.assertAll();
	}
	
	@Test(enabled = true, priority = 5,testName = "AddNewMomentPage-Verify error message when user enters height as 0", 
			description = "AddNewMomentPage-Verify user is able to view error message when user enters 0 as height")
	public void verifyErrorInvalidHeightZero_EditMomentPageTest() throws Exception {
		
		LogAssert logAssert=new LogAssert();
		int noOfMomentsInitial;
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();	
		}
		String invalidHeight=Constants.INVALID_HEIGHT;
		//Tap Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);
		//Add invalid height
		addNewMomentsPage.addHeight(invalidHeight);
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT),"Check if following  Error message is displayed on entering invalid height: "+Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT);
		//This is an invalid height/weight input
		logAssert.assertAll();
	
	}
	
	@Test(enabled = true,priority = 6, testName = "AddNewMomentPage-Verify error message when user enters weight as 0", 
			description = "AddNewMomentPage-Verify user is able to view error message when user enters 0 as weight")
	public void verifyErrorInvalidWeightZero_EditMomentPageTest() throws Exception {
		
		LogAssert logAssert=new LogAssert();
		
		int noOfMomentsInitial;
		
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();	
		}
		String invalidWeight=Constants.INVALID_WEIGHT;
		//Tap Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);
		// Add invalid weight
		addNewMomentsPage.addWeight(invalidWeight);
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT),"Check if following  Error message is displayed on entering invalid height: "+Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT);
		//This is an invalid height/weight input
		logAssert.assertAll();
	}
	
	@Test(enabled = true,priority = 7, 
			testName = "AddNewMomentPage-Verify error message when user enters weight as special characters", 
			description = "AddNewMomentPage-Verify user is able to view error message when user enters special characters #@!$%^&*()_+ as weight")
	public void verifyErrorInvalidWeightSpclChars_EditMomentPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();

		int noOfMomentsInitial;
		
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();	
		}
		String invalidWeight=Constants.INVALID_WEIGHT_SPCL_CHARS;
		//Tap Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);
		// Add invalid weight
		addNewMomentsPage.addWeight(invalidWeight);
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT),"Check if following  Error message is displayed on entering invalid height: "+Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT);
		//This is an invalid height/weight input
		logAssert.assertAll();
	
	
	}
	
	@Test(enabled = true,priority = 8, testName = "AddNewMomentPage-Verify error message when user enters height as special characters", 
			description = "AddNewMomentPage-Verify user is able to view error message when user enters special characters #@!$%^&*()_+ as height")
	public void verifyErrorInvalidHeightSpclChars_EditMomentPageTest() throws Exception {
		
		LogAssert logAssert=new LogAssert();

		
		int noOfMomentsInitial;
		
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();	
		}
		String invalidHeight=Constants.INVALID_HEIGHT_SPCL_CHARS;
		//Tap Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);
		//Add invalid height
		addNewMomentsPage.addHeight(invalidHeight);
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT),"Check if following  Error message is displayed on entering invalid height: "+Constants.ADDNEWMOMENT_ERROR_HEIGHT_WEIGHT);
		//This is an invalid height/weight input
		logAssert.assertAll();
	}
	
	@Test(enabled = true,priority = 9, testName = "AddNewMomentPage-Verify user should be able to select 5 images at max", 
			description = "AddNewMomentPage-Verify at a time user should be able to select 5 images at max,"
					+ "Option to select 6th image should not be enabled")
	public void verifyMoreThan5Images_AddNewMomentPageTest() throws Exception {
	
		LogAssert logAssert=new LogAssert();
		int noOfMomentsInitial;
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		
		if (noOfMomentsInitial == 0) 
		{
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			 // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();	
		}
		// Add 6 images
		boolean result= addNewMomentsPage.addImage(6);
		
		logAssert.assertFalse(result,"Check if user able to add 6 images" );
		logAssert.assertAll();
	}
	
	@Test(enabled = true, priority = 10,testName = "AddNewMomentPage-Verify when user adds a single image and deletes it while creating moment", 
			description = "AddNewMomentPage-Verify when user adds a single image and deletes it while creating moment,Image should be deleted and default message should be displayed \n"
					+ "\n"
					+ "\"Add here your special capture of the day\"")
	public void verifyFirstImageDelete_AddNewMomentPageTest() throws Exception {
	
		LogAssert logAssert=new LogAssert();

		int noOfMomentsInitial;
		
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		
		if (noOfMomentsInitial == 0) 
		{
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();	
		}
		
		// Add 1 image
		addNewMomentsPage.addImage(1);
		// Delete first image
		addNewMomentsPage.tapDeleteFirstImage();
		
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_DEFAULT_IMAGE_SECTION_TEXT),"Check default image section text: "+Constants.ADDNEWMOMENT_DEFAULT_IMAGE_SECTION_TEXT+"is displayed" );
		logAssert.assertAll();
	}
	
	@Test(enabled = true,priority = 11, testName = "AddNewMomentPage-Verify Date selection for current, past and Future date", 
			description = "AddNewMomentPage-Verify by default current date is selected in calendar, past date is avialable for selection and future date is disabled")
	public void verifyDateSelection_AddNewMomentPageTest() throws Exception {
	
		int noOfMomentsInitial;
		LogAssert logAssert=new LogAssert(); 
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		 // Tap on Add new moment button
		momentsLogPage = homePage.goToMomentsLogPage();
		
		if (noOfMomentsInitial == 0) 
		{
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();	
		}
		//Tap calendar
		addNewMomentsPage.tapCalendar();
		logAssert.assertTrue(addNewMomentsPage.verifyCurrentDateSelected(),"Check if current date is selected");
		logAssert.assertTrue(addNewMomentsPage.verifyFutureDateSelection(),"Check if future date is disabled");
		logAssert.assertTrue(addNewMomentsPage.verifyPastDateSelection(),"Check if past date is enabled and available for selection");
		logAssert.assertAll();	
	}

}
