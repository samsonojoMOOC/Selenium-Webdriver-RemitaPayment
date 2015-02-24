package com.remita.tests.epayment;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

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

	public class PayVendorSingleTest extends TestBase {
		// 2nd Step to parameterization
		
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
			public PayVendorSingleTest(String selectAccount, String beneficiaryName, String beneficiaryInst, String commercialBank, String beneficiaryAcctNo, 
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
			System.out.println("Initializing the PayVendorSingle Test");
			initialize();
			
			// xlsx file
			if(TestUtility.isSkip("PayVendorSingleTest"))
				Assume.assumeTrue(false);
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void payVendorTest() throws InterruptedException {
			
			
			//WebDriver driver = new FirefoxDriver();
			
			//WebDriverWait wait = new WebDriverWait(driver, 20);
			
			
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay Vendors, Suppliers & Others']"));
			//driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			act.moveToElement(submenuPayment).click().perform();
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			driver.findElement(By.id("standOrderRadioId")).click();
			
			//driver.switchTo().frame("body-section");
			System.out.println("***************2nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
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
			driver.findElement(By.id("surName")).sendKeys(beneficiaryName);
			new Select(driver.findElement(By.id("institutionId"))).selectByValue(beneficiaryInst);
			new Select(driver.findElement(By.id("instBank"))).selectByValue(commercialBank);
			
			
			getObjectById("txt_BeneficiaryAcctNo").sendKeys(beneficiaryAcctNo);
			getObjectById("txt_Email").sendKeys(email);
			getObjectById("txt_Phone").sendKeys(phone);
			//driver.findElement(By.id("amount")).sendKeys(amount2Pay);
			//driver.findElement(By.id("confirmAmount")).sendKeys(confirmAmount2Pay);
			//driver.findElement(By.id("narration")).sendKeys(descOfPayment);
			
			getObjectById("txt_Amount2Pay").sendKeys(amount2Pay);
			//getObjectById("txt_ConfirmAmount2Pay").sendKeys(confirmAmount2Pay);
			getObjectById("txt_PaymentDesc").sendKeys(descOfPayment);
			getObjectById("btn_Send4Approval").click();
			
			driver.switchTo().defaultContent();
			
			//Assert.assertEquals(message, expected, actual);
			//Assert.assertEquals(expected, actual);
			
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
				System.out.println("Payment to Vendor Transaction was successfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction was successfully carried out", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Payment to Vendor Transaction was unsuccessfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction was unsuccessfully carried out", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayVendorSingleTest");
					return Arrays.asList(data);
			
					
		}
		
		
}
