package com.pom.crimson.testcases.Account;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AccountPage;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.FAQsPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.LogoutPage;
import com.pom.crimson.pages.OnboardingPage;
import com.pom.crimson.pages.PrivacyPolicyPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.pages.RedeemRewardPage;
import com.pom.crimson.pages.TalktoAIAAgentPage;
import com.pom.crimson.pages.TermsOfUsePage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test Menus in Profile screen via Apple Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class AccountApplePageTest extends BaseFixture {
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{	
		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();	
	}
	

	@Test(enabled=true, priority = 84, testName = "Verify all the menus are working in Account screen via Apple", description = "Verify all the menus are working in Account screen via Apple")
		public void VerifyManusInAccountScreenviaApple() throws InterruptedException, IOException
		{ 
		loginPage.Takepic_Recvideo();
		 if (loginPage.isElementDisplayed("Skip"))
		    {
			 loginPage.skipIntroScreens();
		            }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
		loginPage.LoginWithApple();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
		ContactUsPage CP=new ContactUsPage(getDriver(), getPlatformName());
        MP.clickProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
        MP.clickManageProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage profile screen appears");
        logAssert.assertTrue(MP.isElementDisplayed(Constants.AppleTestUser),"Logged in with correct Apple user");
        MP.clickProfile();
        AccountPage AP = new AccountPage(getDriver(), getPlatformName());
        AP.ClickConsultations();
        logAssert.assertTrue(MP.isElementDisplayed("Consultations"),"Consultations screen appears");
        MP.clickProfile();
        AP.ClickSavedArticlesAndVideo();
        logAssert.assertTrue(MP.isElementDisplayed("Saved articles and videos"),"Saved articles and videos screen appears");
        MP.clickProfile();
        RedeemRewardPage RP = new RedeemRewardPage(getDriver(), getPlatformName());
        RP.RewardsMenu();
        logAssert.assertTrue(RP.isElementDisplayed("Rewards"),"Rewards screen appears");
        MP.clickProfile();
        AP.ClickReferAfriend();
        logAssert.assertTrue(AP.isElementDisplayed("Refer a friend"),"Refer a friend screen appears");
        MP.clickProfile();
        AP.ClickNotifications();
        logAssert.assertTrue(AP.isElementDisplayed("Notifications"),"Notifications screen appears");
        MP.clickProfile();
        logAssert.assertTrue(AP.isElementDisplayed("Language"),"Languages is displayed");
        //AP.ClickLanguage();
        //CP.BackButton();
        MP.clickProfile();
        FAQsPage FP=new FAQsPage(getDriver(), getPlatformName());
        FP.FAQsMenu();
        logAssert.assertTrue(FP.isElementDisplayed("FAQs"),"FAQs screen appears");
        MP.clickProfile();
        CP.ContactUsMenu();
        logAssert.assertTrue(CP.isElementDisplayed("Contact us"),"Contact us screen appears");
        CP.BackButton();
        MP.clickProfile();
        logAssert.assertTrue(AP.isElementDisplayed("Rate us"),"Rate us is displayed");
        MP.clickProfile();
        TalktoAIAAgentPage TP=new TalktoAIAAgentPage(getDriver(), getPlatformName());
        TP.ClickTalktoAIAagent();
        logAssert.assertTrue(TP.isElementDisplayed("Connect with an AIA agent"),"Connect with an AIA agent is displayed");
        MP.clickProfile();
        PrivacyPolicyPage PP=new PrivacyPolicyPage(getDriver(), getPlatformName());
        logAssert.assertTrue(PP.isElementDisplayed("Privacy statement"),"Privacy statement is Present");
        if (PP.isElementDisplayed("Privacy statement"))
        {
        	PP.PrivacyStatementMenu();
            logAssert.assertTrue(PP.isElementDisplayed("Privacy Statement"),"Privacy Statement information is available");
            }
        else
        {
        	 logAssert.assertTrue(PP.isElementDisplayed("Privacy Statement"),"Privacy Statement information is not available");
        }
        
        MP.clickProfile();        
        TermsOfUsePage TU=new TermsOfUsePage (getDriver(), getPlatformName());
        logAssert.assertTrue(TU.isElementDisplayed("Terms of use"),"Terms of Use is Present");
        if (TU.isElementDisplayed("Terms of Use"))
        {
        	TU.TermsOfUseMenu();
            logAssert.assertTrue(PP.isElementDisplayed("Terms of use"),"Terms of Use information is available");
            }
        else
        {
        	 logAssert.assertTrue(TU.isElementDisplayed("Terms of use"),"Terms of Use information is not available");
        }
        MP.clickProfile();
        LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
        LP.VerifyLogoutExists();
        logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
        LP.Logout_App();
        logAssert.assertAll();
     	}
     	
		
	/* This is the test case with verifying through back button however not working*
	@Test(enabled=true, priority = 82, testName = "Verify all the menus are working in Account screen via Gmail", description = "Verify all the menus are working in Account screen via Email")
	public void VerifyManusInAccountScreenviaApple() throws InterruptedException, IOException
	{ 
	loginPage.Takepic_Recvideo();
	loginPage.skipIntroScreens();
	loginPage.LoginWithApple();	
	ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
	ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
	ContactUsPage CP=new ContactUsPage(getDriver(), getPlatformName());
    MP.clickProfile();
	logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
    MP.clickManageProfile();
    logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage profile screen appears");
	logAssert.assertTrue(MP.isElementDisplayed(Constants.GmailTestUser),"Logged in with correct Email user");
    logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
	CP.BackButton();
    AccountPage AP = new AccountPage(getDriver(), getPlatformName());
    AP.ClickConsultations();
    logAssert.assertTrue(MP.isElementDisplayed("Consultations"),"Consultations screen appears");
    CP.BackButton();
    AP.ClickSavedArticlesAndVideo();
    logAssert.assertTrue(MP.isElementDisplayed("Saved articles and videos"),"Saved articles and videos screen appears");
    CP.BackButton();
    RedeemRewardPage RP = new RedeemRewardPage(getDriver(), getPlatformName());
    RP.RewardsMenu();
    logAssert.assertTrue(RP.isElementDisplayed("Rewards"),"Rewards screen appears");
    CP.BackButton();
    AP.ClickReferAfriend();
    logAssert.assertTrue(AP.isElementDisplayed("Refer a friend"),"Refer a friend screen appears");
    CP.BackButton();
    AP.ClickNotifications();
    logAssert.assertTrue(AP.isElementDisplayed("Notifications"),"Notifications screen appears");
    CP.BackButton();
    logAssert.assertTrue(AP.isElementDisplayed("Language"),"Languages is displayed");
   FAQsPage FP=new FAQsPage(getDriver(), getPlatformName());
    FP.FAQsMenu();
    logAssert.assertTrue(FP.isElementDisplayed("FAQs"),"FAQs screen appears");
    CP.BackButton();
    CP.ContactUsMenu();
    logAssert.assertTrue(CP.isElementDisplayed("Contact us"),"Contact us screen appears");
    CP.BackButton();
    logAssert.assertTrue(AP.isElementDisplayed("Rate us"),"Rate us is displayed");
    TalktoAIAAgentPage TP=new TalktoAIAAgentPage(getDriver(), getPlatformName());
    TP.ClickTalktoAIAagent();
    logAssert.assertTrue(TP.isElementDisplayed("Connect with an AIA agent"),"Connect with an AIA agent is displayed");
    MP.clickProfile();
    PrivacyPolicyPage PP=new PrivacyPolicyPage(getDriver(), getPlatformName());
    logAssert.assertTrue(PP.isElementDisplayed("Privacy Statement"),"Privacy Statement is Present");
    if (PP.isElementDisplayed("Privacy Statement"))
    {
    	PP.PrivacyStatementMenu();
        logAssert.assertTrue(PP.isElementDisplayed("Privacy Statement"),"Privacy Statement information is available");
        }
    else
    {
    	 logAssert.assertTrue(PP.isElementDisplayed("Privacy Statement"),"Privacy Statement information is not available");
    }
    
    
    MP.clickProfile();
    TermsOfUsePage TU=new TermsOfUsePage (getDriver(), getPlatformName());
    logAssert.assertTrue(TU.isElementDisplayed("Terms of Use"),"Terms of Use is Present");
    if (TU.isElementDisplayed("Terms of Use"))
    {
    	TU.TermsOfUseMenu();
        logAssert.assertTrue(PP.isElementDisplayed("Terms of Use"),"Terms of Use information is available");
        }
    else
    {
    	 logAssert.assertTrue(TU.isElementDisplayed("Terms of Use"),"Terms of Use information is not available");
    }
    MP.clickProfile();
    LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
    LP.VerifyLogoutExists();
    logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
    LP.Logout_App();
    logAssert.assertAll();
 	}
	*/
	
	
     	}
     	
     
	