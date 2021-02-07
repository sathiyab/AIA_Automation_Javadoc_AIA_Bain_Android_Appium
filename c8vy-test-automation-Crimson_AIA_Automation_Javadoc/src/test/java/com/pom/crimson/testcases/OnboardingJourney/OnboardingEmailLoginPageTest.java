package com.pom.crimson.testcases.OnboardingJourney;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.pom.crimson.util.Constants;

/**
 * This class contains test methods to test <b>Onboarding Journey</b> functionality via Email Login 
 * 
 * @author Balaji.Sridharan 
 */
public class OnboardingEmailLoginPageTest extends BaseFixture {

	
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
	
	
	@Test(enabled=true, priority = 21, testName = "Verify Onboarding journey for Planning to have a Baby via eMail - Female Flow", description = "Verify Onboarding journey for Plnning to have a Baby via eMail - Female Flow")
	public void OnboardingPHBjourney_eMail_FemaleFlow() throws InterruptedException, IOException
	{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for Planning to have a Baby via eMail - Female Flow");
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
	loginPage.LoginWithEmail(Constants.EmailTestUser,Constants.EmailTestPassword);	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	//OBP.consentAcceptTest();
	
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
	
	@Test(enabled=true, priority = 22, testName = "Verify Onboarding journey for My partner and I are pregnant via eMail - Female Flow", description = "Verify Onboarding journey for Plnning to have a Baby via eMail - Female Flow")
	public void OnboardingPRGjourney_eMail_Femaleflow() throws InterruptedException, IOException
	{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for My partner and I are pregnant via eMail - Female Flow");
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
	loginPage.LoginWithEmail(Constants.EmailTestUser,Constants.EmailTestPassword);	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	
	@Test(enabled=true, priority = 23, testName = "Verify Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Female Flow", description = "Verify Onboarding journey for  I have a newborn (0 - 1 year old) via eMail - Female Flow")
	public void OnboardingNBBjourney_eMail_FemaleFlow() throws InterruptedException, IOException
	{ 
		loginPage.Takepic_Recvideo();
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Female Flow");
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	        logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	

	@Test(enabled=true, priority = 24, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) via eMail - Female Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) via eMail - Female Flow")
	public void OnboardingCCjourney_eMail_FemaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) via eMail - Female Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	
	
	@Test(enabled=true, priority = 25, testName = "Verify Onboarding journey for Planning to have a Baby via eMail - Male Flow", description = "Verify Onboarding journey for Plnning to have a Baby via eMail - Male Flow")
	public void OnboardingPHBjourney_eMail_MaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for Planning to have a Baby via eMail - Male Flow");
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
		loginPage.LoginWithEmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
		OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	   OBP.GenderSelectionMale();
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
		
	@Test(enabled=true, priority = 26, testName = "Verify Onboarding journey for My partner and I are pregnant via eMail - Male Flow", description = "Verify Onboarding journey for Plnning to have a Baby via eMail - Male Flow")
	public void OnboardingPRGjourney_eMail_MaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for My partner and I are pregnant via eMail - Male Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePRGMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Male"),"are you male or female? Screen appears");
	OBP.GenderSelectionMale();
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
	
	
	@Test(enabled=true, priority = 27, testName = "Verify Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Male Flow ", description = "Verify Onboarding journey for  I have a newborn (0 - 1 year old) via eMail - Male Flow")
	public void OnboardingNBBjourney_eMail_MaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Male Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	
	@Test(enabled=true, priority = 28, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) via eMail - Male Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) via eMail - Male Flow")
	public void OnboardingCCjourney_eMail_MaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) via eMail - Male Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	
	@Test(enabled=true, priority =29, testName = "Verify Twins option is visible and appearing for Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Female Flow", description = "Verify Onboarding journey for  I have a newborn (0 - 1 year old) via eMail - Female Flow")
	public void OnboardingNBBTwins_eMail_FemaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Twins option is visible and appearing for Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Female Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNameNBFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionFemale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a newborn (0 - 1 year old)"),"I have a newborn (0 - 1 year old) Screen appears");
	OBP.Click_NB_I_have_a_newborn();
	logAssert.assertTrue(OBP.isElementDisplayed("Is your child a boy or a girl?"),"Is your child a boy or a girl? Screen appears");
	OBP.Twins();
	logAssert.assertTrue(OBP.isElementDisplayed("The primary baby profile for your account is automatically created using the information that was entered during the sign up process. You can create additional baby profiles from the Manage Profiles screen."),"Twins info appears");
	OBP.ContinueBtn_Click();
	logAssert.assertTrue(OBP.isElementDisplayed("Is your child a boy or a girl?"),"Is your child a boy or a girl? Screen appears");
	logAssert.assertAll();
	}
	
	@Test(enabled=true, priority = 30, testName = "Verify is this your first child in Onboarding journey for Planning to have a Baby  - Female Flow", description = "Verify is this your first child in Onboarding journey for Planning to have a Baby  - Female Flow")
	public void OnboardingPHBIsThisYourFirstChildFemaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify is this your first child in Onboarding journey for Planning to have a Baby  - Female Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	OBP.clickNo();
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

@Test(enabled=true, priority = 31, testName = "Verify Join Group is successful for Onboarding journey for I have a child/children (older than 1 year old) via eMail - Female Flow", description = "Verify Join Group is successful for Onboarding journey for I have a child/children (older than 1 year old) via eMail - Female Flow")
public void OnboardingCCjourney_JoinGoup_eMail_FemaleFlow() throws InterruptedException, IOException
{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify Join Group is successful for Onboarding journey for I have a child/children (older than 1 year old) via eMail - Female Flow");
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
loginPage.LoginWithEmail();	
ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
OBP.ClickTab_showcase();
logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
OBP.ClickOnboarding();
logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
OBP.consentAccept();
OBP.consentContinue();
logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
OBP.enterNameCCFemale();
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
OBP.JoinGroup();
HomePage homePage= new HomePage(getDriver(),getPlatformName());
logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
logAssert.assertAll();
}

@Test(enabled=true, priority = 32, testName = "Verify different conceive options in Onboarding journey for Planning to have a Baby via eMail - Female Flow", description = "Verify different conceive options in Onboarding journey for Planning to have a Baby via eMail - Female Flow")
	public void OnboardingPHBjourneyConceiveOptions_eMail_FemaleFlow() throws InterruptedException, IOException
	{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify different conceive options in Onboarding journey for Planning to have a Baby via eMail - Female Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_Less_than_three_months();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.Previous_screen_Validation();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_Less_than_nine_months();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.Previous_screen_Validation();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_about_one_year();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.Previous_screen_Validation();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_more_than_one_year();
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

@Test(enabled=true, priority = 33, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via eMail - Female Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via eMail - Female Flow")
	public void OnboardingCCjourney_2kids_eMail_FemaleFlow() throws InterruptedException, IOException
	{ 
	ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via eMail - Female Flow");
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
	loginPage.LoginWithEmail();	
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

	@Test(enabled=true, priority = 34, testName = "Verify Past due date validation in Onboarding journey for My partner and I are pregnant via eMail - Female Flow", description = "Verify Onboarding journey for Plnning to have a Baby via eMail - Female Flow")
	public void OnboardingPRGPastDueDatejourney_eMail_Femaleflow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Past due date validation in Onboarding journey for My partner and I are pregnant via eMail - Female Flow");
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
	loginPage.LoginWithEmail();	
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
	OBP.PRG_Do_you_know_your_Past_due_date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String date_1 = sdf.format(new Date());
	System.out.println (date_1);
	logAssert.assertTrue(OBP.isElementDisplayed(date_1),"Due date can't be set to Past date");
	logAssert.assertAll();
	}
	
	
	
	@Test(enabled=true, priority = 35, testName = "Verify Onboarding journey for why only these two - Female Flow", description = "Verify Onboarding journey for why only these two - Female Flow")
	public void OnboardingwhyOnlyTheseTwoFemaleFlow() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for why only these two - Female Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePHBFemale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Why only these two?"),"why only these two? Screen appears");
	OBP.PHB_Why_only_these_two();
	logAssert.assertTrue(OBP.isElementDisplayed("Crimson is still learning about other gender identities and at present can only differentiate between male and female."),"Why only these two info appears");
	OBP.ContinueBtn_Click();
	logAssert.assertAll();
	}
     
     
    @Test(enabled=true, priority = 36, testName = "Verify Twins option is visible and appearing for Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Male Flow", description = "Verify Onboarding journey for  I have a newborn (0 - 1 year old) via eMail - Male Flow")
	public void OnboardingNBBTwins_eMail_MaleFlow() throws InterruptedException, IOException
	{ 
    	ExtentReportManager.getTest().log(Status.INFO, "Verify Twins option is visible and appearing for Onboarding journey for I have a newborn (0 - 1 year old) via eMail - Male Flow");
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
	loginPage.LoginWithEmail();	
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
	OBP.GenderSelectionMale();
	logAssert.assertTrue(OBP.isElementDisplayed("I have a newborn (0 - 1 year old)"),"I have a newborn (0 - 1 year old) Screen appears");
	OBP.Click_NB_I_have_a_newborn();
	logAssert.assertTrue(OBP.isElementDisplayed("Is your child a boy or a girl?"),"Is your child a boy or a girl? Screen appears");
	OBP.Twins();
	logAssert.assertTrue(OBP.isElementDisplayed("The primary baby profile for your account is automatically created using the information that was entered during the sign up process. You can create additional baby profiles from the Manage Profiles screen."),"Twins info appears");
	OBP.ContinueBtn_Click();
	logAssert.assertTrue(OBP.isElementDisplayed("Is your child a boy or a girl?"),"Is your child a boy or a girl? Screen appears");
	logAssert.assertAll();
	}
     
     @Test(enabled=true, priority = 37, testName = "Verify Join Group is successful for Onboarding journey for I have a child/children (older than 1 year old) via eMail - Male Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via eMail - Male Flow")
public void OnboardingCCjourney_JoinGoup_eMail_MaleFlow() throws InterruptedException, IOException
{ 
    	 ExtentReportManager.getTest().log(Status.INFO, "Verify Join Group is successful for Onboarding journey for I have a child/children (older than 1 year old) via eMail - Male Flow");
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
loginPage.LoginWithEmail();	
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
OBP.JoinGroup();
HomePage homePage= new HomePage(getDriver(),getPlatformName());
logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
logAssert.assertAll();
}
     
 @Test(enabled=true, priority = 38, testName = "Verify is this your first child in Onboarding journey for Planning to have a Baby  - Male Flow", description = "Verify is this your first child in Onboarding journey for Planning to have a Baby - Male Flow")
	public void OnboardingPHBIsThisYourFirstChildMaleFlow() throws InterruptedException, IOException
	{ 
	 ExtentReportManager.getTest().log(Status.INFO, "Verify is this your first child in Onboarding journey for Planning to have a Baby  - Male Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
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
	OBP.GenderSelectionMale();
	logAssert.assertTrue(OBP.isElementDisplayed("My partner and I are planning to have a baby"),"Planning to Have a Baby Screen appears");
	OBP.Click_My_partner_and_I_are_planning_to_have_a_baby();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	OBP.PHB_How_long_have_you_been_trying_to_conceive();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.clickNo();
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

     @Test(enabled=true, priority = 39, testName = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via eMail - Male Flow", description = "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via eMail - Male Flow")
	public void OnboardingCCjourney_2kids_eMail_MaleFlow() throws InterruptedException, IOException
	{ 
    	 ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for I have a child/children (older than 1 year old) for maore than 1 kids selection via eMail - Male Flow");
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
	loginPage.LoginWithEmail();	
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
     
    
     
    @Test(enabled=true, priority = 40, testName = "Verify Past due date validation in Onboarding journey for My partner and I are pregnant via eMail - Female Flow", description = "Verify Onboarding journey for Plnning to have a Baby via eMail - Female Flow")
	public void OnboardingPRGPastDueDatejourney_eMail_Maleflow() throws InterruptedException, IOException
	{ 
    	ExtentReportManager.getTest().log(Status.INFO, "Verify Past due date validation in Onboarding journey for My partner and I are pregnant via eMail - Female Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePRGMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionMale();
	logAssert.assertTrue(OBP.isElementDisplayed("My partner and I are pregnant"),"My partner and I are pregnant Screen appears");
	OBP.PRG_Click_My_partner_and_I_are_pregnant();
	logAssert.assertTrue(OBP.isElementDisplayed("Do you know your due date?"),"Do you know your due date? Screen appears");
	OBP.PRG_Do_you_know_your_Past_due_date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String date_1 = sdf.format(new Date());
	System.out.println (date_1);
	logAssert.assertTrue(OBP.isElementDisplayed(date_1),"Due date can't be set to Past date");
	logAssert.assertAll();
	} 
     
     @Test(enabled=true, priority = 41, testName = "Verify different conceive options in Onboarding journey for Planning to have a Baby via eMail - Male Flow", description = "Verify different conceive options in Onboarding journey for Plnning to have a Baby via eMail - Male Flow")
	public void OnboardingPHBjourneyConceiveOptions_eMail_MaleFlow() throws InterruptedException, IOException
	{ 
    	 ExtentReportManager.getTest().log(Status.INFO, "Verify different conceive options in Onboarding journey for Planning to have a Baby via eMail - Male Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(),getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePHBMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Female"),"are you male or female? Screen appears");
	OBP.GenderSelectionMale();
	logAssert.assertTrue(OBP.isElementDisplayed("My partner and I are planning to have a baby"),"Planning to Have a Baby Screen appears");
	OBP.Click_My_partner_and_I_are_planning_to_have_a_baby();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_Less_than_three_months();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.Previous_screen_Validation();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_Less_than_nine_months();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.Previous_screen_Validation();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_about_one_year();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.Previous_screen_Validation();
	logAssert.assertTrue(OBP.isElementDisplayed("How long have you been trying to conceive?"),"How long have you been trying to conceive? Screen appears");
	
	OBP.PHB_How_long_have_you_been_trying_to_conceive_more_than_one_year();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Is this your first child?"),"Is this your first child? Screen appears");
	OBP.clickYes();
	logAssert.assertTrue(OBP.isElementDisplayed("Select date of birth"),"Lastly, what's your date of birth? Screen appears");
	OBP.Lastly_whats_your_date_of_birth();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Choose your interests."),"Choose your interests. Screen appears");
	OBP.PHB_Select_Interests();
	logAssert.assertTrue(OBP.isElementDisplayed("Join group"),"Join Group option appears");
	//logAssert.assertTrue(OBP.isElementDisplayed("I'll do this later"),"I'll do this later option appears");
	OBP.laterButton();
	HomePage homePage= new HomePage(getDriver(),getPlatformName());
	logAssert.assertTrue(homePage.isElementDisplayed("Live events"),"Home page should be loaded.");
	logAssert.assertAll();
	}
         
     
    @Test(enabled=true, priority = 42, testName = "Verify Onboarding journey for why only these two - Male Flow", description = "Verify Onboarding journey for why only these two - Male Flow")
	public void OnboardingwhyOnlyTheseTwoMaleFlow() throws InterruptedException, IOException
	{ 
    ExtentReportManager.getTest().log(Status.INFO, "Verify Onboarding journey for why only these two - Male Flow");
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
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for tab_Showcase to locate");
	OnboardingPage OBP = new OnboardingPage(getDriver(), getPlatformName());
	OBP.ClickTab_showcase();
	logAssert.assertTrue(OBP.isElementDisplayed("OnboardingScreens"),"OnboardingScreens menu appears");
	OBP.ClickOnboarding();
	logAssert.assertTrue(OBP.isElementDisplayed("Confirm the following to continue"),"Consent Screen appears");
	OBP.consentAccept();
	OBP.consentContinue();
	logAssert.assertTrue(OBP.isElementDisplayed("What should we call you?"),"What should we call you? Screen appears");
	OBP.enterNamePHBMale();
	OBP.clickNext();
	logAssert.assertTrue(OBP.isElementDisplayed("Why only these two?"),"why only these two? Screen appears");
	OBP.PHB_Why_only_these_two();
	logAssert.assertTrue(OBP.isElementDisplayed("Crimson is still learning about other gender identities and at present can only differentiate between male and female."),"Why only these two info appears");
	OBP.ContinueBtn_Click();
	logAssert.assertAll();	
}

}