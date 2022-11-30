package com.softserve.homework09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

//Homework 9 (from 22.11.2022)
//        Всі web елементи шукати за допомогою XPath.
//        Підготувати тестовий метод з наступним сценарієм.
//        1) Зайти на сайт http://taqc-opencart.epizy.com/
//        Вибрати валюту USD.
//        Додати до корзини MacBook та iPhone 3. Перевірити, чи дійсно операції успішні.
//        Перейти у корзину. Змінити кількість iPhone 3 на 2, а MacBook на 3.
//        Перевірити очікувану ціну.
//        Для коду використати пакет com.softserve.homework09
//        Запушати код на github у свою вітку.

public class HomeWork09 {
    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;

    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
        } catch (InterruptedException e) {
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
    public void checkSummUSD() throws Exception {

        // Start > select currency
        driver.findElement(By.xpath("//form[@id='form-currency']")).click();
        driver.findElement(By.xpath("//button[@name='USD']")).click();

        // select items
        driver.findElement(By.xpath("//h4//a[text()='MacBook']//..//..//..//span[text()='Add to Cart']")).click();
        driver.findElement(By.xpath("//h4//a[text()='iPhone 3']//..//..//..//span[text()='Add to Cart']")).click();
        presentationSleep();

        // check if items are selected
        WebElement cart = driver.findElement(By.xpath("//span[@id='cart-total']"));
        Assert.assertTrue(cart.getText().contains("2 item(s)"));

        // change items quantity
        cart.click();
        driver.findElement(By.xpath("//p[@class='text-right']/./a[@href][1]")).click();
        driver.findElement(By.xpath("//a[text()='iPhone 3']/../..//input")).click();
        driver.findElement(By.xpath("//a[text()='iPhone 3']/../..//input")).clear();
        driver.findElement(By.xpath("//a[text()='iPhone 3']/../..//input")).sendKeys("2");
        driver.findElement(By.xpath("//a[text()='iPhone 3']/../..//button[@data-original-title='Update']")).click();

        driver.findElement(By.xpath("//a[text()='MacBook']/../..//input")).click();
        driver.findElement(By.xpath("//a[text()='MacBook']/../..//input")).clear();
        driver.findElement(By.xpath("//a[text()='MacBook']/../..//input")).sendKeys("3");
        driver.findElement(By.xpath("//a[text()='MacBook']/../..//button[@data-original-title='Update']")).click();

        // Check expected summ
        WebElement total = driver.findElement(By.xpath("//td[text()='$2,052.40']"));
        Assert.assertTrue(total.toString().contains("$2,052.40"));
        presentationSleep();

    }

}
