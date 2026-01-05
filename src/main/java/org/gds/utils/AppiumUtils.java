package org.gds.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class AppiumUtils {

	private AppiumDriver driver;
	private WebDriverWait wait;

	public AppiumUtils(AppiumDriver driver) {
		this.driver = driver;
	}
	
	public double returnFormattedPrice(String amountString) {
		String removedDollar = amountString.replace("$", "").trim();
		Double productPrice = Double.parseDouble(removedDollar);
		return productPrice;
	}

	public void waitForElementUntilAttrContainsValue(int seconds, By locator, String attr, String value) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.attributeContains(locator, attr, value));
	}
	
}
