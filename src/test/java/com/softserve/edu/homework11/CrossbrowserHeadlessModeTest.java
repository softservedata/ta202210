package com.softserve.edu.homework11;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CrossbrowserHeadlessModeTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final By CURRENCY_DROPDOWN = By.cssSelector(".btn.btn-link.dropdown-toggle");
    private static final By EURO = By.cssSelector("[name='EUR']");
    private static final By MACBOOK_ADD_TO_CART_BTN = By.cssSelector("div.product-layout button[onclick*='43'] > i.fa.fa-shopping-cart");
    private static final By SHOPPING_CART = By.cssSelector("ul.list-inline li > a[href*='checkout/cart']");
    private static final By TOTAL_PRICE = By.cssSelector("#content div.row tr:last-child td:last-child");

    @DataProvider(name = "browsers")
    public Object[][] setupDriver() {
        return new Object[][]{
                {DriverManagerType.CHROME},
                {DriverManagerType.FIREFOX},
                {DriverManagerType.PHANTOMJS}
        };
    }

    @Test(dataProvider = "browsers")
    public void testVerifyLoginForm(DriverManagerType driverManagerType) {

        // Setup driver
        driver = new WebDriverFactory().getWebDriver(driverManagerType);
        System.out.println("*** Now test runs on " + driverManagerType);

        // Open URL
        driver.get(BASE_URL);

        // Change currency to USD
        driver.findElement(CURRENCY_DROPDOWN).click();
        driver.findElement(EURO).click();

        // Add MacBook to cart and verify success alert is displayed
        driver.findElement(MACBOOK_ADD_TO_CART_BTN).click();

        // Navigate to shopping cart and verify total price
        driver.findElement(SHOPPING_CART).click();
        Assert.assertTrue(driver.findElement(TOTAL_PRICE).getText().contains("472.33€"), "Results should be equal!");
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        };
    }
}
