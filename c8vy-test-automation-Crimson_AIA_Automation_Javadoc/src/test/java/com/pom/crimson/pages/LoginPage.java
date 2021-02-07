package com.pom.crimson.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * This is the Page Class for <b>Login</b> page in the mobile application.
 * <br>  
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */	
public class LoginPage extends BasePage{

	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public LoginPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Next') and @index='0']")
	private MobileElement buttonNext;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	private MobileElement buttonOK;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Done']")
	private MobileElement buttonDone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Email']")
	private MobileElement buttonContinueWithEmail;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement inputEmailID;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue']")
	private MobileElement buttonContinue;	
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement inputPassword;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Log in']")
	private MobileElement buttonLogin;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Skip']")
	private MobileElement linkSkip;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter your password']")
	private MobileElement txtEnterYourPassword;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Password']")
	private MobileElement txtApplePassword;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Facebook']")    
	private MobileElement buttonContinueWithFacebook;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Google']")
    private MobileElement buttonContinueWithGoogle;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue with Apple']")
    private MobileElement buttonContinueWithApple;
   
    @AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
    private MobileElement inputAppleID;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='Continue']")
	private MobileElement AppleLoginbuttonContinue;
	
    
    @AndroidFindBy(xpath="//android.widget.Button[@text=' Continue ']")
	private MobileElement ApplebuttonContinue;
    
    @AndroidFindBy(xpath="//android.widget.EditText[contains(@resource-id,'password_text_field') and @index='0']")
	private MobileElement ApplebuttonPassword;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text=' Sign In ']")
   	private MobileElement ApplebuttonSignIn;
    
	@AndroidFindBy(xpath="//android.widget.Button[@text='While using the app']")
	private MobileElement WhileUsingTheApp;
	    
	@AndroidFindBy(xpath="//android.widget.Button[@text='Allow']")
	private MobileElement Allow;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='ALLOW']")
	private MobileElement allow;
    
	/** 
	 * Taps on <b>App permission</b> requests while launching the app for the first time
	 *  
	 */
    public void Takepic_Recvideo()
    {
    	GenericFunctions.WaitForElement(driver, WhileUsingTheApp);
    	GenericFunctions.clickElement(driver, WhileUsingTheApp);
    	GenericFunctions.clickElement(driver, WhileUsingTheApp);
    	GenericFunctions.clickElement(driver, Allow);
    }
    
	/** 
	 * Taps on <b>Next</b> button in the Intro screen Carousel
	 *  
	 */	
	public void NavigateIntroScreen() {
		
		GenericFunctions.clickElement(driver, buttonNext);
		/*
		if (GenericFunctions.isElementDisplayed(buttonNext))
		{
		GenericFunctions.clickElement(driver, buttonNext);
		}
		else
		{
			ExtentReportManager.getTest().log(Status.FAIL, "Intro screens are not available");
			
		}
		*/
	}
	
	/** 
	 * Taps on <b>Done</b> button in the last slide of the Intro screen Carousel
	 *  
	 */		
	public void CompleteIntroScreenNavigation() {
		
		GenericFunctions.clickElement(driver, buttonDone);
		/*
		if (GenericFunctions.isElementDisplayed(buttonDone))
		{
		GenericFunctions.clickElement(driver, buttonDone);
		}
		else
		{
			ExtentReportManager.getTest().log(Status.FAIL, "Intro screens are not available");
		}
		*/
	}	
	
	/** 
	 * Taps on <b>Skip</b> button in the Intro screen Carousel
	 *  
	 */	
	public void skipIntroScreens() {
		
		GenericFunctions.clickElement(driver, linkSkip);
		/*
		if (GenericFunctions.isElementDisplayed(linkSkip))
		{
		GenericFunctions.clickElement(driver, linkSkip);
		}
		else
		{
			ExtentReportManager.getTest().log(Status.FAIL, "Intro screens are not available");
		}
		*/
	}		
	
	/** 
	 * The method will <b>Login</b> to the mobile app using the Email and Password Login method
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */	
	public void LoginWithEmail() throws InterruptedException{

		
		GenericFunctions.clickElement(driver, buttonContinueWithEmail);
		//GenericFunctions.sendKeys(driver, inputEmailID, "balaji.sridharan@mphasis.com");
		//GenericFunctions.sendKeys(driver, inputEmailID, "s.balaji80@gmail.com");
		GenericFunctions.sendKeys(driver, inputEmailID, Constants.EmailTestUser);
		GenericFunctions.clickElement(driver, buttonContinue);
		GenericFunctions.WaitForElement(driver, txtEnterYourPassword);
		//GenericFunctions.sendKeys(driver, inputPassword, "Welcome45!");
		//GenericFunctions.sendKeys(driver, inputPassword, "Winter05!");
		GenericFunctions.sendKeys(driver, inputPassword, Constants.EmailTestPassword);
		
		GenericFunctions.clickElement(driver, buttonLogin);
	}	
	
	/** 
	 * The method will <b>Login</b> to the mobile app using the Email and Password Login method, users parameters
	 *  
	 *  @param username email login method username
	 *  @param password email login method password
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */		
	public void LoginWithEmail(String username, String password) throws InterruptedException{
		
		GenericFunctions.clickElement(driver, buttonContinueWithEmail);
		//GenericFunctions.sendKeys(driver, inputEmailID, "balaji.sridharan@mphasis.com");
		GenericFunctions.sendKeys(driver, inputEmailID, username);
		GenericFunctions.clickElement(driver, buttonContinue);
		GenericFunctions.WaitForElement(driver, txtEnterYourPassword);
		//GenericFunctions.sendKeys(driver, inputPassword, "Welcome45!");
		GenericFunctions.sendKeys(driver, inputPassword, password);
		GenericFunctions.clickElement(driver, buttonLogin);
	}		
	/*
	public void LoginWithEmail() throws InterruptedException{
		GenericFunctions.clickElement(driver, buttonContinueWithEmail);
		if (testCaseName = "Verify Onboarding journey for Planning to have a Baby via eMail - Female Flow")
		{
		//GenericFunctions.sendKeys(driver, inputEmailID, "balaji.sridharan@mphasis.com");
		GenericFunctions.sendKeys(driver, inputEmailID, "s.balaji80@gmail.com");
		GenericFunctions.clickElement(driver, buttonContinue);
		GenericFunctions.WaitForElement(driver, txtEnterYourPassword);
		//GenericFunctions.sendKeys(driver, inputPassword, "Welcome45!");
		GenericFunctions.sendKeys(driver, inputPassword, "Winter05!");
		GenericFunctions.clickElement(driver, buttonLogin);
		}
		
		
		
	}	
	*/
	/** 
	 * The method will try to <b>Login</b> to the mobile app using a blank emailId
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */		
	public void LoginWithBlankEmail() throws InterruptedException 
	{
		GenericFunctions.clickElement(driver, buttonContinueWithEmail);
		//GenericFunctions.sendKeys(driver, inputEmailID, "");
		GenericFunctions.clickElement(driver, buttonContinue);
		Thread.sleep(30000);
	}
	
	/** 
	 * The method will try to <b>Login</b> to the mobile app using a invalid emailId format
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */	
	public void LoginWithIncorrectEmailFormat() throws InterruptedException 
	{
		GenericFunctions.clickElement(driver, buttonContinueWithEmail);
		GenericFunctions.sendKeys(driver, inputEmailID, "balaji@");
		GenericFunctions.clickElement(driver, buttonContinue);
		Thread.sleep(30000);
		
	}
	
	//Verify whether user gets correct error message while providing invalid password
	/** 
	 * The method will try to <b>Login</b> to the mobile app using a invalid password
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */		
    public void LoginInvalidPasswordEmail() throws InterruptedException{
        GenericFunctions.clickElement(driver, buttonContinueWithEmail);
        GenericFunctions.sendKeys(driver, inputEmailID, "balaji.sridharan@mphasis.com");
        GenericFunctions.clickElement(driver, buttonContinue);
        Thread.sleep(3000);
        GenericFunctions.WaitForElement(driver, txtEnterYourPassword);
        GenericFunctions.sendKeys(driver, inputPassword, "Welcome123");
        GenericFunctions.WaitForElement(driver, buttonLogin);
        GenericFunctions.clickElement(driver, buttonLogin);
   }
    
    
	  //Verify whether user gets correct error message while providing invalid password
	/** 
	 * The method will try to <b>Login</b> to the mobile app using a invalid password and verifies the error message
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */		
    public void LoginPasswordStandardMessageEmail() throws InterruptedException{
        GenericFunctions.clickElement(driver, buttonContinueWithEmail);
        GenericFunctions.sendKeys(driver, inputEmailID, "s.balaji80@gmail.com");
        GenericFunctions.clickElement(driver, buttonContinue);
        Thread.sleep(3000);
        GenericFunctions.WaitForElement(driver, txtEnterYourPassword);
        GenericFunctions.sendKeys(driver, inputPassword, "Win");
        GenericFunctions.WaitForElement(driver, buttonLogin);
        GenericFunctions.clickElement(driver, buttonLogin);
   }
    

    /**
     * Taps on <b>Ok</b> button in the page
     */
    public void clickOk()
    {
    	GenericFunctions.WaitForElement(driver, buttonOK);
    	GenericFunctions.clickElement(driver, buttonOK);
     }
       
    //Verify whether user is able to login using Facebook, and user should be able to explore the app
    /**
     * Taps on <b>Continue with Facebook</b> button in the login page
     * 
     * @throws InterruptedException thrown when a thread is interrupted
     */        
    public void LoginWithFacebook() throws InterruptedException{
    	GenericFunctions.WaitForElement(driver, buttonContinueWithFacebook);
        GenericFunctions.clickElement(driver, buttonContinueWithFacebook);
       
     }
   
    //Verify whether user is able to login using Gmail, and user should be able to explore the app
    /**
     * Taps on <b>Continue with Facebook</b> button in the login page
     * 
     * @throws InterruptedException thrown when a thread is interrupted
     */        
    public void LoginWithGmail()throws InterruptedException{
    	GenericFunctions.WaitForElement(driver, buttonContinueWithGoogle);
    	GenericFunctions.clickElement(driver, buttonContinueWithGoogle);
   }
        
    //Verify whether user is able to login using Apple, and user should be able to explore the app
	/** 
	 * The method will try to <b>Login</b> to the mobile app using Apple id and invalid password
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */		    
    public void LoginWithAppleIncorrectPwd()throws InterruptedException{
    	GenericFunctions.WaitForElement(driver, buttonContinueWithApple);
    	GenericFunctions.clickElement(driver, buttonContinueWithApple);
    	GenericFunctions.sendKeys(driver, inputAppleID,"balajisridharan@gmail.com");
    	driver.findElement(By.xpath("//android.widget.Button[@text=' Continue ']")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'password_text_field') and @index='0']")).sendKeys("Welcome123");
		driver.findElement(By.xpath("//android.widget.Button[@text=' Sign In ']")).click();
		
		String s1=driver.findElement(By.xpath("//android.widget.TextView[contains(@id,'errMsg') and @index='1']")).getText();
		System.out.println(s1);
		String s2=driver.findElement(By.id("errMsg")).getText();
		System.out.println(s2);	
		
		List<MobileElement> term =driver.findElements(By.xpath("//android.view.View"));
		
		System.out.println(term.size());
		for(int i=0;i<term.size();i++) {
			System.out.println(term.get(i).getText());
			}
	}
   
    
    //Verify whether user is able to login using Apple, and user should be able to explore the app
	/** 
	 * The method will try to <b>Login</b> to the mobile app using Apple id and valid password
	 *  
	 *  @throws InterruptedException thrown when a thread is interrupted
	 */    
    public void LoginWithApple()throws InterruptedException{
    	//GenericFunctions.WaitForElement(driver, buttonContinueWithApple);
    	//GenericFunctions.clickElement(driver, buttonContinueWithApple);
    	//GenericFunctions.sendKeys(driver, inputAppleID,"balajisridharan01@icloud.com");
    	GenericFunctions.sendKeys(driver, inputAppleID,Constants.AppleTestUser);
    	GenericFunctions.WaitForElement(driver, ApplebuttonContinue);
    	GenericFunctions.clickElement(driver, ApplebuttonContinue);
    	//GenericFunctions.sendKeys(driver, ApplebuttonPassword,"Summer90!");
    	GenericFunctions.sendKeys(driver, ApplebuttonPassword,Constants.AppleTestPassword);
    	GenericFunctions.WaitForElement(driver, ApplebuttonSignIn);
    	GenericFunctions.clickElement(driver, ApplebuttonSignIn);
    	GenericFunctions.WaitForElement(driver, ApplebuttonContinue);
    	GenericFunctions.clickElement(driver, ApplebuttonContinue);
     }
    
    /**
     * Taps on <b>Continue with Apple</b> button in the login page
     * 
     */            
    public void ClickAppleLogin()
        {
    	GenericFunctions.WaitForElement(driver, buttonContinueWithApple);
		GenericFunctions.clickElement(driver, buttonContinueWithApple);
    }

    /**
     * This method will <b>navigate</b> through the Intro screen to the Login Screen
     * 
     */    
    public void loginAllPlatforms()
    {
	
	if(GenericFunctions.isElementDisplayed(WhileUsingTheApp))
	{
		try {
		Takepic_Recvideo();
		}catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO, "WhileusingApp or Allow button not displayed while login");
			e.printStackTrace();
		}
		
		try {
			skipIntroScreens();
			}catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO, "Skip button not displayed while login");
				e.printStackTrace();
			}
		
		try {
			LoginWithEmail();
		} catch (InterruptedException e) {
			ExtentReportManager.getTest().log(Status.INFO, "Login with Email button not displayed while login");
			e.printStackTrace();
		}
	}

	else if (GenericFunctions.isElementDisplayed(allow))
	{
		try {
	GenericFunctions.clickElement(driver, allow);		
	GenericFunctions.clickElement(driver, allow);		
	GenericFunctions.clickElement(driver, allow);
		}catch (Exception e) {
			ExtentReportManager.getTest().log(Status.INFO, "Allow button not displayed while login");
			e.printStackTrace();
		}
	try {
	skipIntroScreens();
	}catch (Exception e) {
		ExtentReportManager.getTest().log(Status.INFO, "Skip button not displayed while login");
		e.printStackTrace();
	}
	try {
		LoginWithEmail();
	} catch (InterruptedException e) {
		ExtentReportManager.getTest().log(Status.INFO, "Login with Email button not displayed while login");
		e.printStackTrace();
	}
	}
	else
	{
		try {
			skipIntroScreens();
			}catch (Exception e) {
				ExtentReportManager.getTest().log(Status.INFO, "Skip button not displayed while login");
				e.printStackTrace();
			}
			try {
				LoginWithEmail();
			} catch (InterruptedException e) {
				ExtentReportManager.getTest().log(Status.INFO, "Login with Email button not displayed while login");
				e.printStackTrace();
			}
	}


    }
}
