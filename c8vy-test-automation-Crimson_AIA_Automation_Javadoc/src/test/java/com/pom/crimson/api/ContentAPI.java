package com.pom.crimson.api;

import com.pom.crimson.base.BaseAPITest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * ContentAPI class contains functions to make GET,PUT,POST,etc calls to Content
 * Service API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses
 *
 * 
 * @author Jaspreet Kaur Chagger
 */
public class ContentAPI {

	BaseAPITest base;

	public ContentAPI() {
		System.out.println("Initializing UserProfileControllerAPI.");
		base = new BaseAPITest();
	}

	/**
	 * Fetches recommended content for the homepage.
	 * 
	 * Uses Content Controller API.
	 * 
	 * @param profileID profileId of logged in user
	 * @return Response with Recommended content containing tagged
	 *         archetypes,title,description, development months, interests etc
	 */
	public Response getRecommendedContentHomePage(String profileID) {
		System.out.println("/content/homepage");
		RestAssured.baseURI = base.getProperties().getProperty("ContentAPIURL");

		Response response = RestAssured
				.given().log().all().headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type",
						ContentType.JSON, "Accept", ContentType.JSON)
				.queryParam("profileId", profileID).when().get("/content/homepage");

		System.out.println(response.asString());
		return response;
	}

	/**
	 * Gets a list of bookmarks for a profile.
	 * 
	 * Uses Content Action Controller API.
	 * 
	 * @param profileID profileId of logged in user
	 * @return Response with bookmarked content
	 */
	public Response getListOfBookmarksForProfile(String profileID) {
		System.out.println("/bookmark/{profileId}");
		RestAssured.baseURI = base.getProperties().getProperty("ContentAPIURL");

		Response response = RestAssured
				.given().log().all().headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type",
						ContentType.JSON, "Accept", ContentType.JSON)
				.pathParam("profileId", profileID).when().get("/bookmark/{profileId}");

		System.out.println(response.asString());
		return response;
	}

	/**
	 * Gets a list of likes for a profile.
	 * 
	 * Uses Content Action Controller API.
	 * 
	 * @param profileID profileId of logged in user
	 * @return Response with Liked content
	 */
	public Response getListOfLikesForProfile(String profileID) {
		System.out.println("/like/{profileId}");
		RestAssured.baseURI = base.getProperties().getProperty("ContentAPIURL");

		Response response = RestAssured
				.given().log().all().headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type",
						ContentType.JSON, "Accept", ContentType.JSON)
				.pathParam("profileId", profileID).when().get("/like/{profileId}");

		System.out.println(response.asString());
		return response;
	}

	/**
	 * Gets a daily tip for the given profile, if one exists.
	 * 
	 * Daily tip is shown on Home Page. Uses Content Controller.
	 * 
	 * @param profileID profileId of logged in user
	 * @return Response with daily tips
	 */
	public Response getDailyTips(String profileID) {
		System.out.println("/content/daily-tip/{profileId}");
		RestAssured.baseURI = base.getProperties().getProperty("ContentAPIURL");

		Response response = RestAssured.given().log().all()
				.headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type", ContentType.JSON, "Accept",
						ContentType.JSON)
				.pathParam("profileId", profileID).when().get("/content/daily-tip/{profileId}");

		return response;

	}

	/**
	 * Fetches recommended content based on a user's joined groups.
	 * 
	 * This content is shown on Based on your groups page.
	 * 
	 * Uses Content Controller
	 * 
	 * @param profileID profileId of logged in user
	 * @return Response with Recommended content containing tagged
	 *         archetypes,title,description, development months, interests etc
	 */
	public Response getContentBasedOnUserGroups(String profileID) {

		System.out.println("/content/group/{profileId}");
		RestAssured.baseURI = base.getProperties().getProperty("ContentAPIURL");

		Response response = RestAssured
				.given().log().all().headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type",
						ContentType.JSON, "Accept", ContentType.JSON)
				.pathParam("profileId", profileID).when().get("/content/group/{profileId}");

		return response;

	}

	/**
	 * Gets a list of content based on given Strapi IDs.
	 * 
	 * Uses Content Controller.
	 * 
	 * @param strapiID StrapiID of content piece(Article,Video etc)
	 * @return Response Complete content response containing tagged
	 *         archetypes,title,description, development months, interests etc
	 */
	public Response getSpecificContentBasedOnStrapiID(String strapiID) {

		System.out.println("/content/{contentId}");
		RestAssured.baseURI = base.getProperties().getProperty("ContentAPIURL");

		Response response = RestAssured
				.given().log().all().headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type",
						ContentType.JSON, "Accept", ContentType.JSON)
				.pathParam("contentId", strapiID).when().get("/content/{contentId}");

		return response;

	}

	/**
	 * Un-like a piece of content.
	 * 
	 * Uses Content Action Controller.
	 * 
	 * @param profileId profileId of logged in user
	 * @param contentId contentId of content piece(Article,Video etc)
	 * @return Response
	 */
	public Response PutLikeContent(String profileId, String contentId) {
		System.out.println("/like/{profileId}/{contentId} Unlike a piece of content");
		RestAssured.baseURI = base.getProperties().getProperty("ContentAPIURL");

		Response response = RestAssured.given().log().all()
				.headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type", ContentType.JSON, "Accept",
						ContentType.JSON)
				.pathParam("profileId", profileId).pathParam("contentId", contentId).when()
				.put("/like/{profileId}/{contentId}");

		System.out.println(response.asString());
		return response;
	}

	/**
	 * Un-bookmark a piece of content.
	 * 
	 * Uses Content Action Controller.
	 * 
	 * @param profileId profileId of logged in user
	 * @param contentId contentId of content piece(Article,Video etc)
	 * @return Response
	 */
	public Response PutBookmarkContent(String profileId, String contentId) {
		System.out.println("Getting the Content Action Controller API URL.");
		RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");

		Response response = RestAssured.given().log().all()
				.headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type", ContentType.JSON, "Accept",
						ContentType.JSON)
				.pathParam("profileId", profileId).pathParam("contentId", contentId).when()
				.put("/bookmark/{profileId}/{contentId}");

		System.out.println(response.asString());
		return response;
	}

	/**
	 * Fetches recommended content based on a specific interest.
	 * 
	 * Uses Content Controller.
	 * 
	 * @param profileId profileId of logged in user
	 * @param interestName Name of Interest (Example: Fertility, Baby care etc)
	 * @return Response with Recommended content containing tagged
	 *         archetypes,title,description, development months, interests etc
	 */
	public Response GetContentInterestName(String profileId, String interestName) {
		System.out.println("Getting the Content Controller API URL.");
		RestAssured.baseURI = base.getProperties().getProperty("CONTENTURL");

		Response response = RestAssured.given().log().all()
				.headers("Authorization", "Bearer " + base.getAccessToken(), "Content-Type", ContentType.JSON, "Accept",
						ContentType.JSON)
				.pathParam("interestName", interestName).queryParam("profileId", profileId).when()
				.get("/content/interest/{interestName}");

		System.out.println(response.asString());
		return response;
	}

}