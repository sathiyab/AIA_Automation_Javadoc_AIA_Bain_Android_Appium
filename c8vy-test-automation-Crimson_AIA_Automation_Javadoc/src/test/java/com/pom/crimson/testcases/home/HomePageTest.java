package com.pom.crimson.testcases.home;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pom.crimson.api.ContentAPI;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.pages.AddMoreInterestPage;
import com.pom.crimson.pages.ContentPage;
import com.pom.crimson.pages.BasedOnYourGroupsPage;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.pages.MomentsLogPage;
import com.pom.crimson.util.Constants;

import io.appium.java_client.MobileBy;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.clipboard.HasClipboard;
import io.restassured.response.Response;

/**
 * This class contains test methods to test <b>Content and Moments</b> related features in Home page 
 * 
 * @author Jaspreet Kaur 
 */	
public class HomePageTest extends BaseFixture {

	HomePage homePage;
	ExplorePage explorePage;
	MomentsLogPage momentsLogPage;
	ContentPage contentPage;
	GroupAdviceAPI groupAdviceAPI;
	UserProfileControllerAPI userProfileControllerAPI;
	ContentAPI contentAPI;

	@BeforeMethod()
	public void beforeLocalMethod() {
		LoginPage loginPage = new LoginPage(getDriver(), getPlatformName());
		loginPage.skipIntroScreens();
        try {
			loginPage.LoginWithEmail();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		homePage=new HomePage(getDriver(),getPlatformName());
			
			userProfileControllerAPI = new UserProfileControllerAPI();
			groupAdviceAPI = new GroupAdviceAPI();
			contentAPI = new ContentAPI();
		}
	

	// Navigation Test Cases-to be added-Moments log/Explore page
	@Test(enabled = true, priority = 1, testName = "Verify navigation to Explore page")
	public void verifyNavigationToExplorePage_HomePageTest() throws Exception {
		explorePage = homePage.goToExplorePage();
		Assert.assertTrue(explorePage.isElementDisplayed("Discover topics"), "Explore page not displayed");
	}

	@Test(enabled = true, priority = 2, testName = "Verify navigation to Moments log")
	public void verifyNavigationToMomentsLogPage_HomePageTest() throws Exception {
		momentsLogPage = homePage.goToMomentsLogPage();
		Assert.assertTrue(momentsLogPage.isElementDisplayed("Moments log"),
				"Moments Log page is not displayed as title not displayed");
	}

	@Test(enabled = true, priority = 3, testName = "Verify navigation to Recommended Content and click See all")
	public void verifyNavigationToRecommendedContentAndClickSeeAll_HomePageTest() throws Exception {
		explorePage = homePage.goToRecommendedContentAndClickSeeAll();
		;
		Assert.assertTrue(explorePage.isElementDisplayed("Discover topics"), "Explore page not displayed");
	}

	@Test(enabled = true, priority = 4, testName = "Navigation to article Page via Recommended Content section")
	public void verifyNavigationToContentPage_HomePageTest() throws Exception {
		contentPage = homePage.goToRecommendedContentAndClickArticle();
		Thread.sleep(5000);
		Assert.assertTrue(contentPage.validateIfContentPageIsDisplayed(), "Content page not displayed");
	}

	@Test(enabled = true, priority = 5, testName = "Verify like functionality")
	public void verifyLike_HomePageTest() throws Exception {
		// contentPage=homePage.goToRecommendedContentAndClickArticle();

		// Get Profile of User
		Response profiles = userProfileControllerAPI.getProfiles();
		String contactId = profiles.jsonPath().getString("contactid");

		// get no. of likes for Profile
		Response response_Likes = contentAPI.getListOfLikesForProfile(contactId);
		List<String> likesInitial;

		if (response_Likes.statusCode() == 200) {
			likesInitial = response_Likes.jsonPath().getList("id");
		} else {
			likesInitial = Collections.emptyList();
		}

		homePage.tapShare();
		boolean likeStatus = homePage.isElementDisplayed("Like");
		boolean UnlikeStatus = homePage.isElementDisplayed("Unlike");

		if (likeStatus == true) {
			homePage.tapOnElementByText("Like");
		} else if (UnlikeStatus == true) {
			homePage.tapOnElementByText("Unlike");
			homePage.tapOnElementByText("Like");
		} else {
			Assert.fail("Like/Unlike  button not found");
		}

		response_Likes = contentAPI.getListOfLikesForProfile(contactId);
		List<String> likesFinal;

		if (response_Likes.statusCode() == 200) {
			likesFinal = response_Likes.jsonPath().getList("id");
		} else {
			likesFinal = Collections.emptyList();
		}

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertNotEquals(likesFinal, likesInitial, "Content not liked, Like not saved in backend");
		softAssert.assertTrue(homePage.isElementDisplayed("Unlike"),
				"Content not liked, Like not displayed in frontend");
		softAssert.assertAll();
	}

	@Test(enabled = true, priority = 6, testName = "Verify Unlike functionality")
	public void verifyUnlike_HomePageTest() throws Exception {
		// contentPage=homePage.goToRecommendedContentAndClickArticle();

		// Get Profile of User
		Response profiles = userProfileControllerAPI.getProfiles();
		String contactId = profiles.jsonPath().getString("contactid");

		// get no. of likes for Profile
		Response response_Likes = contentAPI.getListOfLikesForProfile(contactId);
		List<String> likesInitial;

		if (response_Likes.statusCode() == 200) {
			likesInitial = response_Likes.jsonPath().getList("id");
		} else {
			likesInitial = Collections.emptyList();
		}
		homePage.tapShare();

		boolean likeStatus = homePage.isElementDisplayed("Like");
		boolean UnlikeStatus = homePage.isElementDisplayed("Unlike");

		if (likeStatus == true) {
			homePage.tapOnElementByText("Like");
			homePage.tapOnElementByText("Unlike");

		} else if (UnlikeStatus == true) {
			homePage.tapOnElementByText("Unlike");
		} else {
			Assert.fail("Like/Unlike  button not found");
		}

		response_Likes = contentAPI.getListOfLikesForProfile(contactId);
		List<String> likesFinal;

		if (response_Likes.statusCode() == 200) {
			likesFinal = response_Likes.jsonPath().getList("id");
		} else {
			likesFinal = Collections.emptyList();
		}

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertNotEquals(likesFinal, likesInitial, "Content not liked, Like not saved in backend");
		softAssert.assertTrue(homePage.isElementDisplayed("Like"), "Content not UnLiked");
		softAssert.assertAll();
	}

	@Test(enabled = true, priority = 7, testName = "Verify BookMark functionality")
	public void verifyBookMark_HomePageTest() throws Exception {
		// Get Profile of User
		Response profiles = userProfileControllerAPI.getProfiles();
		String contactId = profiles.jsonPath().getString("contactid");

		// get no. of Bookmarks for Profile
		Response response_Bookmarks = contentAPI.getListOfLikesForProfile(contactId);
		List<String> bookmarksInitial;

		if (response_Bookmarks.statusCode() == 200) {
			bookmarksInitial = response_Bookmarks.jsonPath().getList("id");
		} else {
			bookmarksInitial = Collections.emptyList();
		}

		homePage.tapShare();

		boolean saveStatus = homePage.isElementDisplayed("Save for later");
		boolean RemoveStatus = homePage.isElementDisplayed("Remove from saved items");

		if (saveStatus == true) {
			homePage.tapOnElementByText("Save for later");

		} else if (RemoveStatus == true) {
			homePage.tapOnElementByText("Remove from saved items");
			homePage.tapOnElementByText("Save for later");
		} else {
			Assert.fail("Save for later/Remove from saved items button not found");
		}

		response_Bookmarks = contentAPI.getListOfLikesForProfile(contactId);
		List<String> bookmarksFinal;

		if (response_Bookmarks.statusCode() == 200) {
			bookmarksFinal = response_Bookmarks.jsonPath().getList("id");
		} else {
			bookmarksFinal = Collections.emptyList();
		}

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertNotEquals(bookmarksFinal, bookmarksInitial,
				"Content not bookmarked, Bookmark not saved in backend");
		Assert.assertTrue(homePage.isElementDisplayed("Remove from saved items"),
				"Content not bookmarked,Bookmark not shown on Front end");
	}

	@Test(enabled = true, priority = 8, testName = "Verify remove BookMark functionality")
	public void verifyRemoveBookMark_HomePageTest() throws Exception {

		// Get Profile of User
		Response profiles = userProfileControllerAPI.getProfiles();
		String contactId = profiles.jsonPath().getString("contactid");

		// get no. of Bookmarks for Profile
		Response response_Bookmarks = contentAPI.getListOfLikesForProfile(contactId);
		List<String> bookmarksInitial;

		if (response_Bookmarks.statusCode() == 200) {
			bookmarksInitial = response_Bookmarks.jsonPath().getList("id");
		} else {
			bookmarksInitial = Collections.emptyList();
		}

		homePage.tapShare();

		boolean saveStatus = homePage.isElementDisplayed("Save for later");
		boolean RemoveStatus = homePage.isElementDisplayed("Remove from saved items");

		if (saveStatus == true) {
			homePage.tapOnElementByText("Save for later");
			homePage.tapOnElementByText("Remove from saved items");

		} else if (RemoveStatus == true) {
			homePage.tapOnElementByText("Remove from saved items");
		} else {
			Assert.fail("Save for later/Remove from saved items button not found");
		}
		response_Bookmarks = contentAPI.getListOfLikesForProfile(contactId);
		List<String> bookmarksFinal;

		if (response_Bookmarks.statusCode() == 200) {
			bookmarksFinal = response_Bookmarks.jsonPath().getList("id");
		} else {
			bookmarksFinal = Collections.emptyList();
		}

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertNotEquals(bookmarksFinal, bookmarksInitial,
				"Content not Unbookmarked, Bookmark not removed in backend");

		Assert.assertTrue(homePage.isElementDisplayed("Save for later"),
				"Content was not removed from bookmarks from frontend");
	}

	// prequisite-user part of preschoolers
	// to be completed when internal sharing is implemented
	@Test(enabled = true, priority = 9, testName = "Verify Share in group chat",description="user should get selection for groups to share to")
	public void verifyShareInGroupChat_HomePageTest() throws Exception {

		// Get Profile of User
		Response profiles = userProfileControllerAPI.getProfiles();
		String contactId = profiles.jsonPath().getString("contactid");

		// get no. of groups
		Response response_AllGroups = groupAdviceAPI.getAllGroups("COMMUNITY");
		List<String> AllGroups;

		if (response_AllGroups.statusCode() == 200) {
			AllGroups = response_AllGroups.jsonPath().getList("value.listname");
		} else {
			AllGroups = Collections.emptyList();
		}

		System.out.println("No. of groups available" + AllGroups.size());
		System.out.println("All Group names  " + AllGroups);

		// get no. of groups user is part of
		Response response_groups_user = groupAdviceAPI.getGroupByUserID("COMMUNITY");
		List<String> Groups_User;

		if (response_groups_user.statusCode() == 200) {
			Groups_User = response_groups_user.jsonPath().getList("value.listname");
		} else {
			Groups_User = Collections.emptyList();
		}

		System.out.println("No. of groups of user" + Groups_User.size());
		System.out.println("Groups of user" + Groups_User);
		homePage.tapShare();
		homePage.tapOnElementByText("Share in group chat");

		Thread.sleep(2000);
		SoftAssert softAssert = new SoftAssert();

		for (int i = 0; i < Groups_User.size(); i++) {
			softAssert.assertTrue(homePage.isGroupDisplayed(Groups_User.get(i)),
					Groups_User.get(i) + " group not displayed");

		}

	}

	@Test(enabled = false, testName = "Verify share in other apps")
	public void verifyShareInOtherApps_HomePageTest() throws Exception {
		homePage.tapShare();
		homePage.tapOnElementByText("Share in other apps");
		Thread.sleep(5000);
		Assert.assertTrue(homePage.isElementDisplayed("Messages"), "Messages app not displayed");
	}

	// Deeplink text will be shown in report
	@Test(enabled = true, priority = 10, testName = "Verify url of deeplink shared in other apps",description="Verify that user is able to able to see options for other apps")
	public void verifyURLDeepLinkShareInOtherApps_HomePageTest() throws Exception {
		homePage.tapShare();
		homePage.tapOnElementByText("Share in other apps");
		Thread.sleep(5000);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homePage.isElementDisplayed("Messages"), "Messages not displayed");
		// Copy to clipboard
		homePage.tapOnElementByText("Copy to clipboard");
		Thread.sleep(3000);
		String text = homePage.getTextfromClipboard();
		softAssert.assertAll();

	}

}
