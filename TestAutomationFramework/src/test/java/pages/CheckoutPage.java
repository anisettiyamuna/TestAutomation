package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utilities.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	By placeOrder = By.xpath("(//button[@type='button'])[2]");
	By submit = By.cssSelector(".action__submit");
	
	public void selectCountry(String countryName)

	{
		Actions a = new Actions(driver);
		sendKeysWithActions(driver, a, country, countryName);
		clickElement(driver, placeOrder);

	}

	public ConfirmationPage submit() {
		clickElement(driver, submit);
		return new ConfirmationPage(driver);

	}

}
