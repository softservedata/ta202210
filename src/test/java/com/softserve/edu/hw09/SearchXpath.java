package com.softserve.edu.hw09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.time.Duration;

public class SearchXpath {
    private final String BASE_URL = "http://taqc-opencart.epizy.com/";
    private WebDriver driver;
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;

    private void presentationSleep() {
        presentationSleep(1);
    }

    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        presentationSleep();
        if (driver != null) {
            driver.quit(); // close()
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
        presentationSleep();
    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        presentationSleep();
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("***TC error, name = " + testName + " ERROR");
            presentationSleep();
        }
    }

    @Test
    public void findByXpath() {
        driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
        driver.findElement(By.xpath("//button[@name='USD']")).click();
        driver.findElement(By.xpath("//div//div//button[@onclick = \"cart.add('43');\"]//span[text()='Add to Cart']")).click();
        presentationSleep();
        driver.findElement(By.xpath("//div//div//button[@onclick = \"cart.add('40');\"]//span[text()='Add to Cart']")).click();
        presentationSleep();
        driver.findElement(By.xpath("//span[@id='cart-total']")).click();
        presentationSleep();
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='iPhone 3']")).getText().contains("iPhone 3"));
        presentationSleep();
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='MacBook']")).getText().contains("MacBook"));
        presentationSleep();
        driver.findElement(By.xpath("//p[@class='text-right']/a[@href='http://taqc-opencart.epizy.com/index.php?route=checkout/cart']")).click();
        presentationSleep();
        driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/input[@type='text']")).click();
        presentationSleep();
        driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/input[@type='text']")).clear();
        presentationSleep();
        driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[1]/td[4]/div/input[@type='text']")).sendKeys("2");
        presentationSleep();
        driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[4]/div/input[@type='text']")).click();
        presentationSleep();
        driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[4]/div/input[@type='text']")).clear();
        presentationSleep();
        driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[2]/td[4]/div/input[@type='text']")).sendKeys("3");
        presentationSleep();
        driver.findElement(By.xpath("//button[@data-original-title='Update']")).click();
        presentationSleep();
        driver.findElement(By.xpath("//span[@id='cart-total']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//table[@class='table table-bordered']/tbody/tr[4]/td[2]")).getText().contains("$2,052.40"));
        //Assert.assertTrue(driver.findElement(By.xpath("//tbody/tr[4]/td[2]")).getText().contains("$2,052.40"));


    }
}


