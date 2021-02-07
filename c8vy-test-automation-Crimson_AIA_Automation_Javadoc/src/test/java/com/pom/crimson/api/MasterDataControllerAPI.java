package com.pom.crimson.api;

import com.pom.crimson.base.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * MasterDataControllerAPI class contains functions to make GET,PUT,POST,etc calls to master-data-controller
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Venkat Rao Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class MasterDataControllerAPI {
	
	BaseAPITest base;
	
	/**
	 * Initializes the Base API class.
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 	
	public MasterDataControllerAPI()
	{
		System.out.println("Initializing MasterDataControllerAPI.");
		base = new BaseAPITest();
	}
	   
	/**
	 * sends request to <b>Get all active benefits</b> defined in the application. 
	 * 
	 * Uses master-data-controller.
	 * End point - GET /activebenefits.
	 * 
	 * 
	 * @return Response with the list of all active benefits and its details. 
	 */                
	public Response GetActivebenefits(){
    	System.out.println("Getting Master Data Controller API URL.");
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
                .get("/activebenefits");
    	
    	System.out.println(response.asString());
    	return response;
    	
    }
	
	/**
	 * sends request to <b>Get all settings</b> defined in the application. 
	 * 
	 * Uses master-data-controller.
	 * End point - GET /appsettings.
	 * 
	 * 
	 * @return Response with the details of app version, support email and support phone number. 
	 */                	
	public Response GetAppSettings(){
    	System.out.println("Getting Master Data Controller API URL.");
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
                .get("/appsettings");
    	
    	System.out.println(response.asString());
    	return response;    
    }
	
	/**
	 * sends request to <b>Get all archetypes</b> defined in the app masters. 
	 * 
	 * Uses master-data-controller.
	 * End point - GET /archetypes.
	 * 
	 * 
	 * @return Response with the list of all archetypes and its details. 
	 */                
	public Response GetArcheTypes(){
    	System.out.println("Getting Master Data Controller API URL.");
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
                .get("/archetypes");
    	
    	System.out.println(response.asString());
    	return response;    
    }
	
	/**
	 * sends request to <b>Get all countries</b> defined in the app masters. 
	 * 
	 * Uses master-data-controller.
	 * End point - GET /countries.
	 * 
	 * 
	 * @return Response with the list of all countries defined and its details. 
	 */                
	public Response GetCountries(){
    	System.out.println("Getting Master Data Controller API URL.");
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
                .get("/countries");
    	
    	System.out.println(response.asString());
    	return response;    
    }
	
	/**
	 * sends request to <b>Get all interests</b> defined in the app masters. 
	 * 
	 * Uses master-data-controller.
	 * End point - GET /interests.
	 * 
	 * 
	 * @return Response with the list of all interests defined and its details. 
	 */                
	public Response GetInterests(){
    	System.out.println("Getting Master Data Controller API URL.");
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
                .get("/interests");
    	
    	System.out.println(response.asString());
    	return response;    	
    }	
	
	/**
	 * sends request to <b>Get all provinces</b> defined in the app masters. 
	 * 
	 * Uses master-data-controller.
	 * End point - GET /provinces.
	 * 
	 * 
	 * @return Response with the list of all provinces defined and its details. 
	 */                
	public Response GetProvinces(){
    	System.out.println("Getting Master Data Controller API URL.");
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
                .get("/provinces");
    	
    	System.out.println(response.asString());
    	return response;    	
    }
	
	/**
	 * sends request to <b>Get all relations</b> defined in the app masters. 
	 * 
	 * Uses master-data-controller.
	 * End point - GET /relationships.
	 * 
	 * 
	 * @return Response with the list of all relations defined and its details. 
	 */                
	public Response GetRelationships(){
    	System.out.println("Getting Master Data Controller API URL.");
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
                .get("/relationships");
    	
    	System.out.println(response.asString());
    	return response;    	
    }

}