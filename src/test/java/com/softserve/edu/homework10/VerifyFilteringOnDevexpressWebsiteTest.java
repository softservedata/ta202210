package com.softserve.edu.homework10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Navigate to devexpress.github.io
 * Filter City in Uncontrolled Mode section with "L"
 * Verify filtering data starts with "L" (London, Las Vegas)
 */
public class VerifyFilteringOnDevexpressWebsiteTest {
    private WebDriver driver;
    private static final String BASE_URL = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/";
    private static final String FILTER_BAR_XPATH = "//th[contains(@class, 'MuiTableCell-root')][%d]//input";
    private static final String GRID_COLUMN_VALUES_XPATH = "//tr[contains(@class, 'MuiTableRow-root')]//td[%d]";
    private static final By TABLE_GRID = By.xpath("//div[contains(@data-options, 'grid-filtering/filter-row')]//iframe");
    private static final By GRID_COLUMN_NAMES = By.cssSelector("div.Content-content span");


    @BeforeClass
    public void setup() {
        // Setup driver and open URL
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    public void testVerifyFilterBarWithFrame() {

        // Sch to frame with grid
        driver.switchTo().frame(driver.findElement(TABLE_GRID));

        // Find filter bar for specified column, fill in 'L'
        // Verify filtering
        int columnNumber = getColumnNumber("City");
        findFilterBarAndFillIn(columnNumber, "L");
        Assert.assertTrue(getColumnValues(columnNumber).stream().allMatch(e -> e.toUpperCase().startsWith("L")),
                "All values in column 'City' should start with 'L'");
    }

    private List<String> getColumnNames() {
        List<WebElement> elements = driver.findElements(GRID_COLUMN_NAMES);
        List<String> columnNames = new ArrayList<>();
        for (WebElement element : elements) {
            columnNames.add(element.getText());
        }
        return columnNames;
    }

    private int getColumnNumber(String columnName) {
        int columnNumber = getColumnNames().indexOf(columnName);
        ++columnNumber;
        return columnNumber;
    }

    private void findFilterBarAndFillIn(int columnNumber, String text) {
        driver.findElement(By.xpath(String.format(FILTER_BAR_XPATH, columnNumber))).clear();
        driver.findElement(By.xpath(String.format(FILTER_BAR_XPATH, columnNumber))).sendKeys(text);
    }

    private List<String> getColumnValues(int columnNumber) {
        List<WebElement> elements = driver.findElements(By.xpath(String.format(GRID_COLUMN_VALUES_XPATH, columnNumber)));
        List<String> columnValues = new ArrayList<>();
        for (WebElement element : elements) {
            columnValues.add(element.getText());
        }
        return columnValues;
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
