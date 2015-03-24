package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Util.TestUtility;
import tests.TestBase;

public class UserSetupApprovalTest extends TestBase{
	
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the system");
		
		// xlsx file
		if(TestUtility.isSkip("UserSetupApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void userSetupApprovalTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_inbox").click();
		ApplicationLogs.debug("User Setup Approval Module: Clicked the Inbox link");
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		driver.switchTo().frame(0);
		
		//Click transaction details
		getObjectByXpath("lnk_transDetail").click();
		ApplicationLogs.debug("User Setup Approval Module: Clicked the Transaction to Approve");
		
		driver.findElement(By.id("submit")).click();
		ApplicationLogs.debug("User Setup Approval Module: Clicked the Submit button");
		

		String actualText = getObjectByXpath("val_ForwardApproval").getText();
		Assert.assertEquals("User Setup Approval was not Successfully", "User Setup has been approved.", actualText);
		ApplicationLogs.debug("We asserted that the User Setup Approval was carried out successfully");

		
		driver.switchTo().defaultContent();
		
	}

}