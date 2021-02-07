package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * MilestonesControllerAPI class contains functions to make GET,PUT,POST,etc calls to milestones-controller
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class MilestonesControllerAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 	
	public MilestonesControllerAPI()
	{
		System.out.println("Initializing Milestones Controller API.");
		base = new BaseAPITest();
	}
	
	/**
	 * sends request to <b>Get all milestones</b> for the requested type. 
	 * 
	 * Uses milestones-controller.
	 * End point - GET /milestones/.
	 * 
	 * 
	 * @param type Type Name of the milestone, blank for retrieving all milestones.  
	 * @return Response with the list of all milestones with ID, name and journey type. 
	 */                    
	public Response GetMilestones(String type){
    	System.out.println("Getting the Milestones Controller API URL.");
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
                queryParam("type", type).
        when()
                .get("/milestones/");
    	
    	System.out.println(response.asString());
    	return response;     	
    }	
	
	public Response GetVaccine(String type){
    	System.out.println("Getting the Milestones Controller API URL.");
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
                queryParam("type", type).
        when()
                .get("/milestones/");
    	
    	System.out.println(response.asString());
    	return response;     	
    }				    
}