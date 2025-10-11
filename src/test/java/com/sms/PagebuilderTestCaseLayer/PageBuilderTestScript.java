package com.sms.PagebuilderTestCaseLayer;

import org.testng.annotations.Test;

import com.sms.commonCodes.CommonResuableCode;
import com.sms.testScript.SuiteLableBaseClass;

import page_Builder_Module.ActionComponentPagewise;
import page_Builder_Module.NavigationComponentPagewise;
import page_Builder_Module.ValidationComponentPagewise;

public class PageBuilderTestScript extends SuiteLableBaseClass{
	private NavigationComponentPagewise ncp;
	private CommonResuableCode cd;
	private ActionComponentPagewise acp;
	ValidationComponentPagewise  vcp;
	@Test(priority = 1)
	public void verifydefaultPropertyofTextfieldcomponent() throws InterruptedException
	{
		
		cd=	new CommonResuableCode(kw);
		acp=	new ActionComponentPagewise(kw);
		ncp=  new NavigationComponentPagewise(kw);
		 vcp= 	new ValidationComponentPagewise(kw);
		Thread.sleep(2000);
		cd.navigateToSystemSetting();
		cd.navigateToPageBuilder();
		acp.action_createButton();
		ncp.drag_dropTextField_component();
		//Thread.sleep(2000);
		acp.action_textfieldComponent();
	    vcp.verifydefaulttextfieldlabelname();
	    vcp.verifyblankhelpertext();
	    vcp.verifysearchsourcetoggleof();
	    vcp.verifysearchsourcetoggleEnabled();
	    acp.getaction_okbutton();
		
	}
	
	public void verifydefaultPropertyofDropdowncomponent() throws InterruptedException
	{
	ncp.drag_dropDropdown_component();
	acp.action_dropdownComponent();

	}
	
	

}
