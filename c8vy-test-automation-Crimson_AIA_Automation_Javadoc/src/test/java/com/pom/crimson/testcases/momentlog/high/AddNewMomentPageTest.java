package com.pom.crimson.testcases.momentlog.high;

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

	@Test(enabled = true, priority = 1, testName = "Delete all moments-for data clean up", 
			description = "Delete all moments-for data clean up")
	public void deleteAllData_AddNewMomentPageTest() throws Exception {
		genericFunctionsAPI.deleteAllMomentsAssociatedWithProfile();
	}
		@Test(enabled = true,priority = 2, testName = "As a User, I am able to add a photo to a moment using photo from library", description = "AddNewMomentPage-Verify that Clicking icon to add photo to moment on moments input screen should prompt user to select photo from phone Photo Library ")
	public void verifyPhotoLibraryToggle_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile(); //get initial no. of saved moments
		
		momentsLogPage = homePage.goToMomentsLogPage(); //go to Moments Log Page
		if (noOfMomentsInitial == 0) { 
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		logAssert.assertTrue(addNewMomentsPage.verifyChooseFromLibrary(), "Check if Choose From library option is present on New Moment Page");
		logAssert.assertAll();
	}

	@Test(enabled = true,priority = 3, testName = "AddNewMomentPage-Verify phone camera toggle", 
			description = "AddNewMomentPage-Verify that Clicking icon to add photo should present option to Take photo")
	public void verifyCameraToggle_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();
		
		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		
		momentsLogPage = homePage.goToMomentsLogPage(); //go to Moments Log Page
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		logAssert.assertTrue(addNewMomentsPage.verifyTakePhoto(), "Check if Take photo option is present on New moment page");
		logAssert.assertAll();
	}

	@Test(enabled = true,priority = 4, testName = "AddNewMomentPage-Verify that user is able to accept a photo", 
			description = "AddNewMomentPage-Verify that user is able to accept a photo")
	public void verifyCameraAccept_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();
		
		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			  // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		logAssert.assertTrue(addNewMomentsPage.verifyAccept(), "Check if Photo is accepted");
		logAssert.assertAll();
	}

	@Test(enabled = true,priority = 5, testName = "AddNewMomentPage-Verify that user is able to reject a photo", 
			description = "AddNewMomentPage-Verify that user is able to reject a photo")
	public void verifyCameraReject_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();
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
		logAssert.assertTrue(addNewMomentsPage.verifycancel(), "Check if Option Add here your special capture of the day or add another image is visible");
		logAssert.assertAll();
	}

	@Test(enabled = true,priority = 6, testName = "AddNewMomentPage-Verify that user is able to retake a photo", 
			description = "AddNewMomentPage-Verify that user is able to click on retake photo")
	public void verifyCameraRetake_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert=new LogAssert();
		// Checking initial number of moments in backend
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		
		momentsLogPage = homePage.goToMomentsLogPage();//Go to Moments Log Page
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
		      // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		logAssert.assertTrue(addNewMomentsPage.verifyRetake(), "Check if user can retake camera photo");
		logAssert.assertAll();
	}
	
	@Test(enabled = true,priority = 7, testName = "AddNewMomentPage-Verify the functionality when user taps on Manage Profiles link on New Moment page", description = "AddNewMomentPage-Verify the functionality when user taps on Manage Profiles link on New Moment page")
	public void verifyManageProfileLink_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		int noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
		      // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		// Tap on Manage Profile link
		manageProfilePage = addNewMomentsPage.tapManageProfileLink();
		Thread.sleep(2000);
		logAssert.assertTrue(manageProfilePage.isElementDisplayed(Constants.MANAGE_PROFILE_TITLE),
				"Check if Manage profile page is displayed by checking if "+Constants.MANAGE_PROFILE_TITLE+"  title is displayed");
		logAssert.assertAll();
	}

	@Test(enabled =true,priority = 8, testName = "AddNewMomentPage-Verify the functionality when user creates a moment for   \"Myself\"  with a pregnancy milestone selected", description = "AddNewMomentPage-Verify the functionality when user creates a moment for Myself  with a pregnancy milestone selected")
	public void verifyMomentCreationWithPregnancyMilestone_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial=0;
		int noOfMomentsFinal=0;
		String milestoneId = "";
		String imageUrl = "";
		String note = "";

		String archetype = "Parent to be";
		
		String archIDParentsToBe=genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting Archetype for primary profile
		String archeTypeUser=genericFunctionsAPI.getArchetypeIdOfProfile();

		if(archIDParentsToBe.equals(null)||archeTypeUser.equals(null) )
		{
			System.out.println("archIDParentsToBe"+archIDParentsToBe);
			System.out.println("archeTypeUser"+archeTypeUser);

			throw new SkipException("Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		
		// Changing archetype of user to Parents to be
		if (!archeTypeUser.equals(archIDParentsToBe)) {
			boolean archetypeChanged=genericFunctionsAPI.changeArcheTypeOfUser(archIDParentsToBe);
			if (archetypeChanged==false)
			{
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}
		
		// Checking initial number of moments in backend
		 noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		List<String> momentIdInitial=genericFunctionsAPI.getIDsOfSavedMoment();

		//re-launching app for data refresh
		getDriver().launchApp();
		
		// Logging in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		//Go to Moments Log Page
		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {

			// If no. of moments are zero,Create First Moment 
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestone(1, testNote, Constants.MILESTONE_PREG_PRENATAL_VISIT,
					Constants.ADDNEWMOMENT_MYSELF_TAB);
		} else {
			// Create Moment
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
			momentsLogPage = addNewMomentsPage.createMomentWithImageNoteMilestone(1, testNote, Constants.MILESTONE_PREG_PRENATAL_VISIT,
					Constants.ADDNEWMOMENT_MYSELF_TAB);
			Thread.sleep(1000);
		}
		
		try {
		Thread.sleep(2000);
		// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			// Getting moment id of last saved moment from backend
		List<String> momentIdFinal=genericFunctionsAPI.getIDsOfSavedMoment();
		String momentIdlastMoment=genericFunctionsAPI.getMomentIdLastSavedMoment(momentIdInitial, momentIdFinal);		
		Response MomentsList= genericFunctionsAPI.getResponseSavedMoments();
		// Retrieving imageurl,note,milestoneID saved in backend for last moment saved
		imageUrl = genericFunctionsAPI.getImageUrlByMomentId(MomentsList, momentIdlastMoment);
		note = genericFunctionsAPI.getNoteByMomentId(MomentsList, momentIdlastMoment);
		milestoneId=genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, momentIdlastMoment);
		}catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

		}

		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Moment is saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note is saved in backend");
		logAssert.assertNotNull(imageUrl, "Check if Image is saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone is saved in backend");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check If Note as saved in Moment,Note to be displayed on Moments Log Page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check If milestone as saved in Moment,"+Constants.MILESTONE_PREG_PRENATAL_VISIT+" Milestone to be displayed on Moments Log Page");
		logAssert.assertAll();
	}

	@Test(enabled =true,priority = 9, testName = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page asExperiened parent and Pregnancy Milestones should not be displayed", description = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page asExperiened parent and Pregnancy Milestones should not be displayed")
	public void verifyMyselfTabForExperiencedParents_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		getDriver().closeApp();// closing app
		String archetype = "Experienced parent";
		
		String archIDExperiencedParent=genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting Archetype of primary profile
		String archeTypeUserID=genericFunctionsAPI.getArchetypeIdOfProfile();

		if(archIDExperiencedParent.equals(null)||archeTypeUserID.equals(null) )
		{
			throw new SkipException("Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		
		// Changing archetype of profile to Experienced parent
		if (!archeTypeUserID.equals(archIDExperiencedParent)) {
			boolean archetypeChanged=genericFunctionsAPI.changeArcheTypeOfUser(archIDExperiencedParent);
			if (archetypeChanged==false)
			{
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		Thread.sleep(2000);
		getDriver().launchApp();//re-launching app for data refresh
		
		// Logging in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		momentsLogPage = homePage.goToMomentsLogPage();//Go to Moments Log Page
		if (momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT)) {
			// If Add your first moment button is displayed, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			 // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		
		// Tap on Myself tab on New Moment page
		addNewMomentsPage.tapOnElementByText(Constants.ADDNEWMOMENT_MYSELF_TAB);
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check that "+Constants.MILESTONE_PREG_PRENATAL_VISIT+" milestone is not displayed when user taps Myself tab as Experienced parent");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check that "+Constants.MILESTONE_VACCINEDAY+ "milestone is not  displayed when user taps Myself tab as Experienced parent");

		logAssert.assertAll();
	}

	@Test(enabled =true,priority = 10, testName = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page as New parent and Pregnancy Milestones should not be displayed", description = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page as New parent and Pregnancy Milestones should not be displayed")
	public void verifyMyselfTabForNewParents_AddNewMomentsPageTest() throws Exception {
		
		LogAssert logAssert = new LogAssert();
		
		getDriver().closeApp(); //close app
		String archetype = "New parent";

		String archIDNewParent=genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting archetype of primary profile
		String archeTypeUserID=genericFunctionsAPI.getArchetypeIdOfProfile();

		if(archIDNewParent.equals(null)||archeTypeUserID.equals(null) )
		{
			throw new SkipException("Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		
		// Changing archetype of primary profile to New Parents
		if (!archeTypeUserID.equals(archIDNewParent)) {
			boolean archetypeChanged=genericFunctionsAPI.changeArcheTypeOfUser(archIDNewParent);
			if (archetypeChanged==false)
			{
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		Thread.sleep(2000);
		getDriver().launchApp();//re-launching app for data refresh
		
		//Logging in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		momentsLogPage = homePage.goToMomentsLogPage();//Go to Moments Log Page
		if (momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT)) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
		    // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}

		//Tap on Myself tab
		addNewMomentsPage.tapOnElementByText(Constants.ADDNEWMOMENT_MYSELF_TAB);
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check that" +Constants.MILESTONE_PREG_PRENATAL_VISIT+" milestone is not displayed when user taps Myself tab as New parent");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check that "+Constants.MILESTONE_VACCINEDAY+" milestone is not displayed when user taps Myself tab as New parent");
	
		logAssert.assertAll();
	}

	@Test(enabled =true,priority = 11, testName = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page as Parent to be and Pregnancy Milestones should  be displayed", description = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page as Parent to be and Pregnancy Milestones should  be displayed")
	public void verifyMyselfTabForParentToBe_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		getDriver().closeApp();//closing app
		String archetype = "Parent to be";
		String archIDParentsToBe=genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting Archetype of Primary profile
		String archeTypeUserID=genericFunctionsAPI.getArchetypeIdOfProfile();
		if(archIDParentsToBe.equals(null)||archeTypeUserID.equals(null) )
		{
			throw new SkipException("Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		
		// Changing archetype of primary profile to Parents to be
		if (!archeTypeUserID.equals(archIDParentsToBe)) {
			boolean archetypeChanged=genericFunctionsAPI.changeArcheTypeOfUser(archIDParentsToBe);
			if (archetypeChanged==false)
			{
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}
		Thread.sleep(2000);
		getDriver().launchApp();// Re-launching app for data refresh
		
		//Logging in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		
		momentsLogPage = homePage.goToMomentsLogPage();//Go to Moments Log Page
		if (momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT)) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			// Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}
		//Tap on Myself tab
		addNewMomentsPage.tapOnElementByText(Constants.ADDNEWMOMENT_MYSELF_TAB);
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check that "+Constants.MILESTONE_PREG_PRENATAL_VISIT+" milestone is displayed when user taps Myself tab as Parent to be");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check that "+Constants.MILESTONE_VACCINEDAY+" milestone is  displayed when user taps Myself tab as parent to be");
	
		logAssert.assertAll();
	
	}

	@Test(enabled =true,priority = 12, testName = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page as Planning a baby parent and Pregnancy Milestones should not be displayed", description = "AddNewMomentPage-Verify the functionality when user taps on  \"Myself\" option on New Moment page as Planning a baby parent and Pregnancy Milestones should not be displayed")
	public void verifyMyselfTabForPlanningBaby_AddNewMomentsPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		getDriver().closeApp();//Closing app
		String archetype = "Planning for a baby";
		String archIDPlanningBaby=genericFunctionsAPI.getArchetypeIdForArchetype(archetype);
		// Getting Archetype of Primary profile
		String archeTypeUserID=genericFunctionsAPI.getArchetypeIdOfProfile();
		
		System.out.println("archIDParentsToBe"+archIDPlanningBaby);
		System.out.println("archeTypeUserID"+archeTypeUserID);

		if(archIDPlanningBaby.equals(null)||archeTypeUserID.equals(null) )
		{
			System.out.println("archIDParentsToBe"+archIDPlanningBaby);
			System.out.println("archeTypeUserID"+archeTypeUserID);

			throw new SkipException("Either ArchID of parents to be archetype or User is null in backend. Please check test data/services");
		}
		
		// Changing archetype of Primary profile to Planning for a baby
		if (!archeTypeUserID.equals(archIDPlanningBaby)) {
			boolean archetypeChanged=genericFunctionsAPI.changeArcheTypeOfUser(archIDPlanningBaby);
			System.out.println("archetypeChanged"+archetypeChanged);
			if (archetypeChanged==false)
			{
				throw new SkipException("Can't change Archetype of User.Test case cannot be executed");
			}
		}

		Thread.sleep(2000);
		getDriver().launchApp();//Re-launching app for data refresh
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}


		momentsLogPage = homePage.goToMomentsLogPage();//Go to Moments Log Page
		if (momentsLogPage.isElementDisplayed(Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT)) {
			 // Tap on Add your first moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			 // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}

		//Tap on myself tab
		addNewMomentsPage.tapOnElementByText(Constants.ADDNEWMOMENT_MYSELF_TAB);
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MILESTONE_PREG_PRENATAL_VISIT),
				"Check that "+Constants.MILESTONE_PREG_PRENATAL_VISIT+" milestone is not displayed when user taps Myself tab as Planning a baby parent");
		logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.MILESTONE_VACCINEDAY),
				"Check that "+Constants.MILESTONE_VACCINEDAY+" milestone is not displayed when user taps Myself tab as Planning a baby parent");
		logAssert.assertAll();
	}

	@Test(enabled = true,priority = 13, testName = "AddNewMomentPage-Verify the functionality when user creates a moment for \"ChildA\" with Milestone+Note(milestone other than vaccine day)", 
			description = "AddNewMomentPage-Verify the functionality when user creates a moment for   \"ChildA\" with Milestone+Note(milestone other than vaccine day)")
	public void verifyMomentCreationChildMilestoneNote_AddNewMomentPageTest() throws Exception {
	
		LogAssert logAssert = new LogAssert();
		
		String testNote = GenericFunctions.generateRandomAlphanumericString();
		int noOfMomentsInitial=0;
		int noOfMomentsFinal=0;
		String milestoneId = "";
		String note = "";
		
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


		
		 noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();
		 List<String> momentIdInitial=genericFunctionsAPI.getIDsOfSavedMoment();

		momentsLogPage = homePage.goToMomentsLogPage();
		if (noOfMomentsInitial == 0) {
			// if no moments are present, Tap on Add your First moment button
			addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
		} else {
			  // Tap on Add new moment button
			addNewMomentsPage = momentsLogPage.tapAddNewMomentBtn();
		}

		// Tap on Child tab by preferred name
		addNewMomentsPage.tapChildBtn(childPreferredName);

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.RECOMMENDED_MILESTONE_0_3_MONTHS),
				"Check if following " + Constants.RECOMMENDED_MILESTONE_0_3_MONTHS + " section is displayed");

		logAssert.assertTrue(addNewMomentsPage.isElementDisplayed(Constants.MILESTONE_CHILD_BABY_IS_BORN),
				"Check if milestone " + Constants.MILESTONE_CHILD_BABY_IS_BORN + " is displayed");
		addNewMomentsPage.createMomentWithNoteMilestone(testNote, Constants.MILESTONE_CHILD_BABY_IS_BORN , childPreferredName);
		
		

		try{
			Thread.sleep(2000);

			// Checking final no. of moments in backend
			noOfMomentsFinal = genericFunctionsAPI.getSavedMomentsForProfile();
			 // Getting moment id of last saved moment from backend
			List<String> momentIdFinal=genericFunctionsAPI.getIDsOfSavedMoment();
			String momentIdlastMoment=genericFunctionsAPI.getMomentIdLastSavedMoment(momentIdInitial, momentIdFinal);		
			Response MomentsList= genericFunctionsAPI.getResponseSavedMoments();
		    // Retrieving note,milestoneID saved in backend for last moment saved
			note = genericFunctionsAPI.getNoteByMomentId(MomentsList, momentIdlastMoment);
			milestoneId=genericFunctionsAPI.getMilestoneIDByMomentId(MomentsList, momentIdlastMoment);
			} catch(Exception e)
		{
				ExtentReportManager.getTest().log(Status.INFO, "Exception while fetching moments response from backend"+e.getMessage());

			}

		
		logAssert.assertNotEquals(noOfMomentsFinal, noOfMomentsInitial, "Check if Check if Moment saved in backend");
		logAssert.assertEquals(note, testNote, "Check if Note saved in backend");
		logAssert.assertNotNull(milestoneId, "Check if Milestone saved in backend");
		
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(testNote),
				"Check if Note:" + testNote + " is displayed on Moments Log page");
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MILESTONE_CHILD_BABY_IS_BORN),
				"Check if Milestone: " + Constants.MILESTONE_CHILD_BABY_IS_BORN + " as saved in Moment is displayed on Moments log Page");
		
		logAssert.assertAll();
			
		
	}
		
}
