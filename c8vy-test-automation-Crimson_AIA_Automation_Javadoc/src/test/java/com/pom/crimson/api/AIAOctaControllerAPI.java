package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * AIAOctaControllerAPI class contains functions to make GET,PUT,POST,etc calls to aia-okta-controller
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class AIAOctaControllerAPI {
	
	BaseAPITest base;

	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 		
	public AIAOctaControllerAPI()
	{
		System.out.println("Initializing AIAOctaControllerAPIControllerAPI.");
		base = new BaseAPITest();
	}	
	
	/**
	 * Sends logout request to OKTA to <b>Logout</b> an user.
	 * 
	 * Uses aia-okta-controller API.
	 * End point - GET /logout
	 * 
	 * @return Response with type, code and description 
	 */	
	public Response GetLogout(){
    	System.out.println("Getting the AIA Octa Controller API URL.");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
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
                .get("/logout");
    	
    	System.out.println(response.asString());
    	return response;     	
    } 

	/**
	 * Finds if <b>user exists in OKTA</b> using email and returns success if exists.
	 * 
	 * Uses aia-okta-controller API.
	 * End point - GET /users
	 * 
	 * @param email Email id of the user 
	 * @return Response with the following properties of the user - isPasswordSet, isSocialLogin, isUserCreated 
	 */    
	public Response GetUsers(String email){
	       System.out.println("Getting the AIA Octa Controller API URL.");
	       RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
	       
	       Response response = RestAssured.given()
	                .log().all()
	                .headers(
	                        "Authorization",
	                        "Bearer " + base.getAccessToken(),
	                        "Content-Type",
	                        ContentType.JSON,
	                        "Accept",
	                        ContentType.JSON).      
	                queryParam("email", email).
	        when()
	                .get("/users");
	       
	       System.out.println(response.asString());
	       return response;          
	    }
	
	
	/**
	 * Get user information from OKTA.
	 * 
	 * Uses aia-okta-controller API.
	 * End point - GET /users/{userid}.
	 * 
	 * @param UserId Id of the user  
	 * @return Response with the User Model which contains Credentials, groupIds, profile and status 
	 */    	
	public Response GetUsersUserId(String UserId){
	       System.out.println("Getting the AIA Octa Controller API URL.");
	       RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
	       
	       Response response = RestAssured.given()
	                .log().all()
	                .headers(
	                        "Authorization",
	                        "Bearer " + base.getAccessToken(),
	                        "Content-Type",
	                        ContentType.JSON,
	                        "Accept",
	                        ContentType.JSON).      
	                pathParam("userid", UserId).
	        when()
	                .get("/users/{userid}");
	       
	       System.out.println(response.asString());
	       return response;          
	    }	
		      	    
}