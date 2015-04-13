package com.remita.tests.epayment.BankBranchOps;

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

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class PayPensionViaRRRTest extends TestBase {
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		// 2nd Step to parameterization
	
			public String employerCode;
			public String payerName;
			public String payerEmail;
			public String payerPhone;
			public String address;
			public String tellerNo;
			public String positiveData;
				
			// 3rd Step
			public PayPensionViaRRRTest(String employerCode,  String payerName, String payerEmail, 
										String payerPhone, String address, String tellerNo, String positiveData ){
				
				this.employerCode = employerCode;
				this.payerName = payerName;
				this.payerEmail = payerEmail;
				this.payerPhone = payerPhone;
				this.address = address;
				this.tellerNo = tellerNo;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the PayPensionViaRRR Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayPensionViaRRRTest"))
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
		public void uploadSalariesTest() throws IOException, InterruptedException{
			
								
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions act = new Actions(driver);
			
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[3]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Pay Taxes via RRR Module: Moved mouse to the Main Menu Link");
					
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Process RRR']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the Process RRR");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Switch into the Content iFrame");
			
			getObjectByXpath("lnk_PayPension_ClickRRR").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the RRR link");
			
			getObjectById("lnk_PayPension_PayPension").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the Pay Pension link");
			
			getObjectByName("btn_PayPension_Browse").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\pensioncsvfile.csv", "Open").start();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected and Attached the Tax CSV File");
			
			
			getObjectByName("txt_PayPension_EmployerCode").sendKeys(employerCode);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Employers Code");
			
			getObjectByName("txt_PayPension_PayerName").sendKeys(payerName);
			
			
			getObjectByName("txt_PayPension_PayerEmail").sendKeys(payerEmail);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Payer Email");
			
			getObjectByName("txt_PayPension_PayerPhone").sendKeys(payerPhone);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Payer Phone");
			
			getObjectByName("txt_PayPension_Address").sendKeys(address);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Payer Phone");
			
			getObjectById("btn_PayPension_GenerateRRR").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the Generate RRR button");
			
			getObjectByName("btn_PayPension_Submit").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the Submit button");
			
			getObjectByName("txt_PayPension_TellerNo").sendKeys(tellerNo);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Insert Teller Number");
			
			getObjectByName("btn_PayPension_Send4Approval").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Send for Approval");
			
			
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			
			alert.accept();
	
			String actualText = getObjectByXpath("validate_RRR").getText();
			Assert.assertEquals("The RRR has not been processed successfully", "Awaiting Approval/Print View", actualText);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Validated that transaction successful");
			
						
			
			driver.switchTo().defaultContent();
			
			
			//End of first Iteration
			

			if(positiveData.equals("Y")){
				Assert.assertTrue("Pay Taxes via RRR was successfully carried out", true);
				ApplicationLogs.debug("Pay Taxes via RRR was successfully carried out");
			}else if(positiveData.equals("N")){
				TestUtility.takeScreenShot("PayTaxesViaRRR Test");
				System.out.println("Pay Taxes via RRR was unsuccessfully carried out");
				Assert.assertTrue("Pay Taxes via RRR was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pay Taxes via RRR was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayPensionViaRRRTest");
					return Arrays.asList(data);
			
					
		}
		
}


