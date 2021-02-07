package com.pom.crimson.testcases.Intro;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.IntroPage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Intro Screen</b> Navigation and Validations 
 * 
 * @author Balaji.Sridharan 
 */	
public class IntroPageTest extends BaseFixture{
	
	IntroPage introPage;
	HomePage homePage;	
	LogAssert logAssert;
		
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		introPage=new IntroPage(getDriver(),getPlatformName());
	    logAssert = new LogAssert();
	}	
	
	@Test(enabled=true, priority = 1, testName = "Verify Skip Button in Intro Screens", description = "Verify Button in Intro Screens")
	public void verifySkipButtonInIntroScreen() throws Exception
	{
		
		ExtentReportManager.getTest().log(Status.INFO, "Verify Skip Button in Intro Screens");
		LoginPage lp=new LoginPage(getDriver(),getPlatformName());
		lp.Takepic_Recvideo();
		
		 if (introPage.isElementDisplayed("Skip"))
		    {
			 	introPage.NavigateIntroScreen();		
				//introPage.NavigateIntroScreen();
				//introPage.NavigateIntroScreen();
				introPage.skipIntroScreens();
				logAssert.assertTrue(introPage.isElementDisplayed("Log in or sign up"),"Log in or sign up page should be loaded.");	
		        }
		    else
		    {
		    	logAssert.assertTrue(introPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    	logAssert.assertTrue(introPage.isElementDisplayed("Log in or sign up"),"Log in or sign up page should be loaded.");	
		    }
					
		logAssert.assertAll();
	}
	
	
	
	@Test(enabled=true, priority = 2, testName = "Verify Intro Screens", description = "Verify Intro Screens")
	public void verifyIntroScreen() throws Exception
	{
		ExtentReportManager.getTest().log(Status.INFO, "Verify Intro Screens");
		LoginPage lp=new LoginPage(getDriver(),getPlatformName());
		lp.Takepic_Recvideo();
		 if (introPage.isElementDisplayed("Skip"))
		    {
		logAssert.assertTrue(introPage.isElementDisplayed("Connect with specialist doctors and nurses"),"Intro screen1 heading is validated");
		logAssert.assertTrue(introPage.isElementDisplayed("Consult with experienced healthcare professionals via video chat"),"Intro screen1 description is validated");
		introPage.NavigateIntroScreen();
		logAssert.assertTrue(introPage.isElementDisplayed("Join groups, make friends and learn from each other"),"Intro screen2 heading is validated");
		logAssert.assertTrue(introPage.isElementDisplayed("Ask questions and share experiences of your wellness journey via virtual meet ups and chat sessions"),"Intro screen2 description is validated");
		introPage.NavigateIntroScreen();
		logAssert.assertTrue(introPage.isElementDisplayed("Participate in live events"),"Intro screen3 heading is validated");
		logAssert.assertTrue(introPage.isElementDisplayed("Attend live video sessions hosted by leading experts in health and wellness"),"Intro screen3 description is validated");
		introPage.NavigateIntroScreen();
		logAssert.assertTrue(introPage.isElementDisplayed("Saving memories"),"Intro screen4 heading is validated");
		logAssert.assertTrue(introPage.isElementDisplayed("Capture precious moments and milestones in your wellness journey"),"Intro screen4 description is validated");
		introPage.NavigateIntroScreen();
		logAssert.assertTrue(introPage.isElementDisplayed("Personalized for you"),"Intro screen5 heading is validated");
		logAssert.assertTrue(introPage.isElementDisplayed("Personalized content recommended specially for you and your family"),"Intro screen5 description is validated");
		introPage.NavigateIntroScreen();
		logAssert.assertTrue(introPage.isElementDisplayed("Redeem rewards"),"Intro screen6 heading is validated");
		logAssert.assertTrue(introPage.isElementDisplayed("Get protection plan for 1 year from AIA + teleconsultation with Samitivej"),"Intro screen6 description is validated");
		introPage.CompleteIntroScreenNavigation();
		Thread.sleep(1000);
		logAssert.assertTrue(introPage.isElementDisplayed("Log in or sign up"),"Log in or sign up page should be loaded.");
		    }
		    else
		    {
		    	logAssert.assertTrue(introPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    	logAssert.assertTrue(introPage.isElementDisplayed("Log in or sign up"),"Log in or sign up page should be loaded.");	
		    }
		logAssert.assertAll();
	}


}
