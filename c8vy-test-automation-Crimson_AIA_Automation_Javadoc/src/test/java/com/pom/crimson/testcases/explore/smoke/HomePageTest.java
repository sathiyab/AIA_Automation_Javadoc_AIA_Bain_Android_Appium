package com.pom.crimson.testcases.explore.smoke;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.ContentAPI;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddMoreInterestPage;
import com.pom.crimson.pages.ContentPage;
import com.pom.crimson.pages.BasedOnYourGroupsPage;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.pages.ProfilePage;
import com.pom.crimson.pages.SavedArticlesAndVideosPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.MobileBy;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;
import io.restassured.response.Response;

/**
* This test class contains test cases for Home Page.<br>
* 
* @author Jaspreet Kaur Chagger
*/
public class HomePageTest extends BaseFixture {

	HomePage homePage;
	ExplorePage explorePage;
	MomentsLogPage momentsLogPage;
	ContentPage contentPage;
	GroupAdviceAPI groupAdviceAPI;
	LoginPage loginPage;
	ProfilePage profilePage;
	SavedArticlesAndVideosPage savedArticlesAndVideosPage;
	GenericFunctionsAPI genericFunctionsAPI;

	@BeforeMethod()
	public void beforeLocalMethod() {
		loginPage = new LoginPage(getDriver(), getPlatformName());

		try {
			loginPage.loginAllPlatforms();
		} catch (Exception e) {
			e.printStackTrace();
		}

		homePage = new HomePage(getDriver(), getPlatformName());
		groupAdviceAPI = new GroupAdviceAPI();
		genericFunctionsAPI = new GenericFunctionsAPI();
	}

	@Test(enabled = true, priority = 1, testName = "HomePageTest-Verify navigation to Explore page")
	public void verifyNavigationToExplorePage_HomePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Go to Explore/Content page
		explorePage = homePage.goToExplorePage();
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.EXPLORE_DISCOVER_TOPICS),
				"Check if " + Constants.EXPLORE_DISCOVER_TOPICS + " is displayed-Explore page should be displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 2, testName = "HomePageTest-Verify navigation to Moments log")
	public void verifyNavigationToMomentsLogPage_HomePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Go to Moments log page
		momentsLogPage = homePage.goToMomentsLogPage();
		logAssert.assertTrue(momentsLogPage.isElementDisplayed(Constants.MOMENTS_LOG_TITLE),
				"Check if " + Constants.MOMENTS_LOG_TITLE + " is  displayed-Moments log page should be displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 3, testName = "HomePageTest-Verify navigation to Recommended Content and click See all")
	public void verifyNavigationToRecommendedContentAndClickSeeAll_HomePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Get Titles on Recommended content from back end 
		int recommendedContentSize = genericFunctionsAPI.getRecommendedContentTitlesHomePage().size();
		if (recommendedContentSize == 0) {
			throw new SkipException(
					"No Recommended Content fetched from backend. Please check if Recomended content is available for this user. This test case can't be executed as see all button won't be visible on home page");
		}
		// Click on See all link on Home Page
		explorePage = homePage.goToRecommendedContentAndClickSeeAll();
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.EXPLORE_DISCOVER_TOPICS),
				"Check if " + Constants.EXPLORE_DISCOVER_TOPICS + " is displayed-Explore page should be displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 4, testName = "HomePageTest-Navigation to article Page via Recommended Content section")
	public void verifyNavigationToContentPage_HomePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Get Titles on Recommended content from back end 
		int recommendedContentSize = genericFunctionsAPI.getRecommendedContentTitlesHomePage().size();
		if (recommendedContentSize == 0) {
			throw new SkipException(
					"No Recommended Content fetched from backend. Please check if Recomended content is available for this user. This test case can't be executed as see all button won't be visible on home page");
		}

		// contentPage = homePage.goToRecommendedContentAndClickArticle();

		// get recommended content
		List<String> titles = genericFunctionsAPI.getRecommendedContentTitlesHomePage();
		String firstTitle = titles.get(0);
		// Tap on first article in Recommended content on home page
		homePage.tapOnElementByText(firstTitle);
		ContentPage contentPage = new ContentPage(getDriver(), getPlatformName());

		Thread.sleep(5000);
		logAssert.assertTrue(contentPage.validateIfContentPageIsDisplayed(), "Check if Content page is displayed");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "HomePageTest-Verify like functionality")
	public void verifyLike_HomePageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// Unlike all content
		genericFunctionsAPI.unlike_AllLikedContentForProfile();

		// To refresh homepage
		explorePage = homePage.goToExplorePage();
		homePage = explorePage.goToHomePage();

		// go to home page and like content
		List<String> likesInitial, likesFinal;
		likesInitial = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Initial Like: " + likesInitial.size());

		homePage.tapShare();// Taps on ... button on first article in Recommended Content section
		homePage.tapOnElementByText(Constants.CONTENT_LIKE);// Tap like on first article in Recommended Content section

		likesFinal = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Final Like  " + likesFinal.size());

		logAssert.assertNotEquals(likesFinal.size(), likesInitial.size(),
				"Check if Content is liked and Like is saved in backend");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 5, testName = "HomePageTest-Verify like functionality")
	public void verifyUnLike_HomePageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// Unlike all content
		genericFunctionsAPI.unlike_AllLikedContentForProfile();

		// To refresh homepage
		explorePage = homePage.goToExplorePage();
		homePage = explorePage.goToHomePage();

		// go to home page and like content
		List<String> likesInitial, likesFinal;
		likesInitial = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Initial Like: " + likesInitial.size());

		homePage.tapShare(); // Taps on ... button on first article in Recommended Content section
		homePage.tapOnElementByText(Constants.CONTENT_LIKE);// Tap like on first article in Recommended Content section

		likesFinal = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Final Like  " + likesFinal.size());

		logAssert.assertNotEquals(likesFinal.size(), likesInitial.size(),
				"Check if Content is liked and Like is saved in backend");
		// Now unliking content
		homePage.tapOnElementByText(Constants.CONTENT_LIKE); // Tap like on first article in Recommended Content section again to unlike it
		likesFinal = genericFunctionsAPI.getLikesForProfile();
		System.out.println("---------Final Like  " + likesFinal.size());
		logAssert.assertEquals(likesFinal.size(), likesInitial.size(),
				"Check if Content is unliked and Like is removed in backend");
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "HomePageTest-Verify Bookmark functionality")
	public void verifyBookmark_HomePageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// UnBookmark all content
		genericFunctionsAPI.unbookmark_AllContentForProfile();

		// To refresh homepage
		explorePage = homePage.goToExplorePage();
		homePage = explorePage.goToHomePage();

		List<String> bookmarksInitial, bookmarksFinal;
		bookmarksInitial = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Initial Bookmarks: " + bookmarksInitial.size());

		// go to home page and Bookmark first content
		homePage.tapShare();// Taps on ... button on first article in Recommended Content section
		homePage.tapOnElementByText(Constants.HOME_SAVE_FOR_LATER); // Taps on Save for later on first article in Recommended Content section

		bookmarksFinal = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Final Bookmarks  " + bookmarksFinal.size());

		logAssert.assertNotEquals(bookmarksFinal.size(), bookmarksInitial.size(),
				"Check if Content is Bookmarked and Bookmark is saved in backend");
		
		//get contentid of last Book mark 
			String profileID=
		 genericFunctionsAPI.getProfileId(); 
		 ContentAPI contentAPI=new ContentAPI();
		  Response res=contentAPI.getListOfBookmarksForProfile(profileID);
		  String contentID=res.jsonPath().getString("contentId[-1]");
		  System.out.println("------------------------------------------");
		  
		  System.out.println("Content id: "+contentID);
		  
		  //get Title of content id and check on Save articles page 
		  Response res_Content=contentAPI.getSpecificContentBasedOnStrapiID(contentID);
		  String title=res_Content.jsonPath().getString("title");
		  
		  System.out.println("title is :"+title);
		  // Tap on center of screen and Outside opened menu
		  GenericFunctions.tapByCordinates(getDriver(), 512, 811); 
		  Thread.sleep(1000);
		  // Go to profiles page
		  profilePage=homePage.goToProfilePage();
		  // Go to Saved articles and Videos page
		  savedArticlesAndVideosPage=profilePage.goToSavedArticlesAndVideosPage();
		 logAssert.assertTrue(savedArticlesAndVideosPage.isElementDisplayed(title),
		  "Check if "+title+" is displayed"); 
		 logAssert.assertAll();
		
	}

	@Test(enabled = true, priority = 7, testName = "HomePageTest-Verify remove Bookmark functionality")
	public void verifyRemoveBookmark_HomePageTest() throws Exception {

		LogAssert logAssert = new LogAssert();

		// UnBookmark all content
		genericFunctionsAPI.unbookmark_AllContentForProfile();

		// To refresh homepage
		explorePage = homePage.goToExplorePage();
		homePage = explorePage.goToHomePage();

		List<String> bookmarksInitial, bookmarksFinal;
		bookmarksInitial = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Initial Bookmarks: " + bookmarksInitial.size());

		// go to home page and Bookmark first content
		homePage.tapShare();// Taps on ... button on first article in Recommended Content section
		homePage.tapOnElementByText(Constants.HOME_SAVE_FOR_LATER);// Taps on Save for later on first article in Recommended Content section

		bookmarksFinal = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Final Bookmarks  " + bookmarksFinal.size());

		logAssert.assertNotEquals(bookmarksFinal.size(), bookmarksInitial.size(),
				"Check if Content is Bookmarked and Bookmark is saved in backend");
		
		//get contentid of last Book mark 
		String profileID= genericFunctionsAPI.getProfileId(); 
		ContentAPI contentAPI=new ContentAPI();
	  Response res=contentAPI.getListOfBookmarksForProfile(profileID);
	  String contentID=res.jsonPath().getString("contentId[-1]");
	  System.out.println("------------------------------------------");
	  
	  System.out.println("Content id: "+contentID);
	  
	  //get Title of content id  
	  Response res_Content=contentAPI.getSpecificContentBasedOnStrapiID(contentID);
	  String title=res_Content.jsonPath().getString("title");
	  System.out.println("title of content is :"+title);
	  
		// Now Unbookmark same content
		homePage.tapOnElementByText(Constants.HOME_SAVE_FOR_LATER);// Taps on Save for later on first article in Recommended Content section again
		bookmarksFinal = genericFunctionsAPI.getBookmarksForProfile();
		System.out.println("---------Final Bookmarks   " + bookmarksFinal.size());
		logAssert.assertEquals(bookmarksFinal.size(), bookmarksInitial.size(),
				"Check if Content is unBookmark and Bookmark is removed in backend");
		
		//Check if content is not displayed in saved articles section
		 GenericFunctions.tapByCordinates(getDriver(), 512, 811); 
		  Thread.sleep(1000);
		// Go to profiles page
		  profilePage=homePage.goToProfilePage();
		  // Go to Saved Articles and Videos page
		  savedArticlesAndVideosPage=profilePage.goToSavedArticlesAndVideosPage();
		 logAssert.assertFalse(savedArticlesAndVideosPage.isElementDisplayed(title),
		  "Check if "+title+" is displayed"); 
		 logAssert.assertAll();
		
	}

	@Test(enabled = true, priority = 8, testName = "HomePageTest-Verify Share in group chat", description = "user should get selection for groups to share to")
	public void verifyShareInGroupChat_HomePageTest() throws Exception {

		LogAssert logAssert = new LogAssert();
		// Get Titles of content shown in recommended content section from backend
		int recommendedContentSize = genericFunctionsAPI.getRecommendedContentTitlesHomePage().size();
		if (recommendedContentSize == 0) {
			throw new SkipException(
					"No Recommended Content fetched from backend. Please check if Recomended content is available for this user. This test case can't be executed as see all button won't be visible on home page");
		}
		List<String> Groups_User;
		List<String> groupIDs, topicIDs;
		String groupID1, groupType1, topicID1;
		// Fetch groups that user is part of from backend
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
				loginPage.skipIntroScreens();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				loginPage.LoginWithEmail();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Fetch groups that user is part of from backend
		Groups_User = genericFunctionsAPI.getUserCommunityGroupNames();

		if (Groups_User.size() == 0) {
			throw new SkipException(
					"User not able to join any group.Can't execute test case further.User should be part of atleast one group");
		}

		homePage.tapShare();// Taps on ... button on first article in Recommended Content section
		homePage.tapOnElementByText("Share in group chat"); // Taps on Share in group chat on first article in Recommended Content section

		Thread.sleep(2000);

		for (int i = 0; i < Groups_User.size(); i++) {
			// Check if groups fetched from backend for user are displayed on frontend
			logAssert.assertTrue(homePage.isGroupDisplayed(Groups_User.get(i)),
					"Check if " + Groups_User.get(i) + " group is displayed");

		}

		logAssert.assertAll();

	}

	@Test(enabled = true, priority = 8, testName = "HomePageTest-Verify share in other apps", description = "HomePageTest-Verify if on clicking Share in other apps , Messages app is displayed and deeplink is copied to clipboard")
	public void verifyShareInOtherApps_HomePageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Get Titles of content shown in recommended content section from backend
		int recommendedContentSize = genericFunctionsAPI.getRecommendedContentTitlesHomePage().size();
		if (recommendedContentSize == 0) {
			throw new SkipException(
					"No Recommended Content fetched from backend. Please check if Recomended content is available for this user");
		}

		homePage.tapShare(); // Taps on ... button on first article in Recommended Content section
		homePage.tapOnElementByText(Constants.SHARE_IN_OTHERAPPS); // Taps on Share in Other apps on first article in Recommended Content section
		Thread.sleep(5000);
		// Check if "Messages" app is displayed
		logAssert.assertTrue(homePage.isElementDisplayed(Constants.APP_MESSAGES),
				"Check if " + Constants.APP_MESSAGES + " app is displayed");
		// Copy to clipboard
		homePage.tapOnElementByText("Copy to clipboard");
		Thread.sleep(3000);
		String text = homePage.getTextfromClipboard();
		logAssert.assertAll();
	}

	@Test(enabled = true, priority = 9, testName = "SavedArticlePage-Verify navigation to Saved Articles page")
	public void verifyNavigationToSavedArticlesPage_SavedArticlesPageTest() throws Exception {
		LogAssert logAssert = new LogAssert();
		// Go to Profile Page
		profilePage = homePage.goToProfilePage();
		// Go to Saved articles and Videos page
		savedArticlesAndVideosPage = profilePage.goToSavedArticlesAndVideosPage();
		logAssert.assertTrue(savedArticlesAndVideosPage.isElementDisplayed(Constants.PROFILEPAGE_SAVED_ARTICLES_VIDEOS),
				"Check if " + Constants.PROFILEPAGE_SAVED_ARTICLES_VIDEOS + " is displayed");
		logAssert.assertAll();
	}

	// ----------Test Cases as per Old functionality of
	// like/Unlike/Save/UnSave-----------

	/*
	 * @Test(enabled = true, priority = 5, testName =
	 * "HomePageTest-Verify like functionality") public void
	 * verifyLike_HomePageTest() throws Exception { LogAssert logAssert = new
	 * LogAssert(); int recommendedContentSize=genericFunctionsAPI.
	 * getRecommendedContentTitles_HomePage().size(); if (recommendedContentSize==0)
	 * { throw new
	 * SkipException("No Recommended Content fetched from backend. Please check if Recomended content is available for this user. This test case can't be executed as see all button won't be visible on home page"
	 * ); }
	 * 
	 * List<String> likesInitial,likesFinal;
	 * likesInitial=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Initial Like: "+likesInitial.size());
	 * 
	 * homePage.tapShare(); boolean likeStatus =
	 * homePage.isElementDisplayed(Constants.CONTENT_LIKE); boolean UnlikeStatus =
	 * homePage.isElementDisplayed(Constants.CONTENT_UNLIKE);
	 * 
	 * if (likeStatus == true) {
	 * homePage.tapOnElementByText(Constants.CONTENT_LIKE); } else if (UnlikeStatus
	 * == true) { homePage.tapOnElementByText(Constants.CONTENT_UNLIKE);
	 * Thread.sleep(2000);
	 * 
	 * likesInitial=genericFunctionsAPI.getLikesForProfile(); System.out.
	 * println("---------Initial Like when user has to unlike and than like a content: "
	 * +likesInitial.size());
	 * 
	 * Thread.sleep(1000); homePage.tapOnElementByText(Constants.CONTENT_LIKE); }
	 * else { logAssert.fail("Like/Unlike  button not found"); }
	 * 
	 * Thread.sleep(2000); likesFinal=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Final Like  "+likesFinal.size());
	 * 
	 * logAssert.assertNotEquals(likesFinal, likesInitial,
	 * "Check if Content is liked and Like is saved in backend");
	 * logAssert.assertTrue(homePage.isElementDisplayed(Constants.CONTENT_UNLIKE),
	 * "Check if Content is liked and Like is displayed in frontend");
	 * logAssert.assertAll(); }
	 * 
	 * @Test(enabled = true, priority = 6, testName =
	 * "HomePageTest-Verify Unlike functionality") public void
	 * verifyUnlike_HomePageTest() throws Exception { LogAssert logAssert = new
	 * LogAssert(); int recommendedContentSize=genericFunctionsAPI.
	 * getRecommendedContentTitles_HomePage().size(); if (recommendedContentSize==0)
	 * { throw new
	 * SkipException("No Recommended Content fetched from backend. Please check if Recomended content is available for this user. This test case can't be executed as see all button won't be visible on home page"
	 * ); }
	 * 
	 * 
	 * List<String> likesInitial,likesFinal;
	 * likesInitial=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Initial Like: "+likesInitial.size());
	 * 
	 * homePage.tapShare();
	 * 
	 * boolean likeStatus = homePage.isElementDisplayed(Constants.CONTENT_LIKE);
	 * boolean UnlikeStatus = homePage.isElementDisplayed(Constants.CONTENT_UNLIKE);
	 * 
	 * if (likeStatus == true) {
	 * homePage.tapOnElementByText(Constants.CONTENT_LIKE); Thread.sleep(2000);
	 * likesInitial=genericFunctionsAPI.getLikesForProfile(); System.out.
	 * println("---------Initial Like when user has to like and than unlike a content: "
	 * +likesInitial.size()); homePage.tapOnElementByText(Constants.CONTENT_UNLIKE);
	 * 
	 * } else if (UnlikeStatus == true) {
	 * homePage.tapOnElementByText(Constants.CONTENT_UNLIKE); } else {
	 * logAssert.fail("Like/Unlike  button not found"); }
	 * 
	 * Thread.sleep(2000);
	 * 
	 * likesFinal=genericFunctionsAPI.getLikesForProfile();
	 * System.out.println("---------Final Like  "+likesFinal.size());
	 * logAssert.assertNotEquals(likesFinal, likesInitial,
	 * "Check if Content is Unliked, Like to be removed backend");
	 * logAssert.assertTrue(homePage.isElementDisplayed(Constants.CONTENT_LIKE),
	 * "Check if Content is Unliked  on frontend"); logAssert.assertAll(); }
	 * 
	 * @Test(enabled = true, priority = 7, testName =
	 * "HomePageTest-Verify BookMark functionality") public void
	 * verifyBookMark_HomePageTest() throws Exception { 
	 * LogAssert logAssert = new
	 * LogAssert(); 
	 * int recommendedContentSize=genericFunctionsAPI.
	 * getRecommendedContentTitles_HomePage().size(); 
	 * if (recommendedContentSize==0)
	 * { throw new
	 * SkipException("No Recommended Content fetched from backend. Please check if Recomended content is available for this user. This test case can't be executed as see all button won't be visible on home page"
	 * ); }
	 * 
	 * List<String> bookmarksInitial,bookmarksFinal;
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Initial Bookmarks: "+bookmarksInitial.size());
	 * 
	 * homePage.tapShare();
	 * 
	 * boolean saveStatus =
	 * homePage.isElementDisplayed(Constants.HOME_SAVE_FOR_LATER); boolean
	 * RemoveStatus = homePage.isElementDisplayed(Constants.CONTENT_UNSAVE);
	 * 
	 * if (saveStatus == true) {
	 * homePage.tapOnElementByText(Constants.HOME_SAVE_FOR_LATER);
	 * 
	 * } else if (RemoveStatus == true) {
	 * homePage.tapOnElementByText(Constants.CONTENT_UNSAVE); Thread.sleep(2000);
	 * 
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Updated Bookmarks: "+bookmarksInitial.size());
	 * 
	 * homePage.tapOnElementByText(Constants.HOME_SAVE_FOR_LATER); } else {
	 * logAssert.fail("Save for later/Remove from saved items button not found"); }
	 * 
	 * Thread.sleep(2000);
	 * 
	 * bookmarksFinal=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Final Bookmarks: "+bookmarksFinal.size());
	 * 
	 * logAssert.assertNotEquals(bookmarksFinal, bookmarksInitial,
	 * "Check if Content is bookmarked and Bookmark is saved in backend");
	 * logAssert.assertTrue(homePage.isElementDisplayed(Constants.CONTENT_UNSAVE),
	 * "Check if Content is bookmarked,Bookmark is shown on Front end");
	 * 
	 * //get contentid of last Book mark 
	 * String profileID=
	 * genericFunctionsAPI.getProfileId(); 
	 * ContentAPI contentAPI=new ContentAPI();
	 * Response res=contentAPI.getListOfBookmarksForProfile(profileID); String
	 * contentID=res.jsonPath().getString("contentId[-1]");
	 * System.out.println("------------------------------------------");
	 * 
	 * System.out.println("Content id: "+contentID);
	 * 
	 * //get Title of content id and check on Save articles page Response
	 * res_Content=contentAPI.getSpecificContentBasedOnStrapiID(contentID); String
	 * title=res_Content.jsonPath().getString("title");
	 * 
	 * System.out.println("title is :"+title);
	 * GenericFunctions.tapByCordinates(getDriver(), 512, 811); Thread.sleep(1000);
	 * profilePage=homePage.goToProfilePage();
	 * savedArticlesAndVideosPage=profilePage.goToSavedArticlesAndVideosPage();
	 * logAssert.assertTrue(savedArticlesAndVideosPage.isElementDisplayed(title),
	 * "Check if "+title+" is displayed"); logAssert.assertAll();
	 * 
	 * }
	 * 
	 * @Test(enabled = true, priority = 8, testName =
	 * "HomePageTest-Verify remove BookMark functionality") public void
	 * verifyRemoveBookMark_HomePageTest() throws Exception {
	 * 
	 * 
	 * LogAssert logAssert = new LogAssert(); int
	 * recommendedContentSize=genericFunctionsAPI.
	 * getRecommendedContentTitles_HomePage().size(); if (recommendedContentSize==0)
	 * { throw new
	 * SkipException("No Recommended Content fetched from backend. Please check if Recomended content is available for this user. This test case can't be executed as see all button won't be visible on home page"
	 * ); }
	 * 
	 * List<String> bookmarksInitial,bookmarksFinal;
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Initial Bookmarks: "+bookmarksInitial.size());
	 * homePage.tapShare();
	 * 
	 * boolean saveStatus =
	 * homePage.isElementDisplayed(Constants.HOME_SAVE_FOR_LATER); boolean
	 * RemoveStatus = homePage.isElementDisplayed(Constants.CONTENT_UNSAVE);
	 * 
	 * if (saveStatus == true) {
	 * homePage.tapOnElementByText(Constants.HOME_SAVE_FOR_LATER);
	 * Thread.sleep(2000);
	 * bookmarksInitial=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Updated Bookmarks: "+bookmarksInitial.size());
	 * homePage.tapOnElementByText(Constants.CONTENT_UNSAVE);
	 * 
	 * } else if (RemoveStatus == true) {
	 * homePage.tapOnElementByText(Constants.CONTENT_UNSAVE); } else {
	 * logAssert.fail("Save for later/Remove from saved items button not found"); }
	 * 
	 * Thread.sleep(2000);
	 * bookmarksFinal=genericFunctionsAPI.getBookmarksForProfile();
	 * System.out.println("---------Final Bookmarks: "+bookmarksFinal.size());
	 * logAssert.assertNotEquals(bookmarksFinal, bookmarksInitial,
	 * "Check if Content is Unbookmarked and Bookmark is removed in backend");
	 * 
	 * logAssert.assertTrue(homePage.isElementDisplayed(Constants.
	 * HOME_SAVE_FOR_LATER),
	 * "Check is Content is removed from bookmarks from frontend, Check if "
	 * +Constants.HOME_SAVE_FOR_LATER+" is displayed");
	 * 
	 * logAssert.assertAll(); }
	 */

}
