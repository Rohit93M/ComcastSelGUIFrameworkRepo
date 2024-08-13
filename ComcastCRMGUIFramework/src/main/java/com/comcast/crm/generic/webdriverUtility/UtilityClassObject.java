package com.comcast.crm.generic.webdriverUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	
	//This class will help to share my static variable for multiple threads in case of parallel  execution

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static ExtentTest getTest() {
		return test.get();
		// This method will give the extent report object for multiple threads
	}
	
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);	
	}
	
	public static WebDriver getDriver() {
		return driver.get();
		// This method will give the webdriver object for multiple threads
	}
	
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
		// This method will give the webdriver object for multiple threads
	}
}
