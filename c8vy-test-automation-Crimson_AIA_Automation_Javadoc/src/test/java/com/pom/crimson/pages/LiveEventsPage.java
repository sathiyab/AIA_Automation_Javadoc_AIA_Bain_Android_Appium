package com.pom.crimson.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;

/**
 * This is the Page Class for <b>Live Events page</b> in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * Clicking on <b>Live Events</b> button from home page which will open the <b>Live events</b> page  
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Sivaprakash.Selvaraj 
 */
public class LiveEventsPage extends BasePage{
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='See all live events']")
	private MobileElement seeAllLiveEventsBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='All live events']")
	private MobileElement AllLiveEventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement backButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Drop us a message']")
	private MobileElement linkDropMessage;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Recommended for you']")
	private MobileElement textRecommendedEventsHeader;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Upcoming']")
	private MobileElement textUpcomingEventsHeader;		
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add more interests']")
	private MobileElement textInterestHeader;		
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test  
	 */	
	public LiveEventsPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}
	
	/**
	 * Taps on the "See all live events" button in the Live events page to open "All live events" page
	 *  
	 *  @return {@link AllLiveEventsPage} Object of All live events page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */			
	public AllLiveEventsPage goToSeeAllEvents() throws InterruptedException
	{				
		ExtentReportManager.getTest().log(Status.INFO, "Click on See All Live Events Button");
		GenericFunctions.scroll(driver, "See all live events", platformName);
		GenericFunctions.WaitForElement(driver, seeAllLiveEventsBtn);
		GenericFunctions.clickElement(driver, seeAllLiveEventsBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Opening All Live Events Page");
		AllLiveEventsPage allEventsPage=new AllLiveEventsPage(driver, platformName);
		return allEventsPage;		
	}	
	
	/**
	 * Taps on the first upcoming event card on "Live events" page under "Upcoming" Events section
	 *  
	 *  @return {@link EventDetailsPage} Object of Event Details Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */		
	public EventDetailsPage goToEventDetailsPage() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Upcoming Event Card");
		GenericFunctions.scroll(driver, "Upcoming", platformName);
		//GenericFunctions.WaitForElement(driver, textUpcomingEventsHeader);
		GenericFunctions.tapByCordinates(driver, textUpcomingEventsHeader.getLocation().x+100, textUpcomingEventsHeader.getLocation().y + 200);
		
		ExtentReportManager.getTest().log(Status.INFO, "Opening Live Event Details Page");
		EventDetailsPage EDP =new EventDetailsPage(driver, platformName);
		return EDP;		
	}
	
	/**
	 * Clicks on "see all" link in "Live events" page under "Upcoming" Events section to open the assigned Live Events page for the user
	 *  
	 *  @return {@link MyLiveEventsPage} Object of My Live Events page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */			
	public MyLiveEventsPage goToMyLiveEventsPage() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Click on \"See all\" upcoming events link");
		GenericFunctions.scroll(driver, "Upcoming", platformName);
		//GenericFunctions.WaitForElement(driver, textUpcomingEventsHeader);		
		GenericFunctions.tapByCordinates(driver, textUpcomingEventsHeader.getLocation().x + 900, textUpcomingEventsHeader.getLocation().y + 20);
		
		ExtentReportManager.getTest().log(Status.INFO, "Opening My Live Events Page");
		MyLiveEventsPage MLEP =new MyLiveEventsPage(driver, platformName);
		return MLEP;		
	}
	
	/**
	 * Taps on the first event detail card on "Recommended events" section of "Live Events" page
	 *  
	 *  @return {@link EventDetailsPage} Object of Event Details Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */			
	public EventDetailsPage goToRecommendedEventDetailsPage() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Click on First Recommended Event Card");		
		GenericFunctions.scroll(driver, "Recommended for you", platformName);
		ExtentReportManager.getTest().log(Status.INFO, textRecommendedEventsHeader.getLocation().x + "-" + textRecommendedEventsHeader.getLocation().y);
		GenericFunctions.tapByCordinates(driver, textRecommendedEventsHeader.getLocation().x, textRecommendedEventsHeader.getLocation().y + 200);
		ExtentReportManager.getTest().log(Status.INFO, "Opening Live Event Details Page");
		EventDetailsPage EDP =new EventDetailsPage(driver, platformName);
		return EDP;		
	}
	
	/**
	 * Taps on back button
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted 
	 */	
	public void BackButton() throws InterruptedException{
		GenericFunctions.scrollByCordinatesUp(driver);
		GenericFunctions.scrollByCordinatesUp(driver);
		ExtentReportManager.getTest().log(Status.INFO, "Click on Back Button from Live Events Page");
		GenericFunctions.scroll(driver, "Live events", platformName);
		GenericFunctions.WaitForElement(driver, backButton);
		GenericFunctions.clickElement(driver, backButton);
		ExtentReportManager.getTest().log(Status.INFO, "Back to Home Page");		
	}
	
	/**
	 * Clicks on "see all" link in "Live events" page under "Add more interests" section to open the "Interest" page
	 *  
	 *  @return {@link InterestsPage} Object of interest page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */				
	public InterestsPage seeAllInterests() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on See All Interests from Live Events Page");
		GenericFunctions.scroll(driver, "Add more interests", platformName);
		ExtentReportManager.getTest().log(Status.INFO, textInterestHeader.getLocation().toString());
		ExtentReportManager.getTest().log(Status.INFO, "900");		
		GenericFunctions.tapByCordinates(driver, textInterestHeader.getLocation().x + 900, textInterestHeader.getLocation().y + 20);
		
		InterestsPage IP = new InterestsPage(driver, platformName);
		ExtentReportManager.getTest().log(Status.INFO, "On Interests page now.");
		return IP;		
	}		
	
	/**
	 * Clicks on "Drop us a message" link in "Live events" page under "Interested in something else?" section to open the "Contact us" page
	 *  
	 *  @return {@link ContactUsPage} Object of contact us page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */					
	public ContactUsPage clickDropMessage() throws InterruptedException{
		ExtentReportManager.getTest().log(Status.INFO, "Click on Drop us a Message from Live Events Page");
		GenericFunctions.scroll(driver, "Drop us a message", platformName);
		GenericFunctions.WaitForElement(driver, linkDropMessage);
		GenericFunctions.clickElement(driver, linkDropMessage);
		ContactUsPage CP = new ContactUsPage(driver, platformName);
		ExtentReportManager.getTest().log(Status.INFO, "On Contact us page now.");
		return CP;		
	}

	/**
	 * Taps on the first upcoming event card on "Live events" page under "Upcoming" Events section
	 *  
	 *  @return {@link EventDetailsPage} Object of Event Details Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */			
	public EventDetailsPage clickOnUpcomingEventCard() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Click on upcoming event card in Live Events Page");
		GenericFunctions.WaitForElement(driver, textUpcomingEventsHeader);		
		GenericFunctions.tapByCordinates(driver, textUpcomingEventsHeader.getLocation().x + 50, textUpcomingEventsHeader.getLocation().y + 200);
		
		ExtentReportManager.getTest().log(Status.INFO, "Opening Upcoming Event Details Page");
		EventDetailsPage EDP =new EventDetailsPage(driver, platformName);
		return EDP;
	}
	
}