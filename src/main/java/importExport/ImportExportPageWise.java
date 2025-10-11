package importExport;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sms.utils.WebUtil;

public class ImportExportPageWise extends ImportExportOR{
   private WebUtil wu;
	public ImportExportPageWise(WebUtil wt) {
		super(wt);
		this.wu=wt;
		
	}
	
	public void importexport() throws InterruptedException, AWTException, IOException
	{
		wu.mySendkeys(getemailTB(), "kkdubey@geduservices.com", "email Text box");
		wu.mySendkeys(getpasswordTB(), "Gedu@12345", "password Text box");
		wu.myclick(getloginBT(), "Login button");
		//wu.zoominout("50%");
		Thread.sleep(5000);
	//	wu.myclick(getclickstudentdetailsLK(), "Student details section");
		wu.myclick(getusermanagementSection(), "UsermanagementSection");
		Thread.sleep(2000);
		wu.myclick(getdepartmentmaster(), "Department Master");
		Thread.sleep(5000);
		
		List<String> headers = new ArrayList<>();
		headers.add("SR.No");
		headers.add("Department Name");
		headers.add("Department Code");
		headers.add("Actions");
//		headers.add("Student ID");
//		headers.add("GBS Course");
		 
		wu.writeDataInExcel(headers,"kishan.xlsx","DepartmentMaster","DepartmentMaster");
//		wu.myclick(getclickimportBT(), "Import");
//		Thread.sleep(4000);
//		wu.myclick(getfileuploadBT(), "uploadbt");
//		wu.fileuploadusingrobotclass("C:\\Users\\KishanKumarDubey\\Downloads\\Student records.csv");
		//wu.fileupload(getfileuploadBT(), "C:\\Users\\KishanKumarDubey\\Downloads\\Student records.csv");
		//wu.mySenkeys(getfileuploadBT(), "C:\\Users\\KishanKumarDubey\\Downloads\\Student records.csv", "file upload");
//		wu.myclick(getexportBT(), "Export button");
//		Thread.sleep(5000);
//		wu.myclick(getclickonDownloadBT(), "Click on downlaod button");
//	boolean isdownloaded=	wu.downloadFile("C:\\Users\\KishanKumarDubey\\Downloads", "Student records.csv", "export download");
//	  if(isdownloaded)
//	  {
//		  System.out.println("file download successfully");
//	  }else
//	  {
//		  System.out.println("file  not downloaded");
//	  }
	}
	
	
	

}
