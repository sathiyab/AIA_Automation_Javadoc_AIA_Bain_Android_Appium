package com.pom.crimson.pages;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Contact us page</b> in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * Clicking on <b>Profile </b> from bottom menu bar in home page  
 * <br>click on <b>Contact us</b> menu to open <b>Contact us</b> page. 
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Sivaprakash.Selvaraj 
 */	
public class ContactUsPage extends BasePage {
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement backButton;	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Contact us']")
	private MobileElement ContactUsMenu;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 *
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test	 *  
	 */	
	public ContactUsPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver, platformName);
	}
	
	/**
	 * Taps on back button
	 *
	 * thrown when a thread is interrupted
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted 
	 */		
	public void BackButton() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Back Button");
		GenericFunctions.WaitForElement(driver, backButton);
		GenericFunctions.clickElement(driver, backButton);
		ExtentReportManager.getTest().log(Status.INFO, "Back to Events Page");		
	}
	
	/**
	 * Taps on Contact us link from profiles menu
	 *  
	 */		
	public void ContactUsMenu()
	{
		GenericFunctions.scroll(driver, "Contact us", platformName);
		GenericFunctions.WaitForElement(driver, ContactUsMenu);
		GenericFunctions.clickElement(driver, ContactUsMenu);
    }
}
