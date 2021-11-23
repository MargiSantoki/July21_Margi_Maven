package com.technocredits.orghm.testscripts;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.constants.ConstantPath;
import com.technocredits.orghm.pages.MenuPage;
import com.technocredits.orghm.pages.PIM_AddEmployeePage;
import com.technocredits.orghm.pages.PIM_EmployeeListPage;
import com.technocredits.orghm.pojo.Employee;
import com.technocredits.orghm.util.ExcelOperations;

public class PIM_AddEmployeeTest extends TestBase {

	private MenuPage menuPage;
	Logger log = Logger.getLogger(PIM_AddEmployeeTest.class);

	@BeforeMethod
	public void setUp() {
		menuPage = super.setup();
		log.info("URL launch successfully and navigate to Dashboard page");
	}

	@Test
	public void verifyAddEmployee() {

		log.info("STEP : Navigate to PIM -> Add Employee");
		menuPage.navigateTo("PIM->Add Employee");
		PIM_AddEmployeePage pim_addEmpPage = PIM_AddEmployeePage.getObject();
		log.info("Successfully Navigate to PIM->Add Employee page");
		
		log.info("STEP : Verify Add employee is successfully saved");
		String empId = pim_addEmpPage.getEmployeeId();
		pim_addEmpPage.setEmpFirstName("Techno").setEmpMiddleName("A").setEmpLastName("Credits")
				.setEmpLocation("Canadian Development Center").clickOnNext().setHobbies("reading").clickOnNext()
				.setWorkShift("Twilight").setEffectiveFrom("20").setRegion("Region-1").setFte("0.75")
				.setTemporaryDepartment("Sub unit-1").clickOnSaveButton();

		boolean isSuccessfullySavedVisible = pim_addEmpPage.isSuccessfullySaved();
		Assert.assertTrue(isSuccessfullySavedVisible, "Successfully saved message not visible");
		log.info("Successfully Saved message is visible on Pim_AddEmployeePage");

		boolean isUserNameDisplayed = pim_addEmpPage.isUsernameTitleDisplayed();
		Assert.assertTrue(isUserNameDisplayed, "Invalid Add Employee");
		log.info("Username is displayed on Pim_AddEmployeePage");

		PIM_EmployeeListPage pim_empListPage = PIM_EmployeeListPage.getObject();
		log.info("STEP : Navigate to Employee List");
		menuPage.navigateTo("Employee List");
		log.info("Successfully navigate to employee list on PIM_AddEmployeePage");

		/*
		 * System.out.
		 * println("STEP : Verify record counts according to default pagination"); int
		 * actualRecord = pim_empListPage.getRecordCount(); int expectedRecord = 50;
		 * softAssert.assertEquals(actualRecord, expectedRecord,
		 * "Invalid record counts"); softAssert.assertAll();
		 */

		log.info("STEP : Verify added employee is present in employee list");
		List<Employee> listOfEmployee = pim_empListPage.setEmpIdInQuickSearch(empId).getListOfEmployee();
		Assert.assertEquals(listOfEmployee.get(0).getEmpId(), empId, "Invalid list of employee");
		Assert.assertTrue(listOfEmployee.size() == 1, "Invalid size");
		log.info("STEP : Verify added employee is present in employee list on PIM_AddEmployeePage");
	}

	@Test(dataProvider = "employeeDataProvider")
	public void verifyAddEmployeeUsingDataDriven(String firstName, String middleName, String lastName, String location,
			String hobbies, String workShift, String effectiveFrom, String region, String fte, String tempDept) {

		log.info("STEP : Navigate to PIM -> Add Employee");
		menuPage.navigateTo("PIM->Add Employee");
		PIM_AddEmployeePage pim_addEmpPage = PIM_AddEmployeePage.getObject();
		log.info("Successfully Navigate to PIM->Add Employee page");

		log.info("STEP : Verify Add employee is successfully saved");
		String empId = pim_addEmpPage.getEmployeeId();
		pim_addEmpPage.setEmpFirstName(firstName).setEmpMiddleName(middleName).setEmpLastName(lastName)
				.setEmpLocation(location).clickOnNext().setHobbies(hobbies).clickOnNext().setWorkShift(workShift)
				.setEffectiveFrom(effectiveFrom).setRegion(region).setFte(fte).setTemporaryDepartment(tempDept)
				.clickOnSaveButton();

		boolean isSuccessfullySavedVisible = pim_addEmpPage.isSuccessfullySaved();
		Assert.assertTrue(isSuccessfullySavedVisible, "Successfully saved message not visible");
		log.info("Successfully Saved message is visible on Pim_AddEmployeePage");

		boolean isUserNameDisplayed = pim_addEmpPage.isUsernameTitleDisplayed();
		Assert.assertTrue(isUserNameDisplayed, "Invalid Add Employee");
		log.info("Username is displayed on Pim_AddEmployeePage");

		PIM_EmployeeListPage pim_empListPage = PIM_EmployeeListPage.getObject();
		log.info("STEP : Navigate to Employee List");
		menuPage.navigateTo("Employee List");
		log.info("Successfully navigate to employee list on PIM_AddEmployeePage");

		log.info("STEP : Verify added employee is present in employee list");
		List<Employee> listOfEmployee = pim_empListPage.setEmpIdInQuickSearch(empId).getListOfEmployee();
		Assert.assertEquals(listOfEmployee.get(0).getEmpId(), empId, "Invalid list of employee");
		Assert.assertTrue(listOfEmployee.size() == 1, "Invalid size");
		log.info("STEP : Verify added employee is present in employee list on PIM_AddEmployeePage");
	}

	@DataProvider(name = "employeeDataProvider")
	public Object[][] getEmployeeData() throws IOException {
		return ExcelOperations.getData(ConstantPath.TESTDATA_PATH + ConstantPath.EMPLOYEES_DATA, ConstantPath.SHEET_DATA);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			PredefinedActions.captureScreenshot(result.getMethod().getMethodName());
		}
		super.teardown();
	}
}
