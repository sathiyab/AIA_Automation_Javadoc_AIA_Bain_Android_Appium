package com.pom.crimson.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>FAQs</b> page in the mobile application.
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class FAQsPage extends BasePage {
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='FAQs']")
	private MobileElement ClickFAQs;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */	
	public FAQsPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Taps on the FAQs menu in the profile menu list
	 */
	public void FAQsMenu()
	{
		GenericFunctions.scroll(driver, "FAQs", platformName);
		GenericFunctions.WaitForElement(driver, ClickFAQs);
		GenericFunctions.clickElement(driver, ClickFAQs);
    }
}