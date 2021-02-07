package com.pom.crimson.testcases.Logout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.IntroPage;
import com.pom.crimson.pages.LiveEventsPage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LogoutPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Logout</b> functionality via Apple Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class LogoutApplePageTest extends BaseFixture{
	
	LoginPage loginPage;
	HomePage homePage;
	//LogoutPage logoutPage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
	}
	
	

	@Test(enabled=true, priority = 254, testName = "Verify Log out with Apple", description = "Verify Log out with Apple")
	public void VerifyLogoutviaApple() throws Exception
	{
		ExtentReportManager.getTest().log(Status.INFO, "Verify Log out with Facebook");
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		logAssert.assertTrue(loginPage.isElementDisplayed("Continue with Apple"),"Continue with Apple option appears in Login or Sign up screens");
		loginPage.ClickAppleLogin();
		HomePage homePage= new HomePage(getDriver(),getPlatformName());

		if (homePage.isElementDisplayed("Live events"))
		{
			
		}
		else
		{
			loginPage.LoginWithApple(); 
		}	
		logAssert.assertTrue(homePage.isElementDisplayed("Profile"),"Profile menu is displayed");
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
		ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
		PP.clickProfile();
		logAssert.assertTrue(PP.isElementDisplayed("ACCOUNT"),"ACCOUNT displayed");
		LogoutPage logoutPage= new LogoutPage(getDriver(),getPlatformName());
		logoutPage.VerifyLogoutExists();
		logAssert.assertTrue(PP.isElementDisplayed("Log out"),"Log out is displayed");
		logoutPage.Logout_App();
		IntroPage introPage=new IntroPage(getDriver(),getPlatformName());
		//logAssert.assertTrue(logoutPage.isElementDisplayed("Log in or sign up"),"App gets logged out and shows Log in or sign up screen appears");
		logAssert.assertTrue(introPage.isElementDisplayed("Connect with specialist doctors and nurses"),"Intro screen1 heading is validated");
		logAssert.assertAll();
	}
}