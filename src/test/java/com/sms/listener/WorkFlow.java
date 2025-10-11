package com.sms.listener;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkFlow {
	
	
	public static void main(String[] args) {
		   WebDriverManager.chromedriver().setup();
		   new ChromeDriver();
	}
	
	

}
