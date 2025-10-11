package importExport;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class DatePickerOrlayer {
	
	public DatePickerOrlayer(WebUtil wt)
	{
		PageFactory.initElements(wt.getDriver(), this);
	}
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailTB;
	public WebElement getemailTB()
	{
		return emailTB;
	}
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordTB;
	public WebElement getpasswordTB()
	{
		return passwordTB;
	}
	
	@FindBy(xpath = "//button[text()='Sign In']")
	private WebElement signInBT;
	public WebElement getsignInBT()
	{
		return signInBT;
	}
	
	@FindBy(xpath = "//button[@id='add_Student_Application']")
	private WebElement addstudentApplicationBT;
	public WebElement getaddstudentApplicationBT()
	{
		return addstudentApplicationBT;
	}
	
	@FindBy(xpath = "//p[text()='Add New Student']")
	private WebElement addnewstudent;
	public WebElement getaddnewstudent()
	{
		return addnewstudent;
	}
	
	@FindBy(xpath = "//input[@name='birthdate']")
	private WebElement clickdatepicker;
	public WebElement getclickdatepicker()
	{
		return clickdatepicker;
	}
	
	@FindBy(xpath = "//p[text()='Settings']/parent::div/parent::a/parent::div[contains(@class,'MuiButtonBase-root MuiListItemButton-root MuiListItemButton-gutters MuiListItemButton-root MuiListItemButton-gutters mui-style')]")
	private WebElement settingsBT;
	public WebElement getsettingsBT()
	{
		return settingsBT;
	}
	
	@FindBy(xpath = "//p[text()='Programs']")
	private WebElement programsBT;
	public WebElement getprogramsBT()
	{
		return programsBT;
	}
	
	@FindBy(xpath = "(//button[@aria-label=\"Edit Program\"])[1]")
	private WebElement editprograms;
	public WebElement geteditprograms()
	{
		return editprograms;
	}
	
	@FindBy(xpath = "//button[text()='Program Intake']")
	private WebElement programintake;
	public WebElement getprogramintake()
	{
		return programintake;
	}
	
	@FindBy(xpath = "//button[text()='Add Program Intake ']")
	private WebElement addprogramintakeBT;
	public WebElement getaddprogramintakeBT()
	{
		return addprogramintakeBT;
	}
	
	@FindBy(xpath = "//input[@name='applicationStartDate']")
	private WebElement applicationStartDate;
	public WebElement getapplicationStartDate()
	{
		return applicationStartDate;
	}
	
	@FindBy(xpath = "(//input[contains(@id,':r')])[5]")
    private WebElement clickdatepicker1;
	public WebElement getclickdatepicker1()
	{
		return clickdatepicker1;
	}
}
