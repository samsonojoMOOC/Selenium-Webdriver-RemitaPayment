package com.remita.demo.epayment.BankBranchOps;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import testsDemo.TestBase;
import utilDemo.TestUtility;

public class PaySalaryViaRRRApprovalTest extends TestBase{
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the System");	
		// xlsx file
		if(TestUtility.isSkip("PaySalaryViaRRRApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void massApprovalPaymentTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_inbox").click();
		ApplicationLogs.debug("Pay Salary via RRR Approval Module: Clicked the Inbox Link" );
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		
		driver.switchTo().frame(0);
		
		getObjectByXpath("lnk_transDetail").click();
		ApplicationLogs.debug("Pay Salary via RRR Approval Module: Clicked the Transaction link" );
		
		driver.findElement(By.id("record_1")).click();
		ApplicationLogs.debug("Pay Salary via RRR Approval Module: Clicked the Radio button" );
		
		driver.findElement(By.name("postbtn")).click();
		ApplicationLogs.debug("Pay Salary via RRR Approval Module: Clicked the Post transaction button" );
		
		Alert al = driver.switchTo().alert();
		al.accept();
		
		driver.switchTo().defaultContent();
		
	}

}
