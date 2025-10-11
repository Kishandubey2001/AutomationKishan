package com.sms.utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataUtil {
	
	public  String getToastMessage(String filePath, String toastKey) {
        String toastMessage = null;

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Assume first sheet has data

            for (Row row : sheet) {
                Cell keyCell = row.getCell(0);  // ToastKey column
                Cell messageCell = row.getCell(1);  // ToastMessage column

                if (keyCell != null && keyCell.getCellType() == CellType.STRING) {
                    if (keyCell.getStringCellValue().equalsIgnoreCase(toastKey)) {
                        if (messageCell != null) {
                            toastMessage = messageCell.getStringCellValue();
                            break;
                        }
                    }
                }
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return toastMessage;
    }


}
