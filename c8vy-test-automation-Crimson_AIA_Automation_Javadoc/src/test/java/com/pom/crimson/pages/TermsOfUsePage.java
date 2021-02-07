package com.pom.crimson.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Terms of use</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * by clicking <b>Terms of use</b> from profiles menu list
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class TermsOfUsePage extends BasePage {
	

	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Terms of Use']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Terms of use']")
	private MobileElement ClickTermsofUse;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */	
	public TermsOfUsePage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Clicks on <b>Terms of use</b> menu from profiles menu list
	 */
	public void TermsOfUseMenu()
	{
		GenericFunctions.scroll(driver, "Terms of Use", platformName);
		GenericFunctions.WaitForElement(driver, ClickTermsofUse);
		GenericFunctions.clickElement(driver, ClickTermsofUse);
    }
}