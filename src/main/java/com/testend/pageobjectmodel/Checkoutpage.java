package com.testend.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testend.abstractcomponents.AbstractComponents;

public class Checkoutpage extends AbstractComponents{

	WebDriver driver;

	public Checkoutpage(WebDriver driver){

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}


	@FindBy(css ="[placeholder ='Select Country']")
	public WebElement country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	public void  selectCountry(String countryName) {

		Actions a = new Actions(driver);

		a.sendKeys(country, countryName).build().perform();

		waituntilElementAppear(results);

		selectCountry.click();
	


	}
	
	
	public ConfirmationPage submitOrder() {
		
		submit.click();
		
		return new ConfirmationPage(driver);
	}




}
