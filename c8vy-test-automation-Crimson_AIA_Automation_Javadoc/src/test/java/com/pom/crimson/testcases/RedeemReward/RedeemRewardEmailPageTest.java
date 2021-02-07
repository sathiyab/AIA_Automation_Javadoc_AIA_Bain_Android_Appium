package com.pom.crimson.testcases.RedeemReward;

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
import com.pom.crimson.pages.RedeemRewardPage;
import com.pom.crimson.pages.TermsOfUsePage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test <b>Redeem reward</b> via Email Login 
 * 
 * @author Balaji.Sridharan 
 */
public class RedeemRewardEmailPageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();	
	}
	
	@Test(enabled=true, priority = 163, testName = "Verify able to submit Redeem Reward for Login via eMail", description = "Verify able to submit Redeem Reward for Login via eMail")
	public void VerifyRedeemreward_LoginViaEmail() throws InterruptedException, IOException
	{ 
		ExtentReportManager.getTest().log(Status.INFO, "Verify able to submit Redeem Reward for Login via eMail");
		loginPage.Takepic_Recvideo();
		 if (loginPage.isElementDisplayed("Skip"))
		    {
			 loginPage.skipIntroScreens();
		       
		        }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
	loginPage.LoginWithEmail();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
	ProfilePage PP=new ProfilePage(getDriver(),getPlatformName());
	logAssert.assertTrue(PP.isElementDisplayed("Profile"),"Profile is available");
	PP.clickProfile();
	RedeemRewardPage RR=new RedeemRewardPage(getDriver(),getPlatformName());
	logAssert.assertTrue(RR.isElementDisplayed("Rewards"),"Rewards is available");
	RR.RewardsMenu();
	logAssert.assertTrue(RR.isElementDisplayed("Rewards"),"Heading shows Rewards");
	RR.Scroll_ClickRedeemRewardmenu();
	logAssert.assertTrue(RR.isElementDisplayed("Provide your personal details to redeem this policy"),"Heading is correct");
	RR.Scroll_Next();
	logAssert.assertTrue(RR.isElementDisplayed("Next"),"Next button is displayed");
	//RR.Click_date_of_birth();
	//logAssert.assertTrue(RR.isElementDisplayed("Date of birth"),"Date of birth is selected");
	//RR.EnterNationalId();
	//logAssert.assertTrue(RR.isElementDisplayed("National ID number"),"National ID number is selected");
	RR.Click_Next();
	logAssert.assertTrue(RR.isElementDisplayed("Confirm details"),"Confirm details is displayed");
	logAssert.assertTrue(RR.isElementDisplayed("Submit"),"Submit button details is displayed");
	RR.ClickCheckBox();
	RR.Click_Submit();
	logAssert.assertTrue(RR.isElementDisplayed("Your application has been submitted successfully"),"Your application has been submitted successfully details is displayed");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
	logAssert.assertAll();	
	}
	
		
}