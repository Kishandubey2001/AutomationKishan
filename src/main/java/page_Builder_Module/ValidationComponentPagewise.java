package page_Builder_Module;

import com.sms.utils.WebUtil;

public class ValidationComponentPagewise extends ValidationComponentOR{
     private WebUtil wu;
	public ValidationComponentPagewise(WebUtil wu) {
		super(wu);
		this.wu=wu;
	}
	
	public void verifydefaulttextfieldlabelname()
	{
		wu.verifydefaultValueeditbox(getdefaulttextfield(), "Text Field", "text field label name");
	}
	
	public void verifyblankhelpertext()
	{
		wu.verifydefaultValueeditbox(getdefaulttextfield(), "", "blank Helper text");
	}
	
	public void verifysearchsourcetoggleof()
	{
		wu.verifyToggleButtonIsOff(getsearchsourceToggleButton(), "Search source");
	}
	
	public void verifysearchsourcetoggleEnabled()
	{
		wu.verifyToggleButtonIsEnabled(getsearchsourceToggleButton(), "Search source");
	}
	
	
	

}
