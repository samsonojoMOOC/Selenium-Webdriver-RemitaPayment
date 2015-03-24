package com.remita.tests.epayment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;



import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;



import Util.TestUtility;
import tests.TestBase;

public class RejectPaymentApprovalTest extends TestBase{
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the System");	
		// xlsx file
		if(TestUtility.isSkip("RejectPaymentApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void payVendorApprovalTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_inbox").click();
		ApplicationLogs.debug("Reject Payment Approval Module: Clicked the Inbox Link" );
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		driver.switchTo().frame(0);
		
		//Click transaction details
		getObjectByXpath("lnk_transDetail").click();
		ApplicationLogs.debug("Reject Payment Approval Module: Clicked the Transaction Details Link");
		
		getObjectByXpath("chk_transaction").click();
		ApplicationLogs.debug("Reject Payment Approval Module: Clicked the Transaction Checkbox");
		
		getObjectByXpath("btn_submit").click();
		ApplicationLogs.debug("Reject Payment Approval Module: Clicked the Submit button");
		
		getObjectByName("btn_Reject").click();
		ApplicationLogs.debug("Reject Payment Approval Module: Clicked the Reject button");
		
		String actualText = getObjectByXpath("val_ForwardApproval").getText();
		Assert.assertEquals("The Transaction was not Rejected Successfully", "Transaction Rejected", actualText);
		ApplicationLogs.debug("We asserted that the Rejection was carried out successfully");
		driver.switchTo().defaultContent();
		
	}

}