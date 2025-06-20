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
import utilities.ReadTestData;

public class LoginToAcademy extends TestBase {

	String sheetName = "LoginData";

	@Test
	public void webLoginValid() throws IOException, InterruptedException {
		test = extent.createTest("Login Test - Valid User");

		wait = new WebDriverWait(driver, Duration.ofSeconds(120));

		LoginPage lp = new LoginPage(prop.getProperty("username"), prop.getProperty("password"),
				prop.getProperty("name"), prop.getProperty("email"), prop.getProperty("phone"), driver);

		test.info("Testing with Valid Username: " + prop.getProperty("username") + ", Valid Password: "
				+ prop.getProperty("password"));
		lp.validLogin();
		test.pass("Entered credentials and clicked login");
		String title = driver.getTitle();
		if (title.contains("Academy")) {
			test.pass("Landed on expected page after login");
		} else {
			test.fail("Unexpected page title: " + title);
		}

	}

	@Test(dataProvider = "LoginTestData")
	public void webLoginInvalid(String invalid_user, String invalid_password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		test = extent.createTest("Login Test - Invalid User");
		test.info("Testing with Invalid Username: " + invalid_user + ", Invalid Password: " + invalid_password);
		ReadTestData.sendKeys(driver, By.id("inputUsername"), invalid_user);
		ReadTestData.sendKeys(driver, By.name("inputPassword"), invalid_password);
		ReadTestData.clickElement(driver, By.className("signInBtn"));
		String errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error"))).getText();
		if (errorMsg.contains("Incorrect username")) {
			test.pass("App correctly rejected invalid login. Error shown: " + errorMsg);
		} else {
			test.fail("App accepted invalid credentials. Error shown: " + errorMsg);
		}

	}

	@DataProvider(name = "LoginTestData")
	public Object[][] getData() {
		Object data[][] = ReadTestData.getTestData(sheetName);
		return data;
	}

	@Test
	public void resetPassword() throws IOException, InterruptedException {
		test = extent.createTest("Reset Password Test");
		LoginPage lp = new LoginPage(prop.getProperty("username"), prop.getProperty("password"),
				prop.getProperty("resetname"), prop.getProperty("resetemail"), prop.getProperty("resetphnno"), driver);
		lp.resetPswd();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		test.info("Name:" + prop.getProperty("resetname") + "\n Email:" + prop.getProperty("resetemail") + "\n Phn.No:"
				+ prop.getProperty("resetphnno"));

		if (prop.getProperty("resetname") == null || prop.getProperty("resetemail") == null
				|| prop.getProperty("resetphnno") == null) {
			test.fail("Missing data in config.properties for reset");
			throw new RuntimeException("Missing reset config keys");
		}

		String infoText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("infoMsg"))).getText();
		if (infoText.contains("rahulshettyacademy")) {
			test.pass("Reset successful: " + infoText);
		} else {
			test.fail("Reset failed or message not found: " + infoText);
		}
	}

}
