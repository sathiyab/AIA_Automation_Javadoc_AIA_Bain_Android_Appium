package com.pom.crimson.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * To be able to have a chance to retry a failed test.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;

	/**
	 * Returns true if the test method has to be retried, false otherwise.
	 *
	 * @param result The result of the test method that just ran.
	 * @return true if the test method has to be retried, false otherwise.
	 */
	@Override
	public boolean retry(ITestResult result) {

		// if this annotation is applied to method
		RetryIfFailed annotation = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(RetryIfFailed.class);

		if ((annotation != null) && (counter < annotation.value())) {
			counter++;
			return true;
		}

		return false;
	}

}
