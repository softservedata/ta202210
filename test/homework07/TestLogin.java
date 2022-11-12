package homework07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.xml.soap.SAAJResult;
import java.time.Duration;

public class TestLogin {
    private static String browser = "Chrome";
    private static final int IMPLICIT_WAIT_SECOND = 5;
    static WebDriver driver;
    private static final String baseUrl = "http://taqc-opencart.epizy.com/";


    @BeforeSuite
    public static void beforeSuite(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public static void driverInit(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECOND));
        driver.get(baseUrl);
    }

    @Test
    public static void loginTest(){
        driver.findElement(By.cssSelector("a[title='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
        WebElement emailInput = driver.findElement(By.cssSelector("#input-email"));
        emailInput.sendKeys("hahaha@gmail.com");
        Assert.assertEquals(emailInput.getAttribute("value"), "hahaha@gmail.com");

        WebElement passwordInput = driver.findElement(By.cssSelector("#input-password"));
        passwordInput.sendKeys("qwerty");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        //---Assert login
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content > h2:nth-of-type(1)")).getText(),
                "My Account");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content > h2:nth-of-type(2)")).getText(),
                "My Orders");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content > h2:nth-of-type(3)")).getText(),
                "Newsletter");




    }
}
