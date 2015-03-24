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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class PayNHFTest extends TestBase {
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		// 2nd Step to parameterization
	
			public String nhfMonth;
			public String nhfYear;
			public String briefDesc;
			public String detailedDesc;
			public String FundAcct;
			public String positiveData;
				
			// 3rd Step
			public PayNHFTest(String nhfMonth,  String nhfYear, String briefDesc, String detailedDesc, String FundAcct, String positiveData ){
				
				this.nhfMonth=nhfMonth;
				this.nhfYear = nhfYear;
				this.briefDesc = briefDesc;
				this.detailedDesc = detailedDesc;
				this.FundAcct = FundAcct;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the PayNHF Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayNHFTest"))
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
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Pay NHF Module: Moved mouse to the Main Menu Link");
			
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay NHF ']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Pay MHF Module: Clicked the Pay NHF Menu");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			ApplicationLogs.debug("Pay NHF Module: Switch into the Content iFrame");
			
			new Select(getObjectByName("drd_PayNHF_Month")).selectByValue(nhfMonth);
			ApplicationLogs.debug("Pay NHF Module: Selected NHF Month");
			
			new Select(getObjectByName("drd_PayNHF_Year")).selectByValue(nhfYear);
			ApplicationLogs.debug("Pay NHF Module: Selected NHF Year");
			
			getObjectByName("btn_PayNHF_Browse").click();
			ApplicationLogs.debug("Pay NHF Module: Clicked Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\nhf upload file.csv", "Open").start();
			ApplicationLogs.debug("Pay NHF Module: Selected and Attached the NHF CSV File");
			
			if(isAlertPresent()){
				driver.switchTo().alert().accept();
			}
			
			getObjectByName("txt_PayNHF_briefDesc").sendKeys(briefDesc);
			ApplicationLogs.debug("Pay NHF Module: Inserted Brief Description");
			getObjectByName("txt_PayNHF_detailDesc").sendKeys(detailedDesc);
			ApplicationLogs.debug("Pay NHF Module: Inserted Detailed Description");
			getObjectByName("btn_PayNHF_Preview").click();
			ApplicationLogs.debug("Pay NHF Module: Clicked Preview Button");
			
			
			if(isAlertPresent()){
				driver.switchTo().alert().accept();
			}
				
			//wait for the preview screen
			getObjectByName("btn_PayNHF_Submit").click();	
			ApplicationLogs.debug("Pay NHF Module: Clicked Submit Button");
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			Assert.assertEquals("Your File has not been successfully uploaded. Pls, try again", "Your File has been successfully uploaded. Please Select Funding Bank", actualText);
			ApplicationLogs.debug("Pay NHF Module: Your File has been successfully uploaded.");
			ApplicationLogs.debug(actualText);
			
			//wait for the Choose Paying Account
			System.out.println("***************3nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			getObjectByXpath("lnk_ProcessTransaction").click();
			ApplicationLogs.debug("Pay NHF Module: Clicked to process transactions");
			
			new Select(getObjectByName("drd_PayNHF_FundingBank")).selectByValue(FundAcct);
			ApplicationLogs.debug("Pay NHF Module: Selected the Funding Bank");
			
			getObjectByName("btn_PayNHF_bankSubmit").click();
			ApplicationLogs.debug("Pay NHF Module: Clicked Submit button");
			
			actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			Assert.assertTrue("Unable to send Payment to Pension for Approval", actualText.startsWith("Vendors/Billers Payment (Batch"));
			ApplicationLogs.debug(actualText);

			
			driver.switchTo().defaultContent();
			
			
			//End of first Iteration
			

			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("NHF Payment Transaction(s) was successfully carried out");
				Assert.assertTrue("NHF Payment Transaction(s) was successfully carried out", true);
				ApplicationLogs.debug("NHF Payment Transaction(s) was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay NHF Test");
				System.out.println("NHF Payment Transaction(s) was unsuccessfully carried out");
				Assert.assertTrue("NHF Payment Transaction(s) was unsuccessfully carried out", false);
				ApplicationLogs.debug("NHF Payment Transaction(s) was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayNHFTest");
					return Arrays.asList(data);
			
					
		}
		
		
}


