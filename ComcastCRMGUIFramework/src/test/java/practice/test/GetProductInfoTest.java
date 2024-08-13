package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileUtility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider="getData", dataProviderClass=ProductList.class)
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		
		String xpathExpression = "//span[text()='"+productName+"']/ancestor::div[contains(@class,'a-spacing-top')]//span[@class='a-price-whole']";
		String price = driver.findElement(By.xpath(xpathExpression)).getText();
		System.out.println(productName+":  Rs."+price);
		driver.quit();	
	}
	
	//@DataProvider
	public Object[][] getData() throws IOException {

		ExcelUtility elib = new ExcelUtility();
		int rowCount = elib.getRowCount("product");

		// Create a 2D array
		Object[][] objArr = new Object[rowCount][2];
		for (int i = 0; i < rowCount; i++) {
			objArr[i][0] = elib.getDataFromExcel("product", i + 1, 0);
			objArr[i][1] = elib.getDataFromExcel("product", i + 1, 1);
		}
		return objArr;
	}
}
