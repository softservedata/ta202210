package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.*;
import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.pages.ProductsContainer;
import com.softserve.edu.opencart.pages.ProductComponent;

public class ProductTest extends TestRunnerFirst {


    @DataProvider//(parallel = true)
    public Object[][] searchProducts() {
        return new Object[][]{
                { ProductRepository.get().getDefault() }
                //{Products.MACBOOK},
        };
    }

    @Test(dataProvider = "searchProducts")
    public void checkFirst(IProduct validProduct) {
        HomePage homePage = loadApplication()
                .chooseCurrency(Currencies.US_DOLLAR);
        //.scrollToProduct(searchProduct.getProduct());
        presentationSleep();
        //Assert.assertTrue(homePage.ProductComponentsContainer.contains(validProduct.getName();
        //Assert.assertTrue(homePage.getProductComponentsContainer().contains(validProduct.getName();
        //Assert.assertTrue(homePage.getProductComponentsContainer().contains("MacBook"));
        presentationSleep(4);
    }
}
