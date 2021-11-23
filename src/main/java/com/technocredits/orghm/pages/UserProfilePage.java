package com.technocredits.orghm.pages;

import java.io.IOException;
import java.util.List;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.constants.ConstantPath;
import com.technocredits.orghm.util.PropertiesOperations;

public class UserProfilePage extends PredefinedActions {

	private static UserProfilePage userProfilePage;
	private PropertiesOperations properties;

	private UserProfilePage() {
		try {
			properties = new PropertiesOperations(ConstantPath.USERPROFILEPAGE_LOCATOR_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static UserProfilePage getObject() {
		if (userProfilePage == null)
			userProfilePage = new UserProfilePage();
		return userProfilePage;
	}

	public boolean isProfilePictureDisplayed() {
		return isElementDisplayed(getElement(properties.getValue("profilePicture"), false));
	}

	public void clickOnUserDropdown() {
		clickOnElement(getElement(properties.getValue("clickOnUserDropDown"), false));
	}

	public List<String> getListOfUserDropdownOptions() {
		return getTextOfAllElements(properties.getValue("userDropdownOptions"), false);
	}

	public int getTotalAvailableOptions() {
		return getListOfUserDropdownOptions().size();
	}

	public void clickOnAbout() {
		clickOnElement(getElement(properties.getValue("clickOnAbout"), false));
	}

	public void clickOnSubMenu(String subMenu) {
		clickOnElement(getElement(properties.getValue("clickOnSubMenu"), subMenu, false));
	}

	public String getCmpNameFromAboutPopup() {
		String company = getTextOfElement(getElement(properties.getValue("companyName"), false));
		return company.split(": ")[1];
	}

	public String getVersionFromAboutPopup() {
		String version = getTextOfElement(getElement(properties.getValue("version"), false));
		return version.split(": ")[1];
	}

	public String getEmployeesFromAboutPopup() {
		String employees = getTextOfElement(getElement(properties.getValue("employees"), true));
		return employees.split(": ")[1];
	}

	public String getUsersFromAboutPopup() {
		String users = getTextOfElement(getElement(properties.getValue("users"), false));
		return users.split(": ")[1];
	}

	public String getRenewalOnFromAboutPopup() {
		String renewal = getTextOfElement(getElement(properties.getValue("renewal"), false));
		return renewal.split(": ")[1];
	}

	public boolean isEmpCountGreaterThanZero() {
		boolean flag = true;
		String emp = getEmployeesFromAboutPopup();
		int totalEmp = Integer.parseInt(emp.split(" ")[0]);
		if (totalEmp < 0)
			flag = false;
		else
			flag = true;
		return flag;
	}

	public List<String> getAboutMenuText() {
		return getTextOfAllElements(properties.getValue("aboutMenuText"), false);
	}

	public void clickOnOkButton() {
		clickOnElement(getElement(properties.getValue("okButton"), false));
	}
}
