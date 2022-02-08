package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesFileReader {

	public static PropertiesFileReader envProperties;

	private Properties properties;

	private PropertiesFileReader(String filepath) {
		properties = loadProperties(filepath);
	}

	private Properties loadProperties(String filepath) {
		File file = new File(filepath);
		FileInputStream fileInput = null;
		Properties props = new Properties();
		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
			fileInput.close();
		} 
		catch (FileNotFoundException e) {} 
		catch (IOException e) {}
		return props;
	}

	public static PropertiesFileReader getInstance(String filepath) {
		if (envProperties == null) {
			envProperties = new PropertiesFileReader(filepath);
		}
		return envProperties;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public boolean hasProperty(String key) {
		return StringUtils.isNotBlank(properties.getProperty(key));
	}
}
