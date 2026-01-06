package org.gds.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class AppiumUtils {

	AppiumDriverLocalService service;
	private WebDriverWait wait;
	
	public AppiumDriverLocalService startAppiumServer(String url, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\singh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(url).usingPort(port).build();

		// service.start();
		
		return service;
	}

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
	
	public Properties readPropertiesFile(String propFilePath) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(propFilePath);
		properties.load(fis);
		return properties;
	}
}
