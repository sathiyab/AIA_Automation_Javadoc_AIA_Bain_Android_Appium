package com.pom.crimson.testcases.momentlog.high;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddNewMomentsPage;
import com.pom.crimson.pages.EditMomentPage;
import com.pom.crimson.pages.FullImageViewPage;
import com.pom.crimson.pages.GrowthLogPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.VaccineLogPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.restassured.response.Response;
/**
 *This test class contains test cases for Growth log page.<br>
 * Growth log page can be used  view moments of Child profiles saved with height or weight.
 * <br> 
 * User can navigate to this page by going to:
 * <br> 
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget <br>
 * Click <b>{@value com.pom.crimson.util.Constants#VIEW_GROWTH_LOG_LINK}</b> link <br>
 * <br>
 * <br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class GrowthLogPageTest extends BaseFixture {
	HomePage homePage;
	MomentsLogPage momentsLogPage;
	GrowthLogPage growthLogPage;
	VaccineLogPage vaccineLogPage;
	AddNewMomentsPage addNewMomentsPage;
	EditMomentPage editMomentPage;
	LoginPage loginPage;
	FullImageViewPage fullImageViewPage;
	String milestone = Constants.MILESTONE_VACCINEDAY;
	GroupAdviceAPI groupAdviceAPI;
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
		loginPage = new LoginPage(getDriver(), getPlatformName());
		
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		homePage = new HomePage(getDriver(), getPlatformName());
		groupAdviceAPI = new GroupAdviceAPI();
	}
	
	@Test(enabled = true, priority = 1, testName = "Delete all moments-for data clean up", 
			description = "Delete all moments-for data clean up")
	public void deleteAllData_GrowthLogPageTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}

		@Test(enabled = true, priority = 2, testName = "GrowthLogPage-Verify moment with Image+Heigh+Weight+Milestone( Vaccine day)+Note displayed in growth log,vaccine log,Moments log", description = "GrowthLogPage-Verify moment with Image+Heigh+Weight+Milestone( Vaccine day)+Note displayed in vaccine,growth,Moments log")
	public void verifyMomentCreationWithMilestoneNoteHeightWeight_GrowthLogPageTest() throws Exception {
		
		LogAssert logAssert = new LogAssert();
		

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String note = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if initial no. of moments=0
			// Create Moment for First Child Profile with Note,Height,Weight and Milestone from Add
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile with Note,Height,Weight and Milestone from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		// Getting moment id of last saved moment from backend
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,ids_moments_afterMomentcreation);		
		
		try {
		Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
		
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


		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment  saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight  saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height  saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note  saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone  saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check if Note as saved in Moment,Note to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check if "+milestone+" Milestone as saved in Moment,"+milestone+" Milestone to be displayed on Moments Log Page");

		// Go to vaccine log page
		vaccineLogPage = momentsLogPage.goToVaccineLogPage();
		// Tap on Child Thumbnail image
		vaccineLogPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height  displayed on Vaccine Log Page");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if find weight as saved in Moment,Weight  displayed on Vaccine Log Page");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(testNote),
				"Check if find Note as saved in Moment,Note  displayed on Vaccine Log Page");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(milestone),
				"Check if find Vaccine Day Milestone as saved in Moment,Vaccine Day Milestone  displayed on Vaccine Log Page");

		// Tap back button in vaccine log
		momentsLogPage = vaccineLogPage.tapBackBtn();
		// Go to Growth log page from Moments log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		//growthLogPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if find height as saved in Moment,Height  displayed on Growth Log Page");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if find weight as saved in Moment,Weight  displayed on Growth Log Page");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(testNote),
				"Check if find Note as saved in Moment,Note  displayed on Growth Log Page");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(milestone),
				"Check if find Vaccine Day Milestone as saved in Moment,Vaccine Day Milestone  displayed on Growth Log Page");

		logAssert.assertAll();

	}

		@Test(enabled = true, priority = 3, testName = "GrowthLogPage-Verify edit moment and remove Height or Weight", description = "GrowthLogPage-Verify user edits a moment and removes Height or Weight, moment should not be displayed in growth log,but should be displayed in moments log and vaccineLog(If saved with Vaccine Day milestone)")
	public void verifyRemoveHeightWeight_GrowthLogPageTest() throws Exception {

		
		LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String note = "";
		
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,ids_moments_afterMomentcreation);		
		
		try {
		Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
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

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Weight: " + weight + "Check if weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Height: " + height + " Check if height saved in backend");
		logAssert.assertEquals(note, testNote, "Note: " + testNote + " Check if note saved in backend");
		logAssert.assertNotNull(milestoneId, "Milestone: " + milestone + " Check if milestone in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height: " + height + " to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight: " + weight + " to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check if Note as saved in Moment,Note: " + testNote + " to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check if  Milestone as saved in Moment, Milestone: " + milestone
						+ " to be displayed on Moments Log Page");

		// Go to Growth Log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
	//	growthLogPage.tapChildBtn(childPreferredName);
		
		//Tapping on Moment in Moments Log page by tapping on note
		growthLogPage.tapOnElementByText(testNote);
		//Tapping on Edit moment
		growthLogPage.tapEditMomentBtn();
		EditMomentPage editMomentPage = new EditMomentPage(getDriver(), getPlatformName());
		// Tapping on Child tab
		editMomentPage.tapChildBtn(childPreferredName);
		// Remove text from height field
		editMomentPage.removeTextFromField(height);
		// Remove text from weight field
		editMomentPage.removeTextFromField(weight);
		//Tap save moment button
		editMomentPage.tapSaveMoment();

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
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

		logAssert.assertNotNull(milestoneId, "Check if Milestone: " + milestone + "saved in backend");
		try {	
			logAssert.assertNull(babyWeight, "Check if Weight: " + weight + " is removed in backend");
				}
		catch(NullPointerException e)
		{
			ExtentReportManager.getTest().log(Status.PASS, "Check if Weight is removed in backend");

		}
		
		try {	
			logAssert.assertNull(babyHeight, "Check if Height: " + height + " is removed in backend");
				}
		catch(NullPointerException e)
		{
			ExtentReportManager.getTest().log(Status.PASS, "Check if Height is removed in backend");

		}
		
		
		logAssert.assertEquals(note, testNote, "Check if Note: " + testNote + "saved in backend");

		// Check if moment displayed on Growth Log page when Height, weight removed
		logAssert.assertFalse(growthLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if Height: " + height
						+ " not to be displayed on Growth Log Page after moment is edited-Height to be removed");
		logAssert.assertFalse(growthLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if Weight: ,Weight " + weight
						+ "not to be displayed on Growth Log Page after moment is edited-Weight to be removed");
		logAssert.assertFalse(growthLogPage.isElementDisplayed(testNote), "Check if find Note as saved in Moment,Note: "
				+ testNote + " to be displayed on Growth Log Page after moment is edited-Note not removed");

		momentsLogPage = growthLogPage.tapBackBtn();

		// Check if moment displayed on Moments Log page when Height, weight removed

		logAssert.assertFalse(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if  height as saved in Moment,Height: " + height
						+ " to be displayed on Moments Log Page after moment is edited");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if  weight as saved in Moment,Weight:  " + weight
						+ " to be displayed on Moments Log Page after moment is edited");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote), "Check if  Note as saved in Moment,Note: "
				+ testNote + " to be displayed on Moments Log Page after moment is edited");

		vaccineLogPage = momentsLogPage.goToVaccineLogPage();

		// Check if moment displayed on vaccine Log page when Height, weight removed

		logAssert.assertFalse(vaccineLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height :" + height
						+ "  to be displayed on Growth Log Page after moment is edited");
		logAssert.assertFalse(vaccineLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight: " + weight
						+ "  to be displayed on Growth Log Page after moment is edited");
		logAssert.assertTrue(vaccineLogPage.isElementDisplayed(testNote), "Check if find Note as saved in Moment,Note: "
				+ testNote + " to be displayed on Growth Log Page after moment is edited");

		logAssert.assertAll();

	}

	
}
