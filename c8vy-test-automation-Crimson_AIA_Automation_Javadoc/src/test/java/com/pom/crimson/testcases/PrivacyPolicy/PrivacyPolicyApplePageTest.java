package com.pom.crimson.testcases.PrivacyPolicy;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.FAQsPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LogoutPage;
import com.pom.crimson.pages.PrivacyPolicyPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Privacy policy</b> page via Apple Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class PrivacyPolicyApplePageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}

	@Test(enabled=true, priority = 242, testName = "Verify Privacy Statement Screen appears for Login via Apple", description = "Verify Privacy Statement Screen appears for Login via Apple")
	public void VerifyPrivacyStatement_LoginViaApple() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Privacy Statement Screen appears for Login via Apple");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		
	      
	        loginPage.skipIntroScreens();    
	    }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
		loginPage.ClickAppleLogin();
		HomePage homePage= new HomePage(getDriver(),getPlatformName());

		if (homePage.isElementDisplayed("Live events"))
		{
			
		}
		else
		{
			loginPage.LoginWithApple(); 
		}	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
		PP.clickProfile();
		PrivacyPolicyPage PS=new PrivacyPolicyPage(getDriver(),getPlatformName());
		PS.PrivacyStatementMenu();
		logAssert.assertTrue(PS.isElementDisplayed("Privacy Statement"),"Heading is correct");
		logAssert.assertTrue(PS.isElementDisplayed("Personal data consent"),"Heading is correct");
		logAssert.assertTrue(PS.isElementDisplayed("Direct Marketing Consent"),"Heading is correct");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
		MP.clickProfile();
	    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
	    LP.VerifyLogoutExists();
	    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
	    LP.Logout_App();
		logAssert.assertAll();	
	}
	
	
	
}