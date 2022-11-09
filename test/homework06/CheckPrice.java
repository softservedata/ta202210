package homework06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckPrice {
    private static String browser = "Chrome";
    private static final int IMPLICIT_WAIT_SECOND = 3;
    static WebDriver driver;
    private static final String baseUrl = "http://taqc-opencart.epizy.com/";

   @BeforeClass
    public static void initDriver() {
       if (browser.equals("Chrome")) {
           WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
       } else {
           WebDriverManager.firefoxdriver().setup();
           driver = new FirefoxDriver();
       }
       driver.get(baseUrl);
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECOND));
   }

   @Test
    public static void findIphone (){
       driver.findElement(By.cssSelector("form#form-currency .hidden-md.hidden-sm.hidden-xs")).click();
       driver.findElement(By.name("EUR")).click();
       driver.findElement(By.cssSelector("div#search > input[name='search']")).sendKeys("Iphone");
       driver.findElement(By.cssSelector(".btn.btn-default.btn-lg")).click();
       driver.findElement(By.linkText("iPhone XR")).isDisplayed();

       WebElement price = driver.findElement(By.xpath("//a[text()='iPhone XR']/../following-sibling::p[@class='price']"));
       Assert.assertEquals(price.getText().split("\n")[0],("707.71€"));
       //System.out.println(price.getText());
   }

   @AfterClass
    public static void endDriver(){
       driver.quit();
   }



}
