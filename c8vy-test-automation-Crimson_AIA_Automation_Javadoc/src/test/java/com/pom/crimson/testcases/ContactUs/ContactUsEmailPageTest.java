package com.pom.crimson.testcases.ContactUs;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LogoutPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Contact us</b> page via Email Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class ContactUsEmailPageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();	
	}
	
	@Test(enabled=true, priority = 111, testName = "Verify Contact Us Screen appears for Login via eMail", description = "Verify Contact Us Screen appears for Login via eMail")
	public void VerifyContactUs_LoginViaEmail() throws InterruptedException, IOException
	{ 
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
	ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
	PP.clickProfile();
	ContactUsPage CP=new ContactUsPage(getDriver(),getPlatformName());
	CP.ContactUsMenu();
	logAssert.assertTrue(CP.isElementDisplayed("Contact us"),"Contact Us is printed");
	//logAssert.assertTrue(CP.isElementDisplayed("support@crimson.com"),"e-mail id is correct");
	logAssert.assertTrue(CP.isElementDisplayed("support.alive@aia.com"),"e-mail id is correct");
	logAssert.assertTrue(CP.isElementDisplayed("02 353 8998"),"Phone number is correct");
	//logAssert.assertTrue(CP.isElementDisplayed("Mon - Fri 09:00 - 17:00\r\n" + 
		//	"Sat - Sun 10:00 - 19:00"),"Working Timings is correct");
	/*
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
    */
	logAssert.assertAll();
	}
	
	
}