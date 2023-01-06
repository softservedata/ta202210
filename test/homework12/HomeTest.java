package homework12;

import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.ProductComponent;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.tests.TestRunnerFirst;

public class HomeTest extends TestRunnerFirst {

    @Test
    public void checkHome() {
        // Steps
        HomePage homePage = loadApplication();
        presentationSleep();
        //
        // Check
        ProductComponent iphone3 = homePage.getProductComponentsContainer().getProductComponentByName("iPhone 3");
        String iphone3Price = iphone3.getPriceText();
        Assert.assertEquals(iphone3Price,"$123.20");
        Assert.assertEquals(homePage.getSlideshow0FirstImageAttributeAltText(), HomePage.EXPECTED_IPHONE_6);
        Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText().contains(HomePage.EXPECTED_IPHONE6));
    }

}
