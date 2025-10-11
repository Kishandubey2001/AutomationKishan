package com.sms.testScript;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public XSSFWorkbook workbook;
	private XSSFSheet sheet;
//	private XSSFRow row;
	private XSSFCell cell;


	public static Map<String, String> readTestData(String filePath, String sheetName, String testCaseName) {
        Map<String, String> testData = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            int testCaseRow = -1;
            Row headerRow = sheet.getRow(0);

            for (int row = 1; row < rowCount; row++) {
                Row currentRow = sheet.getRow(row);
                Cell testCaseCell = currentRow.getCell(0);

                if (testCaseCell.getStringCellValue().equals(testCaseName)) {
                    testCaseRow = row;
                    break;
                }
            }

            if (testCaseRow != -1) {
                for (int col = 0; col < colCount; col++) {
                    Cell headerCell = headerRow.getCell(col);
                    Cell dataCell = sheet.getRow(testCaseRow).getCell(col);

                    testData.put(headerCell.getStringCellValue(), dataCell.getStringCellValue());
                }
            } else {
                System.out.println("Test case not found in the Excel sheet.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return testData;
	}
	
	public void setExcelFile(File file) throws IOException {
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);

		// creating workbook instance that refers to .xls file
		workbook = new XSSFWorkbook(inputStream);

		// creating a Sheet object
//         sheet=workbook.getSheet(sheetName);
	}


	
	public String getUserDetails(String userType, String dataType) {
		sheet = workbook.getSheet("UserDetails");
		int cellNum = 0;
		if (dataType.equalsIgnoreCase("FullName")) {
			cellNum = 1;
		} else if (dataType.equalsIgnoreCase("FirstName")) {
			cellNum = 2;
		} else if (dataType.equalsIgnoreCase("LastName")) {
			cellNum = 3;
		} else if (dataType.equalsIgnoreCase("EMailID")) {
			cellNum = 4;
		} else if (dataType.equalsIgnoreCase("Password")) {
			cellNum = 5;
		} else if (dataType.equalsIgnoreCase("MobileNo")) {
			cellNum = 6;
		} else if (dataType.equalsIgnoreCase("CompanyName")) {
			cellNum = 7;
		} else if (dataType.equalsIgnoreCase("EMailPassword")) {
			cellNum = 8;
		}
		int totalRowNum = sheet.getLastRowNum() + 1;
		String value = "";
		for (int i = 1; i < totalRowNum; i++) {
			DataFormatter formatter = new DataFormatter();
			String userTypeCellData = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			if (userType.contains(userTypeCellData)) {
				value = formatter.formatCellValue(sheet.getRow(i).getCell(cellNum));
				break;
			}
		}

		return value;
	}

//  Get Toast Text
	public String getToast(String tostType) {
		sheet = workbook.getSheet("Registration");
		int cellNum = 1;
		int totalRowNum = sheet.getLastRowNum() + 1;
		String value = "";
		for (int i = 1; i < totalRowNum; i++) {
			String userTypeCellData = sheet.getRow(i).getCell(0).getStringCellValue();
			if (tostType.contains(userTypeCellData)) {
				value = sheet.getRow(i).getCell(cellNum).getStringCellValue();
				break;
			}
		}

		return value;
	}

//Get Toast Text
	public String getCardDetails(String userType, String dataType) {
		sheet = workbook.getSheet("CardDetails");
		int cellNum = 0;
		if (dataType.equalsIgnoreCase("Prefix")) {
			cellNum = 1;
		} else if (dataType.equalsIgnoreCase("FirstName")) {
			cellNum = 2;
		} else if (dataType.equalsIgnoreCase("LastName")) {
			cellNum = 3;
		} else if (dataType.equalsIgnoreCase("MobileNo1")) {
			cellNum = 4;
		} else if (dataType.equalsIgnoreCase("MobileNo2")) {
			cellNum = 5;
		} else if (dataType.equalsIgnoreCase("MobileNo3")) {
			cellNum = 6;
		} else if (dataType.equalsIgnoreCase("EMailID1")) {
			cellNum = 7;
		} else if (dataType.equalsIgnoreCase("EMailID2")) {
			cellNum = 8;
		} else if (dataType.equalsIgnoreCase("EMailID3")) {
			cellNum = 9;
		} else if (dataType.equalsIgnoreCase("TelephoneNo1")) {
			cellNum = 10;
		} else if (dataType.equalsIgnoreCase("TelephoneNo2")) {
			cellNum = 11;
		} else if (dataType.equalsIgnoreCase("TelephoneNo3")) {
			cellNum = 12;
		} else if (dataType.equalsIgnoreCase("CompanyName")) {
			cellNum = 13;
		} else if (dataType.equalsIgnoreCase("Designation")) {
			cellNum = 14;
		} else if (dataType.equalsIgnoreCase("Address")) {
			cellNum = 15;
		} else if (dataType.equalsIgnoreCase("PinCode")) {
			cellNum = 16;
		} else if (dataType.equalsIgnoreCase("WebSite")) {
			cellNum = 17;
		} else if (dataType.equalsIgnoreCase("SocialMedia1")) {
			cellNum = 18;
		} else if (dataType.equalsIgnoreCase("SocialMedia2")) {
			cellNum = 19;
		} else if (dataType.equalsIgnoreCase("SocialMedia3")) {
			cellNum = 20;
		} else if (dataType.equalsIgnoreCase("SocialMedia4")) {
			cellNum = 21;
		} else if (dataType.equalsIgnoreCase("SocialMedia5")) {
			cellNum = 22;
		} else if (dataType.equalsIgnoreCase("SocialMedia6")) {
			cellNum = 23;
		} else if (dataType.equalsIgnoreCase("Profession")) {
			cellNum = 24;
		} else if (dataType.equalsIgnoreCase("Industry")) {
			cellNum = 25;
		} else if (dataType.equalsIgnoreCase("Experience")) {
			cellNum = 26;
		} else if (dataType.equalsIgnoreCase("BusinessSummary")) {
			cellNum = 27;
		}
		int totalRowNum = sheet.getLastRowNum() + 1;
		String value = "";
		for (int i = 1; i < totalRowNum; i++) {
			DataFormatter formatter = new DataFormatter();
			String userTypeCellData = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			if (userType.contains(userTypeCellData)) {
				value = formatter.formatCellValue(sheet.getRow(i).getCell(cellNum));
				break;
			}
		}

		return value;
	}
	
	
//  Get Test Data Text
	public String getTestData(String dataType) {
		sheet = workbook.getSheet("TestData");
		int cellNum = 1;
		int totalRowNum = sheet.getLastRowNum() + 1;
		String value = "";
		for (int i = 1; i < totalRowNum; i++) {
			DataFormatter formatter = new DataFormatter();
			String userTypeCellData = sheet.getRow(i).getCell(0).getStringCellValue();
			if (dataType.contains(userTypeCellData)) {
//				value = sheet.getRow(i).getCell(cellNum).getStringCellValue();
				value = formatter.formatCellValue(sheet.getRow(i).getCell(cellNum));
				break;
			}
		}

		return value;
	}

	
	
	
	
	
}
