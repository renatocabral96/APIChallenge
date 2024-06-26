package com.example.common;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
    private WebDriver driver;
    private WebDriverWait wait;


    public Common() {
    }
    
    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    
    public void Initializer() {
    	String chromeDriverPath = Paths.get("drivers", "chromedriver.exe").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        driver = new ChromeDriver(options);
        driver.get("https://www.bolttech.co.th/en/ascend/device-protection?utm_source=ascend");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement findShadowDropdown() {
        WebElement shadowHost1 = driver.findElement(By.cssSelector("#\\37 5715 > edi-section > edi-device-protection-form")); 
        SearchContext shadowRoot1 = shadowHost1.getShadowRoot();

        WebElement shadowHost2 = shadowRoot1.findElement(By.cssSelector("#DeviceProtectionForm > edi-device-detection")); 
        SearchContext shadowRoot2 = shadowHost2.getShadowRoot();
        
        wait.until(ExpectedConditions.visibilityOf(shadowHost2));
        
        WebElement dropdown = shadowRoot2.findElement(By.cssSelector("#purchasePrice"));
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        
        return dropdown;
    }

    public void closeCookiesPrompt() {
        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#onetrust-close-btn-container > button"))); // Replace with the actual selector
            cookiesButton.click();
        } catch (Exception e) {
            System.out.println("No cookies prompt found.");
        }
    }  
}
