package com.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class Action_Vimal {
	AppiumDriver driver;

	public Action_Vimal(AppiumDriver driver) {
		this.driver = driver;
	}

	public void Click(By Locator) {
		driver.findElement(Locator).click();
	}

	public void SendKeys(By Locator, String text) {
		driver.findElement(Locator).sendKeys(text);
	}

	public void ClearAndSendKeys(By Locator, String text) {
		driver.findElement(Locator).click();
		driver.findElement(Locator).clear();
		driver.findElement(Locator).sendKeys(text);
	}

	public void WaitAndClick(By Locator, int TimeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(Locator)).click();
	}

	public void WaitAndSendKeys(By Locator, int TimeOutInSeconds, String Text) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(Locator));
		element.click();
		element.sendKeys(Text);
	}

	public boolean isDisplayed(By Locator) {
		return driver.findElement(Locator).isDisplayed();
	}

}
