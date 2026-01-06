package org.gds.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class AppiumUtils {

	private WebDriverWait wait;

	public double returnFormattedPrice(String amountString) {
		String removedDollar = amountString.replace("$", "").trim();
		Double productPrice = Double.parseDouble(removedDollar);
		return productPrice;
	}

	public void waitForElementUntilAttrContainsValue(AppiumDriver driver, int seconds, By locator, String attr,
			String value) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.attributeContains(locator, attr, value));
	}

	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		
		// convert json to string
		String jsonContent = FileUtils.readFileToString(
				new File(jsonFilePath), "UTF-8");

		// convert string to list of maps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		
		return data;
	}
}
