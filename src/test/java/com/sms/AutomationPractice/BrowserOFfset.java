package com.sms.AutomationPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.graphbuilder.curve.Point;

public class BrowserOFfset {
	@Test
	public void m1()
	{
		WebDriver driver = new ChromeDriver();

        // Open any website
        driver.get("https://www.google.com");

        // Get the position (offset) of the browser window
        org.openqa.selenium.Point position = driver.manage().window().getPosition();

        // Extract x and y offsets
        int xOffset = position.getX();
        int yOffset = position.getY();

        // Print the values
        System.out.println("Browser xOffset: " + xOffset);
        System.out.println("Browser yOffset: " + yOffset);
	}

}
