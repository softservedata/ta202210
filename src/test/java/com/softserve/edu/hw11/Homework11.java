package com.softserve.edu.hw11;

import com.softserve.edu.utils.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Homework11 {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    public static WebDriver driver;
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static String browserType = "Chrome";
    @BeforeClass
    public static void setupClass() {
        driver = DriverFactory.open(browserType);
    }

    @Before
    public void testStart(){


    }

    @Test
    public void userLogin () {

        driver.findElement(By.xpath("//span[text()='Currency']")).click();
        driver.findElement(By.xpath("//button[text()='$ US Dollar']")).click();
        driver.findElement(By.xpath("//div[@id='content']/div[2]/div[1]/div/div[3]/button[1]")).click();
        driver.findElement(By.xpath("//div[@id='content']/div[2]/div[2]/div/div[3]/button[1]")).click();
        driver.findElement(By.xpath("//span[@id='cart-total']")).click();

        WebElement iPhone = driver.findElement(By.xpath("//a[text()='iPhone 3']"));
        Assert.assertEquals("iPhone 3", iPhone.getText());
        WebElement MacBook = driver.findElement(By.xpath("//a[text()='MacBook']"));
        Assert.assertEquals("MacBook", MacBook.getText());
        driver.findElement(By.xpath("//a[@href='http://taqc-opencart.epizy.com/index.php?route=checkout/cart'")).click();
        driver.findElement(By.xpath("//div[@class='input-group btn-block']/input[@name='quantity[8799]']")).clear();
        driver.findElement(By.xpath("//div[@class='input-group btn-block']/input[@name='quantity[8799]']")).sendKeys("2");
        driver.findElement(By.xpath("//div[@class='input-group btn-block']/input[@name='quantity[8798]']")).clear();
        driver.findElement(By.xpath("//div[@class='input-group btn-block']/input[@name='quantity[8798]']")).sendKeys("3");
        driver.findElement(By.xpath("//input[@name='quantity[8799]']")).click();
        WebElement newPrice = driver.findElement(By.xpath("//tbody/tr[4]/td[2]"));
        Assert.assertEquals("$2,052.40", newPrice.getText());
        driver.close();

    }

}
