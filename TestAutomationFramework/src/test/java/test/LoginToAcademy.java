package test;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utilities.AbstractComponent;

public class LoginToAcademy extends TestBase {

	String sheetName = "LoginData";

	@Test(dataProvider = "LoginTestData")
	public void webLoginInvalid(String invalid_user, String invalid_password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		AbstractComponent.sendKeys(driver, By.id("inputUsername"), invalid_user);
		AbstractComponent.sendKeys(driver, By.name("inputPassword"), invalid_password);
		AbstractComponent.clickElement(driver, By.className("signInBtn"));
		String errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error"))).getText();
		if (errorMsg.contains("Incorrect username")) {
			System.out.println("App correctly rejected invalid login. Error shown: " + errorMsg);
		} else {
			System.out.println("App accepted invalid credentials. Error shown: " + errorMsg);
		}

	}

	@DataProvider(name = "LoginTestData")
	public Object[][] getData() {
		Object data[][] = AbstractComponent.getTestData(sheetName);
		return data;
	}

	@Test
	public void webLoginValid() throws IOException, InterruptedException {

		wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		LoginPage lp = new LoginPage(prop.getProperty("username"), prop.getProperty("password"),
				prop.getProperty("name"), prop.getProperty("email"), prop.getProperty("phone"), driver);

		lp.validLogin();
		System.out.println("Entered credentials and clicked login");
		String title = driver.getTitle();
		if (title.contains("Academy")) {
			System.out.println("Landed on expected page after login");
		} else {
			System.out.println("Unexpected page title: " + title);
		}

	}

	@Test(enabled = true)
	public void resetPassword() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(prop.getProperty("username"), prop.getProperty("password"),
				prop.getProperty("resetname"), prop.getProperty("resetemail"), prop.getProperty("resetphnno"), driver);
		lp.resetPswd();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		
		if (prop.getProperty("resetname") == null || prop.getProperty("resetemail") == null
				|| prop.getProperty("resetphnno") == null) {
			System.out.println("Missing data in config.properties for reset");
			throw new RuntimeException("Missing reset config keys");
		}

		String infoText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("infoMsg"))).getText();
		if (infoText.contains("rahulshettyacademy")) {
			System.out.println("Reset successful: " + infoText);
		} else {
			System.out.println("Reset failed or message not found: " + infoText);
		}
	}

}
