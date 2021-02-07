package com.pom.crimson.pages;

import java.util.List;

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
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * Explore Page or Content page displays articles and videos as per user's interests and joined groups. <br>
 * User can navigate to this page by :<br><br>
 * Go to Home Page <br> Click <b>Content</b> in bottom menu.
 * <br><br>
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class ExplorePage extends BasePage {
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.EXPLORE_DISCOVER_TOPICS+" ']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.EXPLORE_DISCOVER_TOPICS+"']")
	private MobileElement discoverTopics;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.TAB_FOR_YOU+"']")
	private MobileElement forYouBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='']")
	private MobileElement crossBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.EXPLORE_TITLE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.EXPLORE_TITLE+"']")
	private MobileElement titleExplorePage;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@label='"+Constants.CONTENT_SEEALL+"'])[2]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.CONTENT_SEEALL+"']")
	private MobileElement andSeeAllBtn;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@label='"+Constants.CONTENT_SEEALL+"'])[3]")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.CONTENT_SEEALL+"']")
	private MobileElement seeAllBtn;
	
	//Not available for ios
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'read')][last()]")
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[contains(@label,'read')][last()]")
	private MobileElement readText;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.CONTENT_DROP_MESSAGE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.CONTENT_DROP_MESSAGE+"']")
	private MobileElement dropUsAMessage;

	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[contains(@label,\""+Constants.EXPLORE_NOT_PART_OF_GROUPS+"\")]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,\""+Constants.EXPLORE_NOT_PART_OF_GROUPS+"\")]")
	private MobileElement youArentPartOfGroupsText;
	
	public ExplorePage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Opens <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b> page by clicking on
	 * <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b> link.
	 * 
	 * @return Object of DiscoverTopicsPage
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public DiscoverTopicsPage goToDiscoverTopicsPage() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover By Interests link");
		GenericFunctions.clickElement(driver, discoverTopics);
		Thread.sleep(3000);
		DiscoverTopicsPage discoverTopicsPage=new DiscoverTopicsPage(driver,platformName);
		return discoverTopicsPage;
		
	}
	
	/**
	 * Opens <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS}</b> page by clicking on
	 * <b>See all</b> link.
	 * 
	 * @return Object of BasedOnYourGroupsPage
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public BasedOnYourGroupsPage goToBasedOnYourGroupsPage() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on See all button in Based on your groups section");
		GenericFunctions.clickElement(driver, andSeeAllBtn);
		Thread.sleep(3000);
		BasedOnYourGroupsPage basedOnYourGroupsPage=new BasedOnYourGroupsPage(driver,platformName);
		return basedOnYourGroupsPage;
		
	}
	
	/**
	 * Opens <b>{@value com.pom.crimson.util.Constants#YOUR_PREFERRED_INTERESTS}</b> page by clicking on
	 * <b>See all</b> link in <b> Update your interests</b> section.
	 * 
	 * @return Object of AddMoreInterestPage
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public AddMoreInterestPage goToAddMoreInterestPage() throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling to Update your interests section");
		GenericFunctions.WaitForElement(driver, seeAllBtn);
		Thread.sleep(3000);
		GenericFunctions.scroll(driver, "See more videos and articles based on the topics that you choose",platformName);
		GenericFunctions.clickElement(driver, seeAllBtn);
		Thread.sleep(3000);
		AddMoreInterestPage addMoreInterestPage=new AddMoreInterestPage(driver,platformName);
		return addMoreInterestPage;
		
	}
	
	/**
	 * Opens <b>{@value com.pom.crimson.util.Constants#YOUR_PREFERRED_INTERESTS}</b> page by clicking on
	 * interest link in <b> Update your interests</b> section.
	 * 
	 * @param text Interest to be clicked
	 * @return Object of AddMoreInterestPage
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public AddMoreInterestPage tapOnInterest(String text) throws InterruptedException
	{
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling to"+text);
		GenericFunctions.scroll(driver, text,platformName);
		MobileElement ele=GenericFunctions.findElementByText(driver, text,platformName);
		GenericFunctions.clickElement(driver, ele);
		Thread.sleep(3000);
		AddMoreInterestPage addMoreInterestPage=new AddMoreInterestPage(driver,platformName);
		return addMoreInterestPage;
	}
	
	/**
	 * Opens article page by clicking on title of article.
	 * 
	 * 
	 * @param text Title of article page
	 * @return Object of ContentPage
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public ContentPage tapOnContent(String text) throws InterruptedException
	{
		scrollToSection(text);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on"+text);
		MobileElement ele=GenericFunctions.findElementByText(driver, text,platformName);
		GenericFunctions.clickElement(driver, ele);
		Thread.sleep(3000);
		ContentPage contentPage=new ContentPage(driver,platformName);
		return contentPage;
	}
	
	/**
	 * Opens first article page by tapping on read text.
	 * 
	 * 
	 * @return Object of ContentPage
	 */
	public ContentPage tapArticle()
	{	
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Article on Explore Page");
		GenericFunctions.clickElement(driver, readText);
		ContentPage contentPage=new ContentPage(driver,platformName);
		return contentPage;
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#CONTENT_DROP_MESSAGE} </b>
	 * link.
	 * 
	 * @return Object of ContactUsPage
	 */
	public ContactUsPage tapDropUsMessage()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Drop us a message link on Explore Page");
		GenericFunctions.clickElement(driver, dropUsAMessage);
		ContactUsPage contactUsPage=new ContactUsPage(driver,platformName);
		return contactUsPage;
	}
	
	/**
	 * Verifies if Explore or Content page is displayed.
	 * 
	 *
	 * It verifies by checking if
	 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_TITLE} </b>
	 * MobileElement is displayed
	 * 
	 * @return boolean value <br>
	 *         true: <b>{@value com.pom.crimson.util.Constants#EXPLORE_TITLE}
	 *         </b> is displayed <br>
	 *         false:
	 *         <b>{@value com.pom.crimson.util.Constants#EXPLORE_TITLE} </b>
	 *         is not displayed
	 */
	public boolean validateIfExplorePageIsDisplayed()
	{
		scrollToSection(Constants.EXPLORE_BASED_ON_YOUR_GROUPS);
		ExtentReportManager.getTest().log(Status.INFO, "Validating if Title of Explore Page is displayed");
		return GenericFunctions.isElementDisplayed(titleExplorePage);
	}
	
	/**
	 * Verifies {@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS} section when user is not part of any group.
	 * 
	 *
	 * It verifies by checking if
	 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_NOT_PART_OF_GROUPS} </b>
	 * MobileElement is displayed
	 * 
	 * @return boolean value <br>
	 *         true: <b>{@value com.pom.crimson.util.Constants#EXPLORE_NOT_PART_OF_GROUPS}
	 *         </b> is displayed <br>
	 *         false:
	 *         <b>{@value com.pom.crimson.util.Constants#EXPLORE_NOT_PART_OF_GROUPS} </b>
	 *         is not displayed
	 */
	public boolean verifyNotPartOfGroupsSection()
	{
		return GenericFunctions.isElementDisplayed(youArentPartOfGroupsText);
	}

	/**
	 * Opens Home Page by tapping <b>For you</b> link in bottom menu.
	 * 
	 * 
	 * @return Object of HomePage
	 */
	public HomePage goToHomePage()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Tapping on For you button in recycler tab");
		GenericFunctions.clickElement(driver, forYouBtn);
		HomePage homePage=new HomePage(driver,platformName);
		return homePage;
	}
}
