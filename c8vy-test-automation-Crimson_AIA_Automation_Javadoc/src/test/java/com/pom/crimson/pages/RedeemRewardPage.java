package com.pom.crimson.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Rewards</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * Click on Rewards menu from the profile menus
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class RedeemRewardPage extends BasePage {
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rewards']")
	private MobileElement ClickRewards;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Redeem reward']")
	private MobileElement ClickRedeemReward;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement NationalId;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Next']")
	private MobileElement ClickNext;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Submit']")
	private MobileElement ClickSubmit;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox")
	private MobileElement ClickCheckBox;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public RedeemRewardPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Taps on <b>Rewards</b> menu from the profiles menu list.
	 *  
	 */
	public void RewardsMenu()
	{
		GenericFunctions.scroll(driver, "Rewards", platformName);
		GenericFunctions.WaitForElement(driver, ClickRewards);
		GenericFunctions.clickElement(driver, ClickRewards);
    }
	
	/**
	 * Clicks on <b>Redeem reward</b> button in the Rewards screen
	 */
	public void Scroll_ClickRedeemRewardmenu()
	{
		GenericFunctions.scroll(driver, "Redeem reward", platformName);
		GenericFunctions.WaitForElement(driver, ClickRedeemReward);
		GenericFunctions.clickElement(driver, ClickRedeemReward);
	}
	
	/**
	 * Enter <b>Date of birth</b> in Rewards page
	 */
	public void Click_date_of_birth()
	{

		driver.findElement(By.xpath("//android.widget.TextView[@text='Invalid date']")).click();
		driver.findElement(By.xpath("//android.view.View[@text='1']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
}
	/**
	 * Enter <b>Nationality ID number</b> in Rewards page
	 */
	public void EnterNationalId()
	{
		
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(1).sendKeys("1234567890123");
	}
	
	/**
	 * Scrolls to the <b>Next</b> button
	 */
	public void Scroll_Next()
	{
		GenericFunctions.scroll(driver, "Next", platformName);
	}
	
	/**
	 * Clicks on the <b>Next</b> button
	 */
	public void Click_Next()
	{
		GenericFunctions.WaitForElement(driver, ClickNext);
		GenericFunctions.clickElement(driver, ClickNext);
	}
	
	/**
	 * Clicks on the Check box in the Rewards screen
	 */
	public void ClickCheckBox()
	{
		GenericFunctions.WaitForElement(driver, ClickCheckBox);
		GenericFunctions.clickElement(driver, ClickCheckBox);
	}
	
	/**
	 * Clicks on the <b>Submit</b> button in the rewards screen
	 */
	public void Click_Submit()
	{
		GenericFunctions.WaitForElement(driver, ClickSubmit);
		GenericFunctions.clickElement(driver, ClickSubmit);
	}
	}
	