package com.testend.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testend.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cart;

	@FindBy(css = ".totalRow button")
	public WebElement checkoutpage;

	public CartPage(WebDriver driver){

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}


	public Boolean verifyCartPage(String productname) {
		Boolean match =	cart.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productname) );
		return match;
	}

	public Checkoutpage goToCheckoutPage() {

		checkoutpage.click();
		
		return new Checkoutpage(driver);

	}




}
