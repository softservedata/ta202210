package com.softserve.edu.hw7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Homework7 {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final String LOGIN = "hahaha@gmail.com";
    private static final String PASSWORD = "qwerty";

    @Test
    public void userLogin (){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        driver.findElement(By.cssSelector("a[title='My Account'")).click();
        driver.findElement(By.cssSelector("a[href$='login'")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(LOGIN);
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();
        driver.close();

    }

}
