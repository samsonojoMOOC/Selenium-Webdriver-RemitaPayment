package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Util.TestUtility;
import tests.TestBase;

public class BankBranchSetupApprovalTest extends TestBase{
	
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the system");
		
		// xlsx file
		if(TestUtility.isSkip("BankBranchSetupApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void bankBranchSetupApprovalTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_Reversal_Inbox").click();
		ApplicationLogs.debug("Bank Branch Setup Approval Module: Clicked the Inbox Link");
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		driver.switchTo().frame(0);
		
		//Click transaction details
		getObjectByXpath("lnk_transDetail").click();
		ApplicationLogs.debug("Bank Branch Setup Approval Module: Clicked the Bank Branch to Approve");
		
		getObjectByXpath("btn_BranchSetup_Approval").click();
		ApplicationLogs.debug("Bank Branch Setup Approval Module: Clicked the Approve Button");
		
		//Validation
		String actualText = getObjectByXpath("val_ForwardApproval").getText();
		Assert.assertEquals("Branch Setup has not been approved successfully.", "Branch Setup has been approved.", actualText);
		ApplicationLogs.debug("Bank Branch Setup Approval Module: Validated that Bank Branch Setup Approval was successful");
		
		driver.switchTo().defaultContent();
		
	}

}

