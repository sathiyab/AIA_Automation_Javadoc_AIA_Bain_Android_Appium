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
 * This test class contains test cases for Filtered Interest Result Page page.<br>
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
	

	@Test(enabled=true,priority=1,
			testName="FilteredInterestResultPage-Verify navigation to Filtered Interest Result page from Discover Topics",
		description="FilteredInterestResultPage-Verify navigation to Filtered Interest Result page by Clicking on Interest on Discover Topics page")
	public void verifyNavigationToFilteredInterestResultPageFromDiscoverTopics_FilteredInterestResultPageTest() throws Exception
	{
		// Go to Explore/Content page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page by tapping on interest on Discover by Interests page
		filteredInterestResultPage=discoverTopicsPage.goToInterestPage(Constants.INTERESTS_BABYCARE);
		LogAssert logAssert=new LogAssert();
		
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.INTERESTS_BABYCARE),"Check if "+Constants.INTERESTS_BABYCARE+" interest title is displayed");
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.FILTERED_INTEREST_RECOMMEDED),"Check if "+Constants.FILTERED_INTEREST_RECOMMEDED+" title is displayed");
		logAssert.assertAll();
		//discoverTopicsPage=filteredInterestResultPage.tapOnCrossBtn();
		//explorePage=discoverTopicsPage.tapOnCrossBtn();
		//explorePage.goToHomePage();
	}
	
	@Test(enabled=true,priority=2,testName="FilteredInterestResultPage-Verify cross button on FilteredInterestResultPage",description="FilteredInterestResultPage-Verify when user clicks on cross  button on 'Filtered by Interest' results page")
	public void verifyCrossBtnOnFilteredInterestResultPage_FilteredInterestResultPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		// Go to Explore/Content page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page by tapping on interest on Discover by Interests page
		filteredInterestResultPage=discoverTopicsPage.goToInterestPage(Constants.INTERESTS_BABYCARE);
		// Tap cross button on Filtered Interest Result page
		discoverTopicsPage=filteredInterestResultPage.tapOnCrossBtn();
		logAssert.assertTrue(discoverTopicsPage.isElementDisplayed(Constants.DISCOVER_TOPICS_BY_INTERESTS)," Check if "+Constants.DISCOVER_TOPICS_BY_INTERESTS+" Page is displayed");
		//explorePage=discoverTopicsPage.tapOnCrossBtn();
		//explorePage.goToHomePage();
		logAssert.assertAll();
	}

	@Test(enabled=true,priority=3,testName="FilteredInterestResultPage-Verify navigation to Discover more section and click interest")
	public void verifyClickInterestOnDiscoverMoreSection_FilteredInterestResultPageTest() throws Exception
	{
		// Go to Explore/Content page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page by tapping on interest on Discover by Interests page
		filteredInterestResultPage=discoverTopicsPage.goToInterestPage(Constants.INTERESTS_BABYCARE);
		Thread.sleep(2000);
		// Scroll to bottom of page
		filteredInterestResultPage.scrollToBottomOfPage();
		Thread.sleep(2000);
		// Scroll to Discover more section
		filteredInterestResultPage.scrollToSection(Constants.CONTENT_DISCOVER_MORE);
		// Tap on Interest
		explorePage.tapOnElementByText(Constants.INTERESTS_FERTILITY);
		FilteredInterestResultPage filteredInterestResultPage=new FilteredInterestResultPage(getDriver(),getPlatformName());
		LogAssert logAssert=new LogAssert();
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.INTERESTS_FERTILITY),"Check if "+Constants.INTERESTS_FERTILITY+" Interest is displayed");
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.FILTERED_INTEREST_RECOMMEDED),"Check if "+Constants.FILTERED_INTEREST_RECOMMEDED+" is displayed");
		logAssert.assertAll();
		
	}

	@Test(enabled=true,priority=4,
			testName="FilteredInterestResultPage-Verify clicking of Article in FilteredInterestResultsPage ",
			description="FilteredInterestResultPage-Verify clicking of Article in FilteredInterestResultsPage")
	public void verifyNavigationToContentPage_FilteredInterestResultPageTest() throws Exception
	{
		LogAssert logAssert=new LogAssert();
		// Go to Explore/Content page
		explorePage=homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage=explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page by tapping on interest on Discover by Interests page
		filteredInterestResultPage=discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);
		// Tap article on Filtered Interest Result page
		contentPage=filteredInterestResultPage.tapArticle();
		Thread.sleep(2000);
		logAssert.assertTrue(contentPage.validateIfContentPageIsDisplayed(),"Check if Content Page is displayed");
		logAssert.assertAll();
	}

}
