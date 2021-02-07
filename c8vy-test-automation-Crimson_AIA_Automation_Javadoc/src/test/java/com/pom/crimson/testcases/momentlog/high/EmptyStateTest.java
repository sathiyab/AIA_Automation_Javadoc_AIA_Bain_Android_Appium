package com.pom.crimson.testcases.momentlog.high;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddNewMomentsPage;
import com.pom.crimson.pages.GrowthLogPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.VaccineLogPage;
import com.pom.crimson.util.Constants;

import io.restassured.response.Response;

/**
 * This test class contains test cases for Empty state of Moments log, Vaccine log, Growth log
 * 
 * @author Jaspreet Kaur Chagger
 */
public class EmptyStateTest extends BaseFixture {

	HomePage homePage;
	MomentsLogPage momentsLogPage;
	AddNewMomentsPage addNewMomentsPage;
	VaccineLogPage vaccineLogPage;
	GrowthLogPage growthLogPage;
	LoginPage loginPage;
	GenericFunctionsAPI genericFunctionsAPI;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{	
		loginPage=new LoginPage(getDriver(),getPlatformName());
		
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		homePage=new HomePage(getDriver(),getPlatformName());
		genericFunctionsAPI=new GenericFunctionsAPI();
	}
	
	@Test(enabled = true, priority = 1, 
			testName = "EmptyStateTest-Verify View Growth log link and View vaccine Log not displayed when child profile is not created,but Grandparent profile is present", 
			description = "Verify View Growth log link and View vaccine Log is not displayed on Moments Log Page when child profile is not created, But Grandparent profile is present ")
	public void verifyVaccineLogGrowthLogNotPresentGrandParent_EmptyStateTest() throws Exception {
		//GenericFunctionsAPI genericFunctionsAPI = new GenericFunctionsAPI();
		
		//Creates Test data
		
		// Delete all Child profiles
		genericFunctionsAPI.deleteAllChildProfiles();
		
		//Create GrandParent profile
		int profiles=genericFunctionsAPI.getNoOfGrandparentProfiles();
		if(profiles==0)
		{
			genericFunctionsAPI.createGrandparentProfile();
		}
		
		// Restart app for data refresh
		getDriver().launchApp();
		
		// log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

        
		
		LogAssert logAssert = new LogAssert();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String testNote=GenericFunctions.generateRandomAlphanumericString();
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		int childProfiles=genericFunctionsAPI.getNoOfChildProfiles();
		if(childProfiles==0)
		{
			momentsLogPage = homePage.goToMomentsLogPage();
			if (noOfMomentsInitial == 0) {
				// If initial no. of moments are zero,Create First Moment
				addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
				momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote,Constants.ADDNEWMOMENT_MYSELF_TAB);
			} 
			logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.VIEW_GROWTH_LOG_LINK), "Check if "+Constants.VIEW_GROWTH_LOG_LINK+" link is displayed");	
			logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.VIEW_VACCINE_LOG_LINK), "Check if "+Constants.VIEW_VACCINE_LOG_LINK+" link is displayed");	
			logAssert.assertAll();
		}
		
	
	}
	
	@Test(enabled = true, priority = 2, testName = "EmptyStateTest-Verify View Growth log link and View vaccine Log not displayed when child profile is not created,but parent profile is present", 
			description = "Verify View Growth log link and View vaccine Log is not displayed on Moments Log Page when child profile is not created, But related Parent profile is present ")
	public void verifyVaccineLogGrowthLogNotPresentParent_EmptyStateTest() throws Exception {
		//GenericFunctionsAPI genericFunctionsAPI = new GenericFunctionsAPI();
		
		//Creates Test data
		
		// Delete all Child profiles
		genericFunctionsAPI.deleteAllChildProfiles();
		genericFunctionsAPI.deleteAllGrandParentProfiles();


		//Create Related Parent profile
		int profiles=genericFunctionsAPI.getNoOfParentProfiles();
		if(profiles==0)
		{
			genericFunctionsAPI.createParentProfile();
		}
		
		// Restart app for data refresh
		getDriver().launchApp();
		
		//login
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

        
		
		LogAssert logAssert = new LogAssert();
		int noOfMomentsInitial;
		int noOfMomentsFinal;
		String testNote=GenericFunctions.generateRandomAlphanumericString();
		// Checking initial number of moments in backend
		noOfMomentsInitial = genericFunctionsAPI.getSavedMomentsForProfile();

		// Get no. of child profiles associated with primary user
		int childProfiles=genericFunctionsAPI.getNoOfChildProfiles();
		if(childProfiles==0)
		{
			//Go to Moments Log Page
			momentsLogPage = homePage.goToMomentsLogPage();
			if (noOfMomentsInitial == 0) {
				// If initial no. of moments are zero,Create First Moment
				addNewMomentsPage = momentsLogPage.tapAddYourFirstMomentBtn();
				momentsLogPage = addNewMomentsPage.createMomentWithNote(testNote,Constants.ADDNEWMOMENT_MYSELF_TAB);
			} 
			logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.VIEW_GROWTH_LOG_LINK), "Check if "+Constants.VIEW_GROWTH_LOG_LINK+" link is displayed");	
			logAssert.assertFalse(momentsLogPage.isElementDisplayed(Constants.VIEW_VACCINE_LOG_LINK), "Check if "+Constants.VIEW_VACCINE_LOG_LINK+" link is displayed");	
			logAssert.assertAll();
		}
	
	}
		
}
