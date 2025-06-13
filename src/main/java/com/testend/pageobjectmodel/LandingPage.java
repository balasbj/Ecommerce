package com.testend.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testend.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;

	public LandingPage(WebDriver driver){

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);


	}

	// TODO Auto-generated method stub
	@FindBy(id = "userEmail")
	WebElement userEmail ;


	@FindBy(id = "userPassword")
	WebElement userpassword;


	@FindBy(id = "login")
	WebElement submit;
	
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errormessage;
	
	
	public ProductCatalogue LoginApplication(String email,String password) {
		
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
		
			}
	
	public String  getErrorMessage() {
		waituntilWebElementAppear(errormessage);
		return errormessage.getText();
	}
	
	public void GoTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}


}
