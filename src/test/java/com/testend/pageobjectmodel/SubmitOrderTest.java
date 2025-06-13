package com.testend.pageobjectmodel;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testend.testcomponents.BaseTest;


public class SubmitOrderTest extends BaseTest{

	String productname = "ZARA COAT 3";
	
	@Test(dataProvider = "getData")
	public  void submitTest(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
		ProductCatalogue productcatalogue = landingpage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement>  products= productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage = 	productcatalogue.goTocartpage();
		Boolean match =	cartpage.verifyCartPage(input.get("product"));
		Assert.assertTrue(match);
		Checkoutpage checkoutpage =	cartpage.goToCheckoutPage();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationPage = 	checkoutpage.submitOrder();
		String confirmationmessage =confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

   @Test(dependsOnMethods = {"submitTest"})
	public void orderHistoryTest() {
		
		ProductCatalogue productcatalogue = landingpage.LoginApplication("balasb477@gmail.com", "Kgf@2000");
		
		OrderPage orderpage = productcatalogue.goToOrderPage();
		
		orderpage.verifyOrderDisplay(productname);
		
		Assert.assertTrue(orderpage.verifyOrderDisplay(productname));
	   
	}

   
   
 
   
   @DataProvider
   public Object[][] getData() throws IOException{
	   
	   
	   List<HashMap<String,String>> data = getJsonDataToMap("src/test/java/com/testend/pageobjectmodel/purchaseOrder.json");
	   
	   
	   
	   return new Object [] [] {
		  
		   {data.get(0)},{data.get(1)}
	   };
	   
   }
   
}


