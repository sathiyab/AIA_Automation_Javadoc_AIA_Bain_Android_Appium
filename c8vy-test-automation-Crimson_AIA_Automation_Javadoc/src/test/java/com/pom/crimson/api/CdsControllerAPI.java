package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * CdsControllerAPI class contains functions to make GET calls to CDS end points 
 * which internally calls dynamics API to get data from Common Data Service   
 * API and receive response for the same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class CdsControllerAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */        	
	public CdsControllerAPI()
	{
		System.out.println("Initializing AIAOctaControllerAPIControllerAPI.");
		base = new BaseAPITest();
	}
	
	/**
	 * Get endpoint for <b>CDS</b>, fetches data from CDS entity.
	 * 
	 * Uses cds-controller API.
	 * End point - GET /cds/{entity}
	 * 
	 * @param contacts entity name for which data to be fetched from CDS
	 * @param query queryParameter to select, filter data from CDS 
	 * @return Response with the following details retrieved from the respective CDS entity 
	 */        
	public Response getCdsEntity(String contacts,String query){
    	System.out.println("Getting the Cds Controller API URL.");
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
                pathParam("entity", contacts).
                queryParam("queryParameter", query).
                
        when()
                .get("cds/{entity}");
    	
    	System.out.println(response.asString());
    	return response; 
    	
	}     	
		      	    
}