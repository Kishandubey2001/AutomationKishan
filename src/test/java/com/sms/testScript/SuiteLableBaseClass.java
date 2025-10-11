package com.sms.testScript;

import java.io.File;
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


public class SuiteLableBaseClass {
	

	protected WebUtil kw=	WebUtil.getobject();
	public CommonResuableCode cd;
	private static ExtentReports extent;
	private ConfigFile configReader;
	
	@SuppressWarnings(value = { "deprecation" })
	 @BeforeSuite
	    public void beforeSuite() {
	        // Delete existing reports before generating a new one
	        File reportDir = new File("Report");
	        if (reportDir.exists()) {
	            for (File file : reportDir.listFiles()) {
	                file.delete();
	            }
	        }

	        System.out.println("Extent Report Initialization");

	        // Date format for timestamp in report name
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh_mm_ss a");
	        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Set timezone to IST
	        String date = dateFormat.format(new Date()).replace(":", "_");

	        // Creating Extent Report
	        extent = new ExtentReports();
	        ExtentSparkReporter spark = new ExtentSparkReporter("Report//NewReport_" + date + ".html");

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
	public void beforeClass(String browser,String url) throws InterruptedException
	{	
		   ExtentTest setupLog = extent.createTest("Setup - beforeClass");
		kw.launchbrowser(browser);
	    kw.deleteAllcookies();
	kw.maximizeWindow();
	kw.hitUrl(url);
	kw.maximizeWindow();
	configReader = new ConfigFile("src\\main\\resources\\propertiesFiles\\loginItems.properties");
	cd= new CommonResuableCode(kw);
	kw.setExtentTestObject(setupLog);
    cd.validLogin(configReader.getProperty("username"), configReader.getProperty("password"));
    
	

	}
		//@BeforeGroups({"Smoke"})
	//@SuppressWarnings(value = { "deprecation" })
	//@Parameters({"Username","Password"})
	@BeforeMethod
	public void beforemethod(Method mt) throws InterruptedException, IOException
	{ 
		  
		ExtentTest  exTest=extent.createTest(mt.getName());
		kw.setExtentTestObject(exTest);
		
	
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
		
		//Thread.sleep(8000);
        extent.flush();
		
	}
	@AfterClass
	public void afterclass()
	{
		cd.logout();
		kw.myquite();
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
