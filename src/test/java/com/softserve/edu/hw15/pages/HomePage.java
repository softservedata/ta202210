package com.softserve.edu.hw15.pages;

//import com.softserve.edu.opencart.data.Product;

import com.softserve.edu.hw15.data.Currencies;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends TopPart {
    //
    public static final String EXPECTED_IPHONE3 = "iPhone3";
    public static final String EXPECTED_IPHONE_3 = "iPhone 3";

    public static final String EXPECTED_IPHONE_3_PRICE = "$123.20";
    //
    private WebElement slideshow0;
    //
    private ProductsContainer productsContainer;

    public HomePage(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        // init elements
        slideshow0 = driver.findElement(By.id("slideshow0"));
        //
        productsContainer = new ProductsContainer(driver);
        //productsContainer = new ProductsContainer();
    }

    // Page Object

    // slideshow0
    public WebElement getSlideshow0() {
        return slideshow0;
    }

    public WebElement getSlideshow0FirstImage() {
        // return getSlideshow0().findElement(By.cssSelector("a > img"));
        return getSlideshow0().findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div/div[1]/a/img"));
        // return Slideshow0.findElement(By.xpath("//a/img")); // ERROR
        // return driver.findElement(By.xpath("//div[@id='slideshow0']//a/img"));
    }

    public String getSlideshow0FirstImageAttributeText(String attribute) {
        return getSlideshow0FirstImage().getAttribute(attribute).trim();
    }

    public String getSlideshow0FirstImageAttributeSrcText() {
        return getSlideshow0FirstImageAttributeText(TAG_ATTRIBUTE_SRC);
    }
    public String getSlideshow0FirstImageAttributeAltText() {
        return getSlideshow0FirstImageAttributeText(TAG_ATTRIBUTE_ALT);
    }

    public String getTextFromProduct(){
        return getSlideshow0FirstImageAttributeText(TAG_ATTRIBUTE_VALUE);
    }

    // productComponentsContainer
    public ProductsContainer getProductComponentsContainer() {
        return productsContainer;
    }

    // Functional

    // Business Logic

    //public HomePage chooseCurrency(String currency) {
    public HomePage chooseCurrency(Currencies currency) {
        //logger.debug("start chooseCurrency() with currency = " + currency.toString());
        clickCurrencyByPartialName(currency);
        //logger.debug("end chooseCurrency() with currency = " + currency.toString());
        return new HomePage(driver);
        //return new HomePage();
    }

//    public HomePage scrollToProduct(Product product) {
//        WebElement webElement = getProductComponentsContainer()
//                .getProductComponentByName(product.getName())
//                .getName();
//        scrollToElement(webElement);
//        //return new HomePage(driver);
//        //return new HomePage();
//        return this;
//    }
}
