package com.pom.crimson.pages;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * This is the Page Class for <b>Profile Menu</b> page in the mobile application.
 * <br>
 * User can navigate to this page by going to:
 * <br><br>
 * <b>Profile</b> menu from home page Tab bar   
 * <br><br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Balaji.Sridharan 
 */
public class ProfilePage extends BasePage {

	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 		
	public ProfilePage(AppiumDriver<MobileElement> driver, String platformName) {
		super(driver, platformName);
		
	}
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Next']")
	private MobileElement buttonNext;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Profile']")
	private MobileElement ClickProfile;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Manage profiles']")
	private MobileElement ClickManageProfile;
	
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='My partner and I are planning to have a baby']")
	private MobileElement PHBClick;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='My partner and I are pregnant']")
	private MobileElement PRGClick;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='I have a child/children (older than 1 year old)']")
	private MobileElement CCClick3;
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='I have a newborn (0 - 1 year old)']")
	private MobileElement NBClick;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	private MobileElement clickOK;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='1']")
	private MobileElement click;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Yes']")
	private MobileElement clickYes;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No']")
	private MobileElement clickNo;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Update status']")
	private MobileElement clickUpdateStatus;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Edit']")
	private MobileElement ClickEdit;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add due date']")
	private MobileElement ClickAddDueDate;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Update status']")
	private MobileElement UpdateStatusButtonClick;
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Done']")
	private MobileElement ClickDone;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='' and @index='1']")
	private MobileElement clickArchetype;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='1']")
	private MobileElement click1;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='2']")
	private MobileElement click2;
	
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='I'll do this later']")
		@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'do this later')]")
		private MobileElement LaterButton;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Save changes']")
		private MobileElement ClickSave;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='DD/MM/YYYY']")
		private MobileElement ChildDOBClick;
		
		@AndroidFindBy(xpath="//android.view.View[@text='1']")
		private MobileElement select1;
		
		@AndroidFindBy(xpath="//android.view.View[@text='2']")
		private MobileElement select2;
		
		
		@AndroidFindBy(xpath="//android.widget.EditText[@text='Select number of kids']")
		private MobileElement SelectNumberOfKids;
		
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.MANAGE_PROFILE_TITLE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.MANAGE_PROFILE_TITLE+"']")
	private MobileElement manageProfiles;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='"+Constants.PROFILEPAGE_SAVED_ARTICLES_VIDEOS+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.PROFILEPAGE_SAVED_ARTICLES_VIDEOS+"']")
	private MobileElement savedArticlesVideos;
		
		
		/**
		 * Taps on the <b>Next</b> button on the screen
		 * 
		 * @throws InterruptedException thrown when a thread is interrupted
		 */
		public void clickNext() throws InterruptedException{
			
			GenericFunctions.clickElement(driver, buttonNext);
			
		}	
		
		/**
		 * Taps on <b>Update status</b> button in the Change Archetype screen 
		 */
		public void UpdateStatusButtonClick()
		{
			GenericFunctions.scroll(driver, "Update status", platformName);
			GenericFunctions.clickElement(driver, UpdateStatusButtonClick);
		}
	
		/**
		 * Taps on <b>Profile</b> menu from home page Tab bar
		 * @throws InterruptedException thrown when a thread is interrupted
		 */
	public void clickProfile() throws InterruptedException
	{
		GenericFunctions.WaitForElement(driver, ClickProfile);
		GenericFunctions.clickElement(driver, ClickProfile);
	}

	/**
	 * Taps on <b>Manage profile</b> menu from Profile menu list
	 * @throws InterruptedException thrown when the Thread is interrupted
	 */
	public void clickManageProfile() throws InterruptedException
	{
		GenericFunctions.WaitForElement(driver, ClickManageProfile);
		GenericFunctions.clickElement(driver, ClickManageProfile);
	}
	
	/**
	 * Taps on <b>'My partner and I are planning to have a baby'</b> archetype in the update status page
	 */
	public void PHBClick()
	{
		GenericFunctions.WaitForElement(driver, PHBClick);
		GenericFunctions.clickElement(driver, PHBClick);
	}
	
	/**
	 * Taps on the <b>Archetype</b> displayed on Manage profile screen for changing it. 
	 */
	public void clickArchetype()
	{
		GenericFunctions.WaitForElement(driver, clickArchetype);
		GenericFunctions.clickElement(driver, clickArchetype);
	}
	
	/**
	 * Taps on <b>'My partner and I are planning to have a baby'</b> archetype in the update status page
	 * 
	 * @throws IOException thrown during an IO Exception
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void Click_planning_to_have_a_baby() throws IOException, InterruptedException {
	   // GenericFunctions.WaitForElement(driver,PHBClick );
		List <MobileElement> alltext=getDriver().findElements(By.xpath("//android.widget.TextView"));
		
		System.out.println(alltext.size());
		for(int i=0;i<alltext.size();i++) {
			System.out.println(alltext.get(i).getText());
			}
		int verify=getDriver().findElements(By.xpath("//android.widget.TextView[@text='My partner and I are planning to have a baby']")).size();
		
		if (verify==1)
			//alltext.get(7).click();
			alltext.get(6).click();
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'planning to have a baby')]")).click();
	}
	
	/**
	 * Clicks on <b>'My partner and I are planning to have a baby'</b> archetype in the update status page
	 * 
	 * @throws IOException thrown during an IO Exception
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void Click_Archtype_planning_to_have_a_baby() throws IOException, InterruptedException {
		   // GenericFunctions.WaitForElement(driver,PHBClick );
			List <MobileElement> alltext=getDriver().findElements(By.xpath("//android.widget.TextView"));
			
			System.out.println(alltext.size());
			for(int i=0;i<alltext.size();i++) {
				System.out.println(alltext.get(i).getText());
				}
			int verify=getDriver().findElements(By.xpath("//android.widget.TextView[@text='My partner and I are planning to have a baby']")).size();
			
			if (verify==1)
				alltext.get(5).click();
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'planning to have a baby')]")).click();
		}
	
	/**
	 * Taps on <b>Yes</b> button
	 */
	public void clickYes()
	{
		 GenericFunctions.WaitForElement(driver, clickYes);
		GenericFunctions.clickElement(driver, clickYes);
	}
	
	
	/**
	 * Taps on <b>Next</b> button 
	 */
	public void Scroll_clickNext()
	{
		GenericFunctions.scroll(driver, "Next", platformName);
		GenericFunctions.clickElement(driver, buttonNext);
	}
	
	/**
	 * Taps on the <b>No</b> button 
	 */
	public void Scroll_clickNo()
	{
		GenericFunctions.scroll(driver, "No", platformName);
		GenericFunctions.clickElement(driver, clickNo);
	}
	
	/**
	 * Taps on android.widget.CheckedTextView[@text='1'] element in the page
	 */
	public void click()
	{
		GenericFunctions.clickElement(driver, click);
	}
	/**
	 * Taps on <b>Update status</b> button in the Change archetype page. 
	 */
	public void updateStatus() {
		GenericFunctions.clickElement(driver, clickUpdateStatus);
	}

	
	/**
	 * Taps on <b>My partner and I are pregnant</b> archetype in the Update status page
	 * 
	 * @throws IOException thrown during an IO Exception
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void PRG_Click_My_partner_and_I_are_pregnant() throws IOException, InterruptedException {
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
		
		System.out.println(alltext.size());
		for(int i=0;i<alltext.size();i++) {
			System.out.println(alltext.get(i).getText());
			}
		
		int verify=driver.findElements(By.xpath("//android.widget.TextView[@text='My partner and I are pregnant']")).size();
        if (verify==1)
        	//alltext.get(3).click();
			alltext.get(6).click();
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'pregnant')]")).click();		


		}
	
	/**
	 * Selects <b>My partner and I are pregnant</b> archetype in the Update status page
	 * 
	 * @throws IOException thrown during an IO Exception
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void ArchType_PRG_Click_My_partner_and_I_are_pregnant() throws IOException, InterruptedException {
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
		
		System.out.println(alltext.size());
		for(int i=0;i<alltext.size();i++) {
			System.out.println(alltext.get(i).getText());
			}
		
		int verify=driver.findElements(By.xpath("//android.widget.TextView[@text='My partner and I are pregnant']")).size();
        if (verify==1)
        	//alltext.get(3).click();
			alltext.get(4).click();
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'pregnant')]")).click();		


		}
   
	/**
	 * Clicks on <b>My partner and I are pregnant</b> archetype in the Update status page
	 * 
	 * @throws IOException thrown during an IO Exception
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
    public void Click_My_partner_and_I_are_planning_to_have_a_baby() throws IOException, InterruptedException {
        GenericFunctions.WaitForElement(driver,PHBClick );
    	List <MobileElement> alltext=getDriver().findElements(By.xpath("//android.widget.TextView"));
    	
    	System.out.println(alltext.size());
    	for(int i=0;i<alltext.size();i++) {
    		System.out.println(alltext.get(i).getText());
    		}
    	int verify=getDriver().findElements(By.xpath("//android.widget.CheckedTextView[@text='My partner and I are planning to have a baby']")).size();
    	
    	if (verify==1)
    		//alltext.get(3).click();
    		alltext.get(6).click();
    		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'planning to have a baby')]")).click();
    }
    
    /**
     * Taps on <b>I have a newborn (0 - 1 year old)</b> archetype in the Update status page
     * 
     * @throws IOException thrown during an IO Exception
     * @throws InterruptedException thrown when a thread is interrupted
     */
    public void Click_NB_I_have_a_newborn() throws IOException, InterruptedException {
    	List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
    	
    	System.out.println(alltext.size());
    	for(int i=0;i<alltext.size();i++) {
    		System.out.println(alltext.get(i).getText());
    		}
    	
    	int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='I have a newborn (0 - 1 year old)']")).size();
    	if (verify==1)
    		//alltext.get(5).click();
    		alltext.get(3).click();
    		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'newborn')]")).click();
    }
    
    /**
     * Taps on <b>I have a child/children (older than 1 year old)</b> archetype in the Update status page
     * 
     * @throws IOException thrown during an IO Exception
     * @throws InterruptedException thrown when a thread is interrupted
     */
    public void CC_Click_I_have_a_child_children() throws IOException, InterruptedException {
    	List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
    	
    	System.out.println(alltext.size());
    	for(int i=0;i<alltext.size();i++) {
    		System.out.println(alltext.get(i).getText());
    		}
    	int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='I have a child/children (older than 1 year old)']")).size();
    	if (verify==1)
    		//alltext.get(6).click();
    		alltext.get(3).click();
    		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'child/children')]")).click();
    	}
    
    /**
     * Taps on the various archetype options available in the Update Status page
     */
    public void Choose_what_best_describes_you_today()
   	{
       	GenericFunctions.clickElement(driver, PHBClick);
   		GenericFunctions.clickElement(driver, PHBClick);
   		GenericFunctions.clickElement(driver, NBClick);
   		GenericFunctions.clickElement(driver, CCClick3);
   		GenericFunctions.clickElement(driver, buttonNext);
   	}
    
    /**
     * 
     */
    public void You_are_switching_to_Planning_a_baby_mode() {
    	
    }
    
    /**
     * Taps on <b>No</b> button
     * 
     * @throws IOException thrown during an IO Exception
     * @throws InterruptedException thrown when a thread is interrupted
     */
    public void Do_you_want_to_Continue_No() throws IOException, InterruptedException {
   	 GenericFunctions.WaitForElement(driver,clickNo);
   	 GenericFunctions.clickElement(driver, clickNo);
   		}
    /**
     * Taps on <b>Yes</b> button
     * 
     * @throws IOException thrown during an IO Exception
     * @throws InterruptedException thrown when a thread is interrupted
     */
   public void Do_you_want_to_Continue_yes() throws IOException, InterruptedException {
   	GenericFunctions.WaitForElement(driver,clickYes);
   	GenericFunctions.clickElement(driver, clickYes);
   		}
   
   /**
    * Scrolls to <b>Update status</b> button in the Status change page
    * 
    * 
    * @throws InterruptedException thrown when a thread is interrupted
    */
   public void Scroll_ClickUpdateStatus() throws InterruptedException
	{
		
		GenericFunctions.scroll(driver, "Update status", platformName);
		GenericFunctions.clickElement(driver, UpdateStatusButtonClick);
	}

   /**
    * Scrolls to the button <b>Yes</b> in the screen and clicks the element
    * 
    */
   public void Scroll_clickYes()
   {
       GenericFunctions.scroll(driver, "Yes", platformName);
       GenericFunctions.WaitForElement(driver, clickYes);
       GenericFunctions.clickElement(driver, clickYes);
   }
   
   /**
    * Scrolls to the button <b>Yes</b> in the screen
    * 
    */
   public void Scroll_Yes()
   {
       GenericFunctions.scroll(driver, "Yes", platformName);
      
   }
   
   
  
	/**
	 * Taps on <b>Edit</b> button in the screen
	 */
	public void Edit()
	{
		GenericFunctions.WaitForElement(driver, ClickEdit);
		GenericFunctions.clickElement(driver, ClickEdit);
	}
	
	/**
	 * Enters the Due Date
	 * 
	 * @throws IOException thrown during an IO Exception
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void Your_child_due_date() throws IOException, InterruptedException {
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.Edit"));
		Calendar calendar = null;
		calendar.get(Calendar.YEAR);	
		
	}
	
	/**
	 * Selects the Due Date and enters it in the screen
	 * 
	 * @throws IOException thrown during an IO Exception
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	 public void PRG_Do_you_know_your_due_date() throws IOException, InterruptedException {
			driver.findElement(By.xpath("//android.widget.TextView[@text='Select date of birth']")).click();
			driver.findElement(By.id("android:id/next")).click();
			driver.findElement(By.id("android:id/next")).click();
			driver.findElement(By.xpath("//android.view.View[@text='1']")).click();
			driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
			}
	 
	 /**
	  * Taps on <b>Add due date</b> button in the screen
	  */
	 public void ClickAddDueDate()
	 {
			GenericFunctions.WaitForElement(driver, ClickAddDueDate);
			GenericFunctions.clickElement(driver, ClickAddDueDate);
	 }
	 
	 /**
	  * Taps on <b>Do this later</b> button in the screen
	  */
	 public void laterButton()
	 {
	 	GenericFunctions.WaitForElement(driver,LaterButton );
	 	GenericFunctions.clickElement(driver, LaterButton);

	 }
	 
	 /**
	  * Selects the options for Changing the archetype to Planning to have a baby. 
	  * @throws InterruptedException thrown when a thread is interrupted
	  */
	 public void PHB_UpdateStatus() throws InterruptedException
	 {
			 /*
			int verify_1=driver.findElements(By.xpath("//android.widget.TextView[@text='More than one year']")).size();
			int verify_2=driver.findElements(By.xpath("//android.widget.TextView[@text='Less than three months']")).size();
			int verify_3=driver.findElements(By.xpath("//android.widget.TextView[@text='Less than nine months']")).size();
			int verify_4=driver.findElements(By.xpath("//android.widget.TextView[@text='About one year']")).size();
			*/
		 
			int verify_1=driver.findElements(By.xpath("//android.widget.EditText[@text='More than one year']")).size();
			int verify_2=driver.findElements(By.xpath("//android.widget.EditText[@text='Less than three months']")).size();
			int verify_3=driver.findElements(By.xpath("//android.widget.EditText[@text='Less than nine months']")).size();
			int verify_4=driver.findElements(By.xpath("//android.widget.EditText[@text='About one year']")).size();
			int verify_5=driver.findElements(By.xpath("//android.widget.EditText[@text='Select']")).size();
			
			if (verify_1==1 || verify_2==1 || verify_3==1 || verify_5==1)
			{
				//driver.findElement(By.xpath("android.widget.EditText")).click();
				//driver.findElement(By.id("android:id/text1")).click();
				List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
				alltext.get(0).click();
				driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='About one year']")).click();
				}
			else if (verify_4==1)
			{
				//driver.findElement(By.id("android:id/text1")).click();
				List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
				alltext.get(0).click();
				driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Less than nine months']")).click();
				}
			
		}
	 
	 /**
	  * Navigates through the screen to update the status
	  * @throws InterruptedException thrown when a thread is interrupted
	  */
	 public void PRG_UpdateStatus() throws InterruptedException
	 {
				 
			int verify_1=driver.findElements(By.xpath("//android.widget.EditText[@text='Yes']")).size();
			int verify_2=driver.findElements(By.xpath("//android.widget.EditText[@text='No']")).size();
						
			if (verify_1==1 )
			{
				List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
				alltext.get(0).click();
				driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='No']")).click();
				}
			else if (verify_2==1)
			{
				//driver.findElement(By.id("android:id/text1")).click();
				List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.EditText"));
				alltext.get(0).click();
				driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Yes']")).click();
				}
			
		}
	 
	 /**
	  * Taps on the <b>Save</b> button
	  */
	 public void ClickSave()
	 {
		 GenericFunctions.WaitForElement(driver,ClickSave );
		 	GenericFunctions.clickElement(driver, ClickSave);

	 }
	 
	 /**
	  * Changes the archetype to <b>I have a newborn (0 - 1 year old)</b>
	  *  
	  * @throws IOException thrown during an IO Exception
	  * @throws InterruptedException thrown when a thread is interrupted
	  */
	 public void I_have_a_newborn() throws IOException, InterruptedException {
			driver.hideKeyboard();
			Thread.sleep(2000);
			//Onboarding.driver.findElement(By.xpath("//android.widget.TextView[@text='(0 - 1 year old)']")).click();
			List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
			
			System.out.println(alltext.size());
			for(int i=0;i<alltext.size();i++) {
				System.out.println(alltext.get(i).getText());
				}
			
			int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='I have a newborn (0 - 1 year old)']")).size();
			if (verify==1)
				alltext.get(5).click();
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'newborn')]")).click();
			//Assert.assertEquals(verify, 1);
							
			}

	 /**
	  * Changes the archetype to I have a child/children (older than 1 year old)
	  * 
	  * @throws IOException thrown during an IO Exception
	  * @throws InterruptedException thrown when a thread is interrupted
	  */
	 public void Click_I_have_a_child_children() throws IOException, InterruptedException {
			List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
			
			System.out.println(alltext.size());
			for(int i=0;i<alltext.size();i++) {
				System.out.println(alltext.get(i).getText());
				}
			
					
			int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='I have a child/children (older than 1 year old)']")).size();
			if (verify==1)
				alltext.get(6).click();
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'child/children')]")).click();
			//Assert.assertEquals(verify, 1);
				}
	 
	 /**
	  * Enter Date of Birth for two children while changing the archetype 
	  * 
	  * @throws IOException thrown during an IO Exception
	  * @throws InterruptedException thrown when a thread is interrupted
	  */
	 public void How_old_are_your_kids_2() throws IOException, InterruptedException {
			GenericFunctions.clickElement(driver, ChildDOBClick);
			GenericFunctions.clickElement(driver, select1);
			GenericFunctions.clickElement(driver, clickOK);
			GenericFunctions.clickElement(driver, ChildDOBClick);
			GenericFunctions.clickElement(driver, select2);
			GenericFunctions.clickElement(driver, clickOK);
			}
	 
	 /**
	  * Taps on Done button
	  */
	 public void ClickDone()
	 {
		 GenericFunctions.WaitForElement(driver,ClickDone );
		 	GenericFunctions.clickElement(driver, ClickDone);

	 }
	 
	 /**
	  * Clicks on "Select number of kids" option while changing the archetype
	  */
		public void SelectNumberOfKids()
		{
			GenericFunctions.clickElement(driver, SelectNumberOfKids);
		}
	 
		/**
		 * Select Number of kids as <b>1</b>
		 */
		public void click1()
		{
			GenericFunctions.clickElement(driver, click1);
		}
		
		/**
		 * Select Number of kids as <b>2</b>
		 */
		public void click2()
		{
			GenericFunctions.clickElement(driver, click2);
		}
		
		/**
		 * Clicks on <b>Edit</b> button
		 * 
		 * @throws InterruptedException thrown when a thread is interrupted
		 */
		public void btnEditClick() throws InterruptedException
		{
			
			List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
			System.out.println(alltext.size());
			for(int i=0;i<alltext.size();i++) {
				System.out.println(alltext.get(i).getText());
				}
			
			int verify=driver.findElements(By.xpath("//android.widget.TextVieww[@text='Edit']")).size();
			//alltext.get(4).click();
			alltext.get(3).click();
			if (verify==1)
			driver.findElement(By.xpath("//android.widget.TextView[@text='Edit']")).click();
			Thread.sleep(3000);
		}
		
		/**
		 * Clicks on <b>Manage profiles</b> menu from profile menu
		 * 
		 * @return {@link ManageProfilePage} Object of Manage Profile page
		 */
	public ManageProfilePage goToManageProfilepage()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Manage Profiles");
		GenericFunctions.clickElement(driver, manageProfiles);
		ManageProfilePage manageProfilePage=new ManageProfilePage(driver,platformName);
		return manageProfilePage;
	}
	
	/**
	 * Clicks on <b>Saved articles and videos</b> menu from profile menu
	 * 
	 * @return {@link SavedArticlesAndVideosPage} Object of Saved Articles and Videos
	 */
	public SavedArticlesAndVideosPage goToSavedArticlesAndVideosPage()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on Saved Articles and Videos");
		GenericFunctions.clickElement(driver, savedArticlesVideos);
		SavedArticlesAndVideosPage savedArticlesAndVideosPage=new SavedArticlesAndVideosPage(driver,platformName);
		return savedArticlesAndVideosPage;
	}
	
	/**
	 * 
	 */
	public void test()
	{
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
		System.out.println(alltext.size());
		for(int i=0;i<alltext.size();i++) {
			System.out.println(alltext.get(i).getText());
			}
	}
		
}
 
