package com.pom.crimson.pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;

import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.clipboard.HasClipboard;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * When user logs in to app, Home page is displayed.<br>
 * User can view recommended content, 
 * daily tips and navigate to various other pages 
 * like Live Events,Talk to doctor nurse, Moments log etc from home page.
 *  
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 * @author Sivaprakash.Selvaraj
 * @author Indirani
 */
public class HomePage extends BasePage{
	
	//Login Xpath
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Skip']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Skip']")
	private MobileElement skip;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log in or sign up']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Log in or sign up']")
	private MobileElement loginOrSignUp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Google']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Continue with Google']")
	private MobileElement continueWithGoogle;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Apple']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Continue with Apple']")
	private MobileElement continueWithApple;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Email']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Continue with Email']")
	private MobileElement continueWithEmail;
	
	@AndroidFindBy(xpath="//android.widget.EditText")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther")
	private MobileElement username;
	
	@AndroidFindBy(xpath="//android.widget.EditText")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther")
	private MobileElement password;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Continue']")
	private MobileElement countinue;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log in']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Log in']")
	private MobileElement logIn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter your password']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Enter your password']")
	private MobileElement enterYourPassword;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter your email address']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='Enter your email address']")
	private MobileElement enterYourEmail;

	
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
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Planning a baby Jan 2021']")
	private MobileElement group1LnkTxt;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement group1Lnk;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Join group']")
	private MobileElement joinGroupBtn;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement actionBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Group info']")
	private MobileElement grpInfoLnk;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='']")
	private MobileElement cancelBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Members']")
	private MobileElement membersLnk;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Member']")
	private MobileElement memberHeaderTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Invite friends to group']")
	private MobileElement invfriendsGrp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Share']")
	private MobileElement sharefriendsHeaderTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Leave group']")
	private MobileElement leaveGrp;
		
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Live events']")
	private MobileElement liveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your live events']")
	private MobileElement yourliveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='All live events']")
	private MobileElement allliveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='See All']")
	private MobileElement seeAllLnk;
	
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='See all live events']")
	//private MobileElement seeAllliveeventsBtn;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement backBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Redeem reward']")
	private MobileElement redeemRewardbtn;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rewards']")
	private MobileElement redeemRewardHeaderTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='FAQs']")
	private MobileElement redeemRewardQstHeaderTxt;
		
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Divyateja']")
	private MobileElement verifyUserNameTxt;
	
	
	//@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Groups, tab, 2 of 5']")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"Groups, tab, 2 of 4\"]/android.widget.TextView")
	private MobileElement groupBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='C-Section 101s']")
	private MobileElement selectGrp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Already joined']")
	private MobileElement alreadyJoinedGroupTxt;
		
	@AndroidFindBy(xpath="//android.widget.TextView[@text='By participating you agree to lorem ipsum dolor sit amet']")
	private MobileElement joinedgroupdescription;
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"For you, tab, 1 of 4\"]/android.widget.TextView")
	private MobileElement forYouBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Specialist chat']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[normalize-space(@label)='Specialist chat']")
	private MobileElement specialistChatLink;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Next']")
	private MobileElement nextBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Live events']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[normalize-space(@label)='Live events']")
	private MobileElement liveEventsLink;

	//@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Profile, tab, 4 of 5']")
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"Profile, tab, 4 of 4\"]/android.widget.TextView")
	private MobileElement profileBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log out']")
	private MobileElement logoutLnk;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your consultation']")
	private MobileElement consultationHeaderTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Start consultation']")
	private MobileElement startConsultBtn;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']")
	private MobileElement confirmBtn;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	private MobileElement closeBtn;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='acceptConsentButton']")
	private MobileElement agreementbtn;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='android:id/copy_button']")
	private MobileElement copybtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='6']")
	private MobileElement ratingNoBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	private MobileElement ratingTxt;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='0']")
	private MobileElement ratingSubmitBtn;
	
	//-----------------Team 1 Locators----------------

	@AndroidFindBy(xpath = "//android.widget.TextView[normalize-space(@text)='"+Constants.MOMENTS_LOG_TITLE+"']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[normalize-space(@label)='"+Constants.MOMENTS_LOG_TITLE+"']")
	private MobileElement momentsLogLink;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.CONTENT_SEEALL+"']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.CONTENT_SEEALL+"']")
	private MobileElement seeAll;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'read')][last()]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@label,'read')][last()]")
	private MobileElement readText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.CONTENT_LIKE+"']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.CONTENT_LIKE+"']")
	private MobileElement like;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.CONTENT_UNLIKE+"']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.CONTENT_UNLIKE+"']")
	private MobileElement unlike;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.SHARE_IN_GROUPCHAT+"']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_GROUPCHAT+"']")
	private MobileElement shareInGroupChat;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.SHARE_IN_OTHERAPPS+"']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_OTHERAPPS+"']")
	private MobileElement shareInOtherApps;
	

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.ADD_TO_MOMENTS+"']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.ADD_TO_MOMENTS+"']")
	private MobileElement addToMoments ;

	
	//locator not available in ios
	@AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement share;
	
	
	
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 	
 	public HomePage(AppiumDriver<MobileElement> driver, String platformName) {
		super(driver, platformName);
	}
	
 	/**
 	 * Runs the Smoke test flow for Groups Module
 	 * 
 	 */
	public void validateSmokeFlow()
	{
		login();
		
		GenericFunctions.WaitForElement(driver, groupBtn);
		
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover new group");
		GenericFunctions.clickElement(driver, discoverGroup);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying All Groups Header");
		GenericFunctions.getText(driver, allgroupTxt, "All groups");
			
		ExtentReportManager.getTest().log(Status.PASS, "Verified Joined Group Navigation Flow");
		
		//30th Oct build back button is not available
		//ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		//GenericFunctions.clickElement(driver, backBtn);
		
		//Specialist Chat functionality
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on For You Button");
		GenericFunctions.clickElement(driver, forYouBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Specialist chat");
		GenericFunctions.clickElement(driver, specialistChatLink);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Next button");
		GenericFunctions.clickElement(driver, nextBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Next button view");
		GenericFunctions.androidScroll(driver, "Next");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Next button");
		GenericFunctions.clickElement(driver, nextBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		ExtentReportManager.getTest().log(Status.PASS, "Verified Specialist Navigation Flow");
		
		//Live Event functionality
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Live Events");
		GenericFunctions.clickElement(driver, liveEventsLink);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Live events Header");
		GenericFunctions.getText(driver, liveeventsTxt, "Live events");
		
		ExtentReportManager.getTest().log(Status.PASS, "Verified Live Event Flow");
		
		/*ExtentReportManager.getTest().log(Status.INFO, "Clicking on See ALL Link Button");
		GenericFunctions.clickElement(driver, seeAllLnk);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying User Live events");
		GenericFunctions.getText(driver, yourliveeventsTxt, "Your live events");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);*/
		
		//See All Live Event functionality
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on See ALL Live Events Button");
		GenericFunctions.clickElement(driver, seeAllliveeventsBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying All Live events");
		GenericFunctions.getText(driver, allliveeventsTxt, "All live events");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
		
		ExtentReportManager.getTest().log(Status.PASS, "Verified See ALL Live Events Navigation Flow");
		
		//Redeem Reward Functionality
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Redeem Reward Button");
		GenericFunctions.clickElement(driver, redeemRewardbtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Header Text");
		GenericFunctions.getText(driver, redeemRewardHeaderTxt, "Rewards");
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Question Header Text");
		GenericFunctions.getText(driver, redeemRewardQstHeaderTxt, "FAQs");
		
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Terms and Conditions text");
		GenericFunctions.androidScroll(driver, "Terms and Conditions");
		
		ExtentReportManager.getTest().log(Status.PASS, "Verified Redeem Reward Navigation Flow");
			
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on For You Button");
		GenericFunctions.clickElement(driver, forYouBtn);
		
		//Discover New Group
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover new group");
		GenericFunctions.clickElement(driver, discoverGroup);
			
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Group Name");
		GenericFunctions.getText(driver, group1LnkTxt, "Planning a baby Jan 2021");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on first group listed in All groups page");
		GenericFunctions.clickElement(driver, group1Lnk);
		
		//ExtentReportManager.getTest().log(Status.INFO, "Clicking on Join Group Button");
		//GenericFunctions.clickElement(driver, joinGroupBtn);	
	}
	
	//TC-02 Specialist Chat functionality
	/**
	 * This method contains scripts for testing Specialist Chat page functionalities
	 * 
	 */
	public void goToSpecialistChatHomePage()
	{
		login();
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Specialist chat");
		GenericFunctions.clickElement(driver, specialistChatLink);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Next button");
		GenericFunctions.clickElement(driver, nextBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Next button view");
		GenericFunctions.androidScroll(driver, "Next");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Next button");
		GenericFunctions.clickElement(driver, nextBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Consultation Header Text");
		GenericFunctions.getText(driver, consultationHeaderTxt, "Your consultation");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Start consultation button");
		GenericFunctions.clickElement(driver, startConsultBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on read agreement button");
		GenericFunctions.clickElement(driver, agreementbtn);

		ExtentReportManager.getTest().log(Status.INFO, "Clicking on close button");
		GenericFunctions.clickElement(driver, closeBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on confirmation button");
		GenericFunctions.clickElement(driver, confirmBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rating Header Text");
		GenericFunctions.getText(driver, ratingTxt, "On a scale of 0 to 10,how likely are you to recommend Tele-consult to your friend or a colleague?");
		
		ExtentReportManager.getTest().log(Status.INFO, "User can select any rating from 0 to 10");
		GenericFunctions.clickElement(driver, ratingNoBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Submit button in Rating window");
		GenericFunctions.clickElement(driver, ratingSubmitBtn);
				
		ExtentReportManager.getTest().log(Status.PASS, "Verified Rating functionality");	
		
		logout();	
	}
	
	
	//TC-03 Verify already joined group functionality
	/**
	 * This method validates the scenario of Joining an existing group, which th user is already part of
	 */
	public void againtrytoJoinexistingjoinedGroup() 
	{
		login();
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
			
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover new group");
		GenericFunctions.clickElement(driver, discoverGroup);
					
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on anyone of the Groups");
		GenericFunctions.clickElement(driver, selectGrp);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying already joined Group Name");
		GenericFunctions.getText(driver, selectGrp, "C-Section 101s");
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying already joined Group Button");
		GenericFunctions.getText(driver, alreadyJoinedGroupTxt, "Already joined");
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying already group joined description");
		GenericFunctions.getText(driver, joinedgroupdescription, "By participating you agree to lorem ipsum dolor sit amet");
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on All Groups Header Text");
		GenericFunctions.clickElement(driver, allgroupTxt);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
	}
	
	//TC-04 Verify Member Group functionality
	/**
	 * This method gets the member details of an existing group 
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void chkMemberGroup() throws InterruptedException 
	{
	
		//login();
		GenericFunctions.WaitForElement(driver, groupBtn);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		//API Call
		GenericFunctions.WaitForElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Action Button");
		GenericFunctions.clickElement(driver, actionBtn);
		Thread.sleep(10000);
		GenericFunctions.WaitForElement(driver, grpInfoLnk);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Members Link");
		GenericFunctions.clickElement(driver, membersLnk);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Member Header Text");
		GenericFunctions.getText(driver, memberHeaderTxt, "Member");
		
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Close Button");
		GenericFunctions.clickElement(driver, cancelBtn);
	}
	
	//TC-05 Verify Group Info functionality
	/**
	 * This method verifies the Group Infor menu
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted 
	 */
	public void chkgroupInfo() throws InterruptedException
	{
		//login();
		GenericFunctions.WaitForElement(driver, groupBtn);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		//API Call
		GenericFunctions.WaitForElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Action Button");
		GenericFunctions.clickElement(driver, actionBtn);
		Thread.sleep(10000);
		GenericFunctions.WaitForElement(driver, grpInfoLnk);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Group Info");
		GenericFunctions.clickElement(driver, grpInfoLnk);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Group Info Header Text");
		GenericFunctions.getText(driver, grpInfoLnk, "Group info");
		
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Close Button");
		GenericFunctions.clickElement(driver, cancelBtn);
	}

	//TC-06 Verify Leave Group functionality
	/**
	 * This method simulate the function of Leaving an existing group
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void chkMembersGroup() throws InterruptedException 
	{
		//login();
		GenericFunctions.WaitForElement(driver, groupBtn);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		//API Call
		GenericFunctions.WaitForElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Action Button");
		GenericFunctions.clickElement(driver, actionBtn);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Leave Group Button");
		GenericFunctions.clickElement(driver, leaveGrp);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Close Button");
		GenericFunctions.clickElement(driver, cancelBtn);
	}
	
	//TC-07 Verify Invite 
	/**
	 * This method simulates the function of generating the group invite and sharing with others
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void chkshareFriendsGroup() throws InterruptedException 
	{
		//login();
		GenericFunctions.WaitForElement(driver, groupBtn);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, groupBtn);
		
		//API Call
		GenericFunctions.WaitForElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		GenericFunctions.clickElement(driver, selectGrp);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Action Button");
		GenericFunctions.clickElement(driver, actionBtn);
		Thread.sleep(10000);
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Invite Friends to Group Button");
		GenericFunctions.clickElement(driver, invfriendsGrp);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Invite Friends to Group Header Text");
		GenericFunctions.getText(driver, sharefriendsHeaderTxt, "Share");
		
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on invite message through copy button");
		GenericFunctions.clickElement(driver, copybtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GenericFunctions.clickElement(driver, backBtn);
	}

	//TC-08 Redeem Reward functionality
	/**
	 * This method simulates the function of Redeeming the rewards in the mobile app
	 */
	public void redeemReward()
	{
		login();
		//Redeem Reward Functionality
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Redeem Reward Button");
		GenericFunctions.clickElement(driver, redeemRewardbtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Header Text");
		GenericFunctions.getText(driver, redeemRewardHeaderTxt, "Rewards");
		
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Question Header Text");
		GenericFunctions.getText(driver, redeemRewardQstHeaderTxt, "FAQs");
		
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Terms and Conditions text");
		GenericFunctions.androidScroll(driver, "Terms and Conditions");
		
		ExtentReportManager.getTest().log(Status.PASS, "Verified Redeem Reward Navigation Flow");
			
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Profile Button");
		GenericFunctions.clickElement(driver,profileBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Rewards Button");
		GenericFunctions.clickElement(driver, redeemRewardHeaderTxt);
		
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Terms and Conditions text");
		GenericFunctions.androidScroll(driver, "Rewards");
		logout();
	}
	
	/**
	 * This method simulates the function of Login to the application
	 */
	public void login()
	{
		if (GenericFunctions.isElementDisplayed(skip))
		{
		GenericFunctions.clickElement(driver, skip);
		System.out.println("Clicked skip");
		}
		GenericFunctions.clickElement(driver,continueWithEmail);
		GenericFunctions.WaitForElement(driver, enterYourEmail);
		username.clear();
		GenericFunctions.sendKeys(driver, username, "divyateja.chepuru@bain.com");
		GenericFunctions.clickElement(driver, countinue);
		GenericFunctions.WaitForElement(driver, enterYourPassword);
		GenericFunctions.sendKeys(driver, password, "Pass@123");
		GenericFunctions.clickElement(driver, logIn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method simulates the function of Logout
	 */
	public void logout()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Profile Button");
		GenericFunctions.clickElement(driver,profileBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Wait for log out link to be appeared");
		//GenericFunctions.WaitForElement(driver, logoutLnk);
		GenericFunctions.scroll(driver, "Log out", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Log out Link view");
		ExtentReportManager.getTest().log(Status.INFO, "Click on Log out Link");
		//GenericFunctions.androidScroll(driver, "Log out");
		GenericFunctions.clickElement(driver,logoutLnk);
				
	}
	
	/** All Live Event related Code **/
	
	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")	
	private MobileElement seeAllliveeventsBtn;	

	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")	
	private MobileElement liveEventTitle;		
		
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Discover live events']")	
	@iOSXCUITFindBy(id="Discover live events")	
	private MobileElement discoverLiveEventsLink;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Upcoming']")	
	@iOSXCUITFindBy(id="Upcoming")	
	private MobileElement textUpcomingHeading;			

	
    /**
     * Getters for Upcoming Live Event Title Element.
     * 
     * @return MobileElement Upcoming Live Event Button 
     */
	public MobileElement getUpcomingLiveEventTitle() {	
		return liveEventTitle;	
	}	

	/**
	 * Taps on Live Events button from the Home Page to open Live Events Page. 
	 *  
	 *  @return {@link LiveEventsPage} Object of Live Events Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */			
	public LiveEventsPage goToLiveEventsPage() throws InterruptedException	
	{	
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Live Events Button from Home Page");
		GenericFunctions.WaitForElement(driver, liveEventsLink);
		GenericFunctions.clickElement(driver, liveEventsLink);
		if (GenericFunctions.isElementDisplayed(liveEventsLink)) {
			GenericFunctions.clickElement(driver, liveEventsLink);
		}
		LiveEventsPage LEP=new LiveEventsPage(driver, platformName);	
		return LEP;	
	}	

	/**
	 * Taps on For You Menu from the Tab bar on the bottom of the Home page. 
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */				
	public void clickOnForYouButton() throws InterruptedException	
	{	
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on \"For You\" Button from Tab Bar");
		GenericFunctions.WaitForElement(driver, forYouBtn);
		GenericFunctions.clickElement(driver, forYouBtn);	
		Thread.sleep(1000);	
	}	

	/**
	 * Clicks on see all upcoming live events link from the Home Page. 
	 *  
	 *  @return {@link MyLiveEventsPage} Object of My assigned Live Events Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */				
	public MyLiveEventsPage goToMyEventsPage() throws InterruptedException	
	{	
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on See All Upcoming Live Events link from Home Page");	
		GenericFunctions.scroll(driver, "Upcoming", platformName);
		GenericFunctions.tapByCordinates(driver, textUpcomingHeading.getLocation().x+900, textUpcomingHeading.getLocation().y + 20);
		ExtentReportManager.getTest().log(Status.INFO, "Opening My Live Events Page");	
		MyLiveEventsPage MLEP=new MyLiveEventsPage(driver, platformName);	
		return MLEP;	
	}	
	
	/**
	 * Taps on Discover Live Events button from the Home Page to open Live Events Page. 
	 *  
	 *  @return {@link LiveEventsPage} Object of Live Events Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */				
	public LiveEventsPage discoverLiveEventsPage() throws InterruptedException	
	{	
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover Live Events Button from Home Page");
		GenericFunctions.WaitForElement(driver, discoverLiveEventsLink);
		GenericFunctions.clickElement(driver, discoverLiveEventsLink);	
		LiveEventsPage LEP=new LiveEventsPage(driver, platformName);	
		return LEP;	
	}	

	/**
	 * Taps on First upcoming Live Events Card from the Home Page to Events Details page. 
	 *  
	 *  @return {@link EventDetailsPage} Object of Live Events Page
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */				
	public EventDetailsPage goToEventDetailsPage() throws InterruptedException	
	{	
		ExtentReportManager.getTest().log(Status.INFO, "Click on Upcoming Event Card");	
		GenericFunctions.scroll(driver, "Upcoming", platformName);
		GenericFunctions.tapByCordinates(driver, textUpcomingHeading.getLocation().x+100, textUpcomingHeading.getLocation().y + 200);
		ExtentReportManager.getTest().log(Status.INFO, "Opening Live Event Details Page");	
		EventDetailsPage EDP =new EventDetailsPage(driver, platformName);	
		return EDP;			
	}
		
//-----Team 1 Functions-----	

	/**
	 * Opens <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> page
	 * 
	 * @return Object of MomentsLogPage
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public MomentsLogPage goToMomentsLogPage() throws InterruptedException {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Moments Log Option");
		GenericFunctions.clickElement(driver, momentsLogLink);
		MomentsLogPage momentsLogPage = new MomentsLogPage(driver, platformName);
		return momentsLogPage;
	}

	/**
	 * Scrolls to <b>Recommended content</b> section
	 * 
	 */
	public void goToRecommendedContent() {
		ExtentReportManager.getTest().log(Status.INFO, "Navigating to Recommended Content");
		GenericFunctions.scroll(driver, "Recommended content", platformName);
	}
	
	/**
	 * Taps <b>{@value com.pom.crimson.util.Constants#ADD_TO_MOMENTS}</b> link on child age card
	 * 
	 * @return Object of AddNewMomentsPage
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public AddNewMomentsPage tapAddToMoments() throws InterruptedException {
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling to "+Constants.ADD_TO_MOMENTS);
		GenericFunctions.scroll(driver, Constants.ADD_TO_MOMENTS, platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Tapping on"+Constants.ADD_TO_MOMENTS+" link");
		GenericFunctions.clickElement(driver, addToMoments);
		AddNewMomentsPage addNewMomentsPage = new AddNewMomentsPage(driver, platformName);
		return addNewMomentsPage;
		
	}
	
	/**
	 * Taps <b>...</b> link on first article in <b>Recommended content</b> section
	  * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void tapShare() throws InterruptedException {
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling to Recommended content");
		GenericFunctions.scroll(driver, "Recommended content", platformName);
		GenericFunctions.scroll(driver, "read", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Tapping to share on Content");
		GenericFunctions.clickElement(driver, share);
	}
	
	/**
	 * Gets text of first article in <b>Recommended content</b> section
	 * 
	 * @param platformName Platform name (Android/iOS)
	 * @return Text of first article as String
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public String getTextOfFirstElementInRecommendedContent(String platformName) throws InterruptedException {
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling to Recommended content");
		GenericFunctions.scroll(driver, "Recommended content", platformName);
		GenericFunctions.scroll(driver, "read", platformName);
		String text="";
		switch(platformName)
		{
		case "Android":
		{
		try {
		MobileElement ele=driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView"));
		text=ele.getAttribute(text);
		} catch (Exception e)
		{
			System.out.println("First element not found");
		}}
		break;
		case "iOS":
			break;
		}
		return text;
	}
	
	/**
	 * Taps <b>See all</b> link in <b>Recommended content</b> section
	 * 
	 * @return Object of ExplorePage
	 */
	public ExplorePage goToRecommendedContentAndClickSeeAll() {
		goToRecommendedContent();
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on  See all");
		GenericFunctions.clickElement(driver, seeAll);
		ExplorePage explorePage = new ExplorePage(driver, platformName);
		return explorePage;
	}

	/**
	 * Taps first article in <b>Recommended content</b> section by clicking on read text
	 * 
	 * @return Object of ContentPage
	 */
	public ContentPage goToRecommendedContentAndClickArticle() {
		goToRecommendedContent();
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Article on Home Page");
		GenericFunctions.clickElement(driver, readText);
		ContentPage contentPage = new ContentPage(driver, platformName);
		return contentPage;
	}
	
	//not in use-can be removed
	public boolean validateIfHomePageIsDisplayed() {
		return GenericFunctions.WaitForElement(driver, profileLink);
	}
}