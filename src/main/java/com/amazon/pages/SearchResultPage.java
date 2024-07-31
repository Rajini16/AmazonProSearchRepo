package com.amazon.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {

	WebDriverWait wait;	
	
	@FindBy(css = ".a-section.a-spacing-small.a-spacing-top-small > span:nth-child(3)")
	WebElement searchResultInfoBar;
	
	@FindBy(xpath =  "//div[@data-component-type='s-search-result']")
	List<WebElement> searchResultsImgList;

	@FindBy(xpath = ".//img")
	WebElement imgElementText;
	
	
	public SearchResultPage(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
	}
    
	public boolean isProductTextPresentOnInfoBar(String productName) {
     
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(searchResultInfoBar, productName));
            return true; // Text found
        } catch (Exception e) {
            return false; // Text not found
        }
    } 
	
	public List<WebElement> getSearchResults() {
		System.out.println("Number of unique imgResults results found: " + searchResultsImgList.size());
        return searchResultsImgList;
	}

}


