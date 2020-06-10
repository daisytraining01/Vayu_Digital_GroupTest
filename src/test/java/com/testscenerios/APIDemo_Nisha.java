package com.testscenerios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

import com.pageobjectrepository.APIDemos_Nisha;
import com.utilities.Listener_Vimal;
import com.utilities.Screenshot_Vimal;

@Listeners(com.utilities.Listener_Vimal.class)
public class APIDemo_Nisha extends APIDemos_Nisha {
	public static AppiumDriver driver;
	Screenshot_Vimal scren;
	
	@BeforeClass
	public void objectCreation() {
		scren = new Screenshot_Vimal();
	}

	@Parameters({ "ModeOfRun" })
	@BeforeTest
	public void setUp(String ModeOfRun) throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (ModeOfRun.contentEquals("Emulator")) {

			capabilities.setCapability("deviceName", "Pixel");
			capabilities.setCapability("udid", "emulator-5554");
			capabilities.setCapability("platformVersion", "7.0");
			capabilities.setCapability("automationName", "UiAutomator1");
			capabilities.setCapability("platformName", "ANDROID");
			capabilities.setCapability("appPackage", "io.appium.android.apis");
			capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, capabilities);

		}

		else if (ModeOfRun.contentEquals("Cloud")) {

			String accessKey = "eyJ4cC51Ijo0NTU2OTcsInhwLnAiOjQ1NTY4OSwieHAubSI6Ik1UVTRPVE0yTnprMU1EQXlNdyIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE5MDQ3Mjc5NTAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.9PEKvslTXNVQjibm_oLIBni8iknsIwo1etQfjBMQ8ME";
			capabilities.setCapability("accessKey", accessKey);
			capabilities.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "io.appium.android.apis.ApiDemos");
			driver = new AndroidDriver(new URL("https://demo.experitest.com/wd/hub"), capabilities);

		}

		else if (ModeOfRun.contentEquals("Real")) {

			capabilities.setCapability("deviceName", "Nish");
			capabilities.setCapability("udid", "JCAAGF06C5578SZ");
			capabilities.setCapability("automationName", "UiAutomator1");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "9");
			capabilities.setCapability("appPackage", "io.appium.android.apis");
			capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
			capabilities.setCapability("automationName", "UiAutomator1");
			URL remoteUrl = new URL("http://localhost:4723/wd/hub");
			driver = new AndroidDriver(remoteUrl, capabilities);

		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void Chronometer_Nisha(final ITestContext testContext) throws Exception {
		driver.findElement(Views).click();
		Thread.sleep(5000);
		driver.findElement(Chronometer).click();
		Thread.sleep(5000);
		driver.findElement(start).click();
		Thread.sleep(5000);
		driver.findElement(stop).click();
		Thread.sleep(5000);
		scren.takeSnapShot(driver, testContext.getName());

	}

	@Test
	public void Animation_Nisha(final ITestContext testContext) throws Exception {
		driver.findElement(Animation).click();
		Thread.sleep(5000);
		driver.findElement(Multiple_Properties).click();
		Thread.sleep(5000);
		driver.findElement(startButton).click();
		Thread.sleep(5000);
		scren.takeSnapShot(driver, testContext.getName());
		String str = driver.findElement(title).getText();
		Assert.assertEquals(str, "Animation/Multiple Properties");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}
}
