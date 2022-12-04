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
        driver.get("http://taqc-opencart.epizy.com/");
    }

    @Test
    public void userLogin () {

        driver.findElement(By.xpath("//*[@id='form-currency']/div/button")).click();
        driver.findElement(By.xpath("//*[@id='form-currency']/div/ul/li[1]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='cart']/button")).click();

        WebElement MacBook = driver.findElement(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody/tr/td[2]/a"));
        Assert.assertEquals("MacBook", MacBook.getText());
        driver.close();

    }

}
