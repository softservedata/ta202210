package com.softserve.homework08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class HomeWork08 {
    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;

    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("TC error, name = " + testName );
        }
         driver.manage().deleteAllCookies();
    }

    @Test
    public void checkSummUSD() throws Exception {
        // Start
        presentationSleep();
        driver.findElement(By.cssSelector("button.btn.btn-link.dropdown-toggle")).click();
        List<WebElement> curr = driver.findElements(By.cssSelector(".currency-select.btn.btn-link.btn-block"));
        if (curr.size() == 0) {
            throw new RuntimeException("No Currency found");
        }
        System.out.println(curr.size());
        // Looking for USD
        WebElement usd = null;
        for (WebElement element : curr) {
            if (element.getText().contains("$ US Dollar")) {
                usd = element;
                break;
            }
        }
        if (usd == null) {
            throw new RuntimeException("USD not found");
        }
        usd.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        presentationSleep();

        // Add list of items
        List<WebElement> elements = driver.findElements(By.cssSelector(".product-thumb.transition"));
        if (elements.size() == 0) {
            throw new RuntimeException("No Elements found");
        }

        // Looking for Mac
        WebElement mac = null;
        for (WebElement element : elements) {
            if (element.getText().contains("MacBook")) {
                mac = element;
                break;
            }
        }
        if (mac == null) {
            throw new RuntimeException("Mac not found");
        }
        mac.findElement(By.cssSelector(".hidden-xs.hidden-sm.hidden-md")).click();
        WebElement alert = driver.findElement(By.cssSelector(".alert.alert-success"));
        WebElement confirm = alert.findElement(By.cssSelector("a[href*='product_id=43']"));
        presentationSleep();
        Assert.assertTrue(alert.isDisplayed());
        Assert.assertTrue(confirm.isDisplayed());
        driver.findElement(By.cssSelector(".close")).click();

        // Looking for iPhone 3
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement iphone3 = null;
        for (WebElement element : elements) {
            if (element.getText().contains("iPhone 3")) {
                iphone3 = element;
                break;
            }
        }
        if (iphone3 == null) {
            throw new RuntimeException("Mac not found");
        }
        iphone3.findElement(By.cssSelector(".hidden-xs.hidden-sm.hidden-md")).click();
        WebElement alert2 = driver.findElement(By.cssSelector(".alert.alert-success"));
        WebElement confirm2 = alert2.findElement(By.cssSelector("a[href*='product_id=40']"));
        Assert.assertTrue(alert2.isDisplayed());
        Assert.assertTrue(confirm2.isDisplayed());
        presentationSleep();

        // Check summ
        WebElement summ = driver.findElement(By.cssSelector("span[id='cart-total']"));
        Assert.assertTrue(summ.getText().contains("2 item(s) - $725.20"));

    }

}
