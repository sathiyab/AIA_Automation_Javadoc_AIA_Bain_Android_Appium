package com.pom.crimson.testcases.explore.medium;

import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.functions.GenericFunctionsAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.AddMoreInterestPage;
import com.pom.crimson.pages.AllGroupsPage;
import com.pom.crimson.pages.ContactUsPage;
import com.pom.crimson.pages.DiscoverTopicsPage;
import com.pom.crimson.pages.ExplorePage;
import com.pom.crimson.pages.FilteredInterestResultPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.util.Constants;

import io.restassured.response.Response;

/**
 * This test class contains test cases for Explore Page or Content page (for Based on Groups section only).<br>
 * Explore Page or Content page displays articles and videos as per user's interests and joined groups. <br>
 * User can navigate to this page by :<br><br>
 * Go to Home Page <br> Click <b>Content</b> in bottom menu.
 * <br><br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class ExplorePageBasedOnGroupsSectionTest extends BaseFixture {

	HomePage homePage;
	ExplorePage explorePage;
	GroupAdviceAPI groupAdviceAPI;
	LoginPage loginPage;

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
	}

	@Test(enabled = true, priority = 1, 
			testName = "ExplorePageTestBasedOnGroupsSectionTest-Verify message displayed on explore Page to join groups and navigation to All Groups page ",
			description = "Verify following message is displayed when user is not part of any group-You aren't a part of any groups yet and "
					+ "Check if user is able to tap on Discover groups link ")
	public void verifyYouNotPartGroupsSection_ExplorePageTestBasedOnGroupsSectionTest() throws Exception {

		GenericFunctionsAPI genericFunctionsAPI = new GenericFunctionsAPI();
		LogAssert logAssert = new LogAssert();
		List<String> Groups_User;
		// Fetching groups that user is part of
		Groups_User = genericFunctionsAPI.getUserGroupIds();

		if (!(Groups_User.size() == 0)) {
			// remove user from groups
			for (int i = 0; i < Groups_User.size(); i++) {
				groupAdviceAPI.removeGroup(Groups_User.get(i));
			}	
		}
		// Reopen app for data refresh
		getDriver().launchApp();
		
		//log in
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}

		// Go to Explore/Content page
		explorePage = homePage.goToExplorePage();
		
		Thread.sleep(3000);

		logAssert.assertTrue(explorePage.verifyNotPartOfGroupsSection(),
				"Check if " + Constants.EXPLORE_NOT_PART_OF_GROUPS + " section is displayed");
		logAssert.assertTrue(explorePage.isElementDisplayed(Constants.EXPLORE_DISCOVER_GROUPS),
				"Check if " + Constants.EXPLORE_DISCOVER_GROUPS + " link is displayed");
		// Tap on Discover groups link
		explorePage.tapOnElementByText(Constants.EXPLORE_DISCOVER_GROUPS);
		AllGroupsPage AllGroupsPage = new AllGroupsPage(getDriver(), getPlatformName());

		logAssert.assertTrue(AllGroupsPage.isElementDisplayed(Constants.All_GROUPS_TITLE),
				"Check if " + Constants.All_GROUPS_TITLE + " title is displayed");

		logAssert.assertAll();


	}
	
	}	
	

	

