package com.softserve.homework11;

//        Homework 11 (from 01.12.2022)
//        Тестовий метод виконати використовуючи два браузери, а саме у headless mode під firefox або chrome, а також під PhantomJS.
//        Браузер передавати через dataprovider.
//        1) Зайти на сайт http://taqc-opencart.epizy.com/
//        Вибрати валюту Euro.
//        Додати до корзини MacBook. Перевірити результат.

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class HomeWork11 {

    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private WebDriver driver;
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private void takeScreenShot(String testname) throws IOException {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./" + currentTime + "_" + testname + "_screenshot.png"));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws Exception {
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("Test case = " + testName + " ERROR");
            takeScreenShot(testName);
        }
    }
    @DataProvider(name = "browsers")
    public Object[][] getBrowser(){
        return new Object[][]{
                {"chrome.h"},{"phantomJS"}
        };
    }

    @Test(dataProvider = "browsers")
    public void crossBrowser(String browser) throws Exception {

        // pick-up browser
        if (browser.equals("chrome.h")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions().addArguments("-headless");
            driver = new ChromeDriver(options);
            driver.get(BASE_URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
            driver.manage().window().setSize(new Dimension(1440, 900));
        } else if (browser.equals("phantomJS")) {
            WebDriverManager.phantomjs().setup();
            driver = new PhantomJSDriver();
            driver.get(BASE_URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
            driver.manage().window().maximize();
        }

        // Choose currency
        driver.findElement(By.xpath("//span[text()='Currency']")).click();
        driver.findElement(By.xpath("//button[text()='€ Euro']")).click();

        // Add a MacBook to the card
        driver.findElement(By.xpath("//a[text()='MacBook']/../../..//span[text()='Add to Cart']")).click();

        // Check result
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success']/././a[text()='MacBook']")).isDisplayed());


    }
}
