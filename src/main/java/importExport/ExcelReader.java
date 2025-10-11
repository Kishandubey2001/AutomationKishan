package importExport;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sms.utils.ExcelDataUtil;

public class ExcelReader {
	
	static ExcelDataUtil var;
	
	public  static String getToastMessage(String filePath, String sheetName, String toastKey) {
	    String toastMessage = null;

	    try {
	        FileInputStream fis = new FileInputStream(new File(filePath));
	        Workbook workbook = new XSSFWorkbook(fis);
	        Sheet sheet = workbook.getSheet(sheetName); // Use sheet by name

	        if (sheet != null) {
	            for (Row row : sheet) {
	                Cell keyCell = row.getCell(0);
	                Cell messageCell = row.getCell(1);

	                if (keyCell != null && keyCell.getCellType() == CellType.STRING) {
	                    if (keyCell.getStringCellValue().equalsIgnoreCase(toastKey)) {
	                        if (messageCell != null) {
	                            toastMessage = messageCell.getStringCellValue();
	                            break;
	                        }
	                    }
	                }
	            }
	        } else {
	            System.out.println("Sheet " + sheetName + " not found.");
	        }

	        workbook.close();
	        fis.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return toastMessage;
	}

    

    // Example usage
    public static void main(String[] args) {
        String path = "C:\\Users\\KishanKumarDubey\\OneDrive - GEDU Global\\Desktop\\ExcelRead.xlsx";  // Update this path to your file location
        String toastKey = "UserDeletedSuccess";
        String sheetname="Kishan";

        String message = getToastMessage(path,sheetname,toastKey);
        System.out.println("Toast Message: " + message);
    	
//    	 var=   new ExcelDataUtil();
//    String value=	 var.getToastMessage("C:\\Users\\KishanKumarDubey\\OneDrive - GEDU Global\\Desktop\\ExcelRead.xlsx","NotificationDeletedSuccess");
//     System.out.println(value);	
    }

}
