package com.pom.crimson.pages;

import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * This is the Page Class for <b>Talk to a doctor or nurse</b> page in the mobile application.
 * <br>
 * User can navigate to this page by clicking the:
 * <br><br>
 * <b>Talk to a doctor or nurse</b> button in the Home page   
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class SpecialistChatPage extends BasePage {
	
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
	public SpecialistChatPage(AppiumDriver<MobileElement> driver, String platformName)
	{
		super(driver, platformName);
	}
	
	/**
	 * Navigates through Specialist Chat Page	
	 * 
	 * @return {@link SpecialistChatPage} Object of SpecialistChatPage
	 */
	public SpecialistChatPage goToSpecialistChatPage()
	{

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
		
		SpecialistChatPage spchatPage=new SpecialistChatPage(driver, platformName);
		return spchatPage;			
	}	
}
