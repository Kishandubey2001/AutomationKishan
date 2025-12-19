package com.sms.utils;

import java.util.Map;

public class ExcelReaderClass {
	
  public static void main(String[] args) {
	  
	  
	  ExcelReader  excel=    new ExcelReader();
	Map<String, String> excelvalue= excel.readExcelData("C:\\Users\\KishanKumarDubey\\Downloads\\Gedu.xlsx", "Kishan") ;
String namevalue=	excelvalue.get("Name");
System.out.println(namevalue);
 String companyname=   excelvalue.get("Company name");
 System.out.println(companyname);
}

}
