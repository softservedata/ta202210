package com.softserve.edu.homework07;

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

public class VerifyLoginTest {

    private WebDriver driver;
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final By MY_ACCOUNT = By.xpath("//li[@class='dropdown']/a[@title]");
    private static final By LOGIN_LINK = By.cssSelector(".dropdown-menu-right");
    private static final By EMAIL_INPUT_FIELD = By.name("email");
    private static final By PASSWORD_INPUT_FIELD = By.name("password");
    private static final By LOGIN_BTN = By.xpath("//input[@value='Login']");
    private static final By LOGOUT_LINK = By.xpath("//div/a[contains(., 'Logout')]");
    private static final By LOGOUT_PROOF = By.xpath("//div[@id='content']/p");
    private List<String> expectedList = Arrays.asList("You have been logged off", "Your shopping cart has been saved");

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
        // Navigate to My Account > Login
        driver.findElement(MY_ACCOUNT).click();
        driver.findElement(LOGIN_LINK).click();

        // Do login with specified credentials
        driver.findElement(EMAIL_INPUT_FIELD).clear();
        driver.findElement(EMAIL_INPUT_FIELD).click();
        driver.findElement(EMAIL_INPUT_FIELD).sendKeys("hahaha@gmail.com");
        driver.findElement(PASSWORD_INPUT_FIELD).clear();
        driver.findElement(PASSWORD_INPUT_FIELD).click();
        driver.findElement(PASSWORD_INPUT_FIELD).sendKeys("qwerty");
        driver.findElement(LOGIN_BTN).click();

        // Verify that login was successful
        Assert.assertTrue(driver.findElement(LOGOUT_LINK).isDisplayed(), "Logout was not found on the page");

        // Logout
        driver.findElement(LOGOUT_LINK).click();

        List<WebElement> elements = driver.findElements(LOGOUT_PROOF);
        List<String> actualResults = new ArrayList<>();
        for (WebElement element : elements) {
            actualResults.add(element.getText());
        }
        int j = 0;
        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertTrue(actualResults.get(j).contains(expectedList.get(i)),
                    "Expected text [" + expectedList.get(i) + "] wasn`t found in actual content " + actualResults);
            j++;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
