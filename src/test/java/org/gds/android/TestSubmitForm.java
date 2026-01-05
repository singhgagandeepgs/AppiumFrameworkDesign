
package org.gds.android;

import org.testng.annotations.Test;

public class TestSubmitForm extends BaseTest {

	@Test
	public void testSubmitForm() throws InterruptedException {

		formPage.setNameField("Gagandeep Singh");
		formPage.setGender("Female");
		formPage.selectCountry("Argentina");
		formPage.submitForm();
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */