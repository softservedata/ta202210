package com.softserve.homework12;

import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.tests.TestRunnerFirst;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends TestRunnerFirst {

    @Test
    public void checkHome() {
        // Steps
        HomePage homePage = loadApplication();
        presentationSleep();

        // Check
        Assert.assertTrue(homePage.getProductComponentsContainer().getProductComponentNames().contains(HomePage.EXPECTED_IPHONE_3));
        Assert.assertTrue(homePage.getProductComponentsContainer().getProductComponentByName(HomePage.EXPECTED_IPHONE_3).getPriceText().contains(HomePage.EXPECTED_IPHONE_3_PRICE));
    }

}
