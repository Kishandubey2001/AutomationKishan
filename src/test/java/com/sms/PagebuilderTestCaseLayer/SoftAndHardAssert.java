package com.sms.PagebuilderTestCaseLayer;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAndHardAssert {
	@Test
	public void HardAssert()
	{
		String name="Kishan";
		String value="Kishandubey";
		Assert.assertEquals(name, value);
		System.out.println("Hii I am Kishan Kumar dubey");

}
	@Test
	public void softAssert()
	{
		String name="Kishan";
		String value="Kishandubey";
		//Assert.assertEquals(name, value);
		
		 SoftAssert sa = new SoftAssert();
		 sa.assertEquals(name, value, "Pass");
		 System.out.println("Hii I am Kishan Kumar dubey");
	}
}
