package com.pom.crimson.functions;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.pom.crimson.base.BasePage;
import com.pom.crimson.util.Constants;
import com.pom.crimson.util.ExtentReportManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.clipboard.HasClipboard;

/** GenericFunctions class contains functions related to 
 * taking action on Mobile Elements displayed on screen like click,clear, sendkeys, wait etc,
 * functions to perform scrolling on screen,
 * functions to take screenshot, copy text , press Numpad keys on keboard
 * and other Generic functions which can be used to create Test data 
 * like generate random alphanumeric string, past date future dates etc.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class GenericFunctions extends BasePage {

	public GenericFunctions(AppiumDriver<MobileElement> driver, String platformName) {
		super(driver, platformName);
	}

	/*----- Mobile Element specific Functions----*/
	
	/**
	 * Clicks MobileElement.
	 * 
	 * @param driver AppiumDriver instance
	 * @param ele    MobileElement
	 */
	public static void clickElement(AppiumDriver<MobileElement> driver, MobileElement ele) {
		WaitForElement(driver, ele);
		ele.click();
	}

	/**
	 * Resets value of Form entry element.
	 * 
	 * @param driver AppiumDriver instance
	 * @param ele    MobileElement
	 */
	public static void clear(AppiumDriver<MobileElement> driver, MobileElement ele) {
		WaitForElement(driver, ele);
		ele.clear();
	}

	/**
	 * Simulates typing into an element by first clearing initial text in element.
	 * 
	 * @param driver AppiumDriver instance
	 * @param ele    MobileElement
	 * @param entryText text to be typed into element
	 */
	public static void sendKeys(AppiumDriver<MobileElement> driver, MobileElement ele, String entryText) {
		WaitForElement(driver, ele);
		clear(driver, ele);
		ele.sendKeys(entryText);
	}
	
	/**
	 * Simulates typing into an element without clearing initial text in element.
	 * 
	 * @param driver AppiumDriver instance
	 * @param ele    MobileElement
	 * @param entryText text to be typed into element
	 */
	public static void sendKeysWithoutClearingInitialText(AppiumDriver<MobileElement> driver, MobileElement ele,
			String entryText) {
		WaitForElement(driver, ele);
		ele.sendKeys(entryText);
	}

	/**
	 * Returns size of MobileElement list.
	 * 
	 * @param ele List of MobileElements
	 * @return size of MobileElement list. Value will be int.
	 */
	public static int checkSizeoflist(List<MobileElement> ele) {
		return ele.size();
	}

	/**
	 * Gets the value of the given attribute of the element.
	 * 
	 * @param driver AppiumDriver instance
	 * @param ele    MobileElement
	 * @param attribute Attribute name
	 * @return Attribute value as String 
	 */
	public static String getAttribute(AppiumDriver<MobileElement> driver, MobileElement ele, String attribute) {
		WaitForElement(driver, ele);
		return ele.getAttribute(attribute);
	}

	/**
	 * Makes AppiumDriver driver wait until MobileElement is visible. Max wait time is 20 sec.
	 * 
	 * @param driver AppiumDriver instance
	 * @param ele MobileElement
	 * @return Boolean value.
	 * 
	 * true : if MobileElement is visible. 
	 * 
	 * false : if MobileElement is not visible. 
	 */
	public static Boolean WaitForElement(AppiumDriver<MobileElement> driver, MobileElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(ele));
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Specifies the amount of time the driver should wait when searching for an element if it is not immediately present. 
	 * 
	 * Max wait time is 5 sec
	 * @param driver AppiumDriver instance
	 * 
	 */
	public static void setImplicitlyWait(AppiumDriver<MobileElement> driver) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	/**
	 * Checks if MobileElement is displayed.
	 *
	 * @param ele MobileElement
	 * @return Boolean value.
	 * 
	 * true : if MobileElement is displayed. 
	 * 
	 * false : if MobileElement is not displayed. 
	 */
	public static Boolean isElementDisplayed(MobileElement ele) {
		try {
			ele.isDisplayed();
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Gets text of MobileElement.
	 *
	 * @param driver AppiumDriver instance
	 * @param ele    MobileElement
	 * @param platformName Platform name (Android or iOS)
	 * @return Text of MobileElement.Value will be String.
	 */
	public static String getText(AppiumDriver<MobileElement> driver, MobileElement ele, String platformName) {
		WaitForElement(driver, ele);
		String txt = null;
		switch (platformName) {
		case "Android":
			txt = getAttribute(driver, ele, "text");
			break;
		case "iOS":
			txt = getAttribute(driver, ele, "label");
			break;
		}

		return txt;
	}

	/**
	 * Gets AccessibilityId of MobileElement.
	 *
	 * @param driver AppiumDriver instance
	 * @param ele    MobileElement
	 * @param platformName Platform name (Android or iOS)
	 * @return AccessibilityId.Value will be String.
	 */
	public static String getAccessibilityId(AppiumDriver<MobileElement> driver, MobileElement ele,
			String platformName) {
		WaitForElement(driver, ele);
		String txt = "";
		switch (platformName) {
		case "Android":
			txt = getAttribute(driver, ele, "content-desc");
			break;
		case "iOS":
			txt = getAttribute(driver, ele, "accessibility-id");
			break;
		}

		return txt;
	}

	/**
	 * Drags and Drops from one MobileElement to another MobileElement.
	 *
	 * @param driver AppiumDriver instance
	 * @param from  From MobileElement
	 * @param to To MobileElement
	 */
	public static void dragAndDrop(AppiumDriver<MobileElement> driver, MobileElement from, MobileElement to) {
		WaitForElement(driver, from);
		WaitForElement(driver, to);

		int middleX = from.getLocation().x + (from.getSize().width / 2);// to start from center
		int toY = to.getLocation().y + (to.getSize().height / 2);// to start from center
		int startY = from.getLocation().y + (from.getSize().height / 2);

		TouchAction act = new TouchAction(driver);
		act.longPress(PointOption.point(middleX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(middleX, toY))
				.release().perform();
	}

	/**
	 * Finds MobileElement of type text by text.
	 *
	 * @param driver AppiumDriver instance
	 * @param text Text of MobileElement to be searched
	 * @param platformName Platform name (Android or iOS)
	 * @return MobileElement if found
	 */
	public static MobileElement findElementByText(AppiumDriver<MobileElement> driver, String text,
			String platformName) {
		MobileElement ele = null;
		if (platformName.equals("Android"))
			ele = androidFindElementByText(driver, text);
		else if (platformName.equals("iOS"))
			ele = iOSFindElementByText(driver, text);
		;
		return ele;

	}
	
	/**
	 * Finds MobileElement of type Edit Text by text.
	 *
	 * @param driver AppiumDriver instance
	 * @param text Edit text of MobileElement to be searched
	 * @param platformName Platform name (Android or iOS)
	 * @return MobileElement if found
	 */
	public static MobileElement findElementByEditText(AppiumDriver<MobileElement> driver, String text,
			String platformName) {
		MobileElement ele = null;
		if (platformName.equals("Android"))
			ele = androidFindElementByEditText(driver, text);
		else if (platformName.equals("iOS"))
			ele = iOSFindElementByText(driver, text);
		;
		return ele;

	}

	/**
	 * Finds MobileElement by AccessibilityID text.
	 *
	 * @param driver AppiumDriver instance
	 * @param text Accessibility ID text
	 * @param platformName Platform name (Android or iOS)
	 * @return MobileElement if found
	 */
	public static MobileElement findElementByAccessibilityID(AppiumDriver<MobileElement> driver, String text,
			String platformName) {
		MobileElement ele = null;
		try {
			ele = driver.findElement(MobileBy.AccessibilityId(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}

	/**
	 * Finds MobileElement of type button by text.
	 *
	 * @param driver AppiumDriver instance
	 * @param text Visible text of MobileElement
	 * @param platformName Platform name (Android or iOS)
	 * @return MobileElement if found
	 */
	public static MobileElement findButtonElementByText(AppiumDriver<MobileElement> driver, String text,
			String platformName) {
		System.out.println(platformName);
		MobileElement ele = null;
		if (platformName.equals("Android"))
			ele = androidFindButtonElementByText(driver, text);
		else if (platformName.equals("iOS"))
			ele = iOSFindElementByText(driver, text);
		;
		return ele;

	}
	
	/**
	 * Finds MobileElement of type text by text(Method specific to Android platform).
	 *
	 * @param driver AppiumDriver instance
	 * @param text Text of MobileElement to be searched
	 * @return MobileElement if found
	 */
	public static MobileElement androidFindElementByText(AppiumDriver<MobileElement> driver, String text) {
		MobileElement ele = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='" + text + "\']"));
		return ele;
	}

	/**
	 * Finds MobileElement of type Edit text by text(Method specific to Android platform).
	 *
	 * @param driver AppiumDriver instance
	 * @param text Text of MobileElement to be searched
	 * @return MobileElement if found
	 */
	public static MobileElement androidFindElementByEditText(AppiumDriver<MobileElement> driver, String text) {
		MobileElement ele = driver.findElement(MobileBy.xpath("//android.widget.EditText[@text='" + text + "\']"));
		return ele;
	}
	
	/**
	 * Finds MobileElement of type button by text(Method specific to Android platform).
	 *
	 * @param driver AppiumDriver instance
	 * @param text Text of MobileElement to be searched
	 * @return MobileElement if found
	 */
	public static MobileElement androidFindButtonElementByText(AppiumDriver<MobileElement> driver, String text) {
		MobileElement ele = driver.findElement(MobileBy.xpath("//android.widget.Button[@text='" + text + "\']"));
		return ele;
	}

	/**
	 * Finds MobileElement of type text by text(Method specific to iOS platform).
	 *
	 * @param driver AppiumDriver instance
	 * @param text Text of MobileElement to be searched
	 * @return MobileElement if found
	 */
	public static MobileElement iOSFindElementByText(AppiumDriver<MobileElement> driver, String text) {
		MobileElement ele = driver.findElement(MobileBy.name(text));
		return ele;
	}

	/** 
	 * Finds MobileElement by Strategy(Method specific to Android platform).
	 * Use this function when either text or content-desc or resource-id or class is
	 * known for Android Element.
	 * @param driver AppiumDriver instance
	 * @param locatorStrategy Locator Strategy to use ( Values=text,content-desc,resource-id,class)
	 * @param locator value specific to locator strategy
	 * @return MobileElement if found
	 */
	public static MobileElement findElementByStrategy(AppiumDriver<MobileElement> driver, String locatorStrategy,
			String locator) {

		MobileElement ele = null;

		if (locatorStrategy.equals(Constants.TEXT))
			ele = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='" + locator + "\']"));
		else if (locatorStrategy.equals(Constants.CONTENTDESC))
			ele = driver.findElement(MobileBy.AccessibilityId(locator));
		else if (locatorStrategy.equals(Constants.RESOURCEID))
			ele = driver.findElement(MobileBy.id(locator));
		else if (locatorStrategy.equals(Constants.CLASSNAME)) {
			ele = driver.findElement(MobileBy.className(locator));
		}

		return ele;
	}

	/**
	 * Taps on screen by Coordinates
	 *
	 * @param driver AppiumDriver instance
	 * @param x  x coordinate
	 * @param y  y coordinate
	 * @throws InterruptedException thrown when a thread is interrupted
	 */
	public static void tapByCordinates(AppiumDriver<MobileElement> driver, int x, int y) throws InterruptedException  {
		Thread.sleep(4000);
		TouchAction act = new TouchAction(driver);
		act.tap(PointOption.point(x, y)).perform();

		Thread.sleep(3000);

	}

	/**
	 * Scrolls Mobile screen up and down until visible text of MobileElement is found(Method specific to Android platform).
	 *
	 * @param driver AppiumDriver instance
	 * @param text Visible text of MobileElement
	 */
	public static void androidScroll(AppiumDriver<MobileElement> driver, String text) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			MobileElement ele = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
					+ ".scrollable(true)).scrollIntoView(" + "new UiSelector().textContains(\"" + text + "\"));"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scrolls Mobile screen up and down until visible text of MobileElement is found (Method specific to iOS platform).
	 *
	 * @param driver AppiumDriver instance
	 * @param text Visible text of MobileElement
	 */
	public static void iOSScroll(AppiumDriver<MobileElement> driver, String text) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			MobileElement element = driver.findElement(MobileBy.AccessibilityId(text));
			String elementID = element.getId();
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("element", elementID);
			scrollObject.put("toVisible", "true");
			driver.executeScript("mobile:scroll", scrollObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Scrolls Mobile screen up and down until visible text of MobileElement is found
	 *
	 * @param driver AppiumDriver instance
	 * @param text Visible text of MobileElement
	 * @param platformName Platform name (Android or iOS)
	 */
	public static void scroll(AppiumDriver<MobileElement> driver, String text, String platformName) {
		if (platformName.equalsIgnoreCase("Android"))
			androidScroll(driver, text);
		else if (platformName.equalsIgnoreCase("iOS"))
			iOSScroll(driver, text);
		;
	}

	/*----- Screen navigation Functions----*/
	
	/**
	 * Scrolls Mobile screen by Coordinates
	 *
	 * Scrolls from bottom (80%) screen to up (20%) screen
	 * @param driver AppiumDriver instance
	 */
	public static void scrollByCordinates(AppiumDriver<MobileElement> driver) {
		Dimension dim = driver.manage().window().getSize();

		int width = dim.getWidth();
		int height = dim.getHeight();

		int middlex = width / 2;
		int starty = (int) (height * 0.8);
		int endy = (int) (height * 0.2);

		TouchAction act = new TouchAction(driver);
		act.press(PointOption.point(middlex, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(middlex, endy)).release().perform();

	}
	
	/**
	 * Scrolls up mobile screen
	 *
	 * @param driver AppiumDriver instance
	 */
	public static void scrollByCordinatesUp(AppiumDriver<MobileElement> driver) {
		Dimension dim = driver.manage().window().getSize();

		int width = dim.getWidth();
		int height = dim.getHeight();

		int middlex = width / 2;
		int starty = (int) (height * 0.8);
		int endy = (int) (height * 0.2);

		TouchAction act = new TouchAction(driver);
		act.press(PointOption.point(middlex, endy)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(middlex, starty)).release().perform();

	}
	
	/**
	 * Scrolls to left on mobile screen
	 *
	 * @param driver AppiumDriver instance
	 * @param y coordinates 
	 */
	public static void scrollByCordinatesLeft(AppiumDriver<MobileElement> driver, int y) {
		Dimension dim = driver.manage().window().getSize();

		int width = dim.getWidth();
		int height = dim.getHeight();

		int startx = (int) (width * 0.4);
		int endx = (int) (width * 0.2);

		TouchAction act = new TouchAction(driver);
		act.press(PointOption.point(endx, y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(startx, y)).release().perform();
	}

	/**
	 * Scrolls to Right on mobile screen
	 *
	 * @param driver AppiumDriver instance
	 * @param y coordinates 

	 */
	public static void scrollByCordinatesRight(AppiumDriver<MobileElement> driver, int y) {
		Dimension dim = driver.manage().window().getSize();

		int width = dim.getWidth();
		int height = dim.getHeight();

		int startx = (int) (width * 0.4);
		int endx = (int) (width * 0.2);

		TouchAction act = new TouchAction(driver);
		act.press(PointOption.point(startx, y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(endx, y)).release().perform();
	}
	
	/*----- Other Driver related Functions---*/
	
	/** 
	 * Gets text from clipboard.
	 * @param driver AppiumDriver instance
	 * @return text from clipboard. Value will be String
	 */
	public static String getTextfromClipboard(AppiumDriver<MobileElement> driver) {
		String text = ((HasClipboard) driver).getClipboardText();
		return text;
	}
	
	/** 
	 * Takes screenshot.
	 * 
	 * @param driver AppiumDriver instance
	 */
	public static void takeScreenshot(AppiumDriver<MobileElement> driver) {
		TakesScreenshot scr = (TakesScreenshot) driver;
		File sourceFile = scr.getScreenshotAs(OutputType.FILE);

		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(sourceFile));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ExtentReportManager.getTest().log(Status.INFO, ("Taking Screenshot"), MediaEntityBuilder
					.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 * Presses keys on Num Pad on keyboard.
	 * Presses following keys : numbers from 0-9,Period (.),Comma (,), Apostrophe(') on keyboard on Mobile screen
	 * Simulates typing of numbers (example: 12.34) on mobile keyboard
	 * @param driver AppiumDriver instance
	 * @param enteredText Text entered on keyboard (example 12.34)
	 * @param platformName Platform name (Android or iOS)
	 * 
	 */
	public static void pressNumPadKeyForString(AppiumDriver<MobileElement> driver, String enteredText, String platformName) {
		int size = enteredText.length();
		for (int i = 0; i < size; i++) {
			char temp = enteredText.charAt(i);

			switch (temp) {

			case '0':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
				break;
			case '1':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
				break;
			case '2':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
				break;
			case '3':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
				break;
			case '4':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
				break;
			case '5':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_5));
				break;
			case '6':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_6));
				break;
			case '7':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_7));
				break;
			case '8':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_8));
				break;
			case '9':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_9));
				break;
			case '.':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.PERIOD));
				break;
			case ',':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.COMMA));
				break;
			case '\'':
				((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.APOSTROPHE));
				break;
			default:
				ExtentReportManager.getTest().log(Status.INFO, "Invalid data");

			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

	}
	
	/*----- Generic Helper Functions for creating Test data ----*/
	
	/** 
	 * Generates random alphanumeric String of 20 characters.
	 * @return alphanumric String of 20 characters
	 */
	public static String generateRandomAlphanumericString() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 20;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}
	
	
	/** 
	 * Generates random alphanumeric String
	 * @param length length of String to be generated
	 * @return alphanumeric String of specified length
	 */
	public static String generateRandomAlphanumericStringLength(int length) {
		/** Generates random alphanumeric String of 20 characters */
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = length;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}
	

	/** 
	 * Generates random double of two decimal places in String format
	 * @return random double of two decimal places as String 
	 */
	public static String generateRandomDoubleInStringFormat() {

		double leftLimit = 1D;
		double rightLimit = 100D;
		double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);

		String st = String.format("%.2f", generatedDouble);
		st = st.contains(".") ? st.replaceAll("0*$", "").replaceAll("\\.$", "") : st;

		// return String.format("%.2f", generatedDouble);
		return st;
	}
	

	/** 
	 * Find red color value of an element by taking screenshot
	 * 
	 * @param driver AppiumDriver instance
	 * @param text Visible text of MobileElement
	 * @return red color value of Mobile Element as int
	 * @throws IOException thrown when a thread is interrupted
	 */
	public static int findRedColorValueOfElement(AppiumDriver<MobileElement> driver, String text) throws IOException {
		MobileElement ele = driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='" + text + "\']"));
		org.openqa.selenium.Point point = ele.getCenter();
		int centerx = point.getX();
		int centerY = point.getY();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage image = ImageIO.read(scrFile);
		int clr = image.getRGB(centerx, centerY);
		int red = (clr & 0x00ff0000) >> 16;
		int green = (clr & 0x0000ff00) >> 8;
		int blue = clr & 0x000000ff;
		return red;
	}
	

	/** 
	 * Check for decimal number pattern in text
	 * 
	 * @param textTocheck Text to check for pattern
	 * @return boolean
	 * 
	 * true: pattern found
	 * false: pattern not found
	 */
	public static boolean CheckForDecimalNumberPattern(String textTocheck) {

		String regex = "\\d*\\.?\\d+";

		Pattern p = Pattern.compile(regex);

		if (textTocheck == null) {
			return false;
		}

		Matcher m = p.matcher(textTocheck);

		if (m.matches()) {
			return true;
		} else
			return false;
	}
	
	
	/** 
	 * Checks occurrences of pattern in String
	 * This  function is created to press following keys : numbers from 0-9,Period (.),Comma (,), Apostrophe(') on keyboard on Mobile screen
	 * This is created specifically to simulate typing of numbers (example: 12.34) on mobile keyboard
	 * @param text Text entered on keyboard (example 12.34)
	 * @param pattern pattern to check in text.
	 * @return occurrences of pattern in text.Value will be int.
	 */
	public static int checkOccurenceOfPatternInString(String text, String pattern) {
		int M = pattern.length();
		int N = text.length();
		int res = 0;

		/* A loop to slide pat[] one by one */
		for (int i = 0; i <= N - M; i++) {
			/*
			 * For current index i, check for pattern match
			 */
			int j;
			for (j = 0; j < M; j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}

			// if pat[0...M-1] = txt[i, i+1, ...i+M-1]
			if (j == M) {
				res++;
				j = 0;
			}

		}
		return res;
	}
	

	/** 
	 * Generates past date in yyyy-MM-dd format 
	 * @param days days from current date
	 * @param months months from current date
	 * @param years  years from current date
	 * @return Past date in yyyy-MM-dd format.Value will be String
	 */
	public static String generatePastDate(int days, int months, int years)

	{

		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.minusDays(days).minusYears(years).minusMonths(months);
		Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		String newdate = dateFormat.format(currentDatePlusOneDay);

		return newdate;

	}
	
	
	/** 
	 * Generates future date in yyyy-MM-dd format 
	 * @param days days from current date
	 * @param months months from current date
	 * @param years  years from current date
	 * @return Future date in yyyy-MM-dd format.Value will be String
	 */
	public static String generateFutureDate(int days, int months, int years)

	{
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		localDateTime = localDateTime.plusDays(days).plusYears(years).plusMonths(months);
		Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		String newdate = dateFormat.format(currentDatePlusOneDay);

		return newdate;
	}

}
