package com.pom.crimson.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Profile Menu</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class AccountPage extends BasePage {
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Refer a friend']")
	private MobileElement ClickReferAFriend;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Consultations']")
	private MobileElement ClickConsultations;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Notifications']")
	private MobileElement ClickNotifications;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Language']")
	private MobileElement ClickLanguage;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Saved articles and videos']")
	private MobileElement ClickSavedArticlesAndVideo;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 	
	public AccountPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Taps on <b>Refer a friend</b> menu in Profile page.
	 *  
	 */		
	public void ClickReferAfriend()
	{
		GenericFunctions.WaitForElement(driver, ClickReferAFriend);
		GenericFunctions.clickElement(driver, ClickReferAFriend);
    }
	
	/**
	 * Taps on <b>Consultations</b> menu in profile page.
	 *  
	 */		
	public void ClickConsultations()
	{
		GenericFunctions.WaitForElement(driver, ClickConsultations);
		GenericFunctions.clickElement(driver, ClickConsultations);
    }
	
	/**
	 * Taps on <b>Notifications</b> menu in profile page.
	 *  
	 */		
	public void ClickNotifications()
	{
		GenericFunctions.scroll(driver, "Notifications", platformName);
		GenericFunctions.WaitForElement(driver, ClickNotifications);
		GenericFunctions.clickElement(driver, ClickNotifications);
    }
	
	/**
	 * Taps on <b>Language</b> menu in profile page.
	 *  
	 */		
	public void ClickLanguage()
	{
		GenericFunctions.scroll(driver, "Language", platformName);
		GenericFunctions.WaitForElement(driver, ClickLanguage);
		GenericFunctions.clickElement(driver, ClickLanguage);
    }
	
	/**
	 * Taps on <b>Saved articles and videos</b> menu in profile page.
	 *  
	 */	
	public void ClickSavedArticlesAndVideo()
	{
		GenericFunctions.WaitForElement(driver, ClickSavedArticlesAndVideo);
		GenericFunctions.clickElement(driver, ClickSavedArticlesAndVideo);
    }
	
}