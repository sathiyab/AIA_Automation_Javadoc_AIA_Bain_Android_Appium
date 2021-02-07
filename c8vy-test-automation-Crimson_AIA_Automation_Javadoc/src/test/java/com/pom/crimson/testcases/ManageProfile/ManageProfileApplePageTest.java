package com.pom.crimson.testcases.ManageProfile;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LogoutPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Manage profile</b> functionality via Apple Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class ManageProfileApplePageTest extends BaseFixture {
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{	
		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();		
	}

@Test(enabled=true, priority = 196, testName = "Verify switching from \"We are planning to have a baby\" to \"We are pregnant flow\" via Apple login", description = "Verify switching from \"We are planning to have a baby\" to \"We are pregnant flow\" with No Option via Apple login")
public void OnboardingPHBSwitchArchetypeviaApple() throws InterruptedException, IOException
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
ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
MP.clickProfile();
logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
MP.clickManageProfile();
logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage Profile screen appears");
MP.Click_planning_to_have_a_baby();
logAssert.assertTrue(MP.isElementDisplayed("What best describes you today"),"What best describes you today. screen appears");
MP.Scroll_ClickUpdateStatus();
logAssert.assertTrue(MP.isElementDisplayed("Choose what best describes you today"),"Choose what best describes you today. screen appears");
MP.ArchType_PRG_Click_My_partner_and_I_are_pregnant();
MP.Scroll_clickNext();
//logAssert.assertTrue(MP.isElementDisplayed("You’re switching to pregnant mode"),"You’re switching to pregnant mode screen appears");
ExtentReportManager.getTest().log(Status.INFO, "You're switching to pregnant mode");
logAssert.assertTrue(MP.isElementDisplayed("Do you want to continue?"),"Do you want to continue? appears");

MP.Scroll_Yes();
logAssert.assertTrue(MP.isElementDisplayed("Yes"),"Yes button appears");
MP.clickYes();
logAssert.assertTrue(MP.isElementDisplayed("Do you know your due date?"),"Do you know your due date? Screen appears");
MP.PRG_Do_you_know_your_due_date();
logAssert.assertTrue(MP.isElementDisplayed("Add due date"),"Add due date button is displayed");
MP.ClickAddDueDate();
logAssert.assertTrue(MP.isElementDisplayed("My partner and I are pregnant"),"Pregnant flow is loaded.");
MP.clickProfile();
LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
LP.VerifyLogoutExists();
logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
LP.Logout_App();
logAssert.assertAll();
	}

@Test(enabled=true, priority = 197, testName =" Verify switching from \"My partner and I are Pregnant\" to \"I have a new born\"via Apple", description = "Verify switching from \"My partner and I are Pregnant\" to \"I have a new born\"via Apple")
public void OnboardingPRGSwitchArchetypedMaleFlowviaApple() throws InterruptedException, IOException
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
ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
MP.clickProfile();
logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
MP.clickManageProfile();
logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage Profile screen appears");
MP.PRG_Click_My_partner_and_I_are_pregnant();
logAssert.assertTrue(MP.isElementDisplayed("What best describes you today"),"What best describes you today. screen appears");
MP.Scroll_ClickUpdateStatus();
logAssert.assertTrue(MP.isElementDisplayed("Choose what best describes you today"),"Choose what best describes you today. screen appears");
MP.Click_NB_I_have_a_newborn();
MP.Scroll_clickNext();
//logAssert.assertTrue(MP.isElementDisplayed("You’re switching to newborn mode"),"You’re switching to new born mode screen appears");
ExtentReportManager.getTest().log(Status.INFO, "You’re switching to newborn mode");
logAssert.assertTrue(MP.isElementDisplayed("Do you want to continue?"),"Do you want to continue? appears");

MP.Scroll_Yes();
logAssert.assertTrue(MP.isElementDisplayed("Yes"),"Yes button appears");
MP.clickYes();
//logAssert.assertTrue(MP.isElementDisplayed("Create a child profile for Crimson to learn and understand your new development journey."),"Description is correct");
logAssert.assertTrue(MP.isElementDisplayed("Create a child profile for ALive to learn and understand your new development journey."),"Description is correct");
logAssert.assertTrue(MP.isElementDisplayed("Add child profile"), "Verify add child profile exists");
MP.laterButton();
logAssert.assertTrue(MP.isElementDisplayed("I have a newborn (0 - 1 year old)"),"I have a newborn (0 - 1 year old) flow is loaded.");
MP.clickProfile();
LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
LP.VerifyLogoutExists();
logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
LP.Logout_App();
logAssert.assertAll();
}

	

 @Test(enabled=true, priority = 198, testName =" Verify switching from \"I have a new born\" to \"I have a child/children\"via Apple", description = "Verify switching from \"My partner and I are Pregnant\" to \"I have a new born\"via Apple")
public void SwitchArchetyped_NB_to_CC_viaApple() throws InterruptedException, IOException
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
ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
MP.clickProfile();
logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage Profile screen appears");
MP.clickManageProfile();
MP.I_have_a_newborn();
logAssert.assertTrue(MP.isElementDisplayed("What best describes you today"),"What best describes you today. screen appears");
MP.Scroll_ClickUpdateStatus();
Assert.assertTrue(MP.isElementDisplayed("Choose what best describes you today"),"Choose what best describes you today. screen appears");
MP.Click_I_have_a_child_children();
MP.Scroll_clickNext();

//logAssert.assertTrue(MP.isElementDisplayed("You’re switching to experienced parent mode"),"You’re switching to experienced parent mode screen appears");
ExtentReportManager.getTest().log(Status.INFO, "You’re switching to experienced parent mode");
logAssert.assertTrue(MP.isElementDisplayed("Do you want to continue?"),"Do you want to continue? appears");


MP.Scroll_Yes();
logAssert.assertTrue(MP.isElementDisplayed("Yes"),"Yes button appears");
MP.clickYes();
logAssert.assertTrue(MP.isElementDisplayed("How many kids do you have?"),"How many kids do you have? Screen appears");
MP.SelectNumberOfKids();
MP.click2();
MP.clickNext();
logAssert.assertTrue(MP.isElementDisplayed("Great, enter your children’s date of birth"),"Great, enter your children’s date of birth Screen appears");
MP.How_old_are_your_kids_2();	
MP.ClickDone();
Assert.assertTrue(MP.isElementDisplayed("I have a child/children (older than 1 year old)"),"I have a child/children (older than 1 year old) flow is loaded.");
MP.clickProfile();
LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
LP.VerifyLogoutExists();
logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
LP.Logout_App();
logAssert.assertAll();
}

 
 @Test(enabled=true, priority = 199, testName ="Verify switching from I have a child children to We are planning to have a baby via Apple", description = "Verify switching from \"My partner and I are Pregnant\" to \"I have a new born\"via Apple")
	public void SwitchArchetyped_CC_to_PHB_viaApple() throws InterruptedException, IOException
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
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
	logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage Profile screen appears");
	MP.clickManageProfile();
	MP.Click_I_have_a_child_children();
	logAssert.assertTrue(MP.isElementDisplayed("What best describes you today"),"What best describes you today. screen appears");
	MP.Scroll_ClickUpdateStatus();
	Assert.assertTrue(MP.isElementDisplayed("Choose what best describes you today"),"Choose what best describes you today. screen appears");
	MP.Click_Archtype_planning_to_have_a_baby();
	MP.Scroll_clickNext();
	//logAssert.assertTrue(MP.isElementDisplayed("You’re switching to planning a baby mode"),"You’re switching to planning a baby mode screen appears");
	ExtentReportManager.getTest().log(Status.INFO, "You’re switching to planning a baby mode");
	logAssert.assertTrue(MP.isElementDisplayed("Do you want to continue?"),"Do you want to continue? appears");
	
	MP.Scroll_Yes();
	 logAssert.assertTrue(MP.isElementDisplayed("Yes"),"Yes button appears");
	 MP.clickYes();
	logAssert.assertTrue(MP.isElementDisplayed("Is this your first child?"),"Is this your first child?");
	MP.clickYes();
	MP.ClickDone();
	logAssert.assertTrue(MP.isElementDisplayed("My partner and I are planning to have a baby"),"My partner and I are planning to have a baby flow is loaded.");
    MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();
	}

	

	 @Test(enabled=true, priority = 200, testName = "Verify Update Archetype for We are planning to have a baby via Apple", description = "Verify Update Archetype for We are planning to have a baby via Apple")
		public void OnboardingPHBUpdateArchetypedMaleFlowviaApple() throws InterruptedException, IOException
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
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	    MP.clickProfile();
	    logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
	    MP.clickManageProfile();
	    logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage Profile screen appears");
	    MP.Click_planning_to_have_a_baby();
	
	    logAssert.assertTrue(MP.isElementDisplayed("What best describes you today"),"What best describes you today. screen appears");
	    MP.Edit();
	    MP.PHB_UpdateStatus();
	    MP.ClickSave();
	    logAssert.assertTrue(MP.isElementDisplayed("My partner and I are planning to have a baby"),"Planning to have a baby flow is loaded.");
	    MP.clickProfile();
	    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
	    LP.VerifyLogoutExists();
	    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
	    LP.Logout_App();
	    logAssert.assertAll();
	    
		}

	}
