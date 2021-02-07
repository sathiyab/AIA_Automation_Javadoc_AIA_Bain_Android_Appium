package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * MomentsControllerAPI class contains functions to make GET,PUT,POST,etc calls to moments-controller
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class MomentsControllerAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 	
	public MomentsControllerAPI()
	{
		System.out.println("Initializing Moments Controller API.");
		base = new BaseAPITest();
	}
	
	/**
	 * sends request to <b>list all moments</b> for a given profile. 
	 * 
	 * Uses moments-controller.
	 * End point - GET /moments/{profileId}.
	 * 
	 * 
	 * @param profileId ID of the user profile for whom the moments should be listed.  
	 * @return Response with all the moments log. 
	 */                    
	public Response GetMomentsProfileId(String profileId){
    	System.out.println("Getting the Moments Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("MOMENTSURL");
    	
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
                .get("/moments/{profileId}");
    	
    	System.out.println(response.asString());
    	return response;     	
    }	
	    	    
}