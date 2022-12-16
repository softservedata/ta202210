package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends AccountSidebarLoggedPart {
    private WebElement success;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        success = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
    }

    // Page Object
        public WebElement getSuccess() {return success;}

    // Functional

    // Business Logic

}
