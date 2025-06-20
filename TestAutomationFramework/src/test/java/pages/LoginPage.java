package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utilities.ReadTestData;

public class LoginPage {

	@FindBy(id = "inputUsername")
	WebElement usrName;

	@FindBy(name = "inputPassword")
	WebElement password;

	@FindBy(id = "chkboxOne")
	WebElement rememberMe;

	@FindBy(id = "chkboxTwo")
	WebElement privacy;

	@FindBy(linkText = "Forgot your password?")
	WebElement resetPswd;

	@FindBy(xpath = "//input[@placeholder='Name']")
	WebElement resetname;

	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement resetemail;

	@FindBy(xpath = "//input[@placeholder='Phone Number']")
	WebElement phoneNo;

	@FindBy(className = "reset-pwd-btn")
	WebElement resetBtn;

	@FindBy(className = "signInBtn")
	WebElement signIn;

	private WebDriver driver;

	private String user;
	private String pswd;
	private String name;
	private String email;
	private String phone;

	public LoginPage(String user, String pswd, String name, String email, String phone, WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.user = user;
		this.pswd = pswd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		PageFactory.initElements(driver, this);

	}

	public void validLogin() {
		usrName.sendKeys(user);
		password.sendKeys(pswd);
		if(!privacy.isSelected())
		{
			privacy.click();
		}
		if(!rememberMe.isSelected())
		{
			rememberMe.click();
		}
		
		signIn.click();

	}

	public void resetPswd() throws IOException, InterruptedException {

		resetPswd.click();
		Thread.sleep(1000);
		resetname.sendKeys(name);
		resetemail.sendKeys(email);
		phoneNo.sendKeys(phone);
		resetBtn.click();

	}

}
