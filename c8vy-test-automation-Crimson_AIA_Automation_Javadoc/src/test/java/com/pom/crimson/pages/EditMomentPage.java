package com.pom.crimson.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * Edit moment page can be used to edit moments for primary profile or child profiles.
 * <br> 
 * User can navigate to this page by going to:
 * <br> 
 * <br>
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget <br>
 * Click <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b> link <br>
 * Create a moment<br>
 * Once moment is created,Tap on Moment and select <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_TITLE}</b> 
 * <br>
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class EditMomentPage extends AddNewMomentsPage {

	@AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private MobileElement crossBtn;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.EDITMOMENT_SAVEMOMENT+"']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.EDITMOMENT_SAVEMOMENT+"']")
	private MobileElement saveMoment;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.EDITMOMENT_DELETEMOMENT+"']")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='"+Constants.EDITMOMENT_DELETEMOMENT+"']")
	private MobileElement deleteMomentBtn;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_CONFIRM+"']")
	private MobileElement confirmDeleteBtn;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_CANCEL+"']")
	private MobileElement cancelDeleteBtn;

	public EditMomentPage(AppiumDriver<MobileElement> driver, String platformName) {
		super(driver, platformName);
	}
	
	/**
	 * Taps on  <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_SAVEMOMENT}</b> button
	 * 
	 */
	public void tapSaveMoment() {
		GenericFunctions.scroll(driver, "Save moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Save moment button");
		GenericFunctions.clickElement(driver, saveMoment);	
	}
	
	/**
	 * Taps on  <b>{@value com.pom.crimson.util.Constants#EDITMOMENT_DELETEMOMENT}</b> button
	 * 
	 */
	public void tapDeleteMomentBtn() {
		GenericFunctions.scroll(driver, "Delete moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Delete moment button on Edit Moment Page ");
		GenericFunctions.clickElement(driver, deleteMomentBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Confirm Delete moment button on Edit Moment Page ");
		GenericFunctions.clickElement(driver, confirmDeleteBtn);
	}
	
	/**
	 * Removes text from a Edit text field
	 * 
	 * @param text to be removed
	 */
	public void removeTextFromField(String text) {
		GenericFunctions.scroll(driver, text, platformName);
		MobileElement ele=GenericFunctions.findElementByEditText(driver, text, platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clearing element: "+text);
		GenericFunctions.clickElement(driver, ele);
		ExtentReportManager.getTest().log(Status.INFO, "Clearing Text: "+text);
		GenericFunctions.clear(driver, ele);
	
	}
	
	/**
	 * Taps on cross button
	 */
	public void tapCrossBtn()
	{
		GenericFunctions.scroll(driver, "Edit moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Clicking Cross Button on Edit Moment Page");
		GenericFunctions.clickElement(driver, crossBtn);
	}

	/**
	 * Appends Text to existing Text
	 * 
	 * @param existingNote  Existing Text
	 * @param appendedText Text to be appended
	 */
	public void appendNote (String existingNote, String appendedText)
	{
		GenericFunctions.scroll(driver, existingNote, platformName);
		MobileElement ele=GenericFunctions.findElementByEditText(driver, existingNote, platformName);
		GenericFunctions.clickElement(driver, ele);
		ExtentReportManager.getTest().log(Status.INFO, "Appending Text: "+appendedText);
		GenericFunctions.sendKeysWithoutClearingInitialText(driver, ele, appendedText);
	}

}
