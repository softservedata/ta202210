package com.softserve.hw07;
//1) Зайти на сайт http://taqc-opencart.epizy.com/
//        Залогуватися. E-Mail Address: hahaha@gmail.com  та Password: qwerty
//        Клікнути на кнопку Login.
//        Перевірити, чи ми дійсно залогувалися.

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

public class LoginTest {
    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
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
            System.out.println("***TC error, name = " + testName + " ERROR");
        }
    }

    public void checkWebElements() throws Exception {

        driver.findElement(By.cssSelector("a[title='My Account']")).click();

        driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).click();

        WebElement login = driver.findElement(By.id("input-email"));
        login.sendKeys("hahaha@gmail.com");
        Assert.assertTrue(login.getAttribute("value").contains("hahaha"));
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys("qwerty");
    }

    @Test
    public void checkExistWebElement() throws Exception {
        System.out.println("1. isVisible Login Link = "
                + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());

        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        System.out.println("2. isVisible Login Link = "
                + driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).isDisplayed());

        driver.findElement(By.cssSelector("div#top-links a[href*='route=account/login']")).click();

        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("hahaha@gmail.com");

        String content = driver.findElement(By.id("input-email")).getAttribute("value");
        System.out.println("***content email = " + content);

        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys("password");
        content = driver.findElement(By.id("input-password")).getAttribute("value");
        System.out.println("content password = " + content);
}
}
