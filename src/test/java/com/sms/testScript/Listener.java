package com.sms.testScript;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter{
	 @Override
	    public void onTestFailure(ITestResult tr) {
	        // Take screenshot or perform other actions on test failure
	        System.out.println("Test failed: " + tr.getName());
	    }

}
