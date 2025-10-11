package com.sms.AutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowRootHandle {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=  new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
	WebElement shadowhost=	driver.findElement(By.cssSelector("#shadow_host"));
	System.out.println("shadow host found successfully");
	
	  JavascriptExecutor js = (JavascriptExecutor) driver;
      WebElement shadowRootElement = (WebElement) js.executeScript(
              "return arguments[0].shadowRoot", shadowhost);
      
      if (shadowRootElement != null) {
          System.out.println("Shadow Root accessed.");
      
		
	}

	}
}
