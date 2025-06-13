package com.testend.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.testend.pageobjectmodel.CartPage;
import com.testend.pageobjectmodel.Checkoutpage;
import com.testend.pageobjectmodel.ConfirmationPage;
import com.testend.pageobjectmodel.LandingPage;
import com.testend.pageobjectmodel.ProductCatalogue;
import com.testend.testcomponents.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	LandingPage landingpage;
	ProductCatalogue productcatalogue;
	ConfirmationPage confirmationPage ;
	
	@Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingpage = launchApplication();
	}
	
	
	@Given("^I logged in with (.+) and (.+)$")
	public void loggedin_username_and_password(String username,String password) {
		
		productcatalogue = landingpage.LoginApplication(username,password);
		
	}
	
	
	@When("I add product {string} to the cart")
	public void I_add_product_to_cart(String productName) {
		
		List<WebElement>  products= productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);

	}
	
	
	@And ("checkout product {string} and submit the order")
	public void checkout_product_and_submit_the_order(String productName) {
		
		CartPage cartpage = 	productcatalogue.goTocartpage();
		Boolean match =	cartpage.verifyCartPage(productName);
		Assert.assertTrue(match);
		Checkoutpage checkoutpage =	cartpage.goToCheckoutPage();
		checkoutpage.selectCountry("India");
	     confirmationPage = 	checkoutpage.submitOrder();
		
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_diplayed_confirmation_page(String string) {
		
		String confirmationmessage =confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	
	@Then("{string} message is displayed")
	public void message_diplayed_(String string) {
		
		Assert.assertEquals(string,  landingpage.getErrorMessage());
		driver.close();
	}
	
	
}
