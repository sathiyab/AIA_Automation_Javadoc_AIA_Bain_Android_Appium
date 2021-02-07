package com.pom.crimson.testcases.api;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.crimson.api.*;
import com.pom.crimson.base.BaseFixtureForAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

import io.restassured.response.Response;
import com.aventstack.extentreports.Status;

/**
 * This class contains test methods to test Group Advice APIs
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class GroupAdviceAPITest extends BaseFixtureForAPI {

	GroupAdviceAPI GP;
	LogAssert logAssert;

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClassLocal() {
		GP = new GroupAdviceAPI();
	}
	
	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}	

	@Test(priority = 1, enabled=true, testName = "Group Controller - Root Health Check", description = "Group Controller - Root Health Check")
	public void verifyRootAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "Root Health Check");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /");

		Response resp = GP.getWelcomeMessage();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		logAssert.assertTrue(resp.asString().contains("Welcome"), "Welcome message is displayed successfully.");
		logAssert.assertAll();
	}

	@Test(priority = 2, enabled=true, testName = "Group Controller - Get all Groups", description = "Group Controller - Get all Groups")
	public void verifyGetAllGroupsAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "Group Controller - Get all Groups");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/group");

		Response resp = GP.getAllGroups("COMMUNITY");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> GroupList = resp.jsonPath().getList("value.listname");
		System.out.println(GroupList.toString());
		logAssert.assertTrue(GroupList.size() > 0, "Group list retrieved is not empty.");
		logAssert.assertAll();
	}

	@Test(priority = 3, enabled=true, testName = "Group Controller - Get Group by GroupID", description = "Group Controller - Get Group by GroupID")
	public void verifyGetGroupByGroupIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Group Controller - Get Group by GroupID");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/group/{groupId}");

		List<String> GroupList = GP.getAllGroups("COMMUNITY").jsonPath().getList("value.listid");
		System.out.println(GroupList.get(0));
		Response resp = GP.getGroupByGroupID(GroupList.get(0));
		List<String> Group = resp.jsonPath().getList("value.listname");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		logAssert.assertTrue(Group.size() > 0, "Group details retrieved from response is not empty");
		logAssert.assertAll();
	}

	@Test(priority = 4, enabled=true, testName = "Group Controller - Get Group Members by GroupID", description = "Group Controller - Get Group Members by GroupID")
	public void verifyGetGroupMemInfoByGroupIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Group Controller - Get Group Members by GroupID");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/group/{groupId}/members");

		List<String> GroupList = GP.getAllGroups("COMMUNITY").jsonPath().getList("value.listid");
		System.out.println(GroupList.get(0));
		Response resp = GP.GetGroupMemInfoByGroupID(GroupList.get(0));
		List<String> memberName = resp.jsonPath().getList("value.fullname");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		logAssert.assertTrue(memberName.size() > 0, "Group details retrieved from response is not empty");
		logAssert.assertAll();
	}

	@Test(priority = 5, enabled=true, testName = "Group Controller - Get all Available Groups", description = "Group Controller - Get all Available Groups")
	public void verifyGetAllAvailableGroupsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Group Controller - Get all Available Groups");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/group/availgroups");

		Response resp = GP.getAllAvailableGroups();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> GroupList = resp.jsonPath().getList("listname");
		System.out.println(GroupList.toString());
		logAssert.assertTrue(GroupList.size() > 0, "Available Groups retrieved is not empty.");
		logAssert.assertAll();
	}

	@Test(priority = 7, enabled=false, testName = "Group Controller - Adds User to the Group V2", description = "Group Controller - Adds User to the Group V2")
	public void verifyJoinGroupV2API() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Group Controller - Adds User to the Group V2");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/group/join/v2");

		String groupID, groupType, topicID;
		Response GroupList = GP.getAllGroups("COMMUNITY");
		groupID = GroupList.jsonPath().getList("value.listid").get(0).toString();
		groupType = GroupList.jsonPath().getList("value.aiabase_typeofgroup").get(0).toString();
		topicID = GroupList.jsonPath().getList("value.aiabase_Topic.aiabase_groupmasterid").get(0).toString();

		Response resp = GP.joinGroupV2(groupID, groupType, topicID);
		logAssert.assertEquals(resp.statusCode(), 200, "To retrieve the group for the user to join.");

		Response Group = GP.getGroupByUserID("COMMUNITY");
		System.out.println(Group.jsonPath().getList("value.listid"));
		List<String> myGroups = Group.jsonPath().getList("value.listid");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the user has joined the group successfully.");
		logAssert.assertTrue(myGroups.contains(groupID));
		
		if (myGroups.size()>0) {
			Response remGroup = GP.removeGroup(groupID);
			
			String respRmGroup = remGroup.jsonPath().getString("dynamicChannel");
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the user has been removed from the group successfully.");
			logAssert.assertNull(respRmGroup, "Verify if the response received is not empty.");					
		}		
		logAssert.assertAll();
	}

	@Test(priority = 8, enabled=true, testName = "Group Controller - Get Group by UserID", description = "Group Controller - Get Group by UserID")
	public void verifyGetGroupByUserIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Group Controller - Get Group by UserID");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/group/user");

		Response resp = GP.getGroupByUserID("COMMUNITY");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> GroupList = resp.jsonPath().getList("value.listname");
		System.out.println(GroupList.toString());
		if (GroupList.size() > 0) {
			logAssert.assertTrue(GroupList.size() > 0, "Group details retrieved is not empty.");
		}
		else {
			ExtentReportManager.getTest().log(Status.INFO, "User is not part of any group.");
		}
		logAssert.assertAll();
	}

	@Test(priority = 9, enabled=true, testName = "Group Controller - Create Invite & Retrieve it by Invite Code", description = "Group Controller - Create Invite & Retrieve it by Invite Code")
	public void verifyGroupInviteAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO,
				"Group Invitation Controller - Create Invite & Retrieve it by Invite Code");
		ExtentReportManager.getTest().log(Status.INFO,
				"API Endpoints - /api/v1/groupadvice/invite, " + "​/api​/v1​/groupadvice​/invite​/{inviteCode}");

		List<String> GroupList = GP.getAllGroups("COMMUNITY").jsonPath().getList("value.listid");
		System.out.println(GroupList.get(0));
		Response respGroupCreate = GP.createGroupInvite(GroupList.get(0));
		logAssert.assertEquals(respGroupCreate.statusCode(), 200, "Verify if the invite code is created sucessfully.");
		
		System.out.println(respGroupCreate.toString());
		String Invite = respGroupCreate.jsonPath().getString("inviteCode");		
		List<String> Group = GP.getGroupByInviteCode(Invite).jsonPath().getList("value.listname");
		System.out.println(Group.toString());
		logAssert.assertTrue(Group.size() > 0, "Verify if the user is able to join the group using the Invite Code.");	
		logAssert.assertAll();
	}

	@Test(priority = 10, enabled=true, testName = "Group Event Controller - Get All Live Events", description = "Group Event Controller - Get All Live Events")
	public void verifyGetGroupByLiveEventsAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "Group Event Controller - Get All Live Events");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/events");

		Response resp = GP.GetGroupByLiveEvents();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> GroupList = resp.jsonPath().getList("value.listname");
		if (GroupList.size() > 0) {
			logAssert.assertTrue(GroupList.size() > 0, "Events list retrieved is not empty.");
		}		
		else {
			ExtentReportManager.getTest().fail("There are no Live Events available.");
		}
		logAssert.assertAll();
	}

	@Test(priority = 11, enabled=true, testName = "Topic Controller - Get Event by TopicID", description = "Topic Controller - Get Event by TopicID")
	public void verifyGetEventsByTopicIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Topic Controller - Get Event by TopicID");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/events/{topicId}");

		List<String> TopicList = GP.GetGroupByLiveEvents().jsonPath().getList("value._aiabase_topic_value");
		if (TopicList.size() > 0) {
			Response resp = GP.getEventByTopicID(TopicList.get(0));
			System.out.println(TopicList.get(0));
			List<String> Topic = resp.jsonPath().getList("value.listname");		
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			logAssert.assertTrue(Topic.size() > 0, "Verify if the Event list for the Topic Id is not empty");			
		}
		else {
			ExtentReportManager.getTest().fail("There are no Live Events available.");
		}
		logAssert.assertAll();
	}

	@Test(priority = 12, enabled=true, testName = "Group Event Controller - Get Past Event by user", description = "Group Event Controller - Get Past Event by user")
	public void verifyGetPastEventsByUserAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Group Event Controller - Get Event by user");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/events/user");

		Response resp = GP.GetEventsByUser("PAST");
		List<String> Events = resp.jsonPath().getList("value.listname");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		if (Events.size() > 0) {
			logAssert.assertTrue(Events.size() > 0, "PAST events for the user are retrieved successfully.");
		}
		else {
			ExtentReportManager.getTest().info("No past events available for the user");
		}		
		logAssert.assertAll();
	}
	
	@Test(priority = 13, enabled=true, testName = "Group Event Controller - Get Upcoming Event by user", description = "Group Event Controller - Get Upcoming Event by user")
	public void verifyGetUpcomingEventsByUserAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Group Event Controller - Get Event by user");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/events/user");

		Response resp = GP.GetEventsByUser("UPCOMING");
		List<String> Events = resp.jsonPath().getList("value.listname");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		if (Events.size() > 0) {
			logAssert.assertTrue(Events.size() > 0, "Upcoming events for the user are retrieved successfully.");			
		}
		else {
			ExtentReportManager.getTest().info("No Upcoming events available for the user");
		}		

		logAssert.assertAll();
	}	

	@Test(priority = 14, enabled=true, testName = "Topic Controller - Get all Topics", description = "Topic Controller - Get all Topics")
	public void verifyGetAllTopicsAPI() throws Exception {
		ExtentReportManager.getTest().log(Status.INFO, "Topic Controller - Get all Topics API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/topic");

		Response resp = GP.getAllTopics("LIVE_EVENT");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> TopicList = resp.jsonPath().getList("value.aiabase_groupmasterid");
		if (TopicList.size() > 0) {
			logAssert.assertTrue(TopicList.size() > 0, "Verify Topic list retrieved is not blank");
		}		
		else {
			ExtentReportManager.getTest().fail("There are no Live Events available.");
		}
		logAssert.assertAll();
	}

	@Test(priority = 15, enabled=true, testName = "Topic Controller - Get Topic by TopicID", description = "Topic Controller - Get Topic by TopicID")
	public void verifyGetTopicByTopicIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Topic Controller - Get Topic by TopicID");
		ExtentReportManager.getTest().log(Status.INFO, "/api/v1/groupadvice/topic/{topicId}");

		List<String> TopicList = GP.getAllTopics("LIVE_EVENT").jsonPath().getList("value.aiabase_groupmasterid");
		
		if (TopicList.size()>0) {
			Response resp = GP.getEventByTopicID(TopicList.get(0));
			List<String> Topic = resp.jsonPath().getList("value.aiabase_name");
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			logAssert.assertTrue(Topic.size() > 0, "Verify if the Topic details retrieved are not empty.");			
		}
		else {
			ExtentReportManager.getTest().info("There are no Live Events available.");
		}

		logAssert.assertAll();
	}

	@Test(priority = 16, enabled=true, testName = "Topic Controller - Get the InterestIds for the requested topicId", description = "Topic Controller - Get the InterestIds for the requested topicId")
	public void verifyGetInterestsByTopicIDAPI() throws Exception {

		
		ExtentReportManager.getTest().log(Status.INFO,
				"Topic Controller - Get the InterestIds for the requested topicId");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/topic/{topicId}/interests");

		List<String> TopicList = GP.getAllTopics("LIVE_EVENT").jsonPath().getList("value.aiabase_groupmasterid");
		//System.out.println(TopicList.toString());
		if (TopicList.size()>0) {
			Response resp = GP.getInterestsByTopicID(TopicList.get(1));
			List<String> Interest = resp.jsonPath().getList("value._aiabase_interestid_value");
			
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			logAssert.assertTrue(Interest.size() > 0, "Interests retrieved for the topic is not empty.");			
		}
		else {
			ExtentReportManager.getTest().fail("There are no Live Events available.");
		}
		
		logAssert.assertAll();
	}

	@Test(priority = 17, enabled=true, testName = "Topic Controller - Save Recommended Topic", description = "Topic Controller - Save Recommended Topic")
	public void verifyPostRecomTopicByTopicIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Topic Controller - Save Recommended Topic");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/recommendation/{topicId}");

		List<String> TopicList = GP.getAllTopics("LIVE_EVENT").jsonPath().getList("value.aiabase_groupmasterid");
		if (TopicList.size()>0) {
			logAssert.assertTrue(TopicList.size()>0, "Retrieving Topic from Topic Controller - Get all Topics Endpoint");
			System.out.println(TopicList.get(0));
			Response resp = GP.postRecomTopicByTopicID(TopicList.get(0));
			String Topic = resp.jsonPath().getString("topicId[0]");
			
			logAssert.assertNotNull(Topic, "Recommended topic has been saved successfully for the user.");			
		}
		else {
			ExtentReportManager.getTest().fail("There are no Live Events available.");
		}
		logAssert.assertAll();
	}

	@Test(priority = 18, enabled=true, testName = "Topic Controller - Get Recommended Groups for the userId", description = "Topic Controller - Get Recommended Groups for the userId")
	public void verifyGetRecommendedGroupsForUserAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Topic Controller - Get Recommended Groups for the userId");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/recommendation/groups");

		Response resp = GP.getRecomTopicForUser();
		List<String> GroupList = resp.jsonPath().getList("value.listid");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		if (GroupList.isEmpty()) {
			logAssert.assertTrue(GroupList.size() > 0, "Recommended topic list for the user has been retrieved successfully.");
		} else {
			ExtentReportManager.getTest().log(Status.INFO, "There are no recommended groups available for the user.");			
		}		
		logAssert.assertAll();
	}

	@Test(priority = 19, enabled=true, testName = "Topic Recommendation Controller - Get Recommended Live Events", description = "Topic Recommendation Controller - Get Recommended Live Events")
	public void verifygetRecommendedEventsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Topic Recommendation Controller - Get Recommended Live Events");
		ExtentReportManager.getTest().log(Status.INFO, "/api/v1/groupadvice/recommendation/recommendedEvents");

		Response resp = GP.getRecommendedEvents();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> TopicList = resp.jsonPath().getList("value.listid");
		System.out.println(TopicList.size());
		if (TopicList.size()>0) {
			logAssert.assertTrue(TopicList.size() > 0, "Recommended live events have been retrieved successfully.");
		}
		else {
			ExtentReportManager.getTest().log(Status.INFO, "There are no recommended live events available for the user.");
		}
		logAssert.assertAll();
	}

	@Test(priority = 20, enabled=false, testName = "Topic Recommendation - Get Contacts Related to the userId", description = "Topic Recommendation - Get Contacts Related to the userId")
	public void verifygetRelationByUserIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Topic Recommendation - Get Contacts Related to the userId");
		ExtentReportManager.getTest().log(Status.INFO,
				"API Endpoint - /api/v1/groupadvice/recommendation/relation/{userId}");

		Response resp = GP.getRelationByUserID();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		logAssert.assertAll();
	}

	@Test(priority = 21, enabled=true, testName = "Topic Recommendation Controller - Get the Similar Events List for the requested topicId", description = "Topic Recommendation Controller - Get the Similar Events List for the requested topicId")
	public void verifyGetSimilarEventsByTopicIDAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO,
				"Topic Recommendation Controller - Get the Similar Events List for the requested topicId");
		ExtentReportManager.getTest().log(Status.INFO,
				"API Endpoint - /api/v1/groupadvice/recommendation/similarEvents/{topicId}");

		List<String> TopicList = GP.getAllTopics("LIVE_EVENT").jsonPath().getList("value.aiabase_groupmasterid");
		if (TopicList.size()>0) {
			Response resp = GP.getSimilarEventsByTopicID(TopicList.get(0));
			List<String> Topic = resp.jsonPath().getList("value.listname");
			logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
			if (Topic.size() > 0) {
				logAssert.assertTrue(Topic.size() > 0);
			}
			else
			{
				ExtentReportManager.getTest().log(Status.INFO, "There are no Similar events available for the requested topic.");
			}			
		}
		else {
			ExtentReportManager.getTest().fail("There are no Live Events available.");
		}
		logAssert.assertAll();
	}

	@Test(priority = 22, enabled=true, testName = "Topic Controller - Get all Active Recommended Topics", description = "Topic Controller - Get all Active Recommended Topics")
	public void verifygetAllActiveRecomTopicByUserAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Topic Controller - Get all Active Recommended Topics");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /api/v1/groupadvice/recommendation/user");

		Response resp = GP.getAllActiveRecomTopicByUser();
		// List<String> Topic = resp.jsonPath().getList("value.listname");
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		// logAssert.assertTrue(Topic.size() > 0);
		logAssert.assertAll();
	}

}
