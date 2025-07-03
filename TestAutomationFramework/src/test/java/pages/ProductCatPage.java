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

public class ProductCatPage extends AbstractComponent {

	WebDriver driver;

	public ProductCatPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	By productsBy = By.cssSelector(".mb-3");
	By cartProductsBy = By.cssSelector(".cartSection h3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By popup = By.cssSelector("#toast-container");

	By checkout = By.xpath("(//button[normalize-space()='Checkout'])[1]");
	By placeOrder = By.xpath("(//button[@type='button'])[2]");
	By submit = By.cssSelector(".action__submit");
	By confirmMsg = By.cssSelector(".hero-primary");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(driver, productsBy);
		return products;

	}

	public WebElement getProductName(String prodName) {
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(prodName))
				.findFirst().orElse(null);
		return prod;

	}

	public void addProductToCart(String prodName) {
		WebElement prod = getProductName(prodName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(driver, popup);
		waitForElementToDisappear(driver, spinner);
	}

	public List<WebElement> getCartProductsList() {
		goToCart();
		waitForElementToAppear(driver, cartProductsBy);
		return cartProducts;

	}

	public void convertWebElementToArrayList(String prodName) {

		List<String> cartItems = getCartProductsList().stream().map(WebElement::getText).collect(Collectors.toList());
		for (WebElement cartItem : cartProducts) {
			cartItems.add(cartItem.getText());
		}
		boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(prodName));
		Assert.assertTrue(match);
	
		if (cartItems.contains(prodName)) {

			test.pass("Product added into the cart");
		} else {
			test.fail("Product not found in the cart");
		}

	}

	public void checkOutPlaceOrder()

	{
		Actions a = new Actions(driver);
		hoverAndClick(driver, a, checkout);
		sendKeysWithActions(driver, a, country, "india");
		clickElement(driver, placeOrder);
		clickElement(driver, submit);
		String message = getElementText(driver, confirmMsg);
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}

}
