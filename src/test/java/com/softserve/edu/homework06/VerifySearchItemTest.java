package com.softserve.edu.homework06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifySearchItemTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final By CURRENCY_DROPDOWN = By.cssSelector(".btn.btn-link.dropdown-toggle");
    private static final By EURO = By.name("EUR");
    private static final By INPUT_SEARCH_FIELD = By.cssSelector(".form-control.input-lg");
    private static final By SEARCH_BUTTON = By.cssSelector(".input-group-btn");
    private static final By EURO_ICON = By.xpath("//strong[contains(., '€')]");
    private static final By IPHONE_XR_PRICE = By.xpath("//h4[contains(., 'iPhone XR')]/following-sibling::p[@class]");

    @BeforeClass
    public void setup() {
        // Setup driver and open URL
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void testSearchItem() {

        // Change currency to USD
        driver.findElement(CURRENCY_DROPDOWN).click();
        driver.findElement(EURO).click();
        Assert.assertTrue(driver.findElement(EURO_ICON).isDisplayed(), "Currency wasn`t changed!");

        // Click on the search input field, clear, fill in search request and click search button
        driver.findElement(INPUT_SEARCH_FIELD).click();
        driver.findElement(INPUT_SEARCH_FIELD).clear();
        driver.findElement(INPUT_SEARCH_FIELD).sendKeys("iPhone");
        driver.findElement(SEARCH_BUTTON).click();

        // Verify result item
        Assert.assertTrue(driver.findElement(IPHONE_XR_PRICE).getText().split("\n")[0].equals("707.71€"),
                "[iPhone XR] wasn`t found in result grid!");
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
