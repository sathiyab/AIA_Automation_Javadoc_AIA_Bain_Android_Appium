package com.pom.crimson.testcases.Login;

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
import com.pom.crimson.pages.LiveEventsPage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.util.ExtentReportManager;

import org.openqa.selenium.Keys;

/**
 * This class contains test methods to test all <b>Login</b> methods and validations in the mobile app 
 * 
 * @author Balaji.Sridharan 
 */	
public class LoginPageTest2 extends BaseFixture{
	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
	}	
	

	@Test(enabled=true, priority = 3, testName = "Verify Log in with Email", description = "Verify Log in with Email")
	public void verifyLoginWithEmail() throws Exception
	{
		ExtentReportManager.getTest().log(Status.INFO, "Verify Log in with Email");
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		loginPage.LoginWithEmail();
	  //  FluentWait<WebDriver> wait = null;
		//	WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Live events']")));
		HomePage homePage= new HomePage(getDriver(),getPlatformName());
		logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
		logAssert.assertAll();
	}	
	
	
	@Test(enabled=true, priority = 4, testName = "Verify Email cannot be empty appears while providing blank email", description = "Verify Log in with Email")
	public void verifyLoginWithBlankEmail() throws Exception
	{
		ExtentReportManager.getTest().log(Status.INFO, "Verify Email cannot be empty appears while providing blank email");
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		loginPage.LoginWithBlankEmail();
		ExtentReportManager.getTest().log(Status.INFO, "Verify Email cannot be empty appears while providing blank email");
		if (loginPage.isElementDisplayed("Email cannot be empty"))
		{
			logAssert.assertTrue(loginPage.isElementDisplayed("Email cannot be empty"),"Correct error message appears");
			ExtentReportManager.getTest().log(Status.PASS, "Verify Email cannot be empty appears while providing blank email");
		}	
		else
		{
			Assert.fail("Email Cannot be empty notification is not displayed.");
			ExtentReportManager.getTest().log(Status.FAIL, "Verify Email cannot be empty appears while providing blank email");
		}
		logAssert.assertAll();
	}	

	
	
	@Test(enabled=true, priority = 5, testName = "Verify appropriate message appears while providing incorrect email format", description = "Verify Log in with Email")
	public void verifyincorrectEmailFormat() throws Exception
	{
		ExtentReportManager.getTest().log(Status.INFO, "Verify appropriate message appears while providing incorrect email format");
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		loginPage.LoginWithIncorrectEmailFormat();
	   	logAssert.assertTrue(loginPage.isElementDisplayed("Enter a valid email address"),"Correct error message appears");
	   	logAssert.assertAll();
	}	
	

	
	@Test(enabled=true, priority = 6, testName = "Verify invalid password using Email", description = "Verify invalid password using Email")
	public void verifyInvalidEmailPassword() throws InterruptedException {
		ExtentReportManager.getTest().log(Status.INFO, "Verify invalid password using Email");
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		loginPage.LoginInvalidPasswordEmail();
		logAssert.assertTrue(loginPage.isElementDisplayed("Alert"),"Password is invaild check the password");		
		loginPage.clickOk();
		ExtentReportManager.getTest().log(Status.PASS, "Message appears Login failed.Please check username and password ");
		logAssert.assertAll();
	}

		
	  //Verify whether user is able to login using Apple, and user should be able to explore the app
    
		@Test(enabled=true, priority = 7, testName = "Verify Login via AppleId", description = "Verify Login via AppleId")
		public void verifyAppleLogin() throws InterruptedException {
			
			ExtentReportManager.getTest().log(Status.INFO, "Verify Login via AppleId");
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
			logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
			logAssert.assertAll();	
		}


	//Verify whether user is able to login using Facebook, and user should be able to explore the app
    @Test(enabled=true, priority = 8, testName = "Verify Log in with Facebook", description = "Verify Log in with Facebook")
    public void verifyLoginWithFaceook() throws Exception
    {
    	ExtentReportManager.getTest().log(Status.INFO, "Verify Log in with Facebook");
    	loginPage.Takepic_Recvideo();
    	loginPage.skipIntroScreens();
        loginPage.LoginWithFacebook();
        FluentWait<WebDriver> wait = null;
		//	WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Live events']")));
		HomePage homePage= new HomePage(getDriver(),getPlatformName());
		logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
		logAssert.assertAll();
    }    
    
    
    //Verify whether user is able to login using Gmail, and user should be able to explore the app
    @Test(enabled=true, priority = 9, testName = "Verify Log in with Google", description = "Verify Log in with Google")
    public void verifyLoginWithGmail() throws Exception
    {
    	ExtentReportManager.getTest().log(Status.INFO, "Verify Log in with Google");
    	loginPage.Takepic_Recvideo();
    	loginPage.skipIntroScreens();
        loginPage.LoginWithGmail();
       	HomePage homePage= new HomePage(getDriver(),getPlatformName());
		logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
		logAssert.assertAll();
    }
	
	
	
	@Test(enabled=true, priority = 10, testName = "Verify password standard message apaars while providing incorrect password that doesn't met the password standard for login via Email option", description = "Verify password standard message apaars while providing incorrect password that doesn't met the password standard for login via Email option")
	public void verifyPasswordStdMessageInvalidEmailPassword() throws InterruptedException {
		ExtentReportManager.getTest().log(Status.INFO, "Verify password standard message apaars while providing incorrect password that doesn't met the password standard for login via Email option");
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		loginPage.LoginPasswordStandardMessageEmail();
		logAssert.assertTrue(loginPage.isElementDisplayed("Password length should be more or equal to 8"),"Password is invaild check the password, and password standard message appears");		
		logAssert.assertAll();	
	}
		

	
	

	
}
