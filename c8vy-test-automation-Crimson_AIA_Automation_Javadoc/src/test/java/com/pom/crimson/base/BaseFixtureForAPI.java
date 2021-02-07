package com.pom.crimson.base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.remote.MobileCapabilityType;

/** BaseFixtureAPI class contains functions which run before starting API test and 
 * after test finishes execution. 
 * This is an extension of BaseFixture class where the code related to APPIUM, Web driver initialization  
 * are removed as they are not applicable for API test.
 * Configuration data like deviceName,platformName,Activity name, Package name etc is fetched from properties and testng.xml
 * 
 * 
 * @author Sivaprakash.Selvaraj
 */
public class BaseFixtureForAPI extends BaseFixture {

	/** This method will be executed first before any API test.
	 * Parameters passed in this method are passed from testng.xml
	 * @param emulator is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param platformName Platform Name (Values: API)
	 * @param platformVersion is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param deviceName is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param systemPort  is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param chromeDriverPort is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param wdaLocalPort is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param webkitDebugProxyPort is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param screenshotCaptureStage is kept to maintain the compatibility of overriding method, not applicable for API Testing
	 * @param passScreenshotFlag is kept to maintain the compatibility of overriding method , not applicable for API Testing
	 * @param context ITestContext
	 */	
	@Override
	@Parameters({ "emulator", "platformName", "platformVersion", "deviceName", "systemPort", "chromeDriverPort",
		"wdaLocalPort", "webkitDebugProxyPort","screenshotCaptureStage","passScreenshotFlag" })	
	@BeforeTest()
	public void beforeTest(@Optional("androidOnly") String emulator, String platformName, @Optional("androidOnly") String platformVersion,
			String deviceName, @Optional("androidOnly") String systemPort,
			@Optional("androidOnly") String chromeDriverPort, @Optional("iOSOnly") String wdaLocalPort,
			@Optional("iOSOnly") String webkitDebugProxyPort, String screenshotCaptureStage, String passScreenshotFlag, 
			ITestContext context) throws Exception {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/project.properties");
		prop.load(fs);
		
		setProperties(prop);
		setPlatformName(platformName);
		setDeviceName(deviceName);
		setScreenshotCaptureStage(screenshotCaptureStage);
		setPassScreenshotFlag(passScreenshotFlag);
		
		context.setAttribute("Platform", getPlatformName());
		context.setAttribute("Device", getDeviceName());
		context.setAttribute("screenshotCaptureStage", screenshotCaptureStage);
		context.setAttribute("passScreenshotFlag", passScreenshotFlag);
	}
	
	/**
	 * Overridden to exclude the mobile driver launch during API Test.
	 */	
	@Override
	@BeforeMethod()
	public void beforeMethod() {
		System.out.println("In API Before Method ");
	}

	/**
	 * Overridden to exclude the mobile app exit statements during API Test.
	 */		
	@AfterMethod()
	public void afterMethod() throws Exception {
		System.out.println("In API after Method");
	}

	/**
	 * Overridden to exclude the mobile driver session close statements during API Test.
	 */			
	@AfterTest()
	public void afterTest() {
		System.out.println("In API after Test");
	}	
}
