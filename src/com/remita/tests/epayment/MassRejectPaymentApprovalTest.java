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

public class MassRejectPaymentApprovalTest extends TestBase{
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the System");	
		// xlsx file
		if(TestUtility.isSkip("MassRejectPaymentApprovalTest"))
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
		
		//Click transaction radio button to reject
		
		driver.findElement(By.name("remove2")).click();
		driver.findElement(By.name("remove3")).click();
		driver.findElement(By.name("remove6")).click();
		driver.findElement(By.name("remove9")).click();
		driver.findElement(By.name("remove12")).click();
		driver.findElement(By.name("remove14")).click();
		driver.findElement(By.name("cmd1")).click();
		driver.findElement(By.xpath("html/body/div[8]/div[3]/div/button[1]")).click();
		
		String actualText = getObjectByXpath("val_ForwardApproval").getText();
		Assert.assertEquals("The Transactions ware not Rejected Successfully", "You have successfully rejected 6 Transaction(s)", actualText);
		ApplicationLogs.debug("We asserted that the Rejections were carried out successfully");
		
		driver.switchTo().defaultContent();
		
	}

}