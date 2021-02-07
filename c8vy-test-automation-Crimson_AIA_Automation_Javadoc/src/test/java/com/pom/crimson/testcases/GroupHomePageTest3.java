package com.pom.crimson.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BaseFixture;
import com.pom.crimson.listeners.LogAssert;
import com.pom.crimson.pages.GroupHomePage;
import com.pom.crimson.pages.LoginPage;
import com.pom.crimson.util.ExtentReportManager;

/**
 * This class contains test methods to test Groups and Specialist chat modules 
 *
 * 
 * @author Indirani
 */
public class GroupHomePageTest3 extends BaseFixture {

	LogAssert logAssert;
	GroupHomePage grphomePage;
	LoginPage loginPage;
	
	
	@BeforeMethod()
	public void beforeMethod()
	{
		loginPage=new LoginPage(getDriver(),getPlatformName());
		logAssert = new LogAssert();
	}
	
	
	
	//TC-03 Verify already joined group functionality
		@Test(enabled=true, priority = 3,testName="Verify joined Group functionality",description="User should not join the group already joined")
		public void verifyChatGroups() throws InterruptedException, IOException
		{
			loginPage.Takepic_Recvideo();
			if (loginPage.isElementDisplayed("Skip"))
		    {
			 loginPage.skipIntroScreens();
		      
		        }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
			   	
			loginPage.LoginWithEmail();	
			ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
			GroupHomePage GP = new GroupHomePage(getDriver(), getPlatformName());
			GP.groupBtn_click();
			logAssert.assertTrue(GP.isElementDisplayed("Groups"),"Groups Page is displayed");
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
			GP.groupdiscoverBtn_click();
			logAssert.assertTrue(GP.isElementDisplayed("All groups"),"All groups Page is displayed");
			GP.selectGrp();			
			ExtentReportManager.getTest().log(Status.INFO, "Clicking on anyone of the Groups");
			logAssert.assertTrue(GP.isElementDisplayed("Pregnancy"),"Groups Name is displayed");
			GP.joinGroupBtn_click();
			GP.groupdiscoverBtn_click();
			logAssert.assertTrue(GP.isElementDisplayed("All groups"),"All groups Page is displayed");
			GP.selectGrp();			
			logAssert.assertTrue(GP.isElementDisplayed("Already joined"),"Already joined");
			
		}
	
	//TC-04 Verify Member Group functionality
		@Test(enabled=true, priority = 4,testName="Verify Member Group functionality",description="User validate Member Group")
		public void verifyMemberGroups() throws InterruptedException, IOException
		{
			loginPage.Takepic_Recvideo();
			if (loginPage.isElementDisplayed("Skip"))
		    {
			 loginPage.skipIntroScreens();
		      
		        }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
			   	
			loginPage.LoginWithEmail();	
			ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
			GroupHomePage GP = new GroupHomePage(getDriver(), getPlatformName());
			GP.groupBtn_click();
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
			logAssert.assertTrue(GP.isElementDisplayed("Groups"),"Groups Page is displayed");
			GP.selectGrp();	
			logAssert.assertTrue(GP.isElementDisplayed("Pregnancy"),"Pregnancy is displayed");
			Thread.sleep(6000);
			GP.test();
			Thread.sleep(3000);
			GP.clickgrpInfoLnk();
			logAssert.assertTrue(GP.isElementDisplayed("Members"),"Members are displayed");
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Members Link");
			GP.memberHeaderTxt();
			ExtentReportManager.getTest().log(Status.INFO, "Verifying Member Header Text");
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Close Button");
			GP.cancelBtn();
		
		}
	
	//TC-05 Verify Group Info functionality
		@Test(enabled=true, priority = 5,testName="Verify Group Info functionality",description="User validate Group Info")
		public void verifyGroupInfo() throws InterruptedException, IOException
		{
			loginPage.Takepic_Recvideo();
			if (loginPage.isElementDisplayed("Skip"))
		    {
			 loginPage.skipIntroScreens();
		      
		        }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
			loginPage.LoginWithEmail();	
			ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
			GroupHomePage GP = new GroupHomePage(getDriver(), getPlatformName());
			GP.groupBtn_click();
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
			logAssert.assertTrue(GP.isElementDisplayed("Groups"),"Groups Page is displayed");
			GP.selectGrp();	
			logAssert.assertTrue(GP.isElementDisplayed("Pregnancy"),"Pregnancy is displayed");
			Thread.sleep(6000);
			GP.test();
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Action Button");
			GP.groupInfo();
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Group Info");
			ExtentReportManager.getTest().log(Status.INFO, "Verifying Group Info Header Text");
			GP.groupInfoTxt();	
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Close Button");
			GP.cancelBtn();
		}
		
		
	//TC-06 Verify Leave Group functionality
		@Test(enabled=true, priority = 6,testName="Verify Leave Group functionality",description="User validate Member Group")
		public void verifyMembersGroup() throws InterruptedException, IOException
		{
			loginPage.Takepic_Recvideo();
			if (loginPage.isElementDisplayed("Skip"))
		    {
			 loginPage.skipIntroScreens();
		      
		        }
		    else
		    {
		    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
		    }
			   	
			loginPage.LoginWithEmail();	
			ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
			GroupHomePage GP = new GroupHomePage(getDriver(), getPlatformName());
			GP.groupBtn_click();
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
			logAssert.assertTrue(GP.isElementDisplayed("Groups"),"Groups Page is displayed");
			GP.selectGrp();	
			logAssert.assertTrue(GP.isElementDisplayed("Pregnancy"),"Pregnancy is displayed");
			Thread.sleep(6000);
			GP.test();
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Action Button");
			Thread.sleep(10000);
			ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Leave Group Button");
			GP.leaveGrp();
			logAssert.assertTrue(GP.isElementDisplayed("Leave group"),"Leave group is displayed");
			GP.leaveGrp();
			logAssert.assertTrue(GP.isElementDisplayed("Leave group"),"Leave group is displayed");

		}

	//TC-07 Verify Invite functionality
	@Test(enabled=true, priority = 7,testName="Verify Invite functionality",description="User validate Shared Friends Group")
	public void verifySharedFriendsGroup() throws InterruptedException, IOException
	{
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	      
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
		   	
		loginPage.LoginWithEmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		GroupHomePage GP = new GroupHomePage(getDriver(), getPlatformName());
		GP.groupBtn_click();
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		logAssert.assertTrue(GP.isElementDisplayed("Groups"),"Groups Page is displayed");
		GP.groupdiscoverBtn_click();
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Discover new group");
		logAssert.assertTrue(GP.isElementDisplayed("All groups"),"All groups Page is displayed");
		GP.selectGrp();			
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Groups icon");
		Thread.sleep(10000);
		GP.actionBtn();
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on Action Button");
		Thread.sleep(10000);
		GP.invfriendsGrp();
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Invite Friends to Group Header Text");
		GP.sharefriendsHeaderTxt();
		ExtentReportManager.getTest().log(Status.INFO,  "Clicking on invite message through copy button");
		GP.copybtn();
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Back Button");
		GP.backBtn();
	}
		
	
	//TC-08 Redeem Reward functionality
	@Test(enabled=true, priority = 8,testName="Verify Redeem Reward functionality",description="User validate Redeem Reward")
	public void verifyRedeemReward() throws InterruptedException, IOException
	{
		loginPage.Takepic_Recvideo();
		if (loginPage.isElementDisplayed("Skip"))
	    {
		 loginPage.skipIntroScreens();
	      
	        }
	    else
	    {
	    	logAssert.assertTrue(loginPage.isElementDisplayed("Skip"),"Intro Screen should be displayed successfully");
	    }
		   	
		loginPage.LoginWithEmail();	
		ExtentReportManager.getTest().log(Status.INFO, "Waiting for Profile to locate");
		GroupHomePage GP = new GroupHomePage(getDriver(), getPlatformName());
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Redeem Reward Button");
		GP.redeemRewardbtn();
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Header Text");
		GP.redeemRewardTxt();
		ExtentReportManager.getTest().log(Status.INFO, "Verifying Rewards Page Question Header Text");
		GP.redeemRewardFAQ();
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Terms and Conditions text");
		GP.termsConditionScroll();
		ExtentReportManager.getTest().log(Status.PASS, "Verified Redeem Reward Navigation Flow");
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Profile Button");
		GP.profileBtn();
		GP.redeemRewardHeaderTxtClick();		
		ExtentReportManager.getTest().log(Status.INFO, "Scrolling into Terms and Conditions text");
		GP.rewardsscroll();
	}
	
	

	
}
