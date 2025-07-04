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

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	By cartProductsBy = By.cssSelector(".cartSection h3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By checkout = By.xpath("(//button[normalize-space()='Checkout'])[1]");

	By popup = By.cssSelector("#toast-container");

	public List<WebElement> getCartProductsList() {
		waitForElementToAppear(driver, cartProductsBy);
		return cartProducts;

	}

	public boolean convertWebElementToArrayList(String prodName) {

		List<String> cartItems = getCartProductsList().stream().map(WebElement::getText).collect(Collectors.toList());
		for (WebElement cartItem : cartProducts) {
			cartItems.add(cartItem.getText());
		}
		boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(prodName));
		return match;

	}

	public CheckoutPage goTocheckOut()

	{
		Actions a = new Actions(driver);
		hoverAndClick(driver, a, checkout);
		return new CheckoutPage(driver);
	}

}
