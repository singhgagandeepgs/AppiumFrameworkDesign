package org.gds.android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.gds.pageObjects.android.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	AndroidDriver driver;
	AppiumDriverLocalService service;
	FormPage formPage;

	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\singh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		// service.start();

		URL appiumServer = new URI("http://127.0.0.1:4723/").toURL();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("GagansEmulator");
		options.setApp("F:\\appium-workspace\\AppiumLearning\\src\\test\\java\\resources\\General-Store.apk");

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
