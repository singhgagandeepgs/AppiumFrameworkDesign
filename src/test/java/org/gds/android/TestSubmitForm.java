
package org.gds.android;

import org.gds.testUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSubmitForm extends BaseTest {
	
	@BeforeMethod
	public void preSetup() {
		formPage.setAppActivityToFormPage();
	}

	@Test
	public void testSubmitFormPositiveFlow() throws InterruptedException {

		formPage.setNameField("Gagandeep Singh");
		formPage.setGender("Female");
		formPage.selectCountry("Argentina");
		formPage.submitForm();
	}
	
	@Test
	public void testSubmitFormErrorToastValidation() {
		formPage.submitForm();
		String validationToastMsg = formPage.getErrorToastMsg();
		Assert.assertEquals(validationToastMsg, "Please enter your name");
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */