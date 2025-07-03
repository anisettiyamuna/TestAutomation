package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	String email;
	String pswd;
	WebDriver driver;

	public LandingPage(WebDriver driver, String email, String pswd) {

		this.driver = driver;
		this.email = email;
		this.pswd = pswd;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement user;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement signin;

	public void loginToApp() {
		user.sendKeys(email);
		password.sendKeys(pswd);
		signin.click();
	}
}
