
package org.gds.android;

import org.gds.pageObjects.android.ProductCatalogPage;
import org.testng.annotations.Test;

public class TestAddToCart extends BaseTest {
	
	ProductCatalogPage catalogPage;

	@Test
	public void testAddToCart() throws InterruptedException {

		formPage.setNameField("Gagandeep Singh");
		formPage.setGender("Male");
		formPage.selectCountry("India");
		catalogPage = formPage.submitForm();
		
		catalogPage.addToCartByIndex(0);
		catalogPage.addToCartByIndex(0);
		catalogPage.goToCart();
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */