package com.technocredits.orghm.testscripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.pages.LoginPage;

public class LoginTest {

	SoftAssert softAssert = new SoftAssert();
	Logger log = Logger.getLogger(LoginTest.class);

	@BeforeMethod
	public void setup() {
		PredefinedActions.start();
		log.info("URL launch successfully");
	}

	@Test
	public void loginTest() {
		log.info("STEP : Verify Logo is displayed on Login page");
		LoginPage loginPage = LoginPage.getObject();
		boolean isLogoDisplayed = loginPage.isLogoDisplayed();
		softAssert.assertTrue(isLogoDisplayed, "Logo is not displayed");
		log.info("Logo is displayed successfully on login page");

		log.info("STEP : Verify Login panel is displayed on Login page");
		boolean isLogoPanelDisplayed = loginPage.isLoginPanelDisplayed();
		softAssert.assertTrue(isLogoPanelDisplayed, "Logo Panel is not displayed");
		log.info("Login panel is successfully displayed on login page");

		log.info("STEP : Enter Credentials");
		loginPage.enterCredentials("Admin", "CSxe9EW@e0");
		softAssert.assertAll();
		log.info("Credentials are entered on Login page");
		log.info("STEP : Click on Login button");
		loginPage.clickOnLoginButton();
		log.info("Clicked on login button on Login page");
	}

	@Test
	public void loginTestWithoutPassword() {
		log.info("STEP : Verify Logo is displayed on Login page");
		LoginPage loginPage = LoginPage.getObject();
		boolean isLogoDisplayed = loginPage.isLogoDisplayed();
		softAssert.assertTrue(isLogoDisplayed, "Logo is not displayed");
		log.info("Logo is displayed successfully on login page");
		
		log.info("STEP : Verify Logo panel is displayed on Login page");
		boolean isLoginPanelDisplayed = loginPage.isLoginPanelDisplayed();
		softAssert.assertTrue(isLoginPanelDisplayed, "Login Panel is not displayed");
		log.info("Login panel is displayed successfully on login page");
		
		log.info("STEP : Enter Credentials");
		loginPage.enterCredentials("Admin", "");
		softAssert.assertAll();
		log.info("Credentials are entered on Login page");
		
		log.info("STEP : Click on Login button");
		loginPage.clickOnLoginButton();
		log.info("Clicked on login button on Login page");
		
		String expectedErrorMessage = "Password cannot be empty";
		String actualErrorMessage = loginPage.getLoginErrorMessage();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid Password error message");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			PredefinedActions.captureScreenshot(result.getMethod().getMethodName());
		}
		PredefinedActions.closeBrowser();
	}
	
	/*@AfterClass
	public void teardown() {
		PredefinedActions.closeBrowser();
	}*/
}
