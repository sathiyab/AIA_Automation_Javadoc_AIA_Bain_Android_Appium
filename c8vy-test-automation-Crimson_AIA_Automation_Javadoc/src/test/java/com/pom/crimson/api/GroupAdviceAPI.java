package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * GroupAdviceAPI class contains functions to make GET,PUT,POST,etc calls to Group Advice
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Sivaprakash.Selvaraj
 */
public class GroupAdviceAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 	
	public GroupAdviceAPI()
	{
		try {
			System.out.println("Initializing GroupAdviceAPI.");
			base = new BaseAPITest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * sends request to check if the <b>Group advice service</b> is up and running.
	 * 
	 * Uses content-action-controller API.
	 * End point - GET /.
	 *
	 * 	 
	 * @return Response with a welcome message. 
	 */		
    public Response getWelcomeMessage(){
    	System.out.println("Getting the Group Advice api URL.");
    	
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                ////.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
        when()
                .get("/");
    	
    	//System.out.println(response.asString());
    	return response;
    }	
	    
	/**
	 * sends request to <b>get all topics </b> related to Groups and Live events.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/topic.
	 * 
	 * @param topicType applicable values are either COMMUNITY OR LIVE_EVENT.  
	 * @return Response with the list of topics and its details. 
	 */
    public Response getAllTopics(String topicType){
    	System.out.println("Getting the Group Advice api URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                queryParam("topicType", topicType).
        when()
                .get("/api/v1/groupadvice/topic");
    	
    	//System.out.println(response.asString());
    	return response;
    }

	/**
	 * sends request to <b>get all groups</b> defined in dynamics365 CRM.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/group.
	 * 
	 * @param groupType applicable values are either COMMUNITY OR LIVE_EVENT.  
	 * @return Response with the list of groups and its details. 
	 */    
    public Response getAllGroups(String groupType)
    {
    	System.out.println("/api/v1/groupadvice/group");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                queryParam("groupType", groupType).
        when()
                .get("/api/v1/groupadvice/group");
    	
    	System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to <b>get all available groups</b> for the user.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/group/availgroups.
	 * 
	 *   
	 * @return Response with the list of available groups and its details. 
	 */        
    public Response getAllAvailableGroups()
    {
    	System.out.println("/api/v1/groupadvice/group/availgroups");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
        when()
                .get("/api/v1/groupadvice/group/availgroups");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }    
    
	/**
	 * sends request to <b>get all available groups</b> for the user.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/group/availgroups.
	 * 
	 * 
	 * @param groupType available values are COMMUNITY and LIVE EVENTS.  
	 * @return Response with the list of available groups and its details. 
	 */            
    public Response getGroupByUserID(String groupType)
    {
    	System.out.println("/api/v1/groupadvice/group/user/{userId}");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                queryParam("groupType", groupType).
                //pathParam("userId", base.getUserID()).
        when()
                .get("/api/v1/groupadvice/group/user");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to get the details of a individual group.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/group/{groupId}.
	 * 
	 * 
	 * @param groupID ID of the requested group  
	 * @return Response with the details of the group. 
	 */            
    public Response getGroupByGroupID(String groupID)
    {
    	System.out.println("/api/v1/groupadvice/group/{groupId}");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("groupId", groupID).
        when()
                .get("/api/v1/groupadvice/group/{groupId}");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
    public Response joinGroup(String groupID, String groupType, String topicID)
    {
    	System.out.println("/api/v1/groupadvice/group/join");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body("{\"Recommendation_flag\":true,\"group_id\":\""+groupID+"\",\"group_type\":\""+groupType+"\",\"okta_id\":\""+base.getUserID()+"\",\"topic_id\":\""+topicID+"\"}")
                .when()
                .post("/api/v1/groupadvice/group/join");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
	
	/**
	 * sends request to join a COMMUNITY group or LIVE_EVENT.
	 * 
	 * Uses Group Advice API.
	 * End point - POST /api/v1/groupadvice/group/join/v2.
	 * 
	 *
	 * @param groupID list ID of the group/Live Event to which the user has to join.
	 * @param groupType applicable values are COMMUNITY or LIVE EVENT
	 * @param topicID topic ID of the group/Live Event  
	 * @return Response with the details of the group. 
	 */                
    public Response joinGroupV2(String groupID, String groupType, String topicID)
    {
    	System.out.println("/api/v1/groupadvice/group/join/v2");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body("{\"Recommendation_flag\":true,\"group_id\":\""+groupID+"\",\"group_type\":\""+groupType+"\",\"okta_id\":\""+base.getUserID()+"\",\"topic_id\":\""+topicID+"\"}")
                .when()
                .post("/api/v1/groupadvice/group/join/v2");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }    
    
	/**
	 * sends request to get the <b>member</b> info of a group.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/group/{groupId}/members.
	 * 
	 *
	 * @param groupID list ID of the group/Live Event for which the member details to be retrieved.
	 * @return Response with the details of the group. 
	 */
    public Response GetGroupMemInfoByGroupID(String groupID)
    {
    	System.out.println("/api/v1/groupadvice/group/{groupId}/members");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("groupId", groupID).
        when()
                .get("/api/v1/groupadvice/group/{groupId}/members");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }    
    
	/**
	 * sends request to get all groups by <b>Live Events</b>.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/events.
	 * 
	 *
	 * @return Response with the list of all Live Events and its details. 
	 */
    public Response GetGroupByLiveEvents()
    {
    	System.out.println("/api/v1/groupadvice/events");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
        when()
                .get("/api/v1/groupadvice/events");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }    
	
	/**
	 * sends request to get <b>all past or upcoming Live Events</b> of an user.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/events/user.
	 * 
	 *
	 * @param type applicable values are PAST and UPCOMING
	 * @return Response with the list of all past or upcoming Live Events based on the type sent. 
	 */
    public Response GetEventsByUser(String type)
    {
    	System.out.println("/api/v1/groupadvice/events/user");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                queryParam("type", type).              
                queryParam("grouptype", "LIVE_EVENT").
        when()
                .get("/api/v1/groupadvice/events/user");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to get <b>all Events</b> associated with the requested topic Id.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/events/{topicId}.
	 * 
	 *
	 * @param topicID Topic ID.
	 * @return Response with the list of all Live Events associated with a Topic. 
	 */    
    public Response getEventByTopicID(String topicID)
    {
    	System.out.println("/api/v1/groupadvice/events/{topicId}");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("topicId", topicID).
        when()
                .get("/api/v1/groupadvice/events/{topicId}");
    	
    	System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to get <b>recommended events</b> for an user.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/recommendation/recommendedEvents.
	 * 
	 *
	 * @return Response with the list of all Recommended Live Events for an user and its details. 
	 */    
    public Response getRecomEventsByUser()
    {
    	System.out.println("/api/v1/groupadvice/recommendation/recommendedEvents");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
        when()
                .get("/api/v1/groupadvice/recommendation/recommendedEvents");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }    
    
	/**
	 * sends request to get <b>details of a topic</b> for the requested topic Id.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/topic/{topicId}.
	 * 
	 *
	 * @param topicID Topic ID
	 * @return Response with the details of the topic. 
	 */    
    public Response getTopicByTopicID(String topicID)
    {
    	System.out.println("/api/v1/groupadvice/topic/{topicId}");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("topicId", topicID).
        when()
                .get("/api/v1/groupadvice/topic/{topicId}");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to Get the InterestIds for the requested topicId.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/topic/{topicId}.
	 * 
	 *
	 * @param topicID Topic ID
	 * @return Response with the interest details of the topic Id. 
	 */    
    public Response getInterestsByTopicID(String topicID)
    {
    	System.out.println("/api/v1/groupadvice/topic/{topicId}/interests");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("topicId", topicID).
        when()
                .get("/api/v1/groupadvice/topic/{topicId}/interests");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to Get <b>all active recommended topics</b> for an user.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/recommendation/user.
	 * 
	 *
	 * @return Response with the list of recommended topics for the user. 
	 */        
    public Response getAllActiveRecomTopicByUser()
    {
    	System.out.println("/api/v1/groupadvice/recommendation/user");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                queryParam("userId", base.getUserID()).
                queryParam("isRecommended","true").
        when()
                .get("/api/v1/groupadvice/recommendation/user");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to <b>Get Recommended Groups</b> for the userId.
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/recommendation/groups.
	 * 
	 *
	 * @return Response with the list of recommended groups for the user. 
	 */        
    public Response getRecomTopicForUser()
    {
    	System.out.println("/api/v1/groupadvice/recommendation/groups");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
        when()
                .get("/api/v1/groupadvice/recommendation/groups");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }    
    
	/**
	 * sends request to <b>Save Recommended Topic</b> for the requested topicId.
	 * 
	 * Uses Group Advice API.
	 * End point - POST /api/v1/groupadvice/recommendation/{topicId}.
	 * 
	 *
	 * @param topicID ID of the requested Topic
	 * @return Response with the list of recommended topics for the user. 
	 */        
    public Response postRecomTopicByTopicID(String topicID)
    {
    	System.out.println("/api/v1/groupadvice/recommendation/{topicId}");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("topicId", topicID).
        when()
                .post("/api/v1/groupadvice/recommendation/{topicId}");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to <b>Create Group Invitation</b>. 
	 * the invitation can be shared with others via any messenger tool to join the group
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/invite.
	 * 
	 * 
	 * @param groupID Id of the requested group
	 * @return Response with the Invite code, created date, expiry date and other details of the group. 
	 */            
    public Response createGroupInvite(String groupID)
    {
    	System.out.println("/api/v1/groupadvice/invite");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body("{\"group_id\":\""+groupID+"\",\"user_id\":\""+base.getUserID()+"\"}")
                .when()
                .post("/api/v1/groupadvice/invite");
    	
    	System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to <b>Get Group Information</b> based on the Invite Code. 
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/invite/{inviteCode}.
	 * 
	 * 
	 * @param inviteCode Invitation Code
	 * @return Response with the group details of the corresponding invite code. 
	 */            
    public Response getGroupByInviteCode(String inviteCode)
    {
    	System.out.println("/api/v1/groupadvice/invite/{inviteCode}");    	
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("inviteCode", inviteCode).
        when()
                .get("/api/v1/groupadvice/invite/{inviteCode}");
    	
    	System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to <b>Get the Recommended Live Events List </b> for the requested okatId. 
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/recommendation/recommendedEvents.
	 * 
	 * 
	 * @return Response with the list of Live Event and its details recommended for the user. 
	 */            
    public Response getRecommendedEvents(){
    	System.out.println("Getting the Group Advice api URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
        when()
                .get("/api/v1/groupadvice/recommendation/recommendedEvents");
    	
    	//System.out.println(response.asString());
    	return response;
    }
    
	/**
	 * sends request to <b>Remove User from the Group</b> based on roomId and userId. 
	 * 
	 * Uses Group Advice API.
	 * End point - POST /api/v1/groupadvice/group/remove.
	 * 
	 * 
	 * @param groupID ID of the requested group from which the user has to be removed
	 * @return Response with status of the removal request sent. 
	 */            
    public Response removeGroup(String groupID)
    {
    	System.out.println("/api/v1/groupadvice/group/remove");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body("{\"roomId\":\""+groupID+"\",\"userId\":\""+base.getUserID()+"\"}")
                .when()
                .post("/api/v1/groupadvice/group/remove");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to <b>Get the Similar Live Events List</b> for the requested topicId. 
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/recommendation/similarEvents/{topicId}.
	 * 
	 * 
	 * @param topicID ID of the requested topic
	 * @return Response with list of similar events related to the requested topic. 
	 */                
    public Response getSimilarEventsByTopicID(String topicID)
    {
    	System.out.println("/api/v1/groupadvice/recommendation/similarEvents/{topicId}");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("topicId", topicID).
        when()
                .get("/api/v1/groupadvice/recommendation/similarEvents/{topicId}");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }
    
	/**
	 * sends request to <b>Get Contacts Related to the userId</b>. 
	 * 
	 * Uses Group Advice API.
	 * End point - GET /api/v1/groupadvice/recommendation/relation/{userId}.
	 * 
	 * 
	 * @return Response with list of topic recommendation for the related user. 
	 */                
    public Response getRelationByUserID()
    {
    	System.out.println("/api/v1/groupadvice/recommendation/relation/{userId}");
    	RestAssured.baseURI = base.getProperties().getProperty("GroupAdviceAPIURL");
    	
    	Response response = RestAssured.given()
                //.log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("userId", base.getUserID()).
        when()
                .get("/api/v1/groupadvice/recommendation/relation/{userId}");
    	
    	//System.out.println(response.asString());
    	return response;    	
    }        
}
