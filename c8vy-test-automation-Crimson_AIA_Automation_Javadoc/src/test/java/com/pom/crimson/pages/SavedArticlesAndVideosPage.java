package com.pom.crimson.pages;

import com.pom.crimson.base.BasePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * Saved Articles And Videos Page can be used to view articles and videos which are marked as <b>{@value com.pom.crimson.util.Constants#HOME_SAVE_FOR_LATER}</b>. 
 *  
 * User can navigate to this page by: <br><br>
 * 1. Go to Home Page <br> Click <b>Profile</b> in bottom menu.<br>Click on <b>{@value com.pom.crimson.util.Constants#PROFILEPAGE_SAVED_ARTICLES_VIDEOS}</b>
 * <br><br>
 * This page class contains MobileElements on this page and functions to
 * simulate interactions with these elements.
 * 
 * @author Jaspreet Kaur Chagger
 */
public class SavedArticlesAndVideosPage extends BasePage {
	
	public SavedArticlesAndVideosPage(AppiumDriver<MobileElement> driver,String platformName)
	{
		super(driver,platformName);
	}


}