package com.pom.crimson.testcases.explore.smoke;

import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddMoreInterestPage;
import com.pom.crimson.pages.BasedOnYourGroupsPage;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.ContentPage;
import org.openqa.selenium.TakesScreenshot;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.FilteredInterestResultPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.ManageProfilePage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;


/**
 *This test class contains test cases for Discover by Interests page.<br>
 * Discover by Interests page helps is searching content by user's interest. <br>
 * User can navigate to this page by: <br><br>
 * Go to Home Page <br> Click <b>Content</b> in bottom menu. Click on
 * <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b>
 * link.<br>
 * <br>
 * 
 * @author Jaspreet Kaur Chagger
 */

public class DiscoverTopicsPageTest extends BaseFixture {

	
	HomePage homePage;
	ExplorePage explorePage;
	DiscoverTopicsPage discoverTopicsPage;
	FilteredInterestResultPage filteredInterestResultPage;
	ContactUsPage contactUsPage;
	
	@BeforeMethod()
	public void beforeLocalMethod()
	{
		LoginPage loginPage=new LoginPage(getDriver(),getPlatformName());
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		homePage=new HomePage(getDriver(),getPlatformName());
	}
	
	//Navigation Test Cases
	
	@Test(enabled=true,priority=1,
			testName="DiscoverTopicsPage-Verify navigation to Discover Topics by Interest Page",
			description="DiscoverTopicsPage-Verify navigation to Discover Topics by Interest Page by Clicking on Discover Topics on Explore Page")
	public void verifyNavigationToDiscoverTopicsPage_DiscoverTopicsPageTest() throws Exception
	{
		LogAssert logAssert = new LogAssert();
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		logAssert.assertTrue(discoverTopicsPage.isElementDisplayed(Constants.INTERESTS_BABYCARE)," Check if "+Constants.INTERESTS_BABYCARE+" care is displayed");
		logAssert.assertAll();
	}
	
	@Test(enabled=true,priority=2,
			testName="DiscoverTopicsPage-Verify cross button on Discover topics by Interest page",
			description="DiscoverTopicsPage-Verify when user taps  on cross button on Discover topics by Interest page")
	public void verifyCrossBtn_DiscoverTopicsPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Tap Cross button
		explorePage=discoverTopicsPage.tapOnCrossBtn();
		logAssert.assertTrue(explorePage.isElementDisplayed("Specially curated for you and your family"),"Explore Page is displayed");
		logAssert.assertAll();
	}
	
	
}
