package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * ContentRootControllerAPI class contains functions to make GET,PUT,POST,etc calls to root-controller 
 * of content Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class ContentRootControllerAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 	
	public ContentRootControllerAPI()
	{
		System.out.println("Initializing  Content Root Controller API.");
		base = new BaseAPITest();
	}

	/**
	 * sends request to check if the <b>Content Service</b> is up and running.
	 * 
	 * Uses content-action-controller API.
	 * End point - GET /.
	 *
	 * 	 
	 * @return Response with a welcome message. 
	 */	
	public Response GetContentRootController(){
    	System.out.println("Getting the Content Root Controller API URL.");
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
                .get("/");
    	
    	System.out.println(response.asString());
    	return response;     	
    }			    	    
}