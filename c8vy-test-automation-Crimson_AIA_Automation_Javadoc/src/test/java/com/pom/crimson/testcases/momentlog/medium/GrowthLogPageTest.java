package com.pom.crimson.testcases.momentlog.medium;

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

	@Test(enabled = true, priority = 1, testName = "GrowthLogPage-Verify growth card displayed when users creates moment with Image,Height", description = "GrowthLogPage-Verify growth card displayed when users creates moment with Image,Height")
	public void verifyMomentCreationWithImageHeight_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyHeight = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageHeight(1, height, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageHeight(1, height, childPreferredName);
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
			// Retrieving image url,babyHeight saved in backend for last moment saved
			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertNotNull(imageUrl, " Check if Image saved in backend  ");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if  height as saved in Moment,Height displayed on Moments Log Page");

		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height displayed on Growth Log Page");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 2, testName = "GrowthLogPage-Verify growth card displayed when users creates moment with Image,Weight", description = "GrowthLogPage-Verify growth card displayed when users creates moment with Image,Weight")
	public void verifyMomentCreationWithImageWeight_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		String imageUrl = "";
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageWeight(1, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageWeight(1, weight, childPreferredName);
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
	          // Retrieving imageurl,note saved in backend for last moment saved

			imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertNotNull(imageUrl, " Check if Image saved in backend  ");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight displayed on Moments Log Page");

		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight displayed on Growth Log Page");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "GrowthLogPage-Verify growth card displayed when users creates moment with Weight", description = "GrowthLogPage-Verify growth card displayed when users creates moment with Weight")
	public void verifyMomentCreationWithWeight_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithWeight(weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithWeight(weight, childPreferredName);
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
			// Retrieving babyWeight saved in backend for last moment saved
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if  weight as saved in Moment,Weight is displayed on Moments Log Page");

		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight is displayed on Growth Log Page");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "GrowthLogPage-Verify growth card displayed when users creates moment with Height", description = "GrowthLogPage-Verify growth card displayed when users creates moment with Height")
	public void verifyMomentCreationWithHeight_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyHeight = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithHeight(height, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithHeight(height, childPreferredName);
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
			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height is displayed on Moments Log Page");

		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height is displayed on Growth Log Page");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "GrowthLogPage-Verify growth card displayed when users creates moment with Height and Weight", description = "GrowthLogPage-Verify growth card displayed when users creates moment with Height")
	public void verifyMomentCreationWithHeightWeight_GrowthLogPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		String height = GenericFunctions.generateRandomDoubleInStringFormat();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyHeight = "";
		String babyWeight = "";

		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> ids_moments_beforeMomentcreation = genericFunctionsAPI.getIDsOfSavedMoment();
		

		// Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// Create First Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithHeightWeight(height, weight, childPreferredName);
		} else {
			// Create Moment for First Child Profile
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithHeightWeight(height, weight, childPreferredName);
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
	        // Retrieving babyHeight,babyWeight saved in backend for last moment saved

			babyHeight = genericFunctionsAPI.getHeightByMomentId(MomentsList, idCreatedMoment);
			babyWeight = genericFunctionsAPI.getWeightByMomentId(MomentsList, idCreatedMoment);
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO,
					"Exception while fetching moments response from backend" + e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment saved in backend");
		logAssert.assertEquals(babyHeight, height, "Check if Height saved in backend");
		logAssert.assertEquals(babyWeight, weight, "Check if Weight saved in backend");

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if height as saved in Moment,Height is displayed on Moments Log Page");

		logAssert.assertTrue(momentsLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if weight as saved in Moment,Weight is displayed on Moments Log Page");

		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();
//		growthLogPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + height + " cm"),
				"Check if  height as saved in Moment,Height is displayed on Growth Log Page");

		logAssert.assertTrue(growthLogPage.isElementDisplayed(" " + weight + " kg"),
				"Check if  weight as saved in Moment,Weight is displayed on Growth Log Page");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "GrowthLogPage-Verify that Grandparent profile is not present on Growth Log Page", description = "GrowthLogPage-Verify that Grandparent profile is not present on Growth Log Page")
	public void verifyGrandParentProfileNotPresent_GrowthLogPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String milestoneId = "";

		// Create GrandParent profile
		int profiles = genericFunctionsAPI.getNoOfGrandparentProfiles();
		if (profiles == 0) {
			genericFunctionsAPI.createGrandparentProfile();
		}
		//Get Preferred name of Grandparent
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
		// Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();

		logAssert.assertFalse(growthLogPage.isElementDisplayed(preferredNameGrandParent),
				"Check If " + preferredNameGrandParent + " Grandparent name is not displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "GrowthLogPage-Verify that parent profile is not present on Growth Log Page", description = "VaccineLogPage-Verify that parent profile is not present on Growth Log Page")
	public void verifyParentProfileNotPresent_GrowthLogPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		String weight = GenericFunctions.generateRandomDoubleInStringFormat();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String babyWeight = "";
		String milestoneId = "";

		// Create Parent profile
		int profiles = genericFunctionsAPI.getNoOfParentProfiles();
		if (profiles == 0) {
			genericFunctionsAPI.createParentProfile();
		}
		//Get Preferred name of parent
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
		
		//Go to Growth log page
		growthLogPage = momentsLogPage.goToGrowthLogPage();

		logAssert.assertFalse(growthLogPage.isElementDisplayed(preferredNameParent),
				"Check If " + preferredNameParent + " Parent name is not displayed");

		logAssert.assertAll();
	}

}
