package com.pom.crimson.testcases.explore.high;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
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
import com.pom.crimson.pages.GroupsPage;
import com.pom.crimson.pages.HomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.restassured.response.Response;
/**
 * This test class contains test cases for Based on groups page.<br>
 * Based on Groups Page contains articles and videos as per user's joined groups. <br>
 * User can navigate to this page by  :<br><br>
 * 1. Go to Home Page <br> Click <b>Content</b> in bottom menu. <br>Click on <b>See all</b> link in 
 * <b>{@value com.pom.crimson.util.Constants#EXPLORE_BASED_ON_YOUR_GROUPS}</b> section.
 * <br><br>
 * 
 * @author Jaspreet Kaur Chagger
 */
public class BasedOnGroupsPageTest extends BaseFixture {

	HomePage homePage;
	ExplorePage explorePage;
	DiscoverTopicsPage discoverTopicsPage;
	FilteredInterestResultPage filteredInterestResultPage;
	ContactUsPage contactUsPage;
	ContentPage contentPage;
	GroupsPage groupsPage;
	GenericFunctionsAPI genericFunctionsAPI;
	GroupAdviceAPI groupAdviceAPI;
	LoginPage loginPage;
	BasedOnYourGroupsPage basedOnYourGroupsPage;

	@BeforeMethod()
	public void beforeLocalMethod() {
		 genericFunctionsAPI=new GenericFunctionsAPI();
		 groupAdviceAPI=new GroupAdviceAPI();
		 
		System.out.println("In Local before method");
		LoginPage loginPage = new LoginPage(getDriver(), getPlatformName());
		
		try {
			loginPage.loginAllPlatforms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		homePage = new HomePage(getDriver(), getPlatformName());
	}

	@Test(enabled = true, priority = 1, testName = "Create Test data-Check if user is part of a group", description = "Create Test data-Check if user is part of a group, if not user joins first available group")
	public void userJoinGroup_BasedOnGroupsPageTest() throws Exception {
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
			
			//log in
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
			testName = "BasedOnGroupsPage-Verify Want more group-related content? section on Based on Groups Page", description = "BasedOnGroupsPage-Verify Want more group-related content? section is displayed on Based on Groups Page")
	public void verifyWantMoreGroupRelatedSection_BasedOnGroupsPageTest() throws Exception {
		
		// Get Recommended content based on groups for user from backend
		List<String> ids=genericFunctionsAPI.getRecommendedContentTitlesBasedOnGroups();
		if (ids.size() == 0) {
			throw new SkipException("No content available for user based on groups. See all button not visible. Test case can't be executed");
		}
		
		// Go to Explore page
		explorePage = homePage.goToExplorePage();
		// Go to Based on Groups page by clicking on See all 
		 basedOnYourGroupsPage = explorePage.goToBasedOnYourGroupsPage();
		Thread.sleep(2000);
		// Scroll to section
		basedOnYourGroupsPage.scrollToSection(Constants.BASED_ON_GROUPS_WANT_MORE);
		LogAssert logAssert = new LogAssert();
		logAssert.assertTrue(basedOnYourGroupsPage.isElementDisplayed(Constants.BASED_ON_GROUPS_WANT_MORE),
				" Check if " + Constants.BASED_ON_GROUPS_WANT_MORE + " section is displayed");
		logAssert.assertTrue(basedOnYourGroupsPage.isElementDisplayed(Constants.BASED_ON_GROUPS_JOIN_MORE),
				" Check if " + Constants.BASED_ON_GROUPS_JOIN_MORE + " link is displayed");
		logAssert.assertAll();
	}

}
