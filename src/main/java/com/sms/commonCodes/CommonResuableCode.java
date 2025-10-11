package com.sms.commonCodes;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sms.utils.ConfigFile;
import com.sms.utils.WebUtil;

public class CommonResuableCode extends CommonReusabledORlayer{
	
	private WebUtil wu;
	private ConfigFile configReader;
	public CommonResuableCode(WebUtil kw) {
		super(kw);
		this.wu=kw;
	}



	public void validLogin(String Username,String Password) throws InterruptedException
	{ 
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getemailTB()));
		wu.mySendkeys(getemailTB(), Username,"Email");
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getpasswordTB()));
		wu.mySendkeys(getpasswordTB(), Password, "Password");
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getloginBT()));
		wu.myclick(getloginBT(), "Login button");
		Thread.sleep(3000);
		configReader = new ConfigFile("src\\main\\resources\\propertiesFiles\\loginItems.properties");
		String actualresut=	wu.mygetText(getsuccessfullloginmessage(), "Login successfully.");
		wu.hardassertwithstring(actualresut, configReader.getProperty("loginsuccessfulmessage"));


	}




	public void logout()
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getprofileBT()));
		wu.myclick(getprofileBT(), "profile button");
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getlogoutBT()));
		wu.myclick(getlogoutBT(), "logoutButton");
		String actualmessage=	wu.mygetText(getlogoutSuccessmessage(), "LogoutSeccess message");
		configReader = new ConfigFile("src\\main\\resources\\propertiesFiles\\logoutItems.properties");
		wu.hardassertwithstring(actualmessage, configReader.getProperty("logoutsuccessfulmessage"));

	}

	public void navigateToPageBuilder()
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getpagebuildersection()));
		wu.myclick(getpagebuildersection(), "page builder section");

	}
	
	public void navigateToSystemSetting()
	{

		wu.explicitWait().until(ExpectedConditions.visibilityOf(getprofileBT()));
		wu.myclick(getprofileBT(), "profilebutton");
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getsytemsettingSection()));
		wu.myclick(getsytemsettingSection(), "System setting section");	
	}





}
