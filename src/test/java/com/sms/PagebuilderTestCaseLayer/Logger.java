package com.sms.PagebuilderTestCaseLayer;

import org.testng.annotations.Test;

import com.sms.utils.LogUtility;

public class Logger {
	
	@Test
	public void loginTest() {
	    LogUtility.info("Starting Login Test");
	    System.out.println(1/0);

	    LogUtility.info("Opening browser");
	    LogUtility.info("Navigating to URL");

	    LogUtility.warn("Username field was taking time to load, using wait");

	    LogUtility.info("Entering username");
	    LogUtility.info("Entering password");

	    LogUtility.error("Login failed due to invalid credentials");

	    LogUtility.info("Ending Login Test");
	}

}
