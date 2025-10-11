package com.sms.AutomationPractice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class HandleCaptcha {
	public static void main(String[] args) throws IOException, TesseractException {
	   WebDriver driver=  new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.get("https://uat-sms.geduservices.com/login");
	// Take full page screenshot
	   File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   BufferedImage fullImg = ImageIO.read(screenshot);

	   // Locate captcha element
	   WebElement captcha = driver.findElement(By.xpath("//canvas[@id='canv']"));
	   Point point = captcha.getLocation();
	   int eleWidth = captcha.getSize().getWidth();
	   int eleHeight = captcha.getSize().getHeight();

	   // Crop captcha image
	   BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);

	   // Save cropped captcha
	   //File captchaFile = new File("C:\\Users\\KishanKumarDubey\\captcha.png");
//	   ImageIO.write(eleScreenshot, "png", captchaFile);
//
//	   // OCR with Tesseract
//	   ITesseract instance = new Tesseract();
//	   instance.setDatapath("C:\\Users\\KishanKumarDubey\\AppData\\Local\\Programs\\Tesseract-OCR");
//	   instance.setLanguage("eng");
//
//	   // Pass only cropped captcha
//	   String result = instance.doOCR(captchaFile);
//	   System.out.println("Captcha Text: " + result);
//	   
	   
	   JavascriptExecutor js = (JavascriptExecutor) driver;
	   String base64Image = (String) js.executeScript(
	       "var canvas = document.getElementById('canv');" +
	       "return canvas.toDataURL('image/png').substring(22);"
	   );

	   // Convert Base64 to File
	   byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
	   File captchaFile = new File("C:\\Users\\KishanKumarDubey\\canvasCaptcha.png");
	   try (java.io.FileOutputStream fos = new java.io.FileOutputStream(captchaFile)) {
	       fos.write(imageBytes);
	   }

	   // OCR with Tesseract
	   ITesseract instance = new Tesseract();
	   instance.setDatapath("C:\\Users\\KishanKumarDubey\\AppData\\Local\\Programs\\Tesseract-OCR");
	   instance.setLanguage("eng");

	   String result = instance.doOCR(captchaFile);
	   System.out.println("Captcha Text: " + result);

	// Fill captcha
	//driver.findElement(By.id("captchaTextBox")).sendKeys(result);
	
//	
//File  source=	element.getScreenshotAs(OutputType.FILE);
//Files.copy(source, new File("C:\\Users\\KishanKumarDubey\\Downloads\\Kishan_Project (2)\\Kishan_Project\\Report.png"));



	}

}
