package com.pom.crimson.testcases.explore.high;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.crimson.api.ContentAPI;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.BasedOnYourGroupsPage;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.ContentPage;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.FilteredInterestResultPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.util.Constants;

import io.restassured.response.Response;
/**
 *This test class contains test cases for Content page or Full Article page.<br>
 * ContentPage or Full Article page displays article content. <br>
 * User can navigate to article page by various ways: <br> <br>
 * 1. Go to Home Page <br>Click on any article in <b>Recommended content</b>
 * section.<br><br>
 * 2. Go to Home Page<br> Click <b>Content</b> in bottom menu. Click on any
 * article in
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS}</b>
 * or
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_INTEREST}</b>
 * section. <br><br>
 * 3. Go to Home Page <br> Click <b>Content</b> in bottom menu. Click on
 * <b>{@value com.pom.crimson.util.Constants#DISCOVER_TOPICS_BY_INTERESTS}</b>
 * link. Select interest.<br>
 * <br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class ContentPageTest extends BaseFixture {

	HomePage homePage;
	ExplorePage explorePage;
	DiscoverTopicsPage discoverTopicsPage;
	FilteredInterestResultPage filteredInterestResultPage;
	ContactUsPage contactUsPage;
	ContentPage contentPage;
	GenericFunctionsAPI genericFunctionsAPI;
	GroupAdviceAPI groupAdviceAPI;
	LoginPage loginPage;

	@BeforeMethod()
	public void beforeLocalMethod() {
		System.out.println("In Local before method");
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

	@Test(enabled = true, priority = 1, testName = "ContentPageTest-Create Test data-Check if user is part of a group", 
			description = "ContentPageTest-Create Test data-Check if user is part of a group, if not user joins first available group")
	public void userJoinGroup_TestDataCreation_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetch groups that user is part of
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
		// Fetch groups that user is part of
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 2, 
			testName = "ContentPageTest-Verify Unable To Find Section On ContentPage and verify navigation to contact us page", description = "ContentPageTest-Verify Unable to find section On ContentPage section is displayed on Content Page and check if contact us page is displayed on clicking Drop us message link ")
	public void verifyUnableToFindSectionOnContentPage_ContentPageTest() throws Exception {
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		// Tap Article
		contentPage = filteredInterestResultPage.tapArticle();
		Thread.sleep(2000);
		// Scroll to section
		contentPage.scrollToSection(Constants.CONTENT_UNABLE_TO_FIND_WHAT);
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(contentPage.verifyUnableToFindMessage(),
				"Check if " + Constants.CONTENT_UNABLE_TO_FIND + " section is displayed");
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_DROP_MESSAGE),
				"Check if " + Constants.CONTENT_DROP_MESSAGE + " link is displayed");
		// Tap "Drop us a message" link
		contactUsPage = contentPage.tapDropUsMessage();
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_CONTACT_US),
				" Check if " + Constants.CONTENT_CONTACT_US + " title is displayed on contact us page");
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_CONTACT_US_EMAIL),
				"Check if " + Constants.CONTENT_CONTACT_US_EMAIL + " link is displayed on contact us page");
		logAssert.assertAll();
	}
	
	@Test(enabled = true, priority = 3, 
			testName = "ContentPageTest-Verify feedback section on Content Page and verify navigation to contact us page", description = "ContentPageTest-Verify feedback section is displayed on Content Page and check if contact us page is displayed on clicking Drop us message link ")
	public void verifyFeedbackSectionIsDisplayedOnContentPage_ContentPageTest() throws Exception {
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		// Tap article
		contentPage = filteredInterestResultPage.tapArticle();
		Thread.sleep(2000);
		// Scroll to section
		contentPage.scrollToSection(Constants.CONTENT_SHARE);
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_FEEDBACK),
				"Check if " + Constants.CONTENT_FEEDBACK + " title is displayed");
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_DROP_MESSAGE),
				"Check if " + Constants.CONTENT_DROP_MESSAGE + " link is displayed");
		contactUsPage = contentPage.tapDropUsMessage();
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_CONTACT_US),
				" Check if " + Constants.CONTENT_CONTACT_US + " title is displayed on contact us page");
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_CONTACT_US_EMAIL),
				"Check if" + Constants.CONTENT_CONTACT_US_EMAIL + "  link is displayed on contact us page");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, 
			testName = "ContentPageTest-Verify navigation to Discover more section on Content Page", description = "ContentPageTest-Verify navigation to Discover more section on Content Page")
	public void verifyNavigationDiscoverMore_ContentPageTest() throws Exception {
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		
		Thread.sleep(2000);
		// Tap Article
		contentPage = filteredInterestResultPage.tapArticle();
		Thread.sleep(2000);
		// Scroll to bottom of page
		contentPage.scrollToBottomOfPage();
		Thread.sleep(2000);
		// scroll to section
		contentPage.scrollToSection(Constants.CONTENT_UNABLE_TO_FIND);
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_DISCOVER_MORE),
				"Check if " + Constants.CONTENT_DISCOVER_MORE + " section is displayed");
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_DISCOVER_MORE_DESCRIPTION),
				"Check if " + Constants.CONTENT_DISCOVER_MORE_DESCRIPTION + "  description is displayed");
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.INTERESTS_FERTILITY),
				"Check if " + Constants.INTERESTS_FERTILITY + " interest in Discover more section is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, 
			testName = "ContentPageTest-Verify navigation to Similar Content and click Content")
	public void verifyClickOnSimilarContent_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);
		//Tap article
		contentPage = filteredInterestResultPage.tapArticle();
		Thread.sleep(2000);
		// Tap on First article in Similar content section on article page
		contentPage.tapOnContentInSimilarContentSection();
		Thread.sleep(2000);
		logAssert.assertFalse(contentPage.isElementDisplayed(Constants.CONTENT_SIMILAR_CONTENT),
				"Check if user able to tap on article In Similar Content");
		logAssert.assertAll();

	}
	
	
	
}
