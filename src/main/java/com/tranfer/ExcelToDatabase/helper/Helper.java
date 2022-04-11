package com.tranfer.ExcelToDatabase.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tranfer.ExcelToDatabase.model.Excel;

public class Helper {
	// check whether input file excel file or not
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	// convert excel to list
	public static List<Excel> convertExcelFileToExcelPOJO(InputStream is) {

		// List<Excel> list=new ArrayList<Excel>();
		List<Excel> excels = new ArrayList<Excel>();
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			Iterator<Row> rows = sheet.iterator();
			
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Excel excel = new Excel();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						excel.setId((long) currentCell.getNumericCellValue());
						break;
					case 1:
						excel.setFirstName(currentCell.getStringCellValue());
						break;
					case 2:
						excel.setLastName(currentCell.getStringCellValue());
						break;
					case 3:
						excel.setGender(currentCell.getStringCellValue());
						break;
					case 4:
						excel.setAge(currentCell.getRowIndex());
						break;
					case 5:
						excel.setDate(currentCell.getDateCellValue());
						break;
					default:
						break;
					}
					cellIdx++;
				}
				excels.add(excel);
			}
			workbook.close();

		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return excels;

	}

}
