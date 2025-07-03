package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AbstractComponent extends TestBase {
	static WebDriver driver;

	static String TESTDATA_SHEET_PATH = "C:\\Users\\ADMIN\\eclipse-workspace\\TestAutomation\\TestAutomationFramework\\src\\test\\resources\\testdata\\ExcelData.xlsx";
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	static XSSFCell cell;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[@routerlink='/dashboard/cart'])[1]")
	WebElement cart;

	public void goToCart() {
		cart.click();
	}

	// To extract the data from DDF(EXCEL)
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +

		// Loop through rows and cells
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i + 1); // i + 1 to skip header row
			if (row != null) { // Check if the row is not null
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					Cell cell = row.getCell(k); // Get the cell
					if (cell != null) { // Check if the cell is not null
						data[i][k] = cell.toString(); // Safe to call toString()
					} else {
						data[i][k] = ""; // Assign empty string or default value for null cells
					}
				}
			}

		}
		return data;
	}

	public static void waitForElementToAppear(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public static void waitForElementToDisappear(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public static void waitForElementAndClick(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public static void clickElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public static void sendKeysToElement(WebDriver driver, By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
	}

	public static void selectDropdownValue(WebDriver driver, By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		new Select(dropdown).selectByVisibleText(value);
	}

	public static void clickWithJs(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public static void setTime(WebDriver driver, String hourSelector, String hourValue, String minuteSelector,
			String minuteValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		sendKeysToElement(driver, By.cssSelector(hourSelector), hourValue);
		sendKeysToElement(driver, By.cssSelector(minuteSelector), minuteValue);
	}

	public static String getElementText(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	public static void hoverAndClick(WebDriver driver, Actions action, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement menu = driver.findElement(locator);
		action.moveToElement(menu).click().build().perform();
	}

	public static void sendKeysWithActions(WebDriver driver, Actions action, WebElement element, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		action.sendKeys(element, value).build().perform();
	}

	public static void sendKeys(WebDriver driver, By locater, String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locater));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

}
