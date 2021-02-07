package com.pom.crimson.testcases.ChangePassword;

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
import com.pom.crimson.pages.TermsOfUsePage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;
import com.pom.crimson.pages.AccountPage;
import com.pom.crimson.pages.ChangePasswordPage;

/**
 * This class contains test methods to test <b>Change password</b> via Apple Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class ChangePasswordApplePageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}
	
	@Test(enabled=true, priority = 236, testName = "Verify Change Password not exists for (Login via Apple)", description = "Verify Change Password not exists for (Login via Apple)")
	public void VerifyChangePassword_LoginViaApple() throws InterruptedException, IOException
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
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage profile screen appears");
        logAssert.assertTrue(MP.isElementDisplayed(Constants.AppleTestUser),"Logged in with correct Apple user");
        MP.clickProfile();
        AccountPage AP = new AccountPage(getDriver(), getPlatformName());
        ChangePasswordPage CPP=new ChangePasswordPage(getDriver(), getPlatformName());
        if (CPP.isElementDisplayed("Change password"))
        {
        	logAssert.assertTrue(AP.isElementDisplayed("Change password"),"Change password appears as it hsould not appear");
        }
        else
        {
        	logAssert.assertTrue(AP.isElementDisplayed("ACCOUNT"),"Change password not exists in the Profile -> Account Screen which is expected for Apple Login");
        }
        MP.clickProfile();
        LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
        LP.VerifyLogoutExists();
        logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
        LP.Logout_App();
        
        logAssert.assertAll();
	}
	 	
	
}
   