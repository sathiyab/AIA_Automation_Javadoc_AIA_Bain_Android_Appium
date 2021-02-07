package com.pom.crimson.listeners;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.pom.crimson.util.ExtentReportManager;
import com.pom.crimson.util.ScreenshotManager;
import com.pom.crimson.base.BaseFixture;

/** LogAssert is inherited from SoftAssert to perform custom actions like taking screenshot, 
 * add logging in Extent Report when a 
 * test case is passed or failed.
 *
 *
 * @author Sivaprakash.Selvaraj
 */
public class LogAssert extends SoftAssert {

	/** onAssertSuccess adds logging in extent report.
	 * Takes screenshot and captures exception.
	 * Invoked when an assert passes.
	 * 
	 * 
	 * @param assertCommand IAssert Interface
	 */	
	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {

		BaseFixture BsF = new BaseFixture();

		if (BsF.getScreenshotCaptureStage().equals("2") || BsF.getPlatformName().equals("API") || BsF.getPassScreenshotFlag().equals("N")) {
			ExtentReportManager.getTest().pass(assertCommand.getMessage() + " <PASSED> ");
		}
		else {
			try {
				ExtentReportManager.getTest().pass(assertCommand.getMessage() + " <PASSED> ", MediaEntityBuilder
						.createScreenCaptureFromBase64String(
								new String(ScreenshotManager.Capture(BsF.getDriver()), StandardCharsets.US_ASCII))
						.build());
			} catch (IOException e) {
				ExtentReportManager.getTest().pass(assertCommand.getMessage() + " <PASSED> ");
			}
		} 
	}

	/** onAssertFailure adds logging in extent report.
	 * Takes screenshot and captures exception.
	 * Invoked when an assert fails.
	 * 
	 * 
	 * @param assertCommand IAssert Interface
	 * @param ex Message provided in the Assert function
	 */
	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		BaseFixture BsF = new BaseFixture();

		String suffix = String.format("Expected [%s] but found [%s]", assertCommand.getExpected().toString(),
				assertCommand.getActual().toString());

		if (BsF.getScreenshotCaptureStage().equals("2") || BsF.getPlatformName().equals("API")) {
			ExtentReportManager.getTest().pass(assertCommand.getMessage() + " <PASSED> ");
		}
		else {
			try {
				ExtentReportManager.getTest().fail(assertCommand.getMessage() + " <FAILED>. " + suffix,
						MediaEntityBuilder
								.createScreenCaptureFromBase64String(new String(
										ScreenshotManager.Capture(BsF.getDriver()), StandardCharsets.US_ASCII))
								.build());
			} catch (IOException e) {
				ExtentReportManager.getTest().fail(assertCommand.getMessage() + " <FAILED>. " + suffix);
				e.printStackTrace();
			}
		} 
	}
}
