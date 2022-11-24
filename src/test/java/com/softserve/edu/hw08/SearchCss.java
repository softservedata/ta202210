package com.softserve.edu.hw08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class SearchCss {
    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private WebDriver driver;
    private final Long IMPLICITLY_WAIT_SECONDS = 20L;
    private final Long ONE_SECOND_DELAY = 2000L;

    private void presentationSleep() {
        presentationSleep(2);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        presentationSleep();
        if (driver != null) {
            driver.quit(); // close()
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
        presentationSleep();
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        presentationSleep();
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("***TC error, name = " + testName + " ERROR");
            presentationSleep();
        }
    }

    @Test
    public void findByCss() {
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        presentationSleep();
        driver.findElement(By.cssSelector("button[name='USD']")).click();
        presentationSleep();
        driver.findElement(By.cssSelector("div.button-group button[onclick*='43']")).click();
        presentationSleep();
        driver.findElement(By.cssSelector("div.button-group button[onclick*='40']")).click();
        presentationSleep();
        driver.findElement(By.cssSelector("#cart.btn-group.btn-block")).click();
        presentationSleep();
        Assert.assertTrue(driver.findElement(By.cssSelector(".text-left a[href*='id=40']")).getText().contains("iPhone 3"));
        presentationSleep();
        Assert.assertTrue(driver.findElement(By.cssSelector(".text-left a[href*='id=43']")).getText().contains("MacBook"));
        presentationSleep();
        Assert.assertTrue(driver.findElement(By.cssSelector("table.table-bordered tr:last-child td.text-right:last-child")).getText().contains("$725.20"));
        driver.quit();
    }
}
