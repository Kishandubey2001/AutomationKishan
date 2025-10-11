package com.sms.AutomationPractice;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import com.sms.testScript.Baseclasswithoutlogin;
import com.sms.utils.WebUtil;

import importExport.ImportExportPageWise;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyfileDownload extends Baseclasswithoutlogin{
	//protected WebUtil kw=	WebUtil.getobject();

    @Test
	public void verifyfile() throws InterruptedException, AWTException, IOException
	{
    	DownloadPagewise dd=	new DownloadPagewise(kw);
    	dd.downloadfile();
//    	ImportExportPageWise im= 	new ImportExportPageWise(kw);
//    	im.importexport();
    	
    	
    	
//    	WebElement downloadButton = driver.findElement(By.xpath("//a[text()='ZIP file']"));
//	 kw.downloadFile("C:\\Users\\KishanKumarDubey\\Downloads", "DownloadDemo-master.zip", downloadButton);	
	}
		
		// Set download path
//		String downloadFilepath = "C:\\Users\\KishanKumarDubey\\Downloads"; // change as needed
//		String expectedFileName = "DownloadDemo-master.zip"; // change to your file name
//
//		// Set Chrome options
//		HashMap<String, Object> chromePrefs = new HashMap<>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", downloadFilepath);
//
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", chromePrefs);
//
//		// Launch browser
//		WebDriver driver = new ChromeDriver(options);
//		driver.get("http://omayo.blogspot.com/p/page7.html"); // replace with your actual URL
//
//		// Click on download button
//		//driver.findElement(By.id("downloadExcelBtn")).click();
//		WebElement downloadButton = driver.findElement(By.xpath("//a[text()='ZIP file']"));
//		downloadButton.click();
//
//		// Wait for file to download
//		Thread.sleep(5000); // You can improve this with dynamic wait
//
//		// Check if file is downloaded
//		if (isFileDownloaded(downloadFilepath, expectedFileName)) {
//			System.out.println("File downloaded successfully.");
//		} else {
//			System.out.println("File not downloaded.");
//		}
//
//		driver.quit();
//
//	}
//
//	public static boolean isFileDownloaded(String downloadPath, String fileName) {
//		// File dir = new File(downloadPath);
//		java.io.File dir=           new java.io.File(downloadPath);
//		java.io.File[] dirContents  = dir.listFiles();
//		//File[] dirContents = dir.listFiles();
//
//		if (dirContents != null) {
//			for (java.io.File file : dirContents) {
//				if (file.getName().equals(fileName)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}


}
