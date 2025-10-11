package com.sms.AutomationPractice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.openqa.selenium.Point;

public class DatePickerTesting {
	public static WebDriver driver;
	
	@Test
	public void dateTesting() throws InterruptedException, AWTException
	{
	 driver=	new ChromeDriver();
	 driver.manage().window().maximize();
	
	driver.get("https://portal-qa.eduapply.io/login/");
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sdehuri@geduservices.com");
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@1234");
	driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//p[text()='Settings']/parent::div/parent::a/parent::div[contains(@class,'MuiButtonBase-root MuiListItemButton-root MuiListItemButton-gutters MuiListItemButton-root MuiListItemButton-gutters mui-style')]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//p[text()='Programs']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("(//button[@aria-label=\"Edit Program\"])[1]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[text()='Program Intake']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[text()='Add Program Intake ']")).click();
	Thread.sleep(2000);
	WebElement  element=driver.findElement(By.xpath("//input[@name='applicationStartDate']"));
	
	clickDatepicker(element, 527, 150,Keys.ARROW_DOWN,40);
	
WebElement  element1=driver.findElement(By.xpath("//input[@name='applicationDeadline']"));
	
	clickDatepicker(element1, 527, 150,Keys.ARROW_DOWN,41);
	
WebElement  element2=driver.findElement(By.xpath("//input[@name='programStartDate']"));
	
	clickDatepicker(element2, 527, 150,Keys.ARROW_DOWN,42);
	
	
	
//     Actions acobj=new Actions(driver);
//     pressKeyMultipleTimes(driver, Keys.ARROW_DOWN, 40);
//    acobj.sendKeys(Keys.ENTER).build().perform();
     
     
//     int times = 10;
//
//     Actions actions = new Actions(driver);
//     for (int i = 0; i < times; i++) {
//         actions.sendKeys(Keys.ARROW_DOWN).perform();
//        // actions.sendKeys(Keys.ENTER).perform();
//     }
     
     
	
	}
	
	public static void clickDatepicker(WebElement element,int xOffset,  int yOffset, CharSequence key, int times) throws AWTException
	{
		//WebElement  element=driver.findElement(By.xpath("//input[@name='programStartDate']"));
		
		
		
	     // Get location of the element
	     org.openqa.selenium.Point location = element.getLocation();
	     int elementX = location.getX();
	     int elementY = location.getY();

	     // Add browser window/frame offsets to calculate screen coordinates
//	     int xOffset = 525;  // Adjust as needed
//	     int yOffset = 145; // Typical top offset for Chrome/Windows

	     int screenX = elementX + xOffset;
	     int screenY = elementY + yOffset;

	     // Use Robot to click on Y location
	     Robot robot = new Robot();
	     robot.mouseMove(screenX, screenY);
	     robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	     robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	     Actions actions = new Actions(driver);
	        for (int i = 0; i < times; i++) {
	            actions.sendKeys(key).perform();
	}
	        actions.sendKeys(Keys.ENTER).build().perform();
	}
	
	public static void pressKeyMultipleTimes(WebDriver driver, CharSequence key, int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(key).perform();
            //actions.sendKeys(Keys.ENTER).perform();
            
        }
    }

}
