package com.pom.crimson.testcases.momentlog.high;

import java.io.IOException;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.pom.crimson.listeners.LogAssert;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.pages.AddNewMomentsPage;
import com.pom.crimson.pages.EditMomentPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.VaccineLogPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.restassured.response.Response;
/**
 * This test class contains test cases for Edit moment page.<br>
 * Edit moment page can be used to edit moments for primary profile or child profiles.
 * <br> 
 * User can navigate to this page by going to:
 * <br> 
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget <br>
 * Click <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b> link <br>
 * Create a moment<br>
 * Once moment is created,Tap on Moment and select <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_TITLE}</b> 
 * <br>
 * <br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class EditMomentPageTest extends BaseFixture {
	
	HomePage homePage;
	MomentsLogPage momentsLogPage;
	VaccineLogPage vaccineLogPage;
	AddNewMomentsPage addNewMomentsPage;
	EditMomentPage editMomentPage;
	String milestone = Constants.MILESTONE_VACCINEDAY;
	GenericFunctionsAPI genericFunctionsAPI;
	String childPreferredName="";

	@BeforeMethod()
	public void beforeLocalMethod() throws IOException {
	//	seedImages();
		genericFunctionsAPI = new GenericFunctionsAPI();
		childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		if (childPreferredName.equals("")) {
			// Create child profile if not created already
			genericFunctionsAPI.createFirstChildProfile();
			childPreferredName = genericFunctionsAPI.getFirstChildPreferredName();
		}
		getDriver().launchApp();
		LoginPage loginPage = new LoginPage(getDriver(), getPlatformName());
		
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		homePage=new HomePage(getDriver(),getPlatformName());
		}
	
	@Test(enabled = true, priority = 1, testName = "Delete all moments-for data clean up", 
			description = "Delete all moments-for data clean up")
	public void deleteAllData_EditMomentPageTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}
	
	@Test(enabled = true, priority = 2, testName = "EditMomentPage-Verify user is able to click cross button on edit moment screen", description = "EditMomentPage-Verify user is able to click cross button on edit moment screen")
	public void verifyCrossBtn_EditMomentPageTest() throws Exception {

		LogAssert logAssert=new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		momentsLogPage = homePage.goToMomentsLogPage();
		if (momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT)) {
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage=addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage=addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
		}
		Thread.sleep(2000);
		momentsLogPage.tapOnElementByText(testNote);
		editMomentPage = momentsLogPage.tapEditMomentBtn();
		editMomentPage.tapCrossBtn();
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),"Check if "+Constants.MOMENTS_LOG_TITLE+" is displayed-Moments log page should be displayed");
		logAssert.assertAll();
		
	}
	
	@Test(enabled = true, priority = 3, testName = "EditMomentPage-Verify that user is able to edit moment and remove milestone", 
			description = "EditMomentPage-Verify that user is able to edit moment and remove milestone, by first creating moment and then remove milestone from it")
	public void verifyRemoveMilestone_EditMomentPageTest() throws Exception {
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = null;
		String babyHeight = null;
		String milestoneId = null;
		String note = null;
		Response MomentsList;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		momentsLogPage = homePage.goToMomentsLogPage();//Go to Moments Log Page
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote,
					milestone, height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight( testNote,
					milestone, height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);

		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		 // Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,ids_moments_afterMomentcreation);		
		
		try {
	 MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	// Retrieving note,milestoneID,babyHeight,babyWeight saved in backend for last moment saved
		note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
		milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
		babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
		babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		}
		catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}


		LogAssert logAssert = new LogAssert();
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(babyWeight, weight,"Check if "+ "Weight: "+weight+" is saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height: "+height+" is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note: "+testNote+" is saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone: "+milestone+"  saved in backend");
		
		
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height "+height+" to be  displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight: "+weight+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If Note as saved in Moment,Note: "+testNote+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If milestone as saved in Moment,"+milestone+" Milestone to be displayed on Moments Log Page");

		//Tapping on moment with note on Moments Log page
		momentsLogPage.tapOnElementByText(testNote);
		//Tapping on Edit Moment 
		editMomentPage = momentsLogPage.tapEditMomentBtn();
		//Tapping on Child tab on New Moment page
		editMomentPage.tapChildBtn(childPreferredName);
		//Tapping on Milestone
		editMomentPage.tapMilestone(milestone);
		//Tapping on Save Moment button
		editMomentPage.tapSaveMoment();
		
		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving milestoneID,babyHeight,babyWeight saved in backend for last moment saved
			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
			}
			catch(Exception e)
			{
				ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend" +e.getMessage());

			}
		
		//System.out.println("Milestone ID: "+milestoneId);
			try {	
		logAssert.assertNull(milestoneId, "Check if Milestone  is removed from backend");
			}
	catch(NullPointerException e)
	{
		ExtentReportManager.getTest().log(Status.PASS, "Check if milestone is removed in backend");

	}
		logAssert.assertEquals(babyWeight, weight, "Check if Weight: "+weight+" is saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height: "+height+" is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note: "+testNote+" is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height: "+height+" to be displayed on Moments Log Page after moment is edited");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight: "+weight+" to be displayed on Moments Log Page after moment is edited");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If note as saved in Moment,Note: "+testNote+" to be displayed on Moments Log Page after moment is edited");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(milestone),
				" Check if milestone: "+milestone+"not to be displayed on Moments Log Page after removing");	
		logAssert.assertAll();
	}
	
	@Test(enabled = true, priority = 4, testName = "EditMomentPage-Verify that user is able to edit moment and remove note", 
			description = "EditMomentPage-Verify that user is able to edit moment and remove note, by first creating moment and then remove note from it")
	public void verifyRemoveNote_EditMomentPageTest() throws Exception {
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = null;
		String babyHeight = null;
		String milestoneId = null;
		String note = null;
		Response MomentsList;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote,
					milestone, height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight( testNote,
					milestone, height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			// Getting moment id of last saved moment from backend
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,ids_moments_afterMomentcreation);		
		
		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	    // Retrieving note,milestoneID,babyHeight,babyWeight saved in backend for last moment saved
		note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
		milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
		babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
		babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		}
		catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}
		
		LogAssert logAssert = new LogAssert();
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight: "+weight+" is saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height: "+height+" is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note: "+testNote+" is saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone: "+milestone+" is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height: "+height+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight: "+weight+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If note as saved in Moment,Note: "+testNote+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If mileston as saved in Moment,"+milestone+" Milestone to be displayed on Moments Log Page");
		
		//Tapping on Moment in Moments Log page by tapping on note
		momentsLogPage.tapOnElementByText(testNote);
		//Tapping on Edit moment
		editMomentPage = momentsLogPage.tapEditMomentBtn();
		// Tapping on Child tab
		editMomentPage.tapChildBtn(childPreferredName);
		//Removing Note
		editMomentPage.removeTextFromField(testNote);
		// Tapping on save moment
		editMomentPage.tapSaveMoment();
		
		try {
			 MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	         // Retrieving note,milestoneID,babyHeight,babyWeight saved in backend for last moment saved
			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
			}
			catch(Exception e)
			{
				ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

			}
		logAssert.assertNotNull(milestoneId, "Check if Milestone: "+milestone+" is saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight: "+weight+" is saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height: "+height+" is saved in backend");
		try {
		logAssert.assertNull(note,  "Check if Note: "+testNote+" is removed in backend");
		}
		catch(NullPointerException e)
		{
			ExtentReportManager.getTest().log(Status.PASS, "Check if note: "+testNote+" is removed in backend");

		}
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height: "+height+" to be displayed on Moments Log Page after moment is edited");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight: "+weight+" to be displayed on Moments Log Page after moment is edited");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(testNote),
				"Check If note as saved in Moment,Note: "+testNote+" not to be  displayed on Moments Log Page after moment is edited-Note should be removed");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If milestone as saved in Moment,Milestone: "+milestone+" to be displayed on Moments Log Page after moment is edited");	
		logAssert.assertAll();
	}
	
	@Test(enabled = true, priority = 5, testName = "EditMomentPage-Verify that user is able to edit moment and remove Height and Weight", 
			description = "EditMomentPage-Verify that user is able to edit moment and remove Height and Weight, by first creating moment and then remove Height and Weight from it")
	public void verifyRemoveHeightWeight_EditMomentPageTest() throws Exception {

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = null;
		String babyHeight = null;
		String milestoneId = null;
		String note = null;
		Response MomentsList;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote,
					milestone, height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile 
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight( testNote,
					milestone, height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		
			// Getting moment id of last saved moment from backend
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,ids_moments_afterMomentcreation);		
		
		try {
		 MomentsList = genericFunctionsAPI.getResponseSavedMoments();
        // Retrieving note,milestoneID,babyHeight,babyWeight saved in backend for last moment saved

		note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
		milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
		babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
		babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		}
		catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}


		LogAssert logAssert = new LogAssert();
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight: "+weight+" is saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height: "+height+" is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note: "+testNote+" is saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone: "+milestone+" is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height: "+height+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight: "+weight+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If note as saved in Moment,Note: "+testNote+" to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If milestone as saved in Moment, Milestone: "+milestone+" to be displayed on Moments Log Page");
		
		//Tapping on Moment in Moments Log page by tapping on note
		momentsLogPage.tapOnElementByText(testNote);
		//Tapping on Edit moment
		editMomentPage = momentsLogPage.tapEditMomentBtn();
		// Tapping on Child tab
		editMomentPage.tapChildBtn(childPreferredName);
		// Remove height
		editMomentPage.removeTextFromField(height);
		// Remove weight
		editMomentPage.removeTextFromField(weight);
		// Tapping Save moment button
		editMomentPage.tapSaveMoment();
		
		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	        // Retrieving note,milestoneID,babyHeight,babyWeight saved in backend for last moment saved
			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
			}
			catch(Exception e)
			{
				ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

			}

				
		logAssert.assertNotNull(milestoneId, "Check if Milestone: "+milestone+" is saved in backend");
		try {
		logAssert.assertNull(babyWeight, "Check if Weight: "+weight+" is removed in backend");
		} catch(NullPointerException e)
		{
			ExtentReportManager.getTest().log(Status.PASS, "Check if Weight: "+weight+" is removed in backend");

		}
		try {
		logAssert.assertNull(babyHeight,"Check if Height: "+height+" is removed in backend");
		}
		catch(NullPointerException e)
		{
			ExtentReportManager.getTest().log(Status.PASS, "Check if Height: "+height+" is removed in backend");

		}
		
		logAssert.assertEquals(note, testNote, "Check if Note: "+testNote+" is saved in backend");

		logAssert.assertFalse(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check If height as saved in Moment,Height"+height+" not to be displayed on Moments Log Page after moment is edited-Height should be removed");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check If weight as saved in Moment,Weight "+weight+" not to be displayed on Moments Log Page after moment is edited-Weight hould be removed");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If note as saved in Moment,Note: "+testNote+" to be displayed on Moments Log Page after moment is edited");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check If milestone as saved in Moment,Milestone: "+milestone+" to be displayed on Moments Log Page after moment is edited");	
		logAssert.assertAll();
	}
		
	@Test(enabled =true, priority = 6, testName = "EditMomentPage-Verify that user is able to edit date", 
			description = "EditMomentPage-Verify that user is able to edit date")
	public void verifyEditDate_EditMomentPageTest() throws Exception {

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String note = "";
		String dateOnFirstSave="";
		String dateAfterEdit="";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		
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

		Thread.sleep(2000);
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,ids_moments_afterMomentcreation);		
		
		try {
		Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
        // Retrieving note,date saved in backend for last moment saved
		note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
		dateOnFirstSave = genericFunctionsAPI.getDateByMomentId(MomentsList, idCreatedMoment);
		}
		catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}

		LogAssert logAssert = new com.pom.crimson.listeners.LogAssert();
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(note, testNote, " Check if Note: "+testNote+" is saved in backend");
		logAssert.assertNotNull(dateOnFirstSave, "Check if date is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If note as saved in Moment,Note: "+testNote+" is displayed on Moments Log Page");
			
		//Tapping on Moment in Moments Log page by tapping on note
		momentsLogPage.tapOnElementByText(testNote);
		//Tapping on Edit moment
		editMomentPage = momentsLogPage.tapEditMomentBtn();
		// Select past calendar date
		editMomentPage.selectCalendarDatePast();
		// Tapping Save moment button
		editMomentPage.tapSaveMoment();
		
		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	        // Retrieving note,date (after edit) saved in backend for last moment saved
			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			dateAfterEdit = genericFunctionsAPI.getDateByMomentId(MomentsList, idCreatedMoment);
			}
			catch(Exception e)
			{
				ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

			}
		
		logAssert.assertNotEquals(dateAfterEdit, dateOnFirstSave, "Check if date is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note: "+testNote+" is saved in backend");
		logAssert.assertAll();
		
	}
	
	
	
}
