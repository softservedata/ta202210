package com.softserve.edu.hw6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework06 {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";

    @Test
    public void iPhoneSearchTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        driver.findElement(By.name("EUR")).click();
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("iPhone XR", Keys.ENTER);
        WebElement price = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/p[2]"));
        Assert.assertTrue(price.getText().contains("707.71€"));
        System.out.println("iPhone XR: " + price.getText());

    }
}
