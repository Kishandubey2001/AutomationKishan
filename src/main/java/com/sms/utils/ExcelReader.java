package com.sms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader {
	
	public static Map<String, String> readExcelData(String filePath, String sheetName) {
        Map<String, String> dataMap = new HashMap<>();
        FileInputStream fileInputStream = null;

        try {
            // Load the Excel file
            fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            // Get the desired sheet
            Sheet sheet = workbook.getSheet(sheetName);       

            // Iterate through rows (Assuming first column as key, second column as value)
            for (Row row : sheet) {
                Cell keyCell = row.getCell(0);   // First column
                Cell valueCell = row.getCell(1); // Second column

                if (keyCell != null && valueCell != null) {
                    String key = keyCell.getStringCellValue();
                    String value = valueCell.getStringCellValue();

                    dataMap.put(key, value);
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }


	
}
