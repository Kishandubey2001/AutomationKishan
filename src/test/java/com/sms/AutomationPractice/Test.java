package com.sms.AutomationPractice;

import java.io.File;

import com.sms.testScript.SuiteLableBaseClass;

public class Test{
	
	
	@org.testng.annotations.Test
	public void m1()
	{
			
	String filepath=	getLatestFile("Downloads");
	System.out.println(filepath);
	}
	
	public static String getLatestFile(String downloadDir) {
	    File dir = new File(downloadDir);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	        if (files[i].lastModified() > lastModifiedFile.lastModified()) {
	            lastModifiedFile = files[i];
	        }
	    }
	    return lastModifiedFile.getAbsolutePath();
	}
	
	public static String filepath()
	{
		String downloadFolder = System.getProperty("user.dir") + "\\Downloads";
        String expectedFileName = "file.pdf";  // jis file ka path chahiye

        File file = new File(downloadFolder, expectedFileName);
    String filepath=    file.getAbsolutePath();
    return filepath;

//        if (file.exists()) {
//            System.out.println("Downloaded File Path: " + file.getAbsolutePath());
//        } else {
//            System.out.println("File not found in: " + downloadFolder);
//        }
	}
	
	
	
	@org.testng.annotations.Test
	public void m2()
	{
		//System.out.println("this is m2 method");
	}
	
	public void m3()
	{
		System.out.println("this is m3 method");
	}
	
	public void m4()
	{
		System.out.println("this s m4 method");
	}
	


}
