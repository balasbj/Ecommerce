package com.testend.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testend.abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	

	public OrderPage(WebDriver driver){

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}


	public Boolean verifyOrderDisplay(String productname) {
		Boolean match =	productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname) );
		return match;
	}

	




}
