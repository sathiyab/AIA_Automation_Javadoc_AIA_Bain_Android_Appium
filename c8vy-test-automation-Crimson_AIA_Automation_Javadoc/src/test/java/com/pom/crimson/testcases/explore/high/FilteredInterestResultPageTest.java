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
 * This test class contains test cases for Filtered Interest Result page.<br>
 * Filtered Interest Result Page articles and videos as per specific interest. <br>
 * User can navigate to this page by various ways :<br><br>
 * 1. Go to Home Page <br> Click <b>Content</b> in bottom menu. <br>Click on
 * <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b>
 * link. <br>Click Interest<br><br>
 * 2. Go to Home Page <br> Click <b>Content</b> in bottom menu.<br>Scroll down page and go to <b>{@value com.pom.crimson.util.Constants#CONTENT_DISCOVER_MORE}</b> section<br>
 * Tap on an interest.
 * <br><br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class FilteredInterestResultPageTest extends BaseFixture {

	
	HomePage homePage;
	ExplorePage explorePage;
	DiscoverTopicsPage discoverTopicsPage;
	FilteredInterestResultPage filteredInterestResultPage;
	ContactUsPage contactUsPage;
	ContentPage contentPage;
	
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
	
	
	@Test(enabled=true,priority=1,testName="FilteredInterestResultPage-Verify Unable To FindSection On Filtered Interest Result Page",
			description="FilteredInterestResultPage-Verify Unable To Find Section On Filtered Interest Result Page")
	public void verifyUnableToFindSectionOnFilteredInterestResultPage_FilteredInterestResultPageTest() throws Exception
	{
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage=discoverTopicsPage.goToInterestPage(Constants.INTERESTS_BABYCARE);
		Thread.sleep(2000);
		// Scroll to bottom of page
		filteredInterestResultPage.scrollToBottomOfPage();
		Thread.sleep(2000);
		// Scroll to section
		filteredInterestResultPage.scrollToSection(Constants.CONTENT_UNABLE_TO_FIND_WHAT);
		LogAssert logAssert=new LogAssert();
		logAssert.assertTrue(filteredInterestResultPage.verifyUnableToFindMessage(),"Check if "+Constants.CONTENT_UNABLE_TO_FIND+" section is displayed");
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.CONTENT_DROP_MESSAGE),"Check if "+Constants.CONTENT_DROP_MESSAGE+" link is displayed");
		logAssert.assertAll();
	}
	
	@Test(enabled=true,priority=2,testName="FilteredInterestResultPage-Verify click on Drop us a message link",
			description="FilteredInterestResultPage-Verify that user can tap on Drop us a message and redirected to Contact us page")
	public void verifyNavigationFromUnableToFindSectionOnFilteredInterestResultPage_FilteredInterestResultPageTest() throws Exception
	{
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage=discoverTopicsPage.goToInterestPage(Constants.INTERESTS_BABYCARE);
		Thread.sleep(2000);
		// Scroll to bottom of page
		filteredInterestResultPage.scrollToBottomOfPage();
		Thread.sleep(2000);
		// Scroll to section
		filteredInterestResultPage.scrollToSection(Constants.CONTENT_UNABLE_TO_FIND_WHAT);
		LogAssert logAssert=new LogAssert();
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.CONTENT_DROP_MESSAGE),"Check if "+Constants.CONTENT_DROP_MESSAGE+" link is displayed");
		contactUsPage=filteredInterestResultPage.tapDropUsMessage();
		logAssert.assertTrue(contactUsPage.isElementDisplayed(Constants.CONTENT_CONTACT_US),"Check if "+Constants.CONTENT_CONTACT_US+" title is displayed on contact us page");
		logAssert.assertTrue(contactUsPage.isElementDisplayed(Constants.CONTENT_CONTACT_US_EMAIL),"Check if "+Constants.CONTENT_CONTACT_US_EMAIL+"  link is displayed on contact us page");
		logAssert.assertAll();
	}

	@Test(enabled=true,priority=3,testName="FilteredInterestResultPage-Verify navigation to Discover more section on FilteredInterestResultPage",
			description="FilteredInterestResultPage-Verify navigation to Discover more section on FilteredInterestResultPage")
	public void verifyNavigationDiscoverMore_FilteredInterestResultPageTest() throws Exception
	{
		// Go to Explore/Content Page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage=discoverTopicsPage.goToInterestPage(Constants.INTERESTS_BABYCARE);
		Thread.sleep(2000);
		// Scroll to bottom of page
		filteredInterestResultPage.scrollToBottomOfPage();
		Thread.sleep(2000);
		// Scroll to section
		filteredInterestResultPage.scrollToSection(Constants.CONTENT_DISCOVER_MORE);
		LogAssert logAssert=new LogAssert();
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.CONTENT_DISCOVER_MORE),"Check if "+Constants.CONTENT_DISCOVER_MORE+" section is displayed");
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.CONTENT_DISCOVER_MORE_DESCRIPTION),"Check if "+Constants.CONTENT_DISCOVER_MORE_DESCRIPTION+" section description is displayed");
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.INTERESTS_FERTILITY),"Check if "+Constants.INTERESTS_FERTILITY+" interest in Discover more section is displayed");
		logAssert.assertAll();
	}
	
	


}
