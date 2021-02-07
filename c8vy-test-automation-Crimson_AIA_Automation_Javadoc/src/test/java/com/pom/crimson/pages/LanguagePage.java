package com.pom.crimson.pages;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Language</b> Menu in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br>
 * Clicking on <b>Language</b> menu from profile menu list
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */
public class LanguagePage extends BasePage {
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Add a language']")
	private MobileElement ClickAddLanguage;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/locale_search_menu']")
	private MobileElement ClickSearch;
	
	@AndroidFindBy(xpath="//android.widget.EditText']")
	private MobileElement EnterThai;
	
	@AndroidFindBy(xpath="//android.widget.EditText']")
	private MobileElement ไทย;;

	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */	
	public LanguagePage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	/**
	 * Taps on <b>Add a language</b> button in the screen
	 * 
	 */
	public void ClickAddLanguage()
	{
		GenericFunctions.WaitForElement(driver, ClickAddLanguage);
		GenericFunctions.clickElement(driver, ClickAddLanguage);
    }
	
	/**
	 * Taps on <b>Search</b> Menu
	 * 
	 */	
	public void ClickSearch()
	{
		driver.findElement(By.id("android:id/locale_search_menu")).click();
		/*GenericFunctions.WaitForElement(driver, ClickSearch);
		GenericFunctions.clickElement(driver, ClickSearch);*/
    }

	/**
	 * Enters <b>Thai</b> in the Search Text field
	 * 
	 */	
	public void EnterThai()
	{
		driver.findElement(By.id("android:id/search_src_text")).sendKeys("Thai");
		/*
		 * GenericFunctions.WaitForElement(driver, EnterThai);
		 * GenericFunctions.sendKeys(driver, EnterThai, "Thai");
		 */
		
}
	
	
}












