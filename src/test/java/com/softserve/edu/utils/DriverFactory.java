package com.softserve.edu.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    //This method returns a WebDriver object
    public static WebDriver open(String browserType){
        if (browserType.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\mlink\\IdeaProjects\\selenium-java-4.7.0\\selenium-chromium-driver-4.7.0.jar");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");

            return new ChromeDriver(options);

        }
        else {System.setProperty("webdriver.edge.driver",
                "C:\\Users\\mlink\\IdeaProjects\\selenium-java-4.7.0\\selenium-edge-driver-4.7.0.jar");
            return  new EdgeDriver();

        }
    }
}
