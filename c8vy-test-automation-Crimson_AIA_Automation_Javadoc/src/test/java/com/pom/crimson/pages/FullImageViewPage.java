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
 * FullI Image View Page opens an image in Moments log in full page view <br>
 * User can view images in carousal. Edit, Share image in Group chat or externally via this page.
 * 
 * User can navigate to this page by :<br><br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget <br>
 * Click <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b> link <br>
 * Create a moment with image<br>
 * Once moment is created,Tap on moment. 
 * <br><br>
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class FullImageViewPage extends BasePage {
	
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.EDITMOMENT_TITLE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.EDITMOMENT_TITLE+"']")
	private MobileElement editMomentBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_GROUPCHAT+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.SHARE_IN_GROUPCHAT+"']")
	private MobileElement shareInGroupChat;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_OTHERAPPS+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.SHARE_IN_OTHERAPPS+"']")
	private MobileElement shareInOtherApps;
	
	//not available for ios
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.ImageView")
	private MobileElement crossBtn;
	
	//not available for ios
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView")
	private MobileElement threeDotsBtn;
	
	//not available for ios
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='1']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='1']")
	private MobileElement firstIndex_carousal;
	
	
	public FullImageViewPage(AppiumDriver<MobileElement> driver ,String platformName)
	{
		super(driver,platformName);
	}

	
	/**
	 * Opens <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_TITLE}</b> page.<br>
	 * It first clicks on <b>...</b> link on top left corner of page and than clicks on
	 * <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_TITLE}</b> link in menu popped up from bottom of page.
	 * 
	 * @return Object of EditMomentPage
	 */
	public EditMomentPage tapEditMomentBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on three dots(More) button on Full Image View page");
		GenericFunctions.clickElement(driver, threeDotsBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Edit moment button on Full Image View page");
		GenericFunctions.clickElement(driver, editMomentBtn);
		EditMomentPage editMomentPage=new EditMomentPage(driver,platformName);
		return editMomentPage;
	}
	
	/**
	 * Opens menu to share moment to a group, showing groups use is part of.
	 * It first clicks on <b>...</b> link on top left corner of page and than clicks on
	 * <b>{@value com.pom.crimson.util.Constants#SHARE_IN_GROUPCHAT}</b> link in menu popped up from bottom of page.
	 * 
	 */
	public void tapShareInGroupChatBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on three dots(More) button on Full Image View page");
		GenericFunctions.clickElement(driver, threeDotsBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Share in group chat button on Full Image View  page");
		GenericFunctions.clickElement(driver, shareInGroupChat);
	}
	
	/**
	 * Opens menu to share moment to Other Apps, showing apps in which user can share moment.
	 * It first clicks on <b>...</b> link on top left corner of page and than clicks on
	 * <b>{@value com.pom.crimson.util.Constants#SHARE_IN_OTHERAPPS}</b> link in menu popped up from bottom of page.
	 * 
	 */
	public void tapShareInOtherAppsBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on three dots(More) button on Full Image View page");
		GenericFunctions.clickElement(driver, threeDotsBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Share in Other Apps button on Full Image View page");
		GenericFunctions.clickElement(driver, shareInOtherApps);
	}
	
	/**
	 * Taps on cross button
	 */
	public void tapCrossBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Cross button button on Full Image View page");
		GenericFunctions.clickElement(driver, crossBtn);
	}
	
	/**
	 * Taps on <b>...</b> link on top left corner of page
	 */
	public void tapThreeDots_MoreBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on three dots(More) button on Full Image View page");
		GenericFunctions.clickElement(driver, threeDotsBtn);
		
	}
	
	/**
	 * Verifies if  <b>...</b> link on top left corner of page is displayed
	 * 
	 * @return boolean value <br>
	 *         true:  <b>...</b> link is displayed <br>
	 *         false: <b>...</b> link is not displayed
	 * 
	 */
	public boolean verifyThreeDotsButtonDisplayed()
	{
		return GenericFunctions.isElementDisplayed(threeDotsBtn);
	}
	
	/**
	 * Verifies if carousal is displayed.<br>
	 * 
	 * @return boolean value <br>
	 *         true:  carousal  is displayed <br>
	 *         false: carousal is not displayed
	 */
	public boolean verifyFirstIndexCarousalDisplayed()
	{
		return GenericFunctions.isElementDisplayed(firstIndex_carousal);
	}
	
}
