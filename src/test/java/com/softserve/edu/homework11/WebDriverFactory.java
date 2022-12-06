package com.softserve.edu.homework11;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.time.Duration;

public class WebDriverFactory {

    public WebDriver getWebDriver(DriverManagerType driverType) {
        WebDriver driver;
        switch (driverType) {
           case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions().setHeadless(true);
                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
//                WebDriverManager.firefoxdriver().setup();
                System.setProperty("webdriver.gecko.driver", "E:\\Idea\\ta202210\\seleniumdrivers\\geckodriver.exe");
                FirefoxOptions options1 = new FirefoxOptions().setHeadless(true);
                driver =
                        new FirefoxDriver(options1);
                break;
            case PHANTOMJS:
                WebDriverManager.phantomjs().setup();
                driver = new PhantomJSDriver();
                break;

            default:
                throw new RuntimeException("Unknown Web Driver type : " + driverType);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
}
