package com.pom.crimson.pages;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * Vaccine log page can be used  view moments of Child profiles saved with milestone <b>{@value com.pom.crimson.util.Constants#MILESTONE_VACCINEDAY}</b> .
 * <br> 
 * User can navigate to this page by going to:
 * <br> 
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget <br>
 * Click <b>{@value com.pom.crimson.util.Constants#VIEW_VACCINE_LOG_LINK}</b> link <br>
 * <br>
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class VaccineLogPage extends BasePage {
	
	//not available for ios
	//@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]")
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView")
	private MobileElement backBtn;
	
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.VACCINE_LOG_ADDNEWMOMENT_LINK+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.VACCINE_LOG_ADDNEWMOMENT_LINK+"']")
	private MobileElement addNewMomentsBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.EDITMOMENT_TITLE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.EDITMOMENT_TITLE+"']")
	private MobileElement editMomentBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_GROUPCHAT+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.SHARE_IN_GROUPCHAT+"']")
	private MobileElement shareInGroupChat;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_OTHERAPPS+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.SHARE_IN_OTHERAPPS+"']")
	private MobileElement shareInOtherApps;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[normalize-space(@label)='"+Constants.EMPTYSTATE_VACCINE_LOG_MESSAGE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='"+Constants.EMPTYSTATE_VACCINE_LOG_MESSAGE+"']")
	private MobileElement emptyStateVaccineLogDescription;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'Record precious moments and milestones throughout your') and contains(@text, 'wellness journey')]")
	private MobileElement addNewMomentDescription;
	
	public VaccineLogPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}

	/**
	 * Taps on thumbnail image of child.
	 * 
	 * @param childPreferedName Preferred name of Child
	 * 
	 */
	public void tapChildBtn(String childPreferedName)
	{
		GenericFunctions.scroll(driver,childPreferedName, platformName);
		MobileElement ele=GenericFunctions.findElementByText(driver, childPreferedName, platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Selecting profile as :"+childPreferedName);
		GenericFunctions.clickElement(driver, ele);
	}
	
	/**
	 * Taps on back button
	 * @return Object of MomentsLogPage
	 */
	public MomentsLogPage tapBackBtn()
	{
		GenericFunctions.scroll(driver,"Vaccine log", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on back button on Vaccine log page");
		GenericFunctions.clickElement(driver, backBtn);
		MomentsLogPage momentsLogPage=new MomentsLogPage(driver,platformName);
		return momentsLogPage;
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b> button.
	 * 
	 * @return Object of AddNewMomentsPage
	 */
	public AddNewMomentsPage tapAddNewMomentBtn()
	{
		GenericFunctions.scroll(driver,"Add new moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Add new moment button on Vaccine log page");
		GenericFunctions.clickElement(driver, addNewMomentsBtn);
		AddNewMomentsPage addNewMomentsPage=new AddNewMomentsPage(driver,platformName);
		return addNewMomentsPage;
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_TITLE}</b> link.
	 * 
	 * @return Object of EditMomentPage
	 */
	public EditMomentPage tapEditMomentBtn()
	{
		GenericFunctions.scroll(driver,"Edit moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Edit moment button on Vaccine log page");
		GenericFunctions.clickElement(driver, editMomentBtn);
		EditMomentPage editMomentPage=new EditMomentPage(driver,platformName);
		return editMomentPage;
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#SHARE_IN_GROUPCHAT}</b> link.
	 * 
	 */
	public void tapShareInGroupChatBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Share in group chat button on Vaccine log page");
		GenericFunctions.clickElement(driver, shareInGroupChat);
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#SHARE_IN_OTHERAPPS}</b> link.
	 * 
	 */
	public void tapShareInOtherAppsBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Share in Other Apps button on Vaccine log page");
		GenericFunctions.clickElement(driver, shareInOtherApps);
	}
	
	/**
	 * Verifies empty state of page when no moments are created with <b>{@value com.pom.crimson.util.Constants#MILESTONE_VACCINEDAY}</b> milestone .
	 * <br>
	 * It checks for presence of text <b>Record and keep track of the vaccines given to your child, all in one place</b>
	 * 
	 * @return boolean value <br>
	 *         true:  <b>Record and keep track of the vaccines given to your child, all in one place</b> text is displayed <br>
	 *         false: <b>Record and keep track of the vaccines given to your child, all in one place </b> text is not displayed
	 * 
	 */
	public boolean verifyEmptyStateMessage()
	{
		return 	GenericFunctions.isElementDisplayed(emptyStateVaccineLogDescription);

	}
	
	/**
	 * Verifies if <b>Record precious moments and milestones throughout your family's wellness journey</b> text is displayed.
	 * <br>
	 * 
	 * @return boolean value <br>
	 *         true:   <b>Record precious moments and milestones throughout your family's wellness journey</b> text is displayed <br>
	 *         false:  <b>Record precious moments and milestones throughout your family's wellness journey</b> text is not displayed
	 * 
	 */
	public boolean verifyAddNewMomentDescription()
	{
		return 	GenericFunctions.isElementDisplayed(addNewMomentDescription);
	}


}
