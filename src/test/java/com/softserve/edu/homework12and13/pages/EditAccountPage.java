package com.softserve.edu.homework12and13.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditAccountPage extends AccountSidebarLoggedPart {

    private WebElement firstNameField;
    private WebElement continueButton;
    private WebElement LAST_NAME_FIELD;
    private WebElement EMAIL_FIELD;
    private WebElement TELEPHONE_FIELD;
    private WebElement FAX_FIELD;

    public EditAccountPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init elements
        firstNameField = driver.findElement(By.name("firstname"));
        continueButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        LAST_NAME_FIELD = driver.findElement(By.id("input-lastname"));
        EMAIL_FIELD = driver.findElement(By.name("email"));
        TELEPHONE_FIELD = driver.findElement(By.name("telephone"));
        FAX_FIELD = driver.findElement(By.name("fax"));
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

    public WebElement getLastNameField() {
        return LAST_NAME_FIELD;
    }

    public void clearLastNameField() {
        getLastNameField().clear();
    }

    public void clickLastNameField() {
        getFirstNameField().click();
    }

    public void setLastNameField(String text) {
        getLastNameField().sendKeys(text);
    }

    public WebElement getEmailField() {
        return EMAIL_FIELD;
    }

    public void clearEmailField() {
        getEmailField().clear();
    }

    public void clickEmailField() {
        getEmailField().click();
    }

    public void setEmailField(String text) {
        getEmailField().sendKeys(text);
    }

    public WebElement getTelephoneField() {
        return TELEPHONE_FIELD;
    }

    public void clearTelephoneField() {
        getTelephoneField().clear();
    }

    public void clickTelephoneField() {
        getTelephoneField().click();
    }

    public void setTelephoneField(String text) {
        getTelephoneField().sendKeys(text);
    }

    public WebElement getFaxField() {
        return FAX_FIELD;
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
}
