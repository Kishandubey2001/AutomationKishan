package com.sms.AutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Slider {
	@Test
	public void sliderhandle() throws InterruptedException
	{
	WebDriver driver=	new ChromeDriver();
	driver.get("https://testautomationpractice.blogspot.com/");
	driver.manage().window().maximize();
WebElement xpaths=   driver.findElement(By.xpath("(//span[@class='ui-slider-handle ui-corner-all ui-state-default'])[2]"));
 xpaths.click();
Thread.sleep(5000);
	 Actions acobj= new Actions(driver);
	
	 for(int i=1; i<=50; i++)
	 {
		 acobj.sendKeys(Keys.ARROW_RIGHT).build().perform(); 
	 }
	 //acobj.scrollToElement(xpaths).build().perform();
//	 acobj.dragAndDropBy(xpaths, 50, 0).perform(); // Move right by 50 pixels
//     Thread.sleep(2000);
     
    // acobj.dragAndDropBy(xpaths, -30, 0).perform(); 
	 
	}

}
