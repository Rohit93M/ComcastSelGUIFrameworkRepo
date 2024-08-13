package com.comcast.crm.baseclass;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ISuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseUtility.DataBaseUtility;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.UtilityClassObject;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

/**
 * This class consists of all the basic configuration annotations for 
 * all the common actions
 * @author Rohit
 **/

public class BaseClass {

	//Create Object
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	public DataBaseUtility dblib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws SQLException {
		System.out.println("Connect to DB, Report Config");
		dblib.getDbConnection();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws SQLException {
		System.out.println("Close DB, Report Backup");
		dblib.closeDbConnection();
	}

	//@Parameters("browser")
	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	public void configBC() throws IOException {
		System.out.println("Launch the browser");
		String BROWSER = flib.getDataFromPropertiesFile("browser");
	//	String BROWSER = browser;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		// This decision of initialization is taken at runtime
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAC() {
		System.out.println("Close the browser");
		driver.quit();
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws IOException {
		System.out.println("Login to application");
		LoginPage lp = new LoginPage(driver);
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");
		lp.loginToApp(url, username, password);
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("Logout from application");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
}
