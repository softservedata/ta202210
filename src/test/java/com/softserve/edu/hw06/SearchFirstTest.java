package com.softserve.edu.hw06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SearchFirstTest {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";


    @Test
    public void checkIphone() {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);

        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();

        driver.findElement(By.name("EUR")).click();

        driver.findElement(By.name("search")).click();

        driver.findElement(By.name("search")).clear();

        driver.findElement(By.name("search")).sendKeys("iPhone", Keys.ENTER);

        WebElement price = driver.findElement(By.xpath("//a[text()='iPhone XR']/../following-sibling::p[@class='price']"));

        Assert.assertTrue(price.getText().contains("707.71€"));

        System.out.println("***contains: " + price.getText());

        driver.quit();
    }
}