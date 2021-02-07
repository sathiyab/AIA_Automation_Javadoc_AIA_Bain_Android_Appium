package com.pom.crimson.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * This is the Page Class for <b>Home page</b> which contains elements and functions related to Groups module.
 * <br>
 * 
 * @author Indirani 
 */	
public class GroupHomePage extends BasePage{
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */	
	public GroupHomePage(AppiumDriver<MobileElement> driver, String platformName) {
		super(driver, platformName);
		
	}
	
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
	
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='SampleGroup_007']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Pregnancy']")
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
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Invite friends to group (26/50)']")
	private MobileElement invfriendsGrp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Share']")
	private MobileElement sharefriendsHeaderTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Leave group']")
	private MobileElement leaveGrp;
		
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Live events']")
	private MobileElement liveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your live events']")
	private MobileElement yourliveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Live events']")
	private MobileElement allliveeventsTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='See All']")
	private MobileElement seeAllLnk;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='See all live events']")
	private MobileElement seeAllliveeventsBtn;
	
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
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Groups, tab, 2 of 4']")
	private MobileElement groupBtn;
	
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='SampleGroup_007']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Pregnancy']")
	private MobileElement selectGrp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Already joined']")
	private MobileElement alreadyJoinedGroupTxt;
		
	@AndroidFindBy(xpath="//android.widget.TextView[@text='By participating you agree to lorem ipsum dolor sit amet']")
	private MobileElement joinedgroupdescription;
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='For you, tab, 1 of 4']")
	private MobileElement forYouBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Talk to a doctor or nurse']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[normalize-space(@label)='Specialist chat']")
	private MobileElement specialistChatLink;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Next']")
	private MobileElement nextBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Live events']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[normalize-space(@label)='Live events']")
	private MobileElement liveEventsLink;

	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Profile, tab, 4 of 4']")
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
	
	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	private MobileElement closegroupbtn;
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='acceptConsentButton']")
	private MobileElement agreementbtn;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='android:id/chooser_copy_button']")
	private MobileElement copybtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='6']")
	private MobileElement ratingNoBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	private MobileElement ratingTxt;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='0']")
	private MobileElement ratingSubmitBtn;
		
	
	/*public GroupHomePage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}*/
	
	/**
	 * Taps on <b>Chat</b> button in the Tab bar of the home page.
	 *  
	 */	
	public void groupBtn_click()
	{
		GenericFunctions.WaitForElement(driver, groupBtn);
		GenericFunctions.clickElement(driver, groupBtn);
	}

	/**
	 * Taps on <b>Discover new groups</b> button in Chat page.
	 *  
	 */	
	public void groupdiscoverBtn_click()
	{
		GenericFunctions.clickElement(driver, discoverGroup);
	}
	
	/**
	 * Taps on <b>Discover new groups</b> button in Chat page.
	 *  
	 */	
	public void joinGroupBtn_click()
	{
		GenericFunctions.clickElement(driver, joinGroupBtn);
	}
	
	/**
	 * Taps on <b>Join group</b> button in Groups menu.
	 *  
	 */		
	public void verifyGroupHeader()
	{
		GenericFunctions.getText(driver, allgroupTxt, "All groups");
	}
	
	/**
	 * Taps on <b>For you</b> menu in the Tab bar to open the Home page.
	 *  
	 */			
	public void forYouBtn()
	{
		GenericFunctions.clickElement(driver, forYouBtn);
	}
	
	/**
	 * Taps on <b>Talk to a doctor or nurse</b> button in the Home page to open Specialist Chat page.
	 *  
	 */		
	public void specialistChatLink()
	{
		GenericFunctions.clickElement(driver, specialistChatLink);
	}
	
	/**
	 * Taps on <b>Close</b> button in the Group page.
	 *  
	 */		
	public void closegroupbtn()
	{
		GenericFunctions.clickElement(driver, closegroupbtn);
	}
	
	/**
	 * Taps on <b>Next</b> button in the Specialist chat page.
	 *  
	 */		
	public void nextBtn()
	{
		
		GenericFunctions.clickElement(driver, nextBtn);
	}	

	/**
	 * Taps on <b>Next</b> button in the Intro screens.
	 *  
	 */	
	public void scrollnext()
	{
		GenericFunctions.scroll(driver, "Next", platformName);
		//GenericFunctions.androidScroll(driver, "Next");
	}
		
		
	/**
	 * Taps on <b>Live events</b> button in the Home page to Open the Live events page.
	 *  
	 */		
	public void liveEventsLink()
	{
		GenericFunctions.clickElement(driver, liveEventsLink);
	}
	
	/**
	 * Gets the text of "Live events" element in the home page.
	 *  
	 */		
	public void liveeventsTxt()
	{
		GenericFunctions.getText(driver, liveeventsTxt, "Live events");
	}
	
	/**
	 * Clicks on <b>All live events</b> button in the Live events page.
	 *  
	 */		
	public void seeAllliveeventsBtn()
	{
		GenericFunctions.clickElement(driver, seeAllliveeventsBtn);
	}
	
	/**
	 * Gets the text of "All live events" button element in the Live events page.
	 *  
	 */	
	public void allliveeventsTxt()
	{
		GenericFunctions.getText(driver, allliveeventsTxt, "All live events");		
	}
	
	/**
	 * Taps on the back button
	 * 
	 */
	public void backBtn()
	{
		GenericFunctions.clickElement(driver, backBtn);
	}
	
	/**
	 * Taps on <b>Redeem reward</b> button in the home page
	 * 
	 */
	public void redeemRewardbtn()
	{
		GenericFunctions.clickElement(driver, redeemRewardbtn);
	}

	/**
	 * Gets the text of "Redeem reward" button element in the Home page.
	 *  
	 */		
	public void redeemRewardTxt()
	{
		GenericFunctions.getText(driver, redeemRewardHeaderTxt, "Rewards");
	}

	/**
	 * Gets the text of "FAQs" element in the Profile Menu list page.
	 *  
	 */			
	public void redeemRewardFAQ()
	{
		GenericFunctions.getText(driver, redeemRewardQstHeaderTxt, "FAQs");
	}
	
	/**
	 * Scrolls to the text "Terms and Conditions" element in the Rewards page.
	 *  
	 */		
	public void termsandCondition()
	{
		GenericFunctions.androidScroll(driver, "Terms and Conditions");
	}
	
	/**
	 * Taps on <b>For you</b> menu in the Tab bar to open the Home page.
	 *  
	 */		
	public void foryoubtn()
	{
		GenericFunctions.clickElement(driver, forYouBtn);
	}
	
	/**
	 * Gets the text of "Your consultation" element in the Consultation page.
	 *  
	 */		
	public void consultationHeaderTxt()
	{
		GenericFunctions.getText(driver, consultationHeaderTxt, "Your consultation");
	}
	
	/**
	 * Scrolls to the text "Start consultation" element in the Consultation page.
	 *  
	 */		
	public void startscrollconsultation()
	{
		GenericFunctions.androidScroll(driver, "Start consultation");
	}
	
	/**
	 * Taps on <b>Start consultation</b> button in the Consultation page.
	 *  
	 */			
	public void clickConsultBtn()
	{
		GenericFunctions.clickElement(driver, startConsultBtn);
	}
	
	/**
	 * Taps on <b>Accept consent</b> button in the Consultation page.
	 *  
	 */	
	public void clickAgreementBtn()
	{
		GenericFunctions.clickElement(driver, agreementbtn);
	}
	
	/**
	 * Taps on <b>Close</b> button.
	 *  
	 */		
	public void clickCloseBtn()
	{
		GenericFunctions.clickElement(driver, closeBtn);
	}
	
	/**
	 * Taps on <b>Confirm</b> button.
	 *  
	 */		
	public void clickConfirmBtn()
	{
		GenericFunctions.clickElement(driver, confirmBtn);
	}
	
	/**
	 * Gets the text of "Rating" element in the Consultation page.
	 *  
	 */
	public void ratingTxt()
	{
		GenericFunctions.getText(driver, ratingTxt, "On a scale of 0 to 10,how likely are you to recommend Tele-consult to your friend or a colleague?");
	}
	
	/**
	 * Taps on <b>Rating</b> button.
	 *  
	 */		
	public void ratingNoBtn()
	{
		GenericFunctions.clickElement(driver, ratingNoBtn);
	}

	/**
	 * Taps on <b>Rating summary button</b> button.
	 *  
	 */			
	public void ratingSubmitBtn()
	{
		GenericFunctions.clickElement(driver, ratingSubmitBtn);
	}

	/**
	 * Taps on a particular group in Groups page.
	 *  
	 */				
	public void selectGrp()
	{
		//GenericFunctions.WaitForElement(driver, selectGrp);
		GenericFunctions.scroll(driver, "Pregnancy", platformName);
		GenericFunctions.clickElement(driver, selectGrp);
	}
	
	/**
	 * Taps on a particular group in Groups page.
	 *  
	 */	
	public void verifySelectGrp()
	{
		//GenericFunctions.getText(driver, selectGrp, "SampleGroup_007");
		//GenericFunctions.getText(driver, selectGrp, "Pregnancy");
		GenericFunctions.clickElement(driver, selectGrp);
	}
	
	/**
	 * Gets the text of "Already joined" element in the Groups page.
	 *  
	 */	
	public void alreadyJoinedGroupTxt()
	{
		GenericFunctions.getText(driver, alreadyJoinedGroupTxt, "Already joined");
	}
	
	/**
	 * Taps on a <b>All groups</b> in Groups page.
	 *  
	 */		
	public void allgroupTxtbtn()
	{
		GenericFunctions.clickElement(driver, allgroupTxt);
	}

	/**
	 * Gets the text of "Joined group" element in the Groups page.
	 *  
	 */		
	public void joinedgroupdescription()
	{
		GenericFunctions.getText(driver, joinedgroupdescription, "By participating you agree to lorem ipsum dolor sit amet");
	}
	
	//TC-04 Verify Member Group functionality
	/**
	 * Taps on the "Group menu" button in Groups page
	 *  
	 */	
	public void actionBtn()
	{
		GenericFunctions.clickElement(driver, actionBtn);
	}
	
	/**
	 * Taps on the "Members" menu in Groups page
	 *  
	 */		
	public void clickgrpInfoLnk()
	{
		GenericFunctions.WaitForElement(driver, grpInfoLnk);
		GenericFunctions.clickElement(driver, membersLnk);
	}
	
	/**
	 * Gets the text of "Member" element in the Groups page.
	 *  
	 */		
	public void memberHeaderTxt()
	{
		GenericFunctions.getText(driver, memberHeaderTxt, "Member");
	}
	
	/**
	 * Taps on the "Cancel" button
	 *  
	 */		
	public void cancelBtn()
	{
		GenericFunctions.clickElement(driver, cancelBtn);
	}
	
	/**
	 * Taps on "Group info" button in Groups page
	 *  
	 */	
	public void groupInfo()
	{
		GenericFunctions.WaitForElement(driver, grpInfoLnk);
		GenericFunctions.clickElement(driver, grpInfoLnk);
	}
	
	/**
	 * Gets the text of "Group info" element in the Groups page.
	 *  
	 */		
	public void groupInfoTxt()
	{
		GenericFunctions.getText(driver, grpInfoLnk, "Group info");
	}

	//TC-06 Verify Leave Group functionality
	/**
	 * Taps on "Leave group" button in Groups page
	 *  
	 */		
	public void leaveGrp()
	{
		GenericFunctions.clickElement(driver, leaveGrp);
	}
		
	//TC-07 Verify Invite
	/**
	 * Taps on "Invite group" button in Groups page
	 *  
	 */			
	public void invfriendsGrp()
	{
		GenericFunctions.clickElement(driver, invfriendsGrp);
	}

	/**
	 * Taps on "Share" button in Groups page
	 *  
	 */				
	public void sharefriendsHeaderTxt()
	{
		GenericFunctions.getText(driver, sharefriendsHeaderTxt, "Share");
	}

	/**
	 * Taps on "Copy" button in Groups page
	 *  
	 */				
	public void copybtn()
	{
		GenericFunctions.clickElement(driver, copybtn);
	}
		
	//TC-08 Redeem Reward functionality
	/**
	 * Taps on <b>Profile</b> menu in the Tab bar to open the Profiles page.
	 *  
	 */		
	public void profileBtn()
	{
		GenericFunctions.clickElement(driver,profileBtn);
	}
	
	/**
	 * Scrolls to <b>Reward</b> Text in profiles page
	 */
	public void rewardsscroll()
	{
		GenericFunctions.androidScroll(driver, "Rewards");
	}
	
	/**
	 * Scrolls to <b>Terms and Conditions</b> Text in profiles page
	 */	
	public void termsConditionScroll()
	{
		GenericFunctions.androidScroll(driver, "Terms and Conditions");
	}
	
	/**
	 * Taps on <b>Redeem</b> button in the Home page.
	 *  
	 */		
	public void redeemRewardHeaderTxtClick()
	{
		GenericFunctions.clickElement(driver, redeemRewardHeaderTxt);
	}
	
	/**
	 * Method used to login to the mobile app.
	 *  
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
		GenericFunctions.sendKeys(driver, username, "venkat.testuser2@gmail.com");
		GenericFunctions.clickElement(driver, countinue);
		GenericFunctions.WaitForElement(driver, enterYourPassword);
		GenericFunctions.sendKeys(driver, password, "Welcome@100");
		GenericFunctions.clickElement(driver, logIn);
	}

	/*public void logout()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Profile Button");
		GenericFunctions.clickElement(driver,profileBtn);
		
		ExtentReportManager.getTest().log(Status.INFO, "Wait for log out link to be appeared");
		GenericFunctions.WaitForElement(driver, logoutLnk);
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Log out Link view");
		ExtentReportManager.getTest().log(Status.INFO, "Click on Log out Link");
		GenericFunctions.androidScroll(driver, "Log out");
		GenericFunctions.clickElement(driver,logoutLnk);
				
	}*/

	
	
	public void test() throws  InterruptedException {
    	List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.ImageView[@index='0']"));
    	//alltext.get(0).click();
    	alltext.get(2).click();
        	
	}
}
