package com.amazon.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    // Private constructor to prevent instantiation
    private DriverFactory() {
        // Initialization code here, if needed
    	System.out.println("At DriverFactory constructor");
    }

   
	public static WebDriver initDriver(String browserType)  {
		WebDriver driver = null;
		int i=0;

		if (driver == null) {
			switch (browserType.toLowerCase()) {
			case "chrome":
		        // Setup WebDriverManager for Chrome
				System.out.println("Before Browser Invoke: " + i++);
		        WebDriverManager.chromedriver().setup();
		        driver = new ChromeDriver();
		    	System.out.println("After Browser Invoke: " + i++);
		        break;
			case "firefox":
		        // Setup WebDriverManager for Firefox
		        WebDriverManager.firefoxdriver().setup();
		        driver = new FirefoxDriver();
		        break;
			case "edge":
		        // Setup WebDriverManager for Edge
		        WebDriverManager.edgedriver().setup();
		        driver = new EdgeDriver();
		        break;    
			case "ie":
		        // Setup WebDriverManager for Internet Explorer
		        WebDriverManager.iedriver().setup();
		        driver = new InternetExplorerDriver();
		        break;   
		    default:
		    	throw new IllegalArgumentException("Unsupported browser Type: " + browserType);
			}
			
//			Thread.sleep(9000);
			driver.get("http://www.google.com");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));	            
		}		
		return driver;
	}

	
	public void quitDriver(WebDriver driver) {
		if(driver != null) {
			System.out.println("closing the driver: ");
		 driver.quit();
			driver = null;
		}
	}
	
}
