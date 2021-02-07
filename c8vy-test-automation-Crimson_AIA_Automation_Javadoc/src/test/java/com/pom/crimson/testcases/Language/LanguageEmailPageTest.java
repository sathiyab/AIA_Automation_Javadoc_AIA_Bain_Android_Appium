package com.pom.crimson.testcases.Language;

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
import com.pom.crimson.pages.PrivacyPolicyPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.pages.TermsOfUsePage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;
import com.pom.crimson.pages.AccountPage;
import com.pom.crimson.pages.ChangePasswordPage;
import com.pom.crimson.pages.LanguagePage;

/**
 * This class contains test methods to test <b>Language Change</b> functionality in the mobile app 
 * 
 * @author Balaji.Sridharan 
 */	
public class LanguageEmailPageTest extends BaseFixture {

	
	LoginPage loginPage;
	HomePage homePage;
	LogAssert logAssert;
	
	@BeforeMethod()
	public void beforeMethodLocal() throws Exception 
	{		
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
			
	}
	
	@Test(enabled=true, priority = 233, testName = "Verify Language change (Login via eMail)", description = "Verify language change (Login via eMail)")
	public void VerifyLanguage_LoginViaEmail() throws InterruptedException, IOException
	{ 
		loginPage.Takepic_Recvideo();
		loginPage.skipIntroScreens();
		 
		loginPage.LoginWithEmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		ProfilePage MP = new ProfilePage(getDriver(), getPlatformName());
        MP.clickProfile();
        AccountPage AP = new AccountPage(getDriver(), getPlatformName());
        LanguagePage LP = new LanguagePage(getDriver(), getPlatformName());
        logAssert.assertTrue(LP.isElementDisplayed("Language"),"Language is displayed");
        AP.ClickLanguage();
        logAssert.assertTrue(LP.isElementDisplayed("Add a language"),"Add a language is displayed");
        LP.ClickAddLanguage();        
        LP.ClickSearch();
        LP.EnterThai();
        logAssert.assertTrue(LP.isElementDisplayed("Thai"),"Thai language is listed");
	}
      
        
}