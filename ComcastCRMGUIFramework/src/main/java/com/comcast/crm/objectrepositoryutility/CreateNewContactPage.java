package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateNewContactPage {

	WebDriver driver;

	public CreateNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameEdit;

	@FindBy(name = "support_start_date")
	private WebElement suppStartDateEle;
	
	@FindBy(name = "support_end_date")
	private WebElement suppEndDateEle;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement plusIcon;
	
	@FindBy(id="search_txt")
	WebElement searchTF;
	
	@FindBy(name="search")
	WebElement searchBtn;
	
	@FindBy(name = "button")
	private WebElement buttonEle;
	
	
	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}
	
	public WebElement getSuppStartDateEle() {
		return suppStartDateEle;
	}

	public WebElement getSuppEndDateEle() {
		return suppEndDateEle;
	}
	
	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getButtonEle() {
		return buttonEle;
	}
	
	
	//Business Logic
	
	public void createContact(String lastName) {
		lastNameEdit.sendKeys(lastName);// getting the data from test script (excel+random int)
		buttonEle.click();
	}
	
	public void createContactWithSupportDate(String lastName, String startDate, String endDate) {
		lastNameEdit.sendKeys(lastName);// getting the data from test script (excel+random int)
		suppStartDateEle.clear();
		suppStartDateEle.sendKeys(startDate);
		suppEndDateEle.clear();
		suppEndDateEle.sendKeys(endDate);
		buttonEle.click();
	}
	
	public void createContactWithOrg(String lastName, WebDriverUtility wlib, String orgName) {
		lastNameEdit.sendKeys(lastName);// getting the data from test script (excel+random int)
		plusIcon.click();
		
		// switch to child window/
		wlib.switchToWindowOnUrl(driver, "module=Accounts");

		searchTF.sendKeys(orgName);

		searchBtn.click();
		driver.findElement(By.linkText(orgName)).click();
		
		// switch to parent window
		wlib.switchToWindowOnUrl(driver, "Contacts&action");
		
		buttonEle.click();
	}
	
}
