package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	WebDriver driver;

	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement listedLastName;

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement listedSuppStartDate;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement listedSuppEndDate;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement listedOrgName;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getListedLastName() {
		return listedLastName;
	}
	
	public WebElement getListedSuppStartDate() {
		return listedSuppStartDate;
	}

	public WebElement getListedSuppEndDate() {
		return listedSuppEndDate;
	}
	
	public WebElement getListedOrgName() {
		return listedOrgName;
	}
}
