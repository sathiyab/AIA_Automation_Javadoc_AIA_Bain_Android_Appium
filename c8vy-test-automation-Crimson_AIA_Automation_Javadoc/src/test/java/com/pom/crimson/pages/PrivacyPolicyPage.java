package com.pom.crimson.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Privacy statement</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * by clicking on <b>Privacy statement</b> menu in the profiles menu list
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class PrivacyPolicyPage extends BasePage {
	

	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Privacy Statement']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Privacy statement']")
	private MobileElement ClickPrivacyStatement;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */	
	public PrivacyPolicyPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Clicks on <b>Privacy statement</b> menu
	 */
	public void PrivacyStatementMenu()
	{
		GenericFunctions.scroll(driver, "Privacy Statement", platformName);
		GenericFunctions.WaitForElement(driver, ClickPrivacyStatement);
		GenericFunctions.clickElement(driver, ClickPrivacyStatement);
    }
}