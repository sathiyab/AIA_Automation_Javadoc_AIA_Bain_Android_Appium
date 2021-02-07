package com.pom.crimson.testcases.momentlog.medium;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddNewMomentsPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.util.Constants;

import io.restassured.response.Response;
/**
 * This test class contains test cases for milestones displayed on Add New moment page.<br>
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
 * Milestones displayed in Child tab are displayed as per Child's Date of Birth.
 * Milestones displayed in Myself tab are displayed for parent to be Archetype and as per due date.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class AddNewMomentPageMilestoneTabTest extends BaseFixture {

	HomePage homePage;
	MomentsLogPage momentsLogPage;
	AddNewMomentsPage addNewMomentsPage;
	GenericFunctionsAPI genericFunctionsAPI;
	String childPreferredName;
	LoginPage loginPage;

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

	}

	@Test(enabled = true, priority = 1, testName = "AddNewMomentPage-Verify milestones on New Moment Page for child 0-3 months", description = "AddNewMomentPage-Verify that following milestones are displayed on Add New Moment page for child with Journey stage(0-3 Months):\n"
			+ "Baby is born\n" + "Bringing baby home\n" + "Bath time\n" + "Tummy time\n"
			+ "Signature sleeping position\n" + "Grasping objects\n" + "Clenching fists\n" + "First breastfeeding\n"
			+ "Family time\n" + "Fun with toys\n" + "Visit to the doctor\n" + "Smiling baby")
	public void verifyChildAgeCard_0To3Months_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Update DOB of First Child
		int ageInMonths = 2;
		String DOB = GenericFunctions.generatePastDate(0, ageInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		// Go to Moments log page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			  // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}

		// Tapping on Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_0_3_MONTHS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_0_3_MONTHS + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BABY_IS_BORN),
				"Check if milestone " + Constants.MILESTONE_CHILD_BABY_IS_BORN + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BRINGING_BABY_HOME),
				"Check if milestone " + Constants.MILESTONE_CHILD_BRINGING_BABY_HOME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BATH_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_BATH_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_TUMMY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_TUMMY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SIGNATURE_SLEEP_POS),
				"Check if milestone " + Constants.MILESTONE_CHILD_SIGNATURE_SLEEP_POS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_GRASPING_OBJECTS),
				"Check if milestone " + Constants.MILESTONE_CHILD_GRASPING_OBJECTS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_CLENCHING_FISTS),
				"Check if milestone " + Constants.MILESTONE_CHILD_CLENCHING_FISTS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FIRST_BREASTFEEDING),
				"Check if milestone " + Constants.MILESTONE_CHILD_FIRST_BREASTFEEDING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FAMILY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_FAMILY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_TOYS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_TOYS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_VISIT_DOCTOR),
				"Check if milestone " + Constants.MILESTONE_CHILD_VISIT_DOCTOR + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SMILING_BABY),
				"Check if milestone " + Constants.MILESTONE_CHILD_SMILING_BABY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 2, testName = "AddNewMomentPage-Verify milestones on New Moment Page for child 3-6 months", description = "AddNewMomentPage-Verify that following milestones are displayed on Add New Moment page for child with Journey stage(3-6 months):\n"
			+ "First teeth\n" + "Smiling baby\n" + "Lifted up their head\n" + "Babbling time\n" + "Tummy time\n"
			+ "Bottling\n" + "Mealtime\n" + "Fun with toys\n" + "Laughed out loud\n" + "Family time\n"
			+ "Shaking and moving!\n" + "Visit to the doctor")
	public void verifyChildAgeCard_3To6Months_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Update DOB of First Child
		int ageInMonths = 4;
		String DOB = GenericFunctions.generatePastDate(0, ageInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		
        //Tap on Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_3_6_MONTHS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_3_6_MONTHS + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FIRST_TEETH),
				"Check if milestone " + Constants.MILESTONE_CHILD_FIRST_TEETH + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SMILING_BABY),
				"Check if milestone " + Constants.MILESTONE_CHILD_SMILING_BABY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_LIFTED_HEAD),
				"Check if milestone " + Constants.MILESTONE_CHILD_LIFTED_HEAD + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BABBLING_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_BABBLING_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_TUMMY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_TUMMY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BOTTLING),
				"Check if milestone " + Constants.MILESTONE_CHILD_BOTTLING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_MEALTIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_MEALTIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_TOYS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_TOYS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_LAUGHED_OUT_LOUD),
				"Check if milestone " + Constants.MILESTONE_CHILD_LAUGHED_OUT_LOUD + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FAMILY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_FAMILY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SHAKING_MOVING),
				"Check if milestone " + Constants.MILESTONE_CHILD_SHAKING_MOVING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_VISIT_DOCTOR),
				"Check if milestone " + Constants.MILESTONE_CHILD_VISIT_DOCTOR + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "AddNewMomentPage-Verify milestones on New Moment Page for child 6-9 months", description = "AddNewMomentPage-Verify that following milestones are displayed on Add New Moment page for child with Journey stage(6-9 months):\n"
			+ "First teeth\n" + "Dentist time\n" + "Shaking and moving!\n" + "Sat steady \n" + "Brushing teeth\n"
			+ "Mealtime\n" + "First words\n" + "Family time\n" + "Playing games\n" + "Outdoor fun\n"
			+ "Fun in the pool\n" + "Visit to the doctor")
	public void verifyChildAgeCard_6To9Months_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Update DOB of First Child
		int ageInMonths = 7;
		String DOB = GenericFunctions.generatePastDate(7, ageInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		
		//Tapping Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_6_9_MONTHS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_6_9_MONTHS + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FIRST_TEETH),
				"Check if milestone " + Constants.MILESTONE_CHILD_FIRST_TEETH + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_DENTIST_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_DENTIST_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SHAKING_MOVING),
				"Check if milestone " + Constants.MILESTONE_CHILD_SHAKING_MOVING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SAT_STEADY),
				"Check if milestone " + Constants.MILESTONE_CHILD_SAT_STEADY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BRUSHING_TEETH),
				"Check if milestone " + Constants.MILESTONE_CHILD_BRUSHING_TEETH + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_MEALTIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_MEALTIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FIRST_WORDS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FIRST_WORDS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FAMILY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_FAMILY_TIME + " is displayed");
		;
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_PLAYING_GAMES),
				"Check if milestone " + Constants.MILESTONE_CHILD_PLAYING_GAMES + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_OUTDOOR_FUN),
				"Check if milestone " + Constants.MILESTONE_CHILD_OUTDOOR_FUN + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_POOL),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_POOL + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_VISIT_DOCTOR),
				"Check if milestone " + Constants.MILESTONE_CHILD_VISIT_DOCTOR + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "AddNewMomentPage-Verify milestones on New Moment Page for child 9-12 months", description = "AddNewMomentPage-Verify that following milestones are displayed on Add New Moment page for child with Journey stage(9-12 months):\n"
			+ "Dentist time\n" + "Stood up\n" + "First steps\n" + "Mealtime\n" + "Wearing shoes\n" + "Family time\n"
			+ "Outdoor fun\n" + "Shaking and moving!\n" + "Playing games\n" + "Haircut\n" + "Fun in the pool\n"
			+ "Visit to the doctor")
	public void verifyChildAgeCard_9To12Months_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Update DOB of First Child
		int ageInMonths = 10;
		String DOB = GenericFunctions.generatePastDate(0, ageInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//Log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			 // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		//Tap on Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_9_12_MONTHS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_9_12_MONTHS + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_DENTIST_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_DENTIST_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_STOOD_UP),
				"Check if milestone " + Constants.MILESTONE_CHILD_STOOD_UP + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FIRST_STEPS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FIRST_STEPS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_MEALTIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_MEALTIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_WEARING_SHOES),
				"Check if milestone " + Constants.MILESTONE_CHILD_WEARING_SHOES + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FAMILY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_FAMILY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_OUTDOOR_FUN),
				"Check if milestone " + Constants.MILESTONE_CHILD_OUTDOOR_FUN + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SHAKING_MOVING),
				"Check if milestone " + Constants.MILESTONE_CHILD_SHAKING_MOVING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_PLAYING_GAMES),
				"Check if milestone " + Constants.MILESTONE_CHILD_PLAYING_GAMES + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_HAIRCUT),
				"Check if milestone " + Constants.MILESTONE_CHILD_HAIRCUT + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_POOL),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_POOL + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_VISIT_DOCTOR),
				"Check if milestone " + Constants.MILESTONE_CHILD_VISIT_DOCTOR + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "AddNewMomentPage-Verify milestones on New Moment Page for child 1-3 years", description = "AddNewMomentPage-Verify that following milestones are displayed on Add New Moment page for child with Journey stage(1-3 years):\n"
			+ "Dentist time\n" + "Family time\n" + "Birthday party!\n" + "Time to draw\n" + "Mealtime\n"
			+ "Signature dance\n" + "Shaking and moving!\n" + "Fun with friends\n" + "Haircut\n" + "Fun in the pool\n"
			+ "At the daycare\n" + "Visit to the doctor")
	public void verifyChildAgeCard_1To3Years_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Update DOB of First Child
		int ageInYears = 2;
		String DOB = GenericFunctions.generatePastDate(0, 0, ageInYears) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			 // If no. of moments are zero,Tap Add your first moment button 
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			 // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		//Tap Child buuton
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_1_3_YEARS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_1_3_YEARS + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_DENTIST_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_DENTIST_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FAMILY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_FAMILY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BIRTHDAY_PARTY),
				"Check if milestone " + Constants.MILESTONE_CHILD_BIRTHDAY_PARTY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_TIME_DRAW),
				"Check if milestone " + Constants.MILESTONE_CHILD_TIME_DRAW + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_MEALTIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_MEALTIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SIGNATURE_DANCE),
				"Check if milestone " + Constants.MILESTONE_CHILD_SIGNATURE_DANCE + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SHAKING_MOVING),
				"Check if milestone " + Constants.MILESTONE_CHILD_OUTDOOR_FUN + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_FRIENDS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_FRIENDS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_HAIRCUT),
				"Check if milestone " + Constants.MILESTONE_CHILD_HAIRCUT + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_POOL),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_POOL + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_AT_DAYCARE),
				"Check if milestone " + Constants.MILESTONE_CHILD_AT_DAYCARE + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_VISIT_DOCTOR),
				"Check if milestone " + Constants.MILESTONE_CHILD_VISIT_DOCTOR + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "AddNewMomentPage-Verify milestones on New Moment Page for child 3-5 years", description = "AddNewMomentPage-Verify that following milestones are displayed on Add New Moment page for child with Journey stage(3-5 years):\n"
			+ "Birthday party!\n" + "Getting dressed\n" + "Fun with toys\n" + "Outdoor fun\n" + "Mealtime\n"
			+ "At kindergarten\n" + "Playing games\n" + "Shaking and moving!\n" + "Fun with friends\n"
			+ "At the daycare\n" + "Study time")
	public void verifyChildAgeCard_3To5Years_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Update DOB of First Child
		int ageInYears = 4;
		String DOB = GenericFunctions.generatePastDate(0, 0, ageInYears) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		// Go to Moments log page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			  // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		// Tap Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_3_5_YEARS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_3_5_YEARS + " section is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BIRTHDAY_PARTY),
				"Check if milestone " + Constants.MILESTONE_CHILD_BIRTHDAY_PARTY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_GETTING_DRESSED),
				"Check if milestone " + Constants.MILESTONE_CHILD_GETTING_DRESSED + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_TOYS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_TOYS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_OUTDOOR_FUN),
				"Check if milestone " + Constants.MILESTONE_CHILD_OUTDOOR_FUN + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_MEALTIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_MEALTIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_AT_KINDERGARTEN),
				"Check if milestone " + Constants.MILESTONE_CHILD_AT_KINDERGARTEN + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_PLAYING_GAMES),
				"Check if milestone " + Constants.MILESTONE_CHILD_PLAYING_GAMES + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_SHAKING_MOVING),
				"Check if milestone " + Constants.MILESTONE_CHILD_SHAKING_MOVING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_FRIENDS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_FRIENDS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_AT_DAYCARE),
				"Check if milestone " + Constants.MILESTONE_CHILD_AT_DAYCARE + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_STUDY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_STUDY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_STUDY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_STUDY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 7, testName = "AddNewMomentPage-Verify milestones on New Moment Page for child 5+ years", description = "AddNewMomentPage-Verify that following milestones are displayed on Add New Moment page for child with Journey stage Journey stage(5+Years):\n"
			+ "Birthday party!\n" + "Study time\n" + "Getting dressed\n" + "Mealtime\n" + "Playing games\n"
			+ "Family time\n" + "Fun with friends\n" + "At kindergarten\n" + "At school\n" + "At the daycare\n"
			+ "Story time")
	public void verifyChildAgeCard_5PlusYears_AddNewMomentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Update DOB of First Child
		int ageInYears = 6;
		String DOB = GenericFunctions.generatePastDate(0, 0, ageInYears) + "T00:00:00Z";
		genericFunctionsAPI.updateDateofBirth_FirstChild(DOB);

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			 // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		
		//Tap on Child tab
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_5_PLUS_YEARS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_5_PLUS_YEARS + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BIRTHDAY_PARTY),
				"Check if milestone " + Constants.MILESTONE_CHILD_BIRTHDAY_PARTY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_STUDY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_STUDY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_GETTING_DRESSED),
				"Check if milestone " + Constants.MILESTONE_CHILD_GETTING_DRESSED + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_MEALTIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_MEALTIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_PLAYING_GAMES),
				"Check if milestone " + Constants.MILESTONE_CHILD_PLAYING_GAMES + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FAMILY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_FAMILY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_FUN_FRIENDS),
				"Check if milestone " + Constants.MILESTONE_CHILD_FUN_FRIENDS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_AT_KINDERGARTEN),
				"Check if milestone " + Constants.MILESTONE_CHILD_AT_KINDERGARTEN + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_AT_SCHOOL),
				"Check if milestone " + Constants.MILESTONE_CHILD_AT_SCHOOL + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_AT_DAYCARE),
				"Check if milestone " + Constants.MILESTONE_CHILD_AT_DAYCARE + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_STORY_TIME),
				"Check if milestone " + Constants.MILESTONE_CHILD_STORY_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 8, testName = "AddNewMomentPage-Verify pregnancy milestones displayed-1st Trimester", description = "AddNewMomentPage-Verify the pregnancy milestones displayed when user is in 1st Trimester of Pregnancy:\n"
			+ "\n" + "Positive Pregnancy Test\n" + "Sharing The Good News\n" + "Prenatal Visit\n"
			+ "First Ultrasound Photo\n" + "First Morning Sickness\n" + "Pregnancy Craving\n" + "Baby Bump\n"
			+ "Maternity Shopping\n" + "Maternity fitness\n" + "Getting ready for parenting\n"
			+ "Celebrating With our Little One")
	public void verifyPregMilestones_FirstTrem_AddNewMomentPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// Update Due date of Primary Profile
		int dueDateInMonths = 8;
		String dueDateInDaysSt = GenericFunctions.generateFutureDate(0, dueDateInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDueDate(dueDateInDaysSt);

		String archetype = Constants.ARCHETYPE_PARENT_TO_BE;
		String archID = genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting Archetype of primary profile
		String archeTypeUserID = genericFunctionsAPI.getArchetypeIdOfProfile();

		if (archID.equals(null) || archeTypeUserID.equals(null)) {
			throw new SkipException(
					"Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		// Change archetype of user to parents to be
		if (!(archeTypeUserID.equals(archID))) {
			boolean archetypeChanged = genericFunctionsAPI.changeArcheTypeOfUser(archID);
			if (archetypeChanged == false) {
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		// Go to Moments log page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}


		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_FIRST_TRIMESTER),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_FIRST_TRIMESTER + " section is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_POSITIVE_TEST),
				"Check if milestone " + Constants.MILESTONE_PREG_POSITIVE_TEST + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_SHARING_NEWS),
				"Check if milestone " + Constants.MILESTONE_PREG_SHARING_NEWS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check if milestone " + Constants.MILESTONE_PREG_PRENATAL_VISIT + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_FIRST_ULTRASOUND),
				"Check if milestone " + Constants.MILESTONE_PREG_FIRST_ULTRASOUND + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_FIRST_SICKNESS),
				"Check if milestone " + Constants.MILESTONE_PREG_FIRST_SICKNESS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_PREGNANCY_CRAVING),
				"Check if milestone " + Constants.MILESTONE_PREG_PREGNANCY_CRAVING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_BABY_BUMP),
				"Check if milestone " + Constants.MILESTONE_PREG_BABY_BUMP + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_MATERNITY_SHOPPING),
				"Check if milestone " + Constants.MILESTONE_PREG_MATERNITY_SHOPPING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_MATERNITY_FITNESS),
				"Check if milestone " + Constants.MILESTONE_PREG_MATERNITY_FITNESS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_GETTING_READY),
				"Check if milestone " + Constants.MILESTONE_PREG_GETTING_READY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_CELEBRATING_ONE),
				"Check if milestone " + Constants.MILESTONE_PREG_CELEBRATING_ONE + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 9, testName = "AddNewMomentPage-Verify pregnancy milestones displayed-2st Trimester", description = "AddNewMomentPage-Verify the pregnancy milestones displayed when user is in 2nd Trimester of Pregnancy:\n"
			+ "\n" + "First Morning Sickness\n" + "Pregnancy Craving\n" + "Listening to Baby's Heart Beat\n"
			+ "First Baby Kick\n" + "Maternity Shopping\n" + "Shopping for the baby\n" + "Gender Reveal\n"
			+ "First Day At Childbirth Class\n" + "Picking our baby's name\n" + "Talking With Little One\n"
			+ "Getting ready for parenting\n" + "Maternity fitness\n" + "Ultrasound photo\n" + "Prenatal Visit\n"
			+ "Baby Bump\n" + "Mum and her friends")
	public void verifyPregMilestones_SecondTrem_AddNewMomentPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// Update Due date of Primary Profile
		int dueDateInMonths = 5;
		String dueDateInDaysSt = GenericFunctions.generateFutureDate(0, dueDateInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDueDate(dueDateInDaysSt);

		String archetype = Constants.ARCHETYPE_PARENT_TO_BE;
		String archID = genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting Archetype of primary profile
		String archeTypeUserID = genericFunctionsAPI.getArchetypeIdOfProfile();

		if (archID.equals(null) || archeTypeUserID.equals(null)) {
			throw new SkipException(
					"Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		// Change archetype of user to parents to be
		if (!(archeTypeUserID.equals(archID))) {
			boolean archetypeChanged = genericFunctionsAPI.changeArcheTypeOfUser(archID);
			if (archetypeChanged == false) {
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		// Go to Moments log page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}


		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_SECOND_TRIMESTER),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_SECOND_TRIMESTER + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_TALKING_ONE),
				"Check if milestone " + Constants.MILESTONE_PREG_TALKING_ONE + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_FIRST_SICKNESS),
				"Check if milestone " + Constants.MILESTONE_PREG_FIRST_SICKNESS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_PREGNANCY_CRAVING),
				"Check if milestone " + Constants.MILESTONE_PREG_PREGNANCY_CRAVING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.verifyListenHeartBeatMilestoneDisplayed(),
				"Check if milestone " + Constants.MILESTONE_PREG_LISTEN_HEARTBEAT + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_FIRST_KICK),
				"Check if milestone " + Constants.MILESTONE_PREG_FIRST_KICK + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_MATERNITY_SHOPPING),
				"Check if milestone " + Constants.MILESTONE_PREG_MATERNITY_SHOPPING + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_SHOPPING_BABY),
				"Check if milestone " + Constants.MILESTONE_PREG_SHOPPING_BABY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_GENDER_REVEAL),
				"Check if milestone " + Constants.MILESTONE_PREG_GENDER_REVEAL + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_FIRSTDAY_CHILDBIRTH),
				"Check if milestone " + Constants.MILESTONE_PREG_FIRSTDAY_CHILDBIRTH + " is displayed");	
		logAssert.assertTrue(addNewMomentsPage.verifyPickingBabyNameMilestoneDisplayed(),
				"Check if milestone " + Constants.MILESTONE_PREG_PICKING_BABYNAME + " is displayed");	
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_GETTING_READY),
				"Check if milestone " + Constants.MILESTONE_PREG_GETTING_READY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_MATERNITY_FITNESS),
				"Check if milestone " + Constants.MILESTONE_PREG_MATERNITY_FITNESS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_ULTRASOUND_PHOTO),
				"Check if milestone " + Constants.MILESTONE_PREG_ULTRASOUND_PHOTO + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check if milestone " + Constants.MILESTONE_PREG_PRENATAL_VISIT + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_BABY_BUMP),
				"Check if milestone " + Constants.MILESTONE_PREG_BABY_BUMP + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_MUM_FRIENDS),
				"Check if milestone " + Constants.MILESTONE_PREG_MUM_FRIENDS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();

	}
	
	@Test(enabled = true, priority = 10, testName = "AddNewMomentPage-Verify pregnancy milestones displayed-3rd Trimester", 
			description = "AddNewMomentPage-Verify the pregnancy milestones displayed when user is in 3rd Trimester of Pregnancy:\n"
					+ "\n"
					+ "Starting My Third Trimester\n"
					+ "Prenatal Visit \n"
					+ "Decorating Baby's Nursery\n"
					+ "Getting The Baby Registry Ready!\n"
					+ "Baby Shower\n"
					+ "Shopping for the baby\n"
					+ "Maternity fitness\n"
					+ "Baby Bump\n"
					+ "Picking our baby's name\n"
					+ "First Contraction\n"
					+ "Ready for delivery\n"
					+ "Packing Hospital Bag\n"
					+ "It's Labor Time!\n"
					+ "Mum and her friends")
	public void verifyPregMilestones_ThirdTrem_AddNewMomentPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// Update Due date of Primary Profile
		int dueDateInMonths = 2;
		String dueDateInDaysSt = GenericFunctions.generateFutureDate(0, dueDateInMonths, 0) + "T00:00:00Z";
		genericFunctionsAPI.updateDueDate(dueDateInDaysSt);

		
		String archetype = Constants.ARCHETYPE_PARENT_TO_BE;
		String archID = genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting Archetype of primary profile
		String archeTypeUserID = genericFunctionsAPI.getArchetypeIdOfProfile();

		if (archID.equals(null) || archeTypeUserID.equals(null)) {
			throw new SkipException(
					"Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		
		// Change archetype of user to parents to be
		if (!(archeTypeUserID.equals(archID))) {
			boolean archetypeChanged = genericFunctionsAPI.changeArcheTypeOfUser(archID);
			if (archetypeChanged == false) {
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		// Go to Moments log page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			 // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}


		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_THIRD_TRIMESTER),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_THIRD_TRIMESTER + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_START_THIRD),
				"Check if milestone " + Constants.MILESTONE_PREG_START_THIRD + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check if milestone " + Constants.MILESTONE_PREG_PRENATAL_VISIT + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.verifyDecoratingNurseryMilestoneDisplayed(),
				"Check if milestone " + Constants.MILESTONE_PREG_DECORATE_NURSERY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_GETTING_REGISTRY),
				"Check if milestone " + Constants.MILESTONE_PREG_GETTING_REGISTRY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_BABY_SHOWER),
				"Check if milestone " + Constants.MILESTONE_PREG_BABY_SHOWER + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_SHOPPING_BABY),
				"Check if milestone " + Constants.MILESTONE_PREG_SHOPPING_BABY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_MATERNITY_FITNESS),
				"Check if milestone " + Constants.MILESTONE_PREG_MATERNITY_FITNESS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_BABY_BUMP),
				"Check if milestone " + Constants.MILESTONE_PREG_BABY_BUMP + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.verifyPickingBabyNameMilestoneDisplayed(),
				"Check if milestone " + Constants.MILESTONE_PREG_PICKING_BABYNAME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_FIRST_CONTRACTION),
				"Check if milestone " + Constants.MILESTONE_PREG_FIRST_CONTRACTION + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_READY_DELIVERY),
				"Check if milestone " + Constants.MILESTONE_PREG_READY_DELIVERY + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_PACK_BAG),
				"Check if milestone " + Constants.MILESTONE_PREG_PACK_BAG + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.verifyLaborTimeMilestoneDisplayed(),
				"Check if milestone " + Constants.MILESTONE_PREG_LABOR_TIME + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_PREG_MUM_FRIENDS),
				"Check if milestone " + Constants.MILESTONE_PREG_MUM_FRIENDS + " is displayed");
		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check if milestone " + Constants.MILESTONE_VACCINEDAY + " is displayed");

		logAssert.assertAll();

	}








}
