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
 * This is the Page Class for <b>NPS</b> page in the mobile application.
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class FeatureRatingDemoPage extends BasePage {
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Terms of Use']")
	private MobileElement ClickTermsofUse;
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public FeatureRatingDemoPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='FeatureRatingDemo']")
	private MobileElement FeatureRatingDemo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Rate feature']")
	private MobileElement buttonRatefeature;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
    private MobileElement selectStar;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not now']")
	private MobileElement ClickNotNow;
	
	

/**
 * Clicks on <b>FeatureRatingDemo</b> link in the screen
 * 
 * @throws InterruptedException thrown when a thread is interrupted
 */
public void FeatureRatingDemo() throws InterruptedException{
	GenericFunctions.scroll(driver, "FeatureRatingDemo", platformName);
	GenericFunctions.WaitForElement(driver, FeatureRatingDemo);
	GenericFunctions.clickElement(driver, FeatureRatingDemo);

}

/**
 * Taps on <b>Rate feature</b> text in the screen 
 * 
 * @throws InterruptedException thrown when a thread is interrupted
 */
public void buttonRatefeature() throws InterruptedException{
	
	GenericFunctions.WaitForElement(driver, buttonRatefeature);
	GenericFunctions.clickElement(driver, buttonRatefeature);

}
 
/**
 * Taps on the first Star in the rating screen
 */
public void selectStar(){  
	 
	GenericFunctions.WaitForElement(driver, selectStar);
	GenericFunctions.clickElement(driver, selectStar);
	
}

/**
 * Taps on the fifth Star in the rating screen
 * 
 * @throws InterruptedException thrown when a thread is interrupted
 */
public void clickStar() throws InterruptedException 
{
	Thread.sleep(3000);
	List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.ImageView"));
	alltext.get(5).click();
}

/**
 * Taps on the <b>Not now</b> in the rating screen
 */
public void ClickNotNow(){  
	 
	GenericFunctions.WaitForElement(driver, ClickNotNow);
	GenericFunctions.clickElement(driver, ClickNotNow);
	
}
}	
	
	
	
