package com.testscenerios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectrepository.WordWeb_Homepage_Suresh;
import com.utilities.Action_Vimal;
import com.utilities.Excel_Utility_Vimal;
import com.utilities.Screenshot_Vimal;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

@Listeners(com.utilities.Listener_Vimal.class)
public class WordWeb_Suresh extends WordWeb_Homepage_Suresh {
	public static AndroidDriver driver;
	Action_Vimal action;
	Screenshot_Vimal scren;

	@BeforeClass
	public void objectCreation() {
		scren = new Screenshot_Vimal();
	}

	@Parameters({ "ModeOfRun" })
	@BeforeMethod
	public void setUp(String ModeOfRun) throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (ModeOfRun.contentEquals("Emulator")) {

			capabilities.setCapability("deviceName", "Pixel");
			capabilities.setCapability("udid", "emulator-5554");
			capabilities.setCapability("platformVersion", "7.0");
			capabilities.setCapability("automationName", "UiAutomator1");
			capabilities.setCapability("platformName", "ANDROID");
			capabilities.setCapability("appPackage", "com.wordwebsoftware.android.wordweb");
			capabilities.setCapability("appActivity", "com.wordwebsoftware.android.wordweb.activity.WordWebActivity");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, capabilities);

		}

		else if (ModeOfRun.contentEquals("Cloud")) {

			String accessKey = "eyJ4cC51Ijo0NTU2OTcsInhwLnAiOjQ1NTY4OSwieHAubSI6Ik1UVTRPVE0yTnprMU1EQXlNdyIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE5MDQ3Mjc5NTAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.9PEKvslTXNVQjibm_oLIBni8iknsIwo1etQfjBMQ8ME";
			capabilities.setCapability("accessKey", accessKey);
			capabilities.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.avrapps.emicalculator");
			capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activities.EMICalculator");
			driver = new AndroidDriver(new URL("https://demo.experitest.com/wd/hub"), capabilities);

		}

		else if (ModeOfRun.contentEquals("Real")) {

			capabilities.setCapability("deviceName", "Nish");
			capabilities.setCapability("udid", "JCAAGF06C5578SZ");
			capabilities.setCapability("automationName", "UiAutomator1");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "9");
			capabilities.setCapability("appPackage", "com.avrapps.emicalculator");
			capabilities.setCapability("appActivity", ".activities.EMICalculator");
			capabilities.setCapability("automationName", "UiAutomator1");
			URL remoteUrl = new URL("http://localhost:4723/wd/hub");
			driver = new AndroidDriver(remoteUrl, capabilities);
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(dataProvider = "TestDataFromExcel")
	public void createBookmark(String keyword, final ITestContext testContext) throws Exception {
		action.Click(clikCheckatabase);
		Thread.sleep(50000);
		action.ClearAndSendKeys(searchText, keyword);
		action.Click(clickFirstResult);
		action.Click(bookMark);
		action.Click(backButton);
		Assert.assertTrue(action.isDisplayed(lookup));
		scren.takeSnapShot(driver, testContext.getName());

	}

	@Test(dataProvider = "TestDataFromExcel")
	public void Search(String Keyword, final ITestContext testContext) throws Exception {
		action.Click(clikCheckatabase);
		Thread.sleep(50000);
		action.Click(searchTab);
		action.Click(noun);
		action.ClearAndSendKeys(searchText, Keyword);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		action.Click(clickFirstResultType);
		action.Click(backButton);
		action.Click(lookup);
		Assert.assertTrue(action.isDisplayed(lookup));
		scren.takeSnapShot(driver, testContext.getName());

	}

	@DataProvider
	public Object[][] TestDataFromExcel() throws IOException {

		return Excel_Utility_Vimal.getTestData("WordWeb.xlsx");

	}

}
