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
 * Filtered Interest Result Page articles and videos as per specific interest. <br>
 * User can navigate to this page by various ways :<br><br>
 * 1. Go to Home Page <br> Click <b>Content</b> in bottom menu. <br>Click on
 * <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b>
 * link. <br>Click Interest<br><br>
 * 2. Go to Home Page <br> Click <b>Content</b> in bottom menu.<br>Scroll down page and go to <b>{@value com.pom.crimson.util.Constants#CONTENT_DISCOVER_MORE}</b> section<br>
 * Tap on an interest.
 * <br><br>
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class FilteredInterestResultPage extends BasePage {
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.FILTERED_INTEREST_RECOMMEDED+"']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.FILTERED_INTEREST_RECOMMEDED+"']")
	private MobileElement recommendedForYou;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.FILTERED_INTEREST_YOU_MAY_ALSO_LIKE+"']")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.FILTERED_INTEREST_YOU_MAY_ALSO_LIKE+"']")
	private MobileElement youMayAlsoLike;
	
	//No locator in ios
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private MobileElement crossBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.CONTENT_DROP_MESSAGE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.CONTENT_DROP_MESSAGE+"']")
	private MobileElement dropUsAMessage;
	
	//No locator in ios
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'read')][last()]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[contains(@label,'read')][last()]")
	private MobileElement readText;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'Unable to find what') and contains(@text, 'looking for?')]")
	private MobileElement unableToFindDescription;
	
	public FilteredInterestResultPage(AppiumDriver<MobileElement> driver ,String platformName)
	{
		super(driver,platformName);
	}

	/**
	 * Taps on cross button.
	 * 
	 * @return Object of DiscoverTopicsPage
	 */
	public DiscoverTopicsPage tapOnCrossBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on cross button on FilteredInterestResult Page");
		GenericFunctions.clickElement(driver, crossBtn);
		DiscoverTopicsPage discoverTopicsPage=new DiscoverTopicsPage(driver,platformName);
		return discoverTopicsPage;
	}

	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#CONTENT_DROP_MESSAGE} </b>
	 * link.
	 * 
	 * @return Object of ContactUsPage
	 */
	public ContactUsPage tapDropUsMessage()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Drop us a message link on FilteredInterestResult Page");
		GenericFunctions.clickElement(driver, dropUsAMessage);
		ContactUsPage contactUsPage=new ContactUsPage(driver,platformName);
		return contactUsPage;
	}
	
	/**
	 * Opens first article page by tapping on read text.
	 * 
	 * 
	 * @return Object of ContentPage
	 */
	public ContentPage tapArticle()
	{	
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Article on FilteredInterestResult Page");
		GenericFunctions.clickElement(driver, readText);
		ContentPage contentPage=new ContentPage(driver,platformName);
		return contentPage;
	}

	/**
	 * Verifies if text <b>Unable to find what you're looking for?</b> is displayed
	 * 
	 * @return boolean value <br>
	 *         true:  <b>Unable to find what you're looking for?</b> text is displayed <br>
	 *         false: <b>Unable to find what you're looking for?</b> text is not displayed
	 * 
	 */
	public boolean verifyUnableToFindMessage()
	{
		return 	GenericFunctions.isElementDisplayed(unableToFindDescription);
	}


}
