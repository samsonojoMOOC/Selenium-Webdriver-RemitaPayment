package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Util.TestUtility;
import tests.TestBase;

public class ReverseWrongTransApprovalTest extends TestBase{
	
	@Before
	public void beforeTest() throws IOException{
		System.out.println("Initializing the system");
		initialize();
		
		// xlsx file
		if(TestUtility.isSkip("PayVendor_ApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void reverseWrongTransApprovalTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_Reversal_Inbox").click();
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		driver.switchTo().frame(0);
		
		//Click transaction details
		getObjectByXpath("lnk_transDetail").click();
		
		getObjectByName("chk_Reversal_ChkBox").click();
		
		getObjectByName("txt_Reversal_Comment").sendKeys("Seen. Its Ok");
		
		getObjectByName("btn_Reversal_Submit").click();
		
		//Validation
		String actualText = getObjectByXpath("val_ForwardApproval").getText();
		Assert.assertEquals("Echeck Reversal has not been approved and forwarded for payment", "Echeque Reversal has been approved and forwarded for Payment", actualText);
				 
		
		driver.switchTo().defaultContent();
		
	}

}
