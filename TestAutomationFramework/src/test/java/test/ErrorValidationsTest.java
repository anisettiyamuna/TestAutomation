package test;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LandingPage;
import utilities.Retry;
import java.io.IOException;


import org.testng.Assert;


/*import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;*/

public class ErrorValidationsTest extends TestBase {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
	
		landingPage.loginToApp("anshika@gmail.com", "Iamki000");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	

	/*
	 * @Test public void ProductErrorValidation() throws IOException,
	 * InterruptedException {
	 * 
	 * String productName = "ZARA COAT 3"; ProductCatalogue productCatalogue =
	 * landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
	 * List<WebElement> products = productCatalogue.getProductList();
	 * productCatalogue.addProductToCart(productName); CartPage cartPage =
	 * productCatalogue.goToCartPage(); Boolean match =
	 * cartPage.VerifyProductDisplay("ZARA COAT 33"); Assert.assertFalse(match);
	 * 
	 * 
	 * 
	 * }
	 */

	
	

}
