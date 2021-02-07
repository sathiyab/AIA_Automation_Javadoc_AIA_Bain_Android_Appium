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
public class AllLiveEventsPage extends BasePage {
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement backBtn;
	
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")
	private MobileElement firstEventCard;
	
	private EventDetailsPage EDP;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 
	public AllLiveEventsPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}
	
	/**
	 * Taps on back button
	 *  
	 */	
	public void clickOnBackButton()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Click on back button");
		GenericFunctions.scroll(driver, "All live events", platformName);
		GenericFunctions.scrollByCordinatesUp(driver);
		GenericFunctions.WaitForElement(driver, backBtn);
		GenericFunctions.clickElement(driver, backBtn);
	}
	
	/**
	 * Taps on the first event detail card on "All live events" page
	 *  
	 *  @return {@link EventDetailsPage} Object of Event Details Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */		
	public EventDetailsPage clickOnLiveEventCard() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Event Card");
		GenericFunctions.WaitForElement(driver, firstEventCard);
		GenericFunctions.clickElement(driver, firstEventCard);
		EDP = new EventDetailsPage(driver, platformName);
		return EDP;
	} 		
}
