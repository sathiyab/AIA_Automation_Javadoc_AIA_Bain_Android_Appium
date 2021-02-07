package com.pom.crimson.testcases.momentlog.smoke;

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
 * This test class contains test cases for Growth log page.<br>
 * Growth log page can be used view moments of Child profiles saved with height
 * or weight. <br>
 * User can navigate to this page by going to: <br>
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget
 * <br>
 * Click <b>{@value com.pom.crimson.util.Constants#VIEW_GROWTH_LOG_LINK}</b>
 * link <br>
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
	String childPreferredName = "";

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
		groupAdviceAPI = new GroupAdviceAPI();
	}

	@Test(enabled = true, priority = 1, testName = "Delete all moments-for data clean up", description = "Delete all moments-for data clean up")
	public void deleteAllData_GrowthLogPageTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}

	@Test(enabled = true, priority = 2, testName = "GrowthLogPage-Verify View Growth log link is displayed", description = "GrowthLogPage-Verify View Growth log link is displayed on Moments Log page ")
	public void verifyGrowthLogLinkIsDisplayed_GrowthLogTest() throws Exception {
		LogAssert logAssert = new LogAssert();
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

			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);

			Thread.sleep(2000);
			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		}

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTSLOG_GROWTH_LOG_LINK_DESCRIPTION),
				" Check if title: " + Constants.MOMENTSLOG_GROWTH_LOG_LINK_DESCRIPTION + " is displayed");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.VIEW_GROWTH_LOG_LINK),
				"Check if " + Constants.VIEW_GROWTH_LOG_LINK + " link  displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "GrowthLogPage-Verify click on View Growth Log Link", description = "GrowthLogPage-Verify user can click on View growth log link  ")
	public void verifyNavigationToGrowthLogPage_GrowthLogTest() throws Exception {

		LogAssert logAssert = new LogAssert();
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

			// Create First Moment
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);

			Thread.sleep(2000);
			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial,
					"Check if Check if Moment saved in backend");
		}
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		logAssert.assertTrue(growthLogPage.isElementDisplayed(Constants.GROWTH_LOG_TITLE),
				"Check if " + Constants.GROWTH_LOG_TITLE + " title displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "GrowthLogPage-Verify Add a moment section in growth log is displayed", description = "GrowthLogPage-Verify user is able to view Add a moment section in growth log")
	public void verifyAddMomentSectionIsDisplayed_GrowthLogTest() throws Exception {

		LogAssert logAssert = new LogAssert();

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

			Thread.sleep(2000);
			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		}
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		// Scroll to bottom of page
		growthLogPage.scrollToBottomOfPage();
		logAssert.assertTrue(growthLogPage.verifyAddNewMomentDescription(),
				" Check if Add new moment section description: " + Constants.GROWTH_LOG_ADDNEWMOMENT_DESCRIPTION
						+ " is displayed");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(Constants.GROWTH_LOG_ADDNEWMOMENT_LINK),
				" Check if Add new moment link  displayed");
		// Record precious moments and milestones throughout your family’s wellness
		// journey
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "GrowthLogPage-Verify navigation to New Moment screen by clicking on Add a moment ", description = "GrowthLogPage-Verify that if user is able to navigate to  New moment screen from growth log")
	public void verifyNavgationToAddNewMomentScreenFromGrowthLog_GrowthLogTest() throws Exception {

		LogAssert logAssert = new LogAssert();

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

			Thread.sleep(2000);
			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		}
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		// Tap on Add new moment button
		addNewMomentsPage = growthLogPage.tapAddNewMomentBtn();

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.ADDNEWMOMENT_TITLE),
				"Check if Add new moment link  displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "GrowthLogPage-Verify moment with Image+Heigh+Weight+Milestone( Vaccine day)+Note displayed in grwoth log", description = "GrowthLogPage-Verify moment with Image+Heigh+Weight+Milestone( Vaccine day)+Note displayed in growth log")
	public void verifyMomentCreationWithImageMilestoneNoteHeightWeight_GrowthLogPageTest() throws Exception {

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

			// Create Moment for First Child Profile from Add
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
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
		logAssert.assertEquals(babyWeight, weight, "Check if Weight  saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height  saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note  saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image  saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone  saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check if Note as saved in Moment,Note displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(milestone),
				"Check if milestone" + milestone + " as saved in Moment,milestone displayed on Moments Log Page");

		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		// Tap child thumbnail image
		growthLogPage.tapChildBtn(childPreferredName);
		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height  displayed on Growth Log Page");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight  displayed on Growth Log Page");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(testNote),
				"Check if Note as saved in Moment,Note  displayed on Growth Log Page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 7, testName = "GrowthLogPage-Verify tap on back button on growth log page", description = "GrowthLogPage-Verify if user can tap on back button on growth log page")
	public void verifyBackButton_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

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
			// Create Moment for First Child Profile
			// from Add First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height, weight,
					childPreferredName);

			Thread.sleep(2000);
			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

			logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		}
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		logAssert.assertTrue(growthLogPage.isElementDisplayed(Constants.GROWTH_LOG_TITLE),
				"Check if " + Constants.GROWTH_LOG_TITLE + " Title is  visible");
		momentsLogPage = growthLogPage.tapBackBtn();
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE + " Title is  visible");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 8, testName = "GrowthLogPage-Verify user can edit moment without image in growth log", description = "GrowthLogPage-Verify user can edit moment without image in growth log")
	public void verifyEditMomentCardWithoutImage_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
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
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
			Thread.sleep(1000);
		}
		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);

		// Tapping on Moment in Growth Log page by tapping on note
		growthLogPage.tapOnElementByText(testNote);
		// Tap Edit moment
		editMomentPage = growthLogPage.tapEditMomentBtn();
		logAssert.assertTrue(editMomentPage.isElementDisplayed(Constants.EDITMOMENT_TITLE),
				"Check if " + Constants.EDITMOMENT_TITLE + " title  displayed on Edit moment Page");
		logAssert.assertTrue(editMomentPage.isElementDisplayed(Constants.EDITMOMENT_SAVEMOMENT),
				"Check if " + Constants.EDITMOMENT_SAVEMOMENT + " button  displayed on Edit moment Page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 9, testName = "GrowthLogPage-Verify share moment without image in other apps in growth log", description = "GrowthLogPage-Verify user is able to share a moment in growth log externally in other apps")
	public void verifyMomentShareExternalAppsWithoutImage_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

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
			// Create Moment for First Child
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile with Height , Weight,Note, Milestone
			// from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment  saved in backend");
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		// Tap on moment in growth log page by tapping on note
		growthLogPage.tapOnElementByText(testNote);
		// Tap share in other apps
		growthLogPage.tapShareInOtherAppsBtn();
		logAssert.assertTrue(growthLogPage.isElementDisplayed(Constants.APP_MESSAGES),
				"Check if " + Constants.APP_MESSAGES + " app is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 10, testName = "GrowthLogPage-Verify share moment without image in group chat in growth log", description = "GrowthLogPage-Verify user is able to share a moment in growth log in group chat")
	public void verifyMomentWithoutImageShareInGroupChat_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetching groups user is part of
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
			//login
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// Fetching groups user is part of
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

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// Create Moment for First Child Profile with Height , Weight,Note, Milestone
			// from Add
			// First Moment Screen
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile with Height , Weight,Note, Milestone
			// from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteMilestoneHeightWeight(testNote, milestone, height,
					weight, childPreferredName);
			Thread.sleep(1000);
		}

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment  saved in backend");
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		// Tap on moment on Growth log page by tapping on note
		growthLogPage.tapOnElementByText(testNote);
		// Tap share in group chat
		growthLogPage.tapShareInGroupChatBtn();

		Thread.sleep(2000);

		for (int i = 0; i < Groups_User.size(); i++) {
			// Check if groups fetched from backend for user are displayed on front end
			logAssert.assertTrue(growthLogPage.isGroupDisplayed(Groups_User.get(i)),
					"Check if " + Groups_User.get(i) + " group  displayed");

		}
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 10, testName = "GrowthLogPage-Verify user can delete moment without image in vaccine log", description = "GrowthLogPage-Verify user can delete moment without image in vaccine log")
	public void verifyDeleteMomentCardWithoutImage_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
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
			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteHeightWeight(testNote, height, weight,
					childPreferredName);
		} else {
			// Create Moment for First Child Profile from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithNoteHeightWeight(testNote, height, weight,
					childPreferredName);

			Thread.sleep(1000);
		}

		// no. of moments in backend after moment is created from frontend
		noOfMoments_afterMomentcreation = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		logAssert.assertNotEquals(noOfMoments_afterMomentcreation, noOfMomentsInitial,
				"Check if Moment saved in backend");
		// Go to Growth Log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		// growthLogPage.tapChildBtn(childPreferredName);
		// Tapping on moment in Growth log page by note
		growthLogPage.tapOnElementByText(testNote);

		// Tap on Edit moment button
		editMomentPage = growthLogPage.tapEditMomentBtn();
		// Tap Delete moment
		editMomentPage.tapDeleteMomentBtn();

		Thread.sleep(3000);
		// Get saved moments for profile from api after moment deletion
		noOfMoments_afterMomentDeletion = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentDeletion = genericFunctionsAPI.getIDsOfSavedMoment();

		logAssert.assertEquals(noOfMoments_afterMomentDeletion, noOfMomentsInitial,
				"Check if Moment deleted in backend");

		logAssert.assertFalse(ids_moments_afterMomentDeletion.contains(idCreatedMoment),
				"Check if Moment  deleted at backend ");
		logAssert.assertFalse(growthLogPage.isElementDisplayed(testNote),
				"Check if Moment deleted from Growth log page-Check for Note displayed");

		momentsLogPage = growthLogPage.tapBackBtn();
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(testNote),
				"Check if Moment  deleted from Moments log page-Check for Note displayed");

		logAssert.assertAll();

	}

	// Full Page Image View Test cases

	@Test(enabled = true, priority = 11, testName = "GrowthLogPage-Verify tap on growth card and view Full page image", description = "GrowthLogPage-Verify tap on growth card and view Full page image, by creating moment with image,height,weight,note,milestone first and than tapping on same")
	public void verifyFullPageImageView_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial = 0;
		int noOfMomentsFinal = 0;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String imageUrl = "";
		String note = "";
		String idCreatedMoment = "";

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
			// Create Moment for First Child Profile from Moments
			// Log Screen
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestoneHeightWeight(1, testNote, milestone,
					height, weight, childPreferredName);
			Thread.sleep(1000);
		}

		try {
			Thread.sleep(2000);
			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
			// Getting moment id of last saved moment from backend
			idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
					ids_moments_afterMomentcreation);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.ERROR,
					"Exception while fetching moments response from backend" + e.getMessage());
		}

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	         // Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend for last moment saved

			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			milestoneId = genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, idCreatedMoment);
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.ERROR,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(imageUrl, " Check if Image saved in backend ");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		// Go to Growth Log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);
		//Tap on moment on Growth log page by note
		growthLogPage.tapOnElementByText(testNote);
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

	@Test(enabled = true, priority = 12, testName = "GrowthLogPage-Verify tap on More action button on Full Page Image view", description = "GrowthLogPage-Verify tap on More action button on Full Page Image view and user able to view options: Edit Moment,\n"
			+ "Share in group chat,\n" + "Share in other apps\n" + "")
	public void verifyMoreButtonFullPageImageView_GrowthLogPageTest() throws Exception {

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
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend for last moment saved
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

		// Go to Growth Log page and tap on Moment created
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);
		//Tap on moment on growth log page by note
		growthLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());
		// Tap on ... button at top left corner of full page view
		fullImageViewPage.tapThreeDots_MoreBtn();

		// Check if links displayed on tapping More button
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.EDITMOMENT_TITLE), "Check if "
				+ Constants.EDITMOMENT_TITLE + " link is displayed on tapping More button on Full Image View page ");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.SHARE_IN_GROUPCHAT), "Check if "
				+ Constants.SHARE_IN_GROUPCHAT + " link is displayed o tapping More button on Full Image View page ");
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed(Constants.SHARE_IN_OTHERAPPS), "Check if "
				+ Constants.SHARE_IN_OTHERAPPS + " link is displayed o tapping More button on Full Image View page ");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 13, testName = "GrowthLogPage-Verify cross button button on Full Page Image view", description = "GrowthLogPage-Verify Cross button on Full page image view")

	public void verifyCrossBtnFullImageView_GrowthLogPageTest() throws Exception {

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
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	          // Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend for last moment saved

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

		// Go to Growth Log page and tap on Moment created
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);
		// Tap on moment on growth log page by tapping on note
		growthLogPage.tapOnElementByText(testNote);

		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Cross Btn on Full Image View Page
		fullImageViewPage.tapCrossBtn();

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.GROWTH_LOG_TITLE),
				"Check if" + Constants.GROWTH_LOG_TITLE + "Title displayed, Growth log page is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 14, testName = "GrowthLogPage-Verify edit moment by tapping on More action button ", description = "GrowthLogPage-Verify edit moment by tapping on More action button, by first creating moment with image and than editing note for same ")
	public void verifyEditMomentFullImageView_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String babyHeight = "";
		String milestoneId = "";
		String newImageURLBackend = "", imageUrl = "";
		String newNoteBackend = "", note = "";
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

		Thread.sleep(2000);
		// Checking final no. of moments in backend
		noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	          // Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend for last moment saved

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
		// Go to Growth Log page 
		growthLogPage = momentsLogPage.goToGrowthLogPage();
		// growthLogPage.tapChildBtn(childPreferredName);
		// Tap on moment created by tapping on note
		growthLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Edit moment button
		editMomentPage = fullImageViewPage.tapEditMomentBtn();
		// Append text to existing note in moment
		editMomentPage.appendNote(testNote, "1234");
		// Tap save moment button
		editMomentPage.tapSaveMoment();
		String newNote = testNote + "1234";

		Thread.sleep(2000);
		try {
			MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	          // Retrieving note, imageurl saved in backend for last moment saved
			newNoteBackend = genericFunctionsAPI.getNoteByMomentId(MomentsList, idCreatedMoment);
			newImageURLBackend = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertEquals(newNoteBackend, newNote, "Check if Note saved in backend");
		logAssert.assertNotNull(newImageURLBackend, " Check if Image saved in backend  ");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(Constants.GROWTH_LOG_TITLE),
				"Check if" + Constants.GROWTH_LOG_TITLE + "Title to be displayed,Growth log page displayed");
		logAssert.assertTrue(growthLogPage.isElementDisplayed(newNote), "Check if"
				+ "New moment  saved on Front end as Note: " + newNote + " to be displayed on Moments Log page");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 15, testName = "GrowthLogPage-Verify share moment to groups by tapping on three dots(More) action button on Full Image View page ", description = "GrowthLogPage-Verify share moment to groups by tapping on three dots(More) action button on Full Image View page, by first creating moment with image and than sharing same ")
	public void verifyShareInGroupChatFullImageView_GrowthLogPageTest() throws Exception {

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
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
	         // Retrieving note,milestoneID,imageurl,babyHeight,babyWeight saved in backend for last moment saved
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

		// Go to Growth Log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);
		// Tap on moment in growth log page by tapping on note
		growthLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap on Share in group chat button
		fullImageViewPage.tapShareInGroupChatBtn();

		Thread.sleep(2000);

		for (int i = 0; i < Groups_User.size(); i++) {
			// Check if groups fetched for user from backend are displayed on frontend
			logAssert.assertTrue(fullImageViewPage.isGroupDisplayed(Groups_User.get(i)),
					"Check if " + Groups_User.get(i) + " group is displayed");

		}

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 16, testName = "GrowthLogPage-Verify share moment to external apps by tapping on three dots(More) action button on Full Image View page ", description = "GrowthLogPage-Verify share moment to external apps by tapping on three dots(More) action button on Full Image View page, by first creating moment with image and than sharing same ")
	public void verifyShareInExternalAppsFullImageView_GrowthLogPageTest() throws Exception {

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
		List<String> ids_moments_afterMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		// Getting moment id of last saved moment from backend
		String idCreatedMoment = genericFunctionsAPI.getMomentIdLastSavedMoment(ids_moments_beforeMomentcreation,
				ids_moments_afterMomentcreation);

		try {
			Response MomentsList = genericFunctionsAPI.getResponseSavedMoments();
			// Retrieving note,milestoneID,imagurl babyHeight,babyWeight saved in backend for last
			// moment saved

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

		// Go to Growth Log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);

		// Tapping on Moment in Growth Log page by tapping on note
		growthLogPage.tapOnElementByText(testNote);
		fullImageViewPage = new FullImageViewPage(getDriver(), getPlatformName());

		// Tap Share in Other apps
		fullImageViewPage.tapShareInOtherAppsBtn();
		// Check if "Messages" app is displayed
		logAssert.assertTrue(fullImageViewPage.isElementDisplayed("Messages"), "Check if Messages app displayed");
		logAssert.assertAll();
	}

}
