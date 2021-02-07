package com.pom.crimson.testcases.explore.smoke;

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
import com.pom.crimson.functions.GenericFunctions;
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
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.pages.SavedArticlesAndVideosPage;
import com.pom.crimson.util.Constants;

import io.restassured.response.Response;

/**
 * This test class contains test cases for Content page or Full Article
 * page.<br>
 * ContentPage or Full Article page displays article content. <br>
 * User can navigate to article page by various ways: <br>
 * <br>
 * 1. Go to Home Page <br>
 * Click on any article in <b>Recommended content</b> section.<br>
 * <br>
 * 2. Go to Home Page<br>
 * Click <b>Content</b> in bottom menu. Click on any article in
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS}</b>
 * or
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_INTEREST}</b>
 * section. <br>
 * <br>
 * 3. Go to Home Page <br>
 * Click <b>Content</b> in bottom menu. Click on
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
	ProfilePage profilePage;
	SavedArticlesAndVideosPage savedArticlesAndVideosPage;

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

	@Test(enabled = true, priority = 1, testName = "ContentPageTest-Create Test data-Check if user is part of a group", description = "ContentPageTest-Create Test data-Check if user is part of a group, if not user joins first available group")
	public void userJoinGroup_TestDataCreation_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
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

		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 2, testName = "ContentPageTest-Verify navigation to Discover more section and click interest", description = "ContentPageTest-Verify navigation to Discover more section and click interest")
	public void verifyClickInterestOnDiscoverMoreSection_ContentPageTest() throws Exception {
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);
		// Tap article
		contentPage = filteredInterestResultPage.tapArticle();
		Thread.sleep(2000);
		// Scroll to bottom of page
		contentPage.scrollToBottomOfPage();
		Thread.sleep(2000);
		// Scroll to section
		contentPage.scrollToSection(Constants.CONTENT_DISCOVER_MORE);
		// Tap on Interest
		contentPage.tapOnElementByText(Constants.INTERESTS_FERTILITY);
		FilteredInterestResultPage filteredInterestResultPage = new FilteredInterestResultPage(getDriver(),
				getPlatformName());
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.INTERESTS_FERTILITY),
				"Check if " + Constants.INTERESTS_FERTILITY + " Interest is displayed");
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.FILTERED_INTEREST_RECOMMEDED),
				" Check if " + Constants.FILTERED_INTEREST_RECOMMEDED + " is displayed");
		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 3, testName = "ContentPageTest-Verify like functionality")
	public void verifyLike_ContentPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// Unlike all content
		genericFunctionsAPI.unlike_AllLikedContentForProfile();

		// To refresh homepage
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Home page
		homePage = explorePage.goToHomePage();

		List<String> likesInitial, likesFinal;
		likesInitial = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Initial Like: " + likesInitial.size());

		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);

		// Get articles pertaining to Interest from backend
		List<String> titles = genericFunctionsAPI
				.getRecommendedContentTitlesBasedOnInterest(Constants.INTERESTS_FERTILITY);
		System.out.println("list: " + titles);
		String firstTitle = titles.get(0);
		// Tap on article
		filteredInterestResultPage.tapOnElementByText(firstTitle);

		ContentPage contentPage = new ContentPage(getDriver(), getPlatformName());

		// contentPage = filteredInterestResultPage.tapArticle();
		// Scroll to section having like button
		contentPage.scrollToSection(Constants.CONTENT_LIKE);
		// Tap like button
		contentPage.tapOnElementByText(Constants.CONTENT_LIKE);

		likesFinal = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Final Like  " + likesFinal.size());

		logAssert.assertNotEquals(likesFinal.size(), likesInitial.size(),
				"Check if Content is liked and Like is saved in backend");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "ContentPageTest-Verify Un-like functionality")
	public void verifyUnLike_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();

		// Unlike all content
		genericFunctionsAPI.unlike_AllLikedContentForProfile();

		// To refresh homepage
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to home page
		homePage = explorePage.goToHomePage();

		List<String> likesInitial, likesFinal;
		likesInitial = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Initial Like: " + likesInitial.size());

		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);

		// Get articles pertaining to Interest from backend
		List<String> titles = genericFunctionsAPI
				.getRecommendedContentTitlesBasedOnInterest(Constants.INTERESTS_FERTILITY);
		System.out.println("list: " + titles);
		String firstTitle = titles.get(0);

		// Tap Article
		filteredInterestResultPage.tapOnElementByText(firstTitle);

		ContentPage contentPage = new ContentPage(getDriver(), getPlatformName());

		// contentPage = filteredInterestResultPage.tapArticle();
		// Scroll to Like section
		contentPage.scrollToSection(Constants.CONTENT_LIKE);
		// Tap on like
		contentPage.tapOnElementByText(Constants.CONTENT_LIKE);

		likesFinal = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Final Like  " + likesFinal.size());

		logAssert.assertNotEquals(likesFinal.size(), likesInitial.size(),
				"Check if Content is liked and Like is saved in backend");

		// Now unliking content
		contentPage.tapOnElementByText(Constants.CONTENT_LIKE);
		likesFinal = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Final Like  " + likesFinal.size());
		logAssert.assertEquals(likesFinal.size(), likesInitial.size(),
				"Check if Content is unliked and Like is removed in backend");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 7, testName = "ContentPageTest-Verify Bookmark functionality")
	public void verifyBookmark_ContentPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// UnBookmark all content
		genericFunctionsAPI.unbookmark_AllContentForProfile();

		// To refresh homepage
		explorePage = homePage.goToExplorePage();
		homePage = explorePage.goToHomePage();

		List<String> bookmarksInitial, bookmarksFinal;
		bookmarksInitial = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Initial Bookmarks: " + bookmarksInitial.size());

		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);

		// Get article pertaining to Interest from backend
		List<String> titles = genericFunctionsAPI
				.getRecommendedContentTitlesBasedOnInterest(Constants.INTERESTS_FERTILITY);
		System.out.println("list:---------------    " + titles);
		System.out.println("list size:---------------    " + titles.size());
		System.out.println("list :---------------    " + titles.get(1));

		String firstTitle = titles.get(0);
		// Tap on article
		filteredInterestResultPage.tapOnElementByText(firstTitle);

		ContentPage contentPage = new ContentPage(getDriver(), getPlatformName());
		// Scroll to save section
		contentPage.scrollToSection(Constants.CONTENT_SAVE);
		// Tap on save
		contentPage.tapOnElementByText(Constants.CONTENT_SAVE);

		// Get Bookmarks for profile
		bookmarksFinal = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Final Bookmarks  " + bookmarksFinal.size());

		logAssert.assertNotEquals(bookmarksFinal.size(), bookmarksInitial.size(),
				"Check if Content is Bookmarked and Bookmark is saved in backend");

		// get contentid of last Book mark
		String profileID = genericFunctionsAPI.getProfileId();
		ContentAPI contentAPI = new ContentAPI();
		Response res = contentAPI.getListOfBookmarksForProfile(profileID);
		String contentID = res.jsonPath().getString("contentId[-1]");
		System.out.println("------------------------------------------");

		System.out.println("Content id: " + contentID);

		// get Title of content id and check on Save articles page
		Response res_Content = contentAPI.getSpecificContentBasedOnStrapiID(contentID);
		String title = res_Content.jsonPath().getString("title");

		System.out.println("title is :" + title);

		Thread.sleep(1000);
		// tap back button on content page
		contentPage.tapOnBackBtn();
		// Tap Article on Filtered Interest Result page
		filteredInterestResultPage.tapArticle();
		// Tap cross button on Filtered Interest result page
		explorePage = discoverTopicsPage.tapOnCrossBtn();

		// Go to Profile page by tapping on profile icon in bottom menu
		profilePage = explorePage.goToProfilePage();
		// Go to Saved Articles and Videos page
		savedArticlesAndVideosPage = profilePage.goToSavedArticlesAndVideosPage();
		logAssert.assertTrue(savedArticlesAndVideosPage.isElementDisplayed(title),
				"Check if " + title + " is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 8, testName = "ContentPageTest-Verify remove Bookmark functionality")
	public void verifyRemoveBookmark_ContentPageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// UnBookmark all content
		genericFunctionsAPI.unbookmark_AllContentForProfile();

		// To refresh homepage
		explorePage = homePage.goToExplorePage();
		homePage = explorePage.goToHomePage();

		List<String> bookmarksInitial, bookmarksFinal;
		bookmarksInitial = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Initial Bookmarks: " + bookmarksInitial.size());

		// Go to Content page and Bookmark a Content

		// Go to Explore apge
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);

		// Get article pertaining to Interest from backend
		List<String> titles = genericFunctionsAPI
				.getRecommendedContentTitlesBasedOnInterest(Constants.INTERESTS_FERTILITY);
		String firstTitle = titles.get(0);
		// Tap on article on Filtered Interest Result page
		filteredInterestResultPage.tapOnElementByText(firstTitle);

		ContentPage contentPage = new ContentPage(getDriver(), getPlatformName());

		// contentPage = filteredInterestResultPage.tapArticle();
		// Scroll to section with save link
		contentPage.scrollToSection(Constants.CONTENT_SAVE);
		// Tap on Save on Content/Full Article page
		contentPage.tapOnElementByText(Constants.CONTENT_SAVE);

		// Get final no. of bookmarks from backend
		bookmarksFinal = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Final Bookmarks  " + bookmarksFinal.size());

		logAssert.assertNotEquals(bookmarksFinal.size(), bookmarksInitial.size(),
				"Check if Content is Bookmarked and Bookmark is saved in backend");

		// get contentid of last Book mark
		String profileID = genericFunctionsAPI.getProfileId();
		ContentAPI contentAPI = new ContentAPI();
		Response res = contentAPI.getListOfBookmarksForProfile(profileID);
		String contentID = res.jsonPath().getString("contentId[-1]");
		System.out.println("------------------------------------------");

		System.out.println("Content id: " + contentID);

		// get Title of content id
		Response res_Content = contentAPI.getSpecificContentBasedOnStrapiID(contentID);
		String title = res_Content.jsonPath().getString("title");
		System.out.println("title of content is :" + title);

		// Now Unbookmark same content

		// Tap save again
		homePage.tapOnElementByText(Constants.CONTENT_SAVE);
		// Get final no. of bookmarks from backend
		bookmarksFinal = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Final Bookmarks   " + bookmarksFinal.size());
		logAssert.assertEquals(bookmarksFinal.size(), bookmarksInitial.size(),
				"Check if Content is unBookmark and Bookmark is removed in backend");

		Thread.sleep(1000);
		// Tap back button on content page
		contentPage.tapOnBackBtn();
		// Tap article on Filtered Interest page
		filteredInterestResultPage.tapArticle();
		// Tap cross button on Explore page
		explorePage = discoverTopicsPage.tapOnCrossBtn();

		// Go to profile page
		profilePage = explorePage.goToProfilePage();
		// Go to Saved Articles and videos page
		savedArticlesAndVideosPage = profilePage.goToSavedArticlesAndVideosPage();
		logAssert.assertFalse(savedArticlesAndVideosPage.isElementDisplayed(title),
				"Check if " + title + " is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 7, testName = "ContentPageTest-Verify Share in group chat")
	public void verifyShareInGroupChat_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetch groups from backend that user is part of
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
			//log in
			try {
				loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// Fetch groups from backend that user is part of
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}
		// Go to Explore Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);
		// Tap article on Filtered Interest result page
		contentPage = filteredInterestResultPage.tapArticle();
		contentPage.scrollToSection(Constants.CONTENT_SHARE);
		// Tap Share
		contentPage.tapOnElementByText(Constants.CONTENT_SHARE);
		// Tap Share in Group chat
		contentPage.tapOnElementByText(Constants.SHARE_IN_GROUPCHAT);
		// Check if groups fetched from backend are displayed in frontend
		for (int i = 0; i < Groups_User.size(); i++) {
			logAssert.assertTrue(contentPage.isGroupDisplayed(Groups_User.get(i)),
					"Check if " + Groups_User.get(i) + " group is displayed");

			logAssert.assertAll();

		}

	}

	@Test(enabled = true, priority = 8, testName = "ContentPageTest-Verify share in other apps", description = "Verify on clicking Share in other apps , Messages app is displayed and deeplink is copied to clipboard")
	public void verifyShareInOtherApps_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Discover by Interests page
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		// Go to Filtered Interest Result page
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);
		// Tap article
		contentPage = filteredInterestResultPage.tapArticle();
		contentPage.scrollToSection(Constants.CONTENT_SHARE);
		// Tap Share
		contentPage.tapOnElementByText(Constants.CONTENT_SHARE);
		Thread.sleep(1000);
		// Tap Share in Other apps
		contentPage.tapOnElementByText(Constants.SHARE_IN_OTHERAPPS);
		Thread.sleep(1000);
		// Check if Messages app is displayed
		logAssert.assertTrue(contentPage.isElementDisplayed(Constants.APP_MESSAGES),
				"Check if " + Constants.APP_MESSAGES + " app is displayed");
		// Copy to clipboard
		homePage.tapOnElementByText("Copy to clipboard");
		Thread.sleep(3000);
		String text = homePage.getTextfromClipboard();
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 9, testName = "ContentPageTest-Verify back navigation to Home Page when user  clicks on Back button on Content page")
	public void verifyBackNavigationToHomePageFromContentPage_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Go to Recommnded Content section  and click article
		contentPage = homePage.goToRecommendedContentAndClickArticle();
		Thread.sleep(1000);
		// Tap back button on article page
		contentPage.tapOnBackBtn();
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.RECOMMENDEDCONTENT),
				"Check if " + Constants.RECOMMENDEDCONTENT + "is displayed-Home page should be displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 10, testName = "ContentPageTest-Verify back navigation to Explore when user  clicks on Back button on Content page (opened from Based on Interest section of Explore page)", description = "Verify back navigation to Explore page when user clicks on back button on Content page (navigated from based on Interests section)")
	public void verifyBackNavigationToExplorePageFromContentPage_BasedOnYourInterests_ContentPageTest()
			throws Exception {
		LogAssert logAssert = new LogAssert();
		explorePage = homePage.goToExplorePage();
		explorePage.scrollToSection(Constants.EXPLORE_BASED_ON_YOUR_INTEREST);
		ContentPage contentPage = explorePage.tapArticle();
		Thread.sleep(1000);
		contentPage.tapOnBackBtn();
		logAssert.assertTrue(explorePage.validateIfExplorePageIsDisplayed(), " Check if Explore Page is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 11, testName = "ContentPageTest-Verify back navigation to Explore when user  clicks on Back button on Content page(opened from Based on Groups section of Explore page)", description = "Verify back navigation to Explore page when user clicks on back button on Content page (navigated from based on Groups section)")
	public void verifyBackNavigationToExplorePageFromContentPage_BasedOnYourGroups_ContentPageTest() throws Exception {

		List<String> ids = genericFunctionsAPI.getRecommendedContentTitlesBasedOnGroups();
		if (ids.size() == 0) {
			throw new SkipException("No data in Based on Group section on Explore page. Test case can't be executed");
		}

		LogAssert logAssert = new LogAssert();
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Scroll to Based on your Groups section
		explorePage.scrollToSection(Constants.EXPLORE_BASED_ON_YOUR_GROUPS);
		// Tap article in Based on your Groups section
		ContentPage contentPage = explorePage.tapArticle();
		Thread.sleep(1000);
		// Tap backbutton on article
		contentPage.tapOnBackBtn();
		logAssert.assertTrue(explorePage.validateIfExplorePageIsDisplayed(), "Check if Explore Page is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 12, testName = "ContentPageTest-Verify back navigation to Filtered Interest Result Page when user  clicks on Back button on Content page")
	public void verifyBackNavigationToFilteredInterestResultPageFromContentPage_ContentPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		explorePage = homePage.goToExplorePage();
		discoverTopicsPage = explorePage.goToDiscoverTopicsPage();
		filteredInterestResultPage = discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
		Thread.sleep(2000);
		contentPage = filteredInterestResultPage.tapArticle();
		Thread.sleep(1000);
		contentPage.tapOnBackBtn();
		logAssert.assertTrue(filteredInterestResultPage.isElementDisplayed(Constants.FILTERED_INTEREST_RECOMMEDED),
				"Check if " + Constants.FILTERED_INTEREST_RECOMMEDED
						+ " title is displayed-Filtered Interest Result Page should be displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 13, testName = "ContentPageTest-Verify back navigation to Based on Groups Page when user clicks on Back button on Content page")
	public void verifyBackNavigationToBasedOnGroupsPageFromContentPage_ContentPageTest() throws Exception {

		List<String> ids = genericFunctionsAPI.getRecommendedContentTitlesBasedOnGroups();
		if (ids.size() == 0) {
			throw new SkipException("No data in Based on Group section on Explore page. Test case can't be executed");
		}

		LogAssert logAssert = new LogAssert();
		// Go to Explore/Content Page
		explorePage = homePage.goToExplorePage();
		// Go to Based on your Groups page
		BasedOnYourGroupsPage basedOnYourGroupsPage = explorePage.goToBasedOnYourGroupsPage();
		Thread.sleep(2000);
		// Tap article
		contentPage = basedOnYourGroupsPage.tapArticle();
		Thread.sleep(1000);
		// Tap back button
		contentPage.tapOnBackBtn();
		logAssert.assertTrue(basedOnYourGroupsPage.isElementDisplayed(Constants.EXPLORE_BASED_ON_YOUR_GROUPS),
				"Check if " + Constants.EXPLORE_BASED_ON_YOUR_GROUPS
						+ " title is displayed, Based on your groups page should be displayed");
		logAssert.assertAll();
	}

	// ----------Test Cases as per Old functionality of
	// like/Unlike/Save/UnSave-----------

	/*
	 * @Test(enabled = true, priority = 3, testName =
	 * "ContentPageTest-Verify like functionality") public void
	 * verifyLike_ContentPageTest() throws Exception { LogAssert logAssert = new
	 * LogAssert();
	 * 
	 * List<String> likesInitial,likesFinal;
	 * likesInitial=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Initial Like: " + likesInitial.size());
	 * 
	 * explorePage = homePage.goToExplorePage(); discoverTopicsPage =
	 * explorePage.goToDiscoverTopicsPage(); filteredInterestResultPage =
	 * discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
	 * Thread.sleep(2000); contentPage = filteredInterestResultPage.tapArticle();
	 * contentPage.scrollToSection(Constants.CONTENT_SHARE);
	 * 
	 * boolean likeStatus = contentPage.isElementDisplayed(Constants.CONTENT_LIKE);
	 * boolean UnlikeStatus =
	 * contentPage.isElementDisplayed(Constants.CONTENT_UNLIKE);
	 * 
	 * if (likeStatus == true) {
	 * contentPage.tapOnElementByText(Constants.CONTENT_LIKE); } else if
	 * (UnlikeStatus == true) {
	 * contentPage.tapOnElementByText(Constants.CONTENT_UNLIKE); Thread.sleep(2000);
	 * 
	 * likesInitial=genericFunctionsAPI.getLikesForProfile(); System.out.println(
	 * "---------Initial Like when user has to unlike and than like a content: " +
	 * likesInitial.size());
	 * 
	 * Thread.sleep(1000); contentPage.tapOnElementByText(Constants.CONTENT_LIKE); }
	 * else { logAssert.fail("Like/Unlike  button not found"); }
	 * 
	 * Thread.sleep(2000); likesFinal=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Final Like  " + likesFinal.size());
	 * logAssert.assertNotEquals(likesFinal, likesInitial,
	 * "Check if Content is liked and Like is saved in backend");
	 * logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_UNLIKE)
	 * , "Check if Content is liked and Like is displayed in frontend");
	 * logAssert.assertAll();
	 * 
	 * }
	 * 
	 * @Test(enabled = true, priority = 4, testName =
	 * "ContentPageTest-Verify Unlike functionality") public void
	 * verifyUnlike_ContentPageTest() throws Exception { LogAssert logAssert = new
	 * LogAssert();
	 * 
	 * List<String> likesInitial,likesFinal;
	 * likesInitial=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Initial Like: " + likesInitial.size());
	 * 
	 * explorePage = homePage.goToExplorePage(); discoverTopicsPage =
	 * explorePage.goToDiscoverTopicsPage(); filteredInterestResultPage =
	 * discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
	 * Thread.sleep(2000); contentPage = filteredInterestResultPage.tapArticle();
	 * contentPage.scrollToSection(Constants.CONTENT_SHARE);
	 * 
	 * boolean likeStatus = contentPage.isElementDisplayed(Constants.CONTENT_LIKE);
	 * boolean UnlikeStatus =
	 * contentPage.isElementDisplayed(Constants.CONTENT_UNLIKE);
	 * 
	 * if (likeStatus == true) {
	 * contentPage.tapOnElementByText(Constants.CONTENT_LIKE); Thread.sleep(2000);
	 * likesInitial=genericFunctionsAPI.getLikesForProfile(); System.out.println(
	 * "---------Initial Like when user has to like and than unlike a content: " +
	 * likesInitial.size());
	 * 
	 * contentPage.tapOnElementByText(Constants.CONTENT_UNLIKE);
	 * 
	 * } else if (UnlikeStatus == true) {
	 * contentPage.tapOnElementByText(Constants.CONTENT_UNLIKE); } else {
	 * logAssert.fail("Like/Unlike  button not found"); }
	 * 
	 * Thread.sleep(2000);
	 * 
	 * likesFinal=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Final Like  " + likesFinal.size());
	 * 
	 * logAssert.assertNotEquals(likesFinal, likesInitial,
	 * "Check if Content is Unliked, Like to be removed backend");
	 * logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_LIKE),
	 * "Check if Content is Unliked  on frontend"); logAssert.assertAll();
	 * 
	 * }
	 * 
	 * @Test(enabled = true, priority = 5, testName =
	 * "ContentPageTest-Verify BookMark functionality") public void
	 * verifyBookMark_ContentPageTest() throws Exception { LogAssert logAssert = new
	 * LogAssert();
	 * 
	 * List<String> bookmarksInitial,bookmarksFinal;
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Initial Bookmarks: " + bookmarksInitial.size());
	 * 
	 * explorePage = homePage.goToExplorePage(); discoverTopicsPage =
	 * explorePage.goToDiscoverTopicsPage(); filteredInterestResultPage =
	 * discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
	 * Thread.sleep(2000); contentPage = filteredInterestResultPage.tapArticle();
	 * 
	 * contentPage.scrollToSection(Constants.CONTENT_SHARE);
	 * 
	 * boolean saveStatus = contentPage.isElementDisplayed(Constants.CONTENT_SAVE);
	 * boolean RemoveStatus =
	 * contentPage.isElementDisplayed(Constants.CONTENT_UNSAVE);
	 * 
	 * if (saveStatus == true) {
	 * contentPage.tapOnElementByText(Constants.CONTENT_SAVE);
	 * 
	 * } else if (RemoveStatus == true) {
	 * contentPage.tapOnElementByText(Constants.CONTENT_UNSAVE); Thread.sleep(2000);
	 * 
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Updated Bookmarks: " + bookmarksInitial.size());
	 * 
	 * contentPage.tapOnElementByText(Constants.CONTENT_SAVE); } else {
	 * logAssert.fail("Save for later/Remove from saved items button not found"); }
	 * 
	 * Thread.sleep(2000);
	 * 
	 * bookmarksFinal=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Final Bookmarks: " + bookmarksFinal.size());
	 * 
	 * logAssert.assertNotEquals(bookmarksFinal, bookmarksInitial,
	 * "Check if Content is bookmarked and Bookmark is saved in backend");
	 * logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_UNSAVE)
	 * , "Check if Content is bookmarked,Bookmark is shown on Front end");
	 * logAssert.assertAll();
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Test(enabled = true, priority = 6, testName =
	 * "ContentPageTest-Verify remove BookMark functionality") public void
	 * verifyRemoveBookMark_ContentPageTest() throws Exception { LogAssert logAssert
	 * = new LogAssert(); List<String> bookmarksInitial,bookmarksFinal;
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Initial Bookmarks: " + bookmarksInitial.size());
	 * 
	 * explorePage = homePage.goToExplorePage(); discoverTopicsPage =
	 * explorePage.goToDiscoverTopicsPage(); filteredInterestResultPage =
	 * discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
	 * Thread.sleep(2000); contentPage = filteredInterestResultPage.tapArticle();
	 * 
	 * contentPage.scrollToSection(Constants.CONTENT_SHARE);
	 * 
	 * boolean saveStatus = contentPage.isElementDisplayed(Constants.CONTENT_SAVE);
	 * boolean RemoveStatus =
	 * contentPage.isElementDisplayed(Constants.CONTENT_UNSAVE);
	 * 
	 * if (saveStatus == true) {
	 * contentPage.tapOnElementByText(Constants.CONTENT_SAVE); Thread.sleep(2000);
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Updated Bookmarks: " + bookmarksInitial.size());
	 * contentPage.tapOnElementByText(Constants.CONTENT_UNSAVE);
	 * 
	 * } else if (RemoveStatus == true) {
	 * 
	 * contentPage.tapOnElementByText(Constants.CONTENT_UNSAVE);
	 * 
	 * } else {
	 * logAssert.fail("Save for later/Remove from saved items button not found"); }
	 * 
	 * Thread.sleep(2000);
	 * bookmarksFinal=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Final Bookmarks: " + bookmarksFinal.size());
	 * 
	 * logAssert.assertNotEquals(bookmarksFinal, bookmarksInitial,
	 * "Check if Content is Unbookmarked and Bookmark is removed in backend");
	 * 
	 * logAssert.assertTrue(contentPage.isElementDisplayed(Constants.CONTENT_SAVE),
	 * "Check is Content is removed from bookmarks from frontend, Check if " +
	 * Constants.CONTENT_SAVE + " is displayed");
	 * 
	 * logAssert.assertAll(); }
	 * 
	 * @Test(enabled = true, priority = 7, testName =
	 * "ContentPageTest-Verify Share in group chat") public void
	 * verifyShareInGroupChat_ContentPageTest() throws Exception { LogAssert
	 * logAssert = new LogAssert(); List<String> Groups_User; List<String> groupIDs,
	 * topicIDs; String groupID1, groupType1, topicID1; Groups_User =
	 * genericFunctionsAPI.getUserCommunityGroupNames();
	 * 
	 * // If user not part of any group if (Groups_User.size() == 0) { Response
	 * availGroupList = groupAdviceAPI.getAllAvailableGroups(); String json =
	 * availGroupList.getBody().asString(); String communityGroupTypeId =
	 * genericFunctionsAPI.getTypeOfGroupIdForCommunityGroup(); groupIDs =
	 * com.jayway.jsonpath.JsonPath.parse(json)
	 * .read("$.[?(@.aiabase_typeofgroup=='" + communityGroupTypeId +
	 * "' )].listid"); topicIDs =
	 * com.jayway.jsonpath.JsonPath.parse(json).read("$.[?(@.aiabase_typeofgroup=='"
	 * + communityGroupTypeId + "' )].aiabase_Topic.aiabase_groupmasterid");
	 * 
	 * System.out.println("Size of available groups community: " + groupIDs.size());
	 * if (groupIDs.size() > 0) { // Join first available group groupID1 =
	 * groupIDs.get(0); groupType1 = communityGroupTypeId; topicID1 =
	 * topicIDs.get(0); Response resp = groupAdviceAPI.joinGroupV2(groupID1,
	 * groupType1, topicID1); logAssert.assertEquals(resp.statusCode(), 200,
	 * "Check if user was able to join group successfully"); }
	 * 
	 * else { throw new SkipException(
	 * "No available group for user to join.Can't execute test case further.User requires atleast 1 available group to join"
	 * ); }
	 * 
	 * // Reopen app for data refresh getDriver().launchApp();
	 * 
	 * try { loginPage.loginAllPlatforms(); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();
	 * 
	 * if (Groups_User.size() == 0) { throw new SkipException(
	 * "User not able to join any group.Can't execute test case further.User should be part of atleast one group"
	 * ); }
	 * 
	 * explorePage = homePage.goToExplorePage(); discoverTopicsPage =
	 * explorePage.goToDiscoverTopicsPage(); filteredInterestResultPage =
	 * discoverTopicsPage.goToInterestPage(Constants.INTERESTS_FERTILITY);
	 * Thread.sleep(2000); contentPage = filteredInterestResultPage.tapArticle();
	 * contentPage.scrollToSection(Constants.CONTENT_SHARE);
	 * contentPage.tapOnElementByText(Constants.CONTENT_SHARE);
	 * contentPage.tapOnElementByText(Constants.SHARE_IN_GROUPCHAT); for (int i = 0;
	 * i < Groups_User.size(); i++) {
	 * logAssert.assertTrue(contentPage.isGroupDisplayed(Groups_User.get(i)),
	 * "Check if " + Groups_User.get(i) + " group is displayed");
	 * 
	 * logAssert.assertAll();
	 * 
	 * }
	 * 
	 * }
	 */

}
