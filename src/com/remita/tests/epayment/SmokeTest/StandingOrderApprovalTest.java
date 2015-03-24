package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import Util.TestUtility;
import tests.TestBase;

public class StandingOrderApprovalTest extends TestBase{
	
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the system");
		
		// xlsx file
		if(TestUtility.isSkip("StandingOrderApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void standingOrderApprovalTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_inbox").click();
		ApplicationLogs.debug("Standing Order Approval Module: Clicked the Inbox link");
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		driver.switchTo().frame(0);
		
		//Click transaction details
		getObjectByXpath("lnk_transDetail").click();
		ApplicationLogs.debug("Standing Order Approval Module: Clicked the Transaction to Approve");
		
		getObjectByName("chk_StandingO_Approval").click();
		ApplicationLogs.debug("Standing Order Approval Module: Clicked the Transaction to Approve");
		
		getObjectById("btn_StandingO_Approval").click();
		ApplicationLogs.debug("Standing Order Approval Module: Clicked the Submit button");
		

		String actualText = getObjectByXpath("val_ForwardApproval").getText();
		Assert.assertEquals("Standing Order Approval was not Successfully", "STANDING ORDER/DIRECT DEBIT has been approved.", actualText);
		ApplicationLogs.debug("We asserted that the Standing Order Approval was carried out successfully");

		
		driver.switchTo().defaultContent();
		
	}

}