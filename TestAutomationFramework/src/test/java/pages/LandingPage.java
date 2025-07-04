package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	String email;
	String pswd;
	WebDriver driver;

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement user;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement signin;

	public ProductCatPage loginToApp(String email, String pswd) {
		user.sendKeys(email);
		password.sendKeys(pswd);
		signin.click();
		ProductCatPage productCatPage = new ProductCatPage(driver);
		return productCatPage;
	}
}
