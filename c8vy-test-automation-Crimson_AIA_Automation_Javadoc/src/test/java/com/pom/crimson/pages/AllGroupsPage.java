package com.pom.crimson.pages;

import com.pom.crimson.base.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * This is the Page Class for <b>Chats page</b> in the mobile application.
 * <br>
 * User can navigate to this page by :
 * <br><br>
 * Clicking on <b>Chats</b> menu from home page Tab bar at the bottom. 
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Indirani 
 */	
public class AllGroupsPage extends BasePage {

	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 	
	public AllGroupsPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}
}
