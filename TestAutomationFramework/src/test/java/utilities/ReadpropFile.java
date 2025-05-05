package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class ReadpropFile extends TestBase {
	
	private static WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(50));
	
	public static void main(String[] args) throws IOException {
		
		fr = new FileReader(
				"C:\\Users\\ADMIN\\eclipse-workspace\\TestAutomation\\TestAutomationFramework\\src\\test\\resources\\configfiles\\config.properties");
		prop.load(fr);
		System.out.println(prop.getProperty("browser"));
	}

	public static void waitForElementAndClick(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public static void sendKeys(By locater, String value) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locater));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

}
