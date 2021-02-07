package com.pom.crimson.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>NPS</b> page in the mobile application.
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class NPSPage extends BasePage {
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='NPS' and @index='0']")
	private MobileElement ClickNPS;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='1' and @index='0']")
	private MobileElement Click1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='2' and @index='0']")
	private MobileElement Click2;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='3' and @index='0']")
	private MobileElement Click3;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='4' and @index='0']")
	private MobileElement Click4;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='5' and @index='0']")
	private MobileElement Click5;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='6' and @index='0']")
	private MobileElement Click6;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='7' and @index='0']")
	private MobileElement Click7;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='8' and @index='0']")
	private MobileElement Click8;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='9' and @index='0']")
	private MobileElement Click9;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='10' and @index='0']")
	private MobileElement Click10;
		
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Next']")
	private MobileElement ClickNext;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Submit']")
	private MobileElement ClickSubmit;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Type your feedback here (Optional)']")
	private MobileElement EnterFeedback_1;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement EnterFeedback;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[normalize-space(@text)='Your feedback will help us improve {appName} experience.']")
	private MobileElement FeedbackDescription;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 	
	public NPSPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Scrolls to NPS menu in the page
	 */
	public void scrollNPS()
	{
		GenericFunctions.scroll(driver, "NPS", platformName);
	}

	/**
	 * Clicks on NPS menu in the page
	 */	
	public void ClickNPS()
	{
		GenericFunctions.WaitForElement(driver, ClickNPS);
		GenericFunctions.clickElement(driver, ClickNPS);
    }
	
	/**
	 * Clicks on Rating no. 1 in the NPS Screen
	 */		
	public void Click1()
	{
		GenericFunctions.WaitForElement(driver, Click1);
		GenericFunctions.clickElement(driver, Click1);
    }
	
	/**
	 * Clicks on Rating no. 2 in the NPS Screen
	 */			
	public void Click2()
	{
		GenericFunctions.WaitForElement(driver, Click2);
		GenericFunctions.clickElement(driver, Click2);
    }
	
	/**
	 * Clicks on Rating no. 3 in the NPS Screen
	 */	
	public void Click3()
	{
		GenericFunctions.WaitForElement(driver, Click3);
		GenericFunctions.clickElement(driver, Click3);
    }
	
	/**
	 * Clicks on Rating no. 4 in the NPS Screen
	 */	
	public void Click4()
	{
		GenericFunctions.WaitForElement(driver, Click4);
		GenericFunctions.clickElement(driver, Click4);
    }
	
	/**
	 * Clicks on Rating no. 5 in the NPS Screen
	 */		
	public void Click5()
	{
		GenericFunctions.WaitForElement(driver, Click5);
		GenericFunctions.clickElement(driver, Click5);
    }
	
	/**
	 * Clicks on Rating no. 6 in the NPS Screen
	 */	
	public void Click6()
	{
		GenericFunctions.WaitForElement(driver, Click6);
		GenericFunctions.clickElement(driver, Click6);
    }	
	
	/**
	 * Clicks on Rating no. 7 in the NPS Screen
	 */	
	public void Click7()
	{
		GenericFunctions.WaitForElement(driver, Click7);
		GenericFunctions.clickElement(driver, Click7);
    }	
	
	/**
	 * Clicks on Rating no. 8 in the NPS Screen
	 */	
	public void Click8()
	{
		GenericFunctions.WaitForElement(driver, Click8);
		GenericFunctions.clickElement(driver, Click8);
    }	
	
	/**
	 * Clicks on Rating no. 9 in the NPS Screen
	 */	
	public void Click9()
	{
		GenericFunctions.WaitForElement(driver, Click9);
		GenericFunctions.clickElement(driver, Click9);
    }	
	
	/**
	 * Clicks on Rating no. 10 in the NPS Screen
	 */	
	public void Click10()
	{
		GenericFunctions.WaitForElement(driver, Click10);
		GenericFunctions.clickElement(driver, Click10);
    }	
	
	/**
	 * Clicks on Next button in the NPS Screen
	 */
	public void ClickNext()
	{
		GenericFunctions.WaitForElement(driver, ClickNext);
		GenericFunctions.clickElement(driver, ClickNext);
    }
	
	/**
	 * Enters the feedback text in NPS screen for the rating 1 to 6
	 */
	public void EnterFeedback_Scale1to6()
	{
		GenericFunctions.sendKeys(driver, EnterFeedback, "Seeing loader issues which needs to be improved");
	}
	
	/**
	 * Enters the feedback text in NPS screen for the rating 7 to 8
	 */	
	public void EnterFeedback_Scale7to8()
	{
		GenericFunctions.sendKeys(driver, EnterFeedback, "Few trivial issues in screen navigations");
	}
	
	/**
	 * Enters the feedback text in NPS screen for the rating 9 to 10
	 */		
	public void EnterFeedback_Scale9to10()
	{
		GenericFunctions.sendKeys(driver, EnterFeedback, "Nice, Like the Features in the App");
	}
	
	/**
	 * Clicks on <b>submit</b> button to submit the feedback 
	 */
	public void clickSubmit()
	{
		GenericFunctions.scroll(driver, "Submit", platformName);
		GenericFunctions.WaitForElement(driver, ClickSubmit);
		GenericFunctions.clickElement(driver, ClickSubmit);
}
	
}