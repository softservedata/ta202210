package com.softserve.edu.homework09;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

public class CartQuantity {
    private static final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    static WebDriver driver;

    @BeforeSuite
    public static void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        if (driver != null){
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod(){
        //Navigate to website
        driver.get(BASE_URL);
        //Mazimize current window
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if (!result.isSuccess()){
            String testName = result.getName();
            System.out.println("Failed" +testName);
        }
    }
    @Test
    public static void addToCart() throws Exception {
        // Choose Currency
        driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
        driver.findElement(By.xpath("//button[@name='USD']")).click();

        //Search for MacBook
        driver.findElement(By.cssSelector("input[placeholder='Search']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("MacBook");

        // Click Search Button
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();

        //Get Macbook Air
        String macBookPriceRaw = driver.findElement(By.xpath("//a[text()='MacBook Air']/../following-sibling::p[@class='price']")).getText();
        String macBookPrice = "";
        macBookPrice = macBookPriceRaw.substring(0, macBookPriceRaw.indexOf('\n'));

        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/*/span[@class='hidden-xs hidden-sm hidden-md']")).click();

        String cartPriceButtonRaw = driver.findElement(By.xpath("//span[@id='cart-total']")).getText();
        String cartPriceButton = "";
        cartPriceButton = cartPriceButtonRaw.substring(cartPriceButtonRaw.indexOf('-')+2, cartPriceButtonRaw.length());

        BigDecimal cartPriceButtonDec = ConvertPriceToBigDecimal(cartPriceButton);
        BigDecimal macBookPriceDec = ConvertPriceToBigDecimal(macBookPrice);

        //Verify that correct price is displayed on the cart button
        System.out.println(cartPriceButtonDec);
        System.out.println(macBookPriceDec);
        Assert.assertEquals(cartPriceButtonDec, macBookPriceDec);


        //Search for iPhone 3
        driver.findElement(By.cssSelector("input[placeholder='Search']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("iPhone");

        // Click Search Button
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();

        //Get iPhone 3
        String iPhone3PriceRaw = driver.findElement(By.xpath("//a[text()='iPhone 3']/../following-sibling::p[@class='price']")).getText();
        String iPhone3Price = "";
        iPhone3Price = iPhone3PriceRaw.substring(0, iPhone3PriceRaw.indexOf('\n'));

        driver.findElement(By.xpath("//a[text()='iPhone 3']/../../following-sibling::div[@class='button-group']/*/span[@class='hidden-xs hidden-sm hidden-md']")).click();

        Thread.sleep(5000);
        cartPriceButtonRaw = driver.findElement(By.xpath("//span[@id='cart-total']")).getText();
        cartPriceButton = "";
        cartPriceButton = cartPriceButtonRaw.substring(cartPriceButtonRaw.indexOf('-')+2, cartPriceButtonRaw.length());

        cartPriceButtonDec = ConvertPriceToBigDecimal(cartPriceButton);
        BigDecimal iPhone3PriceDec = ConvertPriceToBigDecimal(iPhone3Price);

        //Verify that correct price is displayed on the cart button
        System.out.println(cartPriceButtonDec);
        System.out.println(iPhone3PriceDec);
        Assert.assertEquals(cartPriceButtonDec, iPhone3PriceDec.add(macBookPriceDec));

        //Go to cart
        driver.findElement(By.xpath("//span[@id='cart-total']")).click();

        //Find MacBook Air and iPhone 3 in the cart popup
        String macBookCartPopupLabel = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='MacBook Air']")).getText();
        String macBookCartPopupCount = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='MacBook Air']/../following-sibling::td[@class='text-right'][1]")).getText();
        String macBookCartPopupPrice = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='MacBook Air']/../following-sibling::td[@class='text-right'][2]")).getText();

        String iPhone3CartPopupLabel = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='iPhone 3']")).getText();
        String iPhone3CartPopupCount = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='iPhone 3']/../following-sibling::td[@class='text-right'][1]")).getText();
        String iPhone3CartPopupPrice = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='iPhone 3']/../following-sibling::td[@class='text-right'][2]")).getText();

        String cartPopupTotal = driver.findElement(By.xpath("//table[@class='table table-bordered']//strong[text()='Total']/../following-sibling::td")).getText();

        BigDecimal macBookCartPopupPriceDec = ConvertPriceToBigDecimal(macBookCartPopupPrice);
        BigDecimal iPhone3CartPopupPriceDec = ConvertPriceToBigDecimal(iPhone3CartPopupPrice);
        BigDecimal cartPopupTotalDec = ConvertPriceToBigDecimal(cartPopupTotal);

        //Verify that MacBook Air and iPhone 3 are added to the cart
        Assert.assertEquals(macBookCartPopupLabel,"MacBook Air");
        Assert.assertEquals(macBookCartPopupCount,"x 1");
        Assert.assertEquals(macBookCartPopupPriceDec,macBookPriceDec);

        Assert.assertEquals(iPhone3CartPopupLabel,"iPhone 3");
        Assert.assertEquals(iPhone3CartPopupCount,"x 1");
        Assert.assertEquals(iPhone3CartPopupPriceDec,iPhone3PriceDec);

        Assert.assertEquals(cartPopupTotalDec, iPhone3PriceDec.add(macBookPriceDec));
//Go to cart
        Thread.sleep(5000);
        driver.findElement(By.xpath("//p[@class='text-right']//strong")).click();
        Thread.sleep(5000);

        String macBookCartLabel = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']")).getText();
        String macBookCartCount = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']/../following-sibling::td[@class='text-left']//input")).getAttribute("value");
        String macBookCartPrice = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']/../following-sibling::td[@class='text-right'][2]")).getText();

        String iPhone3CartLabel = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']")).getText();
        String iPhone3CartCount = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']/../following-sibling::td[@class='text-left']//input")).getAttribute("value");
        String iPhone3CartPrice = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']/../following-sibling::td[@class='text-right'][2]")).getText();

        BigDecimal macBookCartPriceDec = ConvertPriceToBigDecimal(macBookCartPopupPrice);
        BigDecimal iPhone3CartPriceDec = ConvertPriceToBigDecimal(iPhone3CartPopupPrice);

        //Verify that MacBook Air and iPhone 3 are added to the cart
        Assert.assertEquals(macBookCartLabel,"MacBook Air");
        Assert.assertEquals(macBookCartCount,"1");
        Assert.assertEquals(macBookCartPriceDec,macBookPriceDec);

        Assert.assertEquals(iPhone3CartLabel,"iPhone 3");
        Assert.assertEquals(iPhone3CartCount,"1");
        Assert.assertEquals(iPhone3CartPriceDec,iPhone3PriceDec);

        //Change quantity macbook
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']/../following-sibling::td[@class='text-left']//input")).click();
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']/../following-sibling::td[@class='text-left']//input")).clear();
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']/../following-sibling::td[@class='text-left']//input")).sendKeys("3");
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']/../following-sibling::td[@class='text-left']//button[@type='submit']")).click();

        macBookCartPrice = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='MacBook Air']/../following-sibling::td[@class='text-right'][2]")).getText();
        macBookCartPriceDec = ConvertPriceToBigDecimal(macBookCartPrice);

        BigDecimal macBookQuantity = new BigDecimal("3");
        Assert.assertEquals(macBookCartPriceDec,macBookPriceDec.multiply(macBookQuantity));

        //Change quantity iPhone 3
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']/../following-sibling::td[@class='text-left']//input")).click();
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']/../following-sibling::td[@class='text-left']//input")).clear();
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']/../following-sibling::td[@class='text-left']//input")).sendKeys("2");
        driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']/../following-sibling::td[@class='text-left']//button[@type='submit']")).click();

        iPhone3CartPrice = driver.findElement(By.xpath("//div[@class='col-sm-12']//table[@class='table table-bordered']//a[text()='iPhone 3']/../following-sibling::td[@class='text-right'][2]")).getText();
        iPhone3CartPriceDec = ConvertPriceToBigDecimal(iPhone3CartPrice);

        BigDecimal  iPhone3Quantity = new BigDecimal("2");
        Assert.assertEquals(iPhone3CartPriceDec,iPhone3PriceDec.multiply(iPhone3Quantity));

    }

    private static BigDecimal ConvertPriceToBigDecimal(String price) {
        price = price.replace("$", "");
        price = price.replace(",", "");
        BigDecimal priceDecimal = new BigDecimal(price);
        return priceDecimal;
    }
}
