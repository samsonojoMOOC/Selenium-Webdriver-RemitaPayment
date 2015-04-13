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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class PayVendorsAndSuppliersTest extends TestBase {
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		// 2nd Step to parameterization
	
			public String benefitInst;
			public String commercialBank;
			public String benefitAcctNo;
			public String benefitName;
			public String email;
			public String phone;
			public String amount;
			public String payerName;
			public String payerEmail;
			public String payerPhone;
			public String paymentDesc;
			public String tellerNo;
			public String positiveData;
				
			// 3rd Step
			public PayVendorsAndSuppliersTest(String benefitInst, String commercialBank, String benefitAcctNo, 
											String benefitName, String email, String phone,	String amount,
											String payerName, String payerEmail, String payerPhone, String paymentDesc, 
											String tellerNo, String positiveData ){
				
				this.benefitInst = benefitInst;
				this.commercialBank = commercialBank;
				this.benefitAcctNo =  benefitAcctNo;
				this.benefitName = benefitName;
				this.email = email;
				this.phone = phone;
				this.amount = amount;
				this.payerName = payerName;
				this.payerEmail = payerEmail;
				this.payerPhone = payerPhone;
				this.paymentDesc = paymentDesc;
				this.tellerNo = tellerNo;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the PayVendorsAndSuppliers Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayVendorsAndSuppliersTest"))
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
		public void payVendorsAndSuppliersTest() throws IOException, InterruptedException{
			
								
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions act = new Actions(driver);
			
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[3]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Pay Vendors and Suppliers Module: Moved mouse to the Main Menu Link");
					
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Process RRR']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Pay Vendors and Suppliers Module: Clicked the Process RRR");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			ApplicationLogs.debug("Pay Vendors and Suppliers Module: Switch into the Content iFrame");
			
			getObjectByXpath("lnk_PayVendors_ClickRRR").click();
			ApplicationLogs.debug("Pay Vendors and Suppliers Module: Clicked the RRR link");
			
			getObjectById("lnk_PayVendors_VendorLink").click();
			ApplicationLogs.debug("Pay Vendors and Suppliers Module: Clicked the Pay Vendor and Supplier link");
			
			System.out.println("*******Cheking**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			driver.switchTo().frame(0);
			
			new Select(getObjectByName("drd_PayVendors_BenefitInst")).selectByValue(benefitInst);
			
			ApplicationLogs.debug("Pay Salary via RRR Module: Selected Benefitiary's Name");
			
			new Select(getObjectByName("drd_PayVendors_CommercialBank")).selectByValue(commercialBank);
			ApplicationLogs.debug("Pay Salary via RRR Module: Selected Commercial Bank");
			
			getObjectByName("txt_PayVendors_BenefitAcctNo").sendKeys(benefitAcctNo);;
			ApplicationLogs.debug("Pay Salary via RRR Module: Inserted Benefitiary Account Number");
			
			getObjectByName("txt_PayVendors_BenefitiaryName").sendKeys(benefitName);
			ApplicationLogs.debug("Pay Salary via RRR Module: Inserted Benefitiary Name");
			
			getObjectByName("txt_PayVendors_Email").clear();
			getObjectByName("txt_PayVendors_Email").sendKeys(email);
			ApplicationLogs.debug("Pay Salary via RRR Module: Inserted Benefitiary Email");
			
			getObjectByName("txt_PayVendors_Phone").clear();
			getObjectByName("txt_PayVendors_Phone").sendKeys(phone);
			ApplicationLogs.debug("Pay Salary via RRR Module: Inserted Benefitiary Phone");
			
			getObjectByName("txt_PayVendors_Amount").sendKeys(amount);
			ApplicationLogs.debug("Pay Salary via RRR Module: Inserted Amount");
			
			getObjectByName("txt_PayVendors_PayerName").sendKeys(payerName);
			
			getObjectByName("txt_PayVendors_PayerEmail").clear();
			getObjectByName("txt_PayVendors_PayerEmail").sendKeys(payerEmail);
			
			getObjectByName("txt_PayVendors_PayerPhone").clear();
			getObjectByName("txt_PayVendors_PayerPhone").sendKeys(payerPhone);
			
			getObjectByName("txt_PayVendors_PaymentDesc").sendKeys(paymentDesc);
			
			getObjectById("btn_PayVendors_Continue").click();
			ApplicationLogs.debug("Pay Salary via RRR Module: Click to Send to Approval");
			
			getObjectById("txt_PayVendors_TellerNo").sendKeys(tellerNo);
			ApplicationLogs.debug("Pay Salary via RRR Module: Inserted Teller's Number");
			
			getObjectByName("btn_PayVendors_Send4Approval").click();
			ApplicationLogs.debug("Pay Salary via RRR Module: Click to Send to Approval");
			
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			
			alert.accept();
			
			String actualText = getObjectByXpath("validate_RRR").getText();
			Assert.assertEquals("The RRR has not been processed successfully", "Awaiting Approval/Print View", actualText);
			ApplicationLogs.debug("Pay Salary via RRR Module: Validated that transaction successful");
			
						
			
			driver.switchTo().defaultContent();
			
			
			//End of first Iteration
			

			if(positiveData.equals("Y")){
				Assert.assertTrue("Pay Salary via RRR was successfully carried out", true);
				ApplicationLogs.debug("Pay Salary via RRR was successfully carried out");
			}else if(positiveData.equals("N")){
				TestUtility.takeScreenShot("PaySalaryViaRRR Test");
				System.out.println("Pay Salary via RRR was unsuccessfully carried out");
				Assert.assertTrue("Pay Salary via RRR was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pay Salary via RRR was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayVendorsAndSuppliersTest");
					return Arrays.asList(data);
			
					
		}
		
}


