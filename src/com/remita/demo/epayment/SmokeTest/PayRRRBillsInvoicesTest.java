package com.remita.demo.epayment.SmokeTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import testsDemo.TestBase;
import utilDemo.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class PayRRRBillsInvoicesTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		
		//ePayment details here
			
			
			public String RRR;
			public String acct2PayFrom;
			public String positiveData;
				
			// 3rd Step
			public PayRRRBillsInvoicesTest(String RRR, String acct2PayFrom, String positiveData ){
				
				this.RRR =RRR;
				this.acct2PayFrom =acct2PayFrom;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
						
			initialize();
			ApplicationLogs.debug("Initializing the PayRRRBillsInvoices Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayRRRBillsInvoicesTest"))
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
		public void payRRRBillsInvoiceTest() throws InterruptedException {
			
			
			
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Actions act = new Actions(driver);
			
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Moved mouse to the Main Menu Link");
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay RRR Bills & Invoices']"));
			act.moveToElement(submenuPayment).click().perform();
			
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Moved mouse to the Submenu Link");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Switching to the iFrame");
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			
			
			/*
			new FluentWait<WebDriver>(driver)
			.withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainContentArea']/div[2]/div[1]/iframe/html/body/div[2]/label/input[@id='standOrderRadioId']")));
			*/
			
			
			//Fetching data from XLS file
			
			getObjectById("txt_RRR").sendKeys(RRR);
			
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Selected RRR");
			
			getObjectById("btn_SearchRRR").click();
			
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Clicked Button");
			
			new Select(getObjectById("drd_Acct2PayFromRRR")).selectByValue(acct2PayFrom);
			
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Inputed Account to Pay From");
			
			
			getObjectById("btn_PayBtn").click();
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Clicked the Send for Approval button");
			
			
			//Validate that there is response
			
			//String actualText = "has been forwarded to APPROVER1 for approval";
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to send RRR Bills and Invoices for Approval", actualText.startsWith("Vendors/Billers Payment (Batch"));
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: We validated that RRR Bills and Invoices were Successfully sent for Approval");
			
			driver.switchTo().defaultContent();
			ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Switched back to the default Content");
			
			
			//End of first Iteration
						  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				Assert.assertTrue("RRR Bills and Invoice  Transaction(s) was successfully carried out", true);
				ApplicationLogs.debug("Pay RRR Bills and Invoices Module: Payment to Vendor Transaction(s) was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				Assert.assertTrue("RRR Bills and Invoice  Transaction(s) was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pay RRR Bills and Invoices Module: RRR Bills and Invoices Transaction(s) was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayRRRBillsInvoicesTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

