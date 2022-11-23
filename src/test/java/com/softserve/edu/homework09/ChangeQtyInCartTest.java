package com.softserve.edu.homework09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeQtyInCartTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final String CURRENCY_XPATH = "//button[@name='%s']";
    private static final String ADD_TO_CART_BTN_XPATH = "//h4/a[text()='%s']/../../following-sibling::div/button[text()]";
    private static final String ITEM_IN_CART_XPATH = "//table[contains(@class, 'table-bordered')]//a[text()='%s']";
    private static final String QTY_BAR_XPATH = ITEM_IN_CART_XPATH + "/../following-sibling::td//input";
    private static final String UPDATE_QTY_IN_CART_XPATH = ITEM_IN_CART_XPATH + "/../following-sibling::td//button[@type='submit']";
    private static final By CURRENCY_DROPDOWN = By.xpath("//button[contains(@class, 'btn btn-link dropdown-toggle')]");
    private static String itemName;
    private static String itemName2;
    private static final By CURRENCY = By.xpath(String.format(CURRENCY_XPATH, "USD"));
    private static final By SEARCH_BAR = By.xpath("//input[@name='search']");
    private static final By SEARCH_BUTTON = By.xpath("//span[@class='input-group-btn']/button");
    private static final By SEARCH_RESULTS_NAMES_LIST = By.xpath("//div[contains(@class, 'product-grid')]//h4/a");
    private static final By CART_BTN = By.xpath("//a[@title='Shopping Cart']");
    private static final By TOTAL_PRICE = By.xpath("//div[@class='row']//td/strong[text()='Total:']/../following-sibling::td");


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
        driver.findElement(CURRENCY).click();

        // Search items and add to cart
        searchItemAndAddToCart(Arrays.asList("macbook", "iphone 3"));

        //Navigate to the cart
        driver.findElement(CART_BTN).click();

        // Find item and change quantity
        findItemAndChangeQty(Arrays.asList(itemName, itemName2));

        // Verify expected total price after changes
        Assert.assertEquals(driver.findElement(TOTAL_PRICE).getText(), "$2,052.40",
                "Actual and expected result should be equal!");
    }

    private String getFirstSearchResultItem() {
        List<WebElement> elements = driver.findElements(SEARCH_RESULTS_NAMES_LIST);
        List<String> results = new ArrayList<>();
        for (WebElement element : elements) {
            results.add(element.getText());
        }
        return results.get(0);
    }

    private void searchItemAndAddToCart(List<String> itemsList) {
        for (String item : itemsList) {
            driver.findElement(SEARCH_BAR).clear();
            driver.findElement(SEARCH_BAR).click();
            driver.findElement(SEARCH_BAR).sendKeys(item);
            driver.findElement(SEARCH_BUTTON).click();

            // Add first search result item to the cart
            if (item.toLowerCase().contains("mac")) {
                itemName = getFirstSearchResultItem();
            } else {
                itemName2 = getFirstSearchResultItem();
            }
            String selectedItem = item.toLowerCase().contains("mac") ? itemName : itemName2;
            driver.findElement(By.xpath(String.format(ADD_TO_CART_BTN_XPATH, selectedItem))).click();
        }
    }

    private void findItemAndChangeQty(List<String> itemsList) {
        for (String itemName : itemsList) {
            Assert.assertTrue(driver.findElement(By.xpath(String.format(ITEM_IN_CART_XPATH, itemName))).isDisplayed());
            WebElement qtyBar = driver.findElement(By.xpath(String.format(QTY_BAR_XPATH, itemName)));
            qtyBar.click();
            qtyBar.clear();
            String itemsQty = itemName.toLowerCase().contains("mac") ? "3" : "2";
            qtyBar.sendKeys(itemsQty);
            driver.findElement(By.xpath(String.format(UPDATE_QTY_IN_CART_XPATH, itemName))).click();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
