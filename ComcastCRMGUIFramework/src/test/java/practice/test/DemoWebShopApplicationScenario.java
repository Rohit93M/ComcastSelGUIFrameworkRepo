package practice.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class DemoWebShopApplicationScenario {

	@Test
	public void workWithDemoWebShopApplication() throws IOException {

		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("autofill.profile_enabled", false);
		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();

		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("gender-male")).click();

		FileInputStream fis = new FileInputStream("C:\\Users\\ROHIT M\\OneDrive\\Desktop\\New folder\\Book1.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("Sheet1");

		driver.findElement(By.id("FirstName")).sendKeys(sheet.getRow(0).getCell(0).getStringCellValue());
		driver.findElement(By.id("LastName")).sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
		driver.findElement(By.id("Email")).sendKeys(sheet.getRow(2).getCell(0).getStringCellValue());
		driver.findElement(By.id("Password")).sendKeys(sheet.getRow(3).getCell(0).getStringCellValue());
		driver.findElement(By.id("ConfirmPassword")).sendKeys(sheet.getRow(4).getCell(0).getStringCellValue());
		driver.findElement(By.id("register-button")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/image1.png");
		FileHandler.copy(src, dest);

		driver.findElement(By.linkText("$25 Virtual Gift Card")).click();

		driver.findElement(By.id("giftcard_2_RecipientName")).sendKeys(sheet.getRow(5).getCell(0).getStringCellValue());
		driver.findElement(By.id("giftcard_2_RecipientEmail"))
				.sendKeys(sheet.getRow(6).getCell(0).getStringCellValue());
		driver.findElement(By.id("add-to-cart-button-2")).click();

		driver.findElement(By.linkText("Shopping cart")).click();

		driver.findElement(By.name("termsofservice")).click();
		driver.findElement(By.id("checkout")).click();

		Select countrySelect = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
		countrySelect.selectByVisibleText(sheet.getRow(7).getCell(0).getStringCellValue());
		driver.findElement(By.id("BillingNewAddress_City")).sendKeys(sheet.getRow(8).getCell(0).getStringCellValue());
		driver.findElement(By.id("BillingNewAddress_Address1"))
				.sendKeys(sheet.getRow(9).getCell(0).getStringCellValue());
		driver.findElement(By.id("BillingNewAddress_ZipPostalCode"))
				.sendKeys(sheet.getRow(10).getCell(0).getStringCellValue());
		driver.findElement(By.id("BillingNewAddress_PhoneNumber"))
				.sendKeys(sheet.getRow(11).getCell(0).getStringCellValue());
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();

		File src1 = ts.getScreenshotAs(OutputType.FILE);
		File dest1 = new File("./screenshots/image2.png");
		FileHandler.copy(src1, dest1);

		driver.quit();
	}

}
