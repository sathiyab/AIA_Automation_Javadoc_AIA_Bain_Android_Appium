package com.pom.crimson.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.util.ExtentReportManager;

public class TestListenerForAPI extends TestListener {

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportManager.getTest().pass("Test passed");
		ExtentReportManager.createInstance().flush();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("On failure executing - API");
		ExtentReportManager.getTest().fail("Test Failed");
		ExtentReportManager.getTest().fail(result.getThrowable());
		ExtentReportManager.createInstance().flush();		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {	
		ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped");
		ExtentReportManager.createInstance().flush();		
	}	
	
}
