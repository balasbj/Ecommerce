package com.testend.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testend.pageobjectmodel.CartPage;
import com.testend.pageobjectmodel.OrderPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver){

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}


	@FindBy(css =("[routerlink*='cart']"))
	WebElement cart;
	
	@FindBy(css =("[routerlink*='myorders']"))
	WebElement orderheaders;


	public void waituntilElementAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));}



	public void waituntilWebElementAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOf( findBy));}




	public void waituntilElementDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


		wait.until(ExpectedConditions.invisibilityOf(ele));}

	public CartPage goTocartpage() {

		cart.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
		//		
	}
	
	public OrderPage goToOrderPage() {

		orderheaders.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
		//		
	}
	
}
