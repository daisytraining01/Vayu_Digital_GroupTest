package com.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;

public class Screenshot_Vimal {

	public void takeSnapShot(AppiumDriver driver, String methodName) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ss");

		// Move image file to new destination
		File DestFile = new File(
				System.getProperty("user.dir") + "/Screenshot/" + methodName + dateFormat.format(new Date()) + ".png");
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}

}
