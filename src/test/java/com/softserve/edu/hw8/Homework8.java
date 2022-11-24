package com.softserve.edu.hw8;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Homework8 {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    public static WebDriver driver;
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;

    @BeforeClass
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void testStart() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
    }

    @Test
    public void userLogin() {

        driver.findElement(By.cssSelector("form[id='form-currency'")).click();
        driver.findElement(By.cssSelector("button[name='USD'")).click();
        driver.findElement(By.cssSelector("button[onclick*='43']")).click();
        driver.findElement(By.cssSelector("button[onclick*='40']")).click();
        driver.findElement(By.cssSelector("div[id='cart']")).click();

        WebElement iPhone = driver.findElement(By.cssSelector("#cart > ul > li:nth-child(1) > table > tbody > tr:nth-child(1) > td.text-left > a"));
        Assert.assertEquals("iPhone 3", iPhone.getText());
        WebElement MacBook = driver.findElement(By.cssSelector("#cart > ul > li:nth-child(1) > table > tbody > tr:nth-child(2) > td.text-left > a"));
        Assert.assertEquals("MacBook", MacBook.getText());
        WebElement item1Price = driver.findElement(By.cssSelector("#cart > ul > li:nth-child(1) > table > tbody > tr:nth-child(1) > td:nth-child(4)"));
        Assert.assertEquals("$123.20", item1Price.getText());
        WebElement item2Price = driver.findElement(By.cssSelector("#cart > ul > li:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(4)"));
        Assert.assertEquals("$602.00", item2Price.getText());
        driver.close();

    }

}
