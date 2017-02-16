package com.megasoftworks.gl.poi;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class XlsManager {
static Logger logger = Logger.getLogger(XlsManager.class);
	
	public static String readXlsxFile(FileInputStream file, String separator) {
		StringBuilder sb = new StringBuilder();
		try {
		    POIFSFileSystem fs = new POIFSFileSystem(file);
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
		    Iterator<Row> rowIterator = sheet.iterator();
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
			wb.close();
		    
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
		return sb.toString();
	}

}
