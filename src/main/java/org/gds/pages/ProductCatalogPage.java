package org.gds.pages;

import java.util.List;

import org.gds.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogPage extends AndroidActions{

	private AndroidDriver driver;
	
	public ProductCatalogPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> buttonAddToCart;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement buttonGoToCart;
	
	public void addToCartByIndex(int index) {
		buttonAddToCart.get(index).click();
	}
	
	public void goToCart() {
		buttonGoToCart.click();
		
	}
}
