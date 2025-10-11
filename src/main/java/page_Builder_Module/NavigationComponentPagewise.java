package page_Builder_Module;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sms.utils.WebUtil;

public class NavigationComponentPagewise extends NavigationComponentOR{
    private WebUtil wu;
	public NavigationComponentPagewise(WebUtil wu) {
		super(wu);
		this.wu=wu;
	}
	
	public void drag_dropTextField_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(gettextfield_component()));
		wu.actiondragAnddrop(gettextfield_component(), getdropareaContainer(), "text field", "drop area container");
	}
	
	public void drag_dropDropdown_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getdropdownfield_component()));
		wu.actiondragAnddrop(getdropdownfield_component(), getdropareaContainer(), "dropdown component", "drop area container");
	}
	
	public void drag_dropTextarea_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(gettextareafield_component()));
		wu.actiondragAnddrop(gettextareafield_component(), getdropareaContainer(), "text area component", "drop area container");
	}
	
	public void drag_dropCheckbox_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getcheckboxfield_component()));
		wu.actiondragAnddrop(getcheckboxfield_component(), getdropareaContainer(), "Checkbox component", "drop area container");
	}
	
	public void drag_dropToggle_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(gettogglefield_component()));
		wu.actiondragAnddrop(gettogglefield_component(), getdropareaContainer(), "Toggle component", "drop area container");
	}
	
	public void drag_dropRadio_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getradiofield_component()));
		wu.actiondragAnddrop(getradiofield_component(), getdropareaContainer(), "Radio component", "drop area container");
	}
	
	public void drag_dropStarrating_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getstarratingfield_component()));
		wu.actiondragAnddrop(getstarratingfield_component(), getdropareaContainer(), "Star rating component", "drop area container");
	}
	

	public void drag_dropImageupload_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getimageuploadfield_component()));
		wu.actiondragAnddrop(getimageuploadfield_component(), getdropareaContainer(), "Image upload component", "drop area container");
	}
	
	
	public void drag_dropLabel_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getlabefield_component()));
		wu.actiondragAnddrop(getlabefield_component(), getdropareaContainer(), "Label component", "drop area container");
	}
	
	public void drag_dropDatepicker_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getdatapickerfield_component()));
		wu.actiondragAnddrop(getdatapickerfield_component(), getdropareaContainer(), "Datepicker component", "drop area container");
	}
	
	public void drag_dropTime_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(gettimefield_component()));
		wu.actiondragAnddrop(gettimefield_component(), getdropareaContainer(), "Time component", "drop area container");
	}
	
	public void drag_dropDatagrid_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getdataGridfield_component()));
		wu.actiondragAnddrop(getdataGridfield_component(), getdropareaContainer(), "DataGrid component", "drop area container");
	}
	
	
	public void drag_dropFileupload_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getfileuploadfiel_component()));
		wu.actiondragAnddrop(getfileuploadfiel_component(), getdropareaContainer(), "File upload component", "drop area container");
	}
	
	public void drag_dropFormgroup_component() throws InterruptedException
	{
		wu.explicitWait().until(ExpectedConditions.visibilityOf(getformgroupfield_component()));
		wu.actiondragAnddrop(getformgroupfield_component(), getdropareaContainer(), "Form group component", "drop area container");
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
