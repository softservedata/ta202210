package com.softserve.edu.homework11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Browsers {
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    //public static ChromeDriverService service;
    public static DriverService service;

    private void takeScreenShot(WebDriver driver) throws IOException {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
    }
    @Test
    public void testFirefox() throws Exception {
        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("default-release"); // TODO NOT Start
        WebDriverManager.firefoxdriver().setup();
        //
        FirefoxOptions options = new FirefoxOptions().setProfile(profile);
        WebDriver driver = new FirefoxDriver(options);

        //Select currency
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://taqc-opencart.epizy.com/");
        driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
        driver.findElement(By.xpath("//button[@name='EUR']")).click();
        //Search for MacBook
        driver.findElement(By.cssSelector("input[placeholder='Search']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("MacBook");
        // Click Search Button
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        //Get Macbook Air
        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/*/span[@class='hidden-xs hidden-sm hidden-md']")).click();
        //Go to cart
        driver.findElement(By.xpath("//span[@id='cart-total']")).click();
        //Find MacBook Air in the cart popup
        String macBookCartPopupLabel = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='MacBook Air']")).getText();
        String macBookCartPopupCount = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='MacBook Air']/../following-sibling::td[@class='text-right'][1]")).getText();
        //Verify that MacBook Air and iPhone 3 are added to the cart
        Assert.assertEquals(macBookCartPopupLabel,"MacBook Air");
        Assert.assertEquals(macBookCartPopupCount,"x 1");

        driver.quit();
    }
    // Chrome Without UI
    @Test
    public void testChrome() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Chrome Without UI
        WebDriver driver = new ChromeDriver(options);
        System.out.println("\tStart");

        //Select currency
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://taqc-opencart.epizy.com/");
        driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
        driver.findElement(By.xpath("//button[@name='EUR']")).click();
        System.out.println("\tCurrency is selected");
        //Search for MacBook
        driver.findElement(By.cssSelector("input[placeholder='Search']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("MacBook");
        System.out.println("\tSearch for MacBook");
        // Click Search Button
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
        System.out.println("\tSearch button is clicked");
        //Get Macbook Air
        List<WebElement> macBooks = driver.findElements(By.cssSelector(".product-thumb"));
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
        System.out.println("\tMacbook found");
        //Add to cart
        macBookAir.findElement(By.cssSelector(".button-group > button > i.fa-shopping-cart")).click();
        Thread.sleep(5000);
        //Go to cart
        driver.findElement(By.xpath("//span[@id='cart-total']")).click();
        System.out.println("\tCart is open");
        String cartLabel = "";
        cartLabel = driver.findElement(By.cssSelector("#cart > .dropdown-menu.pull-right .table.table-striped .text-left")).getText();
        List <WebElement> cartHeader = driver.findElements(By.cssSelector("#cart > .dropdown-menu.pull-right .table.table-striped .text-right"));
        String cartCount = "";
        cartCount = cartHeader.get(0).getText();
        System.out.println("\tMacbook is found in cart");
        //Verify that MacBook Air and iPhone 3 are added to the cart
        Assert.assertEquals(cartLabel,"MacBook Air");
        Assert.assertEquals(cartCount,"x 1");
        System.out.println("\tPage title is: " + driver.getTitle());
        driver.quit();
    }
    @Test
    public void testPhantomjs() throws Exception {
        WebDriver driver = new PhantomJSDriver();
        driver.manage().window().maximize();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://taqc-opencart.epizy.com/");
        driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
        driver.findElement(By.xpath("//button[@name='EUR']")).click();
        System.out.println("\tTaking First Screenshot ...");

        //Search for MacBook
        driver.findElement(By.cssSelector("input[placeholder='Search']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("MacBook");
        System.out.println("\tTaking Second Screenshot ...");
        takeScreenShot(driver);

        // Click Search Button
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();

        //Get Macbook Air
        driver.findElement(By.xpath("//a[text()='MacBook Air']/../../following-sibling::div[@class='button-group']/*/span[@class='hidden-xs hidden-sm hidden-md']")).click();
        System.out.println("\tTaking Third Screenshot ...");
        takeScreenShot(driver);

        //Go to cart
        driver.findElement(By.xpath("//span[@id='cart-total']")).click();

        //Find MacBook Air in the cart popup
        String macBookCartPopupLabel = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='MacBook Air']")).getText();
        String macBookCartPopupCount = driver.findElement(By.xpath("//td[@class='text-left']/a[text()='MacBook Air']/../following-sibling::td[@class='text-right'][1]")).getText();

        System.out.println("\tTaking Fourth Screenshot ...");takeScreenShot(driver);

        //Verify that MacBook Air and iPhone 3 are added to the cart
        Assert.assertEquals(macBookCartPopupLabel,"MacBook Air");
        Assert.assertEquals(macBookCartPopupCount,"x 1");

        System.out.println("\tPage title is: " + driver.getTitle());
        driver.quit();
    }

}
