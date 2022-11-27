package com.softserve.edu.homework07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;

public class LoginTest {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    static WebDriver driver;

    @BeforeSuite
    public static void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        if (driver != null){
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod(){
        //Navigate to website
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if (!result.isSuccess()){
            String testName = result.getName();
            System.out.println("Failed" +testName);
        }
    }

    @Test
    public static void checkLogin() {
        //Verify that Login option is not visible
        System.out.println("1. isvisible Login Link =" + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());

        // Open Login form
        driver.findElement(By.cssSelector("a[title='My Account']")).click();

        //Verify that Login option is visible
        System.out.println("1. isvisible Login Link =" + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());
        //driver.findElement(By.xpath("//a[text()='Login']")).click();
        driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).click();

        // Enter E-Mail
        driver.findElement(By.id("input-email")).clear();
        WebElement login = driver.findElement(By.id("input-email"));
        login.sendKeys("hahaha@gmail.com");
        Assert.assertTrue(login.getAttribute("value").contains("hahaha"));

        // Enter Password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("qwerty");

        //Click Login button
        driver.findElement(By.xpath("//*[@class='btn btn-primary' and @value='Login']")).click();

        //Verify that Login option is not present in dropdown
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        System.out.println("1. isvisible Login Link =" + driver.findElements(By.cssSelector("div#top-links a[href*='route=account/login']")));
        Assert.assertEquals(driver.findElements(By.cssSelector("div#top-links a[href*='route=account/login']")),new ArrayList<>());
        Assert.assertTrue(driver.findElement(By.cssSelector("div#top-links a[href*='route=account/logout']")).isDisplayed());
    }
}
