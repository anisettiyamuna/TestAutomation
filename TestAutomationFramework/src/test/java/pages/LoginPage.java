package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

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

	@FindBy(className = "signInBtn")
	WebElement signIn;

	private WebDriver driver;

	private String user;

	private String pswd;

	public LoginPage(String user, String pswd, WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.user = user;
		this.pswd = pswd;
		PageFactory.initElements(driver, this);
		System.out.println(user + "\n" + pswd);

	}
	
	public void validLogin() {
		usrName.sendKeys(user);
		password.sendKeys(pswd);
		signIn.click();

	}

}
