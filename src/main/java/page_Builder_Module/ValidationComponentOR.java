package page_Builder_Module;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class ValidationComponentOR {
	
	public ValidationComponentOR(WebUtil wu)
	{
		PageFactory.initElements(wu.getDriver(), this);
		
	}
	
	// Text field 
	@FindBy(xpath = "//label[text()='Label']/following-sibling::div//input[contains(@class,'MuiInputBase-input MuiOutlinedInput')]")
	private WebElement defaulttextfield;
	public WebElement getdefaulttextfield()
	{
		return defaulttextfield;
	}
	
	@FindBy(xpath = "//label[text()='Helper Text']/following-sibling::div//input[contains(@class,'MuiInputBase-input MuiOutlinedInput')]")
	private WebElement helpertext;
	public WebElement gethelpertext()
	{
		return helpertext;
	}
	
	@FindBy(xpath = "//label[text()='Field Type']/following-sibling::div//input[contains(@class,'MuiInputBase-input MuiOutlinedInput')]")
	private WebElement fieldtype;
	public WebElement getfieldtype()
	{
		return fieldtype;
	}
	
	@FindBy(xpath = "//p[text()='Search Source']/following-sibling::label//span//span//input[@type='checkbox']")
	private WebElement searchsourceToggleButton;
	public WebElement getsearchsourceToggleButton()
	{
		return searchsourceToggleButton;
	}
	
	@FindBy(xpath = "//p[text()='Primary Trigger']/following-sibling::label//span//span//input[@type='checkbox']")
	private WebElement primarytriggerToggleButton;
	public WebElement getprimarytriggerToggleButton()
	{
		return primarytriggerToggleButton;
	}
	
	@FindBy(xpath = "//p[text()='Auto Generate']/following-sibling::label//span//span//input[@type='checkbox']")
	private WebElement autogenerateToggleButton;
	public WebElement getautogenerateToggleButton()
	{
		return autogenerateToggleButton;
	}
	
	@FindBy(xpath = "//p[text()='Allow Duplicate']/following-sibling::label//span//span//input[@type='checkbox']")
	private WebElement allowduplicateToggleButton;
	public WebElement getallowduplicateToggleButton()
	{
		return allowduplicateToggleButton;
	}
	
	@FindBy(xpath = "//p[text()='Encrypted Data']/following-sibling::label//span//span//input[@type='checkbox']")
	private WebElement encrypteddataToggleButton;
	public WebElement getencrypteddataToggleButton()
	{
		return encrypteddataToggleButton;
	}
	
	@FindBy(xpath = "//p[text()='Required Field']/following-sibling::label//span//span//input[@type='checkbox']")
	private WebElement requiredfieldToggleButton;
	public WebElement getrequiredfieldToggleButton()
	{
		return requiredfieldToggleButton;
	}
	
	@FindBy(xpath = "//label[text()='Min Length']/following-sibling::div//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')]")
	private WebElement minlengthTB;
	public WebElement getminlengthTB()
	{
		return minlengthTB;
	}
	
	@FindBy(xpath = "//label[text()='Max Length']/following-sibling::div//input[contains(@class,'MuiInputBase-input MuiOutlinedInput-input')]")
	private WebElement maxlengthTB;
	public WebElement getmaxlengthTB()
	{
		return maxlengthTB;
	}
	
	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement okbutton;
	public WebElement getokbutton()
	{
		return okbutton;
	}
	
	@FindBy(xpath = "//h5[text()='Text Field: Properties']")
	private WebElement textfieldpropertyName;
	public WebElement gettextfieldpropertyName()
	{
		return textfieldpropertyName;
	}
	
	@FindBy(xpath = "//button[text()='Properties']")
	private WebElement propertiestab;
	public WebElement getpropertiestab()
	{
		return propertiestab;
	}
	
	@FindBy(xpath = "//button[text()='Styles']")
	private WebElement stylestab;
	public WebElement getstylestab()
	{
		return stylestab;
	}
	
	@FindBy(xpath = "//h5[text()='Text Field: Properties']/following-sibling::button//*[name()='svg']")
	private WebElement crossButton;
	public WebElement getcrossButton()
	{
		return crossButton;
	}
	
	
	
	
	
	
}
