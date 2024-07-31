package com.amazon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.*;

public class ExtentTestNGITestListener implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Load properties from extent.properties file
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("extent.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find extent.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Initialize ExtentReports
        extent = new ExtentReports();

        // Create ExtentSparkReporter and attach to ExtentReports
        String reportPath = properties.getProperty("extent.reporter.spark.out");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        // Load XML configuration
        try {
            sparkReporter.loadXMLConfig("src/test/resources/extent-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Attach reporter
        extent.attachReporter(sparkReporter);
        
        System.out.println("onStart: ExtentReports Initialized");
    }

    @Override
    public void onTestStart(ITestResult result) {
//        test = extent.createTest(result.getMethod().getMethodName());
        System.out.println("onTestStart: Test Started - " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
        System.out.println("onTestSuccess: Test Passed - " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());
        System.out.println("onTestFailure: Test Failed - " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
        System.out.println("onTestSkipped: Test Skipped - " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            System.out.println("onFinish: ExtentReports Flushed");
        }
    }
    
 // Static method to access the ExtentReports instance
    public static ExtentReports getExtentReports() {
        return extent;
    }
    
    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }

    public static ExtentTest getTest() {
        return test;
    }
}

    
    

