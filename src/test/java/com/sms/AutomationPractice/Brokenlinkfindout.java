package com.sms.AutomationPractice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sms.testScript.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Brokenlinkfindout {
	
	
		
		
	public  void checkBrokenLinks(WebDriver driver) {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) {
                continue; // Skip empty links
            }
            verifyBrokenLink(url);
        }
    }

    // Method to verify if a link is broken
    public  void verifyBrokenLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setRequestMethod("HEAD"); // Faster than GET request
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 400) { // Print only broken links
                System.out.println("❌ Broken Link: " + url + " → Response Code: " + responseCode);
                
           
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error checking URL: " + url + " → " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Set up WebDriver
       // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with actual path
    	WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open webpage
        driver.get("https://testautomationpractice.blogspot.com/"); // Change to your target website

        // Check only broken links
        
        Brokenlinkfindout bro=     new Brokenlinkfindout();
        bro.checkBrokenLinks(driver);
        

        // Close browser
        driver.quit();
    }
}
