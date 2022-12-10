package com.softserve.edu.homework08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Cart {
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
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        driver.findElement(By.cssSelector("button[name='USD']")).click();

        //Search for MacBook
        driver.findElement(By.cssSelector("input[placeholder='Search']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("MacBook", Keys.ENTER);

        //Get Macbook Air
        List<WebElement> macBooks = driver.findElements(By.cssSelector(".product-thumb"));
        System.out.println("***macBooks.size() = " + macBooks.size());
        if (macBooks.size() == 0) {
            // Develop Custom Exception
            throw new RuntimeException("***macBooks(s) not found");
        }
        WebElement macBookAir = null;
        for (WebElement macBook : macBooks) {
            if (macBook.findElement(By.cssSelector(".caption")).getText().contains("MacBook Air")) {
                macBookAir = macBook;
                break;
            }
        }
        if (macBookAir == null) {
            throw new RuntimeException("*** macBookAir not found");
        }

        //Store price
        String macBookPriceRaw = "";
        macBookPriceRaw = macBookAir.findElement(By.cssSelector(".price")).getText();
        System.out.println(macBookPriceRaw);
        String macBookPrice = "";
        macBookPrice = macBookPriceRaw.substring(0, macBookPriceRaw.indexOf('\n'));
        System.out.println(macBookPrice);

        //Add to cart
        macBookAir.findElement(By.cssSelector(".button-group > button > i.fa-shopping-cart")).click();
        Thread.sleep(5000);

        //Read cart price
        String cartPriceButtonRaw = "";
        cartPriceButtonRaw = driver.findElement(By.cssSelector("#cart-total")).getText();
        System.out.println(cartPriceButtonRaw);
        String cartPriceButton = "";
        cartPriceButton = cartPriceButtonRaw.substring(cartPriceButtonRaw.indexOf('-')+2, cartPriceButtonRaw.length());
        System.out.println(cartPriceButton);
        //Verify that correct price is displayed on the cart button
        Assert.assertEquals(cartPriceButton,macBookPrice);

        //Go to cart
        driver.findElement(By.cssSelector("#cart-total")).click();

        //Check that MacBook is added to the cart
        String cartLabel = "";
        cartLabel = driver.findElement(By.cssSelector("#cart > .dropdown-menu.pull-right .table.table-striped .text-left")).getText();
        List <WebElement> cartHeader = driver.findElements(By.cssSelector("#cart > .dropdown-menu.pull-right .table.table-striped .text-right"));
        String cartCount = "";
        cartCount = cartHeader.get(0).getText();
        Assert.assertEquals(cartLabel,"MacBook Air");
        Assert.assertEquals(cartCount,"x 1");

        //Check that MacBook costs as expected
        String cartPrice = "";
        cartPrice = cartHeader.get(1).getText();
        Assert.assertEquals(cartPrice,macBookPrice);



        //Search for iPhone 3
        driver.findElement(By.cssSelector("input[placeholder='Search']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("iPhone 3", Keys.ENTER);

        //Get Macbook Air
        List<WebElement> iPhones = driver.findElements(By.cssSelector(".product-thumb"));
        System.out.println("***iPhones.size() = " + iPhones.size());
        if (iPhones.size() == 0) {
            // Develop Custom Exception
            throw new RuntimeException("***iPhone(s) not found");
        }
        WebElement iPhone3 = null;
        for (WebElement iPhone : iPhones) {
            if (iPhone.findElement(By.cssSelector(".caption")).getText().contains("iPhone 3")) {
                iPhone3 = iPhone;
                break;
            }
        }
        if (iPhone3 == null) {
            throw new RuntimeException("*** iPhone3 not found");
        }

        //Store price
        String iPhone3PriceRaw = "";
        iPhone3PriceRaw = iPhone3.findElement(By.cssSelector(".price")).getText();
        System.out.println(iPhone3PriceRaw);
        String iPhone3Price = "";
        iPhone3Price = iPhone3PriceRaw.substring(0, iPhone3PriceRaw.indexOf('\n'));
        System.out.println(iPhone3Price);

        //Add to cart
        iPhone3.findElement(By.cssSelector(".button-group > button > i.fa-shopping-cart")).click();
        Thread.sleep(5000);

        //Read cart price
        cartPriceButtonRaw = "";
        cartPriceButtonRaw = driver.findElement(By.cssSelector("#cart-total")).getText();
        System.out.println(cartPriceButtonRaw);
        cartPriceButton = "";
        cartPriceButton = cartPriceButtonRaw.substring(cartPriceButtonRaw.indexOf('-')+2, cartPriceButtonRaw.length());
        System.out.println(cartPriceButton);
        cartPriceButton = cartPriceButton.replace("$","");
        cartPriceButton = cartPriceButton.replace(",","");
        macBookPrice = macBookPrice.replace("$","");
        iPhone3Price = iPhone3Price.replace("$","");
        macBookPrice = macBookPrice.replace(",","");
        iPhone3Price = iPhone3Price.replace(",","");
        float cartPriceButtonInt = Float.parseFloat(cartPriceButton);
        float macBookPriceInt = Float.parseFloat(macBookPrice);
        float iPhone3PriceInt = Float.parseFloat(iPhone3Price);
        float countCart =  iPhone3PriceInt + macBookPriceInt;
        //Verify that correct price is displayed on the cart button
        Assert.assertEquals(cartPriceButtonInt,countCart);

        //Go to cart
        driver.findElement(By.cssSelector("#cart-total")).click();

        //Check that MacBook is added to the cart
        cartLabel = "";
        cartLabel = driver.findElement(By.cssSelector("#cart > .dropdown-menu.pull-right .table.table-striped .text-left")).getText();
        cartHeader = driver.findElements(By.cssSelector("#cart > .dropdown-menu.pull-right .table.table-striped .text-right"));
        cartCount = "";
        cartCount = cartHeader.get(0).getText();
        Assert.assertEquals(cartLabel,"iPhone 3");
        Assert.assertEquals(cartCount,"x 1");
    }
}
