package com.pom.crimson.testcases.explore.high;

import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.ContentAPI;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
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
import io.restassured.response.Response;
/**
 * This test class contains test cases for Explore Page or Content page.<br>
 * Explore Page or Content page displays articles and videos as per user's interests and joined groups. <br>
 * User can navigate to this page by :<br><br>
 * Go to Home Page <br> Click <b>Content</b> in bottom menu.
 * <br><br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class ExplorePageTest extends BaseFixture {

	HomePage homePage;
	ExplorePage explorePage;
	DiscoverTopicsPage discoverTopicsPage;
	FilteredInterestResultPage filteredInterestResultPage;
	ContactUsPage contactUsPage;
	GenericFunctionsAPI genericFunctionsAPI;
	GroupAdviceAPI groupAdviceAPI;
	LoginPage loginPage;

	@BeforeMethod()
	public void beforeLocalMethod() {
		genericFunctionsAPI = new GenericFunctionsAPI();
		groupAdviceAPI = new GroupAdviceAPI();

		loginPage = new LoginPage(getDriver(), getPlatformName());

		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		homePage = new HomePage(getDriver(), getPlatformName());
	}

	// Navigation Test Cases
		@Test(enabled = true, priority = 1, 
				testName = "ExplorePageTest-Verify navigation to Discover more section", description = "ExplorePageTest-Verify navigation to Discover more section")
	public void verifyNavigationDiscoverMore_ExplorePageTest() throws Exception {
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		Thread.sleep(5000);
		// Scroll to bottom of pag
		explorePage.scrollToBottomOfPage();
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.CONTENT_DISCOVER_MORE),
				"Check if " + Constants.CONTENT_DISCOVER_MORE + " section is displayed");
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.CONTENT_DISCOVER_MORE_DESCRIPTION),
				"Check if " + Constants.CONTENT_DISCOVER_MORE_DESCRIPTION + " section description is displayed");
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.INTERESTS_FERTILITY),
				"Check if " + Constants.INTERESTS_FERTILITY + " interest in Discover more section is displayed");
		logAssert.assertAll();
		// explorePage.goToHomePage();
	}

		@Test(enabled = true, priority = 2, 
				testName = "ExplorePageTest-Verify  Unable to find what you’re looking for? section", description = "ExplorePageTest-Verify  Unable to find what you’re looking for? section")
	public void verifyUnableToFindSection_ExplorePageTest() throws Exception {
		// Go to Explore/Content Page	
		explorePage = homePage.goToExplorePage();
		Thread.sleep(3000);
		// Scroll to bottom page
		explorePage.scrollToBottomOfPage();
		Thread.sleep(3000);
		// Scroll to section
		explorePage.scrollToSection(Constants.CONTENT_UNABLE_TO_FIND_WHAT);
		Assert.assertTrue(explorePage.isElementDisplayed("Drop us a message"), "Drop us a message link not displayed");
	}

	@Test(enabled = true, priority = 3, 
			testName = "ExplorePageTest-Verify click on Drop us a message link", description = "ExplorePageTest-Verify that user can tap on Drop us a message and redirected to Contact us page")
	public void verifyNavigationFromUnableToFindSection_ExplorePageTest() throws Exception {
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		Thread.sleep(3000);
		// Scroll to bottom of page
		explorePage.scrollToBottomOfPage();
		Thread.sleep(3000);
		// Scroll to section
		explorePage.scrollToSection(Constants.CONTENT_UNABLE_TO_FIND_WHAT);
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.CONTENT_DROP_MESSAGE),
				"Check if " + Constants.CONTENT_DROP_MESSAGE + " link is displayed");
		contactUsPage = explorePage.tapDropUsMessage();
		logAssert.assertTrue(contactUsPage.isElementDisplayed(Constants.CONTENT_CONTACT_US),
				"Check if " + Constants.CONTENT_CONTACT_US + " title is displayed on contact us page");
		logAssert.assertTrue(contactUsPage.isElementDisplayed(Constants.CONTENT_CONTACT_US_EMAIL),
				"Check if " + Constants.CONTENT_CONTACT_US_EMAIL + " Email  link is displayed on contact us page");
		logAssert.assertAll();
	}
}
