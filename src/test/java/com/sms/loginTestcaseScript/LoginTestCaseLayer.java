package com.sms.loginTestcaseScript;

import org.testng.annotations.Test;

import com.sms.Login.LoginPageWise;
import com.sms.commonCodes.CommonResuableCode;
import com.sms.testScript.Baseclasswithoutlogin;

public class LoginTestCaseLayer extends Baseclasswithoutlogin{
	private CommonResuableCode common;
	private LoginPageWise lg;
	
	@Test
	public void verifylogwithBlankemail_password()
	{
		lg=new LoginPageWise(kw);
		lg.loginwithblankemail_password();
		
	}
	
	

}
