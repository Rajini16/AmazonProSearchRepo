package com.amazon.runners;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.amazon.utils.ExtentTestNGITestListener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"com.amazon.stepdefinitions", "com.amazon.hooks"}, 
//		dryRun= true,
		monochrome = true, // Display the console output in a readable format
		publish = true,
		plugin= {
				"pretty",
						"html:cucumber-reports/cucumber.html",
						"json:cucumber-reports/cucumber.json",
						"junit:cucumber-reports/Cucumber.xml",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"timeline:test-output-thread/"
						
		}
		)
@Listeners(ExtentTestNGITestListener.class)
public class ProTestNGRunner extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}	
	
}