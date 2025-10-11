package importExport;

import org.openqa.selenium.JavascriptExecutor;

import com.sms.utils.WebUtil;

public class DatepickerPagewise extends DatePickerOrlayer{
    private WebUtil wu;
	public DatepickerPagewise(WebUtil wt) {
		super(wt);
		this.wu=wt;
		
	}
	
	public void datepicker() throws InterruptedException
	{
		wu.mySendkeys(getemailTB(), "sdehuri@geduservices.com", "email");
		wu.mySendkeys(getpasswordTB(), "Test@1234", "Password");
		wu.myclick(getsignInBT(), "signInButton");
		wu.myclick(getsettingsBT(), "settings");
		Thread.sleep(2000);
		wu.myclick(getprogramsBT(), "programs");
		wu.myclick(geteditprograms(), "editprograms");
		Thread.sleep(2000);
		wu.myclick(getprogramintake(), "intek");
		Thread.sleep(2000);
		wu.myclick(getaddprogramintakeBT(), "hjgjgj");
		Thread.sleep(2000);
		wu.myclick(getclickdatepicker1(), "dfsf");
//		//wu.mySenkeys(getapplicationStartDate(), "01/07/2025", "jkkh");
//	JavascriptExecutor js=	wu.sendKeysbyJavascript();
//	js.executeScript("arguments[0].value='01/07/2025'; arguments[0].dispatchEvent(new Event('change'));", getapplicationStartDate());
//
//		//js.executeScript("arguments[0].value='05/03/2025';", getapplicationStartDate());
		
		
		
//		wu.myclick(getaddstudentApplicationBT(), "getstudentappButton");
//		Thread.sleep(3000);
//		wu.myclick(getaddnewstudent(), "addnewStudent");
//		wu.mySenkeys(getclickdatepicker(), "05/09/1980", "Datepicker");
		//wu.myclick(getclickdatepicker(), "datepicker");
		//wu.clickonDatePicker();
		//wu.selectDate(null, getclickdatepicker(), getaddstudentApplicationBT(), getaddnewstudent(), null);
	}

}
