package com.pom.crimson.pages;

import com.aventstack.extentreports.ExtentTest;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import com.pom.crimson.util.ExtentReportManager;
import com.aventstack.extentreports.Status;

/**
 * This is the Page Class for <b>Intro screens</b> page in the mobile application.
 * <br>
 * This page will be opened when the application is launched for the first time. 
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class IntroPage extends BasePage{

	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */	
	public IntroPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	//@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Next') and @index='0']")
	//@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Next') and @index='0']")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Next']")
	private MobileElement buttonNext;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Done']")
	private MobileElement buttonDone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Email']")
	private MobileElement buttonContinueWithEmail;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement inputEmailID;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue']")
	private MobileElement buttonContinue;	
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement inputPassword;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log in']")
	private MobileElement buttonLogin;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Skip']")
	private MobileElement linkSkip;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter your password']")
	private MobileElement txtEnterYourPassword;		
	

	/**
	 * Taps on the <b>Next</b> button to navigate through the Intro screen carousel
	 */
	public void NavigateIntroScreen() {
		GenericFunctions.clickElement(driver, buttonNext);
		/*
		
		if (GenericFunctions.isElementDisplayed(buttonNext))
		{
		GenericFunctions.clickElement(driver, buttonNext);
		}
		else
		{
			ExtentReportManager.getTest().log(Status.FAIL, "Intro screens are not available");
			
		}
		*/
	}
	
	/**
	 * Taps on the <b>Done</b> button on the last slide of the Intro screen carousel
	 */	
	public void CompleteIntroScreenNavigation() {
		GenericFunctions.clickElement(driver, buttonDone);
		/*
		if (GenericFunctions.isElementDisplayed(buttonDone))
		{
		GenericFunctions.clickElement(driver, buttonDone);
		}
		else
		{
			ExtentReportManager.getTest().log(Status.FAIL, "Intro screens are not available");
		}
		*/
	}	
	
	/**
	 * Taps on the <b>Skip</b> button on the Intro screen
	 */		
	public void skipIntroScreens() {
		
		GenericFunctions.clickElement(driver, linkSkip);
		/*
		
		if (GenericFunctions.isElementDisplayed(linkSkip))
		{
		GenericFunctions.clickElement(driver, linkSkip);
		}
		else
		{
			ExtentReportManager.getTest().log(Status.FAIL, "Intro screens are not available");
		}
		*/
	}	
	
	/**
	 * This method is used to login the application using Email user
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */			
	public void LoginWithEmail() throws InterruptedException{
		GenericFunctions.clickElement(driver, buttonContinueWithEmail);
		GenericFunctions.sendKeys(driver, inputEmailID, "balaji.sridharan@mphasis.com");
		GenericFunctions.clickElement(driver, buttonContinue);
		Thread.sleep(3000);
		GenericFunctions.WaitForElement(driver, txtEnterYourPassword);
		GenericFunctions.sendKeys(driver, inputPassword, "Welcome45!");
		GenericFunctions.clickElement(driver, buttonLogin);
		GenericFunctions.clickElement(driver, buttonLogin);
		Thread.sleep(30000);
	}
	
	
}
