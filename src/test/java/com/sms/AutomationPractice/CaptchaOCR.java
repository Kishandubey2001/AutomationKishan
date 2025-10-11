package com.sms.AutomationPractice;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.sourceforge.tess4j.Tesseract;

public class CaptchaOCR {
	
	public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://uat-sms.geduservices.com/login");

        WebElement captchaImg = driver.findElement(By.xpath("//canvas[@id='canv']"));
        File screenshot = captchaImg.getScreenshotAs(OutputType.FILE);
        BufferedImage bufferedImage = ImageIO.read(screenshot);

       //ITesseract tess = new Tesseract();
       Tesseract tess=     new Tesseract();
        tess.setDatapath("C:\\tessdata"); // path to tessdata folder
        tess.setLanguage("eng");

        String captchaText = tess.doOCR(bufferedImage).replaceAll("[^0-9]", ""); // only numbers
        System.out.println("Captcha is: " + captchaText);

        driver.findElement(By.id("captcha")).sendKeys(captchaText);
        //driver.findElement(By.id("submitBtn")).click();
    }

}
