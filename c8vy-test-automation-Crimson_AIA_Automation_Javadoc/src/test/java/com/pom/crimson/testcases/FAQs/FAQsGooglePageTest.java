package com.pom.crimson.testcases.FAQs;

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
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>FAQ</b> Screen functions via Google Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class FAQsGooglePageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();	
	}
	
		
	@Test(enabled=true, priority = 118, testName = "Verify FAQs Screen appears for Login via Google", description = "Verify Contact Us Screen appears for Login via Google")
	public void VerifyFAQs_LoginViaGoogle() throws InterruptedException, IOException
	{ 
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
	ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
	PP.clickProfile();
	FAQsPage FP=new FAQsPage(getDriver(),getPlatformName());
	FP.FAQsMenu();
	logAssert.assertTrue(FP.isElementDisplayed("FAQs"),"FAQ Haeading is displayedd");
	logAssert.assertTrue(FP.isElementDisplayed("Insurance"),"Description is displayed");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();
	}
	
	
	
}