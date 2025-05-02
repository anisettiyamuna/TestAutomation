package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginToZoho extends TestBase {

	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));


	@Test(priority = 1)
	public void LoginToZoho() throws InterruptedException {
		
		driver.findElement(By.linkText("Sign In")).click(); // base
		driver.findElement(By.xpath("//input[@id='login_id']")).sendKeys(prop.getProperty("user"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(prop.getProperty("pswd"));
		driver.findElement(By.xpath("//button[@id='nextbtn']//span[contains(text(),'Sign in')]")).click();
		String Title = driver.getTitle();
		Assert.assertEquals("Zoho Home", Title);

	}

	/*
	 * private static void waitForElementAndClick(By locater) { WebElement element =
	 * wait.until(ExpectedConditions.elementToBeClickable(locater));
	 * element.click(); }
	 * 
	 * private static void sendKeys(By locater, String value) { WebElement element =
	 * wait.until(ExpectedConditions.presenceOfElementLocated(locater));
	 * wait.until(ExpectedConditions.visibilityOf(element));
	 * element.sendKeys(value); }
	 */
	 

}
