
package org.gds.android;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.gds.pageObjects.android.CartPage;
import org.gds.pageObjects.android.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestValidateCartTotal extends BaseTest {

	ProductCatalogPage catalogPage;
	CartPage cartPage;

	@Test(dataProvider = "getData")
	public void testValidateCartTotal(HashMap<String, String> input) throws InterruptedException {

		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.selectCountry(input.get("country"));
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
	public Object[][] getData() throws IOException {
		String jsonFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\org\\gds\\testData\\ecom.json";
		List<HashMap<String, String>> data = getJsonDataToMap(jsonFilePath);
		/*
		 * return new Object[][] { { "Harleen Kaur", "Female", "Antarctica" }, {
		 * "Harmeet Singh", "Male", "American Samoa" } };
		 */
		
		return new Object[][] { { data.get(0)}, { data.get(1)} };
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */