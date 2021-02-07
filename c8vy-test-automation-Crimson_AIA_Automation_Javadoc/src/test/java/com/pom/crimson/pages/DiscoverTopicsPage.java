package com.pom.crimson.pages;

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
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Discover by Interests page helps is searching content by user's interest. <br>
 * User can navigate to this page by: <br><br>
 * Go to Home Page <br> Click <b>Content</b> in bottom menu. Click on
 * <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b>
 * link.<br>
 * <br>
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class DiscoverTopicsPage extends BasePage {

	
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private MobileElement crossBtn;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_FERTILITY+"']")
	private MobileElement fertilityMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_MEDICAL_CARE+"']")
	private MobileElement medicalCheckupsMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_NUTRITION_FITNESS+"']")
	private MobileElement nutritionFitnessMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_PREGNANCY_PREP+"']")
	private MobileElement pregnancyPreparationMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_PREPARING_FOR_BIRTH+"']")
	private MobileElement preparingForBirthMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_PRENATALCARE+"']")
	private MobileElement prenatalCareMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_BABYCARE+"']")
	private MobileElement babyCareMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_BABY_GROWTH_DEVELOPMENT+"']")
	private MobileElement babyGrowthDevelopmentMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_POSTPARTUM_WELLNESS+"']")
	private MobileElement postpartumWellnessMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_CHILD_HABITS+"']")
	private MobileElement childHabitsRoutineMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_HOLISTIC_CHILD+"']")
	private MobileElement holisticChildDevelopmentEducationMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.INTERESTS_FAMILY_PLANNING+"']")
	private MobileElement familyPlanningWellnessMilestone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.CONTENT_DROP_MESSAGE+"']")
	private MobileElement dropUsAMessage;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text, 'Unable to find what') and contains(@text, 'looking for?')]")
	private MobileElement unableToFindDescription;
	public DiscoverTopicsPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	//not in use
	private void closePage()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Cross button");
		GenericFunctions.clickElement(driver, crossBtn);
	}
	
	/**
	 * Opens Filtered Interest Result page by tapping on specific interest.
	 * 
	 * @param text Name of Interest
	 * @return Object of FilteredInterestResultPage
	 */
	public FilteredInterestResultPage goToInterestPage(String text)
	{
		ExtentReportManager.getTest().log(Status.INFO, "Going to FilteredInterestResult Page for "+text+" interest");
		MobileElement ele=GenericFunctions.findElementByText(driver, text, platformName);
		GenericFunctions.clickElement(driver, ele);
		FilteredInterestResultPage interestContenPage=new FilteredInterestResultPage(driver,platformName);
		return interestContenPage;
		
	}
	
	/**
	 * Taps on cross button.
	 * 
	 * @return Object of ExplorePage
	 */
	public ExplorePage tapOnCrossBtn()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Cross button");
		GenericFunctions.clickElement(driver, crossBtn);
		ExplorePage explorePage=new ExplorePage(driver,platformName);
		return explorePage;
		
		
	}

	/**
	 * Taps on  <b>{@value com.pom.crimson.util.Constants#CONTENT_DROP_MESSAGE}</b> link
	 * 
	 * @return Object of ContactUsPage
	 */
	public ContactUsPage tapDropUsMessage()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Drop us a message link");
		GenericFunctions.clickElement(driver, dropUsAMessage);
		ContactUsPage contactUsPage=new ContactUsPage(driver,platformName);
		return contactUsPage;
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
