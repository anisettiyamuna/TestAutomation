package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AbstractComponent;

public class LandingPage extends AbstractComponent {
	String email;
	String pswd;
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement user;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement signin;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatPage loginToApp(String email, String pswd) {
		user.sendKeys(email);
		password.sendKeys(pswd);
		signin.click();
		ProductCatPage productCatPage = new ProductCatPage(driver);
		return productCatPage;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
