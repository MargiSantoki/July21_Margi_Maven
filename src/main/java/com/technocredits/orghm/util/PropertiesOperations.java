package com.technocredits.orghm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.technocredits.orghm.constants.ConstantPath;

public class PropertiesOperations {

	private Properties properties;

	public PropertiesOperations(String fileName) throws IOException {
		File file = new File(ConstantPath.PROPERTIES_BASE_PATH + fileName + ".properties");
		FileInputStream inputStream = new FileInputStream(file);
		properties = new Properties();
		properties.load(inputStream);
	}

	public String getValue(String key) {
		String value = properties.getProperty(key);
		return value;
	}
}
