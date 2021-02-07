package com.pom.crimson.pages;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Event details page</b> in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * Clicking on <b>any event card </b> from home page or Live Events page or All events page  
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Sivaprakash.Selvaraj 
 */	
public class EventDetailsPage extends BasePage{
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement backButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Choose']")
	private MobileElement btnFirstChooseSchedule;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Choose') and @index='1']")
	private MobileElement btnSecondChooseSchedule;			
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Confirm your seat']")
	private MobileElement schMenuBtnConfirmYourSeat;						
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Invite friends']")
	private MobileElement inviteLink;
	
	@AndroidFindBy(id = "android:id/chooser_copy_button")
	private MobileElement inviteCopyButton;	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Drop us a message']")
	private MobileElement linkDropMessage;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 *
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test  
	 */		
	public EventDetailsPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}
	
	/**
	 * Taps on back button
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */			
	public void BackButton() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Back Button");

		GenericFunctions.scrollByCordinatesUp(getDriver());
		GenericFunctions.scrollByCordinatesUp(getDriver());
		GenericFunctions.scrollByCordinatesUp(getDriver());
		GenericFunctions.WaitForElement(driver, backButton);
		GenericFunctions.clickElement(driver, backButton);
		if (this.isElementDisplayed("LIVE FORMAT")) {
			driver.navigate().back();
		}		
		ExtentReportManager.getTest().log(Status.INFO, "Back to Events Page");		
	}
	
	/**
	 * Taps on first schedule button for the event.
	 * <br>
	 * There will be multiple schedules for an event.
	 * <br>
	 * This method will tap on the first <b>Choose</b> button to participate on the first event schedule.
	 * 
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */			
	public void ScheduleLiveEvent() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Choose Button");

		GenericFunctions.scrollByCordinates(getDriver());
		GenericFunctions.scrollByCordinates(getDriver());
		GenericFunctions.scrollByCordinates(getDriver());
		GenericFunctions.WaitForElement(driver, btnFirstChooseSchedule);
		GenericFunctions.clickElement(driver, btnFirstChooseSchedule);
		ExtentReportManager.getTest().log(Status.INFO, "Confirm Your Seats Menu Opened.");		
	}
	
	/**
	 * Taps on confirming the seat to participate on the live event schedule.
	 * <br>
	 * This action will confirm the participation to the event for the timings chosen.
	 *  
	 * @throws InterruptedException thrown when a thread is interrupted 
	 */			
	public void ConfirmYourSeats() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Confirm Your Seats Button");
		GenericFunctions.WaitForElement(driver, schMenuBtnConfirmYourSeat);
		GenericFunctions.clickElement(driver, schMenuBtnConfirmYourSeat);
		ExtentReportManager.getTest().log(Status.INFO, "Confirm Your Seats Menu Opened.");		
	}
	
	/**
	 * Clicks on <b>Invite friends</b> link on an upcoming event.
	 * <br>
	 * The link will appear only on scheduled events and not in the un-scheduled events.
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */				
	public void inviteToEvent() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Invite link");
		GenericFunctions.WaitForElement(driver, inviteLink);
		GenericFunctions.clickElement(driver, inviteLink);
		ExtentReportManager.getTest().log(Status.INFO, "Share link menu opened");		
	}
	
	/**
	 * Taps on <b>Copy</b> button on share invite screen.
	 *   
	 * @throws InterruptedException thrown when a thread is interrupted  
	 */					
	public void clickOnCopyButton() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Invite Copy button ");
		GenericFunctions.WaitForElement(driver, inviteCopyButton);
		GenericFunctions.clickElement(driver, inviteCopyButton);
		Thread.sleep(1000);		
	}
	
	/**
	 * Clicks on <b>Drop us a message</b> link on an un-scheduled event.
	 * <br>
	 * The link will appear only on un-scheduled events and not in the scheduled events.
	 *  
	 * @return {@link ContactUsPage} Object reference to Contact us page 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */	
	public ContactUsPage clickDropMessage() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Drop us a Message from Event Details Page");
		GenericFunctions.scroll(driver, "Drop us a message", platformName);
		GenericFunctions.WaitForElement(driver, linkDropMessage);
		GenericFunctions.clickElement(driver, linkDropMessage);
		ContactUsPage CP = new ContactUsPage(driver, platformName);
		ExtentReportManager.getTest().log(Status.INFO, "On Contact us page now.");
		return CP;		
	}		
	
}