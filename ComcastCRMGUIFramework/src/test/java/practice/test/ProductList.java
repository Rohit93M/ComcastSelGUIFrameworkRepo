package practice.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.comcast.crm.generic.fileUtility.ExcelUtility;

public class ProductList {
	
	@DataProvider
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
