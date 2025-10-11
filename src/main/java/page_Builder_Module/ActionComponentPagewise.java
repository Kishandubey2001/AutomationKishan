package page_Builder_Module;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sms.utils.WebUtil;

import net.bytebuddy.utility.RandomString;

public class ActionComponentPagewise extends ActionComponentOR{
     private WebUtil wu;
	public ActionComponentPagewise(WebUtil wu) {
		super(wu);
		this.wu=wu;
	}
	
	public void action_createButton()
	{
	wu.myclick(getaction_pagebuilderCreatebutton(), "Createbutton");
	}
	
	public void action_formname()
	{
	wu.explicitWait().until(ExpectedConditions.visibilityOf(get_formname()));
	String prefix="GEDU";
	String sufix="Form";
	String radomname=	RandomString.make(10);
	String finalRandom=prefix+radomname+sufix;
	wu.mySendkeys(get_formname(), finalRandom, "formname");
	}
	
	public void action_textfieldComponent()
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getaction_textfieldComponent()));
		wu.myclick(getaction_textfieldComponent(), "text field");

	}
	
	public void action_dropdownComponent()
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getaction_dropdownComponent()));
		wu.myclick(getaction_dropdownComponent(), "text field");

	}
	
	
	
	
}
