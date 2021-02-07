package com.pom.crimson.base;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.GroupsPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

	/**
	 * Base page class contains common verification and navigation functions. These
	 * can be used on all pages where applicable.
	 * 
	 * @author Jaspreet Kaur Chagger
	 */
	public class BasePage extends BaseFixture {

	protected AppiumDriver<MobileElement> driver;
	protected String platformName = getPlatformName();

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Content']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Content, tab, 3 of 5']")
	protected MobileElement exploreLink;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='For you, tab, 1 of 5']")
	protected MobileElement homeLink;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Chats']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Chats, tab, 2 of 5']")
	protected MobileElement groupsLink;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Profile']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Profile, tab, 4 of 5']")
	protected MobileElement profileLink;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='tab_showcase']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='tab_showcase, tab, 5 of 5']")
	protected MobileElement tabShowcaseLink;

	public BasePage(AppiumDriver<MobileElement> driver, String platformName) {
		this.platformName = platformName;
		this.driver = driver;
		// this.test=test;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	// common verification functions

	/**
	 * Checks if element is displayed on screen. First scrolls and than finds element
	 * by finding its visible text.
	 * 
	 * @param text Visible text of element
	 * @return boolean value
	 * true: Element is displayed
	 * false: Element is not displayed
	 */
	public boolean isElementDisplayed(String text) {
		ExtentReportManager.getTest().log(Status.INFO, "Checking if " + text + " is displayed");
		try {
			GenericFunctions.scroll(driver, text, platformName);
			MobileElement ele = GenericFunctions.findElementByText(driver, text, platformName);
			return GenericFunctions.isElementDisplayed(ele);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Taps on element. First scrolls and than finds element by finding its visible
	 * text and than clicks same.
	 * 
	 * @param text Visible text of element
	 */
	public void tapOnElementByText(String text) {
		try {
			GenericFunctions.scroll(driver, text, platformName);
			MobileElement ele = GenericFunctions.findElementByText(driver, text, platformName);
			ExtentReportManager.getTest().log(Status.INFO, "Clicking on " + text);
			GenericFunctions.clickElement(driver, ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Taps on button type element. First scrolls and than finds element by finding
	 * its visible text and than clicks same.
	 * 
	 * @param text Visible text of element
	 */
	public void tapOnButtonByText(String text) {
		try {
			GenericFunctions.scroll(driver, text, platformName);
			MobileElement ele = GenericFunctions.findButtonElementByText(driver, text, platformName);
			ExtentReportManager.getTest().log(Status.INFO, "Clicking on " + text);
			GenericFunctions.clickElement(driver, ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scrolls to bottom of page.
	 */
	public void scrollToBottomOfPage() {
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling to bottom of page ");
		GenericFunctions.scrollByCordinates(driver);
	}

	/**
	 * Scrolls to section of page.
	 * 
	 * @param sectionName Visible text of section
	 */
	public void scrollToSection(String sectionName) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling to " + sectionName);
		GenericFunctions.scroll(driver, sectionName, platformName);
	}

	/**
	 * Navigates to Profile Page by clicking on Profile icon in bottom menu.
	 * 
	 * @return Object of ProfilePage
	 */
	public ProfilePage goToProfilePage() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Profile Option in recycler tab");
		GenericFunctions.clickElement(driver, profileLink);
		ProfilePage profilePage = new ProfilePage(driver, platformName);
		return profilePage;
	}

	/**
	 * Navigates to Content Page by clicking on Content icon in bottom menu.
	 * 
	 * Note : Content was previously named as Explore page
	 * @return Object of ExplorePage
	 */
	public ExplorePage goToExplorePage() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Content Option in recycler tab");
		GenericFunctions.clickElement(driver, exploreLink);
		ExplorePage explorePage = new ExplorePage(driver, platformName);
		return explorePage;
	}

	/**
	 * Navigates to Groups Page by clicking on Chats icon in bottom menu.
	 * @return Object of GroupsPage
	 */
	public GroupsPage goToGroupsPage() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Chats Option in recycler tab");
		GenericFunctions.clickElement(driver, groupsLink);
		GroupsPage groupsPage = new GroupsPage(driver, platformName);
		return groupsPage;
	}

	/**
	 * Navigates to Home Page by clicking on For you icon in bottom menu.
	* @return Object of HomePage
	 */
	public HomePage goToHomePage() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on For You Option in recycler tab");
		GenericFunctions.clickElement(driver, homeLink);
		HomePage homePage = new HomePage(driver, platformName);
		return homePage;
	}

	/**
	 * Gets text which is copied in clipboard.
	 * @return Text from Clipboard. Value will be String.
	 */
	public String getTextfromClipboard() {
		ExtentReportManager.getTest().log(Status.INFO,
				"Text copied from Clipboard: " + GenericFunctions.getTextfromClipboard(driver));
		return GenericFunctions.getTextfromClipboard(driver);
	}

	/**
	 * Checks if Group name is displayed on screen.
	 * 
	 * @param text Group name
	 * @return boolean value
	 * true: Group name is displayed
	 * false: Group name is not displayed
	 */
	public boolean isGroupDisplayed(String text) {
		ExtentReportManager.getTest().log(Status.INFO, "Checking if " + text + " is displayed");
		try {
			MobileElement ele = GenericFunctions.findElementByText(driver, text, platformName);
			return GenericFunctions.isElementDisplayed(ele);
		} catch (Exception e) {
			return false;
		}
	}

}
