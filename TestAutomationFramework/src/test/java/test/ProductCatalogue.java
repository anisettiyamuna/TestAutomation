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
import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;
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
		String countryName = "India";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		LandingPage landingPage = new LandingPage(driver);
		// Initialize LandingPage with driver and credentials
		ProductCatPage productCatPage = landingPage.loginToApp(prop.getProperty("email"), prop.getProperty("password"));
		String title = driver.getTitle();
		if (title.contains("Shop")) {
			
			System.out.println("Landed on expected page after login");
		} else {
			System.out.println("Unexpected page title: " + title);
		}

		// Storing the product-list in products object
		productCatPage.getProductsList();

		// Getting the product from product-list and add to cart
		productCatPage.getProductName(prodName);
	//	test.info("Filtered the Zara product from the product list.");
		productCatPage.addProductToCart(prodName);
		CartPage cartPage = productCatPage.goToCart();

		// Getting the cart product-list
		cartPage.getCartProductsList();
		boolean match=cartPage.convertWebElementToArrayList(prodName);
		
		if (match) {
			System.out.println("Product is present in the cart.");
		    // Continue with assertions or further steps
		} else {
			System.out.println("Product not found in the cart.");
		}
		CheckoutPage checkoutPage = cartPage.goTocheckOut();
		checkoutPage.selectCountry(countryName);
		
		ConfirmationPage confirmationPage = checkoutPage.submit();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
}
