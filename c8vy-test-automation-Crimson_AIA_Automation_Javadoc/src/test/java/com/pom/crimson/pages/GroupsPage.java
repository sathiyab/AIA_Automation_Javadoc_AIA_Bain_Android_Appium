package com.pom.crimson.pages;

import java.util.List;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * This is the Page Class for <b>Chats</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Chats</b> menu from home page Tab bar   
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class GroupsPage extends BasePage {
	
	

	//use contains
	//XCUIElementTypeButton[contains(@label,'Discover topics')]
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label=' Discover topics ']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Discover topics']")
	private MobileElement discoverTopics;
	
	//close button//not able to find in Ios
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='']")
	private MobileElement crossBtn;
	
	//@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=\"See all\"])[2]")
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='See all']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='See all']")
	private MobileElement andSeeAllBtn;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name='See all']")
	private List<MobileElement> seeAllBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Discover new groups']")
	private MobileElement discoverGroup;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='All groups']")
	private MobileElement allgroupTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Live events']")
	private MobileElement liveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your live events']")
	private MobileElement yourliveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='All live events']")
	private MobileElement allliveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='See All']")
	private MobileElement seeAllLnk;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='See all live events']")
	private MobileElement seeAllliveeventsBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='']")
	private MobileElement backBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Redeem reward']")
	private MobileElement redeemRewardbtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rewards']")
	private MobileElement redeemRewardHeaderTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='FAQs']")
	private MobileElement redeemRewardQstHeaderTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Divyateja']")
	private MobileElement verifyUserNameTxt;
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Groups, tab, 2 of 4']")
	private MobileElement groupBtn;
		
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='For you, tab, 1 of 4']")
	private MobileElement forYouBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Specialist chat']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[normalize-space(@label)='Specialist chat']")
	private MobileElement specialistChatLink;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Next']")
	private MobileElement nextBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Live events']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[normalize-space(@label)='Live events']")
	private MobileElement liveEventsLink;

	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public GroupsPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}
	
	/**
	 * Navigates to through the elements in the group page.
	 *
	 * @return {@link GroupsPage} Object of Groups page 
	 */			
	public GroupsPage goToGroupPage()
	{
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover new group");
		GenericFunctions.clickElement(driver, discoverGroup);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying All Groups Header");
		GenericFunctions.getText(driver, allgroupTxt, "All groups");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		

		//Specialist Chat functionality
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on For You Button");
		GenericFunctions.clickElement(driver, forYouBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Specialist chat");
		GenericFunctions.clickElement(driver, specialistChatLink);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Next button");
		GenericFunctions.clickElement(driver, nextBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Next button");
		GenericFunctions.clickElement(driver, nextBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		//Live Event functionality
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Live Events");
		GenericFunctions.clickElement(driver, liveEventsLink);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Live events Header");
		GenericFunctions.getText(driver, liveeventsTxt, "Live events");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on See ALL Link Button");
		GenericFunctions.clickElement(driver, seeAllLnk);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying User Live events");
		GenericFunctions.getText(driver, yourliveeventsTxt, "Your live events");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		//See All Live Event functionality
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on See ALL Live Events Button");
		GenericFunctions.clickElement(driver, seeAllliveeventsBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying All Live events");
		GenericFunctions.getText(driver, allliveeventsTxt, "All live events");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		
		//Redeem Reward Functionality
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Redeem Reward Button");
		GenericFunctions.clickElement(driver, redeemRewardbtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Header Text");
		GenericFunctions.getText(driver, redeemRewardHeaderTxt, "Rewards");
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Question Header Text");
		GenericFunctions.getText(driver, redeemRewardQstHeaderTxt, "FAQs");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying User Name");
		GenericFunctions.getText(driver, verifyUserNameTxt, "Divyateja");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on For You Button");
		GenericFunctions.clickElement(driver, forYouBtn);
		
		//Discover New Group
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover new group");
		GenericFunctions.clickElement(driver, discoverGroup);
		
		GroupsPage grpPage=new GroupsPage(driver, platformName);
		return grpPage;			
	}
		
	
	
	
}
