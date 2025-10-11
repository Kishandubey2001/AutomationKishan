package com.sms.AutomationPractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class DownloadORLayer {
	
	
	public DownloadORLayer(WebUtil wt)
	{
		PageFactory.initElements(wt.getDriver(), this);
	}
	
	@FindBy(xpath = "//a[text()='ZIP file']")
	private WebElement filebutton;;
	public WebElement getfilebutton()
	{
		return filebutton;
	}
	
	

}
