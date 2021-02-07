package com.pom.crimson.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.functions.GenericFunctions;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

/**
 * AddNewMomentsPage or New moment page can be used to add moments for primary profile or child profiles.
 * <br> 
 * User can navigate to this page by going to:
 * <br>
 * <br> 
 * Go to Home Page<br>
 * Click <b>{@value com.pom.crimson.util.Constants#MOMENTS_LOG_TITLE}</b> widget<br>
 * Click <b>{@value com.pom.crimson.util.Constants#GROWTH_LOG_ADDNEWMOMENT_LINK}</b> link
 * <br>
 * <br>
 * This page class contains MobileElements on this page 
 * and functions to simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class AddNewMomentsPage extends BasePage {
	
	//Page Objects
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_HEIGHT+" cm']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_HEIGHT+"']")
	private MobileElement heightField;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_WEIGHT+" kg']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_WEIGHT+"']")
	private MobileElement weightField;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_MYSELF_TAB+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_MYSELF_TAB+"']")
	private MobileElement myselfBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.MILESTONE_VACCINEDAY+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.MILESTONE_VACCINEDAY+"']")
	private MobileElement vaccineDayMilestone;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_ADD_ANOTHER_IMAGE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_ADD_ANOTHER_IMAGE+"']")
	private MobileElement selectAnotherImage;
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement crossBtnFirstImage;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_DEFAULT_IMAGE_SECTION_TEXT+"']")
	private MobileElement selectImage;
	
	// can't check camera in Ios Simulator
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='"+Constants.ADDNEWMOMENT_DONE+"']")
	private MobileElement cameraAccept;
	
	// can't check camera in Ios Simulator
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='"+Constants.ADDNEWMOMENT_CANCEL+"']")
	private MobileElement cameraReject;
	
	// can't check camera in Ios Simulator
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='"+Constants.ADDNEWMOMENT_RETAKE+"']")
	private MobileElement cameraRetake;
	
	//Not available in ios
	@AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ImageView")
	private MobileElement calenderPicker;
	
	//Not available in ios
	@AndroidFindBy(xpath="//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_OK+"']")
	private MobileElement okCalenderBtn;
	
	//Not available in ios
	@AndroidFindBy(xpath="//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_CANCEL+"']")
	private MobileElement cancelCalenderBtn;
	
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeOther[@name=''])[2]")
	@AndroidFindBy(xpath="//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private MobileElement crossBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_BUTTON_ALLOW+"']")
	@AndroidFindBy(xpath="//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_ALLOW+"']")
	private MobileElement allowBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_BUTTON_NEXT+"']")
	@AndroidFindBy(xpath="//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_NEXT+"']")
	private MobileElement nextBtn;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_DENY+"']")
	private MobileElement denyBtn;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='"+Constants.ADDNEWMOMENT_BUTTON_GOT_IT+"']")
	private MobileElement gotItBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_BUTTON_CANCEL+"']")
	private MobileElement cancelBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@label='"+Constants.ADDNEWMOMENT_TAKE_PHOTO+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_TAKE_PHOTO+"']")
	private MobileElement takePhotoBtn;
	
	//don't work in ios simulator take photo
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='"+Constants.ADDNEWMOMENT_SHUTTER+"']")
	private MobileElement clickPhotoShutterIcon;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeButton[@label='"+Constants.ADDNEWMOMENT_CHOOSE_FROM_LIBRARY+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,\""+Constants.ADDNEWMOMENT_CHOOSE_FROM_LIBRARY+"\")]")
	private MobileElement chooseFromLibraryBtn;
	
	//not required on ios
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_PICTURES+"']")
	private MobileElement picturesFolder;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_CAMERA+"']")
	private MobileElement cameraFolder;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeImage")
//	@AndroidFindBy(xpath="//android.view.ViewGroup[@clickable='true']")
	@AndroidFindBy(xpath="//android.view.ViewGroup[contains(@content-desc,\""+Constants.ADDNEWMOMENT_PHOTO+"\")]")

	private MobileElement imageOnPhone;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_NOTE_TEXT+"']")
	@AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText")
	private MobileElement noteField;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_DONE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_DONE+"']")
	private MobileElement doneBtn;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.EDITMOMENT_SAVEMOMENT+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.EDITMOMENT_SAVEMOMENT+"']")
	private MobileElement saveMoment;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@label='"+Constants.ADDNEWMOMENT_MANAGE_PROFILE+"']")
	@AndroidFindBy(xpath="//android.widget.TextView[@text='"+Constants.ADDNEWMOMENT_MANAGE_PROFILE+"']")
	private MobileElement manageProfileLink;
		
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\""+Constants.MILESTONE_PREG_LISTEN_HEARTBEAT+"\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,\""+Constants.MILESTONE_PREG_LISTEN_HEARTBEAT+"\")]")
	private MobileElement milestoneListenHeartbeat;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\""+Constants.MILESTONE_PREG_PICKING_BABYNAME+"\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,\""+Constants.MILESTONE_PREG_PICKING_BABYNAME+"\")]")
	private MobileElement milestonePickingBabyName;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\""+Constants.MILESTONE_PREG_LABOR_TIME+"\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,\""+Constants.MILESTONE_PREG_LABOR_TIME+"\")]")
	private MobileElement milestoneLaborTime;
	
	@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[@name=\""+Constants.MILESTONE_PREG_DECORATE_NURSERY+"\"]")
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,\""+Constants.MILESTONE_PREG_DECORATE_NURSERY+"\")]")
	private MobileElement milestoneDecoratingNursery;
	
	//Page Constructor
	
	public AddNewMomentsPage(AppiumDriver<MobileElement> driver ,String platformName)
	{
		super(driver,platformName);
	}
	
	//Page Functions
	
 //--------------------Create Moment Functions-----------------------------------------//	
	/**
	 * Create a moment with a Note only
	 * 
	 * @param note Note to be added
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithNote(String note,String profileName)
	{
		/* Create moment with  Note */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with note: "+note+" for : " +profileName);
		tapProfile(profileName);
		addNote(note);
		return tapDone();

	}
	
	/**
	 * Create a moment with  <b>Recommended milestones</b> only.
	 * <br>
	 * <b>Recommended milestones</b> are shown in <b>Whose moment is it?</b> section as per tab selected <b>Myself</b> or <i> preferred name of related child</i> tab
	 * <br>
	 * If a Archetype selected in <b>Profile</b> is <b>Parent to be </b> than pregnancy milestones will be displayed in <b>Myself</b> tab as per Due date.
	 * <br>
	 * If a related Child profile is added in Profile section than Recommended milestones will be displayed in <i> preferred name of related child</i> tab as per child's date of birth.
	 * 
	 * @param milestone Recommended milestone name 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithMilestone(String milestone,String profileName)
	{
		/* Create moment with Milestone */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with milestone: "+milestone+" for : " +profileName);
		tapProfile(profileName);
		tapMilestone(milestone);
		return tapDone();

	}
	
	/**
	 * Create a moment with image only.
	 * <br>
	 * 
	 * @param no_of_Images no. of images to be added 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithImage(int no_of_Images,String profileName)
	{
		/* Create moment with Image only */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with no_of_Images: "+no_of_Images+" for : " +profileName);

		addImage(no_of_Images);
		tapProfile(profileName);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Height only.
	 * <br>
	 * 
	 * @param height Height of Child
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithHeight(String height,String profileName)
	{
		/** Create moment with Height */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with height: "+height+" for : " +profileName);

		tapProfile(profileName);
		addHeight(height);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Weight only.
	 * <br>
	 * 
	 * @param weight Weight of Child
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithWeight(String weight,String profileName)
	{
		/** Create moment with Weight */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with weight: "+weight+" for : " +profileName);

		tapProfile(profileName);
		addWeight(weight);
		return tapDone();
	}
	
	/**
	 * Create a moment with Image and Note.
	 * 
	 * @param no_of_Images no. of images to be added 
	 * @param note Note to be added
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithImageNote(int no_of_Images,String note,String profileName)
	{
		/** Create moment with Image,Note */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with images: "+no_of_Images+" ,note: "+note+" for : " +profileName);
		addImage(no_of_Images);
		tapProfile(profileName);
		addNote(note);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Image and Height.
	 * 
	 * @param no_of_Images no. of images to be added 
	 * @param height Height to be added
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */	
	public MomentsLogPage createMomentWithImageHeight(int no_of_Images,String height,String profileName)
	{
		/** Create moment with Image,Height */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with images: "+no_of_Images+" ,height: "+height+" for : " +profileName);

		addImage(no_of_Images);
		tapProfile(profileName);
		addHeight(height);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Image and Weight.
	 * 
	 * @param no_of_Images no. of images to be added 
	 * @param weight Weight to be added
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithImageWeight(int no_of_Images,String weight,String profileName)
	{
		/** Create moment with Image,Weight */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with images: "+no_of_Images+" ,weight: "+weight+" for : " +profileName);
		addImage(no_of_Images);
		tapProfile(profileName);
		addWeight(weight);
		return tapDone();
	}
	
	/**
	 * Create a moment with Height and Weight.
	 * 
	 * @param height Height to be added
	 * @param weight Weight to be added
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithHeightWeight(String height,String weight,String profileName)
	{
		/** Create moment with Height and Weight */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with height: "+height+" ,weight: "+weight+" for : " +profileName);
		tapProfile(profileName);
		addHeight(height);
		addWeight(weight);
		return tapDone();
	}
	
	/**
	 * Create a moment with Note and <b>Recommended milestones</b>.
	 * 
	 * @param note Note to be added
	 * @param milestone Recommended milestone name 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithNoteMilestone(String note,String milestone,String profileName)
	{
		
		/* Create moment with Milestone and Note */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with milestone: "+milestone+" ,note: "+note+" for : " +profileName);

		tapProfile(profileName);
		tapMilestone(milestone);
		addNote(note);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Image,Note,<b>Recommended milestones</b>, Height, Weight.
	 * @param no_of_Images no. of images to be added 
	 * @param note Note to be added
	 * @param milestone Recommended milestone name
	 * @param height Height to be added
	 * @param weight Weight to be added 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithImageNoteMilestoneHeightWeight(int no_of_Images,String note,String milestone,String height,String weight,String profileName)
	{
		/** Create moment with Image,Note,Milestone,Height,Weight */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with no_of_Images: "+no_of_Images+" ,milestone: "+milestone+" ,note: "+note+" height: "+height+" weight: "+weight+" for : " +profileName);
		addImage(no_of_Images);
		tapProfile(profileName);
		addHeight(height);
		addWeight(weight);
		tapMilestone(milestone);
		addNote(note);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Note,<b>Recommended milestones</b>, Height, Weight.
	 * @param note Note to be added
	 * @param milestone Recommended milestone name
	 * @param height Height to be added
	 * @param weight Weight to be added 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithNoteMilestoneHeightWeight(String note,String milestone,String height,String weight,String profileName)
	{
		/* Create moment with Note,Milestone,Height,Weight */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with milestone: "+milestone+" ,note: "+note+" height: "+height+" weight: "+weight+" for : " +profileName);
		tapProfile(profileName);
		addHeight(height);
		addWeight(weight);
		tapMilestone(milestone);
		addNote(note);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with <b>Recommended milestones</b> and Weight.
	 * @param milestone Recommended milestone name
	 * @param weight Weight to be added 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithMilestoneWeight(String milestone,String weight,String profileName)
	{
		/** Create moment with Milestone and Weight */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with milestone: "+milestone+" weight: "+weight+" for : " +profileName);

		tapProfile(profileName);
		addWeight(weight);
		tapMilestone(milestone);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with <b>Recommended milestones</b> and Height.
	 * @param milestone Recommended milestone name
	 * @param height Height to be added 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithMilestoneHeight(String milestone,String height,String profileName)
	{
		/* Create moment with Milestone and Height */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with milestone: "+milestone+" height: "+height+" for : " +profileName);
		tapProfile(profileName);
		addHeight(height);
		tapMilestone(milestone);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Image,Note,<b>Recommended milestones</b>.
	 * @param no_of_Images no. of images to be added 
	 * @param note Note to be added
	 * @param milestone Recommended milestone name
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithImageNoteMilestone(int no_of_Images,String note,String milestone,String profileName)
	{
		/* Create moment with Image, Note, Milestone */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with no_of_Images: "+no_of_Images+" ,milestone: "+milestone+" ,note: "+note+" for : " +profileName);

		addImage(no_of_Images);
		tapProfile(profileName);
		tapMilestone(milestone);
		addNote(note);
		return tapDone();
		
	}
	
	/**
	 * Create a moment with Note, Height, Weight.
	 * @param note Note to be added
	 * @param height Height to be added
	 * @param weight Weight to be added 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 * @return Object of MomentsLogPage 
	 */
	public MomentsLogPage createMomentWithNoteHeightWeight(String note,String height,String weight,String profileName)
	{
		/** Create moment with Note,Height,Weight */
		ExtentReportManager.getTest().log(Status.INFO, "Creating a moment with note: "+note+" ,height: "+height+" ,weight: "+weight+" for : " +profileName);
		tapProfile(profileName);
		addHeight(height);
		addWeight(weight);
		addNote(note);
		return tapDone();
		
	}
	
	/**
	 * Taps on profile tab in <b> Whose moment is it?</b> section.
	 * 
	 * @param profileName tab to selected in <b> Whose moment is it?</b> section. (Values: <b>Myself</b> or  <i> preferred name of related child</i>)
	 */	
	public void tapProfile(String profileName)
	{
		if (profileName.equals("Myself"))
		{
			tapMyselfBtn();
		}
		else
		{
			tapChildBtn(profileName);
		}
	}
	
	//---------------------------- Date Selection Functions-----------------------------//
	
	//Date Functions cannot be used in ios
	
	/**
	 * Selects past calendar date.
	 * <br>
	 * Android only function
	 * @throws InterruptedException thrown when a thread is interrupted
	 */	
	public void selectCalendarDatePast() throws InterruptedException 
	{
		GenericFunctions.clickElement(driver, calenderPicker);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<MobileElement> DatesInCurrentMonth=driver.findElements(MobileBy.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@enabled='true']"));
		System.out.println("Total dates: "+DatesInCurrentMonth.size());
		if (DatesInCurrentMonth.size()==1)
		{//back to past month
			GenericFunctions.tapByCordinates(driver, 263, 619);
			driver.findElement(MobileBy.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@enabled='true']")).click();
			GenericFunctions.clickElement(driver,okCalenderBtn);
		}
		else
		{
			//will click pastest date in same month
			DatesInCurrentMonth.get(0).click();
			GenericFunctions.clickElement(driver,okCalenderBtn);
		}
	}
	
	/**
	 * Gets date from calendar which is currently selected.
	  * <br>
	 * Android only function
	 */
	public void getCheckedDate()
	{
		GenericFunctions.clickElement(driver, calenderPicker);
		MobileElement ele=driver.findElement(MobileBy.xpath("//android.view.View[@enabled='true' and @checked='true']"));
		String currentDate=GenericFunctions.getAccessibilityId(driver, ele, platformName);
		System.out.println("Accessibility id new: "+ele.getAttribute("content-desc"));

		System.out.println("Accessibility id: "+currentDate);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	SimpleDateFormat newFormat=new SimpleDateFormat("dd MMMMM yyyy");
		try {
			java.util.Date dDate = newFormat.parse( currentDate );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check today's date
		
		Date date1 = new Date();  
		System.out.println("Current date: "+newFormat.format(date1));  
		
		
	}
	
	/**
	 * Checks if current date is selected.
	  * <br>
	 * Android only function
	 * @return boolean value
	 * <br>
	 * true: current date is selected
	 * <br>
	 * false: current date is not selected
	 */
	public boolean verifyCurrentDateSelected()
	{
    	SimpleDateFormat newFormat=new SimpleDateFormat("dd MMMMM yyyy");
    	
    	//Check today's date
    	Date todayDate = new Date(); 
    	String currentDate=newFormat.format(todayDate);
		MobileElement ele=GenericFunctions.findElementByAccessibilityID(driver, currentDate, platformName);
		if (ele.getAttribute("enabled").equals("true")&& ele.getAttribute("checked").equals("true"))
			return true;
		return false;	
		
	}
	
	/**
	 * Checks if future date is selected.
	  * <br>
	 * Android only function
	 * @return boolean value
	 *  <br>
	 * true: future date is selected
	 *  <br>
	 * false: future date is not selected
	 */
	public boolean verifyFutureDateSelection()
	{
    	SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMMM yyyy");
    	
    	//Check today's date
    	Date todayDate = new Date(); 
    	Calendar c = Calendar.getInstance();
        c.setTime(todayDate);

       //Add 1 day today's date
        c.add(Calendar.DATE, 1); 
        Date futureDate = c.getTime();
    	String futureDateSt=dateFormat.format(futureDate);
		MobileElement ele=GenericFunctions.findElementByAccessibilityID(driver, futureDateSt, platformName);
		if (!(ele==null))
		{
		if (ele.getAttribute("enabled").equals("false")&& ele.getAttribute("checked").equals("false"))
			return true;
		else
			return false;
		}
		return true;	
	}
	
	/**
	 * Checks if past date is selected.
	  * <br>
	 * Android only function
	 * @return boolean value
	 *  <br>
	 * true: past date is selected
	 *  <br>
	 * false: past date is not selected
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public boolean verifyPastDateSelection() throws InterruptedException
	{
    	SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMMM yyyy");
    	
    	//Check today's date
    	Date todayDate = new Date(); 
    	
        LocalDateTime localDateTime = todayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime=localDateTime.minusDays(4);
        Date currentDateMinusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    	String pastDateSt=dateFormat.format(currentDateMinusOneDay);
		MobileElement ele=GenericFunctions.findElementByAccessibilityID(driver, pastDateSt, platformName);
		if (!(ele==null))
		{
				if (ele.getAttribute("enabled").equals("true"))
					return true;
			   else	
					return false;	
		} else
		{
			try {
				GenericFunctions.tapByCordinates(driver, 278, 742);
			} catch (InterruptedException e) {	e.printStackTrace();}
			ele=GenericFunctions.findElementByAccessibilityID(driver, pastDateSt, platformName);
			if (!(ele==null))
			{
				if (ele.getAttribute("enabled").equals("true"))
				return true;
					 else
			return false;
		}
	}
		return false;	
	}
	
	/**
	 * Selects calendar date in past month.
	  * <br>
	 * Android only function
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void selectCalendarDatePastMonth() throws InterruptedException
	{
		GenericFunctions.clickElement(driver, calenderPicker);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			GenericFunctions.tapByCordinates(driver, 263, 619);
			driver.findElement(MobileBy.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@enabled='true']")).click();
			GenericFunctions.clickElement(driver,okCalenderBtn);
	}
	
	/**
	 * Selects calendar date in past year.
	  * <br>
	 * Android only function
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public void selectCalendarDatePastYear() throws InterruptedException
	{
		GenericFunctions.clickElement(driver, calenderPicker);
		MobileElement yearHeader=driver.findElement(MobileBy.xpath("//android.widget.TextView[@resource-id='android:id/date_picker_header_year']"));
		String currentYear=yearHeader.getAttribute("text");
		String pastYear=currentYear;
		do {
			GenericFunctions.tapByCordinates(driver, 263, 619);
			String content_desc=driver.findElement(MobileBy.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@enabled='true']")).getAttribute("content-desc");
			System.out.println("content_des"+content_desc);
			String[] arr=content_desc.split("\\s");
			System.out.println(arr);
			System.out.println(arr.length);
			System.out.println("past year"+arr[2]);
			pastYear=arr[2];
		}while(pastYear.equals(currentYear)); 
		
		driver.findElement(MobileBy.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[@enabled='true']")).click();
		GenericFunctions.clickElement(driver,okCalenderBtn);
	}
	
	
	//---------------------------- Add Functions-----------------------------//
	
	/**
	 * Simulates typing of note in text field
	 *
	 * @param note text note to be typed in
	 */
	public void addNote(String note) {
		GenericFunctions.scroll(driver,"Note", platformName);
		GenericFunctions.sendKeys(driver, noteField, note);
		ExtentReportManager.getTest().log(Status.INFO, "Added following note: "+note);
	}
	
	/**
	 * Simulates typing of height in text field
	 *
	 * @param height height to be typed in
	 */
	public void addHeight(String height) {
		GenericFunctions.scroll(driver,"Height", platformName);
		GenericFunctions.clickElement(driver, heightField);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) 
		{
			e1.printStackTrace();
		}
		GenericFunctions.pressNumPadKeyForString(driver,height,platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Added following Height: "+height);
	}
	
	/**
	 * Simulates typing of weight in text field
	 *
	 * @param weight weight to be typed in
	 */
	public void addWeight(String weight) {
		GenericFunctions.scroll(driver,"Weight", platformName);
		GenericFunctions.clickElement(driver, weightField);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) 
		{
			e1.printStackTrace();
		}
		GenericFunctions.pressNumPadKeyForString(driver,weight,platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Added following Weight: "+weight);
	}

	/**
	 * Adds Image from device library.
	 * <br> 
	 * In app, Image can be added by clicking on <b> {@value com.pom.crimson.util.Constants#ADDNEWMOMENT_DEFAULT_IMAGE_SECTION_TEXT}</b> section and 
	 * by Taking photo from camera or Choosing from Library.
	 * <br>
	 * Android only function
	 * @param no_of_images no. of images to be added
	 * @return boolean value
	 *  <br>
	 * true: image is added
	 *  <br>
	 * false: image is not added
	 */
	public boolean addImage(int no_of_images)
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on "+Constants.ADDNEWMOMENT_DEFAULT_IMAGE_SECTION_TEXT);

		GenericFunctions.clickElement(driver, selectImage);
		chooseFromLibrary();
		for (int i=1;i<no_of_images;i++)
		{
		try {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on "+Constants.ADDNEWMOMENT_ADD_ANOTHER_IMAGE);

		GenericFunctions.clickElement(driver, selectAnotherImage);
		} catch(Exception e)
		{
			return false;
		}
		chooseFromLibrary();
		}
		ExtentReportManager.getTest().log(Status.INFO, "Added "+no_of_images+" images from library");
		return true;
	}

	//---------------------------- Photo Selection Functions-----------------------------//
	
	/**
	 * Taps on <b> {@value com.pom.crimson.util.Constants#ADDNEWMOMENT_CHOOSE_FROM_LIBRARY}</b> option.
	 * Android only function
	 */
	public void chooseFromLibrary()
	{
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on button: "+Constants.ADDNEWMOMENT_CHOOSE_FROM_LIBRARY);

		GenericFunctions.clickElement(driver, chooseFromLibraryBtn);
		try {
			if(allowBtn.isDisplayed())
			{
				GenericFunctions.clickElement(driver, allowBtn);	
			}
			
		}catch(Exception e){
			System.out.println("Allow button not present");
		}
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on pictures/camera Folder on device");
			GenericFunctions.clickElement(driver, cameraFolder);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ExtentReportManager.getTest().log(Status.INFO, "Selecting image from pictures Folder");
		GenericFunctions.clickElement(driver, imageOnPhone);
	}
	
	/**
	 * Simulates taking picture from camera and re-taking same.
	 * <br>
	 * Taps on <b>Take Photo</b>, Takes photo from camera and clicks on retake option on snapped picture.
	 * Android only function
	 */
	public void takePhotoAndRetake()
	{
		takePhoto();
		try {
			ExtentReportManager.getTest().log(Status.INFO, "Clicking on camera retake button");
			GenericFunctions.clickElement(driver, cameraRetake);
			//GenericFunctions.tapByCordinates(driver, 112, 1630);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Simulates taking picture from camera and canceling same.
	 * <br>
	 * Taps on <b>Take Photo</b>, Takes photo from camera and clicks on cancel option on snapped picture.
	 * Android only function
	 */
	public void takePhotoAndCancel()
	{
		takePhoto();
		try {
			ExtentReportManager.getTest().log(Status.INFO, "Clicking on camera reject button");

			GenericFunctions.clickElement(driver, cameraReject);
			//GenericFunctions.tapByCordinates(driver, 972, 1630);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Simulates taking picture from camera and accepting same.
	 * <br>
	 * Taps on <b>Take Photo</b>, Takes photo from camera and clicks on accept option on snapped picture.
	 * Android only function
	 */
    public void takePhotoAndAccept()
	{
		takePhoto();
		try {
			GenericFunctions.clickElement(driver, cameraAccept);
			//GenericFunctions.tapByCordinates(driver, 530, 1630);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
    /**
	 * Simulates taking picture from camera. 
	 * <br>
	 * Taps on <b>Take Photo</b> link, Takes photo from camera
	 * Android only function
	 */
	private void takePhoto() {
		ExtentReportManager.getTest().log(Status.INFO, "Clicking on pictures Folder");

		GenericFunctions.clickElement(driver, selectImage);
		GenericFunctions.clickElement(driver, takePhotoBtn);
		try {
			if(allowBtn.isDisplayed())
			{
				GenericFunctions.clickElement(driver, allowBtn);
				GenericFunctions.clickElement(driver, allowBtn);
				GenericFunctions.clickElement(driver, allowBtn);
			}
			
		}catch(Exception e){
			System.out.println("Allow button not present");
		}
		
		try {
			GenericFunctions.clickElement(driver, nextBtn);
		}catch(Exception e){
			System.out.println("Next button not present");
		}
		
		GenericFunctions.clickElement(driver, clickPhotoShutterIcon);
	}
	
	//---------------------------- Verification Functions-----------------------------//
	
	/**
	 * Verifies if <b>Take Photo</b>  MobileElement is displayed.
	 * <br>
	 * Taps on <b>Add here your special capture of the day</b> section first and checks if <b>Take Photo</b>  is displayed.
	 * <br>
	 * Android only function
	* @return boolean value
	 *  <br>
	 * true: <b>Take Photo</b> MobileElement is displayed
	 *  <br>
	 * false: <b>Take Photo</b> MobileElement is not displayed
	 */
	public boolean verifyTakePhoto()
	{
		GenericFunctions.clickElement(driver, selectImage);
		return GenericFunctions.isElementDisplayed(takePhotoBtn);
	}

	/**
	 * Verifies when user accepts camera photo,User lands back on New Moment screen and option to add another image is displayed.
	 * <br>
	 * Taps on <b>Add here your special capture of the day</b> section first and takes camera photo and accepts same.
	 * Checks if <b>Add another image</b> MobileElement is displayed.
	 * <br>
	 * Android only function.
	* @return boolean value
	 *  <br>
	 * true: Option to select another image on New Moment screen is displayed
	 *  <br>
	 * false: Option to select another image on New Moment screen is not displayed
	 */
	public boolean verifyAccept() {
		takePhotoAndAccept();
		if ((GenericFunctions.isElementDisplayed(selectImage)==false)|| (GenericFunctions.isElementDisplayed(selectAnotherImage)==true))
			return true;
		return false;
		}
	
	/**
	 * Verifies when user cancels camera photo,User lands back on New Moment screen.
	 * <br>
	 * Taps on <b>Add here your special capture of the day</b> section first and takes camera photo and cancels same.
	 * Checks if <b>Add here your special capture of the day</b> MobileElement or <b> Add another image </b> element is displayed on New Moment screen
	 * <br>
	 * Android only function
	* @return boolean value
	 *  <br>
	 * true: New Moment screen is displayed
	 *  <br>
	 * false: New Moment screen is not displayed
	 */
	public boolean verifycancel() {
	takePhotoAndCancel();
	boolean statusSelectImage=GenericFunctions.isElementDisplayed(selectImage);
	System.out.println("statusSelectImage: "+statusSelectImage);
	
	boolean statusAnotherImage=GenericFunctions.isElementDisplayed(selectAnotherImage);
	System.out.println("statusSelectAnotheeImage: "+statusAnotherImage);

	if (statusSelectImage || statusAnotherImage )
		{
		System.out.println("Status true");
		return true;
		
		}
	return false;
		
	}

	/**
	 * Verifies when user takes camera photo and clicks option to re-take it, User remains on camera screen.
	 * <br>
	 * Taps on <b>Add here your special capture of the day</b> section first and takes camera photo and than clicks on re-take.
	 * Checks if camera shutter MobileElement is displayed on camera screen
	 * <br>
	 * Android only function
	* @return boolean value
	 *  <br>
	 * true: Camera screen is displayed
	 *  <br>
	 * false: Camera screen is not displayed
	 */
	public boolean verifyRetake() {
		takePhotoAndRetake();
		return GenericFunctions.isElementDisplayed(clickPhotoShutterIcon);
	}
	
	/**
	 * Verifies when user takes camera photo and clicks option to re-take it, User remains on camera screen.
	 * <br>
	 * Taps on <b>Add here your special capture of the day</b> section first and takes camera photo and than clicks on re-take.
	 * Checks if camera shutter MobileElement is displayed on camera screen
	 * <br>
	 * Android only function
	* @return boolean value
	 *  <br>
	 * true: Camera screen is displayed
	 *  <br>
	 * false: Camera screen is not displayed
	 */
	public boolean verifyChooseFromLibrary() {
		GenericFunctions.clickElement(driver, selectImage);
		return GenericFunctions.isElementDisplayed(chooseFromLibraryBtn);
	}
		
	/**
	 * Verifies if <b> {@value com.pom.crimson.util.Constants#MILESTONE_PREG_LABOR_TIME} </b> milestone is displayed
	 *
	 * @return boolean value
	 * <br>
	 * true: milestone is displayed
	 * <br> 
	 * false: milestone is not displayed
	 */
	public boolean verifyLaborTimeMilestoneDisplayed()
	{
		return GenericFunctions.isElementDisplayed(milestoneLaborTime);
	}
	
	/**
	 * Verifies if <b> {@value com.pom.crimson.util.Constants#MILESTONE_PREG_DECORATE_NURSERY} </b> milestone is displayed
	 *
	 * @return boolean value
	 * <br>
	 * true: milestone is displayed
	 * <br> 
	 * false: milestone is not displayed
	 */
	public boolean verifyDecoratingNurseryMilestoneDisplayed()
	{
		return GenericFunctions.isElementDisplayed(milestoneDecoratingNursery);
	}
	
	/**
	 * Verifies if <b> {@value com.pom.crimson.util.Constants#MILESTONE_PREG_PICKING_BABYNAME} </b> milestone is displayed
	 *
	 * @return boolean value
	 * <br>
	 * true: milestone is displayed
	 * <br> 
	 * false: milestone is not displayed
	 */
	public boolean verifyPickingBabyNameMilestoneDisplayed()
	{
		return GenericFunctions.isElementDisplayed(milestonePickingBabyName);
	}
	
	/**
	 * Verifies if <b> {@value com.pom.crimson.util.Constants#MILESTONE_PREG_LISTEN_HEARTBEAT} </b> milestone is displayed
	 *
	 * @return boolean value
	 * <br>
	 * true: milestone is displayed
	 * <br> 
	 * false: milestone is not displayed
	 */
	public boolean verifyListenHeartBeatMilestoneDisplayed()
	{
		return GenericFunctions.isElementDisplayed(milestoneListenHeartbeat);
	}
	
	//---------------------------- Tap Functions-----------------------------//
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#MANAGE_PROFILE_TITLE}</b>.
	 * 
	 * @return ManageProfilePage Object of ManageProfilePage
	 * 
	 */
	public ManageProfilePage tapManageProfileLink()
	{
		GenericFunctions.scroll(driver,"You can add/edit family members under Manage Profiles", platformName);
		GenericFunctions.clickElement(driver, manageProfileLink);
		ExtentReportManager.getTest().log(Status.INFO, "Clicked Manage Profile link");
		ManageProfilePage manageProfilePage=new ManageProfilePage(driver,platformName);
		return manageProfilePage;
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#ADDNEWMOMENT_MYSELF_TAB}</b>.
	 * 
	 */
	public void tapMyselfBtn()
	{
		GenericFunctions.scroll(driver,"Myself", platformName);
		GenericFunctions.clickElement(driver, myselfBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Selected profile as Myself");
	}
	
	/**
	 * Taps on Child profile tab.
	 * 
	 * @param childPreferedName Preferred name of child
	 */
	public void tapChildBtn(String childPreferedName)
	{
		GenericFunctions.scroll(driver,childPreferedName, platformName);
		MobileElement ele=GenericFunctions.findElementByText(driver, childPreferedName, platformName);
		GenericFunctions.clickElement(driver, ele);
		ExtentReportManager.getTest().log(Status.INFO, "Selected profile as :"+childPreferedName);
	}
	
	/**
	 * Taps on <b>{@value com.pom.crimson.util.Constants#ADDNEWMOMENT_DONE}</b> button.
	 * 
	 * @return Object of MomentsLogPage
	 */
	public MomentsLogPage tapDone()
	{
		GenericFunctions.scroll(driver,"Done", platformName);
		GenericFunctions.clickElement(driver, doneBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Clicked Done button");
		MomentsLogPage momentsLogPage=new MomentsLogPage(driver,platformName);
		return momentsLogPage;
	}
	
	/**
	 * Taps on Recommended milestone.
	 * 
	 * @param MilestoneName Milestone name to be selected
	 */
	public void tapMilestone(String MilestoneName)
	{
		GenericFunctions.scroll(driver, MilestoneName, platformName);
		MobileElement ele=GenericFunctions.findElementByText(driver, MilestoneName, platformName);
		GenericFunctions.clickElement(driver, ele);
		ExtentReportManager.getTest().log(Status.INFO, "Selected following Milestone: "+MilestoneName);
	}
	
	/**
	 * Taps on cross button.
	 * 
	 */
	public void tapCrossBtn()
	{
		GenericFunctions.scroll(driver, "New moment", platformName);
		GenericFunctions.clickElement(driver, crossBtn);
		ExtentReportManager.getTest().log(Status.INFO, "Clicked Cross Button on New Moment Page");
	}
	
	/**
	 * Taps on cross button on First image selected in <b>{@value com.pom.crimson.util.Constants#ADDNEWMOMENT_DEFAULT_IMAGE_SECTION_TEXT}</b> section.
	 * 
	 */
	public void tapDeleteFirstImage()
	{
		GenericFunctions.scroll(driver, "New moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Trying to delete First Image by clicking cross button");
		GenericFunctions.clickElement(driver,crossBtnFirstImage );
	}
	
	/**
	 * Taps on calendar icon.
	 * 
	 */
	public void tapCalendar()
	{
		GenericFunctions.scroll(driver, "New moment", platformName);
		ExtentReportManager.getTest().log(Status.INFO, "Trying to tap calendar icon");
		GenericFunctions.clickElement(driver,calenderPicker );
	}
}
