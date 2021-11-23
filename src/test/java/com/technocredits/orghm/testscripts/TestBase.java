package com.technocredits.orghm.testscripts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.asserts.SoftAssert;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.pages.LoginPage;
import com.technocredits.orghm.pages.MenuPage;

public class TestBase {

	static {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM");
		System.setProperty("current.date.time", dateFormat.format(date));
		PropertyConfigurator.configure("./src/main/resources/log4j.properties");
	}
	
	SoftAssert softAssert = new SoftAssert();
	Logger log = Logger.getLogger(TestBase.class);

	public MenuPage setup() {
		log.info("STEP : Launch OrgHm Application");
		PredefinedActions.start();
		log.info("URL launch successfully");
		
		log.info("STEP : Enter Credentials");
		LoginPage loginPage = LoginPage.getObject();
		//ib@W7uVF8O
		loginPage.enterCredentials("Admin", "CSxe9EW@e0");
		log.info("Successfully entered credentials On login page");
		
		log.info("STEP : Click on Login button");
		return loginPage.clickOnLoginButton();
	}
	
	public void teardown() {
		PredefinedActions.closeBrowser();
	}
}
