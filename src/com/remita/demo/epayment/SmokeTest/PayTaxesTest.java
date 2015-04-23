

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
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

	public class PayTaxesTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		
		//ePayment details here
			
			
			public String acct2PayFrom;
			public String taxType;
			public String payAmount;
			public String taxTin;
			public String positiveData;
				
			// 3rd Step
			public PayTaxesTest(String acct2PayFrom, String taxType, String payAmount, String taxTin, String positiveData ){
				
				
				this.acct2PayFrom =acct2PayFrom;
				this.taxType = taxType;
				this.payAmount = payAmount;
				this.taxTin = taxTin;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
						
			initialize();
			ApplicationLogs.debug("Initializing the PayTaxes Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("PayTaxesTest"))
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
		public void payTaxesTest() throws InterruptedException {
			
			
			//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);		
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Pay Taxes Module: Moved mouse to the Main Menu Link");
			
			
			
			new FluentWait<WebDriver>(driver)
		       .withTimeout(120, TimeUnit.SECONDS)
		       .pollingEvery(5, TimeUnit.SECONDS)
		       .ignoring(ElementNotVisibleException.class)
		       //ElementNotVisibleException
		       .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Pay Taxes']")));
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay Taxes']"));
			act.moveToElement(submenuPayment).click().perform();
			
			ApplicationLogs.debug("Pay Taxes Module: Moved mouse to the Submenu Link");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			
			ApplicationLogs.debug("Pay Taxes Module: Switching to the iFrame");
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			
				
			//Fetching data from XLS file
			
			new Select(getObjectById("drd_Acct2PayFrom")).selectByValue(acct2PayFrom);
						
			ApplicationLogs.debug("Pay Taxes Module: Selected Account to Pay from");
			
			
			new Select(getObjectById("drd_TaxType")).selectByValue(taxType);
			ApplicationLogs.debug("Pay Taxes Module: Selected Tax Type");
			
			getObjectById("txt_Amt2Pay").sendKeys(payAmount);
			
			
			ApplicationLogs.debug("Pay Taxes Module: Inputed Amount to Pay");
			
			getObjectById("txt_TaxTin").sendKeys(taxTin);
			ApplicationLogs.debug("Pay Taxes Module: Inputed Tax Tin");
			
			
			getObjectById("btn_Send4Approval").click();
			ApplicationLogs.debug("Pay Taxes Module: Clicked the Send for Approval button");
			
			driver.switchTo().defaultContent();
			ApplicationLogs.debug("Pay Taxes Module: Switched back to the default Content");
			
			
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Taxes Transaction(s) was successfully carried out");
				Assert.assertTrue("Taxes  Transaction(s) was successfully carried out", true);
				ApplicationLogs.debug("Pay Taxes Module: Taxes Transaction(s) was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Taxes  Transaction(s) was unsuccessfully carried out");
				Assert.assertTrue("Taxes Transaction(s) was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pay Taxes Module: Taxes Transaction(s) was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayTaxesTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

