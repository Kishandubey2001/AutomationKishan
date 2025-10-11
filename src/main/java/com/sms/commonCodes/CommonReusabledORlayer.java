package com.sms.commonCodes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class CommonReusabledORlayer {

	public CommonReusabledORlayer(WebUtil kw)
	{
		PageFactory.initElements(kw.getDriver(), this);
	}

	
	
	//  ====    ======= Login  ==================

	@FindBy(xpath = "//input[@id='userName']")
	private WebElement emailTB;
	public WebElement getemailTB()
	{
		return emailTB;
	}

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordTB;
	public WebElement getpasswordTB()
	{
		return passwordTB;
	}

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginBT;
	public WebElement getloginBT()
	{
		return loginBT;
	}
	
     
     @FindBy(xpath = "//div[text()='Login successfully.']")
     private WebElement successfullloginmessage;
     public WebElement getsuccessfullloginmessage()
     {
    	 return successfullloginmessage;
     }
     
     
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeLarge css-156xpzl']")
	private WebElement profileBT;
	public WebElement getprofileBT()
	{
		return profileBT;
	}
	
	//  ====  Logout or items 

	@FindBy(xpath = "//p[text()='Log Out']")
	private WebElement logoutBT;
	public WebElement getlogoutBT()
	{
		return logoutBT;
	}
	
	@FindBy(xpath = "//div[text()='Logout successfully.']")
	private WebElement logoutSuccessmessage;
	public WebElement getlogoutSuccessmessage()
	{
		return logoutSuccessmessage;
	}

     @FindBy(xpath = "//p[text()='System Settings']")
     private WebElement sytemsettingSection;
     public WebElement getsytemsettingSection()
     {
    	 return sytemsettingSection;
     }
     @FindBy(xpath = "//span[text()='Page Builder']")
     private WebElement pagebuilderSection;
     public WebElement getpagebuildersection()
     {
    	 return pagebuilderSection;
     }
    
    
     
    
     
     
	 







}
