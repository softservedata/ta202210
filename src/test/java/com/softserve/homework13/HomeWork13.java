package com.softserve.homework13;

import com.softserve.edu.opencart.pages.EditAccountPage;
import com.softserve.edu.opencart.tests.TestRunnerFirst;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomeWork13 extends TestRunnerFirst {

//    Homework 13 (from 08.12.2022)
//    Підготувати тестовий метод з наступним сценарієм.
//    1) Використати PageObject класи підготовлені на занятті (вітка pageobj).
//    Апдейтнути клас EditAccountPage, додати web елементи для зміни Last Name, E-Mail, Telephone, Fax.
//    Підготувати тестовий метод для зміни персональних даних.
//    Для коду використати пакет com.softserve.homework13
//    Запушати код на github у свою вітку.

    @DataProvider
    public Object[][] dataSuccessful() {
        return new Object[][] {

                { "r.khodorovych@gmail.com", "qwerty", "haha" },
        };
    }

    @Test(dataProvider = "dataSuccessful")
    public void checkEditAccount(String email, String password, String firstName) {
        // Login
        EditAccountPage login = loadApplication()
                .gotoLoginPage()
                .successfulLogin(email, password)
                .gotoEditAccountRight();
        presentationSleep();

        // Edit Account
        login.clickLastNameField();
        login.clearLastNameField();
        login.setLastNameField("Roman");
        login.clickEmealField();
        login.clearEmailField();
        login.setEmailField("r.khodorovych@gmail.com");
        login.clickTelephoneFieldField();
        login.clearTelephoneFieldField();
        login.setTelephoneFieldField("100500");
        login.clickFaxField();
        login.clearFaxField();
        login.setFaxField("No fax exist");
        login.clickContinueButton();

        // Check
        Assert.assertTrue(login.gotoContinue().getSuccess().isDisplayed());


        }

}
