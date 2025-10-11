package com.sms.AutomationPractice;

import com.sms.utils.WebUtil;

public class DownloadPagewise extends DownloadORLayer
{
	private WebUtil wu;

	public DownloadPagewise(WebUtil wt) {
		super(wt);
		this.wu=wt;
		
	}
	
	public void downloadfile()
	{
		wu.myclick(getfilebutton(), "Click on file button");
   boolean   isdownload=		wu.downloadFile("C:\\Users\\KishanKumarDubey\\Downloads", "DownloadDemo-master.zip","downloadbutton");
     if(isdownload)
     {
    	 wu.printmethod("file downloaded successfully in system");
     }else
     {
    	 System.out.println("file not downloaded in system");
     }
	}

}
