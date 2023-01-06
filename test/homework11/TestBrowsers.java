package homework11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;

public class TestBrowsers {

    @Test
    public static void testBrowserProfile(){
       String userProfile = System.getenv("HOMEPATH" )
               + "\\AppData\\Local\\Google\\Chrome\\User Data";
//        String userProfile = "C:\\Users\\oliap\\AppData\\Local\\Google\\Chrome\\User Data";
        System.out.println(userProfile);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--user-data-dir="+userProfile);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.google.com/");
    }

    @Test
    public static void testHeadlessBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.google.com/");
    }

    @Test
    public static void testHeadlessHTMLUnitDriverBrowser(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://www.google.com/");
        System.out.println("Google open");
        driver.findElement(By.className("gLFyf")).sendKeys("Argentina");
        System.out.println("Enters Argentina");
        driver.findElement(By.name("btnK")).click();
    }

    @Test
    public static void testHeadlessPhantomJSBrowser() throws InterruptedException {
        WebDriverManager.phantomjs().setup();
        WebDriver driver = new PhantomJSDriver();
        driver.get("https://www.google.com/");
        System.out.println("Google open");
        Thread.sleep(1000);
        driver.findElement(By.className("gLFyf")).sendKeys("Argentina"); //error
        System.out.println("Enters Argentina");
        driver.findElement(By.name("btnK")).click();
    }


}
