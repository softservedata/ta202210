package com.softserve.edu.homework10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filter {
    private static final String BASE_URL = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/";
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    static WebDriver driver;

    private void takeScreenShot() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // Use Custom Exception
        }
    }

    private void takePageSource() {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_source.html");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void closePopup1(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        long timeStart = System.currentTimeMillis();
        List<WebElement> foooterButton = driver.findElements(By.xpath("//footer[contains(@class,'cookie')]//button"));
        System.out.println("***foooterButton.size() = " + foooterButton.size());
        System.out.println("***time = " + (System.currentTimeMillis() - timeStart));
        if (foooterButton.size() > 0) {
            foooterButton.get(0).click();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
    }

    @BeforeSuite
    public static void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
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
        closePopup1();
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if (!result.isSuccess()) {
            String testName = result.getName();
            System.out.println("Failed" + testName);
            takeScreenShot();
            takePageSource();
        }
    }

    @Test
    public static void searchByFilter() {
        // Move to Element by JavaScript Injection
        WebElement position = driver.findElement(By.id("use-filtering-with-other-data-processing-plugins"));
        Actions action = new Actions(driver);
        action.moveToElement(position).perform();
        // Switch To IFrame
        driver.switchTo().frame(driver.findElement(By.xpath("//div[contains(@data-options,'grid-filtering/filter-row')]//iframe")));

        // type 'L' in City filter
        driver.findElement(By.xpath("//th[@class='MuiTableCell-root MuiTableCell-head MuiTableCell-sizeMedium TableFilterCell-cell css-1bkpkl5'][3]")).click();
        driver.findElement(By.xpath("//th[@class='MuiTableCell-root MuiTableCell-head MuiTableCell-sizeMedium TableFilterCell-cell css-1bkpkl5'][3]//input")).clear();
        driver.findElement(By.xpath("//th[@class='MuiTableCell-root MuiTableCell-head MuiTableCell-sizeMedium TableFilterCell-cell css-1bkpkl5'][3]//input")).sendKeys("L");
        //Check existence of "Las Vegas" and "London"
        List<WebElement> lines = driver.findElements(By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']/tr[@class='MuiTableRow-root css-e42jjo']/td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-sizeMedium TableCell-cell TableCell-cellNoWrap css-1ebvd0u'][3]"));
        System.out.println("***lines.size() = " + lines.size());
        List<String> linesText = new ArrayList();
        for (WebElement line : lines) {
            String lineText = line.getText();
            linesText.add(lineText);
            }
        linesText.forEach(System.out::println);
        System.out.println((linesText).toString());
        Assert.assertTrue(linesText.contains("Las Vegas"));
        Assert.assertTrue(linesText.contains("London"));
    }

}
