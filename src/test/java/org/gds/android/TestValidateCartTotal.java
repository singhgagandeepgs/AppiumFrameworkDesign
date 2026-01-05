
package org.gds.android;

import java.time.Duration;

import org.gds.pageObjects.android.CartPage;
import org.gds.pageObjects.android.ProductCatalogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestValidateCartTotal extends BaseTest {

	ProductCatalogPage catalogPage;
	CartPage cartPage;

	@Test
	public void testValidateCartTotal() throws InterruptedException {

		formPage.setNameField("Harleen Kaur");
		formPage.setGender("Female");
		formPage.selectCountry("Canada");
		catalogPage = formPage.submitForm();

		catalogPage.addToCartByIndex(0);
		catalogPage.addToCartByIndex(0);
		cartPage = catalogPage.goToCart();

		Thread.sleep(2000);
		
			
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.attributeContains(By.id(
		 * "com.androidsample.generalstore:id/toolbar_title"), "text", "Cart"));
		 */
		 

		double totalAmount = cartPage.getProductPriceSum();

		Double displayedTotalAmt = cartPage.getTotalAmtDisplayed();

		Assert.assertEquals(totalAmount, displayedTotalAmt);

		cartPage.acceptTnC();

		cartPage.checkPromotionalEmailsCheckbox();

		cartPage.submitOrder();

		Thread.sleep(3000);
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */