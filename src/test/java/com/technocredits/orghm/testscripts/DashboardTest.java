package com.technocredits.orghm.testscripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.pages.DashboardPage;

public class DashboardTest extends TestBase {
	
	Logger log = Logger.getLogger(DashboardTest.class);

	@BeforeClass
	public void setUp() {
		super.setup();
		log.info("URL launch successfully and navigate to Dashboard page");
	}

	@Test
	public void verifyDashboard() {

		log.info("STEP : Verify page header title");
		DashboardPage dashboardPage = DashboardPage.getObject();
		String actualPageHeaderTitle = dashboardPage.getHeaderTitle();
		String expectedPageHeaderTitle = "Dashboard";
		softAssert.assertEquals(actualPageHeaderTitle, expectedPageHeaderTitle, "Invalid page header title");
		log.info("Page Header match on Dashboard page");
		
		log.info("STEP : Verify page title");
		String actualPageTitle = dashboardPage.getPageTitle();
		String expectedPageTitle = "Dashboard";
		softAssert.assertEquals(actualPageTitle, expectedPageTitle, "Invalid page title");
		log.info("Page Title match on Dashboard page");
		
		log.info("STEP : Verify count of total widgets is 12");
		int actualCount = dashboardPage.getTotalWidgets();
		int expectedCount = 12;
		softAssert.assertEquals(actualCount, expectedCount, "Invalid count of total widgets");
		softAssert.assertAll();
		log.info("Total widgets count match on Dashboard page");
		
		log.info("STEP : Verify widgets name");
		List<String> actualList = dashboardPage.getAllWidgetsText();

		List<String> expectedList = new ArrayList<String>();
		expectedList.add("Quick Access");
		expectedList.add("Buzz Latest Posts");
		expectedList.add("Employee Job Details");
		expectedList.add("My Actions");
		expectedList.add("Headcount by Location");
		expectedList.add("Employees on Leave Today");
		expectedList.add("Time At Work");
		expectedList.add("Performance Quick Feedback");
		expectedList.add("Annual basic payment by Location");
		expectedList.add("Latest Documents");
		expectedList.add("Latest News");
		//expectedList.add("Covid - 19 Vaccination");
		expectedList.add("Yearly New Hires");
		Assert.assertEquals(actualList, expectedList, "Invalid widgets name");
		log.info("List of Widgets name texts match on Dashboard page");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			PredefinedActions.captureScreenshot(result.getMethod().getMethodName());
		}
	}

	@AfterClass
	public void tearDown() {
		super.teardown();
	}
}
