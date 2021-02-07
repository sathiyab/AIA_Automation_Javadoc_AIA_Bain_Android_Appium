package com.pom.crimson.testcases.api;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.api.MasterDataControllerAPI;
import com.pom.crimson.base.BaseFixtureForAPI;

import io.restassured.response.Response;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test master-data-controller API
 *
 * 
 * @author VenkatRao.Kulkarni
 * @author Sivaprakash.Selvaraj
 */
public class MasterDataControllerAPITest extends BaseFixtureForAPI {

	MasterDataControllerAPI MDC;
	LogAssert logAssert;

	/**
	 * Method to initialize the respective Rest Assured API objects.
	 */	
	@BeforeClass()
	public void beforeClass() {
		MDC = new MasterDataControllerAPI();
	}

	/**
	 * Method to initialize Assert objects before each Test Method.
	 *  
	 */	
	@BeforeMethod()
	public void beforeMethodLocal() {
		logAssert = new LogAssert();
	}

	@Test(priority = 1, enabled=true, testName = "Master Data Controller - Get all Active Benefits", description = "Master Data Controller - Get all Active Benefits")
	public void verifyGetActiveBenefitsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /activebenefits");

		Response resp = MDC.GetActivebenefits();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		List<String> BenefitList = resp.jsonPath().getList("aiabase_name");

		logAssert.assertTrue(BenefitList.size() > 0, "Benefits list retrieved is not empty.");

	}

	@Test(priority = 2, enabled=true, testName = "Master Data Controller - Get App Settings", description = "Master Data Controller - Get App Settings")
	public void verifyGetAppSettingsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /appsettings");

		Response resp = MDC.GetAppSettings();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> Appsettings = resp.jsonPath().getList("aiabase_minversion");
		logAssert.assertTrue(Appsettings.size() > 0, "App Settings retrieved should not be empty.");

	}

	@Test(priority = 3, enabled=true, testName = "Master Data Controller - Get Arche Types", description = "Master Data Controller - Get Arche Types")
	public void verifyGetArcheTypesAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "Master Data Controller - Get Arche Types API test");
		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /archetypes");

		Response resp = MDC.GetArcheTypes();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> Archetypes = resp.jsonPath().getList("aiabase_name");
		logAssert.assertTrue(Archetypes.size() > 0, "Archetypes list retrieved should not be empty.");

	}

	@Test(priority = 4, enabled=true, testName = "Master Data Controller - Get Countries", description = "Master Data Controller - Get Countries")
	public void verifyGetCountriesAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /countries");

		Response resp = MDC.GetCountries();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
		
		List<String> Countries = resp.jsonPath().getList("statuscode");
		logAssert.assertTrue(Countries.size() > 0, "Countries list retrieved should not be empty.");

	}

	@Test(priority = 5, enabled=true, testName = "Master Data Controller - Get Interests", description = "Master Data Controller - Get Interests")
	public void verifyGetInterestsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /interests");

		Response resp = MDC.GetInterests();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");
	
		List<String> Interests = resp.jsonPath().getList("aiabase_name");
		logAssert.assertTrue(Interests.size() > 0, "Interests list retrieved should not be empty.");
	}

	@Test(priority = 6, enabled=true, testName = "Master Data Controller - Get Provinces", description = "Master Data Controller - Get Provinces")
	public void verifyGetProvincesAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /provinces");

		Response resp = MDC.GetProvinces();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");

		List<String> Provinces = resp.jsonPath().getList("aiabase_directioncode");
		logAssert.assertTrue(Provinces.size() > 0, "Provinces list retrieved should not be empty.");
	}

	@Test(priority = 7, enabled=true, testName = "Master Data Controller - Get Relationships", description = "Master Data Controller - Get Relationships")
	public void verifyGetRelationshipsAPI() throws Exception {

		ExtentReportManager.getTest().log(Status.INFO, "API Endpoint - /relationships");

		Response resp = MDC.GetRelationships();
		logAssert.assertEquals(resp.statusCode(), 200, "Verify if the Response code is \"200\"");

		List<String> Relationships = resp.jsonPath().getList("msemr_name");
		logAssert.assertTrue(Relationships.size() > 0, "Relationship list retrieved should not be empty.");
	}
}
