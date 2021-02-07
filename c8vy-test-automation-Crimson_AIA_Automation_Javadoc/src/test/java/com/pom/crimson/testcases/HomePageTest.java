package com.pom.crimson.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.pages.HomePage;

/**
 * This class contains test methods to Smoke test Groups and Specialist chat modules 
 *
 * 
 * @author Indirani
 */
public class HomePageTest extends BaseFixture {

	
	HomePage homePage;
	
	@BeforeMethod()
	public void beforeMethod()
	{
		homePage=new HomePage(getDriver(), getPlatformName());
	} 
	
	//Navigation Test Cases
	
	@Test(testName="Verify navigation link for Smoke Flow",description="Navigating to Specialist Chat, Groups and Live Events scenario")
	public void verifyNavigationToDiscoverIntrestPage() throws Exception
	{
		homePage.validateSmokeFlow();
		
	}
	
	@Test(testName="Verify Specialist Chat functionality",description="Navigate to Virtual Hospital via Specialist Chat")
	public void verifySpecialistChatPage() throws Exception
	{
		homePage.goToSpecialistChatHomePage();
	}

	/*@Test(testName="Verify Join Group functionality",description="User joined the new group")
	public void verifyNewJoinGroups() throws Exception
	{
		homePage.createNewGroup();
	}*/
	
	
	@Test(testName="Verify joined Group functionality",description="User should not join the group already joined")
	public void verifyChatGroups() throws Exception
	{
		homePage.againtrytoJoinexistingjoinedGroup();
	}
	
	@AfterMethod()
	public void afterMethodLocal() {
		homePage.logout();
	}
}
