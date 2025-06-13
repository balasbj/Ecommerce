package com.testend.pageobjectmodel;

import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.testend.testcomponents.BaseTest;


public class ErrorValidation extends BaseTest{

	@Test
	public  void loginTest() throws IOException {
		// TODO Auto-generated method stub

		landingpage.LoginApplication("balasb477@gmail.com", "Kgf@200");
		landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.",  landingpage.getErrorMessage());

	}

	@Test
	public  void productErrorValidation() throws IOException{
		String productname = "ZARA COAT 3";
		LandingPage loginpage =	launchApplication();
		ProductCatalogue productcatalogue = loginpage.LoginApplication("anshika@gmail.com", "Iamking@2000");
		List<WebElement>  products= productcatalogue.getProductList();
		productcatalogue.addProductToCart(productname);
		CartPage cartpage = 	productcatalogue.goTocartpage();
		Boolean match =	cartpage.verifyCartPage("ZARA COAT 33");
		Assert.assertFalse(match);
	}


	

}