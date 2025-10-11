package page_Builder_Module;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class ActionComponentOR {
	
	public ActionComponentOR(WebUtil wu)
	{
		PageFactory.initElements(wu.getDriver(), this);
	}
	
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement pagebuildercreatebutton;
	public WebElement getaction_pagebuilderCreatebutton()
	{
		return pagebuildercreatebutton;
	}
	
	@FindBy(xpath = "//input[@id='form-name']")
	private WebElement formname;
	public WebElement get_formname()
	{
		return formname;
	}
	
	@FindBy(xpath = "(//p[contains(text(),'Text Field')])[2]")
	private WebElement action_textfieldComponent;
	public WebElement getaction_textfieldComponent()
	{
		return action_textfieldComponent;
	}
	
	@FindBy(xpath = "(//p[text()='Dropdown'])[2]")
	private WebElement action_dropdownComponent;
	public WebElement getaction_dropdownComponent()
	{
		return action_dropdownComponent;
	}
	
	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement action_okbutton;
	public WebElement getaction_okbutton()
	{
		return action_okbutton;
	}
	

}
