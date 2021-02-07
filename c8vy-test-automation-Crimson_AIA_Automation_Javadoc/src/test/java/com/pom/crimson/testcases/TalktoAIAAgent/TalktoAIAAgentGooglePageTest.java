package com.pom.crimson.testcases.TalktoAIAAgent;

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
import com.pom.crimson.pages.TalktoAIAAgentPage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Talk to AIA agent</b> Page via Google Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class TalktoAIAAgentGooglePageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
	}
	
	@Test(enabled=true, priority = 143, testName = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer Yes With Email Option", description = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer Yes With Email Option")
	public void SubmitTalktoAIAAgentSelectAIACustomerYes_WithEmailOption_LoginViaEmail() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Talk to AIA Agent Screen appears for Login via eMail");
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
	ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
	PP.clickProfile();
	TalktoAIAAgentPage TA=new TalktoAIAAgentPage(getDriver(),getPlatformName());
	TA.ClickTalktoAIAagent();
	logAssert.assertTrue(TA.isElementDisplayed("Connect with an AIA agent"),"Connect with an AIA agent is correct");
	TA.scroll_RequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request call back"),"Heading is correct");
	TA.SelectProvince();
	//logAssert.assertTrue(TA.isElementDisplayed("Bangkok"),"Bangkok is selected");
	ExtentReportManager.getTest().log(Status.INFO, "Province Bangkok is selected");
	TA.ClickYes();
	logAssert.assertTrue(TA.isElementDisplayed("Yes"),"Yes option is displayed");
	TA.ClickEmail();
	logAssert.assertTrue(TA.isElementDisplayed("Email"),"Email is selected");
	TA.ClickRequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request successfully submitted"),"Request is submitted");
	logAssert.assertTrue(TA.isElementDisplayed("Our agent will contact you back within the next 2 business days."),"Request details is submitted");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();
}
	
	@Test(enabled=true, priority = 144, testName = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer No With Email Option", description = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer No With Email Option")
	public void SubmitTalktoAIAAgentSelectAIACustomerNo_WithEmailOption_LoginViaEmail() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Talk to AIA Agent Screen appears for Login via eMail");
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
	ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
	PP.clickProfile();
	TalktoAIAAgentPage TA=new TalktoAIAAgentPage(getDriver(),getPlatformName());
	TA.ClickTalktoAIAagent();
	logAssert.assertTrue(TA.isElementDisplayed("Connect with an AIA agent"),"Connect with an AIA agent is correct");
	TA.scroll_RequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request call back"),"Heading is correct");
	TA.SelectProvince();
	//logAssert.assertTrue(TA.isElementDisplayed("Bangkok"),"Bangkok is displayed");
	ExtentReportManager.getTest().log(Status.INFO, "Province Bangkok is selected");
	TA.ClickNo();
	logAssert.assertTrue(TA.isElementDisplayed("No"),"No option is displayed");
	TA.ClickEmail();
	logAssert.assertTrue(TA.isElementDisplayed("Email"),"Email is selected");
	TA.ClickRequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request successfully submitted"),"Request is submitted");
	logAssert.assertTrue(TA.isElementDisplayed("Our agent will contact you back within the next 2 business days."),"Request details is submitted");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();	
}
	
	@Test(enabled=true, priority = 145, testName = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer No With Email Option", description = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer No With Email Option")
	public void SubmitTalktoAIAAgentSelectAIACustomerYes_WithPhoneOption_LoginViaEmail() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Talk to AIA Agent Screen appears for Login via eMail");
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
	ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
	PP.clickProfile();
	TalktoAIAAgentPage TA=new TalktoAIAAgentPage(getDriver(),getPlatformName());
	TA.ClickTalktoAIAagent();
	logAssert.assertTrue(TA.isElementDisplayed("Connect with an AIA agent"),"Connect with an AIA agent is correct");
	TA.scroll_RequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request call back"),"Heading is correct");
	TA.SelectProvince();
	//logAssert.assertTrue(TA.isElementDisplayed("Bangkok"),"Bangkok is displayed");
	ExtentReportManager.getTest().log(Status.INFO, "Province Bangkok is selected");
	TA.ClickYes();
	logAssert.assertTrue(TA.isElementDisplayed("Yes"),"Yes option is displayed");
	TA.ClickPhone();
	logAssert.assertTrue(TA.isElementDisplayed("Phone"),"Phone is selected");
	TA.clickPreferredCallTime();
	//logAssert.assertTrue(TA.isElementDisplayed("Morning"),"Preferred Call Time is selected");
	ExtentReportManager.getTest().log(Status.INFO, "Preferred Call Time is selected");
	TA.scroll_RequestCallBack();
	TA.ClickRequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request successfully submitted"),"Request is submitted");
	logAssert.assertTrue(TA.isElementDisplayed("Our agent will contact you back within the next 2 business days."),"Request details is submitted");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();	
}
	
	@Test(enabled=true, priority = 146, testName = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer No With Phone Option", description = "Verify Talk to AIA Agent Screen appears for Login via Google for Select AIA Customer No With Phone Option")
	public void SubmitTalktoAIAAgentSelectAIACustomerNo_WithPhoneOption_LoginViaEmail() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Talk to AIA Agent Screen appears for Login via eMail");
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
	ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
	PP.clickProfile();
	TalktoAIAAgentPage TA=new TalktoAIAAgentPage(getDriver(),getPlatformName());
	TA.ClickTalktoAIAagent();
	logAssert.assertTrue(TA.isElementDisplayed("Connect with an AIA agent"),"Connect with an AIA agent is correct");
	TA.scroll_RequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request call back"),"Heading is correct");
	TA.SelectProvince();
	//logAssert.assertTrue(TA.isElementDisplayed("Bangkok"),"Bangkok is displayed");
	ExtentReportManager.getTest().log(Status.INFO, "Province Bangkok is selected");
	TA.ClickNo();
	logAssert.assertTrue(TA.isElementDisplayed("No"),"No option is displayed");
	TA.ClickPhone();
	logAssert.assertTrue(TA.isElementDisplayed("Phone"),"Phone is selected");
	TA.clickPreferredCallTime();
	//logAssert.assertTrue(TA.isElementDisplayed("Morning"),"Preferred Call Time is selected");
	ExtentReportManager.getTest().log(Status.INFO, "Preferred Call Time is selected");
	TA.scroll_RequestCallBack();
	TA.ClickRequestCallBack();
	logAssert.assertTrue(TA.isElementDisplayed("Request successfully submitted"),"Request is submitted");
	logAssert.assertTrue(TA.isElementDisplayed("Our agent will contact you back within the next 2 business days."),"Request details is submitted");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();
}
	
}