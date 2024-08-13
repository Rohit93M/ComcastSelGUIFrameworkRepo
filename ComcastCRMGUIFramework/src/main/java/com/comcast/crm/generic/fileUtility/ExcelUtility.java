package com.comcast.crm.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 *This class consists of all the reusable methods related to excel file
 * @author ROHIT M
 */

public class ExcelUtility {
	
	/**
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws IOException
	 * @author ROHIT M
	 * This method is used to retrieve data from excel
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws IOException {
		FileInputStream fis = new FileInputStream("./testData/testscriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		String data = workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
	
	/**
	 * @param sheetName
	 * @return
	 * @throws IOException
	 * @author ROHIT M
	 * This method is used to get row count of the current excel sheet
	 */
	public int getRowCount(String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream("./testData/testscriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		int rowCount = workbook.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
	
	/**
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param data
	 * @throws IOException
	 * @author ROHIT M
	 * This method is used to set data to excel
	 */
	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		FileInputStream fis = new FileInputStream("./testData/testscriptdata.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		workbook.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./configAppData/commondata.properties");
		workbook.write(fos);
		workbook.close();
	}
}
