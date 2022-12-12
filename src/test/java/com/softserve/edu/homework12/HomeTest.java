package com.softserve.edu.homework12;

import com.softserve.edu.homework12.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends TestRunnerFirst {

    @Test
    public void checkHome() {
        // Steps
        HomePage homePage = loadApplication();
//        presentationSleep();
        //
        // Check
        Assert.assertEquals(homePage.getSlideshow0FirstImageAttributeAltText(), HomePage.EXPECTED_IPHONE_6);
        Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText().contains(HomePage.EXPECTED_IPHONE6));
    }

    @Test
    public void verifyIPhoneOnPage() {
        HomePage homePage = loadApplication();
        Assert.assertTrue(homePage.getProductComponentsContainer()
                .getProductComponentByName(HomePage.EXPECTED_IPHONE_3)
                .getPriceText()
                .contains("$123.20"),
                HomePage.EXPECTED_IPHONE_3 + " doesn`t contain price [$123.20]");
    }
}
