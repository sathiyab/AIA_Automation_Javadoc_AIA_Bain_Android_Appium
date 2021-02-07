package com.pom.crimson.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Talk to AIA agent</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * Clicking on <b>Talk to AIA agent</b> menu from the profiles menu list 
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class TalktoAIAAgentPage extends BasePage {
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Talk to AIA agent']")
	private MobileElement ClickTalktoAIAagent;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Request call back']")
	private MobileElement ClickRequestCallBack;
	
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Select province']")
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Select province']")
	private MobileElement ClickProvince;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Bangkok']")
	private MobileElement SelectBankok;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Morning']")
	private MobileElement PreferredcallTime;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Yes']")
	private MobileElement ClickYes;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No']")
	private MobileElement ClickNo;
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	private MobileElement buttonOK;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Phone') and @index='1']")
	private MobileElement ClickPhone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Email') and @index='1']")
	private MobileElement ClickEmail;
	
	//@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Select') and @index='0']")
	@AndroidFindBy(xpath="//android.widget.EditText[contains(@text,'Select') and @index='0']")
	private MobileElement ClickSelect;
	

	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public TalktoAIAAgentPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Clicks on <b>Talk to AIA agent</b> menu from profiles menu list 
	 */
	public void ClickTalktoAIAagent()
	{
		GenericFunctions.scroll(driver, "Talk to AIA agent", platformName);
		GenericFunctions.WaitForElement(driver, ClickTalktoAIAagent);
		GenericFunctions.clickElement(driver, ClickTalktoAIAagent);
    }
	
	/**
	 * Scrolls to <b>Request call back</b> button in <b>Talk to AIA agent</b> screen 
	 */
	public void scroll_RequestCallBack()
	{
		GenericFunctions.scroll(driver, "Request call back", platformName);
	}
	
	/**
	 * Selects <b>Province</b> in <b>Talk to AIA agent</b> screen
	 */
	public void SelectProvince()
	{
	
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Select province']")).click();
		//driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Bangkok']")).click();
		GenericFunctions.clickElement(driver, ClickProvince);
		GenericFunctions.clickElement(driver, SelectBankok);
	
	}
	
	/**
	 * Taps on <b>Yes</b> option in <b>Talk to AIA agent</b> screen
	 */
	public void ClickYes()
	{
		GenericFunctions.clickElement(driver, ClickYes);
		
	}
	
	/**
	 * Taps on <b>Ok</b> option in <b>Talk to AIA agent</b> screen 
	 */
	  public void clickOk()
	    {
	    	GenericFunctions.WaitForElement(driver, buttonOK);
	    	GenericFunctions.clickElement(driver, buttonOK);
	     }
	
	  /**
	   * Taps on <b>No</b> option in <b>Talk to AIA agent</b> screen
	   */
	public void ClickNo()
	{
		GenericFunctions.clickElement(driver, ClickNo);
		
	}
	
	/**
	 * Taps on <b>Email</b> option in <b>Talk to AIA agent</b> screen
	 */
	public void ClickEmail()
	{
		GenericFunctions.clickElement(driver, ClickEmail);
		
	}
	
	/**
	 * Taps on <b>Phone</b> option in <b>Talk to AIA agent</b> screen
	 */
	public void ClickPhone()
	{
		GenericFunctions.clickElement(driver, ClickPhone);
		
	}
	
	/**
	 * Taps on <b>Request call back</b> button in <b>Talk to AIA agent</b> screen
	 */
	public void ClickRequestCallBack()
	{
		GenericFunctions.clickElement(driver, ClickRequestCallBack);
	}

	/**
	 * Selects <b>Preferred called time</b> for contacting via phone in <b>Talk to AIA agent</b> screen
	 */
	public void clickPreferredCallTime()
	{
		GenericFunctions.clickElement(driver, ClickSelect);
		GenericFunctions.clickElement(driver, PreferredcallTime);
	}
	
	/**
	 * Fills in details in <b>Talk to AIA agent</b> screen
	 */
	public void EnterPendingData()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(1).sendKeys("Balaji");
		alltext.get(2).sendKeys("S");
		alltext.get(3).sendKeys("9790040572");
		
	}
}