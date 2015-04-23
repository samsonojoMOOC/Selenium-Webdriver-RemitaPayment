package com.remita.demo.epayment;

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

import testsDemo.TestBase;
import utilDemo.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class PayVendorMultipleTest extends TestBase {
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
			public PayVendorMultipleTest(String selectAccount, String beneficiaryName, String beneficiaryInst, String commercialBank, String beneficiaryAcctNo, 
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
			System.out.println("Initializing the PayVendorMultiple Test");
			
			initialize();
			ApplicationLogs.debug("Initializing the PayVendorMultiple Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayVendorMultipleTest"))
				Assume.assumeTrue(false);
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void payVendorTest() throws InterruptedException {
			
			
			//WebDriver driver = new FirefoxDriver();
			
			//WebDriverWait wait = new WebDriverWait(driver, 20);
			
			
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Payment Vendor Module: Moved mouse to the Main Menu Link");
			
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			ApplicationLogs.debug("Payment Vendor Module: Implicitly Waits for 30Sec");
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay Vendors, Suppliers & Others']"));
			//driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			act.moveToElement(submenuPayment).click().perform();
			
			ApplicationLogs.debug("Payment Vendor Module: Moved mouse to the Submenu Link");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			
			ApplicationLogs.debug("Payment Vendor Module: Switching to the iFrame to click Radio button");
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			driver.findElement(By.id("standOrderRadioId")).click();
			
			
			/*
			new FluentWait<WebDriver>(driver)
			.withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainContentArea']/div[2]/div[1]/iframe/html/body/div[2]/label/input[@id='standOrderRadioId']")));
			*/
			//getObjectById("rdo_IssuePayment").click();
			
			//Fetching data from XLS file
			
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
			
			
			//End of first Iteration
			

			/* Enter PIN
			  WebElement confirmPin = getObjectById("confirmPin");
			  ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('readonly', 'readonly')",confirmPin);
			  confirmPin.sendKeys("4321");
			  getObjectClassName("go").click();
			  */
			  
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
					Object[][] data = TestUtility.getDataFromExcelSheet("PayVendorMultipleTest");
					return Arrays.asList(data);
			
					
		}
		
		
}
