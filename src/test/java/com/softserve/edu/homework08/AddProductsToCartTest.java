package com.softserve.edu.homework08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddProductsToCartTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final By CURRENCY_DROPDOWN = By.cssSelector(".btn.btn-link.dropdown-toggle");
    private static final By USD = By.cssSelector("[name='USD']");
    private static final By MACBOOK_ADD_TO_CART_BTN = By.cssSelector("div.product-layout button[onclick*='43'] > i.fa.fa-shopping-cart");
    private static final By IPHONE_ADD_TO_CART_BTN = By.cssSelector("div.product-layout button[onclick*='40'] > i.fa.fa-shopping-cart");
    private static final By ALERT_SUCCESS = By.cssSelector("div.alert.alert-success");
    private static final By SHOPPING_CART = By.cssSelector("ul.list-inline li > a[href*='checkout/cart']");
    private static final By TOTAL_PRICE = By.cssSelector("#content div.row tr:last-child td:last-child");

    @BeforeClass
    public void setup() {
        // Setup driver and open URL
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void testVerifyLoginForm() {
        // Change currency to USD
        driver.findElement(CURRENCY_DROPDOWN).click();
        driver.findElement(USD).click();

        // Add MacBook to cart and verify success alert is displayed
        driver.findElement(MACBOOK_ADD_TO_CART_BTN).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(ALERT_SUCCESS)).perform();
        Assert.assertTrue(driver.findElement(ALERT_SUCCESS).isDisplayed(), "Success alert should be displayed!");

        // Add iPhone 3 to cart and verify success alert is displayed
        actions.scrollToElement(driver.findElement(IPHONE_ADD_TO_CART_BTN)).perform();
        driver.findElement(IPHONE_ADD_TO_CART_BTN).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15L));
        actions.scrollToElement(driver.findElement(ALERT_SUCCESS)).perform();
        Assert.assertTrue(driver.findElement(ALERT_SUCCESS).isDisplayed(), "Success alert should be displayed!");

        // Navigate to shopping cart and verify total price
        driver.findElement(SHOPPING_CART).click();
        Assert.assertTrue(driver.findElement(TOTAL_PRICE).getText().contains("$725.20"), "Results should be equal!");
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
