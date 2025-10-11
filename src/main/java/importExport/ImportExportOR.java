package importExport;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sms.utils.WebUtil;

public class ImportExportOR {
	
  public	ImportExportOR(WebUtil wt)
  {
	  PageFactory.initElements(wt.getDriver(), this);
  }
  
  @FindBy(xpath = "//input[@id='userName']")
  private WebElement emailTB;
  public WebElement getemailTB()
  {
	  return emailTB;
  }
  
  @FindBy(xpath = "//input[@id='password']")
  private WebElement passwordTB;
  public WebElement getpasswordTB()
  {
	  return passwordTB;
  }
  
  @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary MuiButton-fullWidth MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary MuiButton-fullWidth css-ac1mdb']")
  private WebElement loginBT;
  public WebElement getloginBT()
  {
	  return loginBT;
  }
  
  @FindBy(xpath = "//a[text()='Student Details form']")
  private WebElement clickstudentdetailsLK;
  public WebElement getclickstudentdetailsLK()
  {
	  return clickstudentdetailsLK;
  }
  
  @FindBy(id = "exportButton")
  private WebElement exportBT;
  public WebElement getexportBT()
  {
	  return exportBT;
  }
  
  @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-colorPrimary css-19uemm7']")
  private WebElement clickonDownloadBT;
  public WebElement getclickonDownloadBT()
  {
	  return clickonDownloadBT;
  }
  
  @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeLarge css-1ytt3ja']")
  private WebElement clickimportBT;
  public WebElement getclickimportBT()
  {
	  return clickimportBT;
  }
  
  @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-fotgxr']")
  private WebElement fileupload;
  public WebElement getfileuploadBT()
  {
	  return fileupload;
  }
  
  @FindBy(xpath = "//p[text()='User Management']")
  private WebElement usermanagementSection;
  public WebElement getusermanagementSection()
  {
	  return usermanagementSection;
  }
  
  @FindBy(xpath = "//p[text()='Department Master']")
  private WebElement departmentmaster;
  public WebElement getdepartmentmaster()
  {
	  return departmentmaster;
  }
  
  
	

}
