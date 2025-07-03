package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LandingPage;
import pages.ProductCatPage;
import utilities.AbstractComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogue extends TestBase {

	@Test
	public void submitOrder() throws InterruptedException {

		String prodName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		test = extent.createTest("SubmitOrder - Products");

		// Initialize LandingPage with driver and credentials
		LandingPage landingPage = new LandingPage(driver, prop.getProperty("email"), prop.getProperty("password"));

		// Perform login action
		landingPage.loginToApp();
		test.pass("Login Successful");
		String title = driver.getTitle();
		if (title.contains("Shop")) {
			test.pass("Landed on expected page after login");
		} else {
			test.fail("Unexpected page title: " + title);
		}

		// Creating object for ProductCatPage
		ProductCatPage productCatPage = new ProductCatPage(driver);

		// Storing the product-list in products object
		productCatPage.getProductsList();
		test.info("Products stored successful");

		// Getting the product from product-list and add to cart
		productCatPage.getProductName(prodName);
		test.info("Filtered the Zara product from the product list");
		productCatPage.addProductToCart(prodName);

		// Getting the cart product-list
		productCatPage.getCartProductsList();
		productCatPage.convertWebElementToArrayList(prodName);
		productCatPage.checkOutPlaceOrder();

	}
}
