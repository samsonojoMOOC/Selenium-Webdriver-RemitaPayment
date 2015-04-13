package com.remita.tests.epayment.BankBranchOps;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import tests.TestBase;
import Util.TestUtility;

public class StandingOrderApprovalTest extends TestBase{
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the System");	
		// xlsx file
		if(TestUtility.isSkip("StandingOrderApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void standingOrderApprovalTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_inbox").click();
		ApplicationLogs.debug("Pay Salary via RRR Approval Module: Clicked the Inbox Link" );
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		
		driver.switchTo().frame(0);
		
		getObjectByXpath("lnk_transDetail").click();
		ApplicationLogs.debug("Pay Salary via RRR Approval Module: Clicked the Transaction link" );
		
		driver.findElement(By.xpath("//*[@id='tdisplayx']/tbody/tr[1]/td[8]/a")).click();
		
		driver.findElement(By.name("savebtn")).click();
		
		
		String actualResult = getObjectByXpath("val_ForwardApproval").getText();
		Assert.assertEquals("The Standing order was not approved successfully", "Mandate Confirmation is Successful", actualResult);
		ApplicationLogs.debug("Standing Order Approval Module: We have asserted that the approval was done successfully");
		
		driver.switchTo().defaultContent();
		
	}

}

