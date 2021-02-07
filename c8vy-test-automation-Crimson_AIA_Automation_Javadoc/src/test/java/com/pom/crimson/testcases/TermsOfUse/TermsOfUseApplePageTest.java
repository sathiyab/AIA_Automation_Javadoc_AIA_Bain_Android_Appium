package com.pom.crimson.testcases.TermsOfUse;

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
import com.pom.crimson.pages.TermsOfUsePage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Terms of use</b> Page via Apple Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class TermsOfUseApplePageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}
	
	
	
	
	@Test(enabled=true, priority = 248, testName = "Verify Terms Of Use Screen appears for Login via Apple", description = "Verify Terms Of Use Screen appears for Login via Apple")
	public void VerifyTermsOfUse_LoginViaApple() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Terms Of Use Screen appears for Login via Google");
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
		TermsOfUsePage TU=new TermsOfUsePage(getDriver(),getPlatformName());
		TU.TermsOfUseMenu();
		logAssert.assertTrue(TU.isElementDisplayed("Terms of use"),"Heading is correct");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
		MP.clickProfile();
	    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
	    LP.VerifyLogoutExists();
	    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
	    LP.Logout_App();
		logAssert.assertAll();	
		logAssert.assertAll();
	}
	
	
	
}