package com.pom.crimson.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Change password</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * Clicking on <b>Change password</b> menu from profile menu
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class ChangePasswordPage extends BasePage {
	

	@AndroidFindBy(xpath="//android.widget.EditText")
	private MobileElement CurrentPassword;
	
	
	@AndroidFindBy(xpath="//android.widget.EditText")
	private MobileElement NewPassword;
	
	@AndroidFindBy(xpath="//android.widget.EditText")
	private MobileElement RetypeNewPassword;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Change password']")
	private MobileElement ClickChangePassword;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Save']")
	private MobileElement ClickSave;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	private MobileElement buttonOK;
	
	
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public ChangePasswordPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Gets the existing password and enters it in old password field
	 * 
	 */
	public void EnterValidOldPassword()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(0).sendKeys(Constants.EmailTestPassword);
	 }
		
	/**
	 * Enters the Invalid password in the New Password field
	 * 
	 */	
	public void EnterInvalidNewPassword()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(1).sendKeys("Win");
		alltext.get(2).sendKeys("Win");
	 }
	
	/**
	 * Enters password in the New Password field with alphanumeric characters but in lower case letters.
	 * 
	 */	
	public void EnterLowercasedNewPassword()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(1).sendKeys("welcome91");
		alltext.get(2).sendKeys("welcome91");
	 }
	
	/**
	 * Enters password in the New Password field without numeric characters.
	 * 
	 */	
	public void EnterNewPasswordWithoutNumeric()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(1).sendKeys("Welcomes");
		alltext.get(2).sendKeys("Welcomes");
	 }
	
	/**
	 * Taps on the Save button in Password change screen.
	 * 
	 */	
	public void ClickSave()
	{
		GenericFunctions.WaitForElement(driver, ClickSave);
		GenericFunctions.clickElement(driver, ClickSave);
	}
	
	/**
	 * Taps on Change Password menu from the Profile menu screen.
	 * 
	 */		
	public void ClickChangePassword()
	{
		GenericFunctions.WaitForElement(driver, ClickChangePassword);
		GenericFunctions.clickElement(driver, ClickChangePassword);
    }
	
	/**
	 * Enters different passwords in <b>New password</b> and <b>Re-type New password</b> fields
	 * 
	 */	
	public void EnterMismatchNewPassword()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(1).sendKeys("Welcome45");
		alltext.get(2).sendKeys("Welcome55");
	 }
	
	/**
	 * Enters the same old password in <b>New password</b> and <b>Re-type New password</b> fields
	 * 
	 */	
	public void EnterOldPasswordInTheNewPasswordField()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
		alltext.get(1).sendKeys(Constants.EmailTestPassword);
		alltext.get(2).sendKeys(Constants.EmailTestPassword);
	 }
	
    /**
     * Taps on button OK.
     */
	public void clickOk()
	    {
	    	GenericFunctions.WaitForElement(driver, buttonOK);
	    	GenericFunctions.clickElement(driver, buttonOK);
	     }
	       
		/**
		 * Enters incorrect old password.
		 * 
		 */	   
		public void EnterIncorrectOldPassword()
		{
			List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
			alltext.get(0).sendKeys("Winter01");
		}
		
		/**
		 * Enters valid password in the New Password field with alphanumeric characters.
		 * 
		 */		
		public void EnterValidNewPassword()
		{
			List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
			alltext.get(1).sendKeys("Welcome75");
			alltext.get(2).sendKeys("Welcome75");
		 }
		
		
		
		
		
}