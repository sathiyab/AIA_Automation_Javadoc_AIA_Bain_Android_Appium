package com.pom.crimson.listeners;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.pom.crimson.util.ExtentReportManager;
import org.testng.annotations.Test;
import com.pom.crimson.util.ScreenshotManager;

/** TestNG listener used to perform certain actions like taking screenshot, 
 * add logging in Extent Report when a 
 * test case is failed/passed/skipped.
 *
 */
public class TestListener  implements ITestListener {
	
	private WebDriver driver = null;
	private ExtentTest test=null;
	private String platformName=null;
	private String deviceName=null;
	private String screenshotCaptureStage=null;
	private String passScreenshotFlag=null;
	
	/** on Test case failure,adds logging in extent report.
	 * Takes screenshot and captures exception.
	 * @param result ITestResult
	 */
	public void onTestFailure(ITestResult result) {

		ITestContext context = result.getTestContext();
		driver = (WebDriver) context.getAttribute("AppuimDriver");
		test = (ExtentTest) context.getAttribute("Test");
		screenshotCaptureStage = (String) context.getAttribute("screenshotCaptureStage");
		platformName = (String) context.getAttribute("Platform");
		passScreenshotFlag = (String) context.getAttribute("passScreenshotFlag");

		System.out.println("On failure executing");
		if (result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			
			if (platformName.equals("API") || !passScreenshotFlag.equals("Y") || screenshotCaptureStage.equals("1")) {
				ExtentReportManager.getTest().fail("Test Failed");
			} else {
				try {
					ExtentReportManager.getTest().fail("Test Failed",
							MediaEntityBuilder
									.createScreenCaptureFromBase64String(new String(ScreenshotManager.Capture(driver), StandardCharsets.US_ASCII))
									.build());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ExtentReportManager.getTest().fail(result.getThrowable());
			ExtentReportManager.createInstance().flush();

		}

	}

	/** Starts Test. 
	 * Fetches Test name and description in Extent report.
	 * Adds Platform name and author to Extent report.
	 * @param result ITestResult
	 */
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Starts - " + result.getMethod().getDescription());
		ITestContext context = result.getTestContext();
		 platformName=(String) context.getAttribute("Platform");
		 deviceName=(String) context.getAttribute("Device");
	
		 ExtentReportManager.startTest(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName(),result.getMethod().getDescription())
		.assignCategory(platformName + "_" + deviceName)
		.assignAuthor("Crimson QA Team");	

	}

	/** On Test Success, adds logging in extent report.
	 * Takes screenshot and embeds in Extent report in case  screenshotCaptureStage=1
	 * @param result ITestResult
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		// ExtentReportManager.getTest().log(Status.PASS, "Test Passed");

		ITestContext context = result.getTestContext();
		driver = (WebDriver) context.getAttribute("AppuimDriver");
		test = (ExtentTest) context.getAttribute("Test");
		screenshotCaptureStage = (String) context.getAttribute("screenshotCaptureStage");
		platformName = (String) context.getAttribute("Platform");
		

		System.out.println("On Success executing");
		if (platformName.equals("API") || screenshotCaptureStage.equals("1")) {
			ExtentReportManager.getTest().pass("Test Passed");
		} else {
			try {
				ExtentReportManager.getTest().pass("Test Passed",
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										new String(ScreenshotManager.Capture(driver), StandardCharsets.US_ASCII))
								.build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ExtentReportManager.createInstance().flush();
	}

	/** On Test Skipped, adds logging in extent report.
	 * @param result ITestResult
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		ITestContext context = result.getTestContext();
		driver = (WebDriver) context.getAttribute("AppuimDriver");
		test = (ExtentTest) context.getAttribute("Test");
		screenshotCaptureStage = (String) context.getAttribute("screenshotCaptureStage");
		platformName = (String) context.getAttribute("Platform");

		System.out.println("On Skipped executing");
		if (result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
		}

		if (platformName.equals("API") || screenshotCaptureStage.equals("1")) {
			ExtentReportManager.getTest().skip("Test Skipped");
		} else {
			try {
				ExtentReportManager.getTest().skip("Test Skipped",
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(
										new String(ScreenshotManager.Capture(driver), StandardCharsets.US_ASCII))
								.build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ExtentReportManager.getTest().skip(result.getThrowable());
		ExtentReportManager.createInstance().flush();

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {	
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentReportManager.createInstance().flush();	
	}

}
