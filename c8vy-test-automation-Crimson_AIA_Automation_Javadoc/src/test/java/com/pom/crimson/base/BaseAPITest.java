/**
 * 
 */
package com.pom.crimson.base;

import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * BaseAPITest class contains functions to login to OKTA and get the Bearer token.
 * It uses Login using Email Authentication method to fetch the bearer token. 
 * Email user and passwords are available in the property file.
 *
 * 
 * @author Sivaprakash.Selvaraj
 */
public class BaseAPITest extends BaseFixture {

    private static String accessToken;
    private static String userID;
	
	/**
	 * The base API constructor calls the login method and retrieves the bearer token 
	 * which is used for authorization of other API end points. 
	 *  
	 */ 		
    public BaseAPITest() {           
        try {
			System.out.println(getProperties().getProperty("appUsername"));
			loginAndGetToken(getProperties().getProperty("appUsername"), getProperties().getProperty("appPassword"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
    }

	/**
	 * Getters to return the bearer token after authentication.  
	 * 
	 * @return String accessToken   
	 */    
	public String getAccessToken()
	{
		return accessToken;
	}
	
	/**
	 * Getters to return the OKTA user id after authentication.
	 *
	 * @return String accessToken
	 */ 	
	public String getUserID()
	{
		return userID;
	}

	/**
	 * Sends request to <b>Login</b> an user to OKTA with emailid and password and retrieve the bearer token.
	 * 
	 * Uses aia-okta-controller API.
	 * End point - GET /login
	 * 
	 * 
	 * @param username Email Id of the login user
	 * @param password Password of the login user 
	 */		
    public void loginAndGetToken(String username, String password) 
    {        
        RestAssured.baseURI = getProperties().getProperty("OKTAAPIURL");
        
        Response response = RestAssured.given()
        		.log().all()
        		.contentType(ContentType.JSON)
        		.accept(ContentType.JSON)
        		.when()
        		.body("{\r\n \"username\": \"" + username + "\",\r\n \"password\": \""+password +"\"\r\n}")
        		.post("/login") ;        
        
        accessToken = response.jsonPath().getString("accessToken") ;
        userID=response.jsonPath().getString("user.id");                
    }    
        
}
