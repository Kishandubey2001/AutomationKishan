package page_Builder_Module;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class NavigationComponentOR {
	
	public NavigationComponentOR(WebUtil wu)
	{
		PageFactory.initElements(wu.getDriver(), this);
	}
	
	@FindBy(xpath = "//p[text()='Text Field']")
	private WebElement textfield_component;
	public WebElement gettextfield_component()
	{
		return textfield_component;
	}
	
	@FindBy(xpath = "//p[text()='Dropdown']")
	private WebElement dropdownfield_component;
	public WebElement getdropdownfield_component()
	{
		return dropdownfield_component;
	}
	
	@FindBy(xpath = "//p[text()='Text Area']")
	private WebElement textareafield_component;
	public WebElement gettextareafield_component()
	{
		return textareafield_component;
	}
	
	@FindBy(xpath = "//p[text()='Checkbox']")
	private WebElement checkboxfield_component;
	public WebElement getcheckboxfield_component()
	{
		return checkboxfield_component;
	}
	
	@FindBy(xpath = "//p[text()='Toggle']")
	private WebElement togglefield_component;
	public WebElement gettogglefield_component()
	{
		return togglefield_component;
	}
	
	@FindBy(xpath = "//p[text()='Radio']")
	private WebElement radiofield_component;
	public WebElement getradiofield_component()
	{
		return radiofield_component;
	}
	
	@FindBy(xpath = "//p[text()='Star Rating']")
	private WebElement starratingfield_component;
	public WebElement getstarratingfield_component()
	{
		return starratingfield_component;
	}
	
	@FindBy(xpath = "//p[text()='Image Upload']")
	private WebElement imageuploadfield_component;
	public WebElement getimageuploadfield_component()
	{
		return imageuploadfield_component;
	}
	
	@FindBy(xpath = "//p[text()='Label']")
	private WebElement labefield_component;
	public WebElement getlabefield_component()
	{
		return labefield_component;
	}
	
	@FindBy(xpath = "//p[text()='Date Picker']")
	private WebElement datapickerfield_component;
	public WebElement getdatapickerfield_component()
	{
	   return datapickerfield_component;
	}
	
	@FindBy(xpath = "//p[text()='Time']")
	private WebElement timefield_component;
	public WebElement gettimefield_component()
	{
		return timefield_component;
	}
	
	@FindBy(xpath = "//p[text()='Data Grid']")
	private WebElement dataGridfield_component;
	public WebElement getdataGridfield_component()
		{
		return dataGridfield_component;
	}
	
	@FindBy(xpath = "//p[text()='File Upload']")
	private WebElement fileuploadfiel_component;
	public WebElement getfileuploadfiel_component()
	{
		return fileuploadfiel_component;
	}
	
	@FindBy(xpath = "//p[text()='Form Group']")
	private WebElement formgroupfield_component;
	public WebElement getformgroupfield_component()
	{
		return formgroupfield_component;
	}
	
	@FindBy(xpath = "//div[@id='drop-area-container']")
	private WebElement dropareaContainer;
	public WebElement getdropareaContainer()
	{
		return dropareaContainer;
	}
	

}
