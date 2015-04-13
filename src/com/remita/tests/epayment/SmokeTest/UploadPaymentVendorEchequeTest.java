package com.remita.tests.epayment.SmokeTest;


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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class UploadPaymentVendorEchequeTest extends TestBase {
		// 2nd Step to parameterization
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String UploadType;
			public String FundAcct;
			public String positiveData;
				
			// 3rd Step
			public UploadPaymentVendorEchequeTest(String UploadType, String FundAcct,  String positiveData ){
				
				this.UploadType=UploadType;
				this.FundAcct = FundAcct;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Initializing the PayVendorBulk Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayVendorBulkTest"))
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
		public void payVendorBulkTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Moved to the Main Menu");
						
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay Vendors, Suppliers & Others']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Clicked the Sub Menu");
			
			/*
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			*/
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			getObjectById("rdo_BulkUpload").click();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Clicked Bulk Upload Radio Button");
			/*
			System.out.println("***************2nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			*/
			
			new Select(getObjectById("drd_SelectUploadType")).selectByValue(UploadType);
			ApplicationLogs.debug("Pay Vendor eCheck Module: Selected the Upload Type from drop down");
			
			getObjectByName("btn_Browser").click();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Clicked the Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\normalechequeupload01.csv", "Open").start();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Attached the eCheck CVS file");

			if(isAlertPresent()){
			driver.switchTo().alert().accept();
			}
			
			
			getObjectByName("btn_Preview").click();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Clicked the Preview Menu");
			
			
			//wait for the preview screen
			getObjectByName("btn_Submit").click();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Clicked the Submit Button");
			
			/*wait for the Choose Paying Account
			System.out.println("***************3nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			*/

/*			new FluentWait<WebDriver>(driver)
		       .withTimeout(120, TimeUnit.SECONDS)
		       .pollingEvery(5, TimeUnit.SECONDS)
		       .ignoring(NoSuchElementException.class)
		       //ElementNotVisibleException
		       .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[2]")));
			*/
						
			new Select(getObjectByName("drd_FundingBank")).selectByValue(FundAcct);
			ApplicationLogs.debug("Pay Vendor eCheck Module: Selected the Funding Bank");
			
			getObjectByName("btn_SubmitPay").click();
			ApplicationLogs.debug("Pay Vendor eCheck Module: Clicked the Submit Pay button");
			//Validate that there is response
			
			//String actualText = "has been forwarded to APPROVER1 for approval";
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to send Payment to Approval", actualText.startsWith("Vendors/Billers Payment (Batch"));
			ApplicationLogs.debug("Pay Vendor eCheck Module: Asserted that the Payment have been successfully sent for Approval");
								
			driver.switchTo().defaultContent();
			
			
			//End of first Iteration
			
			  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Payment to Vendor Transaction(s) was successfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction(s) was successfully carried out", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Payment to Vendor Transaction(s) was unsuccessfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction(s) was unsuccessfully carried out", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayVendorBulkTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

