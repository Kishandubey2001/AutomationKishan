package com.sms.Login;

import com.sms.commonCodes.CommonResuableCode;
import com.sms.utils.ConfigFile;
import com.sms.utils.WebUtil;

public class LoginPageWise extends LoginOrlayer{
	private CommonResuableCode cd;
	private ConfigFile config;
	private WebUtil wu;


	public LoginPageWise(WebUtil wt) {
		super(wt);
		this.wu=wt;
	}

	public void loginwithblankemail_password()
	{
		wu.myclick(getloginBT(), "loginbutton");
		config=  new ConfigFile("src\\main\\resources\\propertiesFiles\\loginItems.properties");
		String emailblankerrormessage=	  wu.mygetText(getemailblankerrormessage(), "emailblankerrormessage");
		wu.hardassertwithstring(emailblankerrormessage, config.getProperty("blankemailErrormessage"));
		String passworderrormessage=	wu.mygetText(getpasswordblankerrormessage(), "passworderrormessage");
		wu.hardassertwithstring(passworderrormessage, config.getProperty("blankpasswordErrormessage"));

	}
	
	public void loginwithblankemail_and_valid_passowrd()
	{
		wu.mySendkeys(getpasswordTB(), "Gedu@12345", "Password");
		wu.myclick(getloginBT(), "Login button");
		config=  new ConfigFile("src\\main\\resources\\propertiesFiles\\loginItems.properties");
		String emailblankerrormessage=	  wu.mygetText(getemailblankerrormessage(), "emailblankerrormessage");
		wu.hardassertwithstring(emailblankerrormessage, config.getProperty("blankemailErrormessage"));
		
	}
	
	public void loginwithValidemail_blankPassword()
	{
		wu.mySendkeys(getemailTB(), "kkdubey@geduservices.com", "Email");
		wu.myclick(getloginBT(), "Login Button ");
		config=  new ConfigFile("src\\main\\resources\\propertiesFiles\\loginItems.properties");

		
	}
	

}
