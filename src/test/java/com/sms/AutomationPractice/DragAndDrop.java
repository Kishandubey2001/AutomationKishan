package com.sms.AutomationPractice;

import org.testng.annotations.Test;

import com.sms.commonCodes.CommonResuableCode;
import com.sms.testScript.SuiteLableBaseClass;

import page_Builder_Module.ActionComponentPagewise;
import page_Builder_Module.NavigationComponentPagewise;

public class DragAndDrop extends SuiteLableBaseClass{

	private NavigationComponentPagewise ncp;
	private CommonResuableCode cd;
	private ActionComponentPagewise acp;
	@Test
	public void verifyDragAndDrop() throws InterruptedException
	{
		cd=	new CommonResuableCode(kw);
		cd.navigateToSystemSetting();
		cd.navigateToPageBuilder();
		acp=	new ActionComponentPagewise(kw);
		acp.action_createButton();
	
		ncp=  new NavigationComponentPagewise(kw);
		
		ncp.drag_dropTextField_component();
		ncp.drag_dropDropdown_component();
		ncp.drag_dropTextarea_component();
		ncp.drag_dropCheckbox_component();
		ncp.drag_dropToggle_component();
		ncp.drag_dropRadio_component();
		ncp.drag_dropStarrating_component();
		ncp.drag_dropImageupload_component();
		ncp.drag_dropLabel_component();
		ncp.drag_dropDatepicker_component();
		ncp.drag_dropTime_component();
		ncp.drag_dropDatagrid_component();
		ncp.drag_dropFileupload_component();
		ncp.drag_dropFormgroup_component();
	}

}
