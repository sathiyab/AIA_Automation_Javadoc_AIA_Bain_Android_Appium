package com.pom.crimson.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.aventstack.extentreports.ExtentTest;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * This is the Page Class for <b>Logout</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * by <b>clicking</b> on the logout menu in profiles menu list.
 * This page class contains functions to simulate logout action.
 * 
 * @author Balaji.Sridharan 
 */
public class LogoutPage extends BasePage{

	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public LogoutPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log out']")
	private MobileElement ClickLogout;
	
	/*
	 * @AndroidFindBy(xpath="//android.widget.TextView[@text='Profile']") private
	 * MobileElement ClickProfile;
	 */
	/**
	 * Scrolls to the <b>Log out</b> menu in the profile menu list
	 * 
	 * @throws InterruptedException thrown when the Thread is interrupted 
	 */
	public void VerifyLogoutExists() throws InterruptedException
	{
		GenericFunctions.scroll(driver, "Log out", platformName);
	
	}

	/**
	 * clicks on the <b>Log out</b> menu in the profile menu list
	 * 
	 * @throws InterruptedException thrown when the Thread is interrupted
	 */	
	public void Logout_App() throws InterruptedException
	{
		GenericFunctions.clickElement(driver, ClickLogout);
	}

	
}



	
	
