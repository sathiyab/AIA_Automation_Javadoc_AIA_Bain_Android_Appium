package com.pom.crimson.api;

import com.pom.crimson.base.BaseAPITest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * MomentsLogAPI class contains functions to make GET,PUT,POST,etc calls to moments API 
 * and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Jaspreet Kaur Chagger
 */
public class MomentsLogAPI {
	
BaseAPITest base;
	
	public MomentsLogAPI()
	{
		try {
			System.out.println("Initializing MomentsLogAPI.");
			base = new BaseAPITest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets Moments for a given profile
	 * 
	 * Uses Moments Controller.
	 * 
	 * @param profileID profileID of logged in user
	 * @return Response containing moment data like note, babyWeight,babyHeight, imageUrl etc
	 */
	public Response getMomentsForProfile(String profileID){
		
		System.out.println("/moments/{profileId}");
    	RestAssured.baseURI = base.getProperties().getProperty("MomentsAPIURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("profileId", profileID).
        when()
                .get("/moments/{profileId}");
    	
    	return response;
    }
	
	/**
	 * Deletes Moment.
	 * 
	 * Uses Moments Controller.
	 * 
	 * @param momentId momentId of moment
	 * @return Response 
	 */
		public Response deleteMoment(String momentId){
		
		System.out.println("/moments/{profileId}");
    	RestAssured.baseURI = base.getProperties().getProperty("MomentsAPIURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                pathParam("id", momentId).
        when()
                .delete("/moments/{id}");
    	
    	return response;
    }
}