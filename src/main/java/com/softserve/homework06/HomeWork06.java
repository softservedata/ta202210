package com.softserve.homework06;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
public class HomeWork06 {
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;

    @Test
    public void checkIPhoneXR() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/rkhod/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        //
        driver.get("http://taqc-opencart.epizy.com/");
        Thread.sleep(1000);
        driver.findElement(By.className("btn-group")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("EUR")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("search")).sendKeys("iPhone");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        Thread.sleep(1000);
        //
        WebElement phone = driver.findElement(By.partialLinkText("iPhone XR"));
        Assert.assertTrue(phone.getText().contains("iPhone XR"));
        WebElement price = driver.findElement(By.xpath("//a[text()='iPhone XR']/../following-sibling::p[@class='price']"));
        Assert.assertTrue(price.getText().contains("707.71€"));
        Thread.sleep(1000);
        driver.quit();
    }
}
