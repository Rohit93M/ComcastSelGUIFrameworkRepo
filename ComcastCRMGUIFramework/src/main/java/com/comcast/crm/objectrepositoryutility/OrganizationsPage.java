package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	WebDriver driver;

	public OrganizationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement CreateNewOrgBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchTF;

	@FindBy(id = "bas_searchfield")
	private WebElement searchDD;

	@FindBy(name = "submit")
	private WebElement searchBtn;
	
	public WebElement getCreateNewOrgBtn() {
		return CreateNewOrgBtn;	
	}
	
	public WebElement getSearchTF() {
		return searchTF;
	}
	
	public WebElement getSearchDD() {
		return searchDD;
	}
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}
}