package com.remita.demo.epayment.BankBranchOps;

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

import testsDemo.TestBase;
import utilDemo.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class PayTaxesRRRTest extends TestBase {
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		// 2nd Step to parameterization
	
			public String taxType;
			public String employerTIN;
			public String payerEmail;
			public String payerPhone;
			public String tellerNo;
			public String positiveData;
				
			// 3rd Step
			public PayTaxesRRRTest(String taxType,  String employerTIN, String payerEmail, 
										String payerPhone, String tellerNo, String positiveData ){
				
				this.taxType = taxType;
				this.employerTIN = employerTIN;
				this.payerEmail = payerEmail;
				this.payerPhone = payerPhone;
				this.tellerNo = tellerNo;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the PayTaxesRRR Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayTaxesRRRTest"))
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
			
			getObjectByXpath("lnk_PayTaxes_ClickRRR").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the RRR link");
			
			getObjectById("lnk_PayTaxes_PayTax").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the Pay Tax link");
			
			getObjectByName("btn_PayTaxes_Browse").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\taxcsvfile.csv", "Open").start();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected and Attached the Tax CSV File");
			
				
			new Select(getObjectByName("drd_PayTaxes_TaxType")).selectByValue(taxType);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Tax Type");
			
			getObjectByName("txt_PayTaxes_EmployerTIN").sendKeys(employerTIN);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Employers TIN");
			
			getObjectByName("txt_PayTaxes_PayerEmail").sendKeys(payerEmail);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Payer Email");
			
			getObjectByName("txt_PayTaxes_PayerPhone").sendKeys(payerPhone);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Selected Payer Phone");
			
			
			getObjectById("btn_PayTaxes_GenerateRRR").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the Generate RRR button");
			
			getObjectByName("btn_PayTaxes_Submit").click();
			ApplicationLogs.debug("Pay Taxes via RRR Module: Clicked the Submit button");
			
			getObjectByName("txt_PayTaxes_TellerNo").sendKeys(tellerNo);
			ApplicationLogs.debug("Pay Taxes via RRR Module: Insert Teller Number");
			
			getObjectByName("btn_PayTaxes_Send4Approval").click();
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
					Object[][] data = TestUtility.getDataFromExcelSheet("PayTaxesRRRTest");
					return Arrays.asList(data);
			
					
		}
		
}


