package com.testscenerios;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

import com.pageobjectrepository.*;
import com.utilities.*;

@Listeners(com.utilities.Listener_Vimal.class)
public class WheelSize_Chandrika extends WheelSize_Homepage_Chandrika {

	public static AndroidDriver driver;
	Screenshot_Vimal scren;
	Action_Vimal action;

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
			capabilities.setCapability("appPackage", "com.wheelsize");
			capabilities.setCapability("appActivity", "com.wheelsize.presentation.container.AppContainer");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, capabilities);

		}

		else if (ModeOfRun.contentEquals("Cloud")) {

			String accessKey = "eyJ4cC51Ijo0NTU2OTcsInhwLnAiOjQ1NTY4OSwieHAubSI6Ik1UVTRPVE0yTnprMU1EQXlNdyIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE5MDQ3Mjc5NTAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.9PEKvslTXNVQjibm_oLIBni8iknsIwo1etQfjBMQ8ME";
			capabilities.setCapability("accessKey", accessKey);
			capabilities.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.wheelsize");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
					"com.wheelsize.presentation.container.AppContainer");
			driver = new AndroidDriver(new URL("https://demo.experitest.com/wd/hub"), capabilities);

		}

		else if (ModeOfRun.contentEquals("Real")) {

			capabilities.setCapability("deviceName", "Redmi Note Pro");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "8.0");
			capabilities.setCapability("udid", "uwgidmk7beyhq8eq");
			capabilities.setCapability("appPackage", "com.wheelsize");
			capabilities.setCapability("appActivity", "com.wheelsize.presentation.container.AppContainer");
			capabilities.setCapability("automationName", "UiAutomator1");
			capabilities.setCapability("app", "C:\\Users\\ranganath\\Downloads\\WheelSize_v1.1.2_apkpure.com.apk");
			URL remoteUrl = new URL("http://localhost:4723/wd/hub");
			driver = new AndroidDriver(remoteUrl, capabilities);

		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void WheelappDemo(final ITestContext testContext) throws Exception {
		action = new Action_Vimal(driver);
		driver.findElement(make).click();
		Thread.sleep(10000);
		driver.findElement(makePath).click();
		Thread.sleep(10000);
		driver.findElement(year).click();
		Thread.sleep(10000);
		driver.findElement(model).click();
		Thread.sleep(10000);
		driver.findElement(trim).click();
		Thread.sleep(5000);
		String str = driver.findElement(title).getText();
		System.out.println(str);
		scren.takeSnapShot(driver, testContext.getName());
		Assert.assertEquals(str, "2019 Audi A1, 30 TFSi [GB]");

	}

	@AfterTest
	public void WheelappDemo1() {
		System.out.println("Wheel Details");
		driver.quit();
	}

}
