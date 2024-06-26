package com.example.stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.After;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

import com.example.common.Common;


public class FirstReq {
	private WebDriver driver;
	private WebDriverWait wait;
	private Common common;
    
    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
    	common = new Common();
        common.Initializer();
        driver = common.getDriver();
        wait = common.getWait();
        common.closeCookiesPrompt();
    }
    
    @When("I select the dropdown price list and wait")
	public void selectDropdownPriceListAndWait() {
		WebElement dropdown = common.findShadowDropdown();
		assertTrue(dropdown.isDisplayed());
	}
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
