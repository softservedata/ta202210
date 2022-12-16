package com.softserve.edu.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAccountPage extends AccountSidebarLoggedPart {

    private WebElement firstNameField;
    private WebElement continueButton;
    private WebElement lastNameField;
    private WebElement emailField;
    private WebElement telephoneField;
    private WebElement faxField;
//    private WebElement success;


    public EditAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init elements
        firstNameField = driver.findElement(By.name("firstname"));
        continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        lastNameField = driver.findElement(By.name("lastname"));
        emailField = driver.findElement(By.name("email"));
        telephoneField = driver.findElement(By.name("telephone"));
        faxField = driver.findElement(By.name("fax"));
//        success = driver.findElement(By.xpath("//div[@class='alert alert-success']"));
    }

    // Page Object

    // firstNameField
    public WebElement getFirstNameField() {
        return firstNameField;
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

    // lastNameField
    public WebElement getLastNameField() {
        return lastNameField;
    }

    public String getLastNameFieldText() {
        return getLastNameField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearLastNameField() {
        getLastNameField().clear();
    }

    public void clickLastNameField() {
        getLastNameField().click();
    }

    public void setLastNameField(String text) {
        getLastNameField().sendKeys(text);
    }

    // emailField
    public WebElement getEmailField() {
        return emailField;
    }

    public String getEmailFieldText() {
        return getEmailField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearEmailField() {
        getEmailField().clear();
    }

    public void clickEmealField() {
        getEmailField().click();
    }

    public void setEmailField(String text) {
        getEmailField().sendKeys(text);
    }

    // telephoneField
    public WebElement getTelephoneField() {
        return telephoneField;
    }

    public String getTelephoneFieldText() {
        return getTelephoneField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearTelephoneFieldField() {
        getTelephoneField().clear();
    }

    public void clickTelephoneFieldField() {
        getTelephoneField().click();
    }

    public void setTelephoneFieldField(String text) {
        getTelephoneField().sendKeys(text);
    }

    // faxField
    public WebElement getFaxField() {
        return faxField;
    }

    public String getFaxFieldText() {
        return getFaxField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearFaxField() {
        getFaxField().clear();
    }

    public void clickFaxField() {
        getFaxField().click();
    }

    public void setFaxField(String text) {
        getFaxField().sendKeys(text);
    }

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

    // success message
//    public WebElement getSuccess() {return success;}

    // Functional

    // Business Logic

    public MyAccountPage gotoContinue() {
        clickContinueButton();
        return new MyAccountPage(driver);
    }

}
