package com.pom.crimson.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * Moments log page can be used  view all moments.
 * <br> 
 * User can navigate to this page by going to:
 * <br> 
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget <br>
 * <br>
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class MomentsLogPage extends BasePage {
	
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADD_YOUR_FIRST_MOMENT_BTN_TEXT+"']")
	private MobileElement addYourFirstMomentBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.VACCINE_LOG_ADDNEWMOMENT_LINK+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.VACCINE_LOG_ADDNEWMOMENT_LINK+"']")
	private MobileElement addNewMomentBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.VIEW_VACCINE_LOG_LINK+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.VIEW_VACCINE_LOG_LINK+"']")
	private MobileElement viewVaccineLogBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.VIEW_GROWTH_LOG_LINK+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.VIEW_GROWTH_LOG_LINK+"']")
	private MobileElement viewGrowthLogBtn;
	
	//android.widget.TextView[@text='View growth log']
	
	//not available for ios
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement backBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.EDITMOMENT_TITLE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.EDITMOMENT_TITLE+"']")
	private MobileElement editMomentBtn;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_GROUPCHAT+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.SHARE_IN_GROUPCHAT+"']")
	private MobileElement shareInGroupChat;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.SHARE_IN_OTHERAPPS+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.SHARE_IN_OTHERAPPS+"']")
	private MobileElement shareInOtherApps;
	
	public MomentsLogPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}

	/**
	 * Opens Vaccine log page by tapping on <b>{@value com.pom.crimson.util.Constants#VIEW_VACCINE_LOG_LINK}</b> link
	 * 
	 * @return Object of VaccineLogPage
	 * 
	 */
	public VaccineLogPage goToVaccineLogPage() {
		GenericFunctions.scroll(driver, "View vaccine log", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on View vaccine log link on Moments Log Page");
		GenericFunctions.clickElement(driver, viewVaccineLogBtn);
		VaccineLogPage vaccineLogPage=new VaccineLogPage(driver,platformName);
		return vaccineLogPage;
	}

	/**
	 * Opens Growth log page by tapping on <b>{@value com.pom.crimson.util.Constants#VIEW_GROWTH_LOG_LINK}</b> link
	 * 
	 * @return Object of GrowthLogPage
	 * 
	 */
	public GrowthLogPage goToGrowthLogPage() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GenericFunctions.scroll(driver, "View growth log", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on View growth log link on Moments Log Page");
		GenericFunctions.clickElement(driver, viewGrowthLogBtn);
		GrowthLogPage growthLogPage=new GrowthLogPage(driver,platformName);
		return growthLogPage;
	}

	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#ADD_YOUR_FIRST_MOMENT_BTN_TEXT}</b> button
	 * 
	 * @return Object of AddNewMomentsPage
	 * 
	 */
	public AddNewMomentsPage tapAddYourFirstMomentBtn() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Add Your First Moment button on Moments Log Page");
		GenericFunctions.clickElement(driver, addYourFirstMomentBtn);
		AddNewMomentsPage addNewMomentsPage=new AddNewMomentsPage(driver,platformName);
		return addNewMomentsPage;
		
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b> link
	 * 
	 * @return Object of AddNewMomentsPage
	 * 
	 */
	public AddNewMomentsPage tapAddNewMomentBtn() {
		GenericFunctions.scroll(driver, "Add new moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Add New Moment button on Moments Log Page ");
		GenericFunctions.clickElement(driver, addNewMomentBtn);
		AddNewMomentsPage addNewMomentsPage=new AddNewMomentsPage(driver,platformName);
		return addNewMomentsPage;
		
	}
	
	/**
	 * Taps on back button
	 * 
	 * @return Object of HomePage
	 * 
	 */
	public HomePage tapBackBtn()
	{
		GenericFunctions.scroll(driver,"Moments log", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on back button on Moments log page");
		GenericFunctions.clickElement(driver, backBtn);
		HomePage homePage=new HomePage(driver,platformName);
		return homePage;
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_TITLE}</b> link.
	 * 
	 * @return Object of EditMomentPage
	 */
	public EditMomentPage tapEditMomentBtn()
	{
		GenericFunctions.scroll(driver,"Edit moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Edit moment button on Moments log page");
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
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Share in group chat button on Moments log page");
		GenericFunctions.clickElement(driver, shareInGroupChat);
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#SHARE_IN_OTHERAPPS}</b> link.
	 * 
	 */
	public void tapShareInOtherAppsBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Share in Other Apps button on Moments log page");
		GenericFunctions.clickElement(driver, shareInOtherApps);
	}	
}
