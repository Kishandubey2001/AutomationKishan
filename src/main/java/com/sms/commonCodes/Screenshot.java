package com.sms.commonCodes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshot {
	
	public static void main(String[] args) throws IOException {
		 WebDriver driver=	new ChromeDriver();
		 driver.get("https://uat-sms.geduservices.com/login");
	    	TakesScreenshot ts= (TakesScreenshot)driver;
	      File source=  ts.getScreenshotAs(OutputType.FILE);
	   Path Destination=  Path.of("C:\\Users\\KishanKumarDubey\\Downloads\\Kishan_Project (2)\\Kishan_Project\\Report.login.png");
	      Files.copy(source.toPath(), Destination);
	 
	      
		
	}
	
	
}
