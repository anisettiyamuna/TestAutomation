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
import utilities.ReadTestData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginToZoho extends TestBase {

	@Test(priority = 1)
	public void loginToZoho() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		ReadTestData.waitForElementAndClick(driver,By.linkText("Sign In")); // base
	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login_id']"))).click();
	    
	    ReadTestData.sendKeys(driver,By.xpath("//input[@id='login_id']"), prop.getProperty("user"));
	    
	    ReadTestData.waitForElementAndClick(driver,By.xpath("//button[@id='nextbtn']"));
	    
	    ReadTestData.sendKeys(driver,By.xpath("//input[@id='password']"), prop.getProperty("pswd"));
		
	    ReadTestData.waitForElementAndClick(driver,By.xpath("//button[@id='nextbtn']//span[contains(text(),'Sign in')]"));
	    
	    Thread.sleep(5000);
		
		String actual_Title = driver.getTitle();
		
		String expected_Title = "Home Page - Zoho CRM";
		
		Assert.assertEquals(actual_Title, expected_Title);

	}

}
