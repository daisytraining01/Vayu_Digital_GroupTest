package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utility_Vimal {


	public static Object[][] getTestData() throws IOException {

		// used to create connection to any kind of file:

		String fileName = "EMI_Data.xlsx";
		File file = new File(System.getProperty("user.dir") + "/Database/" + fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbookObj = new XSSFWorkbook(inputStream);
		

		// Read sheet inside the workbook by its name
		Sheet sheetObj = workbookObj.getSheet("Vimal");

		Object data[][] = new Object[sheetObj.getLastRowNum()][sheetObj.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheetObj.getLastRowNum(); i++) {
			for (int k = 0; k < sheetObj.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheetObj.getRow(i + 1).getCell(k).toString();

			}

		}
		workbookObj.close();
		return data;
	}
}
