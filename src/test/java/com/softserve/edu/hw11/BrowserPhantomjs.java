package com.softserve.edu.hw11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BrowserPhantomjs {

    @Test
    public void checkPhantomjs () {
        WebDriverManager.phantomjs().setup();
        WebDriver driver = new PhantomJSDriver();
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
