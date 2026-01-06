package org.gds.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.gds.pageObjects.android.FormPage;
import org.gds.utils.AppiumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends AppiumUtils{

	AndroidDriver driver;
	AppiumDriverLocalService service;
	protected FormPage formPage;
	Properties properties;

	@BeforeClass
	public void configureAppium() throws IOException {
		
		String propFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\org\\gds\\resources\\globalConfig.properties";
		properties = readPropertiesFile(propFilePath);
		
		String ipAddress = properties.getProperty("ipAddress");
		String port = properties.getProperty("port");
		String deviceName = properties.getProperty("deviceName");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		URL appiumServer = service.getUrl();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\org\\gds\\testResources\\General-Store.apk");

		// New statement added myself (Not in tutorial) to handle webview Chrome compatibility error
		options.setCapability("appium:chromedriverAutodownload", true);
		options.setCapability("appium:autoWebview", false);
		options.setCapability("appium:ensureWebviewsHavePages", true);

		driver = new AndroidDriver(appiumServer, options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		if (service != null) {
			service.stop();
		}
	}

	public void addProductToCart(String productName) {
		WebElement productContainer = driver.findElement(By.xpath(
				"//android.widget.TextView[@text='" + productName + "']/ancestor::android.widget.RelativeLayout[1]"));
		WebElement addToCart = productContainer
				.findElement(By.xpath(".//android.widget.TextView[@text='ADD TO CART']"));
		addToCart.click();
	}
}
