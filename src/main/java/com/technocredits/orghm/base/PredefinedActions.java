package com.technocredits.orghm.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.technocredits.orghm.constants.ConstantPath;
import com.technocredits.orghm.customExceptions.InvalidSelectorException;
import com.technocredits.orghm.util.DateOperations;

public class PredefinedActions {

	protected static WebDriver driver;
	private static WebDriverWait wait;
	static Logger log = Logger.getLogger(PredefinedActions.class);
	private JavascriptExecutor js = (JavascriptExecutor) driver;

	public static void start(String url) {
		System.setProperty(ConstantPath.CHROMEDRIVER, ConstantPath.CHROMEDRIVER_PATH);
		log.trace("Open chrome browser");
		driver = new ChromeDriver();
		log.trace("STEP : Enter URL");
		driver.get(url);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
	}

	public static void start() {
		//https://22novpractice-trials73.orangehrmlive.com/
		start("https://oct30automation-trials72.orangehrmlive.com/");
	}

	private By getByReference(String locator) {
		String locatorType = locator.split(":-")[0].replace("[", "").replace("]", "");
		String locatorValue = locator.split(":-")[1];
		log.trace("Locator type: " + locatorType);
		log.trace("Locator value: " + locatorValue);
		switch (locatorType) {
		case "XPATH":
			return By.xpath(locatorValue);
		case "ID":
			return By.id(locatorValue);
		default:
			throw new InvalidSelectorException("User Should Select values from XPATH, CSS, ID, NAME");
		}
	}

	protected String getPageHeaderTitle() {
		return driver.getTitle();
	}

	protected void waitTillInvisiblityOfElement(String locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getByReference(locator)));
		log.trace("Wait till invisibility of element");
	}

	protected void waitTillNumbersOfElementToBe(String locator, int num) {
		wait.until(ExpectedConditions.numberOfElementsToBe(getByReference(locator), num));
		log.trace("Wait till numbers of element to be");
	}

	protected WebElement getElement(String locator, boolean isWaitRequired) {
		WebElement element = null;
		if (isWaitRequired)
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(getByReference(locator)));
		else
			element = driver.findElement(getByReference(locator));
		return element;
	}
	
	protected WebElement getElement(String locator, Object dataText, boolean isWaitRequired) {
		locator = String.format(locator, dataText);
		WebElement element = null;
		if (isWaitRequired)
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(getByReference(locator)));
		else
			element = driver.findElement(getByReference(locator));
		return element;
	}

	protected List<WebElement> getElements(String locator, boolean isWaitRequired) {
		List<WebElement> elements = null;
		if (isWaitRequired)
			elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByReference(locator)));
		else
			elements = driver.findElements(getByReference(locator));
		return elements;
	}

	private void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		log.trace("Scroll on element: " + element);
	}

	protected void clickOnElement(WebElement element) {
		if (!element.isDisplayed())
			scrollToElement(element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		log.trace("Element clicked: " + element);
	}

	protected List<String> getTextOfAllElements(String locator, boolean isWaitRequired) {
		List<WebElement> ListElements = getElements(locator, isWaitRequired);
		List<String> element = new ArrayList<String>();
		for (WebElement ele : ListElements) {
			element.add(ele.getText());
		}
		return element;
	}

	protected boolean isElementDisplayed(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		boolean display = isElementDisplayed(element);
		return display;
	}

	protected boolean isElementDisplayed(WebElement element) {
		boolean display;
		if (element.isDisplayed()) {
			display = true;
			log.trace("Element is displayed: " + element);
			return display;
		} else {
			scrollToElement(element);
			log.trace("Element is diplayed: " + element);
			display = element.isDisplayed() ? true : false;
			return display;
		}
	}

	protected boolean enterText(String locator, boolean isWaitRequired, String textValue) {
		WebElement element = getElement(locator, isWaitRequired);
		return enterText(element, textValue);
	}

	protected boolean enterText(WebElement element, String textValue) {
		if (element.isEnabled()) {
			element.sendKeys(textValue);
			log.trace("Text entered on element: " + element);
			return true;
		} else
			return false;
	}

	protected String getTextOfElement(WebElement element) {
		if (element.isDisplayed())
			return element.getText();
		else {
			scrollToElement(element);
			return element.getText();
		}
	}

	public static void captureScreenshot(String name) {
		name = name + DateOperations.getTimestamp();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/" + name + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void smartClickByJS(WebElement element) {
		js.executeScript("arguments[0].click()", element);
	}

	protected void smartSendkeysByJS(WebElement element, String text) {
		js.executeScript("arguments[0].value='" + text + "'", element);
	}

	protected String smartGetValueByJS(WebElement element) {
		return js.executeScript("return arguments[0].value", element).toString();
	}

	protected void smartHandleCheckedBoxByJS(WebElement element, boolean checkedValue) {
		js.executeScript("arguments[0].checked=" + checkedValue + "", element);
	}

	protected String smartPageTitleByJS() {
		return js.executeScript("return document.title").toString();
	}

	protected void navigation(int direction) {
		switch (direction) {
		case 0:
			js.executeScript("history.go(0)");
			break;
		case 1:
			js.executeScript("history.go(1)");
			break;
		case -1:
			js.executeScript("history.go(-1)");
			break;
		}
	}

	public static void closeBrowser() {
		driver.close();
	}

	public static void quitBrowser() {
		driver.quit();
	}
}
