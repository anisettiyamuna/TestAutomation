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
import utilities.ReadpropFile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginToZoho extends TestBase {

	@Test(priority = 1)
	public void LoginToZoho() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		
	    ReadpropFile.waitForElementAndClick(By.linkText("Sign In")); // base
	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login_id']"))).click();
	    
	    ReadpropFile.sendKeys(By.xpath("//input[@id='login_id']"), prop.getProperty("user"));
	    
	    ReadpropFile.waitForElementAndClick(By.xpath("//button[@id='nextbtn']"));
	    
	    ReadpropFile.sendKeys(By.xpath("//input[@id='password']"), prop.getProperty("pswd"));
		
	    ReadpropFile.waitForElementAndClick(By.xpath("//button[@id='nextbtn']//span[contains(text(),'Sign in')]"));
	    
	    Thread.sleep(5000);
		
		String Title = driver.getTitle();
		
		Assert.assertEquals("Home Page - Zoho CRM", Title);

	}

}
