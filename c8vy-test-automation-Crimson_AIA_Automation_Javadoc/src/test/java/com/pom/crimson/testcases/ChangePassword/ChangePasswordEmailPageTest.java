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
import com.pom.crimson.pages.LanguagePage;

/**
 * This class contains test methods to test <b>Change password</b> via email Login 
 * 
 * @author Balaji.Sridharan 
 */	
public class ChangePasswordEmailPageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}
	
	@Test(enabled=true, priority = 231, testName = "Verify appropriate validation message appears for Change Password (Login via eMail)", description = "Verify appropriate validation message appears for Change Password (Login via eMail)")
	public void VerifyChangePasswordValidation_LoginViaEmail() throws InterruptedException, IOException
	{ 
		loginPage.Takepic_Recvideo();
		
		loginPage.skipIntroScreens();
		 
		loginPage.LoginWithEmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
        MP.clickProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
        MP.clickManageProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage profile screen appears");
        logAssert.assertTrue(MP.isElementDisplayed(Constants.EmailTestUser),"Logged in with correct Email user");
        MP.clickProfile();
	    AccountPage AP = new AccountPage(getDriver(), getPlatformName());
        ChangePasswordPage CPP=new ChangePasswordPage(getDriver(), getPlatformName());
        CPP.ClickChangePassword();
        logAssert.assertTrue(AP.isElementDisplayed("Change password"),"Change password is displayed");
        CPP.EnterValidOldPassword();
        CPP.EnterInvalidNewPassword();
        CPP.ClickSave();
        if (CPP.isElementDisplayed("Enter at least 8 characters"))
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Enter at least 8 characters"),"Enter at least 8 characters is available");
	}
        else
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Enter at least 8 characters"),"Enter at least 8 characters is available");
        }
     
        MP.clickProfile();
        CPP.ClickChangePassword();
        logAssert.assertTrue(AP.isElementDisplayed("Change password"),"Change password is displayed");
        CPP.EnterValidOldPassword();
        CPP.EnterLowercasedNewPassword();
        CPP.ClickSave();
        if (CPP.isElementDisplayed("Your password should contain an uppercase letter"))
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Your password should contain an uppercase letter"),"Your password should contain an uppercase letter is available");
	}
        else
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Your password should contain an uppercase letter"),"Your password should contain an uppercase letter is available");
        } 
        
        MP.clickProfile();
        CPP.ClickChangePassword();
        logAssert.assertTrue(AP.isElementDisplayed("Change password"),"Change password is displayed");
        CPP.EnterValidOldPassword();
        CPP.EnterNewPasswordWithoutNumeric();
        CPP.ClickSave();
        if (CPP.isElementDisplayed("Your password should contain a number"))
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Your password should contain a number"),"Your password should contain a number is available");
	}
        else
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Your password should contain a number"),"Your password should contain a number is available");
        } 
        
        MP.clickProfile();
        CPP.ClickChangePassword();
        logAssert.assertTrue(AP.isElementDisplayed("Change password"),"Change password is displayed");
        CPP.EnterValidOldPassword();
        CPP.EnterMismatchNewPassword();
        CPP.ClickSave();
        
        if (CPP.isElementDisplayed("Your passwords do not match"))  
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Your passwords do not match"),"Your passwords do not match is available");
	}
        else
        {
        	logAssert.assertTrue(CPP.isElementDisplayed("Your passwords do not match"),"Your passwords do not match is available");
        }    
        MP.clickProfile();
        LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
        LP.VerifyLogoutExists();
        logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
        LP.Logout_App();
        logAssert.assertAll();
     }
	
	@Test(enabled=true, priority = 232, testName = "Verify appropriate ALERT  appears for Change Password - Your new password cannot be the same as your current password (Login via eMail)", description = "Verify appropriate ALERT  appears for Change Password (Login via eMail)")
	public void VerifyAlertMessageFornewPasswordCurrentPasswordInChangePassword_LoginViaEmail() throws InterruptedException, IOException
	{ 
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		
		loginPage.LoginWithEmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
        MP.clickProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
        MP.clickManageProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage profile screen appears");
        logAssert.assertTrue(MP.isElementDisplayed(Constants.EmailTestUser),"Logged in with correct Email user");
        MP.clickProfile();
	    AccountPage AP = new AccountPage(getDriver(), getPlatformName());
        ChangePasswordPage CPP=new ChangePasswordPage(getDriver(), getPlatformName());
        CPP.ClickChangePassword();
        logAssert.assertTrue(AP.isElementDisplayed("Change password"),"Change password is displayed");
        CPP.EnterValidOldPassword();
        CPP.EnterOldPasswordInTheNewPasswordField();
        CPP.ClickSave();
        if (CPP.isElementDisplayed("Your new password cannot be the same as your current password."))
        {
        logAssert.assertTrue(CPP.isElementDisplayed("Your new password cannot be the same as your current password."),"Your new password cannot be the same as your current password.");		
		CPP.clickOk();
		ExtentReportManager.getTest().log(Status.PASS, "Alert Message appears Password validation ");
        }
        else
        {
        logAssert.assertTrue(CPP.isElementDisplayed("Your new password cannot be the same as your current password."),"Your new password cannot be the same as your current password.");
        }
        MP.clickProfile();
        LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
        LP.VerifyLogoutExists();
        logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
        LP.Logout_App();
		logAssert.assertAll();
        
	}
	
	@Test(enabled=true, priority = 233, testName = "Verify appropriate ALERT  appears for Change Password - Old Password is incorrect (Login via eMail)", description = "Verify appropriate ALERT  appears for Change Password - Old Password is incorrect (Login via eMail)")
	public void VerifyAlertMessageForIncorrectOldPasswordInChangePassword_LoginViaEmail() throws InterruptedException, IOException
	{ 
		loginPage.Takepic_Recvideo();
		
		loginPage.skipIntroScreens();
		    
		loginPage.LoginWithEmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
        MP.clickProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Profile screen appears");
        MP.clickManageProfile();
        logAssert.assertTrue(MP.isElementDisplayed("Manage profiles"),"Manage profile screen appears");
        logAssert.assertTrue(MP.isElementDisplayed(Constants.EmailTestUser),"Logged in with correct Email user");
        MP.clickProfile();
	    AccountPage AP = new AccountPage(getDriver(), getPlatformName());
        ChangePasswordPage CPP=new ChangePasswordPage(getDriver(), getPlatformName());
        CPP.ClickChangePassword();
        logAssert.assertTrue(AP.isElementDisplayed("Change password"),"Change password is displayed");
        CPP.EnterIncorrectOldPassword();
        CPP.EnterValidNewPassword();
        CPP.ClickSave();
        if (CPP.isElementDisplayed("Old Password is incorrect"))
        {
        logAssert.assertTrue(CPP.isElementDisplayed("Old Password is incorrect"),"Old Password is incorrect");		
		CPP.clickOk();
		ExtentReportManager.getTest().log(Status.PASS, "Alert Message appears for Password validation ");
        }
        else
        {
        logAssert.assertTrue(CPP.isElementDisplayed("Old Password is incorrect"),"Old Password is incorrect");
        }
        MP.clickProfile();
        LogoutPage LP=new LogoutPage (getDriver(), getPlatformName());
        LP.VerifyLogoutExists();
        logAssert.assertTrue(LP.isElementDisplayed("Log out"),"Log out is displayed");
        LP.Logout_App();
		logAssert.assertAll();
        
	}
	
	       
}