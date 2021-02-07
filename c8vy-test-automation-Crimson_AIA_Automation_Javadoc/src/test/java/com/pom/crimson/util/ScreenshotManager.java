package com.pom.crimson.util;

import java.io.File;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Class used to capture screenshot of the mobile screen
 * 
 * @author Sivaprakash.Selvaraj
 *
 */
public class ScreenshotManager {
	
	public static byte[] Capture(WebDriver driver) {		
		
		TakesScreenshot scr = (TakesScreenshot) driver;
		File sourceFile = scr.getScreenshotAs(OutputType.FILE);

		byte[] encoded = null;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(sourceFile));
		} catch (IOException e1) { 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return encoded;
	}
}
