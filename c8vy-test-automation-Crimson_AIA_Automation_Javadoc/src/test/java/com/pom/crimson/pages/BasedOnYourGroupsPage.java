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
 * Based On Your Groups Page displays recommended content as per user's joined
 * groups. In order to view this page, user should be part of Groups. <br>
 * User can navigate to this page by going to: <br><br>
 * Go to Home Page<br>
 * Click <b>Content</b> in bottom menu<br>
 * Go to
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS}</b>
 * section on Content Page<br>Click <b>See all</b> link <br><br>
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class BasedOnYourGroupsPage extends BasePage {

	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
	private MobileElement backBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='" + Constants.BASED_ON_GROUPS_JOIN_MORE + "']")
	private MobileElement joinMoreGroups;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'read')][last()]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@label,'read')][last()]")
	private MobileElement readText;

	public BasedOnYourGroupsPage(AppiumDriver<MobileElement> driver, String platformName) {
		super(driver, platformName);
	}

	/**
	 * Verifies if <b>
	 * {@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS} </b>
	 * page is displayed.
	 *
	 * It verifies by checking if
	 * <b>{@value com.pom.crimson.util.Constants#BASED_ON_GROUPS_JOIN_MORE} </b>
	 * MobileElement is displayed
	 * 
	 * @return boolean value <br>
	 *         true:
	 *         <b>{@value com.pom.crimson.util.Constants#BASED_ON_GROUPS_JOIN_MORE}
	 *         </b> is displayed <br>
	 *         false:
	 *         <b>{@value com.pom.crimson.util.Constants#BASED_ON_GROUPS_JOIN_MORE}
	 *         </b> is not displayed
	 */
	public boolean validateIfBasedOnYourGroupsPageIsDisplayed() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GenericFunctions.scroll(driver, Constants.BASED_ON_GROUPS_WANT_MORE, platformName);

		return GenericFunctions.isElementDisplayed(joinMoreGroups);
	}

	/**
	 * Taps on First article widget.
	 * 
	 * It taps by searching read text.
	 * 
	 * @return Object of ContentPage
	 */
	public ContentPage tapArticle() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Article");
		GenericFunctions.clickElement(driver, readText);
		ContentPage contentPage = new ContentPage(driver, platformName);
		return contentPage;
	}

	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#BASED_ON_GROUPS_JOIN_MORE}
	 * </b> link
	 * 
	 * @return Object of GroupsPage
	 */
	public GroupsPage tapJoinMoreGroups() {

		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Join more groups link");
		GenericFunctions.clickElement(driver, joinMoreGroups);
		GroupsPage groupsPage = new GroupsPage(driver, platformName);
		return groupsPage;

	}

	/**
	 * Taps on back button
	 * 
	 * @return Object of ExplorePage
	 */
	public ExplorePage tapOnBackBtn() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back button On Based on Groups Page");
		GenericFunctions.clickElement(driver, backBtn);
		ExplorePage explorePage = new ExplorePage(driver, platformName);
		return explorePage;
	}
}
