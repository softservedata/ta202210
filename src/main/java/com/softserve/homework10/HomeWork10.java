package com.softserve.homework10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;

//    Homework 10 (from 24.11.2022)
//    Підготувати тестовий метод з наступним сценарієм.
//    1) Зайти на сайт https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/
//    У першій таблиці (пункт  Uncontrolled Mode) у фільтрі Сity внести букву L. Перевірити присутність у стовпчику міст Las Vegas та London.
//    Для коду використати пакет com.softserve.homework10
//    Запушати код на github у свою вітку.

public class HomeWork10 {


    private final String BASE_URL = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private WebDriver driver;


    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
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
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("TC error, name = " + testName );
        }
        driver.manage().deleteAllCookies();
    }

    @Test
    public void checkCity() throws Exception {

        // Accept cookie from temporary profile
        driver.findElement(By.xpath("//button[contains(@class,'cookie-module--button--3dBOi')]")).click();

        // Select iframe
        WebElement posiotion = driver.findElement(By.xpath("//h3[@id='uncontrolled-mode']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", posiotion);
        driver.switchTo().frame(driver.findElement(By.xpath("//h3[@id='uncontrolled-mode']/following-sibling::div[1]/.//iframe")));

        // Use correct input field
        List<WebElement> input = driver.findElements(By.xpath("//input"));
        input.get(2).click();
        input.get(2).sendKeys("L");

        // Check result
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='London']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Las Vegas']")).isDisplayed());

    }

}
