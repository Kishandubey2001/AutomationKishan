package com.sms.testScript;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sms.commonCodes.CommonResuableCode;
import com.sms.utils.ConfigFile;
import com.sms.utils.WebUtil;

import lombok.Getter;
@Getter
public class Baseclasswithoutlogin {
	

	
	
	protected WebUtil kw=	WebUtil.getobject();
	public CommonResuableCode cd;
	private static ExtentReports extent;
	private ConfigFile configReader;
	
	@SuppressWarnings(value = { "deprecation" })
	@BeforeSuite
	public void beforesuit()
	{ 
	
			System.out.println("Extent-Report Initialization");
			//SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy hh_mm_ss a").format(new Date());
		    SimpleDateFormat dateFormat = new SimpleDateFormat(" dd-MM-yyyy hh_mm_ss a");

	    dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Set timezone to IST (Gurugram, India)

	    String date = dateFormat.format(new Date()).replace(":", "_");

	        // Set the report file path with the dynamic timestamp
	       // String reportPath = reportFolder + "/ExtentReport_" + date + ".html";
		extent =new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter("Report//NewReport"+date+".html");
		extent.attachReporter(spark);
		 // Set Report Configurations
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Functional Testing");
        spark.config().setTheme(Theme.STANDARD); // Setting Standard Theme

        // Attach the reporter
        extent.attachReporter(spark);

        // Adding System Information
        extent.setSystemInfo("Tester Name", "Kishan");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        System.out.println("Extent Report Configured Successfully");
	}
	
	@BeforeTest
	public void beforeTestTag() {
		System.out.println("Connect To The Data Base");
	}

	@SuppressWarnings(value = { "deprecation" })
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browser,String url)
	{	kw.launchbrowser(browser);
	    kw.deleteAllcookies();
	kw.maximizeWindow();
	   kw.hitUrl(url);
	//kw.maximizeWindow();
	

	}
		//@BeforeGroups({"Smoke"})
	@SuppressWarnings(value = { "deprecation" })
	//@Parameters({"Username","Password"})
	@BeforeMethod
	public void beforemethod(Method mt) throws InterruptedException, IOException
	{ 
		  
		ExtentTest  exTest=extent.createTest(mt.getName());
		kw.setExtentTestObject(exTest);
	
	}
	@SuppressWarnings(value = { "deprecation" })
	@BeforeMethod
	public void beforemethod()
	{
		System.out.println("This is  before method");
	}
	@SuppressWarnings(value = { "deprecation" })
	@AfterMethod
	public void aftermethod(ITestResult result,Method mt) throws InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
		String snapshotPath=kw.takeSnapshot(mt.getName());	
	     kw.getExtentTestObject().addScreenCaptureFromPath(snapshotPath);
		}
		//cd.logout();
		//Thread.sleep(8000);
        extent.flush();
		
	}
	@AfterClass
	public void afterclass()
	{
	//kw.myquite();
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Disconnect From The Data Base");
	}
	
	@AfterSuite
	public void aftersuit()
	{
		System.out.println("Extent-Report Finalization");
		extent.flush();
	}


}
