package org.gds.pageObjects.android;

import java.util.List;

import org.gds.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

	private AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement pageTitle;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayedTotalAmtStr;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement buttonTnC;
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement buttonCloseTnCPopup;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkboxPromotions;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement buttonCompletePurchase;
	
	By locPageTitle = By.id("com.androidsample.generalstore:id/toolbar_title");
	
	public By getLocatorOfPageTitle() {
		return locPageTitle;
	}
	
	public List<WebElement> getProductList(){
		return productList;
	}
	
	public double getProductPriceSum() {
		double totalAmount = 0.0;

		for (WebElement webElement : getProductList()) {
			String amountString = webElement.getText();
			Double productPrice = returnFormattedPrice(amountString);
			totalAmount += productPrice;
		}
		return totalAmount;
	}
	
	public double getTotalAmtDisplayed() {
		String amtString = displayedTotalAmtStr.getText();
		double displayedTotalAmt = returnFormattedPrice(amtString);
		return displayedTotalAmt;
	}
	
	public void acceptTnC() {
		gestureLongClick(buttonTnC);
		buttonCloseTnCPopup.click();
	}
	
	public void checkPromotionalEmailsCheckbox() {
		checkboxPromotions.click();
	}
	
	public void submitOrder() {
		buttonCompletePurchase.click();
	}
}
