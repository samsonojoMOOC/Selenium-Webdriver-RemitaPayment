package com.remita.demo.epayment.colletions;


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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import testsDemo.TestBase;
import utilDemo.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class SendOutInvoiceUploadTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String serviceType;
			public String narration;
			public String positiveData;
			
				
			// 3rd Step
			public SendOutInvoiceUploadTest(String serviceType, String narration, String positiveData ){
				
				this.serviceType = serviceType;
				this.narration = narration;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			
			ApplicationLogs.debug("Initializing the SendOutInvoiceUpload Test");
			
			// xlsx file
			if(TestUtility.isSkip("SendOutInvoiceUploadTest"))
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
		public void sendOutInvoicesUploadTest() throws IOException, InterruptedException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[4]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Send Out Invoices Upload Module: Moved to the Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Send Out Invoices']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Send Out Invoices Upload Module: Clicked the Send Out Invoices Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			
			getObjectsByName("rdo_CollectSendInvoiceUpload_UploadFile").get(1).click();
			ApplicationLogs.debug("Send Out Invoices Upload Module: Clicked the Upload Invoice File Radio");
			new Select(getObjectByName("drd_CollectSendInvoiceUpload_ServiceType")).selectByValue(serviceType);
			ApplicationLogs.debug("Send Out Invoices Upload Module: Selected the Service Type drop down");
			

			getObjectByName("btn_CollectSendInvoiceUpload_Browse").click();
			ApplicationLogs.debug("Send Out Invoices Upload Module: Clicked Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\collectionUploadFormat-1.csv", "Open").start();
			ApplicationLogs.debug("Send Out Invoices Upload Module: Selected and Attached the Invoice CSV File");
					
			if(isAlertPresent()){
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
			
			getObjectByXpath("btn_CollectSendInvoiceUplaod_Preview").click();			
			ApplicationLogs.debug("Send Out Invoices Upload Module: Clicked the Preview Button");
			
			
			
			
			
			
			
			getObjectByName("btn_CollectSendInvoiceUpload_Submit").click();
			ApplicationLogs.debug("Send Out Invoices Upload Module: Clicked the Submit button");
			
			new FluentWait <WebDriver> (driver)
			.withMessage("We are not able to load Narration")
			.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.invisibilityOfElementLocated(By.id("window_eventfull_image")));
			
			
			getObjectByName("txt_CollectSendInvoiceUpload_Naration").sendKeys(narration);
			ApplicationLogs.debug("Send Out Invoices Upload Module: Inserted Narration");
			
			getObjectById("btn_CollectSendInvoiceUpload_SendInvoice").click();
			ApplicationLogs.debug("Send Out Invoices Upload Module: Clicked Send Invoice button");
			
			
			
			String actualText = driver.findElement(By.xpath("html/body/div[2]")).getText();
			//System.out.println(actualText);
			
			String expectedText = "Bill Payment has been successfully generated and sent to your uploaded payers";
			//Assert.assertEquals(expectedText, actualText);
			Assert.assertEquals("Send Out Invoice Upload was unsuccessful", expectedText, actualText);
			//Assert.assertTrue("Send Out Invoice was unsuccessful", expectedText = actualText);
			ApplicationLogs.debug("Send Out Invoices Upload Module: We verified that Upload has been successfully generated");
			
					
		
			driver.switchTo().defaultContent();
			
			  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Invoice was Uploaded to Client successfully");
				Assert.assertTrue("Invoice was Uploaded to Client successfully", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("SendInvoiceUploadTest");
				System.out.println("Invoice Upload was unsuccessful");
				Assert.assertTrue("Invoice Upload was unsuccessful", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("SendOutInvoiceUploadTest");
					return Arrays.asList(data);
			
					
		}
		
		
}



