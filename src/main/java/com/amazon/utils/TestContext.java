package com.amazon.utils;

import org.openqa.selenium.WebDriver;

import com.amazon.pages.HomePage;
import com.amazon.pages.SearchResultPage;

public class TestContext {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultPage searchResultPage;
    private Utility utility;
    public ConfigReader configReader;

    // Default constructor
    public TestContext() throws InterruptedException {
        configReader = ConfigReader.getInstance();
        // Set up WebDriver here
        driver = DriverFactory.initDriver(configReader.getBrowser());
        initializeContext(driver); // Call the parameterized constructor to initialize context
        System.out.println("%%%%%%%%%%%%%%TestContext Driver Initializing: " + getDriver());
    }

    private void initializeContext(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        homePage = new HomePage(driver);
        searchResultPage = new SearchResultPage(driver);
        utility = new Utility(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public SearchResultPage getSearchResultPage() {
        return searchResultPage;
    }

    public Utility getUtility() {
        return utility;
    }
    
    public String getUrl() {
        return ConfigReader.getUrl();
    }

    public void quitDriver() {
        if (driver != null) {
            System.out.println("%%%%%%%%%%%%%%TestContext Driver closing: " + driver);
            driver.quit();
            driver = null;
        }
        // Nullify other references to allow garbage collection
        homePage = null;
        searchResultPage = null;
        utility = null;
    }
    
    public void resetDriver() throws InterruptedException {
        quitDriver();
        ConfigReader.resetInstance();  // Reset ConfigReader instance
        configReader = ConfigReader.getInstance();  // Get a new instance
        driver = DriverFactory.initDriver(configReader.getBrowser());
        initializeContext(driver); // Reinitialize context after resetting the driver
    }

}
