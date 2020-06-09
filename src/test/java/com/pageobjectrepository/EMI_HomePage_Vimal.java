package com.pageobjectrepository;

import org.openqa.selenium.By;

public class EMI_HomePage_Vimal {

	public static final By amount = By.id("amount");
	public static final By interest = By.id("interest");
	public static final By calculateEmi = By.id("button");
	public static final By saveButton = By.id("button2");
	public static final By emiName = By.xpath(
			"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText");
	public static final By okButton = By.id("button1");
	public static final By selectCurrencyTag = By.xpath("//android.view.View*[@text='Select Currency:']");
	public static final By currency = By.id("currency");
	public static final By savedEMI = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.ImageView");
}
