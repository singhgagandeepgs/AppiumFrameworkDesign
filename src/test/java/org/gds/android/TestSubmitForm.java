
package org.gds.android;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;

public class TestSubmitForm extends BaseTest {
	
	@BeforeMethod
	public void preSetup() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
	}

	@Test
	public void testSubmitFormPositiveFlow() throws InterruptedException {

		formPage.setNameField("Gagandeep Singh");
		formPage.setGender("Female");
		formPage.selectCountry("Argentina");
		formPage.submitForm();
	}
	
	@Test
	public void testSubitFormErrorToastValidation() {
		formPage.submitForm();
		String validationToastMsg = formPage.getErrorToastMsg();
		Assert.assertEquals(validationToastMsg, "Please enter your name");
	}
}

/*
 * ### V.Imp Learning: To prevent StaleElementException, use locator instead of
 * webelement in the ExpectedConditions methods for Explicit Wait
 */