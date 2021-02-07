package com.pom.crimson.util;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Class deprecated and replaced with {@link ExtentReportManager}
 * 
 * @deprecated
 * @author Jaspreet Kaur
 *
 */
public class ExtentManager {
    
    private static ExtentReports extent;
    public static String screenshotFolderPath;
    
    
    
    public static ExtentReports getInstance(String reportPath) {
    	if (extent == null){
    		String fileName="Report.html";
    		Date d = new Date();
    		//String fileName=d.toString().replace(":", "_").replace(" ", "_");
    		String folderName=d.toString().replace(":", "_").replace(" ", "_");
    		
    		// directory of the report folder
    		new File(reportPath+folderName+"/screenshots").mkdirs();
    		reportPath=reportPath+folderName+"/";
    		//reportPath=reportPath+folderName;
    		screenshotFolderPath=reportPath+"screenshots/";
    		System.out.println("screenshot folder path:"+screenshotFolderPath);
    		System.out.println(reportPath+"/"+fileName);
    		createInstance(reportPath+fileName);
    	}
    	
        return extent;
    }
    
    public static ExtentReports createInstance(String fileName) {
        //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        ExtentSparkReporter spark=new ExtentSparkReporter(fileName);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Reports");
        spark.config().setEncoding("utf-8");
        spark.config().setReportName("Reports - Automation Testing");
        spark.config().setCSS("css-string");
        spark.config().enableTimeline(true);
        spark.config().setJS("js-string");
 
   
        
       // htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
       // htmlReporter.config().setChartVisibilityOnOpen(true);
       // htmlReporter.config().setTheme(Theme.DARK);
        //htmlReporter.config().setDocumentTitle("Reports");
        //htmlReporter.config().setEncoding("utf-8");
        //htmlReporter.config().setReportName("Reports - Automation Testing");
        
        extent = new ExtentReports();
       // extent.attachReporter(htmlReporter);
        extent.attachReporter(spark);
        
        return extent;
    }
}