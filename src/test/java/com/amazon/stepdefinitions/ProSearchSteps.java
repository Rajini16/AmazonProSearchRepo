package com.amazon.stepdefinitions;

import org.testng.Assert;

import com.amazon.pages.HomePage;
import com.amazon.pages.SearchResultPage;
import com.amazon.utils.TestContext;
import com.amazon.utils.Utility;

import io.cucumber.java.en.*;

public class ProSearchSteps {
	private HomePage homePage;
    private SearchResultPage searchResultPage;
    private Utility utility;
//    private WebDriver driver;
    
    public ProSearchSteps(TestContext context) throws InterruptedException {
        homePage = context.getHomePage();
        searchResultPage = context.getSearchResultPage();
        utility = context.getUtility();
        
    }
    
	
	@Given("I open the Amazon homepage")
	public void i_open_the_amazon_homepage() {
	   	
	   System.out.println("At @Given method"); 
      
       try {
           // Validate the URL
    	   utility.validateUrl();
       } catch (RuntimeException e) {
           throw e;  // Rethrow the exception to stop the test execution
       }
   }

    @When("^I enter (.+) in the search box$")
    public void i_enter_in_the_search_box(String productName) throws InterruptedException{
		
    	System.out.println("At @When1 method: " + productName );
    	
		homePage.waitForSearchBoxClickable();
		homePage.enterSearchText(productName);
        
    }
	

	@When("I click on the search button for product (.+)$")
	public void i_click_on_the_search_button(String productName) {
		
		System.out.println("At @When2 method: " + productName );
		
		homePage.clickSearchButton();
        String enteredText = homePage.ValidateenterSearchText();

        // Validate the entered text
        Assert.assertEquals(enteredText, productName, "Search text is not entered correctly.");
	}

    @Then("^I should see search results for product (.+)$")
    public void i_should_see_search_results_for_product (String productName) {
    	
		System.out.println("At @Then method: " + productName );
	}

}
