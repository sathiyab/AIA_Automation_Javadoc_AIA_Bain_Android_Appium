package com.pom.crimson.api;

import com.pom.crimson.base.*;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.JsonTestData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * UserProfileControllerAPI class contains functions to make GET,PUT,POST,etc calls to backend
 * API and receive response from same. This uses Rest Assured library to
 * create requests and fetch responses.
 *
 * 
 * @author Sivaprakash Selvaraj
 * @author Jaspreet Kaur Chagger
 */
public class UserProfileControllerAPI {
	
	BaseAPITest base;
	
	public UserProfileControllerAPI()
	{
		System.out.println("Initializing UserProfileControllerAPI.");
		base = new BaseAPITest();
	}
	
    //User Profile Controller
	
	/**
	 * Gets user profile information from CRM.
	 * 
	 * @return Response containing profile information like isSocialLogin,contactType, gender etc.
	 */
	public Response GetProfiles(){
    	System.out.println("Getting the Profile Controller API URL.");
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
                .get("/profiles");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * Gets archetypes associated with contact.
	 * 
	 * @param profileid profileid of logged user
	 * @return Response containing archtype info like archtypeid,startdate etc associated with profile 
	 */
	public Response GetProfilesArcheTypes(String profileid){
    	System.out.println("Getting the Profile Controller API URL.");
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
                pathParam("profileid", profileid).
        when()
                .get("/profiles/{profileid}/archetypes");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * Gets benefits associated with contact.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing benefits associated with profile 
	 */
	public Response GetProfilesContactBenefits(String profileID){
    	System.out.println("Getting the Profile Controller API URL.");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/contactbenefits");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * Gets Profile image.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing image 
	 */
	public Response GetProfilesImage(String profileID){
    	System.out.println("Getting the Profile Controller API URL.");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/image");
    	
    	System.out.println(response.asString());
    	return response;     	
    } 
	
	/**
	 * Gets interests associated with contact.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing interests associated 
	 */
	public Response GetProfilesInterests(String profileID){
    	System.out.println("Getting the Profile Controller API URL.");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/interests");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * Gets Associated NPS
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing associated NPS 
	 */
	public Response GetProfilesNps(String profileID){
    	System.out.println("Getting the Profile Controller API URL.");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/nps");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * Gets all related contacts.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing related contacts 
	 */
	public Response GetProfilesRelatedPersons(String profileID){
    	System.out.println("Getting the Profile Controller API URL.");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/relatedpersons");
    	
    	System.out.println(response.asString());
    	return response;     	
    }
	
	/**
	 * Gets user profile information from CRM.
	 * 
	 * @return Response containing profile information like isSocialLogin,contactType, gender etc.
	 */
    public Response getProfiles(){
    	System.out.println("Getting the Profile Controller API URL.");
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
                .get("/profiles");
    	
    	System.out.println(response.asString());
    	return response;
    }
    
    /**
	 * Gets interests associated with contact.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing interests associated 
	 */
    public Response getInterestsByProfile(String profileID){
    	System.out.println("/profiles/{profileid}/interests");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/interests");
    	
    	System.out.println(response.asString());
    	return response;
    }	
    
    /**
	 * Gets all related contacts.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing related contacts 
	 */	
    public Response getRelatedProfiles(String profileID){
		
		System.out.println("/profiles/{profileid}/relatedpersons");
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
                pathParam("profileid", profileID).
        when()
                .get("profiles/{profileid}/relatedpersons");
    	
    	return response;
    }
    
    /**
	 * Gets master list of relations.
	 * 
	 * @return Response containing list of all relationships 
	 */	
    public Response getAllRelationships(){
		
		System.out.println("/relationships");
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
    	
    	return response;
    }
    
    /**
	 * Gets master list of archetypes.
	 * 
	 * @return Response containing list of all archetypes 
	 */
    public Response getAllArchetypes(){
		
		System.out.println("/archetypes");
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
    	
    	return response;
    }
    
    /**
	 * Gets master list of interests.
	 * 
	 * @return Response containing list of all interests 
	 */
    public Response getAllInterests()
    {
    	System.out.println("/interests");
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
    	
    	return response;
    		
    }
 
    /**
	 * Associates archetypes to contact.
	 * 
	 * @param profileID profileID of logged in user
	 * @param archetypeID archetypeID of profile to be associated
	 * 
	 * @return Response 
	 */
    public Response postArchetypeToContact(String profileID,String archetypeID)
    {
    	System.out.println("/profiles/{profileid}/archetypes");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .pathParam("profileid", profileID)
                .body("{\"archetypeId\":\""+archetypeID+"\"}")
                .when()
                .post("/profiles/{profileid}/archetypes");
    	
    	return response;
    }
    
    /**
	 * Gets archetypes associated with contact.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing archetype info like archetypeid,startdate etc associated with profile 
	 */
    public Response getArchetypeOfProfile(String profileID){
    	System.out.println("/profiles/{profileid}/archetypes");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/archetypes");
    	
    	System.out.println(response.asString());
    	return response;
    }	
    
    /**
	 * Gets interests associated with contact.
	 * 
	 * @param profileID profileID of logged user
	 * @return Response containing interests associated 
	 */
    public Response getInterestsAssociatedWithContact(String profileID)
    {
    	System.out.println("/profiles/{profileid}/interests");
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
                pathParam("profileid", profileID).
        when()
                .get("/profiles/{profileid}/interests");
    	
    	System.out.println(response.asString());
    	return response;
    	
    	
    }
    
    /**
	 * Creates second child profile related with primary profile.
	 * 
	 * @param profileID profileID of logged user
	 * @param relationshipId relationshipId of Child relation
	 * @param countryCode countryCode of residing country
	 * @param contactType Contact Type
	 * @return Response 
	 */
    public Response createSecondChild(String profileID,String relationshipId,String countryCode,String contactType)
    {	
    	String body="{"
    			+ "\"bloodtype\":\""+JsonTestData.BLOODTYPE_2+"\","
    			+ "\"consentMarketing\":\""+JsonTestData.CONSENTMARKETING_2+"\","
    			+ "\"consentUnderwriting\":\""+JsonTestData.CONSENTUNDERWRITING_2+"\","
    			+ "\"contactType\":\""+contactType+"\","
    			+ "\"country\":\""+countryCode+"\","
    			+ "\"dateOfBirth\":\""+JsonTestData.DATEOFBIRTH_2+"\","
    			+ "\"description\":\""+JsonTestData.DESCRIPTION_2+"\","
    			+ "\"eventEmail\":\""+JsonTestData.EVENTEMAIL_2+"\","
    			+ "\"eventNotification\":\""+JsonTestData.EVENTNOTIFICATION_2+"\","
    			+ "\"firstChild\":\""+JsonTestData.FIRSTCHILD_2+"\","
    			+ "\"firstname\":\""+JsonTestData.FIRSTNAME_2+"\","
    			+ "\"gender\":\""+JsonTestData.GENDER_2+"\","
    			+ "\"lastname\":\""+JsonTestData.LASTNAME_2+"\","
    			+ "\"mobilephone\":\""+JsonTestData.MOBILEPHONE_2+"\","
    			+ "\"nationalIdNumber\":\""+JsonTestData.NATIONALIDNUMBER_2+"\","
    			+ "\"preferredName\":\""+JsonTestData.PREFERREDNAME_2+"\","
    			+ "\"relationid\":\""+relationshipId+"\","
    			+ "\"rocketChatId\":\""+JsonTestData.ROCKETCHATID_2+"\","
    			+ "\"telephone\":\""+JsonTestData.TELEPHONE_2+"\""
    					+ "}";

    	
    			
    	System.out.println("/profiles/{profileid}/relatedpersons");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .pathParam("profileid", profileID)
                .body(body)
                .when()
                .post("/profiles/{profileid}/relatedpersons");
    	
    	return response;
    	
    	
    	
    	
    }
    
    /**
	 * Creates Grand parent profile related with primary profile.
	 * 
	 * @param profileID profileID of logged user
	 * @param relationshipId relationshipId of Grand Parent relation
	 * @param countryCode countryCode of residing country
	 * @param contactType Contact Type
	 * @return Response 
	 */
    public Response createGrandParent(String profileID,String relationshipId,String countryCode,String contactType)
    {	
    	String body="{"
    			+ "\"bloodtype\":\""+JsonTestData.BLOODTYPE_GRPARENT+"\","
    			+ "\"consentMarketing\":\""+JsonTestData.CONSENTMARKETING_GRPARENT+"\","
    			+ "\"consentUnderwriting\":\""+JsonTestData.CONSENTUNDERWRITING_GRPARENT+"\","
    			+ "\"contactType\":\""+contactType+"\","
    			+ "\"country\":\""+countryCode+"\","
    			+ "\"dateOfBirth\":\""+JsonTestData.DATEOFBIRTH_GRPARENT+"\","
    			+ "\"description\":\""+JsonTestData.DESCRIPTION_GRPARENT+"\","
    			+ "\"eventEmail\":\""+JsonTestData.EVENTEMAIL_GRPARENT+"\","
    			+ "\"eventNotification\":\""+JsonTestData.EVENTNOTIFICATION_GRPARENT+"\","
    			+ "\"firstname\":\""+JsonTestData.FIRSTNAME_GRPARENT+"\","
    			+ "\"gender\":\""+JsonTestData.GENDER_GRPARENT+"\","
    			+ "\"lastname\":\""+JsonTestData.LASTNAME_GRPARENT+"\","
    			+ "\"mobilephone\":\""+JsonTestData.MOBILEPHONE_GRPARENT+"\","
    			+ "\"nationalIdNumber\":\""+JsonTestData.NATIONALIDNUMBER_GRPARENT+"\","
    			+ "\"preferredName\":\""+JsonTestData.PREFERREDNAME_GRPARENT+"\","
    			+ "\"relationid\":\""+relationshipId+"\","
    			+ "\"rocketChatId\":\""+JsonTestData.ROCKETCHATID_GRPARENT+"\","
    			+ "\"telephone\":\""+JsonTestData.TELEPHONE_GRPARENT+"\""
    					+ "}";

    	
    			
    	System.out.println("/profiles/{profileid}/relatedpersons");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .pathParam("profileid", profileID)
                .body(body)
                .when()
                .post("/profiles/{profileid}/relatedpersons");
    	
    	return response;
    	
    	
    	
    	
    }
    
    /**
	 * Creates Parent profile related with primary profile.
	 * 
	 * @param profileID profileID of logged user
	 * @param relationshipId relationshipId of Parent relation
	 * @param countryCode countryCode of residing country
	 * @param contactType Contact Type
	 * @return Response 
	 */
    public Response createParent(String profileID,String relationshipId,String countryCode,String contactType)
    {	
    	String body="{"
    			+ "\"bloodtype\":\""+JsonTestData.BLOODTYPE_PARENT+"\","
    			+ "\"consentMarketing\":\""+JsonTestData.CONSENTMARKETING_PARENT+"\","
    			+ "\"consentUnderwriting\":\""+JsonTestData.CONSENTUNDERWRITING_PARENT+"\","
    			+ "\"contactType\":\""+contactType+"\","
    			+ "\"country\":\""+countryCode+"\","
    			+ "\"dateOfBirth\":\""+JsonTestData.DATEOFBIRTH_PARENT+"\","
    			+ "\"description\":\""+JsonTestData.DESCRIPTION_PARENT+"\","
    			+ "\"eventEmail\":\""+JsonTestData.EVENTEMAIL_PARENT+"\","
    			+ "\"eventNotification\":\""+JsonTestData.EVENTNOTIFICATION_PARENT+"\","
    			+ "\"firstname\":\""+JsonTestData.FIRSTNAME_PARENT+"\","
    			+ "\"gender\":\""+JsonTestData.GENDER_PARENT+"\","
    			+ "\"lastname\":\""+JsonTestData.LASTNAME_PARENT+"\","
    			+ "\"mobilephone\":\""+JsonTestData.MOBILEPHONE_PARENT+"\","
    			+ "\"nationalIdNumber\":\""+JsonTestData.NATIONALIDNUMBER_PARENT+"\","
    			+ "\"preferredName\":\""+JsonTestData.PREFERREDNAME_PARENT+"\","
    			+ "\"relationid\":\""+relationshipId+"\","
    			+ "\"rocketChatId\":\""+JsonTestData.ROCKETCHATID_PARENT+"\","
    			+ "\"telephone\":\""+JsonTestData.TELEPHONE_PARENT+"\""
    					+ "}";

    	
    			
    	System.out.println("/profiles/{profileid}/relatedpersons");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .pathParam("profileid", profileID)
                .body(body)
                .when()
                .post("/profiles/{profileid}/relatedpersons");
    	
    	return response;
    	
    	
    	
    	
    }
    
    /**
   	 * Creates first child profile related with primary profile.
   	 * 
   	 * @param profileID profileID of logged user
   	 * @param relationshipId relationshipId of Child relation
   	 * @param countryCode countryCode of residing country
   	 * @param contactType Contact Type
   	 * @return Response 
   	 */
    public Response createFirstChild(String profileID,String relationshipId,String countryCode,String contactType)
    {	
    	String body="{"
    			+ "\"bloodtype\":\""+JsonTestData.BLOODTYPE_1+"\","
    			+ "\"consentMarketing\":\""+JsonTestData.CONSENTMARKETING_1+"\","
    			+ "\"consentUnderwriting\":\""+JsonTestData.CONSENTUNDERWRITING_1+"\","
    			+ "\"contactType\":\""+contactType+"\","
    			+ "\"country\":\""+countryCode+"\","
    			+ "\"dateOfBirth\":\""+JsonTestData.DATEOFBIRTH_1+"\","
    			+ "\"description\":\""+JsonTestData.DESCRIPTION_1+"\","
    			+ "\"eventEmail\":\""+JsonTestData.EVENTEMAIL_1+"\","
    			+ "\"eventNotification\":\""+JsonTestData.EVENTNOTIFICATION_1+"\","
    			+ "\"firstChild\":\""+JsonTestData.FIRSTCHILD_1+"\","
    			+ "\"firstname\":\""+JsonTestData.FIRSTNAME_1+"\","
    			+ "\"gender\":\""+JsonTestData.GENDER_1+"\","
    			+ "\"lastname\":\""+JsonTestData.LASTNAME_1+"\","
    			+ "\"mobilephone\":\""+JsonTestData.MOBILEPHONE_1+"\","
    			+ "\"nationalIdNumber\":\""+JsonTestData.NATIONALIDNUMBER_1+"\","
    			+ "\"preferredName\":\""+JsonTestData.PREFERREDNAME_1+"\","
    			+ "\"relationid\":\""+relationshipId+"\","
    			+ "\"rocketChatId\":\""+JsonTestData.ROCKETCHATID_1+"\","
    			+ "\"telephone\":\""+JsonTestData.TELEPHONE_1+"\""
    					+ "}";

    	System.out.println("/profiles/{profileid}/relatedpersons");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .pathParam("profileid", profileID)
                .body(body)
                .when()
                .post("/profiles/{profileid}/relatedpersons");
    	
    	return response;
    	
    	
    	
    	
    }
    
    /**
   	 * Gets master list of all countries.
   	 * 
   	 * @return Response containing list of all countries
   	 */
    public Response getAllCountries(){
		
		System.out.println("/countries");
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
    	
    	return response;
    }
    
    /**
   	 * Deletes related person.
   	 * 
   	 * @param profileId profileID of logged user
   	 * @param relatedPersonId relatedPersonId of related person
   	 * @return Response 
   	 */
    public Response deleteRelatedPerson(String profileId,String relatedPersonId){
		
		System.out.println("/profiles/{profileid}/relatedpersons/{relatedPersonId}");
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
                pathParam("profileid", profileId)
                .pathParam("relatedPersonId", relatedPersonId).
        when()
                .delete("/profiles/{profileid}/relatedpersons/{relatedPersonId}");
    	
    	return response;
    }
    
    /**
   	 * Updates Date of Birth of related person.
   	 * 
   	 * @param profileId profileId of logged user
   	 * @param relatedPersonId relatedPersonId of related person
   	 * @param dateOfBirth Date of birth of related person to be updated
   	 * @return Response 
   	 */
    public Response updateDateOfBirthRelatedPerson(String profileId,String relatedPersonId,String dateOfBirth)
    {
    	System.out.println("/profiles/{profileid}/relatedpersons/{relatedPersonId}");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .pathParam("profileid",profileId)
                .pathParam("relatedPersonId",relatedPersonId)
                .body("{\"dateOfBirth\":\""+dateOfBirth+"\"}")
                .when()
                .patch("/profiles/{profileid}/relatedpersons/{relatedPersonId}");
    	
    	return response;	
    }
    
    /**
   	 * Updates Due Date of primary profile.
   	 * 
   	 * @param profileId profileId of logged user
   	 * @param dueDate  Due date to be updated
   	 * @return Response 
   	 */
    public Response updateDueDate(String profileId,String dueDate)
    {
    	System.out.println("/profiles/{profileid}");
    	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
    	
    	Response response = RestAssured.given()
                .log().all()
                .headers(
                        "Authorization",
                        "Bearer " + base.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .pathParam("profileid",profileId)
                .body("{\"dueDate\":\""+dueDate+"\"}")
                .when()
                .patch("/profiles/{profileid}");
    	
    	return response;	
    }
    
    /**
   	 * Associates interests to contact.
   	 * 
   	 * @param profileId profileId of logged user
   	 * @param interestID1  InterestID of first interest to be associated
     * @param interestID2  InterestID of second interest to be associated
     * @param interestID3  InterestID of third interest to be associated
   	 * @return Response 
   	 */ 
   public Response associateInterestToContact(String interestID1,String interestID2,String interestID3,String profileId) 
   {
	   
	 System.out.println("/profiles/{profileid}/interests");
   	RestAssured.baseURI = base.getProperties().getProperty("OKTAAPIURL");
   	
   	Response response = RestAssured.given()
               .log().all()
               .headers(
                       "Authorization",
                       "Bearer " + base.getAccessToken(),
                       "Content-Type",
                       ContentType.JSON,
                       "Accept",
                       ContentType.JSON)
               .pathParam("profileid",profileId)
               .body("{\"interestIdList\":[ \""+interestID1+"\",\""+interestID2+"\",\""+interestID3+"\"]}")
               .when()
               .post("/profiles/{profileid}/interests");
   	
   	return response;
	   
   }
      
    
}