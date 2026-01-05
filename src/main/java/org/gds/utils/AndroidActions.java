package org.gds.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {

	private AndroidDriver driver;

	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}

	public void gestureLongClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public void gestureScrollDownOnceChatGPT() {
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 400,
				"width", 800, "height", 1200, "direction", "down", "percent", 0.5));
	}

	public void gestureScrollToEnd() {
		boolean canScrollMore;

		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
		} while (canScrollMore);
	}

	public void gestureScrollToVisibleText(String visibleText) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + visibleText + "\"));"));
	}

	public void gestureScrollToVisibleTextChatGPT(String visibleText) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())" + ".setMaxSearchSwipes(5)"
				+ ".scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\"))"));
	}

	public WebElement scrollUntilElementVisibleChatGPT(By locator) {

		int maxScrolls = 8;

		for (int i = 0; i < maxScrolls; i++) {
			try {
				WebElement element = driver.findElement(locator);
				if (element.isDisplayed()) {
					return element;
				}
			} catch (NoSuchElementException e) {
				// element not yet visible
			}

			gestureScrollDownOnceChatGPT();

			try {
				Thread.sleep(400); // allow RecyclerView to settle
			} catch (InterruptedException ignored) {
			}
		}

		throw new NoSuchElementException("Element not found after scrolling");
	}

	public void gestureSwipeOnAnElement(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.25));
	}

	public void gestureDragDrop(WebElement element, int dropCordX, int dropCordY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "endX", dropCordX, "endY", dropCordY));
	}
}
