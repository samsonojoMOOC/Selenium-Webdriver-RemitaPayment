package com.remita.tests.epayment.colletions;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;




import org.apache.log4j.Logger;
import org.junit.After;
//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class ResendUnpaidInvoiceTest extends TestBase {
		// 2nd Step to parameterization
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			

			public String RRR;
			public String positiveData;
				
			// 3rd Step
			public ResendUnpaidInvoiceTest(String RRR, String positiveData ){
				
				this.RRR = RRR;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the ResendUnpaidInvoice Test");
			// xlsx file
			if(TestUtility.isSkip("ResendUnpaidInvoiceViaRRR"))
				Assume.assumeTrue(false);
		}
		@After
		  public void tearDown() throws Exception {
		    try {
		      driver.switchTo().defaultContent();
		    } catch (Exception e) {
		      e.getMessage();
		    }
		  }
		
		@SuppressWarnings("deprecation")
		@Test
		public void resendOutInvoicesTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[4]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Resend Unpaid Invoice Module: Moved to the Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Send Reminders - Unpaid Invoices']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Resend Unpaid Invoice Module: Clicked the Send Reminders Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			
			getObjectById("txt_RRRRend").sendKeys(RRR);
			ApplicationLogs.debug("Resend Unpaid Invoice Module: Inserted Service RRR");
			getObjectById("btn_Search").click();
			ApplicationLogs.debug("Resend Unpaid Invoice Module: Clicked the Search button");
			
			//Wait a while for the WebTable to be visible
			
			getObjectByXpath("chk_Transaction").click();
			ApplicationLogs.debug("Resend Unpaid Invoice Module: Clicked the Check box for the transaction");
			getObjectById("btn_SendUpdaidEmail").click();
			ApplicationLogs.debug("Resend Unpaid Invoice Module: Clicked the Send Unpaid Email button");
		
			String actualText = driver.findElement(By.xpath("html/body/div[2]")).getText();
			System.out.println(actualText);
			
			String expectedText = "Notification Sent Successfully";
			//Assert.assertEquals(expectedText, actualText);
			Assert.assertEquals("Resend Email Notification was unsuccessful", expectedText, actualText);
			//Assert.assertTrue("ReSend Email Notification was unsuccessful", expectedText = actualText);
			ApplicationLogs.debug("Resend Unpaid Invoice Module: We validated that Email Notification was sent successfully");
			driver.switchTo().defaultContent();
			  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Unpaid Invoice was sent to Client successfully");
				Assert.assertTrue("Unpaid Invoice was sent to Client successfully", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Unpaid Invoice Test");
				System.out.println("Unpaid Invoice was unsuccessful");
				Assert.assertTrue("Unpaid Invoice was unsuccessful", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name for example LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("ResendUnpaidInvoiceViaRRR");
					return Arrays.asList(data);
			
					
		}
		
		
}


