package com.pom.crimson.pages;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * ContentPage or Full Article page displays article content. <br>
 * User can navigate to article page by various ways: <br> <br>
 * 1. Go to Home Page <br>Click on any article in <b>Recommended content</b>
 * section.<br><br>
 * 2. Go to Home Page<br> Click <b>Content</b> in bottom menu. Click on any
 * article in
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS}</b>
 * or
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_INTEREST}</b>
 * section. <br><br>
 * 3. Go to Home Page <br> Click <b>Content</b> in bottom menu. Click on
 * <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b>
 * link. Select interest.<br>
 * <br>
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class ContentPage extends BasePage {

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@label='" + Constants.CONTENT_DISCOVER_MORE + "']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_DISCOVER_MORE + "']")
	private MobileElement discoverMore;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@label='" + Constants.CONTENT_DROP_MESSAGE + "']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_DROP_MESSAGE + "']")
	private MobileElement dropUsAMessage;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@label='" + Constants.CONTENT_UNLIKE + "']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_UNLIKE + "']")
	private MobileElement Unlike;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@label='" + Constants.CONTENT_LIKE + "']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_LIKE + "']")
	private MobileElement like;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@label='" + Constants.CONTENT_SHARE + "']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_SHARE + "']")
	private MobileElement share;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@label='" + Constants.CONTENT_SAVE + "']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_SAVE + "']")
	private MobileElement save;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_SIMILAR_CONTENT + "']")
	private MobileElement similarContent;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")
	private MobileElement firstElementSimilarContent;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView")
	private MobileElement topImageOnContentPage;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Unable to find what') and contains(@text, 'looking for?')]")
	private MobileElement unableToFindDescription;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@label='" + Constants.CONTENT_UNSAVE + "']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.CONTENT_UNSAVE + "']")
	private MobileElement unSave;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'read')][last()]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@label,'read')][last()]")
	private MobileElement readText;

	public ContentPage(AppiumDriver<MobileElement> driver, String platformName) {
		super(driver, platformName);
	}

	/**
	 * Verifies if article page is displayed.
	 * 
	 *
	 * It verifies by checking if
	 * <b>{@value com.pom.crimson.util.Constants#CONTENT_DROP_MESSAGE} </b>
	 * MobileElement is displayed
	 * 
	 * @return boolean value <br>
	 *         true: <b>{@value com.pom.crimson.util.Constants#CONTENT_DROP_MESSAGE}
	 *         </b> is displayed <br>
	 *         false:
	 *         <b>{@value com.pom.crimson.util.Constants#CONTENT_DROP_MESSAGE} </b>
	 *         is not displayed
	 *         
	 */
	public boolean validateIfContentPageIsDisplayed() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GenericFunctions.scroll(driver, "Got any feedback or suggestions?", platformName);
		return GenericFunctions.isElementDisplayed(dropUsAMessage);
	}

	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#CONTENT_DROP_MESSAGE} </b>
	 * link.
	 * 
	 * @return Object of ContactUsPage
	 */
	public ContactUsPage tapDropUsMessage() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Drop us a message link on Content Page");
		GenericFunctions.clickElement(driver, dropUsAMessage);
		ContactUsPage contactUsPage = new ContactUsPage(driver, platformName);
		return contactUsPage;
	}

	/**
	 * Taps on first article in
	 * <b>{@value com.pom.crimson.util.Constants#CONTENT_SIMILAR_CONTENT} </b>
	 * section.
	 */
	public void tapOnContentInSimilarContentSection() {
		scrollToSection("Similar content");
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on article in Similar Content");
		GenericFunctions.clickElement(driver, readText);

	}

	/**
	 * Taps on back button.
	 */
	public void tapOnBackBtn() {
		ExtentReportManager.getTest().log(Status.INFO, "Trying to click on Back button by Cordinates");

		try {
			GenericFunctions.tapByCordinates(driver, 100, 231);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Verifies if text <b>Unable to find what you're looking for?</b> is displayed
	 * 
	 * @return boolean value <br>
	 *         true:  <b>Unable to find what you're looking for?</b> text is displayed <br>
	 *         false: <b>Unable to find what you're looking for?</b> text is not displayed
	 * 
	 */
	public boolean verifyUnableToFindMessage() {
		return GenericFunctions.isElementDisplayed(unableToFindDescription);
	}

}
