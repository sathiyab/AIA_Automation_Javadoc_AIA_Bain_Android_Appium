package com.pom.crimson.pages;

import com.pom.crimson.base.BasePage;
import com.pom.crimson.util.Constants;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * This is the Page Class for <b>Manage profile</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * by clicking on <b>Manage profile</b> menu in the profiles menu list
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class ManageProfilePage extends BasePage {
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.MANAGE_EDIT+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.MANAGE_EDIT+"']")
	private MobileElement edit;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Interests']")
	private MobileElement interests;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 	
	public ManageProfilePage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}

}
