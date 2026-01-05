
package org.gds;

import java.time.Duration;
import java.util.List;

import org.gds.pages.FormPage;
import org.gds.pages.ProductCatalogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class TestSubmitForm extends BaseTest {
	
	FormPage formPage;
	ProductCatalogPage catalogPage;

	@Test
	public void testSubmitForm() throws InterruptedException {
		
		formPage = new FormPage(driver);
		formPage.setNameField("Gagandeep Singh");
		formPage.setGender("Female");
		formPage.selectCountry("Argentina");
		formPage.submitForm();
		
		catalogPage = new ProductCatalogPage(driver);
		catalogPage.addToCartByIndex(0);
		catalogPage.addToCartByIndex(0);
		catalogPage.goToCart();

		
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */