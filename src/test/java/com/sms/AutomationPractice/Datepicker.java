package com.sms.AutomationPractice;

import org.testng.annotations.Test;

import com.sms.testScript.Baseclasswithoutlogin;

import importExport.DatepickerPagewise;

public class Datepicker extends Baseclasswithoutlogin{
	private DatepickerPagewise date;
	
	@Test
	public void datepicker() throws InterruptedException
	{
	  date=  new DatepickerPagewise(kw);
	  date.datepicker();
	}

}
