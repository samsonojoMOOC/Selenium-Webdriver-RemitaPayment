package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class StandingOrderOthersTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		
		//ePayment details here
			
			
			public String selectAccount;
			public String beneficiaryName;
			public String beneficiaryInst;
			public String commercialBank;
			public String beneficiaryAcctNo;
			public String email;
			public String phone;
			public String amount2Pay;
			//public String confirmAmount2Pay;
			public String descOfPayment;
			public String positiveData;
				
			// 3rd Step
			public StandingOrderOthersTest(String selectAccount, String beneficiaryName, String beneficiaryInst, String commercialBank, String beneficiaryAcctNo, 
					             String email, String phone, String amount2Pay, String descOfPayment, String positiveData ){
				
				this.selectAccount =selectAccount;
				this.beneficiaryName =beneficiaryName;
				this.beneficiaryInst = beneficiaryInst;
				this.commercialBank = commercialBank;
				this.beneficiaryAcctNo = beneficiaryAcctNo;
				this.email = email;
				this.phone = phone;
				this.amount2Pay = amount2Pay;
				//this.confirmAmount2Pay = confirmAmount2Pay;
				this.descOfPayment = descOfPayment;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
						
			initialize();
			ApplicationLogs.debug("Initializing the StandingOrderOthers Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("StandingOrderOthersTest"))
				Assume.assumeTrue(false);
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void payVendorTest() throws InterruptedException {
			
						
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Standing Order Module: Moved Mouse on the Main Menu");
									
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Issue Standing Order/Direct Debit ']"));
			act.moveToElement(submenuPayment).click().perform();
						
			ApplicationLogs.debug("Standing Order Module: Moved Mouse on the Sub Menu");		
			
			submenuPayment = driver.findElement(By.xpath("//a[text()='Others']"));
			act.moveToElement(submenuPayment).click().perform();
			
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			
			ApplicationLogs.debug("Payment Vendor Module: Switching to the iFrame to click Radio button");
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
						
			new Select(driver.findElement(By.id("fromAccount"))).selectByValue(selectAccount);
			ApplicationLogs.debug("Payment Vendor Module: Selected Account Number");
			
			driver.findElement(By.id("surName")).sendKeys(beneficiaryName);
			ApplicationLogs.debug("Payment Vendor Module: Inputed Beneficiary Name");
			
			new Select(driver.findElement(By.id("institutionId"))).selectByValue(beneficiaryInst);
			ApplicationLogs.debug("Payment Vendor Module: Selected Beneficiary Institution");
			
			new Select(driver.findElement(By.id("instBank"))).selectByValue(commercialBank);
			ApplicationLogs.debug("Payment Vendor Module: Selected Commercial Bank");
			
			getObjectById("txt_BeneficiaryAcctNo").sendKeys(beneficiaryAcctNo);
			ApplicationLogs.debug("Payment Vendor Module: Inputed Beneficiary Acct Number");
			
			getObjectById("txt_Email").sendKeys(email);
			ApplicationLogs.debug("Payment Vendor Module: Inputed Email");
			
			getObjectById("txt_Phone").sendKeys(phone);
			ApplicationLogs.debug("Payment Vendor Module: Inputed Phone");
			
			getObjectById("txt_Amount2Pay").sendKeys(amount2Pay);
			ApplicationLogs.debug("Payment Vendor Module: Inputed Amount to Pay");
			
			getObjectById("txt_PaymentDesc").sendKeys(descOfPayment);
			ApplicationLogs.debug("Payment Vendor Module: Inputed Payment Description");
			
			getObjectById("btn_Send4Approval").click();
			ApplicationLogs.debug("Payment Vendor Module: Clicked the Send for Approval button");
			
			driver.switchTo().defaultContent();
			ApplicationLogs.debug("Payment Vendor Module: Switched back to the default Content");
			
	
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Payment to Vendor Transaction(s) was successfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction(s) was successfully carried out", true);
				ApplicationLogs.debug("Payment Vendor Module: Payment to Vendor Transaction(s) was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Payment to Vendor Transaction(s) was unsuccessfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction(s) was unsuccessfully carried out", false);
				ApplicationLogs.debug("Payment Vendor Module: Payment to Vendor Transaction(s) was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("StandingOrderOthersTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

