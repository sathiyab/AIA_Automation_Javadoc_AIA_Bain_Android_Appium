package com.pom.crimson.testcases.NPS;

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
import com.pom.crimson.pages.NPSPage;
import com.pom.crimson.pages.OnboardingPage;
import com.pom.crimson.pages.PrivacyPolicyPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.pages.TermsOfUsePage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>NPS</b> functionality via Google Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class NPSGooglePageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}
	
	@Test(enabled=true, priority = 157, testName = "Verify NPS for (Scale 1 -6) Login via Google", description = "Verify NPS for (Scale 1 -6) Login via Google")
	public void VerifyNPS_Scale1to6_LoginViaGoogle() throws InterruptedException, IOException
	{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify NPS appears for Login via Google");
	loginPage.Takepic_Recvideo();
	 if (loginPage.isElementDisplayed("Skip"))
	    {
			      
			        loginPage.skipIntroScreens();
	    }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
	OBP.ClickTab_showcase();
	NPSPage NP=new NPSPage(getDriver(),getPlatformName());
	NP.scrollNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
	NP.ClickNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
	logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
	NP.Click1();
	logAssert.assertTrue(OBP.isElementDisplayed("1"),"Scale 1 is clicked");
	NP.ClickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("What went wrong?"),"What went wrong? appears");
	NP.EnterFeedback_Scale1to6();
	NP.clickSubmit();
	logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
	logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Thank you for your feedback! appears");
	Thread.sleep(1000);
	
	OBP.ClickTab_showcase();
	NP.scrollNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
	NP.ClickNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
	logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
	NP.Click2();
	logAssert.assertTrue(OBP.isElementDisplayed("1"),"Scale 2 is clicked");
	NP.ClickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("What went wrong?"),"What went wrong? appears");
	NP.EnterFeedback_Scale1to6();
	NP.clickSubmit();
	logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
	logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Your feedback will help us improve Crimson experience.");
	
	Thread.sleep(1000);
	OBP.ClickTab_showcase();
	NP.scrollNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
	NP.ClickNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
	NP.Click3();
	logAssert.assertTrue(OBP.isElementDisplayed("3"),"Scale 3 is clicked");
	logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
	NP.ClickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("What went wrong?"),"What went wrong? appears");
	NP.EnterFeedback_Scale1to6();
	NP.clickSubmit();
	logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
	logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Your feedback will help us improve Crimson experience.");
	
	Thread.sleep(1000);
	OBP.ClickTab_showcase();
	NP.scrollNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
	NP.ClickNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
	logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
	NP.Click4();
	logAssert.assertTrue(OBP.isElementDisplayed("4"),"Scale 4 is clicked");
	NP.ClickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("What went wrong?"),"What went wrong? appears");
	NP.EnterFeedback_Scale1to6();
	NP.clickSubmit();
	logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
	logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Your feedback will help us improve Crimson experience.");
	
	Thread.sleep(1000);
	OBP.ClickTab_showcase();
	NP.scrollNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
	NP.ClickNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
	logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
	NP.Click5();
	logAssert.assertTrue(OBP.isElementDisplayed("5"),"Scale 5 is clicked");
	NP.ClickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("What went wrong?"),"What went wrong? appears");
	NP.EnterFeedback_Scale1to6();
	NP.clickSubmit();
	logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
	logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Your feedback will help us improve Crimson experience.");
	
	Thread.sleep(1000);
	OBP.ClickTab_showcase();
	NP.scrollNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
	NP.ClickNPS();
	logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
	logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
	NP.Click6();
	logAssert.assertTrue(OBP.isElementDisplayed("6"),"Scale 6 is clicked");
	NP.ClickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("What went wrong?"),"What went wrong? appears");
	NP.EnterFeedback_Scale1to6();
	NP.clickSubmit();
	logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
	logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Your feedback will help us improve Crimson experience.");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();	
	}
	
	

	@Test(enabled=true, priority = 158, testName = "Verify NPS for (Scale 7 -8) Login via Google", description = "Verify NPS for (Scale 7 -8) Login via Google")
	public void VerifyNPS_Scale7to8_LoginViaGmail() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify NPS appears for Login via Google");
		loginPage.Takepic_Recvideo();
		 if (loginPage.isElementDisplayed("Skip"))
		    {
			 
		       
		        loginPage.skipIntroScreens();   
		    }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
		loginPage.LoginWithGmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
		OBP.ClickTab_showcase();
		NPSPage NP=new NPSPage(getDriver(),getPlatformName());
		NP.scrollNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
		NP.ClickNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
		logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
		NP.Click7();
		logAssert.assertTrue(OBP.isElementDisplayed("1"),"Scale 7 is clicked");
		NP.ClickNext();
		logAssert.assertTrue(OBP.isElementDisplayed("What can be improved?"),"What can be improved? appears");
		NP.EnterFeedback_Scale7to8();
		NP.clickSubmit();
		logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
		logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Thank you for your feedback! appears");
		Thread.sleep(1000);
		
		OBP.ClickTab_showcase();
		NP.scrollNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
		NP.ClickNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
		logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
		NP.Click8();
		logAssert.assertTrue(OBP.isElementDisplayed("1"),"Scale 8 is clicked");
		NP.ClickNext();
		logAssert.assertTrue(OBP.isElementDisplayed("What can be improved"),"What can be improved? appears");
		NP.EnterFeedback_Scale7to8();
		NP.clickSubmit();
		logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
		logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Your feedback will help us improve Crimson experience.");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
		MP.clickProfile();
	    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
	    LP.VerifyLogoutExists();
	    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
	    LP.Logout_App();
		logAssert.assertAll();	
	
	}
	
	@Test(enabled=true, priority = 159, testName = "Verify NPS for (Scale 9 -10) Login via Google", description = "Verify Contact Us Screen appears for Login via Google")
	public void VerifyNPS_Scale9to10_LoginViaGoogle() throws InterruptedException, IOException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Verify NPS appears for Login via Google");
		loginPage.Takepic_Recvideo();
		 if (loginPage.isElementDisplayed("Skip"))
		    {
			 
		        
		        loginPage.skipIntroScreens();   
		    }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
		loginPage.LoginWithGmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
		OBP.ClickTab_showcase();
		NPSPage NP=new NPSPage(getDriver(),getPlatformName());
		NP.scrollNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
		NP.ClickNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
		logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
		NP.Click9();
		logAssert.assertTrue(OBP.isElementDisplayed("1"),"Scale 9 is clicked");
		NP.ClickNext();
		logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you liked or what can be improved"),"Tell us what you liked or what can be improved appears");
		NP.EnterFeedback_Scale9to10();
		NP.clickSubmit();
		logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
		logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Thank you for your feedback! appears");
		Thread.sleep(1000);
		
		OBP.ClickTab_showcase();
		NP.scrollNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("NPS"),"NPS menu appears");
		NP.ClickNPS();
		logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you think"),"Tell us what you think appears");
		logAssert.assertTrue(OBP.isElementDisplayed("On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague?"),"On a scale of 1 to 10, how likely are you to recommend Crimson to a friend or colleague? appears");
		NP.Click10();
		logAssert.assertTrue(OBP.isElementDisplayed("1"),"Scale 10 is clicked");
		NP.ClickNext();
		logAssert.assertTrue(OBP.isElementDisplayed("Tell us what you liked or what can be improved"),"Tell us what you liked or what can be improved appears");
		NP.EnterFeedback_Scale9to10();
		NP.clickSubmit();
		logAssert.assertTrue(OBP.isElementDisplayed("Thank you for your feedback!"),"Thank you for your feedback! appears");
		logAssert.assertTrue(OBP.isElementDisplayed("Your feedback will help us improve Crimson experience."),"Your feedback will help us improve Crimson experience.");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
		MP.clickProfile();
	    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
	    LP.VerifyLogoutExists();
	    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
	    LP.Logout_App();
		logAssert.assertAll();	
	
	
}

}