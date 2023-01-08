package com.softserve.edu.hw14.tests;

import com.softserve.edu.hw14.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends TestRunnerFirst {

    @Test
    public void checkHome() {
        // Steps
        HomePage homePage = loadApplication();
        presentationSleep();
        //
        // Check
        Assert.assertEquals(homePage.getSlideshow0FirstImageAttributeAltText(), HomePage.EXPECTED_IPHONE_3);
        Assert.assertTrue(homePage.getProductComponentsContainer().getProductComponentByName(HomePage.EXPECTED_IPHONE_3).getPriceText().contains(HomePage.EXPECTED_IPHONE_3_PRICE));
        /*Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText().contains(HomePage.EXPECTED_IPHONE3));*/
    }

}
