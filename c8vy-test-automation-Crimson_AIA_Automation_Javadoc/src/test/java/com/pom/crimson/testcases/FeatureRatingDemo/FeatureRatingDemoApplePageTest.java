package com.pom.crimson.testcases.FeatureRatingDemo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.FAQsPage;
import com.pom.crimson.pages.FeatureRatingDemoPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LogoutPage;
import com.pom.crimson.pages.OnboardingPage;
import com.pom.crimson.pages.PrivacyPolicyPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.pages.TermsOfUsePage;
import com.pom.crimson.util.ExtentReportManager;
import com.pom.crimson.util.Constants;

/**
 * This class contains test methods to test <b>Feature Rating</b> functionality via Apple Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class FeatureRatingDemoApplePageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}
	
	@Test(enabled=true, priority = 177, testName = "Verify Feature rating demo via Apple", description = "Verify Feature rating demo via Apple")
	public void verifyFeatureRatingLoginWithApple() throws Exception
	{
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		loginPage.ClickAppleLogin();
		HomePage homePage= new HomePage(getDriver(),getPlatformName());

		if (homePage.isElementDisplayed("Live events"))
		{
			
		}
		else
		{
			loginPage.LoginWithApple(); 
		}	
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	    MP.clickProfile();
	    logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
	    MP.clickManageProfile();
	    logAssert.assertTrue(MP.isElementDisplayed(Constants.AppleTestUser),"Logged in with correct Apple user");
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_showcase to locate");
			OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
			OBP.ClickTab_showcase();
			FeatureRatingDemoPage FR = new FeatureRatingDemoPage(getDriver(), getPlatformName());
			logAssert.assertTrue(FR.isElementDisplayed("FeatureRatingDemo"),"Navigated to feature rating demo menu");
			FR.FeatureRatingDemo();
			logAssert.assertTrue(FR.isElementDisplayed("Rate feature"),"Rate feature button appears on screen");
			FR.buttonRatefeature();
			 logAssert.assertTrue(FR.isElementDisplayed("How was your experience?"),"How was your experience is displayed with the stars and rate feature button");
			FR.clickStar();
			FR.buttonRatefeature();
			logAssert.assertTrue(FR.isElementDisplayed("Rating submitted"),"Rating submitted appears on screen");
			MP.clickProfile();
		    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
		    LP.VerifyLogoutExists();
		    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
		    LP.Logout_App();
			logAssert.assertAll();
		}
	
	


	@Test(enabled=true, priority = 178, testName = "Verify Feature rating demo with Not Now option via Apple", description = "Verify Feature rating demo with Not Now option via Apple")
	public void verifyFeatureRatingLogin_NotNow_WithGmail() throws Exception
	{
			loginPage.Takepic_Recvideo();
			loginPage.skipIntroScreens();
			loginPage.ClickAppleLogin();
			HomePage homePage= new HomePage(getDriver(),getPlatformName());

			if (homePage.isElementDisplayed("Live events"))
			{
				
			}
			else
			{
				loginPage.LoginWithApple(); 
			}	
			ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
			MP.clickProfile();
			logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
			MP.clickManageProfile();
			logAssert.assertTrue(MP.isElementDisplayed(Constants.AppleTestUser),"Logged in with correct Apple user");
			ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_showcase to locate");
			OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
			OBP.ClickTab_showcase();
			FeatureRatingDemoPage FR = new FeatureRatingDemoPage(getDriver(), getPlatformName());
			logAssert.assertTrue(FR.isElementDisplayed("FeatureRatingDemo"),"Navigated to feature rating demo menu");
			FR.FeatureRatingDemo();
			logAssert.assertTrue(FR.isElementDisplayed("Rate feature"),"Rate feature button appears on screen");
			FR.buttonRatefeature();
			 logAssert.assertTrue(FR.isElementDisplayed("How was your experience?"),"How was your experience is displayed with the stars and rate feature button");
			 logAssert.assertTrue(FR.isElementDisplayed("Not now"),"Not now button is displayed");
			 FR.ClickNotNow();
			logAssert.assertTrue(FR.isElementDisplayed("Rate feature"),"Rating is not submitted and Rate feature button appears on screen");
			MP.clickProfile();
		    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
		    LP.VerifyLogoutExists();
		    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
		    LP.Logout_App();
			logAssert.assertAll();
		}
	
	
}
	
	
	
	