package com.pom.crimson.pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * This class contains MobileElements and functions to simulate action of on-boarding journey.
 * 
 * @author Balaji.Sridharan 
 */	
public class OnboardingPage extends BasePage{
	
	/**
	 * Initializes the BasePage class.
	 * which assigns the driver and platform to this page object.  
	 * 
	 * @param driver Appium driver created while starting the test
	 * @param platformName Platform name initialized while starting the test    
	 */ 			 	
	public OnboardingPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}

	@AndroidFindBy(xpath="//android.widget.TextView[@text='tab_showcase']")
	private MobileElement tab_showcase;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='OnboardingScreens']")
	private MobileElement Onboarding;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Next']")
	private MobileElement buttonNext;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Boy']")
	private MobileElement selectBoy;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Female') and @index='1']")
	private MobileElement FemaleGenderSelection;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'Male') and @index='1']")
	private MobileElement MaleGenderSelection;

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'planning to have a baby')]")
	private MobileElement PlanningtoHaveaBaby;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Yes']")
	private MobileElement clickYes;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No']")
	private MobileElement clickNo;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement callYou;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	private MobileElement NationalId;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select date of birth']")
	private MobileElement selectDOB;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select']")
	private MobileElement ClickSelect;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Select number of kids']")
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Select number of kids']")
	private MobileElement SelectNumberOfKids;
	
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='1']")
	private MobileElement click1;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='2']")
	private MobileElement click2;
	
	@AndroidFindBy(xpath="//android.view.View[@text='1']")
	private MobileElement select1;
	
	@AndroidFindBy(xpath="//android.view.View[@text='2']")
	private MobileElement select2;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	private MobileElement clickOK;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Medical care & checkups']")
	private MobileElement Interest1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Nutrition & fitness']")
	private MobileElement Interest2;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Baby care']")
	private MobileElement Interest3;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='My partner and I are planning to have a baby']")
	private MobileElement PHBClick;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Let’s get started']")
	private MobileElement StartedButton;
	
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='I'll do this later']")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'do this later')]")
	private MobileElement LaterButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Baby's date of birth']")
	private MobileElement BabyDOBClick;
	
	//@AndroidFindBy(xpath="//android.widget.TextView[@text='dd/mm/yyyy']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='DD/MM/YYYY']")
	private MobileElement ChildDOBClick;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='I have twins']")
	private MobileElement Twins;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Continue']")
	private MobileElement ContinueBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Why only these two?']")
	private MobileElement theseTwo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Join group']")
	private MobileElement JoinGroup;
		
	/**
	 * Clicks on <b>Select number of kids</b> in the on-boarding journey screen
	 */
	public void SelectNumberOfKids()
	{
		GenericFunctions.clickElement(driver, SelectNumberOfKids);
	}
	
	/**
	 * Clicks on <b>Number 1</b> in the screen for number of kids.
	 */	
	public void click1()
	{
		GenericFunctions.clickElement(driver, click1);
	}
	
	/**
	 * Clicks on <b>Number 2</b> in the screen for number of kids.
	 */		
	public void click2()
	{
		GenericFunctions.clickElement(driver, click2);
	}
		
	/**
	 * Clicks on <b>Yes</b> in the On-boarding journey screen.
	 */			
	public void clickYes()
	{
		GenericFunctions.clickElement(driver, clickYes);
	}
	
	/**
	 * Clicks on <b>No</b> in the On-boarding journey screen.
	 */				
	public void clickNo()
	{
		GenericFunctions.clickElement(driver, clickNo);
	}
	
	/**
	 * Select <b>Boy</b> in the on-boarding journey screen.
	 */
	public void selectBoy()
	{
		GenericFunctions.clickElement(driver, selectBoy);
	}
	
	/**
	 * Clicks on <b>Tab_showcase</b> button in the Tab bar of the Home page
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void ClickTab_showcase() throws InterruptedException{
		//WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Live events']")));
		GenericFunctions.WaitForElement(driver, tab_showcase);
		GenericFunctions.clickElement(driver, tab_showcase);
		GenericFunctions.WaitForElement(driver, Onboarding);
		
	}	
	
	/**
	 * Clicks on <b>OnboardingScreens</b> Menu in the Tab_showcase Menu list.
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */	
	public void ClickOnboarding() throws InterruptedException{
		GenericFunctions.WaitForElement(driver, Onboarding);
		GenericFunctions.clickElement(driver, Onboarding);
		
	}	
	
	/**
	 * Navigates through the <b>Terms and conditions</b> screen in the on-boarding flow and accepts all consents
	 */
	public void consentContinue()
	{
		GenericFunctions.scroll(driver, "Continue", platformName);
		
		List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
		System.out.println(alltext.size());
		for(int i=0;i<alltext.size();i++) {
			System.out.println(alltext.get(i).getText());
			}
		
		int verify=driver.findElements(By.xpath("//android.widget.TextVieww[@text='Continue']")).size();
		alltext.get(4).click();
		if (verify==1)
		driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();
	}
	
	/**
	 * Enters the Data of Birth of the user in the On-boarding journey
	 */
	public void Lastly_whats_your_date_of_birth()
	{
		
		GenericFunctions.clickElement(driver, selectDOB);
		GenericFunctions.clickElement(driver, select1);
		GenericFunctions.clickElement(driver, clickOK);
	}

	/**
	 * Navigates through the <b>Terms and conditions</b> screen in the on-boarding flow and accepts all consents
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */	
	public void consentAccept() throws InterruptedException
	{
		GenericFunctions.WaitForElement(driver, getDriver().findElement(By.xpath("//android.widget.ImageView")));
	List<MobileElement> term =getDriver().findElements(By.xpath("//android.widget.ImageView"));
	
	System.out.println(term.size());
	for(int i=0;i<term.size();i++) {
		System.out.println(term.get(i).getText());
		}
	term.get(2).click();
	term.get(3).click();
}
	
	/**
	 * Navigates through the <b>Terms and conditions</b> screen in the on-boarding flow and accepts all consents
	 * 
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void consentAcceptTest() throws InterruptedException
	{
		GenericFunctions.WaitForElement(driver, getDriver().findElement(By.xpath("//android.widget.ImageView")));
	List<MobileElement> term =getDriver().findElements(By.xpath("//android.widget.ImageView"));
	term.get(0).click();
	term.get(1).click();
	term.get(2).click();
	term.get(3).click();
    Thread.sleep(2000);
}
	/**
	 * Gender Selection in on-boarding journey flow
	 */
	public void genderSelectionFemaleTest()
	{
		
		GenericFunctions.WaitForElement(driver, getDriver().findElement(By.xpath("//android.widget.TextView")));
	List<MobileElement> term =getDriver().findElements(By.xpath("//android.widget.TextView"));
	
	System.out.println(term.size());
	for(int i=0;i<term.size();i++) {
		System.out.println(term.get(i).getText());
		}
	term.get(2).click();
}
	
/**
 * Enters Female name in Planning to have a Baby on-boarding journey	
 */
public void enterNamePHBFemale()
{
	GenericFunctions.sendKeys(driver, callYou, "Divya");
}

/**
 * Enters Female name in My partner and I are pregnant on-boarding journey	
 */
public void enterNamePRGFemale()
{
	GenericFunctions.sendKeys(driver, callYou, "Lesya");
}

/**
 * Enters Female name in New Born on-boarding journey	
 */
public void enterNameNBFemale()
{
	GenericFunctions.sendKeys(driver, callYou, "Sathiya");
}

/**
 * Enters Female name in I have a child/children (older than 1 year old) on-boarding journey	
 */
public void enterNameCCFemale()
{
	GenericFunctions.sendKeys(driver, callYou, "Ria");
}

/**
 * Enters Male name in Planning to have a Baby on-boarding journey	
 */
public void enterNamePHBMale()
{
	GenericFunctions.sendKeys(driver, callYou, "Surya");
}

/**
 * Enters Male name in My partner and I are pregnant on-boarding journey	
 */
public void enterNamePRGMale()
{
	GenericFunctions.sendKeys(driver, callYou, "Ravi");
}

/**
 * Enters Male name in New Born on-boarding journey	
 */
public void enterNamenNBMale()
{
	GenericFunctions.sendKeys(driver, callYou, "Sam");
}

/**
 * Enters Male name in I have a child/children (older than 1 year old) on-boarding journey	
 */
public void enterNameCCMale()
{
	GenericFunctions.sendKeys(driver, callYou, "Sanjay");
}

/**
 * Clicks on Next button in On-boarding flow screen
 *  
 * @throws InterruptedException thrown when a thread is interrupted
 */
public void clickNext() throws InterruptedException{
	
	GenericFunctions.clickElement(driver, buttonNext);
	
}	

/**
 * Select Gender as <b>Female</b> during the on-boarding journey
 * 
 * @throws InterruptedException thrown when the Thread is interrupted
 */
public void GenderSelectionFemale() throws InterruptedException{
	GenericFunctions.WaitForElement(driver, FemaleGenderSelection);
	GenericFunctions.clickElement(driver, FemaleGenderSelection);
	
}	

/**
 * Select <b>Twins</b> option during the on-boarding journey
 */
public void Twins()
{
	GenericFunctions.clickElement(driver, Twins);
}

/**
 * Select <b>Continue</b> button during the on-boarding journey
 */
public void ContinueBtn_Click()
{
	GenericFunctions.clickElement(driver, ContinueBtn);
}



/**
 * Select Child's Data of Birth during the on-boarding journey for one Child Scenario
 * @throws IOException thrown during an IO Exception
 * @throws InterruptedException thrown when a thread is interrupted 
 */
public void How_old_are_your_kids() throws IOException, InterruptedException {
	GenericFunctions.clickElement(driver, ChildDOBClick);
	GenericFunctions.clickElement(driver, select1);
	GenericFunctions.clickElement(driver, clickOK);
	}

/**
 * Select Children's Data of Birth during the on-boarding journey for two Child Scenario
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
 * Select Gender as <b>Female</b> during the on-boarding journey
 * 
 * @throws InterruptedException thrown when the Thread is interrupted
 */
public void GenderSelectionMale() throws InterruptedException{
	GenericFunctions.WaitForElement(driver, MaleGenderSelection);
	GenericFunctions.clickElement(driver, MaleGenderSelection);
	
}	

/**
 * Selects archetype <b>My partner and I are planning to have a baby</b> for the on-boarding journey.
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
		alltext.get(3).click();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'planning to have a baby')]")).click();
}

/**
 * Selects archetype <b>I have a newborn (0 - 1 year old)</b> for the on-boarding journey.
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
		alltext.get(5).click();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'newborn')]")).click();
}

/**
 * Selects archetype <b>I have a child/children (older than 1 year old)</b> for the on-boarding journey.
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
		alltext.get(6).click();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'child/children')]")).click();
	}

/**
 * Chooses <b>About one year</b> for How long have you been trying to conceive question during the on-boarding journey .
 *   
 * @throws IOException thrown during an IO Exception
 * @throws InterruptedException thrown when a thread is interrupted
 */   
public void PHB_How_long_have_you_been_trying_to_conceive() throws IOException, InterruptedException {
	
	   GenericFunctions.WaitForElement(driver, ClickSelect);
	  // driver.findElement(By.xpath("//android.widget.TextView[@text='Select']")).click();
	   driver.findElement(By.xpath("//android.widget.EditText[@text='Select']")).click();
	int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='About one year']")).size();
	if (verify==1)
	driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='About one year']")).click();
	Assert.assertEquals(verify, 1);
	}
   
/**
 * Chooses <b>Less than three months</b> for How long have you been trying to conceive question during the on-boarding journey .
 *   
 * @throws IOException thrown during an IO Exception
 * @throws InterruptedException thrown when a thread is interrupted
 */   
   public void PHB_How_long_have_you_been_trying_to_conceive_Less_than_three_months() throws IOException, InterruptedException {
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Select']")).click();
	   driver.findElement(By.xpath("//android.widget.EditText[@text='Select']")).click();
		int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='Less than three months']")).size();
		if (verify==1)
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Less than three months']")).click();
		Assert.assertEquals(verify, 1);
	
		}

   /**
    * Chooses <b>Less than nine months</b> for How long have you been trying to conceive question during the on-boarding journey .
    *   
    * @throws IOException thrown during an IO Exception
    * @throws InterruptedException thrown when a thread is interrupted
    */   
public void PHB_How_long_have_you_been_trying_to_conceive_Less_than_nine_months() throws IOException, InterruptedException {
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Less than three months']")).click();
	driver.findElement(By.xpath("//android.widget.EditText[@text='Less than three months']")).click();
		int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='Less than nine months']")).size();
		if (verify==1)
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Less than nine months']")).click();
		Assert.assertEquals(verify, 1);
		}

/**
 * Chooses <b>About one year</b> for How long have you been trying to conceive question during the on-boarding journey .
 *   
 * @throws IOException thrown during an IO Exception
 * @throws InterruptedException thrown when a thread is interrupted
 */   
public void PHB_How_long_have_you_been_trying_to_conceive_about_one_year() throws IOException, InterruptedException {
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Less than nine months']")).click();
	driver.findElement(By.xpath("//android.widget.EditText[@text='Less than nine months']")).click();
		int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='About one year']")).size();
		if (verify==1)
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='About one year']")).click();
		Assert.assertEquals(verify, 1);
		}

/**
 * Chooses <b>More than one year</b> for How long have you been trying to conceive question during the on-boarding journey .
 *   
 * @throws IOException thrown during an IO Exception
 * @throws InterruptedException thrown when a thread is interrupted
 */   
public void PHB_How_long_have_you_been_trying_to_conceive_more_than_one_year() throws IOException, InterruptedException {
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='About one year']")).click();
	driver.findElement(By.xpath("//android.widget.EditText[@text='About one year']")).click();
		int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='More than one year']")).size();
		if (verify==1)
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='More than one year']")).click();
		Assert.assertEquals(verify, 1);
	
		}
/**
 * Goes back to the previous screen   
 */
public void Previous_screen_Validation()
{
	List<MobileElement> term =driver.findElements(By.xpath("//android.widget.ImageView"));
	
	System.out.println(term.size());
	for(int i=0;i<term.size();i++) {
		System.out.println(term.get(i).getText());
		}
	term.get(0).click();
}
   
   
/**
 * Enters <b>Baby's date of birth</b> during the on-boarding journey   
 */
 public void BabyDOBClick()
   {
		GenericFunctions.WaitForElement(driver, BabyDOBClick);
		GenericFunctions.clickElement(driver, BabyDOBClick);
		GenericFunctions.clickElement(driver, select1);
		GenericFunctions.clickElement(driver, clickOK);
	}
   
  /**
   * Selects <b>My partner and I are pregnant</b> archetype for the on-boarding journey
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
		
		int verify=driver.findElements(By.xpath("//android.widget.CheckedTextView[@text='My partner and I are pregnant']")).size();
		alltext.get(3).click();
		if (verify==1)
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='pregnant']")).click();
		}
   
   
  /**
   * Selects <b>Interests</b> for Planning to have a baby on-boarding journey
   *  
   * @throws IOException thrown during an IO Exception
   * @throws InterruptedException thrown when a thread is interrupted
   */  
   public void PHB_Select_Interests() throws IOException, InterruptedException {
		GenericFunctions.clickElement(driver, Interest1);
		GenericFunctions.clickElement(driver, Interest2);
		GenericFunctions.clickElement(driver, Interest3);
		GenericFunctions.scroll(driver, "Let’s get started", platformName);
		GenericFunctions.clickElement(driver, StartedButton);
	}
   
   /**
    * Clicks on <b>Later</b> button during the on-boarding journey
    */
public void laterButton()
{
	GenericFunctions.WaitForElement(driver,LaterButton );
	GenericFunctions.clickElement(driver, LaterButton);

}

/**
 * Clicks on <b>Join group</b> button during the on-boarding journey
 */
public void JoinGroup()
{
	GenericFunctions.WaitForElement(driver,JoinGroup );
	GenericFunctions.clickElement(driver, JoinGroup);
	

}


/**
 * Enters <b>Due date</b> for My partner and I are pregnant on-boarding journey
 *  
 * @throws IOException thrown during an IO Exception
 * @throws InterruptedException thrown when a thread is interrupted
 */ 
 public void PRG_Do_you_know_your_due_date() throws IOException, InterruptedException {
	driver.findElement(By.xpath("//android.widget.TextView[@text='Select date']")).click();
	driver.findElement(By.id("android:id/next")).click();
	driver.findElement(By.id("android:id/next")).click();
	driver.findElement(By.xpath("//android.view.View[@text='1']")).click();
	driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
	}
 
 /**
  * Enters <b>Past due date</b> for My partner and I are pregnant on-boarding journey
  *  
  * @throws IOException thrown during an IO Exception
  * @throws InterruptedException thrown when a thread is interrupted
  */  
 public void PRG_Do_you_know_your_Past_due_date() throws IOException, InterruptedException {
	
	driver.findElement(By.xpath("//android.widget.TextView[@text='Select date']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//android.view.View[@text='1']")).click();
	driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
}

 /**
  * Enters <b>Baby's date of birth</b> during the New Born on-boarding journey
  *    
  * @throws IOException thrown during an IO Exception
  * @throws InterruptedException thrown when a thread is interrupted    
  */ 
 public void NB_Enter_your_baby_date_of_birth() throws IOException, InterruptedException {
		
  List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
		
		System.out.println(alltext.size());
		for(int i=0;i<alltext.size();i++) {
			System.out.println(alltext.get(i).getText());
			}
		alltext.get(1).click();
		
		driver.findElement(By.xpath("//android.view.View[@text='1']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
		
		}
 
 /**
  * Selects <b>Yes</b> for the question <b>Is this your first child </b> during the Planning to have a baby on-boarding journey
  *    
  * @throws IOException thrown during an IO Exception
  * @throws InterruptedException thrown when a thread is interrupted    
  */  
 public void PHB_Is_this_your_first_child() throws IOException, InterruptedException {
	 List <MobileElement> alltext=driver.findElements(By.xpath("//android.widget.TextView"));
		
		System.out.println(alltext.size());
		for(int i=0;i<alltext.size();i++) {
			System.out.println(alltext.get(i).getText());
			}
		alltext.get(0).click();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Yes')]")).click();
		
		}
 
 /**
  * Selects <b>No</b> for the question <b>Is this your first child </b> during the My partner and I are pregnant on-boarding journey
  *    
  * @throws IOException thrown during an IO Exception
  * @throws InterruptedException thrown when a thread is interrupted    
  */   
 public void PRG_Is_this_your_first_child_Select_No() throws IOException, InterruptedException {
	 GenericFunctions.WaitForElement(driver,clickNo);
	 GenericFunctions.clickElement(driver, clickNo);
		}
 
 /**
  * Selects <b>Yes</b> for the question <b>Is this your first child </b> during the Planning to have a baby on-boarding journey
  *    
  * @throws IOException thrown during an IO Exception
  * @throws InterruptedException thrown when a thread is interrupted    
  */ 
public void PHB_Is_this_your_first_child_yes() throws IOException, InterruptedException {
	GenericFunctions.WaitForElement(driver,clickYes);
	GenericFunctions.clickElement(driver, clickYes);
		}

/**
 * Clicks on <b>Why only these two?</b> in the Gender screen of on-boarding journey
 */
public void PHB_Why_only_these_two()
{   GenericFunctions.WaitForElement(driver,theseTwo);
	GenericFunctions.clickElement(driver, theseTwo);
}

/**
 * Clicks on Continue button on <b>Why only these two?</b> in the Gender screen of on-boarding journey
 */
public void PHB_Why_only_these_two_continue()
{
	GenericFunctions.WaitForElement(driver,ContinueBtn);
	GenericFunctions.clickElement(driver, ContinueBtn);
}
	
/**
 * Enters Nationality ID
 */
public void enterNationalId()
{
	GenericFunctions.sendKeys(driver, NationalId, "1234567890123");
}

}