package com.softserve.hw06;
//1) Зайти на сайт http://taqc-opencart.epizy.com/
//        Вибрати валюту Euro.
//        Ввести у поле Search текст для пошуку "iPhone".
//        Клікнути на кнопку пошуку.
//        Перевірити, чи на сторінці присутній товар "iPhone XR" з ціною 707.71 Euro.

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class iPhoneTest {
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;

    @Test
    public void checkiPhone(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));

        driver.get("http://taqc-opencart.epizy.com/");
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        driver.findElement(By.name("EUR")).click();
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("iPhone");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        WebElement price = driver.findElement(By.xpath("//a[text()='iPhone XR']/../following-sibling::p[@class='price']"));
        Assert.assertTrue(price.getText().contains("707.71€"));
        System.out.println("***contains: " + price.getText());
        driver.quit();
    }
}
