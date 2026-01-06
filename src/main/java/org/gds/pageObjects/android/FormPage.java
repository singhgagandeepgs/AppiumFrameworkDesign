package org.gds.pageObjects.android;

import org.gds.utils.AndroidActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{

	private AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadio;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement maleRadio;
	
	@AndroidFindBy(className = "android.widget.Spinner")
	private WebElement countryDropdown;
	
	@AndroidFindBy(className = "android.widget.Button")
	private WebElement buttonLetsShop;
	
	@AndroidFindBy(xpath = "//android.widget.Toast")
	private WebElement errorToast;
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		if(gender.toLowerCase().contains("female")){
			femaleRadio.click();
		}
		else if(gender.toUpperCase().contains("MALE")) {
			maleRadio.click();
		}
	}
	
	public void selectCountry(String countryName) {
		countryDropdown.click();
		gestureScrollToVisibleText(countryName);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='"+countryName+"']")).click();
	}
	
	public ProductCatalogPage submitForm() {
		buttonLetsShop.click();
		return new ProductCatalogPage(driver);
	}
	
	public String getErrorToastMsg() {
		return errorToast.getAttribute("name");
	}
	
	public void setAppActivityToFormPage() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
	}
}
