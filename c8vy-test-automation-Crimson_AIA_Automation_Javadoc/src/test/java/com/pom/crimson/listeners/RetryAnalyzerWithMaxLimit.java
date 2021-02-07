package com.pom.crimson.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/** It adds max retry limit to a test case in case of test case failure due to specific exception.
 * It will re-execute testcase if it fails until Max Retry is over.
 * 
 * 
 * Customize this method to add exceptions.
 * 
 * MaxRetry=2;
 * 
 */
public class RetryAnalyzerWithMaxLimit implements IRetryAnalyzer {
	
	private static int count=0;
	private static int maxTry=2;

	/** 
	 * It will re-execute testcase if it fails due to exception until Max Retry is over.
	 * 
	 * 
	 * Customize this method to add exceptions.
	 * 
	 * MaxRetry=2;
	 * @return boolean value 
	 * 
	 */
	@Override
	public boolean retry(ITestResult result) {
	boolean status=result.getThrowable().getMessage().contains("java.lang.NullPointerException\n"
			+ "	at com.pom.crimson.listeners.LogAssert.onAssertFailure");
	if (status)
	{
		if (count>=maxTry)
		{
			count=0;
		} else {
			count++;
			return true;
		}
	}	
		return false;
	}
	
	/** 
	 * Checks if retry is oevr
	 * 
	 * 
	 * @return boolean value 
	 * 
	 */
	public boolean isRetryOver()
	{
		if (count>=maxTry)
			return true;
		else return false;
	}
	

}
