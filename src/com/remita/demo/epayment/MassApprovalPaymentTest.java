package com.remita.demo.epayment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


import utilDemo.TestUtility;
import testsDemo.TestBase;

public class MassApprovalPaymentTest extends TestBase{
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
	@Before
	public void beforeTest() throws IOException{
		
		initialize();
		ApplicationLogs.debug("Initializing the System");	
		// xlsx file
		if(TestUtility.isSkip("MassApprovalPaymentTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void massApprovalPaymentTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_inbox").click();
		ApplicationLogs.debug("Reject Payment Approval Module: Clicked the Inbox Link" );
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		driver.switchTo().frame(0);
		
		//Click transaction radio button to reject
		
		driver.findElement(By.name("record1")).click();
		driver.findElement(By.name("record3")).click();
		driver.findElement(By.name("record5")).click();
		driver.findElement(By.name("record6")).click();
		driver.findElement(By.name("record8")).click();
		driver.findElement(By.name("record9")).click();
		driver.findElement(By.name("cmd1")).click();
		driver.findElement(By.xpath("html/body/div[8]/div[3]/div/button[1]")).click();
		
		String actualText = driver.findElement(By.xpath("html/body/form/div[1]/p")).getText(); 
		Assert.assertEquals("The Transactions ware not Approved Successfully", "Mass Remit", actualText);
		ApplicationLogs.debug("We asserted that the Mass Approvals were carried out successfully");
		
		driver.switchTo().defaultContent();
		
	}

}