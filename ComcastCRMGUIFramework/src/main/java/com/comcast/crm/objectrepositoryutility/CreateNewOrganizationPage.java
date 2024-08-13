package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateNewOrganizationPage {

	WebDriver driver;

	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgNameEdit;

	@FindBy(name = "industry")
	private WebElement industryDD;
	
	@FindBy(name = "accounttype")
	private WebElement typeDD;
	
	@FindBy(id = "phone")
	private WebElement phoneNoEdit;
	
	@FindBy(name = "button")
	private WebElement saveBtn;

	public WebElement getIndustryDropdown() {
		return industryDD;
	}
	
	public WebElement gettypeDropdown() {
		return typeDD;
	}

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	
	public WebElement getphoneNoEdit() {
		return phoneNoEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Business Logic
	
	public void createOrg(String orgName) {
		orgNameEdit.sendKeys(orgName);// getting the data from test script (excel+random int)
		saveBtn.click();
	}

	public void createOrg(String orgName, WebDriverUtility wlib, String industryName, String typeName ) {
		orgNameEdit.sendKeys(orgName);
		wlib.handleDropdown(industryDD, industryName); // coming from excel
		wlib.handleDropdown(typeDD, typeName); // coming from excel
		saveBtn.click();
	}
	
	public void createOrg(String orgName, String phoneNo) {
		orgNameEdit.sendKeys(orgName);// getting the data from test script (excel+random int)
		phoneNoEdit.sendKeys(phoneNo);
		saveBtn.click();
	}
	
}
