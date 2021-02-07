package com.pom.crimson.testcases.explore.high;

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
	
	@Test(enabled=true,priority=1,
			testName="DiscoverTopicsPage-Verify  Unable to find what you’re looking for? section",
			description="DiscoverTopicsPage-Verify  Unable to find what you’re looking for? section")
	public void verifyUnableToFindSectionOnDiscoverTopicsPage_DiscoverTopicsPageTest() throws Exception
	{
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Scroll to bottom of page
		discoverTopicsPage.scrollToBottomOfPage();
		LogAssert logAssert=new LogAssert();
		logAssert.assertTrue(discoverTopicsPage.verifyUnableToFindMessage(),"Check if "+Constants.CONTENT_UNABLE_TO_FIND+" section is displayed");
		logAssert.assertTrue(discoverTopicsPage.isElementDisplayed(Constants.CONTENT_DROP_MESSAGE)," Check if "+Constants.CONTENT_DROP_MESSAGE+" link is displayed");
		logAssert.assertAll();
	}
	
	@Test(enabled=true,priority=2,
			testName="DiscoverTopicsPage-Verify click on Drop us a message link",
			description="DiscoverTopicsPage-Verify that user can tap on Drop us a message and redirected to Contact us page")
	public void verifyNavigationFromUnableToFindSectionOnDiscoverTopics_DiscoverTopicsPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Scroll to bottom of page
		discoverTopicsPage.scrollToBottomOfPage();
		logAssert.assertTrue(discoverTopicsPage.isElementDisplayed(Constants.CONTENT_DROP_MESSAGE)," Check if "+Constants.CONTENT_DROP_MESSAGE+" link is displayed");
		// Tap "Drop us a message" link
		contactUsPage=discoverTopicsPage.tapDropUsMessage();
		logAssert.assertTrue(contactUsPage.isElementDisplayed(Constants.CONTENT_CONTACT_US),"Check if "+Constants.CONTENT_CONTACT_US+" title is displayed on contact us page");
		logAssert.assertTrue(contactUsPage.isElementDisplayed(Constants.CONTENT_CONTACT_US_EMAIL),"Check if "+Constants.CONTENT_CONTACT_US_EMAIL+"  link is displayed on contact us page");
		logAssert.assertAll();
	}

}
