package com.pom.crimson.pages;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>All Live Events page</b> in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * Clicking on <b>Live Events</b> button from home page which will open the <b>Live events</b> page 
 * <br>click on <b>See all live events</b> button to open <b>All Live Events</b> page. 
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Sivaprakash.Selvaraj 
 */	 
public class MyLiveEventsPage extends BasePage{
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement backButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='']")
	private MobileElement firstUpcomingEventCard;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Past']")
	private MobileElement tabPagePast;	
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 *
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test  	 *  
	 */	
	public MyLiveEventsPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}	
	
	/**
	 * Taps on "PAST" tab to open the past events tab page. 
	 *  
	 * @throws InterruptedException thrown when a thread is interrupted  
	 */	
	public void PastTabPage() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Past Tab Page");
		GenericFunctions.clickElement(driver, tabPagePast);
		Thread.sleep(3000);
		ExtentReportManager.getTest().log(Status.INFO, "Past Tab Page Opened");		
	}	
	
	/**
	 * Taps on back button
	 *  
	 * @throws InterruptedException thrown when a thread is interrupted  
	 */	
	public void BackButton() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Back Button");
		
		GenericFunctions.scrollByCordinatesUp(driver);
		GenericFunctions.scrollByCordinatesUp(driver);
		GenericFunctions.scrollByCordinatesUp(driver);
		
		GenericFunctions.clickElement(driver, backButton);
		Thread.sleep(3000);
		ExtentReportManager.getTest().log(Status.INFO, "Back to Events Page");		
	}	

}
