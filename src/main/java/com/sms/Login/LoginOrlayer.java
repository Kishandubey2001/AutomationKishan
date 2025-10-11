package com.sms.Login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class LoginOrlayer {
	

	public LoginOrlayer(WebUtil wt)
	{
		PageFactory.initElements(wt.getDriver(), this);
	}
	

	 @FindBy(xpath = "//div[text()='Username or password does not exists. Please try again.']")
    private WebElement logininvalidCredentailserrorMessage;
    public WebElement getlogininvalidCredentailserrorMessage()
    {
   	 return logininvalidCredentailserrorMessage;
    }
    
    @FindBy(xpath = "//p[text()='Email is required.']")
    private WebElement emailblankerrormessage;
    public WebElement getemailblankerrormessage()
    {
   	 return emailblankerrormessage;
    }
    
    @FindBy(xpath = "//p[text()='Password is required.']")
    private WebElement passwordblankerrormessage;
    public WebElement getpasswordblankerrormessage()
    {
   	 return passwordblankerrormessage;
    }
    
    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary MuiButton-fullWidth MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary MuiButton-fullWidth css-ac1mdb']")
	private WebElement loginBT;
	public WebElement getloginBT()
	{
		return loginBT;
	}
	

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
	
}
