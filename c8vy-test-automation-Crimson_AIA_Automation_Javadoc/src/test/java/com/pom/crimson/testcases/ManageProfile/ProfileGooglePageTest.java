package com.pom.crimson.testcases.ManageProfile;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LogoutPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.ExtentReportManager;
import com.pom.crimson.util.Constants;

/**
 * This class contains test methods to test <b>Profile</b> Screen Elements via Google Login 
 * 
 * @author Balaji.Sridharan 
 */
public class ProfileGooglePageTest extends BaseFixture {
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{	
		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();	
	}
	

	@Test(enabled=true, priority = 203, testName = "Verify Headings and Profile details exists in Manage Profile via Google", description = "Verify Headings and Profile details exists in Manage Profile via Google")
	public void VerifyProfileDetailsViaGmail() throws InterruptedException, IOException
	{ 
	loginPage.Takepic_Recvideo();
	loginPage.skipIntroScreens();
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
    MP.clickProfile();
    logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
    MP.clickManageProfile();
    logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage profile screen appears");
    logAssert.assertTrue(MP.isElementDisplayed("What best describes you today"),"Manage profile screen appears");
    logAssert.assertTrue(MP.isElementDisplayed("Interests"),"Manage profile screen appears");
    logAssert.assertTrue(MP.isElementDisplayed("Preferred name"),"Preferred name field should exist");
    logAssert.assertTrue(MP.isElementDisplayed("First name"),"First name field should exist");
    logAssert.assertTrue(MP.isElementDisplayed("Last name"),"Last name field should exist");
    logAssert.assertTrue(MP.isElementDisplayed("Date of birth"),"Date of birth field should exist");
    logAssert.assertTrue(MP.isElementDisplayed("Email"),"Email field should exist");
    //logAssert.assertTrue(MP.isElementDisplayed("balaji.testuser5@gmail.com"),"Email-id is correct");
    logAssert.assertTrue(MP.isElementDisplayed(Constants.GmailTestUser),"Email-id is correct");
    logAssert.assertTrue(MP.isElementDisplayed("Phone number"),"Phone number field should exist");
    logAssert.assertTrue(MP.isElementDisplayed("National ID number"),"National ID number field should exist");
    MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
    logAssert.assertAll();
 	}
}
