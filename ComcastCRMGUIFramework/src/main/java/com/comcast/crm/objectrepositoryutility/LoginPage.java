package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

/**
 * 
 *  This class contains login page elements and business library like login()
 *  @author ROHIT M
 */

public class LoginPage extends WebDriverUtility {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		/*
		 * Storing the browser session driver object(which is here in a local variable) 
		 * into the global variable so that you will be able to access the driver object 
		 * inside other methods, since local variable and global variable are having same 
		 * name, in order to avoid confusion this keyword is being used inside the constructor
		 */
		PageFactory.initElements(driver, this);
		/*
		 * Here this means current pom page class object reference, and you have to specify
		 * which pom class you have to initialize along with in which browser you are going
		 * to initialize elements 
		 */	
		}
	
	// Rule 1: Create a separate java class
	// Rule 2: Object Creation	
	// Rule 3: Object Initialization: should be done inside constructor
    // Rule 4: Object Encapsulation
	
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	

	
	/**
	 * Business Library - project specific generic method.
	 * This method will perform Login operation
	 * @param url
	 * @param username
	 * @param password
	 */
	
	public void loginToApp(String url, String username, String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		maximizeWindow(driver);
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}
		
}
