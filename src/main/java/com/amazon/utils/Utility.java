package com.amazon.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.pages.SearchResultPage;

public class Utility {

    private  WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public Utility(WebDriver driver) throws InterruptedException {
        this.driver = driver;
//    S	driver = TestContext.getInstance().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        actions = new Actions(driver);
    }


	public void highlightElement(WebElement element) throws InterruptedException {
		 actions = new Actions(driver);
        // Scroll to the element's location
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
        
        actions.moveToElement(element).perform();
        
        // Highlight with a bold dark color border
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid darkred';", element);

        // Capture screenshot
//        captureScreenshot();
        Thread.sleep(2000); // Wait to see the highlighting effect
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='';", element); // Reset style
    }

	public byte[] captureScreenshot() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotFileName = "screenshot_" + timestamp + ".png";
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot captured: " + screenshotFileName);
            return Files.readAllBytes(screenshotFile.toPath());
        } catch (IOException ex) {
            System.err.println("Failed to capture screenshot: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
	public String captureScreenshotReport(String scenarioName) throws IOException {
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Ensure the screenshot directory exists
        String screenshotDir = System.getProperty("user.dir") + "/target/screenshots/";
        Files.createDirectories(Paths.get(screenshotDir));

        // Sanitize scenario name for use as a file name
        String sanitizedScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
        String screenshotPath = screenshotDir + sanitizedScenarioName + ".png";

        // Save screenshot to file
        File screenshotFile = new File(screenshotPath);
        FileUtils.copyFile(sourcePath, screenshotFile);

        return screenshotFile.getAbsolutePath();
	}
 
    
    public boolean validateUrl() {
    	
    	String currentUrl = driver.getCurrentUrl(); // Actual URL
        String expectedUrl = ConfigReader.getUrl(); // Expected URL
        expectedUrl = "https://www.amazon.in/";

        if (!currentUrl.equals(expectedUrl)) {
            return false; // URL is not valid
        }
        return true; // URL is valid   
    }
    
	public void validateProductItems(SearchResultPage searchResultPage) throws InterruptedException {
	
//			for (WebElement element :  searchResultPage.productImgList()) {
//			highlightElement(element, actions);	
//            WebElement imgText = element.findElement(By.xpath(".//h2//span")); 
//            System.out.println("Element text: " + element.getAttribute("data-index")+ ": "+ element.getAttribute("data-component-id")+"  ::"+ element.getText());
//            }
			int i = 1;
			for (WebElement element :  searchResultPage.getSearchResults()) {			
			try {
              highlightElement(element);
              System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
              System.out.println("Product Details # "+ i++ +":\n " + element.getText());
              System.out.println("****************************************************");
			} catch (InterruptedException e) {
              e.printStackTrace();
			}
			}
       			
		}

}
