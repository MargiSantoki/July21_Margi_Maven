package com.technocredits.orghm.testscripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.pages.UserProfilePage;

public class UserProfileTest extends TestBase {

	Logger log = Logger.getLogger(UserProfileTest.class);
	
	@BeforeClass
	public void setUp() {
		super.setup();
		log.info("URL launch successfully and navigate to Dashboard page");
	}

	@Test
	public void verfiyUserMenuOptions() {
		UserProfilePage userProfilePage = UserProfilePage.getObject();
		log.info("STEP : Verify user profile picture is displayed");
		softAssert.assertTrue(userProfilePage.isProfilePictureDisplayed());
		log.info("User profile picture is displayed successfully on user profile page");

		log.info("STEP : Click on user dropdown");
		userProfilePage.clickOnUserDropdown();
		log.info("Successfully clicked on user dropdown on user profile page");

		log.info("STEP : Verify total options");
		int actualCount = userProfilePage.getTotalAvailableOptions();
		int expectedCount = 3;
		softAssert.assertEquals(actualCount, expectedCount, "Invalid number of options");
		softAssert.assertAll();
		log.info("Total options are matched on user profile page");

		log.info("STEP : Verify Drop down options");
		List<String> actualList = userProfilePage.getListOfUserDropdownOptions();
		List<String> expectedList = new ArrayList<>();
		expectedList.add("About");
		expectedList.add("Change Password");
		expectedList.add("Logout");
		softAssert.assertEquals(actualList, expectedList, "Invalid options");
		log.info("Dropdown options are matched on user profile page");

		log.info("STEP : Click on About");
		userProfilePage.clickOnAbout();
		log.info("Successfully clicked on about on user profile page");

		log.info("STEP : Verify employee count is more than 0");
		softAssert.assertTrue(userProfilePage.isEmpCountGreaterThanZero());
		log.info("Employee count is verified on user profile page");

		log.info("STEP : Verify company name");
		String actualCompanyName = userProfilePage.getCmpNameFromAboutPopup();
		String expectedCompanyName = "OrangeHRM (Pvt) Ltd(Parallel Demo)";
		softAssert.assertEquals(actualCompanyName, expectedCompanyName);
		log.info("Company name is verified on user profile page");

		log.info("STEP : Verify version");
		String actualVersion = userProfilePage.getVersionFromAboutPopup();
		//OrangeHRM 7.3.164919
		String expectedVersion = "OrangeHRM 7.2.159485";
		softAssert.assertEquals(actualVersion, expectedVersion);
		log.info("Version is verified on user profile page");

//		System.out.println("STEP : Verify employees");
//		String actualEmployees = userProfilePage.getEmployeesFromAboutPopup();
//		String expectedEmployees = "101 (299 more allowed)";
//		softAssert.assertEquals(actualEmployees, expectedEmployees);

		log.info("STEP : Verify users");
		String actualUsers = userProfilePage.getUsersFromAboutPopup();
		//80 (320 more allowed)
		String expectedUsers = "77 (723 more allowed)";
		softAssert.assertEquals(actualUsers, expectedUsers);
		log.info("Users are verified on user profile page");

		log.info("STEP : Verify Renewal On");
		String actualRenewalOn = userProfilePage.getRenewalOnFromAboutPopup();
		//Tue, 04 Oct 2022
		String expectedRenewalOn = "Wed, 01 Jun 2022";
		softAssert.assertEquals(actualRenewalOn, expectedRenewalOn);
		softAssert.assertAll();
		log.info("Renewal on is verified on user profile page");

		log.info("STEP : Click on OK Button");
		userProfilePage.clickOnOkButton();
		softAssert.assertAll();
		log.info("Successfully clicked on OK button on user profile page");
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
