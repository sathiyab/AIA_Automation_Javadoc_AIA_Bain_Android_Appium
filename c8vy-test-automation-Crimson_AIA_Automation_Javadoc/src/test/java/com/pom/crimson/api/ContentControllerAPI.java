package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * ContentControllerAPI class contains functions to make GET,PUT,POST,etc calls to content-controller
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class ContentControllerAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 
	public ContentControllerAPI()
	{
		System.out.println("Initializing ContentControllerAPI.");
		base = new BaseAPITest();
	}
	
		public Response GetContent(){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON).                
        when()
                .get("/content");
    	
    	System.out.println(response.asString());
    	return response;     	
    }	

	
	/**
	 * sends request to <b>Gets a specific piece of content</b> based on its Strapi ID.
	 * 
	 * Uses content-controller API.
	 * End point - GET /content/{contentId}.
	 * 
	 * @param contentId Id of the content.  
	 * @return Response with the details of the content. 
	 */    		    
	public Response GetContentId(String contentId){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON). 
                pathParam("contentId", contentId).
        when()
                .get("/content/{contentId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to <b>Gets a daily tip for the given profile</b>, if one exists.
	 * 
	 * Uses content-controller API.
	 * End point - GET /content/{contentId}.
	 * 
	 * @param profileId Profile Id of the user.  
	 * @return Response with the details of the daily tip. 
	 */
	public Response GetContentDailyTipProfileId(String profileId){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON). 
                pathParam("profileId", profileId).
        when()
                .get("/content/daily-tip/{profileId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to <b>Fetch recommended content</b> based on a user's joined groups.
	 * 
	 * Uses content-controller API.
	 * End point - GET /content/group/{profileId}.
	 * 
	 * @param profileId Profile Id of the user.  
	 * @return Response with the details of the recommended content of the profile. 
	 */
	public Response GetContentGroupProfileId(String profileId){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON). 
                pathParam("profileId", profileId).
        when()
                .get("/content/group/{profileId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to Fetch recommended content for the <b>homepage</b>.
	 * 
	 * Uses content-controller API.
	 * End point - GET /content/homepage.
	 * 
	 * @param profileId Profile Id of the user.  
	 * @return Response with the details of the recommended content to be displayed in the homepage of the profile. 
	 */
	public Response GetContentHomePage(String profileId){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON).  
                queryParam("profileId", profileId).
        when()
                .get("/content/homepage");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to <b>Gets a list of content</b> based on given Strapi IDs.
	 * 
	 * Uses content-controller API.
	 * End point - GET /content/ids.
	 * 
	 * @param contentId Id of the content.  
	 * @return Response with the details of the contents fetched. 
	 */
	public Response GetContentIds(String contentId){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON).
                queryParam("contentIds", contentId).
        when()
                .get("/content/ids");
    	
    	System.out.println(response.asString());
    	return response;     	
    }

	public Response GetContentInterest(String profileId){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON). 
                pathParam("interest", profileId).
        when()
                .get("/content/interest/{interestName}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to Fetch recommended content based on a <b>user's interests</b>.
	 * 
	 * Uses content-controller API.
	 * End point - GET /content/profile/{profileId}.
	 * 
	 * @param profileId Profile Id of the user.  
	 * @return Response with the details of the recommended content based on user's interest. 
	 */	
	public Response GetContentProfileId(String profileId){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON). 
                pathParam("profileId", profileId).
        when()
                .get("/content/profile/{profileId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to Fetch recommended content based on a <b>specific interest</b>.
	 * 
	 * Uses content-controller API.
	 * End point - GET /content/interest/{interestName}.
	 * 
	 * @param profileId profile Id of the user.  
	 * @param interestName Name of interest.
	 * @return Response with the details of the contents fetched based on the interest. 
	 */	
	public Response GetContentInterestName(String profileId, String interestName){
    	System.out.println("Getting the Content Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                         ContentType.JSON,
                        "Accept",
                         ContentType.JSON). 
                pathParam("interestName", interestName).
                queryParam("profileId", profileId).
        when()
                .get("/content/interest/{interestName}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	    	    
}