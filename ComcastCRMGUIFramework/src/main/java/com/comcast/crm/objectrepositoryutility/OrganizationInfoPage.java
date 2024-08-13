package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;

	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement listedOrgName;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement listedIndustryName;

	@FindBy(id = "dtlview_Type")
	private WebElement listedType;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement listedPhoneNo;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	public WebElement getListedOrgName() {
		return listedOrgName;
	}
	
	public WebElement getListedIndustryName() {
		return listedIndustryName;
	}

	public WebElement getListedType() {
		return listedType;
	}
	
	public WebElement getListedPhoneNo() {
		return listedPhoneNo;
	}
}
