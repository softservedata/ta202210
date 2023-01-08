package com.softserve.edu.hw15.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAccountPage extends AccountSidebarLoggedPart {

    private WebElement firstNameField;
    private WebElement lastNameField;
    private WebElement emailNameField;
    private WebElement phoneNameField;
    private WebElement faxNameField;
    private WebElement continueButton;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init elements
        firstNameField = driver.findElement(By.name("firstname"));
        continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        lastNameField = driver.findElement(By.name("lastname"));
        emailNameField = driver.findElement(By.name("email"));
        phoneNameField = driver.findElement(By.name("telephone"));
        faxNameField = driver.findElement(By.name("fax"));
    }

    // Page Object

    // firstNameField
    public WebElement getFirstNameField() {
        return firstNameField;
    }
    public WebElement getLastNameField() {
        return lastNameField;
    }
    public WebElement getEmailNameField() {
        return emailNameField;
    }
    public WebElement getPhoneNameField() {
        return phoneNameField;
    }
    public WebElement getFaxNameField() {
        return faxNameField;
    }

    public String getFirstNameFieldText() {
        return getFirstNameField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearFirstNameField() {
        getFirstNameField().clear();
    }

    public void clickFirstNameField() {
        getFirstNameField().click();
    }

    public void setFirstNameField(String text) {
        getFirstNameField().sendKeys(text);
    }

    public String getLastNameFieldText() {return getLastNameField().getAttribute(TAG_ATTRIBUTE_VALUE); }
    public void clearLastNameFieldText() { getLastNameField().clear();}
    public void clickLastNameFieldText() {getLastNameField().click();}
    public void setLastNameFieldText() {getLastNameField().sendKeys();}

    public String getEmailNameFieldText() {return getEmailNameField().getAttribute(TAG_ATTRIBUTE_VALUE); }
    public void clearEmailNameField() { getEmailNameField().clear();}
    public void clickEmailNameField() {getEmailNameField().click();}
    public void setEmailNameField() {getEmailNameField().sendKeys();}

    public String getPhoneNameFieldText() {return getPhoneNameField().getAttribute(TAG_ATTRIBUTE_VALUE); }
    public void clearPhoneNameField() { getPhoneNameField().clear();}
    public void clickPhoneNameField() {getPhoneNameField().click();}
    public void setPhoneNameField() {getPhoneNameField().sendKeys();}

    public String getFaxNameFieldText() {return getFaxNameField().getAttribute(TAG_ATTRIBUTE_VALUE); }
    public void clearFaxNameField() { getFaxNameField().clear();}
    public void clickFaxNameField() {getFaxNameField().click();}
    public void setFaxNameField() {getFaxNameField().sendKeys();}


    // continueButton
    public WebElement getContinueButton() {
        return continueButton;
    }

    public String getContinueButtonText() {
        return getContinueButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    // Functional

    // Business Logic

    public MyAccountPage gotoContinue() {
        clickContinueButton();
        return new MyAccountPage(driver);
    }

}
