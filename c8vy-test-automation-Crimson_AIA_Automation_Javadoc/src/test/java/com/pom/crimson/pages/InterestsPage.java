package com.pom.crimson.pages;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Edit interest page</b> in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * Clicking on <b>Profile </b> from bottom menu bar in home page  
 * <br>click on <b>Manage profile</b> menu to open <b>profile</b> page.
 * <br>click on <b>edit</b> link adjacent to Interest header 
 * <br>Edit Interest page can be navigated from live events page, contents page where the list of interests are displayed.  
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Sivaprakash.Selvaraj 
 */	
public class InterestsPage extends BasePage {
	
	//not available for ios
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private MobileElement closeButton;	
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test 
	 */		
	public InterestsPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}
	
	/**
	 * Taps on back button
	 *
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */				
	public void CloseButton() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Close Button");
		GenericFunctions.WaitForElement(driver, closeButton);
		GenericFunctions.clickElement(driver, closeButton);
		ExtentReportManager.getTest().log(Status.INFO, "Back to Events Page");		
	}	
}
