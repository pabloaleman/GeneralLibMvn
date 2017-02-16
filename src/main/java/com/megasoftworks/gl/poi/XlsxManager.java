package com.megasoftworks.gl.poi;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxManager {
	static Logger logger = Logger.getLogger(XlsxManager.class);
	
	public static String readXlsxFile(FileInputStream file, String separator) {
		StringBuilder sb = new StringBuilder();
		
		try {
			XSSFWorkbook myWorkBook = new XSSFWorkbook(file);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = mySheet.iterator();
			// Traversing over each row of XLSX file
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							sb.append(cell.getStringCellValue());
							sb.append(separator);
							logger.debug(cell.getStringCellValue() + "\t");
							break;
						case Cell.CELL_TYPE_NUMERIC:
							String value = cell.getNumericCellValue() + "";
							value = value.replace(",", ".");
							sb.append(value);
							sb.append(separator);
							logger.debug(cell.getNumericCellValue() + "\t");
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							boolean valueB = cell.getBooleanCellValue();
							String valueS = valueB ? "true" : "false";
							sb.append(valueS);
							sb.append(separator);
							logger.debug(cell.getBooleanCellValue() + "\t");
							break;
						default : 
					}
				}
				logger.debug("");
				sb.append("\n");
			}
			myWorkBook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sb.toString(); 
	}

}
