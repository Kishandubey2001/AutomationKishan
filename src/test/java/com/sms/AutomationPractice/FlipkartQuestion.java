package com.sms.AutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class FlipkartQuestion {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		WebElement searchmobile=	driver.findElement(By.xpath("//input[@name=\"q\"]"));
		ITesseract instance = new Tesseract();
		

		Actions acobj=  new Actions(driver);
		acobj.sendKeys(searchmobile, "Iphone").sendKeys(Keys.ENTER).build().perform();
		java.util.List<WebElement> listofmobile=    driver.findElements(By.xpath("//div[contains(@class,'Nx')]"));
		for(int i=0; i<listofmobile.size(); i++)
		{
			WebElement getlistmobile=	 listofmobile.get(i);
			String realtext=	getlistmobile.getText();
		String	 priceTextnew = realtext.replaceAll("[^0-9]", ""); 
		int integertext=	Integer.parseInt(priceTextnew);
		  if(integertext<70000)
		  {
		System.out.println(priceTextnew);  
		  }
		}




	}

}
