package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.pages.EditAccountPage;
import com.softserve.edu.opencart.tests.TestRunnerFirst;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditPersonalData extends TestRunnerFirst {
    @DataProvider
    public Object[][] dataSuccessful() {
        return new Object[][]{
                {"nastenakv3@gmail.com", "qwerty", "Ana"},
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

        login.clickLastNameField();
        login.clearLastNameField();
        login.setLastNameField("Dalik1");
        login.clickEmealField();
        login.clearEmailField();
        login.setEmailField("nastenakv3+1@gmail.com");
        login.clickTelephoneFieldField();
        login.clearTelephoneFieldField();
        login.setTelephoneFieldField("0966105699");
        login.clickFaxField();
        login.clearFaxField();
        login.setFaxField("123456789");
        login.clickContinueButton();
    }
}