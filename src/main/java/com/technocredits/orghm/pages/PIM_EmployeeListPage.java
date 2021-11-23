package com.technocredits.orghm.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.constants.ConstantPath;
import com.technocredits.orghm.pojo.Employee;
import com.technocredits.orghm.util.PropertiesOperations;

public class PIM_EmployeeListPage extends PredefinedActions {

	private static PIM_EmployeeListPage pim_employeeListPage;
	private PropertiesOperations properties;

	public static PIM_EmployeeListPage getObject() {
		if (pim_employeeListPage == null)
			pim_employeeListPage = new PIM_EmployeeListPage();
		return pim_employeeListPage;
	}

	private PIM_EmployeeListPage() {
		try {
			properties = new PropertiesOperations(ConstantPath.PIM_EMPLOYEELISTPAGE_LOCATOR_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PIM_EmployeeListPage searchEmployee(String name) {
		enterText(getElement(properties.getValue("searchEmployee"), false), name);
		return this;
	}

	public PIM_EmployeeListPage clickOnQuickSearchIcon(boolean isWaitRequired) {
		clickOnElement(getElement(properties.getValue("quickSearchIcon"), true));
		return this;
	}

	public boolean isEmpPresent(String empName) {
		return isElementDisplayed(getElement(properties.getValue("isEmpPresent"), empName, false));
	}

	public int getRecordCount() {
		return getElements(properties.getValue("recordCount"), false).size();
	}

	public Employee getEmployeeDetailsById(boolean isEmployeeExpected) {
		if (isEmployeeExpected)
			waitTillNumbersOfElementToBe(properties.getValue("recordCount"), 1);
		return getListOfEmployee().get(0);
	}

	public List<Employee> getListOfEmployee() {
		int totalRows = getRecordCount();
		List<Employee> listOfEmp = new ArrayList<Employee>();
		for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
			Employee emp = new Employee();
			emp.setEmpId(getTextOfElement(getElement(properties.getValue("empId"), rowIndex, false)));
			emp.setEmpName(getTextOfElement(getElement(properties.getValue("empName"), rowIndex, false)));
			emp.setJobTitle(getTextOfElement(getElement(properties.getValue("jobTitle"), rowIndex, false)));
			emp.setStatus(getTextOfElement(getElement(properties.getValue("status"), rowIndex, false)));
			emp.setSubUnit(getTextOfElement(getElement(properties.getValue("subUnit"), rowIndex, false)));
			emp.setCostCenter(getTextOfElement(getElement(properties.getValue("costCenter"), rowIndex, false)));
			emp.setLocation(getTextOfElement(getElement(properties.getValue("location"), rowIndex, false)));
			emp.setSupervisor(getTextOfElement(getElement(properties.getValue("supervisor"), rowIndex, false)));
			listOfEmp.add(emp);
		}
		return listOfEmp;
	}

	public PIM_EmployeeListPage setEmpIdInQuickSearch(String empId) {
		return setEmpNameInQuickSearch(empId);
	}

	public PIM_EmployeeListPage setEmpNameInQuickSearch(String empName) {
		enterText(getElement(properties.getValue("empNameInQuickSearch"), true), empName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		return this;
	}

	public void waitTillSuggestionBoxAppear() {
		System.out.println("wait until the list get populated and then click quick search");
		getElement(properties.getValue("suggestionBox"), true);
	}
}
