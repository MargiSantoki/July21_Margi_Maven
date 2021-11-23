package com.technocredits.orghm.pages;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.constants.ConstantPath;
import com.technocredits.orghm.util.PropertiesOperations;

public class PIM_AddEmployeePage extends PredefinedActions {

	private static PIM_AddEmployeePage pim_addEmployeePage;
	private PropertiesOperations properties;

	public static PIM_AddEmployeePage getObject() {
		if (pim_addEmployeePage == null)
			pim_addEmployeePage = new PIM_AddEmployeePage();
		return pim_addEmployeePage;
	}

	private PIM_AddEmployeePage() {
		try {
			properties = new PropertiesOperations(ConstantPath.PIM_ADDEMPLOYEEPAGE_LOCATOR_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEmployeeId() {
		WebElement employeeId = getElement(properties.getValue("employeeId"), true);
		return employeeId.getAttribute("value");
	}
	
	public PIM_AddEmployeePage setEmpFirstName(String fname) {
		enterText(getElement(properties.getValue("empFirstName"), false), fname);
		return this;
	}

	public PIM_AddEmployeePage setEmpMiddleName(String mname) {
		enterText(getElement(properties.getValue("empMiddleName"), false), mname);
		return this;
	}

	public PIM_AddEmployeePage setEmpLastName(String lname) {
		enterText(getElement(properties.getValue("empLastName"), false), lname);
		return this;
	}

	public PIM_AddEmployeePage setEmpLocation(String location) {
		clickOnElement(getElement(properties.getValue("locationDropDown"), false));
		clickOnElement(getElement(properties.getValue("location"), location, false));
		return this;
	}
	
	public PIM_AddEmployeePage clickOnNext() {
		try {
			clickOnElement(getElement(properties.getValue("clickOnNextButton"), false));
		}catch(ElementClickInterceptedException e) {
			smartClickByJS(getElement(properties.getValue("clickOnNextButton"), false));
		}
		return this;
	}

	public PIM_AddEmployeePage setHobbies(String hobbies) {
		enterText(getElement(properties.getValue("hobbies"), true), hobbies);
		return this;
	}

	public PIM_AddEmployeePage setWorkShift(String shiftValue) {
		clickOnElement(getElement(properties.getValue("workShiftDropDown"), true));
		clickOnElement(getElement(properties.getValue("workShift"), shiftValue, true));
		return this;
	}

	public PIM_AddEmployeePage setEffectiveFrom(String date) {
		date = date.split("\\.")[0];
		clickOnElement(getElement(properties.getValue("setEffectiveFromDropDown"), false));
		clickOnElement(getElement(properties.getValue("setEffectiveFrom"), date, true));
		return this;
	}

	public PIM_AddEmployeePage setRegion(String region) {
		clickOnElement(getElement(properties.getValue("setRegionDropDown"), true));
		clickOnElement(getElement(properties.getValue("setRegion"), region, true));
		return this;
	}

	public PIM_AddEmployeePage setFte(String fteInput) {
		if (fteInput.equals("1.0"))
			fteInput = "1";
		clickOnElement(getElement(properties.getValue("fteDropDown"), false));
		clickOnElement(getElement(properties.getValue("fte"), fteInput, true));
		return this;
	}

	public PIM_AddEmployeePage setTemporaryDepartment(String tempDept) {
		clickOnElement(getElement(properties.getValue("tempDeptDropDown"), false));
		clickOnElement(getElement(properties.getValue("tempDept"), tempDept, true));
		return this;
	}

	public void addEmployeeInfo(String fname, String mname, String lname, String location) {
		setEmpFirstName(fname);
		setEmpMiddleName(mname);
		setEmpLastName(lname);
		setEmpLocation(location);
	}

	public PIM_AddEmployeePage clickOnSaveButton() {
		clickOnElement(getElement(properties.getValue("saveButton"), false));
		return this;
	}

	public boolean isSuccessfullySaved() {
		try {
			getElement(properties.getValue("successfullySavedMessage"), true);
			return true;
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean isUsernameTitleDisplayed() {
		try {
			getElement(properties.getValue("isUsernameTitleDisplayed"), true);
			return true;
		} catch (TimeoutException te) {
			return false;
		}
	}
}
