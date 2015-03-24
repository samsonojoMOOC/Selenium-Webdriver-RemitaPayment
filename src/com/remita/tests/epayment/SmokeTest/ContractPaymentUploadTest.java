package com.remita.tests.epayment.SmokeTest;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import tests.TestBase;
import Util.TestUtility;


	public class ContractPaymentUploadTest extends TestBase {
		// 2nd Step to parameterization
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
	
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the PayContractorUpload Test");
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			
			// xlsx file
			if(TestUtility.isSkip("PayContractorUploadTest"))
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
		public void payContractorUploadTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Pay Contractor Upload Module: Moved to the Main Menu");
						
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay Vendors, Suppliers & Others']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked the Sub Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			
			getObjectById("rdo_BulkUpload").click();
			
			getObjectsByName("rdo_UploadFileAndTax").get(2).click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked Bulk Upload Radio Button");
			
			System.out.println("***************2nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);

			
			getObjectByName("btn_Browser").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked the Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\CONTRACT_0_Upload.csv", "Open").start();
			ApplicationLogs.debug("Pay Contractor Upload Module: Attached the Contractor's CVS file");


			Alert alert = driver.switchTo().alert();
			alert.accept();
			
			
			getObjectByName("btn_Contractor_Preview").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked the Preview Menu");
			
			
			//wait for the Choose Paying Account
			System.out.println("***************3nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);


			getObjectByName("btn_Contractor_Submit").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked the Submit Pay button");
			
			//Validation that we uploaded the Echeque successfully
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to Upload Echeque Successfully", actualText.startsWith("Sucessful Echeque Upload - Batch Number"));
			ApplicationLogs.debug("Pay Contractor Upload Module: Asserted that the Payment have been successfully sent for Approval");
			
			//Ensuring that we are able to Remit each of the 3 transactions - 1st One
			getObjectByXpath("lnk_Contractor_Transaction").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked the transaction to Remit the first transaction");
			
			new FluentWait<WebDriver> (driver)
			.withTimeout(90, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.visibilityOfElementLocated(By.name("fundingbank")));
			
			new Select(getObjectByName("drd_Contractor_FundingBank")).selectByValue("1509329270451");
			
			ApplicationLogs.debug("Pay Contractor Upload Module: Selected bank to Remit into");
			
			getObjectByName("btn_Contractor_SubmitBank").click();
			
			
			//Validate that there is response
			
			actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to send Uploaded Payment for Approval", actualText.startsWith("Tax Remittance (Batch"));
			ApplicationLogs.debug("Pay Vendor eCheck Module: Asserted that the Payment have been successfully sent for Approval");
								
			//Ensuring that we are able to Remit each of the 3 transactions - 2nd One

			getObjectByXpath("lnk_Contractor_Transaction").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked the transaction to Remit");
			
			new FluentWait<WebDriver> (driver)
			.withTimeout(90, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.visibilityOfElementLocated(By.name("fundingbank")));
			
			new Select(getObjectByName("drd_Contractor_FundingBank")).selectByValue("1509329270451");
			ApplicationLogs.debug("Pay Contractor Upload Module: Selected bank to Remit into");
			new Select(getObjectById("drd_Contractor_FundingBank1")).selectByValue("1509329270451");
			ApplicationLogs.debug("Pay Contractor Upload Module: Selected bank to Remit into");
			getObjectByName("btn_Contractor_SubmitBank").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Submit the transaction to Remit");
			
			//Validate that there is response
			
			actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to send Payment for Approval", actualText.startsWith("Tax Remittance (Batch"));
			ApplicationLogs.debug("Pay Vendor eCheck Module: Asserted that the Payment have been successfully sent for Approval");
			
			//Ensuring that we are able to Remit each of the 3 transactions - 3rd One
			

			getObjectByXpath("lnk_Contractor_Transaction").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Clicked the transaction to Remit");
			
			new FluentWait<WebDriver> (driver)
			.withTimeout(90, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.visibilityOfElementLocated(By.name("fundingbank")));
			
			new Select(getObjectByName("drd_Contractor_FundingBank")).selectByValue("1509329270451");
			ApplicationLogs.debug("Pay Contractor Upload Module: Selected bank to Remit into");
			
			getObjectByName("btn_Contractor_SubmitBank").click();
			ApplicationLogs.debug("Pay Contractor Upload Module: Submit the transaction to Remit");
			
			//Validate that there is response
			
			//String actualText = "has been forwarded to APPROVER1 for approval";
			
			actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to send Vendors/Billers Payment has not been successfully forwarded for approval", actualText.startsWith("Vendors/Billers Payment (Batch"));
			ApplicationLogs.debug("Pay Vendor eCheck Module: Asserted that the Payment have been successfully sent for Approval");
			
			driver.switchTo().defaultContent();
			
			
			//End of first Iteration
			
		}
	}


