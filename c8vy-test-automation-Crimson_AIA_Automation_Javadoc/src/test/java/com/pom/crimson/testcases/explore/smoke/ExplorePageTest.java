package com.pom.crimson.testcases.explore.smoke;

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
			testName = "ExplorePageTest-Verify navigation to Based on your groups Page")
	public void verifyNavigationToBasedOnYourGroupsPage_ExplorePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetch Groups of users from backend
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		// If user not part of any group
		if (Groups_User.size() == 0) {
			Response availGroupList = groupAdviceAPI.getAllAvailableGroups();
			String json = availGroupList.getBody().asString();
			String communityGroupTypeId = genericFunctionsAPI.getTypeOfGroupIdForCommunityGroup();
			groupIDs = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.[?(@.aiabase_typeofgroup=='" + communityGroupTypeId + "' )].listid");
			topicIDs = com.jayway.jsonpath.JsonPath.parse(json).read("$.[?(@.aiabase_typeofgroup=='"
					+ communityGroupTypeId + "' )].aiabase_Topic.aiabase_groupmasterid");

			System.out.println("Size of available groups community: " + groupIDs.size());
			if (groupIDs.size() > 0) {
				// Join first available group
				groupID1 = groupIDs.get(0);
				groupType1 = communityGroupTypeId;
				topicID1 = topicIDs.get(0);
				Response resp = groupAdviceAPI.joinGroupV2(groupID1, groupType1, topicID1);
				logAssert.assertEquals(resp.statusCode(), 200, "Check if user was able to join group successfully");
			}

			else {
				throw new SkipException(
						"No available group for user to join.Can't execute test case further.User requires atleast 1 available group to join");
			}

			// Reopen app for data refresh
			getDriver().launchApp();
			try {
				loginPage.loginAllPlatforms();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}
		// Fetch Groups of users from backend
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}

		// check if content is present in Based on Groups
		List<String> ids = genericFunctionsAPI.getRecommendedContentTitlesBasedOnGroups();
		if (ids.size() == 0) {
			throw new SkipException(
					"No content available for user based on groups. See all button not visible. Test case can't be executed");
		}
		// Go to Explore/Content page
		explorePage = homePage.goToExplorePage();
		// Go to Based on Groups page by clicking on See all link in Based on Groups section
		BasedOnYourGroupsPage basedOnYourGroupsPage = explorePage.goToBasedOnYourGroupsPage();
		logAssert.assertTrue(basedOnYourGroupsPage.isElementDisplayed(Constants.EXPLORE_BASED_ON_YOUR_GROUPS),
				"Check if " + Constants.EXPLORE_BASED_ON_YOUR_GROUPS + " title is displayed");
		logAssert.assertTrue(basedOnYourGroupsPage.validateIfBasedOnYourGroupsPageIsDisplayed(),
				" Check if " + Constants.EXPLORE_BASED_ON_YOUR_GROUPS + " is  displayed ");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 2, 
			testName = "ExplorePageTest-Verify navigation to Discover more section and click interest")
	public void verifyClickInterestOnDiscoverMoreSection_ExplorePageTest() throws Exception {
		// Go to Explore/Content page
		explorePage = homePage.goToExplorePage();
		Thread.sleep(5000);
		// Scroll to Bottom of page
		explorePage.scrollToBottomOfPage();
		// Tap on Interest
		explorePage.tapOnElementByText(Constants.INTERESTS_FERTILITY);
		FilteredInterestResultPage filteredInterestResultPage = new FilteredInterestResultPage(getDriver(),
				getPlatformName());
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.INTERESTS_FERTILITY),
				"Check if " + Constants.INTERESTS_FERTILITY + " Interest is displayed");
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.FILTERED_INTEREST_RECOMMEDED),
				"Check if " + Constants.FILTERED_INTEREST_RECOMMEDED + " title is displayed");
		logAssert.assertAll();
		// explorePage=filteredInterestResultPage.tapOnCrossBtn();
		// explorePage.goToHomePage();

	}

	@Test(enabled = true, priority = 3, 
			testName = "ExplorePageTest-Verify clicking of Article in Based on Interests section", description = "Tap on Content")
	public void verifyClickingOfArticleInBasedOnIntrestSection_ExplorePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Go to Explore/Content page
		explorePage = homePage.goToExplorePage();
		// Scroll to section Based on Interests
		explorePage.scrollToSection(Constants.EXPLORE_BASED_ON_YOUR_INTEREST);
		// Tap article in Based on Interests section
		ContentPage contentPage = explorePage.tapArticle();
		logAssert.assertTrue(contentPage.validateIfContentPageIsDisplayed(), "Check if Content page is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "ExplorePageTest-Verify clicking of Article in Based on Groups section", description = "Tap on Content")
	public void verifyNavigationToContentPageBasedOnGroups_ExplorePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// check if content is present in Based on Groups
		List<String> ids = genericFunctionsAPI.getRecommendedContentTitlesBasedOnGroups();
		if (ids.size() == 0) {
			throw new SkipException(
					"No content available for user based on groups. See all button not visible. Test case can't be executed");
		}
		// Go to Explore/Content page
		explorePage = homePage.goToExplorePage();
		// Scroll to Based on your Groups section
		explorePage.scrollToSection(Constants.EXPLORE_BASED_ON_YOUR_GROUPS);
		// Tap article in Based on your Groups section
		ContentPage contentPage = explorePage.tapArticle();
		logAssert.assertTrue(contentPage.validateIfContentPageIsDisplayed(), "Check if Content page is displayed");
		logAssert.assertAll();

	}

}
