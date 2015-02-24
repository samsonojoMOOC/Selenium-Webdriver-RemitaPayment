package com.remita.tests.epayment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

public class PayVendorApprovalTest extends TestBase{
	
	@Before
	public void beforeTest() throws IOException{
		System.out.println("Initializing the system");
		initialize();
		
		// xlsx file
		if(TestUtility.isSkip("PayVendor_ApprovalTest"))
			Assume.assumeTrue(false);
	}
	
	@Test
	public void payVendorApprovalTest() throws InterruptedException{
		// implement
		getObjectByXpath("lnk_inbox").click();
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		driver.switchTo().frame(0);
		
		//Click transaction details
		getObjectByXpath("lnk_transDetail").click();
		
		driver.findElement(By.xpath(".//*[@id='notAvailableDiv']/p[1]/a")).click();
		
		
		//Select Recalculate Total Payable CheckBox
		getObjectByXpath("chk_transaction").click();
		getObjectByXpath("btn_submit").click();
		
		//Finalize Payment via Remita STP
		getObjectByXpath("txt_EnterPIN").click();
		
		  WebElement txtEnterPIN = getObjectByXpath("txt_EnterPIN");
		  ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly', 'readonly')",txtEnterPIN);
		  txtEnterPIN.sendKeys("4321");
		  
		  
		
		new Select(driver.findElement(By.xpath("//*[@id='PG_0']"))).selectByValue("N-1509329270451");
		
		System.out.println("**************************");
		int size1 = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size1);
		
		 //Enter PIN
		/* 
		  WebElement txtEnterPIN = getObjectByXpath("txt_EnterPIN");
		  ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly', 'readonly')",txtEnterPIN);
		  Thread.sleep(20000);
		  txtEnterPIN.sendKeys("4321");
		  
		  
		  //((JavascriptExecutor) driver).executeScript("");
		   * 
		 
		  ((JavascriptExecutor) driver).executeScript("document.getElementById('txtPin0').click()");
		  ((JavascriptExecutor) driver).executeScript("document.getElementById('txtPin0').removeAttribute('readonly', 'readonly')");
		  
		  
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("document.getElementsById('txtPin0')[0].removeAttribute('readonly');");
		    */
		
		//getObjectByXpath("drd_paymentGateway").click();
		//getObjectByXpath("chk_transaction").click();
		//getObjectByXpath("btn_submit").click();
		
		driver.switchTo().defaultContent();
		
	}

}