package com.softserve.edu.homework12and13.tests;

//import com.softserve.edu.opencart.data.IUser;
//import com.softserve.edu.opencart.data.UserRepository;

import com.softserve.edu.homework12and13.pages.EditAccountPage;
import com.softserve.edu.homework12and13.pages.HomePage;
import com.softserve.edu.homework12and13.pages.UnsuccessfulLoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//public class LoginTest extends TestRunnerFirst {
public class LoginTest extends TestRunnerFirst {

    // /*
    @DataProvider//(parallel = true)
    public Object[][] dataSuccessful() {
        return new Object[][] {
            { "xdknxusqvjeovowpfk@awdrt.com", "awdrt123", "my" },
            { "hahaha@gmail.com", "qwerty", "hahaha" },
        };
    }
    // */

    /*
    @DataProvider(parallel = true)
    public Object[][] dataSuccessful() {
        return new Object[][] {
                { UserRepository.getHahaha() },
                { UserRepository.getAwdrt() },
        };
    }
    */

    @Test(dataProvider = "dataSuccessful")
    public void checkSuccessful(String email, String password, String firstName) {
    //public void checkSuccessful(IUser validUser) {
        //
        // Steps
        EditAccountPage editAccountPage = loadApplication()
                .gotoLoginPage()
                .successfulLogin(email, password)
                //.successfulLogin(validUser)
                .gotoEditAccountRight();
        presentationSleep();
        //
        // Check
        Assert.assertEquals(editAccountPage.getFirstNameFieldText(), firstName);
        //Assert.assertEquals(editAccountPage.getFirstNameFieldText(), validUser.getFirstname());
        //
        // Return to Previous State
        HomePage homePage = editAccountPage
                .gotoContinue() // optional
                .gotoLogoutRight()
                .gotoContinue();
        //
        // Check
        Assert.assertTrue(homePage
                .getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.EXPECTED_IPHONE6));
        presentationSleep();
    }

    // /*
    @DataProvider//(parallel = true)
    public Object[][] dataUnsuccessful() {
        return new Object[][] {
                { "xdknx@awdrt.com", "aw123" },
                { "hah@gmail.com", "qwy" },
        };
    }
    // */

    /*
    @DataProvider//(parallel = true)
    public Object[][] dataUnsuccessful() {
        return new Object[][] {
                { UserRepository.getInvalidUser() },
        };
    }
    */

    //@Test(dataProvider = "dataUnsuccessful")
    public void checkUnsuccessful(String emailInvalid, String passwordInvalid) {
    //public void checkUnsuccessful(IUser invalidUser) {
        //
        // Steps
        UnsuccessfulLoginPage unsuccessfulLoginPage = loadApplication()
                .gotoLoginPage()
                .unsuccessfulLoginPage(emailInvalid, passwordInvalid);
                //.unsuccessfulLoginPage(invalidUser);
        presentationSleep();
        //
        // Check
        Assert.assertTrue(unsuccessfulLoginPage.getAlertWarningText()
                .contains(UnsuccessfulLoginPage.EXPECTED_LOGIN_MESSAGE));
        //
        // Return to Previous State
        HomePage homePage = unsuccessfulLoginPage
                .gotoHomePage();
        //
        // Check
        Assert.assertTrue(homePage
                .getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.EXPECTED_IPHONE6));
        presentationSleep();
    }

    //@Test
    public void checkHome() {
        // Steps
        HomePage homePage = loadApplication();
        presentationSleep();
        //
        // Check
        Assert.assertEquals(homePage.getSlideshow0FirstImageAttributeAltText(), HomePage.EXPECTED_IPHONE_6);
        Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText().contains(HomePage.EXPECTED_IPHONE6));
    }

    @Test
    public void editAccountInfo() {

        EditAccountPage editAccountPage = loadApplication()
                .gotoLoginPage()
                .successfulLogin("haha@gmail.com", "qwerty")
                .gotoEditAccountRight();

        editAccountPage.clearLastNameField();
        editAccountPage.clickLastNameField();
        editAccountPage.setLastNameField("Smith");

        editAccountPage.getEmailField();
        editAccountPage.clearEmailField();
        editAccountPage.setEmailField("blabla@mail.com");

        editAccountPage.clearTelephoneField();
        editAccountPage.clearTelephoneField();
        editAccountPage.setTelephoneField("0123456789");

        editAccountPage.clearFaxField();
        editAccountPage.clickFaxField();
        editAccountPage.setFaxField("fax 09876554");

        editAccountPage.getEmailField();
        editAccountPage.clearEmailField();
        editAccountPage.setEmailField("haha@gmail.com");

        editAccountPage.gotoContinue();

    }
}
