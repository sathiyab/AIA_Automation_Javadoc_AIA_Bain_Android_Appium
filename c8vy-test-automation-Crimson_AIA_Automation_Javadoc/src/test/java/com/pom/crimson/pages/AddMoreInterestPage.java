package com.pom.crimson.pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * AddMoreInterestPage or Your preferred interests page can be used to add more interests to profile.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * Go to Home Page<br>Click <b>Content</b> in bottom menu<br>Go to <b>Update your interests</b> section on Content Page <br>Click <b>See all</b> link 
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class AddMoreInterestPage extends BasePage {
	
	//Locator not present on ios
	@AndroidFindBy(xpath="//android.view.ViewGroup[13]/android.view.ViewGroup/android.view.ViewGroup")
	private MobileElement saveMyInterests;
	
	//Locator not present on ios
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private MobileElement crossBtn;
	
	public AddMoreInterestPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}

	/**
	 * Taps on <b>Save my interests</b> button
	 * 
	 * @return Object of ExplorePage 
	 */
	public ExplorePage tapOnSaveMyInterest() {
		try {
			ExtentReportManager.getTest().log(Status.INFO, "Tapping on Save my interest button");
			GenericFunctions.tapByCordinates(driver, 555, 1917);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ExplorePage explorePage=new ExplorePage(driver,platformName);
		return explorePage;
	}
	
	/**
	 * Taps on cross button
	 * 
	 * @return Object of ExplorePage 
	 */
	public ExplorePage tapOnCrossBtn() {
		
			ExtentReportManager.getTest().log(Status.INFO, "Tapping on Cross button");
			GenericFunctions.clickElement(driver, crossBtn);
			ExplorePage explorePage=new ExplorePage(driver,platformName);
			return explorePage;
	}

}
