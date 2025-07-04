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

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By popup = By.cssSelector("#toast-container");

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
	
	public CartPage goToCart() {
	
		goToCartPage();
		return new CartPage(driver);
	}
	
}
