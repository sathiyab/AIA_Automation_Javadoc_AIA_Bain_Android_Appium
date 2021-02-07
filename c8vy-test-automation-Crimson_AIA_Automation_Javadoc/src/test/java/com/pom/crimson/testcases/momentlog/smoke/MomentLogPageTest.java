package com.pom.crimson.testcases.momentlog.smoke;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import com.pom.crimson.pages.EditMomentPage;
import com.pom.crimson.pages.FullImageViewPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.VaccineLogPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.MobileBy;
import io.restassured.response.Response;

/**
 * This test class contains test cases for Moments log page.<br>
 * Moments log page can be used view all moments. <br>
 * User can navigate to this page by going to: <br>
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget
 * <br>
 * <br>
 * <br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class MomentLogPageTest extends BaseFixture {

	HomePage homePage;
	MomentsLogPage momentsLogPage;
	VaccineLogPage vaccineLogPage;
	AddNewMomentsPage addNewMomentsPage;
	EditMomentPage editMomentPage;
	FullImageViewPage fullImageViewPage;
	LoginPage loginPage;
	String milestone = Constants.MILESTONE_VACCINEDAY;
	GroupAdviceAPI groupAdviceAPI;
	String childPreferredName;
	GenericFunctionsAPI genericFunctionsAPI;

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
	public void deleteAllData_MomentPageLogTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}

	@Test(enabled = true, priority = 2, testName = "MomentsLogPage-Verify user can share moment without image in group chat in Moments log", description = "MomentsLogPage-Verify that if user is able to share a moment in Moments log in group chat")
	public void verifyMomentWithoutImageShareInGroupChat_MomentLogPageTest() throws Exception {

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
			// Create First Moment for Myself
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment for Myself
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);

			Thread.sleep(1000);
		}
		// Tap on moment on Moments log page by tapping on note
		momentsLogPage.tapOnElementByText(testNote);
		// Tap Share in Group chat button
		momentsLogPage.tapShareInGroupChatBtn();

		Thread.sleep(2000);

		for (int i = 0; i < Groups_User.size(); i++) {
			// Check if groups fetched for user from backend are displayed on front end
			logAssert.assertTrue(momentsLogPage.isGroupDisplayed(Groups_User.get(i)),
					"Check if " + Groups_User.get(i) + " group is displayed");

		}
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "MomentsLogPage-Verify user can share moment without image in other apps in Moments log", description = "MomentsLogPage-Verify that if user is able to share a moment in Moments log externally in other apps")
	public void verifyMomentShareExternalAppsWithoutImage_MomentsLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment

			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);

			Thread.sleep(1000);
		}
		// Tap on moment in moments log by tapping on text
		momentsLogPage.tapOnElementByText(testNote);
		// Tap Share in other apps
		momentsLogPage.tapShareInOtherAppsBtn();
		// Check if "Messages" app is displayed
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.APP_MESSAGES),
				"Check if " + Constants.APP_MESSAGES + " app displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "MomentsLogPage-Verify user able to click on back button on MomentsLog", description = "MomentsLogPage-Verify user able to click on back button on MomentsLog and land on Home Page")
	public void verifyCrossBtn_MomentsLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote, Constants.ADDNEWMOMENT_MYSELF_TAB);
			Thread.sleep(1000);
		}
		// Tap back button
		homePage = momentsLogPage.tapBackBtn();
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.RECOMMENDEDCONTENT),
				"Check if " + Constants.RECOMMENDEDCONTENT + " is displayed-Home page is displayed");
		logAssert.assertAll();

	}

	// Full page Image View Test cases
	@Test(enabled = true, priority = 5, testName = "MomentsLogPage-Verify Full page Image View  by tapping on a moment with image on timeline", description = "MomentsLogPage-Verify Full page Image View of moment, by creating moment with image first and than tapping on same.")
	public void verifyFullPageImageView_MomentsLogPageTest() throws Exception {

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
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);

// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		// Retrieving imageurl,note,milestoneID saved in backend for last moment saved
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
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

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");

		// Tap on Image with Note
		momentsLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		logAssert.assertTrue(fullImageViewPage.verifyFirstIndexCarousalDisplayed(),
				"Check if first Index of carousal is displayed on Full Image View Page");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height to be displayed on Full Image View Page");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight to be displayed on Full Image View  Page");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(testNote),
				"Check if Note as saved in Moment,Note to be displayed on Full Image View  Page");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(milestone), "Check if Milestone: " + milestone
				+ " as saved in Moment," + milestone + " Milestone to be displayed on Full Image View Page");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE
						+ " title is displayed on Full Image View Page-moments log title should not be displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "MomentsLogPage-Verify More button on Full Page Image View", description = "MomentsLogPage-Verify tap on More action button on Full Page Image view and user able to view options: Edit Moment,\n"
			+ "Share in group chat,\n" + "Share in other apps\n" + "")
	public void verifyMoreButtonFullPageImageView_MomentLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
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
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote,
					Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote,
					Constants.ADDNEWMOMENT_MYSELF_TAB);
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
			// Retrieving note,imageurl saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend");

		// Tap on Image with Note
		momentsLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());
		// Tap on ... button on top left corner of page
		fullImageViewPage.tapThreeDots_MoreBtn();

		// Check links displayed on tapping More button
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.EDITMOMENT_TITLE), "Check if "
				+ Constants.EDITMOMENT_TITLE + " link is displayed on tapping More button on Full Image View page ");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.SHARE_IN_GROUPCHAT), "Check if "
				+ Constants.SHARE_IN_GROUPCHAT + " link is displayed on tapping More button on Full Image View page ");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.SHARE_IN_OTHERAPPS), "Check if "
				+ Constants.SHARE_IN_OTHERAPPS + " link is displayed on tapping More button on Full Image View page ");

		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 7, testName = "MomentsLogPage-Verify if user is able to tap on Cross button on full page Image Viewer and land on Moments log page", description = "MomentsLogPage-Verify if user is able to tap on Cross button on full page Image Viewer and land on Moments log page")
	public void verifyCrossBtnFullImageView_MomentsLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String note = "";
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
		} else {
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
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
			// Retrieving note,imageurl saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend ");

		// Tap on Image with Note
		momentsLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Cross Btn on Full Image View Page
		fullImageViewPage.tapCrossBtn();

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE + "title displayed,Moments log page should be displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 8, testName = "MomentsLogPage-Verify if user is able to edit moment by tapping on More action button ", description = "MomentsLogPage-Verify if user is able to edit moment by tapping on More action button, by first creating moment with image and than editing note for same ")
	public void verifyEditMomentFullImageView_MomentsLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String note = "";
		String newNotebackEnd = "";
		String newImageURLbackend = "";
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		Response MomentsList;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
		} else {
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
		}

		Thread.sleep(2000);

// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,imageurl saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend ");

		// Tap on Image with Note
		momentsLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Edit moment button
		editMomentPage = fullImageViewPage.tapEditMomentBtn();
		// Appned text to existing note in moment
		editMomentPage.appendNote(testNote, "1234");
		// Tap Save moment button
		editMomentPage.tapSaveMoment();
		String newNote = testNote + "1234";

		MomentsList = genericFunctionsAPI.getResponseSavedMoments();
		// Retrieving note,imageurl saved in backend for last moment saved
		newNotebackEnd = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
		newImageURLbackend = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);

		logAssert.assertEquals(newNotebackEnd, newNote, "Check if Note saved in backend");
		logAssert.assertNotNull(newImageURLbackend, "Check if Image saved in backend ");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE + " title is displayed,Moments log page to be displayed");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(newNote),
				"Check if New moment  saved on Front end as Note: " + newNote + "to be displayed on Moments Log page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 9, testName = "MomentsLogPage-Verify if user is able to share moment to groups by tapping on three dots(More) action button on Full Image View page ", description = "MomentsLogPage-Verify if user is able to share moment to groups by tapping on three dots(More) action button on Full Image View page, by first creating moment with image and than sharing same ")
	public void verifyShareInGroupChatFullImageView_MomentsLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetch Groups that user is part of
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
		// Fetch Groups that user is part of
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String note = "";
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
		} else {
			// Create moment
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
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
			// Retrieving note,imageurl saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend ");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check if New moment is saved on Front end as Note: " + testNote
						+ "to be displayed on Moments Log Page");

		// Tap on Image with Note
		momentsLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());
		// Tap Share in group chat button
		fullImageViewPage.tapShareInGroupChatBtn();

		Thread.sleep(2000);

		for (int i = 0; i < Groups_User.size(); i++) {
			// Check if groups fetched for user from backend are displayed in frontend
			logAssert.assertTrue(momentsLogPage.isGroupDisplayed(Groups_User.get(i)),
					"Check if " + Groups_User.get(i) + " group is displayed");

		}

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 10, testName = "MomentsLogPage-Verify if user is able to share moment to external apps by tapping on three dots(More) action button on Full Image View page ", description = "MomentsLogPage-Verify if user is able to share moment to external apps by tapping on three dots(More) action button on Full Image View page, by first creating moment with image and than sharing same ")
	public void verifyShareInExternalAppsFullImageView_MomentsLogPageTest() throws Exception {

		GenericFunctionsAPI genericFunctionsAPI = new GenericFunctionsAPI();
		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;

		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException("Please add User in a group before running this test case");
		}

		String testNote = GenericFunctions.generateRandomAlphanumericString();
		String note = "";
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
		} else {
			// Create moment
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNote(1, testNote, "Myself");
		}

		Thread.sleep(2000);

// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,imageurl saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image saved in backend ");

		// Tap on Image with Note
		momentsLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap share in other apps
		fullImageViewPage.tapShareInOtherAppsBtn();
		logAssert.assertTrue(momentsLogPage.isElementDisplayed("Messages"), "Check if Messages app is displayed");
		logAssert.assertAll();
	}

}
