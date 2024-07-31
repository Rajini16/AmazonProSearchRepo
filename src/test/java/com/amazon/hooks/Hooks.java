package com.amazon.hooks;

import com.amazon.utils.TestContext;

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
    	
    	System.out.println("AT @BeforeScenario method Hooks Class");
    	
    	testContext.getDriver().get(testContext.getUrl());
    	
 	    System.out.println("At @BeforeScenaario url from config file: " + testContext.getUrl());
   	
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
    	
    	System.out.println("AT @AfterStep method Hooks Class"); 
    	   
    }

    @After
    public void afterScenario(Scenario scenario) {
    	
    	System.out.println("#########################AT @AfterScenario method Hooks Class");
    	
    	testContext.quitDriver();
    }

}
