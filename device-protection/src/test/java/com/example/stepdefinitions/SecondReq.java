package com.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;


import com.example.common.Common;

public class SecondReq {

    private WebDriver driver;
    private  WebDriverWait wait;
	private Common common;
	private String selectedOptionText;

    
    @Given("Open the registration page")
    public void open_the_registration_page() {
    	common = new Common();
        common.Initializer();
        driver = common.getDriver();
        wait = common.getWait();
        common.closeCookiesPrompt();
    }

    @When("I select a random purchase price from the dropdown")
    public void i_select_a_random_purchase_price_from_the_dropdown() {
		WebElement dropdown = common.findShadowDropdown();
        dropdown.click();
        
        SearchContext shadowRoot2 = dropdown.getShadowRoot();
        WebElement list = shadowRoot2.findElement(By.cssSelector("#list"));
        
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(
        		list, By.cssSelector("li")));
        Random random = new Random();
        int index = random.nextInt(options.size());
        WebElement selectedOption = options.get(index);
        for (WebElement option : options) {
            if (option.getText().equals(selectedOption.getText())) {
                selectedOption.click();
                selectedOptionText = option.getText();
                //System.out.println("Selected option: " + selectedOptionText);
                break;
            }
        }
    }

    @When("I select the purchase price {string} from the dropdown")
    public void i_select_the_purchase_price_from_the_dropdown(String price) {
    	WebElement dropdown = common.findShadowDropdown();
        dropdown.click();
        
        SearchContext shadowRoot2 = dropdown.getShadowRoot();
        WebElement list = shadowRoot2.findElement(By.cssSelector("#list"));
        
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(
        		list, By.cssSelector("li")));
        
        for (WebElement option : options) {
            if (option.getText().equals(price)) {
                option.click();
                selectedOptionText = option.getText();
                break;
            }
        }
    }

    @Then("the selected price should be displayed correctly")
    public void the_selected_price_should_be_displayed_correctly() {
    	WebElement dropdown = common.findShadowDropdown();
        
        SearchContext shadowRoot2 = dropdown.getShadowRoot();
        WebElement price = shadowRoot2.findElement(By.cssSelector("#selected"));
        
        wait.until(ExpectedConditions.visibilityOf(price));

        String displayedPrice = price.getText();

        //System.out.println("Displayed selected price: " + displayedPrice);

        assertEquals("The selected price is not displayed correctly", selectedOptionText, displayedPrice);
    }
    
    @Then("I click the Select button")
    public void i_click_the_select_button() {    	
    	
        WebElement shadowHost3 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-card-slider > edi-card-vertical")); // Replace with actual selector for the Select button
        SearchContext shadowRoot3 = shadowHost3.getShadowRoot();
        
        WebElement shadowHost4 = shadowRoot3.findElement(By.cssSelector("#card > edi-card-vertical-content")); // Replace with actual selector for the Select button
        SearchContext shadowRoot4 = shadowHost4.getShadowRoot();
        
        WebElement shadowHost5 = shadowRoot4.findElement(By.cssSelector("div > edi-cta")); // Replace with actual selector for the Select button
        SearchContext shadowRoot5 = shadowHost5.getShadowRoot();

        wait.until(ExpectedConditions.elementToBeClickable(shadowRoot5.findElement(By.cssSelector("div > a > span")))).click(); // Wait for the Select button to be clickable and click it
    }
    

}
