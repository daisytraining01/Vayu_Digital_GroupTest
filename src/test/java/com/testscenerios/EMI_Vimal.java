package com.testscenerios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectrepository.EMI_HomePage_Vimal;
import com.pageobjectrepository.EMI_SavedEMI_Viaml;
import com.utilities.Action_Vimal;
import com.utilities.Excel_Utility_Vimal;
import com.utilities.Listener_Vimal;
import com.utilities.Screenshot_Vimal;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

@Listeners(com.utilities.Listener_Vimal.class)

public class EMI_Vimal extends EMI_HomePage_Vimal {
	public static AppiumDriver driver;
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
			capabilities.setCapability("appPackage", "com.avrapps.emicalculator");
			capabilities.setCapability("appActivity", ".activities.EMICalculator");
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
	public void EMI_AddNewEMI(String Amount, String Interest, String NameEMI, final ITestContext testContext)
			throws Exception {
		action = new Action_Vimal(driver);
		driver.hideKeyboard();
		action.SendKeys(amount, Amount);
		driver.hideKeyboard();
		action.SendKeys(interest, Interest);
		driver.hideKeyboard();

		TouchAction touch = new TouchAction(driver);
		touch.press(PointOption.point(200, 740)).moveTo(PointOption.point(280, 650)).release().perform();

		action.WaitAndClick(calculateEmi, 20);
		Thread.sleep(2000);
		Assert.assertTrue(action.isDisplayed(currency));
		action.Click(saveButton);
		action.SendKeys(emiName, NameEMI);
		action.Click(okButton);
		action.Click(savedEMI);
		Thread.sleep(1000);
		scren.takeSnapShot(driver, testContext.getName());

	}

	@Test(dataProvider = "TestData")
	public void EMI_ViewEMI(String Amount, String Interest, String NameEMI, final ITestContext testContext)
			throws Exception {
		EMI_SavedEMI_Viaml pageObj = new EMI_SavedEMI_Viaml();

		action = new Action_Vimal(driver);
		driver.hideKeyboard();
		action.SendKeys(amount, Amount);
		driver.hideKeyboard();
		action.SendKeys(interest, Interest);
		driver.hideKeyboard();

		TouchAction touch = new TouchAction(driver);
		touch.press(PointOption.point(200, 740)).moveTo(PointOption.point(280, 650)).release().perform();

		action.WaitAndClick(calculateEmi, 20);
		Thread.sleep(2000);
		Assert.assertTrue(action.isDisplayed(currency));
		action.Click(saveButton);
		action.SendKeys(emiName, NameEMI);
		action.Click(okButton);
		action.Click(savedEMI);
		Thread.sleep(1000);

		// *****************************************************************************************************
		// Second Scenario Starts
		// *****************************************************************************************************

		Thread.sleep(2000);
		action.Click(pageObj.FirstEmi);
		action.Click(pageObj.Currency);
		action.Click(pageObj.PoundCurrency);
		Thread.sleep(5000);
		action.Click(pageObj.NavigateBack);
		Assert.assertEquals(action.isDisplayed(pageObj.EmiCallculate), true);
		scren.takeSnapShot(driver, testContext.getName());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] TestDataFromExcel() throws IOException {

		return Excel_Utility_Vimal.getTestData();

	}

	@DataProvider
	public Object[][] TestData() throws IOException {

		return new Object[][] { { "80808", "12", "Housing Loan" }, };
	}

	@AfterClass
	public void endClass(final ITestContext testContext) throws Exception {
		scren.takeSnapShot(driver, testContext.getName());
		System.out.println("End of Class");
	}

}
