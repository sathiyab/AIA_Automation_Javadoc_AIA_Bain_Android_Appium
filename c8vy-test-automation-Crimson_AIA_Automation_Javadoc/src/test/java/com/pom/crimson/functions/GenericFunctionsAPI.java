package com.pom.crimson.functions;

import java.util.Collections;
import java.util.List;

import org.testng.SkipException;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.ContentAPI;
import com.pom.crimson.api.GroupAdviceAPI;
import com.pom.crimson.api.MomentsLogAPI;
import com.pom.crimson.api.UserProfileControllerAPI;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.restassured.response.Response;

/** GenericFunctionsAPI class contains:
 * Functions related extracting profile data like profileId,contactType, archtypeId etc of profile
 * Functions related to fetching related profiles data like fetching preferred name for Child,parent,
 * Functions related to creating/deleting test data for profile: Create related contacts like Child,parent, Grandparent
 * Functions related to fetching recommended content, daily tips etc
 * Functions for extracting user groups.
 * 
* @author Jaspreet Kaur Chagger
*/
public class GenericFunctionsAPI {

	ContentAPI contentAPI;
	GroupAdviceAPI groupAdviceAPI;
	MomentsLogAPI momentsLogAPI;
	UserProfileControllerAPI userProfileControllerAPI;

	public GenericFunctionsAPI() {

		contentAPI = new ContentAPI();
		groupAdviceAPI = new GroupAdviceAPI();
		momentsLogAPI = new MomentsLogAPI();
		userProfileControllerAPI = new UserProfileControllerAPI();

	}

	/*----- Functions based on User Profile Controller API-------*/

	/**
	 * Returns profileId of logged in user.
	 * 
	 * @return profileId for logged in app user. Value will be String.
	 */
	public String getProfileId() {
		String profileId = "";
		Response profiles = userProfileControllerAPI.getProfiles();
		if (profiles.statusCode() == 200) {
			profileId = profiles.jsonPath().getString("contactid");
		}

		return profileId;

	}
	

	/**
	 * Returns contactType value of primary user profile.
	 * 
	 * @return contactType (Example:935000000).Value will be String.
	 */
	public String getContactType() {
		Response profile = userProfileControllerAPI.getProfiles();
		String contactType = "";

		if (profile.statusCode() == 200) {
			contactType = profile.jsonPath().getString("contactType");
		}
		return contactType;
	}

	/**
	 * Returns the ArchetypeID associated with primary profile.
	 * 
	 * @return ArchetypeID of primary user profile. Value will be String
	 */
	public String getArchetypeIdOfProfile() {
		String contactID = getProfileId();
		Response res_ArchetypeProfile = userProfileControllerAPI.getArchetypeOfProfile(contactID);
		List<String> archIDProfile;

		if (res_ArchetypeProfile.statusCode() == 200) {

			String json = res_ArchetypeProfile.getBody().asString();
			archIDProfile = com.jayway.jsonpath.JsonPath.parse(json).read("$.._aiabase_archetypeid_value");
		} else {
			archIDProfile = Collections.emptyList();
		}
		return archIDProfile.get(0);
	}

	/**
	 * Updates Archetype of user. Currently there are 4 Archetypes: Parent to be,
	 * Planning for a baby,Experienced parent, New parent
	 * @param archetypeID id of Archetype.Value will be String.
	 * @return boolean status true: Archetype is updated false: Archetype is not
	 *         updated
	 */
	public boolean changeArcheTypeOfUser(String archetypeID) {
		String contactID = getProfileId();
		Response res_UpdateArchetypeProfile = userProfileControllerAPI.postArchetypeToContact(contactID, archetypeID);

		if (res_UpdateArchetypeProfile.statusCode() == 200) {
			return true;

		}
		return false;

	}

	/**
	 * Returns ArchetypeId (aiabase_archetypeid) for an Archetype. Currently there
	 * are 4 Archetypes: Parent to be, Planning for a baby,Experienced parent, New
	 * parent
	 * 
	 * @param archetype name of Archetype.Value will be String.
	 * @return aiabase_archetypeid for an Archetype.Value will be String.
	 */
	public String getArchetypeIdForArchetype(String archetype) {
		// Get all Archetypes
		Response res_allArchetypes = userProfileControllerAPI.getAllArchetypes();
		List<String> archetypeId;

		if (res_allArchetypes.statusCode() == 200) {
			// $[?(@.aiabase_name=='New parent')].aiabase_archetypeid
			String json = res_allArchetypes.getBody().asString();
			archetypeId = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$[?(@.aiabase_name=='" + archetype + "')].aiabase_archetypeid");

		} else {
			archetypeId = Collections.emptyList();

		}

		return archetypeId.get(0);
	}

	/**
	 * Returns codeableConceptID for Child.
	 * 
	 * @return codeableConceptID for Child.Value will be String.
	 */
	public String getCodeableConceptId_Child()

	{
		Response allRelationships = userProfileControllerAPI.getAllRelationships();
		List<String> relationName;
		List<String> relationId;

		if (allRelationships.statusCode() == 200) {
			relationName = allRelationships.jsonPath().getList("msemr_name");
			relationId = allRelationships.jsonPath().getList("msemr_codeableconceptid");
		} else {
			relationName = Collections.emptyList();
			relationId = Collections.emptyList();
		}

		String child = "Child";
		String parent = "Parent";
		String grandParent = "Grandparent";
		int index_child = relationName.indexOf(child);
		String codeableConceptIDChild = relationId.get(index_child);
		return codeableConceptIDChild;
	}

	/**
	 * Returns codeableConceptID for Grandparent.
	 * 
	 * @return codeableConceptID for Grandparent .Value will be String.
	 */
	public String getCodeableConceptId_GrandParent()

	{
		Response allRelationships = userProfileControllerAPI.getAllRelationships();
		List<String> relationName;
		List<String> relationId;

		if (allRelationships.statusCode() == 200) {
			relationName = allRelationships.jsonPath().getList("msemr_name");
			relationId = allRelationships.jsonPath().getList("msemr_codeableconceptid");
		} else {
			relationName = Collections.emptyList();
			relationId = Collections.emptyList();
		}

		String child = "Child";
		String parent = "Parent";
		String grandParent = "Grandparent";
		int index_grandparent = relationName.indexOf(grandParent);
		String codeableConceptIDGrandParent = relationId.get(index_grandparent);
		return codeableConceptIDGrandParent;
	}

	/**
	 * Returns codeableConceptID for parent.
	 * 
	 * @return codeableConceptID for parent.Value will be String.
	 */
	public String getCodeableConceptId_Parent()

	{
		Response allRelationships = userProfileControllerAPI.getAllRelationships();
		List<String> relationName;
		List<String> relationId;

		if (allRelationships.statusCode() == 200) {
			relationName = allRelationships.jsonPath().getList("msemr_name");
			relationId = allRelationships.jsonPath().getList("msemr_codeableconceptid");
		} else {
			relationName = Collections.emptyList();
			relationId = Collections.emptyList();
		}

		String child = "Child";
		String parent = "Parent";
		String grandParent = "Grandparent";
		int index_parent = relationName.indexOf(parent);
		String codeableConceptIDParent = relationId.get(index_parent);
		return codeableConceptIDParent;
	}

	/**
	 * Returns list of preferred names of all child profiles related with primary
	 * user profile .
	 * 
	 * @return List of preferred names of all child profiles Value will be
	 *         List of Strings.
	 */
	public List<String> getAllChildPreferredName() {

		String contactID = getProfileId();
		Response relatedProfiles = userProfileControllerAPI.getRelatedProfiles(contactID);
		String json = relatedProfiles.getBody().asString();
		String childRelationId = getCodeableConceptId_Child();
		List<String> ChildPreferredNames;

		if (relatedProfiles.statusCode() == 200) {
			ChildPreferredNames = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.contactsList.[?(@.relationid=='" + childRelationId + "')].preferredName");
		} else {
			ChildPreferredNames = Collections.emptyList();
		}

		return ChildPreferredNames;
	}

	/**
	 * Returns preferred name of first child profile related with primary user
	 * profile.
	 * 
	 * @return Preferred name of first child profile.Value will be String.
	 */
	public String getFirstChildPreferredName() {
		String contactID = getProfileId();
		Response relatedProfiles = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> relatedPersonsPreferredNames;
		List<String> relationIds;
		String firstChildNamePreferredName = "";

		if (relatedProfiles.statusCode() == 200) {
			relatedPersonsPreferredNames = relatedProfiles.jsonPath().getList("contactsList.preferredName");
			System.out.println("relatedPersonsPreferredNames: " + relatedPersonsPreferredNames);
			relationIds = relatedProfiles.jsonPath().getList("contactsList.relationid");
			System.out.println("relationIds: " + relationIds);

		} else {
			relatedPersonsPreferredNames = Collections.emptyList();
			relationIds = Collections.emptyList();
		}

		String childRelationId = getCodeableConceptId_Child();

		if (relationIds.contains(childRelationId)) {
			int index_firstChildRelation = relationIds.indexOf(childRelationId);
			firstChildNamePreferredName = relatedPersonsPreferredNames.get(index_firstChildRelation);
			System.out.println("firstChildNamePreferredName: " + firstChildNamePreferredName);
		}

		return firstChildNamePreferredName;

	}

	/**
	 * Returns preferred name of first GrandParent profile related with primary user
	 * profile .
	 * 
	 * @return Preferred name of first GrandParent profile Value will be String.
	 */
	public String getFirstGrandParentPreferredName() {
		String contactID = getProfileId();
		Response relatedProfiles = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> relatedPersonsPreferredNames;
		List<String> relationIds;
		String firstGrandParentNamePreferredName = "";

		if (relatedProfiles.statusCode() == 200) {
			relatedPersonsPreferredNames = relatedProfiles.jsonPath().getList("contactsList.preferredName");
			relationIds = relatedProfiles.jsonPath().getList("contactsList.relationid");

		} else {
			relatedPersonsPreferredNames = Collections.emptyList();
			relationIds = Collections.emptyList();
		}

		String grandParentRelationId = getCodeableConceptId_GrandParent();

		if (relationIds.contains(grandParentRelationId)) {
			int index_firstGrandParentRelation = relationIds.indexOf(grandParentRelationId);
			firstGrandParentNamePreferredName = relatedPersonsPreferredNames.get(index_firstGrandParentRelation);
		}

		return firstGrandParentNamePreferredName;

	}

	/**
	 * Returns preferred name of first Parent profile related with primary user
	 * profile .
	 * 
	 * @return The preferred name of first Parent profile Value will be String.
	 */
	public String getFirstParentPreferredName() {
		String contactID = getProfileId();
		Response relatedProfiles = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> relatedPersonsPreferredNames;
		List<String> relationIds;
		String firstParentNamePreferredName = "";

		if (relatedProfiles.statusCode() == 200) {
			relatedPersonsPreferredNames = relatedProfiles.jsonPath().getList("contactsList.preferredName");
			relationIds = relatedProfiles.jsonPath().getList("contactsList.relationid");

		} else {
			relatedPersonsPreferredNames = Collections.emptyList();
			relationIds = Collections.emptyList();
		}

		String parentRelationId = getCodeableConceptId_Parent();

		if (relationIds.contains(parentRelationId)) {
			int index_firstParentRelation = relationIds.indexOf(parentRelationId);
			firstParentNamePreferredName = relatedPersonsPreferredNames.get(index_firstParentRelation);
		}

		return firstParentNamePreferredName;

	}

	/**
	 * Returns list of ContactIds of all child profiles related with primary user
	 * profile .
	 * 
	 * @return list of ContactIds of all child profiles Value will be List of Strings..
	 */
	public List<String> getContactId_AllChildProfiles() {
		String contactID = getProfileId();
		String relationshipId = getCodeableConceptId_Child();
		Response relatedProf = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> contactId_Child;

		if (relatedProf.statusCode() == 200) {
			String json = relatedProf.getBody().asString();
			contactId_Child = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.contactsList.[?(@.relationid=='" + relationshipId + "')].contactid");
		} else {
			contactId_Child = Collections.emptyList();
		}

		return contactId_Child;
	}

	/**
	 * Returns list of ContactIds of all GrandParent profiles related with primary
	 * user profile .
	 * 
	 * @return List of ContactIds of all GrandParent profiles Value will be
	 *         List of Strings..
	 */
	public List<String> getContactId_AllGrandParentProfiles() {
		String contactID = getProfileId();
		String relationshipId = getCodeableConceptId_GrandParent();
		Response relatedProf = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> contactId_GrandParent;

		if (relatedProf.statusCode() == 200) {
			String json = relatedProf.getBody().asString();
			contactId_GrandParent = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.contactsList.[?(@.relationid=='" + relationshipId + "')].contactid");
		} else {
			contactId_GrandParent = Collections.emptyList();
		}

		return contactId_GrandParent;
	}

	/**
	 * Returns list of ContactIds of all Parent profiles related with primary user
	 * profile .
	 * 
	 * @return List of ContactIds of all Parent profiles Value will be List of Strings..
	 */
	public List<String> getContactId_AllRelatedParentProfiles() {
		String contactID = getProfileId();
		String relationshipId = getCodeableConceptId_Parent();
		Response relatedProf = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> contactId_Parent;

		if (relatedProf.statusCode() == 200) {
			String json = relatedProf.getBody().asString();
			contactId_Parent = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.contactsList.[?(@.relationid=='" + relationshipId + "')].contactid");
		} else {
			contactId_Parent = Collections.emptyList();
		}

		return contactId_Parent;
	}

	/**
	 * Returns total number of child profiles related with primary user profile.
	 * 
	 * @return Total number of children.Value will be int.
	 */
	public int getNoOfChildProfiles() {
		String contactID = getProfileId();
		Response relatedProfiles = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> relationIds;
		if (relatedProfiles.statusCode() == 200) {
			relationIds = relatedProfiles.jsonPath().getList("contactsList.relationid");
		} else {
			relationIds = Collections.emptyList();
		}

		String childRelationId = getCodeableConceptId_Child();
		int occurrences = 0;
		if (relationIds.contains(childRelationId)) {
			occurrences = Collections.frequency(relationIds, childRelationId);
		}

		return occurrences;

	}

	/**
	 * Returns total number of Grandparent profiles related with primary user
	 * profile.
	 * 
	 * @return Total number of Grandparents.Value will be int.
	 */
	public int getNoOfGrandparentProfiles() {
		String contactID = getProfileId();
		Response relatedProfiles = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> relationIds;
		if (relatedProfiles.statusCode() == 200) {
			relationIds = relatedProfiles.jsonPath().getList("contactsList.relationid");
		} else {
			relationIds = Collections.emptyList();
		}

		String grandParentRelationId = getCodeableConceptId_GrandParent();
		int occurrences = 0;
		if (relationIds.contains(grandParentRelationId)) {
			occurrences = Collections.frequency(relationIds, grandParentRelationId);
		}

		return occurrences;

	}

	/**
	 * Returns total number of parent profiles related with primary user profile.
	 * 
	 * @return Total number of parents.Value will be int.
	 * 
	 */
	public int getNoOfParentProfiles() {
		String contactID = getProfileId();
		Response relatedProfiles = userProfileControllerAPI.getRelatedProfiles(contactID);
		List<String> relationIds;
		if (relatedProfiles.statusCode() == 200) {
			relationIds = relatedProfiles.jsonPath().getList("contactsList.relationid");
		} else {
			relationIds = Collections.emptyList();
		}

		String parentRelationId = getCodeableConceptId_Parent();
		int occurrences = 0;
		if (relationIds.contains(parentRelationId)) {
			occurrences = Collections.frequency(relationIds, parentRelationId);
		}

		return occurrences;

	}

	/**
	 * Deletes all child profiles related with primary user profile.
	 */
	public void deleteAllChildProfiles() {
		String profileId = getProfileId();
		List<String> userIds = getContactId_AllChildProfiles();
		int size = userIds.size();
		try {
			for (int i = 0; i < size; i++) {
				Response res = userProfileControllerAPI.deleteRelatedPerson(profileId, userIds.get(i));
			}
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO, "Not able to delete Grandparent profile");
			e.printStackTrace();
		}
	}

	/**
	 * Deletes all Grandparent profiles related with primary user profile.
	 */
	public void deleteAllGrandParentProfiles() {
		String profileId = getProfileId();
		List<String> userIds = getContactId_AllGrandParentProfiles();
		int size = userIds.size();
		try {
			for (int i = 0; i < size; i++) {
				Response res = userProfileControllerAPI.deleteRelatedPerson(profileId, userIds.get(i));
			}
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO, "Not able to delete Grandparent profile");
			e.printStackTrace();
		}
	}

	/**
	 * Deletes all Parent profiles related with primary user profile.
	 */
	public void deleteAllParentProfiles() {
		String profileId = getProfileId();
		List<String> userIds = getContactId_AllRelatedParentProfiles();
		int size = userIds.size();
		try {
			for (int i = 0; i < size; i++) {
				Response res = userProfileControllerAPI.deleteRelatedPerson(profileId, userIds.get(i));
			}
		} catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO, "Not able to delete parent profile");
			e.printStackTrace();
		}
	}

	/**
	 * Creates related first Child Profile for primary user.
	 * 
	 * @return boolean status true: Child Profile created false: Child Profile not
	 *         created
	 */
	public boolean createFirstChildProfile() {
		String contactID = getProfileId();
		String relationshipId = getCodeableConceptId_Child();
		String countryCode = getCountryCode("Thailand");
		String contactType = getContactType();

		Response createChildProfile = userProfileControllerAPI.createFirstChild(contactID, relationshipId, countryCode,
				contactType);
		if (createChildProfile.statusCode() == 200) {
			return true;
		}
		return false;

	}

	/**
	 * Creates related second Child Profile for primary user.
	 * 
	 * @return boolean status true: Child Profile created false: Child Profile not
	 *         created
	 */
	public boolean createSecondChildProfile() {
		String contactID = getProfileId();
		String relationshipId = getCodeableConceptId_Child();
		String countryCode = getCountryCode("India");
		String contactType = getContactType();

		Response createChildProfile = userProfileControllerAPI.createSecondChild(contactID, relationshipId, countryCode,
				contactType);
		if (createChildProfile.statusCode() == 200) {
			return true;
		}
		return false;

	}

	/**
	 * Creates related Grand parent Profile for primary user.
	 * 
	 * @return boolean status true: Child Profile created false: Child Profile not
	 *         created
	 */
	public boolean createGrandparentProfile() {
		String contactID = getProfileId();
		String relationshipId = getCodeableConceptId_GrandParent();
		String countryCode = getCountryCode("India");
		String contactType = getContactType();

		Response createGrandParentProfile = userProfileControllerAPI.createGrandParent(contactID, relationshipId,
				countryCode, contactType);
		if (createGrandParentProfile.statusCode() == 200) {
			return true;
		}
		return false;

	}

	/**
	 * Creates related Parent Profile for primary user.
	 * 
	 * @return boolean status true: Parent Profile created false: Parent Profile not
	 *         created
	 */
	public boolean createParentProfile() {
		String contactID = getProfileId();
		String relationshipId = getCodeableConceptId_Parent();
		String countryCode = getCountryCode("India");
		String contactType = getContactType();

		Response createParentProfile = userProfileControllerAPI.createParent(contactID, relationshipId, countryCode,
				contactType);
		if (createParentProfile.statusCode() == 200) {
			return true;
		}
		return false;

	}

	/**
	 * Updates date of birth of first child profile related with primary user
	 * profile.
	 * 
	 * @param DOB Date of birth to be updated.Value will be String.
	 * @return boolean value. true: Date of birth updated false: Date of birth not
	 *         updated
	 */
	public boolean updateDateofBirth_FirstChild(String DOB) {
		String profileID = getProfileId();
		String relationshipId_Child = getCodeableConceptId_Child();
		List<String> childPreferredName = getAllChildPreferredName();
		String firstChildPrefferedName = childPreferredName.get(0);
		Response relatedProf = userProfileControllerAPI.getRelatedProfiles(profileID);
		List<String> contactId_Child;
		if (relatedProf.statusCode() == 200) {
			String json = relatedProf.getBody().asString();
			contactId_Child = com.jayway.jsonpath.JsonPath.parse(json).read("$.contactsList.[?(@.relationid=='"
					+ relationshipId_Child + "' && @.preferredName=='" + firstChildPrefferedName + "')].contactid");
		} else {
			contactId_Child = Collections.emptyList();
		}
		String relationshipID = contactId_Child.get(0);
		Response res = userProfileControllerAPI.updateDateOfBirthRelatedPerson(profileID, relationshipID, DOB);

		if (res.statusCode() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * Updates dueDate of primary user profile.
	 * 
	 * @param dueDate Due date to be updated.Value will be String.
	 * @return boolean value. true: dueDate updated false: dueDate not updated
	 */
	public boolean updateDueDate(String dueDate) {
		String profileID = getProfileId();
		Response res = userProfileControllerAPI.updateDueDate(profileID, dueDate);
		if (res.statusCode() == 200) {
			return true;
		}
		return false;
	}

	/*----- Functions based on Content API-------*/

	/**
	 * Returns daily tip for primary user profile from contentAPI (Content
	 * Controller).
	 * 
	 * @return Daily tip as Response(io.restassured.response.Response)
	 *
	 */
	public Response getDailyTip() {
		String contactID = getProfileId();
		Response dailyTipRes = contentAPI.getDailyTips(contactID);
		return dailyTipRes;
	}
	/**
	 * Returns country code for a country.
	 * 
	 * @param countryname country name.Value will be String.
	 * @return country code.Value will be String.
	 */
	public String getCountryCode(String countryname) {
		Response countries = userProfileControllerAPI.getAllCountries();
		String json = "";
		List<String> ids;

		if (countries.statusCode() == 200) {
			json = countries.asString();
			ids = com.jayway.jsonpath.JsonPath.parse(json)
					.read("$.[?(@.aiabase_name=='" + countryname + "')].aiabase_countryid");
		} else {
			ids = Collections.emptyList();
		}
		return ids.get(0);
	}

	/**
	 * Returns list of content titles for recommended content displayed on Home Page.
	 * 
	 * @return List of content titles for recommended content displayed on Home
	 *         Page.Value will be List of Strings
	 */
	public List<String> getRecommendedContentTitlesHomePage() {
		String profileID = getProfileId();
		Response res = contentAPI.getRecommendedContentHomePage(profileID);
		List<String> titleContent;

		// Check if Recommended content is available for user
		if (res.statusCode() == 200) {
			String json = res.getBody().asString();
			titleContent = com.jayway.jsonpath.JsonPath.parse(json).read("$..title");
		} else {
			titleContent = Collections.emptyList();
		}

		return titleContent;

	}

	/**
	 * Returns list of content titles for recommended content based on groups user
	 * is part of.
	 * 
	 * @return List of content titles for recommended content based on groups user
	 *         is part of.Value will be List of Strings
	 */
	public List<String> getRecommendedContentTitlesBasedOnGroups() {
		String profileID = getProfileId();
		Response res = contentAPI.getContentBasedOnUserGroups(profileID);
		List<String> ids;

		if (res.statusCode() == 200) {
			String json = res.getBody().asString();
			ids = com.jayway.jsonpath.JsonPath.parse(json).read("$..content.id");

		} else {
			ids = Collections.emptyList();
		}

		return ids;

	}

	/**
	 * Returns list of content titles for recommended content displayed based on specific
	 * Interest.
	 * @param interestName Interest Name
	 * @return List of content titles for content displayed based on specific
	 *         Interest.Value will be List of Strings
	 */
	public List<String> getRecommendedContentTitlesBasedOnInterest(String interestName) {
		String profileID = getProfileId();
		Response res = contentAPI.GetContentInterestName(profileID, interestName);
		List<String> titleContent;

		// Check if Recommended content is available for user
		if (res.statusCode() == 200) {
			String json = res.getBody().asString();
			titleContent = com.jayway.jsonpath.JsonPath.parse(json).read("$..title");
		} else {
			titleContent = Collections.emptyList();
		}

		return titleContent;

	}

	/**
	 * Returns list of ids of Bookmarks saved by user.
	 * 
	 * @return List of ids of Bookmarks saved by user.Value will be List of Strings
	 */
	public List<String> getBookmarksForProfile() {

		String profileID = getProfileId();
		Response response_Bookmarks = contentAPI.getListOfBookmarksForProfile(profileID);
		List<String> bookmarks;

		if (response_Bookmarks.statusCode() == 200) {
			bookmarks = response_Bookmarks.jsonPath().getList("id");
		} else {
			bookmarks = Collections.emptyList();
		}

		return bookmarks;
	}

	/**
	 * Returns list of ids of Likes saved by user.
	 * 
	 * @return List of ids of Likes saved by user.Value will be List of Strings
	 */
	public List<String> getLikesForProfile() {

		String profileID = getProfileId();
		Response response_Likes = contentAPI.getListOfLikesForProfile(profileID);
		List<String> likes;

		if (response_Likes.statusCode() == 200) {
			likes = response_Likes.jsonPath().getList("id");
		} else {
			likes = Collections.emptyList();
		}
		return likes;
	}

	/**
	 * Returns list of contentIDs for all liked content for a profile.
	 * 
	 * @return List of contentIDs for all liked content for a profile.Value will be
	 *         List of Strings
	 */
	public List<String> getContentIDLikedContentForProfile() {

		String profileID = getProfileId();
		Response response_Likes = contentAPI.getListOfLikesForProfile(profileID);
		List<String> likes;

		if (response_Likes.statusCode() == 200) {
			likes = response_Likes.jsonPath().getList("contentId");
		} else {
			likes = Collections.emptyList();
		}
		return likes;
	}

	/**
	 * Returns list of contentIDs for all Bookmarked content for a profile.
	 * 
	 * @return List of contentIDs for all Bookmarked content for a profile.Value
	 *         will be List of Strings
	 */
	public List<String> getContentIDOfBookmarkedContentForProfile() {

		String profileID = getProfileId();
		Response response_bookmarks = contentAPI.getListOfBookmarksForProfile(profileID);
		List<String> bookmarks;

		if (response_bookmarks.statusCode() == 200) {
			bookmarks = response_bookmarks.jsonPath().getList("contentId");
		} else {
			bookmarks = Collections.emptyList();
		}
		return bookmarks;
	}

	/**
	 * Unlikes all liked content.
	 */
	public void unlike_AllLikedContentForProfile() {
		List<String> likes = getContentIDLikedContentForProfile();
		if (!likes.isEmpty()) {
			String profileID = getProfileId();
			int size = likes.size();
			for (int i = 0; i < size; i++) {
				contentAPI.PutLikeContent(profileID, likes.get(i));
			}
		}

	}
	
	/**
	 * Removes bookmarks from all bookmarked content (content which is saved for
	 * later).
	 */
	public void unbookmark_AllContentForProfile() {
		List<String> bookmarks = getContentIDOfBookmarkedContentForProfile();
		if (!bookmarks.isEmpty()) {
			String profileID = getProfileId();
			int size = bookmarks.size();
			for (int i = 0; i < size; i++) {
				contentAPI.PutBookmarkContent(profileID, bookmarks.get(i));
			}
		}

	}

	/*----- Functions based on Moments API-------*/

	/**
	 * Returns total number of moments created for primary user profile.
	 * 
	 * @return Total number of moments created for primary user profile.Value will
	 *         be int.
	 */
	public int getSavedMomentsForProfile() {
		String contactId = getProfileId();
		int noOfMomentsInitial;
		Response response = momentsLogAPI.getMomentsForProfile(contactId);
		List<String> momentsList_PrimaryProfile;

		if (response.statusCode() == 200) {
			momentsList_PrimaryProfile = response.jsonPath().getList("id");
		} else {
			momentsList_PrimaryProfile = Collections.emptyList();
		}

		noOfMomentsInitial = momentsList_PrimaryProfile.size();
		return noOfMomentsInitial;

	}

	/**
	 * Returns all Moments created for primary user profile as a Response.
	 * 
	 * @return All Moments as a Response (io.restassured.response.Response)
	 */
	public Response getResponseSavedMoments() {
		String contactID = getProfileId();
		return momentsLogAPI.getMomentsForProfile(contactID);
	}

	/**
	 * Returns ids of saved moments as List.
	 * 
	 * @return ids of saved moments.Value will be List of Strings.
	 */
	public List<String> getIDsOfSavedMoment() {
		String contactID = getProfileId();
		Response response = momentsLogAPI.getMomentsForProfile(contactID);
		List<String> ids_momentsList_PrimaryProfile;

		if (response.statusCode() == 200) {
			ids_momentsList_PrimaryProfile = response.jsonPath().getList("id");
		} else {
			ids_momentsList_PrimaryProfile = Collections.emptyList();
		}

		return ids_momentsList_PrimaryProfile;
	}

	/**
	 * Deletes all moments.
	 */
	public void deleteAllMomentsAssociatedWithProfile() {
		String contactID = getProfileId();
		List<String> ids_momentsList_PrimaryProfile = getIDsOfSavedMoment();

		for (int i = 0; i < ids_momentsList_PrimaryProfile.size(); i++) {
			momentsLogAPI.deleteMoment(ids_momentsList_PrimaryProfile.get(i));
		}

	}

	/**
	 * Returns last saved moment id.
	 * @param initial List of moment ids before a moment is created
	 * @param latest List of moment ids after a moment is created
	 * @return id of moment.Value will be String.
	 */
	public String getMomentIdLastSavedMoment(List<String> initial, List<String> latest) {
		latest.removeAll(initial);
		String momentId = latest.get(0);
		return momentId;

	}

	/**
	 * Returns note saved in moment by passing id of moment.
	 * @param res Complete moment Response
	 * @param momentId moment id of moment
	 * @return note.Value will be String.
	 */
	public String getNoteByMomentId(Response res, String momentId) {

		List<String> notes;
		String note = "";
		String json = res.getBody().asString();
		notes = com.jayway.jsonpath.JsonPath.parse(json).read("$..[?(@.id=='" + momentId + "')].note");
		note = notes.get(0).toString();
		return note;
	}

	/**
	 * Returns milestoneId saved in moment by passing id of moment.
	 * @param res Complete moment Response
	 * @param momentId moment id of moment 
	 * @return milestoneId.Value will be String.
	 */
	public String getMilestoneIDByMomentId(Response res, String momentId) {
		List<String> milestones;
		String milestone = "";
		String json = res.getBody().asString();
		milestones = com.jayway.jsonpath.JsonPath.parse(json).read("$..[?(@.id=='" + momentId + "')].milestoneId");
		milestone = milestones.get(0).toString();
		return milestone;
	}

	/**
	 * Returns imageUrl saved in moment by passing id of moment.
	 * @param res Complete moment Response
	 * @param momentId moment id of moment 
	 * @return imageUrl.Value will be String.
	 */
	public String getImageUrlByMomentId(Response res, String momentId) {
		List<String> imageURLS;
		String imageURL = "";
		String json = res.getBody().asString();
		imageURLS = com.jayway.jsonpath.JsonPath.parse(json).read("$..[?(@.id=='" + momentId + "')].imageUrl");
		imageURL = imageURLS.get(0).toString();
		return imageURL;
	}

	/**
	 * Returns babyHeight saved in moment by passing id of moment.
	 * @param res Complete moment Response
	 * @param momentId moment id of moment  
	 * @return babyHeight.Value will be String.
	 */
	public String getHeightByMomentId(Response res, String momentId) {
		List<String> heights;
		String height = "";
		String json = res.getBody().asString();
		heights = com.jayway.jsonpath.JsonPath.parse(json).read("$..[?(@.id=='" + momentId + "')].babyHeight");
		height = String.valueOf(heights.get(0));
		System.out.println("height as string: " + height);
		return height;
	}

	/**
	 * Returns babyWeight saved in moment by passing id of moment.
	 * @param res Complete moment Response
	 * @param momentId moment id of moment 
	 * @return babyWeight.Value will be String.
	 */
	public String getWeightByMomentId(Response res, String momentId) {
		List<String> weights;
		String weight = "";
		String json = res.getBody().asString();
		weights = com.jayway.jsonpath.JsonPath.parse(json).read("$..[?(@.id=='" + momentId + "')].babyWeight");
		weight = String.valueOf(weights.get(0));
		return weight;
	}

	/**
	 * Returns date saved in moment by passing id of moment.
	 * @param res Complete moment Response
	 * @param momentId moment id of moment 
	 * @return date.Value will be String.
	 */
	public String getDateByMomentId(Response res, String momentId) {
		List<String> dates;
		String date = "";
		String json = res.getBody().asString();
		dates = com.jayway.jsonpath.JsonPath.parse(json).read("$..[?(@.id=='" + momentId + "')].date");
		date = dates.get(0).toString();
		return date;
	}

	/*----- Functions based on GroupAdvice API-------*/

	/**
	 * Returns all group names of type "COMMUNITY".
	 * 
	 * @return List of group names of type "COMMUNITY".Value will be List of Strings.
	 */
	public List<String> getAllCommunityGroupNames() {
		Response response_AllGroups = groupAdviceAPI.getAllGroups("COMMUNITY");
		List<String> allGroupsNames;

		if (response_AllGroups.statusCode() == 200) {
			allGroupsNames = response_AllGroups.jsonPath().getList("value.listname");
		} else {
			allGroupsNames = Collections.emptyList();
		}

		return allGroupsNames;
	}

	/**
	 * Returns aiabase_typeofgroup value for a groupType "COMMUNITY".
	 * 
	 * @return aiabase_typeofgroup. Value will be String.
	 */
	public String getTypeOfGroupIdForCommunityGroup() {
		Response response_AllGroups = groupAdviceAPI.getAllGroups("COMMUNITY");
		String json = response_AllGroups.getBody().asString();
		List<Integer> typeOfGroupIdForCommunityGroup;
		String st = "";

		if (response_AllGroups.statusCode() == 200) {
			typeOfGroupIdForCommunityGroup = com.jayway.jsonpath.JsonPath.parse(json).read("$..aiabase_typeofgroup");
		} else {
			typeOfGroupIdForCommunityGroup = Collections.emptyList();
		}
		if (typeOfGroupIdForCommunityGroup.size() > 0) {
			st = Integer.toString(typeOfGroupIdForCommunityGroup.get(0));
		}

		return st;

	}

	/**
	 * Returns all group names of type "COMMUNITY" that user is part of.
	 * 
	 * @return List of all "COMMUNITY" group names. Value will be List of Strings
	 */
	public List<String> getUserCommunityGroupNames() {
		Response response_groups_user = groupAdviceAPI.getGroupByUserID("COMMUNITY");
		List<String> Groups_User;

		if (response_groups_user.statusCode() == 200) {
			Groups_User = response_groups_user.jsonPath().getList("value.aiabase_Topic.aiabase_name");
			System.out.println("Groups of user: "+Groups_User);
		} else {
			Groups_User = Collections.emptyList();
		}
		return Groups_User;
	}

	/**
	 * Returns list of ids of "COMMUNITY" type groups that user is part of.
	 * 
	 * @return List of ids of "COMMUNITY" type groups that user is part of.Value
	 *         will be List of Strings
	 */
	public List<String> getUserGroupIds() {
		Response response_groups_user = groupAdviceAPI.getGroupByUserID("COMMUNITY");
		List<String> listIds_Groups_User;

		if (response_groups_user.statusCode() == 200) {
			listIds_Groups_User = response_groups_user.jsonPath().getList("value.listid");
		} else {
			listIds_Groups_User = Collections.emptyList();
		}
		return listIds_Groups_User;
	}
	
	}
