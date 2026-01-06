
package org.gds.android;

import org.gds.pageObjects.android.CartPage;
import org.gds.pageObjects.android.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestValidateCartTotal extends BaseTest {

	ProductCatalogPage catalogPage;
	CartPage cartPage;

	@Test(dataProvider = "getData")
	public void testValidateCartTotal(String name, String gender, String country) throws InterruptedException {

		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.selectCountry(country);
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
		
		cartPage.navigateBack();
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Harleen Kaur", "Female", "Antarctica" },
				{ "Harmeet Singh", "Male", "American Samoa" } };
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */