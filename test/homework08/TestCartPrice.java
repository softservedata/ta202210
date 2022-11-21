package homework08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCartPrice {
    private static String browser = "Chrome";
    private static final long IMPLICIT_WAIT_SECOND = 100L;
    static WebDriver driver;
    private static final String baseUrl = "http://taqc-opencart.epizy.com/";


    @BeforeSuite
    public static void beforeSuite(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public static void initDriver() {
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECOND));
        driver.get(baseUrl);
    }

    @Test
    public static void testCartPrice() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.cssSelector(".fa-caret-down")).click();
        driver.findElement(By.cssSelector("button[name='USD']")).click();
        driver.findElement(By.cssSelector("div#search > input[name='search']")).sendKeys("Iphone");
        driver.findElement(By.cssSelector(".btn.btn-default.btn-lg")).click();
        driver.findElement(By.linkText("iPhone 3")).isDisplayed();
        String iphone3Price= driver.findElement(By.xpath("//a[text()='iPhone 3']/ancestor::node()/p[@class='price']")).getText().split("\n")[0];
        Assert.assertEquals(iphone3Price, "$123.20");
        driver.findElement(By.xpath(
                "//a[text()='iPhone 3']/ancestor::node()/div[@class='button-group']/button[contains(@onclick,'cart.add')]")
        ).click();
        driver.findElement(By.linkText("Laptops & Notebooks")).click();
        driver.findElement(By.partialLinkText("Macbook")).click();
        //String macBookPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='MacBook']/ancestor::div[@class='caption']/p[@class='price']"))).getText().split("\n")[0];
        driver.findElement(By.xpath("//a[text()='MacBook']/ancestor::node()/div[@class='button-group']/button[contains(@onclick,'cart')]")).click();
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cart']//span")));
        cart.click();
        String totalPrice = driver.findElement(By.xpath("//td[strong='Total']/following-sibling::td")).getText();
        Assert.assertEquals(totalPrice, "$725.20");
    }

}
