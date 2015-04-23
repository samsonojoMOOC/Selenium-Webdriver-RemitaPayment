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

	public class PayVendorSwift103Test extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		
		//ePayment details here
			
			
			public String UploadType;
			public String FundAcct;
			public String positiveData;
				
			// 3rd Step
			public PayVendorSwift103Test(String UploadType, String FundAcct,  String positiveData ){
				
				this.UploadType=UploadType;
				this.FundAcct = FundAcct;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Initializing the PayVendorBulkSwift103 Test");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			// xlsx file
			if(TestUtility.isSkip("PayVendorBulkSwift103Test"))
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
		public void payVendorBulkSwift103Test() throws IOException{
			
											
			Actions act = new Actions(driver);
			
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Moved to Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay Vendors, Suppliers & Others']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Clicked the Sub Main Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			getObjectById("rdo_BulkUpload").click();
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Clicked the Bulk Upload Radio button");
			
			System.out.println("***************2nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);

			
			new Select(getObjectById("drd_SelectUploadType")).selectByValue(UploadType);
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Selected the Swift103 upload type");
			getObjectByName("btn_Browser").click();
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Clicked the Browse button to select file");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\SWIFT_MT103", "Open").start();
			
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Selected the Swift103 file for upload");
			
			if(isAlertPresent()){
				driver.switchTo().alert().accept();
			}
						
			getObjectByName("btn_Preview").click();
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Clicked the Preview Button");
			
					
			//wait for the preview screen
			getObjectByName("btn_Submit").click();	
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Clicked the Submit Button");
			
			//wait for the Choose Paying Account
			System.out.println("***************3nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);

			new Select(driver.findElement(By.id("fundingbank1"))).selectByValue(FundAcct);
			new Select(driver.findElement(By.id("fundingbank2"))).selectByValue(FundAcct);
			new Select(driver.findElement(By.id("fundingbank3"))).selectByValue(FundAcct);
			new Select(driver.findElement(By.id("fundingbank4"))).selectByValue(FundAcct);
			new Select(driver.findElement(By.id("fundingbank5"))).selectByValue(FundAcct);
			new Select(driver.findElement(By.id("fundingbank6"))).selectByValue(FundAcct);
			
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Selected the funding bank(s)");
			
			getObjectByName("btn_SubmitPay").click();
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Clicked the Submit Pay Button");
			
			//Validate that there is response
			
			//String actualText = "has been forwarded to APPROVER1 for approval";
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to send Payment to Approval", actualText.startsWith("Vendors/Billers Payment (Batch"));
			ApplicationLogs.debug("Pay Vendor Swift103 Module: Asserted that Payments have been sent for approval");
								
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
					Object[][] data = TestUtility.getDataFromExcelSheet("PayVendorBulkSwift103Test");
					return Arrays.asList(data);
			
					
		}
		
		
}

