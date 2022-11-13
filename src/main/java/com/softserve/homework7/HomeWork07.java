package com.softserve.homework7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class HomeWork07 {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;


    @Test
    public void checkMac() {
        WebDriverManager.chromedriver().setup();
        //
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        //
        driver.findElement(By.partialLinkText("My Account")).click();
        driver.findElement(By.partialLinkText("Login")).click();
        driver.findElement(By.name("email")).sendKeys("hahaha@gmail.com");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("qwerty");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("account/account"));
        //
        driver.quit();
    }
}
