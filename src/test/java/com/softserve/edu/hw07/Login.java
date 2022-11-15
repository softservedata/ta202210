package com.softserve.edu.hw07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class Login {
    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit(); // close()
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("***TC error, name = " + testName + " ERROR");
        }
    }

    @Test
    public void checkWebElements() throws Exception {
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).click();
        WebElement login = driver.findElement(By.id("input-email"));
        login.sendKeys("hahaha@gmail.com");
        Assert.assertTrue(login.getAttribute("value").contains("hahaha"));
        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("qwerty");
        Assert.assertTrue(password.getAttribute("value").contains("qwerty"));
        driver.findElement(By.xpath("//div//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div//a[@class='list-group-item'][contains(text(),'Logout')]")).getText().contains("Logout"));
        driver.quit();
    }
}
