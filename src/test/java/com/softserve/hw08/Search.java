package com.softserve.hw08;

//Всі web елементи шукати по CSS.
//        1) Зайти на сайт http://taqc-opencart.epizy.com/
//        Вибрати валюту USD.
//        Додати до корзини MacBook та iPhone 3. Перевірити, чи дійсно операції успішні.
//        Перевірити очікувану ціну.

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;


public class Search {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private WebDriver driver;


    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
        WebElement usd = driver.findElement(By.cssSelector("button[name='USD']"));
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("button[name='USD']")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }


    @Test
        public void findCheck() {
            driver.findElement(By.cssSelector("div#search > input")).click();
            driver.findElement(By.cssSelector("#search > input")).clear();
            driver.findElement(By.cssSelector("#search > input")).sendKeys("mac");
            driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
            driver.findElement(By.cssSelector("div.product-layout button[onclick*='43'] > i.fa.fa-shopping-cart")).click();


            driver.findElement(By.cssSelector("div#search > input")).click();
            driver.findElement(By.cssSelector("#search > input")).clear();
            driver.findElement(By.cssSelector("#search > input")).sendKeys("iPhone 3");
            driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
            driver.findElement(By.cssSelector("div.product-layout button[onclick*='40'] > i.fa.fa-shopping-cart")).click();

            driver.findElement(By.cssSelector("ul.list-inline li > a[href*='checkout/cart']")).click();
            Assert.assertTrue(driver.findElement(By.cssSelector("#content div.col-sm-4.col-sm-offset-8 tr:last-child td:last-child")).getText().contains("$725.20"), "Equals");
    }

    }




