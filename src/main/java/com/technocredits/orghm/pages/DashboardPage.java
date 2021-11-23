package com.technocredits.orghm.pages;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.technocredits.orghm.base.PredefinedActions;
import com.technocredits.orghm.constants.ConstantPath;
import com.technocredits.orghm.util.PropertiesOperations;

public class DashboardPage extends PredefinedActions {

	private static DashboardPage dashboardPage;
	private PropertiesOperations properties;
	Logger log = Logger.getLogger(DashboardPage.class);

	public static DashboardPage getObject() {
		if (dashboardPage == null)
			dashboardPage = new DashboardPage();
		return dashboardPage;
	}

	private DashboardPage() {
		try {
			properties = new PropertiesOperations(ConstantPath.DASHBOARDPAGE_LOCATOR_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getHeaderTitle() {
		String header = getPageHeaderTitle();
		log.debug("Header on dashboard page: " + header);
		return header;
	}

	public String getPageTitle() {
		String pageTitle = getTextOfElement(getElement(properties.getValue("pageTitle"), false));
		log.debug("Page title on dashboard page: " + pageTitle);
		return pageTitle;
	}

	public int getTotalWidgets() {
		int size = getElements(properties.getValue("totalWidgets"), true).size();
		log.debug("Total widgets on dashboard page: " + size);
		return size;
	}

	public List<String> getAllWidgetsText() {
		List<String> textList = getTextOfAllElements(properties.getValue("allWidgetsText"), false);
		log.debug("All widgets text on dashboard page: " + textList);
		return textList;
	}

	public String getAllWidgetTextBasedOnIndex(int index) {
		List<String> widgetsList = getAllWidgetsText();
		String widgetText = widgetsList.get(index);
		log.debug("Widget text based on index on dashboard page: " + widgetText);
		return widgetText;
	}
}
