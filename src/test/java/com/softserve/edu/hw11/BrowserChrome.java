package com.softserve.edu.hw11;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserChrome {
    public static DriverService service;

    @Test
    public void checkChrome () {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("\tStart");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://taqc-opencart.epizy.com/");
        System.out.println("\tdriver.get http://taqc-opencart.epizy.com/ DONE");
        System.out.println("\tCurrent title is: " + driver.getTitle());
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        driver.findElement(By.name("EUR")).click();
        driver.findElement(By.cssSelector("div.button-group button[onclick*='43']")).click();
        System.out.println("\tMacbook added to cart DONE");
        driver.quit();
    }
}
