package com.testend.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testend.abstractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{

	WebDriver driver;

	public ProductCatalogue(WebDriver driver){

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);


	}

	// TODO Auto-generated method stub
	@FindBy(css = ".mb-3")
	List<WebElement> items ;

	@FindBy(css = ".ng-animating")
	WebElement spinner ;

//	WebElement loading = driver.findElement(By.cssSelector(".ng-animating"));
	
	By productsBy = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	  By toastmessage = By.cssSelector(".toast-container");

	public List<WebElement> getProductList() {

		waituntilElementAppear(productsBy);		
		return items;
	}

	public WebElement getProductName(String productName) {

		WebElement prod =  getProductList().stream().filter(item-> item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		return prod;
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod =getProductName(productName);
		
		prod.findElement(addToCart).click();
		
		waituntilElementAppear(toastmessage);
		
		waituntilElementDisappear(spinner);
	}

}
