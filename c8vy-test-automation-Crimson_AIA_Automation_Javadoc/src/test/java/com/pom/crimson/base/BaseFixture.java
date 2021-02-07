package com.pom.crimson.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.Arrays;
import java.util.Map;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.PushesFiles;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/** BaseFixture class contains functions which run before starting test and 
 * after test finishes execution. 
 * Before starting test, Appium Driver is launched on device 
 * and after test finishes Appium session is closed on device.
 * On test case start , application under test is launched on device and credentials are passed for user to login.
 * On test case finish, user is logged out of application and application under test is closed, so that each test launches independent of other tests. 
 * Configuration data like deviceName,platformName,Activity name, Package name etc is fetched from properties and testng.xml
 * It also contains methods to check if Appium service is running,if Appium installed from CLI mode.
 * Also contains functions to seed/flush images from android device.
 * 
 * @author Jaspreet Kaur Chagger
 * @author Sivaprakash Selvaraj
 */
public class BaseFixture {
	
	protected static ThreadLocal <Properties> prop=new ThreadLocal<Properties>();
	protected static ThreadLocal<AppiumDriver<MobileElement>> driver=new ThreadLocal<AppiumDriver<MobileElement>>();
	protected static ThreadLocal <String> platformName=new ThreadLocal<String>();
	protected static ThreadLocal <String> deviceName=new ThreadLocal<String>();
	protected static ThreadLocal <String> screenshotCaptureStage=new ThreadLocal<String>();
	protected static ThreadLocal <String> passScreenshotReq=new ThreadLocal<String>();
	
	/*------------------------------------getter setter--------------------------------------*/
	
	/** Get screenshotCaptureStage
	 * @return the screenshotCaptureStage
	 */
	public String getScreenshotCaptureStage() {
		return screenshotCaptureStage.get();
	}
	
	/** Set screenshotCaptureStage
	 * @param screenshotCaptureStage the screenshotCaptureStage to set
	 */
	public void setScreenshotCaptureStage(String screenshotCaptureStage) {
		BaseFixture.screenshotCaptureStage.set(screenshotCaptureStage);
	}
	
	/** Set passScreenshotFlag
	 * @param passScreenshotFlag the passScreenshotFlag to set
	 */
	public void setPassScreenshotFlag(String passScreenshotFlag) {
		BaseFixture.passScreenshotReq.set(passScreenshotFlag);
	}	
	
	/** Get passScreenshotFlag
	 * @return the passScreenshotFlag
	 */
	public String getPassScreenshotFlag() {
		return passScreenshotReq.get();
	}	
	
	/** Get AppiumDriver
	 * @return the AppiumDriver
	 */
	public AppiumDriver<MobileElement> getDriver()
	{
		return driver.get();
	}
	
	/** Set AppiumDriver
	 * @param driver2 the AppiumDriver to set
	 */
	public void setDriver(AppiumDriver<MobileElement> driver2)
	{
		driver.set(driver2);
	}
	
	/** Get Properties
	 * @return the Properties
	 */
	public Properties getProperties()
	{
		return prop.get();
	}
	
	/** Set Properties
	 * @param props2 the Properties to set
	 */
	public void setProperties(Properties props2)
	{
		prop.set(props2);
	}

	/** Get Platform Name
	 * @return the Platform name
	 */
	public String getPlatformName()
	{
		return platformName.get();
	}
	
	/** Set Platform name
	 * @param platformName2 the platform Name to set
	 */
	public void setPlatformName(String platformName2)
	{
		platformName.set(platformName2);
	}
	
	/** Get Device name
	 * @return the Device name
	 */
	public String getDeviceName()
	{
		return deviceName.get();
	}
	
	/** Set Device name
	 * @param deviceName2 the Device Name to set
	 */
	public void setDeviceName(String deviceName2)
	{
		deviceName.set(deviceName2);
	}
	
	
	/*------------------------------------Base Fixture Methods--------------------------------------*/
	
	/** Launches AppiumDriver on mobile device.
	 * This method will be executed first before any test.
	 * Parameters passed in this method are passed from testng.xml
	 * @param emulator  true if device is emulator (Optional parameter,Android Only, Values: true/false)
	 * @param platformName Platform Name (Values: Android/iOS)
	 * @param platformVersion Platform Version
	 * @param deviceName Device name
	 * @param systemPort  unique system port for each parallel session (Optional parameter,Android Only)
	 * @param chromeDriverPort the chromedriver port (if using webviews or chrome) (Optional parameter,Android Only)
	 * @param wdaLocalPort unique system port for each parallel session (Optional parameter,iOS Only)
	 * @param webkitDebugProxyPort port for accessing web views on real iOS device (Optional parameter,iOS Only)
	 * @param screenshotCaptureStage screenshotCaptureStage controls the screenshot functionality of the testing, “1” for Taking screenshot at Test Step level and “2” for taking screenshot at test case level.
	 * @param passScreenshotFlag  If screenshots are required for Passed testcases, set it to "Y" 
	 * @param context ITestContext
	 * @throws Exception throws all exception
	 */
	@Parameters({"emulator","platformName","platformVersion","deviceName","systemPort","chromeDriverPort","wdaLocalPort","webkitDebugProxyPort","screenshotCaptureStage", "passScreenshotFlag"})
	@BeforeTest()
	public void beforeTest(@Optional("androidOnly")String emulator, String platformName, String platformVersion, String deviceName, 
			  @Optional("androidOnly")String systemPort, @Optional("androidOnly")String chromeDriverPort, 
			  @Optional("iOSOnly")String wdaLocalPort, @Optional("iOSOnly")String webkitDebugProxyPort,
			  String screenshotCaptureStage, String passScreenshotFlag, ITestContext context) throws Exception {

		Properties prop=new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/project.properties");
		prop.load(fs);
		setProperties(prop);
			AppiumDriver<MobileElement> driver;
			
			try {
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
				
			setPlatformName(platformName);
			setDeviceName(deviceName);
			setScreenshotCaptureStage(screenshotCaptureStage);
			setPassScreenshotFlag(passScreenshotFlag);
			
			switch(platformName) {
			case "Android":
				cap.setCapability("automationName", getProperties().getProperty("androidAutomationName"));
				cap.setCapability("appPackage",getProperties().getProperty("appPackage"));
				cap.setCapability("appActivity",getProperties().getProperty("appActivity"));
				cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
				if(emulator.equalsIgnoreCase("true")) {
					cap.setCapability("avd", deviceName);
					cap.setCapability("avdLaunchTimeout", 120000);
				}
				cap.setCapability("systemPort",systemPort);
				cap.setCapability("chromeDriverPort",chromeDriverPort);
				cap.setCapability("app", getProperties().getProperty("apkPath"));
				cap.setCapability(MobileCapabilityType.NO_RESET,false);
				cap.setCapability(MobileCapabilityType.FULL_RESET, false);
				cap.setCapability("unicodeKeyboard", true);
				cap.setCapability("resetKeyboard", true);
				
				driver=new AndroidDriver<MobileElement>(new URL(getProperties().getProperty("hubURL")),cap);
				break;
			/*case "iOS":
				cap.setCapability("automationName", getProperties().getProperty("iOSAutomationName"));
				cap.setCapability("usePrebuiltWDA","true");
				cap.setCapability("derivedDataPath","/Users/jaspreetchagger/Library/Developer/Xcode/DerivedData/WebDriverAgent-ewatixzcpuknkndrtzdvtgavzoli");
				cap.setCapability("bundleId", getProperties().getProperty("iOSBundleId"));//
				cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
				//cap.setCapability("wdaLocalPort", wdaLocalPort);//
				//cap.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);//
				cap.setCapability("app", "/Users/jaspreetchagger/Library/Developer/Xcode/DerivedData/CrimsonApp-daairteyekdkfuabyfguidkiyjwc/Build/Products/Debug-iphonesimulator/CrimsonApp.app");
				cap.setCapability("toggleSoftwareKeyboard","true");
				//cap.setCapability(MobileCapabilityType.NO_RESET,"true");
				//cap.setCapability(MobileCapabilityType.FULL_RESET, "false");
				
				driver=new IOSDriver<MobileElement>(new URL(getProperties().getProperty("hubURL")),cap);
				break;*/
				default:
					throw new Exception("InvalidPlatformName: "+platformName);
			}
			setDriver(driver);
			

			} catch(Exception e){
				e.printStackTrace();
			}
			getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		      
		     // System.out.println("Before Test executed");
		      context.setAttribute("AppuimDriver", getDriver());
		      context.setAttribute("Platform", getPlatformName());
		      context.setAttribute("Device", getDeviceName());   
		      context.setAttribute("screenshotCaptureStage", screenshotCaptureStage);
		      context.setAttribute("passScreenshotFlag", passScreenshotFlag);
	}
	
	/**
	 * Launches Application under Test on device
	 */
	@BeforeMethod()
	public void beforeMethod()
	{
		getDriver().launchApp();
	}
	
	/**
	 * Closes Application under Test on device
	 * @throws Exception throws all exception
	 */
	@AfterMethod()
	public void afterMethod() throws Exception  {
		getDriver().closeApp();
	}
	
	
	/**
	 * Closes driver session device
	 */
	@AfterTest()
	public void afterTest() {
		if (getDriver()!=null)
			getDriver().quit();
		
	}
	
	/*----------------------------Appium Service methods------------------------*/
	/**
	 * Checks if Appium server is running on port
	 * Use this method in case Appium CLI version is used
	 * @param port Appium server port to check
	 * @return boolean 
	 * true: if server is running
	 * false: if server is not running
	 * @throws Exception throws all exception
	 */
	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	/**  
	 * 
	 * @return AppiumDriverLocalService
	 */
	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}

	/**
	 * Get AppiumDriverLocalService
	 * @return AppiumDriverLocalService
	 */
	public AppiumDriverLocalService getAppiumService() {
		return AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("C:\\Users\\jaspreetchagger\\node_modules\\appium\\build\\lib\\main.js"))
						.usingPort(4723).withArgument(GeneralServerFlag.SESSION_OVERRIDE)
						.withLogFile(new File("ServerLogs/server.log")));
	}

	/**
	 * Captures Appium Server logcat logs
	 * @param  driver AppiumDriver instance
	 * @param testCaseName Test case name
	 * @throws Exception throws all exception
	 */
	public void captureLog(AppiumDriver<MobileElement> driver, String testCaseName) throws Exception {
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH-mm-ss");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		String logPath = "D:\\automation_capture\\";
		// test.log(Status.INFO,driver.getSessionId() + ": Saving device log...");
		List<LogEntry> logEntries = driver.manage().logs().get("logcat").filter(Level.ALL);
		File logFile = new File(logPath + reportDate + "_" + testCaseName + ".txt");
		PrintWriter log_file_writer = new PrintWriter(logFile);
		log_file_writer.println(logEntries);
		log_file_writer.flush();
		// log.info(driver.getSessionId() + ": Saving device log - Done.");
	}

	//Not in use methods
	
	/**
	 * Seeds 6 sample images to emulator at /sdcard/DCIM/Pictures location
	 * @throws IOException throws IOException
	 */
	public void seedImages() throws IOException
	{
		 String packageName= "com.aia.rn.th.dp01";
		 
	       // String grantSDcardPermission= "adb shell pm grant " + packageName +" android.permission.WRITE_EXTERNAL_STORAGE";
	        try {
	           // Runtime.getRuntime().exec(grantSDcardPermission);
	            List<String> grantWritePermissionArgs = Arrays.asList(packageName,
	        "android.permission.WRITE_EXTERNAL_STORAGE");
	            List<String> grantReadPermissionArgs = Arrays.asList(packageName,
	        	        "android.permission.READ_EXTERNAL_STORAGE");
	            
	    Map<String, Object> Cmd1 = ImmutableMap.of(
	        "command", "pm grant",
	        "args", grantWritePermissionArgs
	    );
	    
	    Map<String, Object> Cmd2 = ImmutableMap.of(
		        "command", "pm grant",
		        "args", grantReadPermissionArgs
		    );
	    getDriver().executeScript("mobile: shell", Cmd1);
	    getDriver().executeScript("mobile: shell", Cmd2);

	        } catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO, "not able to grant permission ");
				ExtentReportManager.getTest().log(Status.INFO, e.getMessage());
	            e.printStackTrace();
	        }	
		
		try {
		// this will seed 6 images on to emulator from assets folder
		File assetDir = new File(System.getProperty("user.dir")+"/src/test/resources/Assets");
		for(int i=1;i<7;i++)
		{
		File img = new File(assetDir.getCanonicalPath(), "babyImage"+i+".jpeg");
		// actually push the file
		((PushesFiles) getDriver()).pushFile("/sdcard/DCIM/Pictures" + "/" + img.getName(), img);

		//((PushesFiles) getDriver()).pushFile("/mnt/sdcard/Pictures" + "/" + img.getName(), img);
		}
		} catch(Exception e)
		{
			ExtentReportManager.getTest().log(Status.INFO, "not able to add image ");

			ExtentReportManager.getTest().log(Status.INFO, e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Seeds sample image to emulator
	 * Image to be present in assets folder
	 * Image name to be provided should be appended with correct extension .jpeg,.png
	 * @param imageTitle image name
	 * @throws IOException
	 */
	protected void seedSingleImageByText(String imageTitle) throws IOException
	{
		// this will seed 1 image on to emulator from assets folder based on image name provided
		File assetDir = new File(System.getProperty("user.dir")+"/src/test/resources/Assets");
		File img = new File(assetDir.getCanonicalPath(), imageTitle);
		// actually push the file
		((PushesFiles) getDriver()).pushFile("/mnt/sdcard/Pictures" + "/" + img.getName(), img);
		
	}
	
	/**
	 * Flushes image from emulator from /mnt/sdcard/Pictures.
	 * Image name to be provided should be appended with correct extension .jpeg,.png
	 * @param imageName image name
	 * @throws IOException
	 */
	protected void flushImagesFromPhone(String imageName) throws IOException
	{
		// run this method after seedimages method to clean test data
		String ANDROID_PHOTO_PATH = "/mnt/sdcard/Pictures";
		List<String> removePicsArgs = Arrays.asList( ANDROID_PHOTO_PATH + "/"+imageName);
        Map<String, Object> removePicsCmd = ImmutableMap
            .of("command", "rm", "args", removePicsArgs);
        getDriver().executeScript("mobile: shell", removePicsCmd);
	}

}
	
	


