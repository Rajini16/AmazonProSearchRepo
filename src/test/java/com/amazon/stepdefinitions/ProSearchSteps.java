package com.amazon.stepdefinitions;

import org.testng.Assert;

import com.amazon.pages.HomePage;
import com.amazon.pages.SearchResultPage;
import com.amazon.utils.ExtentTestNGITestListener;
import com.amazon.utils.TestContext;
import com.amazon.utils.Utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;

import io.cucumber.java.en.*;

public class ProSearchSteps {
	private HomePage homePage;
    private SearchResultPage searchResultPage;
    private Utility utility;
    private ExtentTest test;
    private ExtentReports extentReports;
//    private WebDriver driver;
    
    public ProSearchSteps(TestContext context, Scenario scenario) throws InterruptedException {
        homePage = context.getHomePage();
        searchResultPage = context.getSearchResultPage();
        utility = context.getUtility();
     // Fetch ExtentReports from listener
        extentReports = context.getExtentReports();
        test = extentReports.createTest(Scenario.getGherkinName());
    }
    
	    @Given("I open the Amazon homepage")
	    public void i_open_the_amazon_homepage() {
	    
	        try {
	        	// Validate the URL and assert
	            boolean isValidUrl = utility.validateUrl();
	            Assert.assertTrue(isValidUrl, "The current URL is not as expected.");
	            test.log(Status.PASS, "URL validation passed.");
	        } catch (AssertionError e) {
	            test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
	            throw e; // Optionally rethrow to fail the test
	        } catch (RuntimeException e) {
	            test.log(Status.FAIL, "URL validation failed: " + e.getMessage());
	            throw e; // Optionally rethrow to fail the test
	        }  
	    
   }

    @When("^I enter (.+) in the search box$")
    public void i_enter_in_the_search_box(String productName) throws InterruptedException{
		
    	System.out.println("At @When1 method: " + productName );
    	
		homePage.waitForSearchBoxClickable();
		homePage.enterSearchText(productName);
		test.log(Status.INFO, "Entered product: " + productName);
    }
	

	@When("I click on the search button for product (.+)$")
	public void i_click_on_the_search_button(String productName) {
		
		System.out.println("At @When2 method: " + productName );
		
		homePage.clickSearchButton();
        String enteredText = homePage.ValidateenterSearchText();

        // Validate the entered text
        Assert.assertEquals(enteredText, productName, "Search text is not entered correctly.");
        test.log(Status.PASS, "Clicked search button and validated text: " + productName);
	}

    @Then("^I should see search results for product (.+)$")
    public void i_should_see_search_results_for_product (String productName) {
    	
		System.out.println("At @Then method: " + productName );
		test.log(Status.PASS, "Search results displayed for product: " + productName);
	}

}
