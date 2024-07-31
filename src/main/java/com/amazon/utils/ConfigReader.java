package com.amazon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private static Properties properties = new Properties();

    // Private constructor to prevent instantiation outside of the class
    private ConfigReader() {
    	System.out.println("At ConfigReader constructor\n");
        loadProperties();
    }

    // Static method to get the instance of ConfigReader (Singleton)
    public static synchronized ConfigReader getInstance() {
    	System.out.println("At ConfigReader getInstance ");
        if (instance == null) {
        	System.out.println("At ConfigReader getInstance() executed and assigned to insatnce object");
            instance = new ConfigReader();
        }
        return instance;
    }

    // Method to load properties from configuration file
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    public String getReportConfigPath() {
//        String reportConfigPath = properties.getProperty("reportConfigPath");
//        System.out.println("Report Config Path: " + reportConfigPath); // Debug statement
//        if (reportConfigPath != null) {
//            return reportConfigPath;
//        } else {
//            throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key: reportConfigPath");
//        }
//    }

    
    // Method to reset the singleton instance
    public static void resetInstance() {
        instance = null;
    }
    
    // Method to retrieve a property value from loaded properties
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Example method to retrieve browser type from properties
    public String getBrowser() {
        return getProperty("browserType");
    }
    
    // Method to retrieve URL from properties
    public static String getUrl() {
        return getProperty("url");
    }
}
