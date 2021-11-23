package com.technocredits.orghm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

	static final public Object[][] getData(String filePath, String sheetName) throws IOException {
		File file = new File(filePath);
		FileInputStream input = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(input);

		Sheet sheet = workbook.getSheet(sheetName);

		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[totalRows][totalCols];
		for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalCols; colIndex++) {
				Cell cell = sheet.getRow(rowIndex + 1).getCell(colIndex);
				if (CellType.NUMERIC == cell.getCellType())
					data[rowIndex][colIndex] = String.valueOf(cell.getNumericCellValue());
				else if (CellType.STRING == cell.getCellType())
					data[rowIndex][colIndex] = cell.getStringCellValue();
				else if (CellType.BOOLEAN == cell.getCellType())
					data[rowIndex][colIndex] = String.valueOf(cell.getBooleanCellValue());
			}
		}
		return data;
	}
}
