package com.pom.crimson.testcases.OnboardingJourney;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.OnboardingPage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Onboarding Journey</b> functionality via Google Login 
 * 
 * @author Balaji.Sridharan 
 */
public class OnboardingGoogleLoginPageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	//OnboardingPage onboardingPage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}
	
	
	@Test(enabled=true, priority = 51, testName = "Verify Onboarding journey for Planning to have a Baby via Google - Female Flow", description = "Verify Onboarding journey for Planning to have a Baby via Google - Female Flow")
	public void OnboardingPHBjourney_FemaleFlow_Google() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for Planning to have a Baby via Google - Female Flow");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePHBFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("My partner and I are planning to have a baby"),"Planning to Have a Baby Screen appears");
	OBP.Click_My_partner_and_I_are_planning_to_have_a_baby();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	OBP.PHB_How_long_have_you_been_trying_to_conceive();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.clickYes();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	   }

    @Test(enabled=true, priority = 52, testName = "Verify Onboarding journey for My partner and I are pregnant via Google - Female Flow", description = "Verify Onboarding journey for My partner and I are pregnant via Google - Female Flow")
	public void OnboardingPRGjourney_Femaleflow_Google() throws InterruptedException, IOException
	{ 
    	ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for My partner and I are pregnant via Google - Female Flow");
    	loginPage.Takepic_Recvideo();
    	if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePRGFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("My partner and I are pregnant"),"My partner and I are pregnant Screen appears");
	OBP.PRG_Click_My_partner_and_I_are_pregnant();
	logAssert.assertTrue(OBP.isElementDisplayed("Do you know your due date?"),"Do you know your due date? Screen appears");
	OBP.PRG_Do_you_know_your_due_date();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.clickYes();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	}

@Test(enabled=true, priority = 53, testName = "Verify Onboarding journey for I have a newborn (0 - 1 year old) via Google - Female Flow", description = "Verify Onboarding journey for  I have a newborn (0 - 1 year old) via Google - Female Flow")
	public void OnboardingNBBjourney_Femaleflow_Google() throws InterruptedException, IOException
	{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a newborn (0 - 1 year old) via Google - Female Flow");
	loginPage.Takepic_Recvideo();
	loginPage.skipIntroScreens();
	if (loginPage.isElementDisplayed("Skip"))
    {
	 loginPage.skipIntroScreens();
        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
        }
    else
    {
    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
    }
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePHBFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a newborn (0 - 1 year old)"),"I have a newborn (0 - 1 year old) Screen appears");
	OBP.Click_NB_I_have_a_newborn();
	logAssert.assertTrue(OBP.isElementDisplayed("Is your child a boy or a girl?"),"Is your child a boy or a girl? Screen appears");
	OBP.selectBoy();
	logAssert.assertTrue(OBP.isElementDisplayed("Great, enter your baby’s date of birth"),"Great, enter your baby’s date of birth Screen appears");
	OBP.NB_Enter_your_baby_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	}
	
	
	@Test(enabled=true, priority = 54, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) via Google - Female Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) via Google - Female Flow")
	public void OnboardingCCjourney_Femaleflow_Google() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) via Google - Female Flow");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNameCCFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a child/children (older than 1 year old)"),"I have a child/children (older than 1 year old) Screen appears");
	OBP.CC_Click_I_have_a_child_children();
	logAssert.assertTrue(OBP.isElementDisplayed("How many kids do you have?"),"How many kids do you have? Screen appears");
	OBP.SelectNumberOfKids();
	OBP.click1();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Great, enter your children’s date of birth"),"Great, enter your children’s date of birth Screen appears");
	OBP.How_old_are_your_kids();	
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	}

@Test(enabled=true, priority = 55, testName = "Verify Onboarding journey for Planning to have a Baby via Google - Male Flow", description = "Verify Onboarding journey for Planning to have a Baby via Google - Male Flow")
	public void OnboardingPHBjourney_MaleFlow_Google() throws InterruptedException, IOException
	{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for Planning to have a Baby via Google - Male Flow");
	loginPage.Takepic_Recvideo();
	loginPage.skipIntroScreens();
	if (loginPage.isElementDisplayed("Skip"))
    {
	 loginPage.skipIntroScreens();
        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
        }
    else
    {
    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
    }	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePHBMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Male"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("My partner and I are planning to have a baby"),"Planning to Have a Baby Screen appears");
	OBP.Click_My_partner_and_I_are_planning_to_have_a_baby();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	OBP.PHB_How_long_have_you_been_trying_to_conceive();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.clickYes();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
}

	
	@Test(enabled=true, priority = 56, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) via Google - Male Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) via Google - Male Flow")
	public void OnboardingCCjourney_Google_MaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) via Google - Male Flow");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNameCCMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionMale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a child/children (older than 1 year old)"),"I have a child/children (older than 1 year old) Screen appears");
	OBP.CC_Click_I_have_a_child_children();
	logAssert.assertTrue(OBP.isElementDisplayed("How many kids do you have?"),"How many kids do you have? Screen appears");
	OBP.SelectNumberOfKids();
	OBP.click1();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Great, enter your children’s date of birth"),"Great, enter your children’s date of birth Screen appears");
	OBP.How_old_are_your_kids();	
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	}
	
	@Test(enabled=true, priority = 57, testName = "Verify Onboarding journey for I have a newborn (0 - 1 year old) via Google - Male Flow ", description = "Verify Onboarding journey for  I have a newborn (0 - 1 year old) via Google - Male Flow")
	public void OnboardingNBBjourney_Maleflow_Google() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a newborn (0 - 1 year old) via Google - Male Flow");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamenNBMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Male"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a newborn (0 - 1 year old)"),"I have a newborn (0 - 1 year old) Screen appears");
	OBP.Click_NB_I_have_a_newborn();
	logAssert.assertTrue(OBP.isElementDisplayed("Is your child a boy or a girl?"),"Is your child a boy or a girl? Screen appears");
	OBP.selectBoy();
	logAssert.assertTrue(OBP.isElementDisplayed("Great, enter your baby’s date of birth"),"Great, enter your baby’s date of birth Screen appears");
	OBP.NB_Enter_your_baby_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	}
	
	
	@Test(enabled=true, priority = 58, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via Google - Male Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via Google - Male Flow")
	public void OnboardingCCjourney_2kids_Google_MaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via Google - Male Flow");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    };
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNameCCMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Male"),"are you male or female? Screen appears");
	OBP.GenderSelectionMale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a child/children (older than 1 year old)"),"I have a child/children (older than 1 year old) Screen appears");
	OBP.CC_Click_I_have_a_child_children();
	logAssert.assertTrue(OBP.isElementDisplayed("How many kids do you have?"),"How many kids do you have? Screen appears");
	OBP.SelectNumberOfKids();
	OBP.click2();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Great, enter your children’s date of birth"),"Great, enter your children’s date of birth Screen appears");
	OBP.How_old_are_your_kids_2();	
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	}
	
	@Test(enabled=true, priority = 59, testName = "Verify Onboarding journey for My partner and I are pregnant Via Google - Male Flow", description = "Verify Onboarding journey for My partner and I are pregnant Via Google - Male Flow")
	public void OnboardingPRGjourney_Maleflow_Google() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for My partner and I are pregnant Via Google - Male Flow");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePRGFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("My partner and I are pregnant"),"My partner and I are pregnant Screen appears");
	OBP.PRG_Click_My_partner_and_I_are_pregnant();
	logAssert.assertTrue(OBP.isElementDisplayed("Do you know your due date?"),"Do you know your due date? Screen appears");
	OBP.PRG_Do_you_know_your_due_date();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.clickYes();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");	
	logAssert.assertAll();
	}
	
	@Test(enabled=true, priority = 60, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) for more than 1 kids selection via Google - Female Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via Google - Female Flow")
	public void OnboardingCCjourney_2kids_Google_FemaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) for more than 1 kids selection via Google - Female Flow");
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithGmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),  getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNameCCFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a child/children (older than 1 year old)"),"I have a child/children (older than 1 year old) Screen appears");
	OBP.CC_Click_I_have_a_child_children();
	logAssert.assertTrue(OBP.isElementDisplayed("How many kids do you have?"),"How many kids do you have? Screen appears");
	OBP.SelectNumberOfKids();
	OBP.click2();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Great, enter your children’s date of birth"),"Great, enter your children’s date of birth Screen appears");
	OBP.How_old_are_your_kids_2();	
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group Screen appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");	
	logAssert.assertAll();
	}
	
}