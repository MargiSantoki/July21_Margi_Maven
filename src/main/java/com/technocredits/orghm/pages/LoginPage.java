package com.technocredits.orghm.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.constants.ConstantPath;
import com.technocredits.orghm.util.PropertiesOperations;

public class LoginPage extends PredefinedActions {

	private static LoginPage loginPage;
	private PropertiesOperations properties;

	public static LoginPage getObject() {
		if (loginPage == null)
			loginPage = new LoginPage();
		return loginPage;
	}

	private LoginPage() {
		try {
			properties = new PropertiesOperations(ConstantPath.LOGINPAGE_LOCATOR_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isLogoDisplayed() {
		String logoLocator = properties.getValue("logo");
		return isElementDisplayed(logoLocator, false);
	}

	public boolean isLoginPanelDisplayed() {
		return isElementDisplayed(properties.getValue("loginPanel"), false);
	}

	public void enterCredentials(String username, String password) {
		enterText(getElement(properties.getValue("username"), false), username);
		enterText(getElement(properties.getValue("password"), false), password);
	}

	public MenuPage clickOnLoginButton() {
		clickOnElement(getElement(properties.getValue("loginButton"), false));
		return new MenuPage();
	}

	public String getLoginErrorMessage() {
		WebElement e = getElement(properties.getValue("loginErrorMessage"), false);
		return e.getText();
	}
}
