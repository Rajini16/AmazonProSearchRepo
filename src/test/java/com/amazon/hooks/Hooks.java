package com.amazon.hooks;

import com.amazon.utils.ExtentTestNGITestListener;
import com.amazon.utils.TestContext;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private TestContext testContext;

    public Hooks(TestContext context) {
        this.testContext = context;
    }

	    
    @Before
    public void beforeScenario(Scenario scenario) throws InterruptedException {
    	
    	testContext.getDriver().get(testContext.getUrl());
    	String scenarioName = scenario.getName();
    	ExtentTest test = ExtentTestNGITestListener.getExtentReports().createTest(scenarioName);
    	ExtentTestNGITestListener.setTest(test);   	
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
    	
    	   
    }

    @After
    public void afterScenario(Scenario scenario) {
    	
    	testContext.quitDriver();
    	testContext.flushReports(); // Finalize the report
    }

}
