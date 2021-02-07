package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * ContentActionControllerAPI class contains functions to make GET,PUT,POST,etc calls to content-action-controller
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class ContentActionControllerAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 
	public ContentActionControllerAPI()
	{
		System.out.println("Initializing Action Content Controller API.");
		base = new BaseAPITest();
	}
	
	
	/**
	 * sends request to <b>Record viewing of a piece of content</b>.
	 * 
	 * Uses content-action-controller API.
	 * End point - POST /contentView/{profileId}/{contentId}.
	 * 
	 * @param profileId Profile Id of the user.
	 * @param contentId Id of the content.  
	 * @return Response with the status of recording the content view. 
	 */    		
	public Response PostContentView(String profileId, String contentId){
    	System.out.println("Getting the Content Action Controller API URL.");
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
                pathParam("contentId", contentId).
        when()
                .post("/contentView/{profileId}/{contentId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to <b>bookmark a piece of content</b>.
	 * 
	 * Uses content-action-controller API.
	 * End point - POST /bookmark/{profileId}/{contentId}.
	 * 
	 * @param profileId Profile Id of the user.
	 * @param contentId Id of the content.  
	 * @return Response with the status of the bookmarking. 
	 */
	public Response PostBookmarkContent(String profileId, String contentId){
    	System.out.println("Getting the Content Action Controller API URL.");
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
                pathParam("contentId", contentId).
        when()
                .post("/bookmark/{profileId}/{contentId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends PUT request to <b>un-bookmark a piece of content</b>.
	 * 
	 * Uses content-action-controller API.
	 * End point - PUT /bookmark/{profileId}/{contentId}.
	 * 
	 * @param profileId Profile Id of the user.
	 * @param contentId Id of the content.  
	 * @return Response with the status of the un-bookmarking. 
	 */
	public Response PutBookmarkContent(String profileId, String contentId){
    	System.out.println("Getting the Content Action Controller API URL.");
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
                pathParam("contentId", contentId).
        when()
                .put("/bookmark/{profileId}/{contentId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }	
	
	/**
	 * sends request to <b>like a piece of content</b>.
	 * 
	 * Uses content-action-controller API.
	 * End point - POST /like/{profileId}/{contentId}.
	 * 
	 * @param profileId Profile Id of the user.
	 * @param contentId Id of the content.  
	 * @return Response with the status of the like done. 
	 */
	public Response PostLikeContent(String profileId, String contentId){
    	System.out.println("Getting the Content Action Controller API URL.");
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
                pathParam("contentId", contentId).
        when()
                .post("/like/{profileId}/{contentId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }

	/**
	 * sends request to <b>un-like a piece of content</b>.
	 * 
	 * Uses content-action-controller API.
	 * End point - PUT /like/{profileId}/{contentId}.
	 * 
	 * @param profileId Profile Id of the user.
	 * @param contentId Id of the content.  
	 * @return Response with the status of the un-like done. 
	 */	
	public Response PutLikeContent(String profileId, String contentId){
    	System.out.println("Getting the Content Action Controller API URL.");
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
                pathParam("contentId", contentId).
        when()
                .put("/like/{profileId}/{contentId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }	    			
	
	/**
	 * sends request to <b>get all bookmarked content</b> of a profile.
	 * 
	 * Uses content-action-controller API.
	 * End point - GET /bookmark/{profileId}.
	 * 
	 * @param profileId Profile Id of the user.  
	 * @return Response with the list of bookmarked contents and its details. 
	 */	
	public Response GetBookmarkProfileId(String profileId){
    	System.out.println("Getting the Content Action Controller API URL.");
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
                .get("/bookmark/{profileId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }	
	
	/**
	 * sends request to <b>get all viewed content history</b> of a profile.
	 * 
	 * Uses content-action-controller API.
	 * End point - GET /contentHistory/{profileId}.
	 * 
	 * @param profileId Profile Id of the user.  
	 * @return Response with the list of contents viewed and its details. 
	 */	
	public Response GetContentHistory(String profileId){
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
                .get("/contentHistory/{profileId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * sends request to <b>get all liked contents</b> of a profile.
	 * 
	 * Uses content-action-controller API.
	 * End point - GET /like/{profileId}.
	 * 
	 * @param profileId Profile Id of the user.  
	 * @return Response with the list of contents liked and its details. 
	 */	
	public Response GetLikeProfileId(String profileId){
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
                .get("/like/{profileId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }

	/**
	 * sends request to <b>Record sharing of a piece of content</b>.
	 * 
	 * Uses content-action-controller API.
	 * End point - POST /share/{profileId}/{contentId}.
	 * 
	 * @param profileId Profile Id of the user.
	 * @param contentId Id of the content.	 *   
	 * @return Response with the status of sharing done. 
	 */		
	public Response PostContentSharing(String profileId, String contentId){
    	System.out.println("Getting the Content Action Controller API URL.");
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
                pathParam("contentId", contentId).
        when()
                .post("/share/{profileId}/{contentId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }	
		    	    
}