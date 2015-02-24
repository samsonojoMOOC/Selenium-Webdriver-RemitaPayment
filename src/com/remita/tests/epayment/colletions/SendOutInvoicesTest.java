package com.remita.tests.epayment.colletions;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

//import junit.framework.Assert;
import org.junit.Assert;


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

	public class SendOutInvoicesTest extends TestBase {
		// 2nd Step to parameterization
		
		//ePayment details here
			
			
			public String selectService;
			public String selectAcct;
			public String payerName;
			public String payerEmail;
			public String payerPhone;
			public String amount2Pay;
			public String paymentDesc;
			public String positiveData;
				
			// 3rd Step
			public SendOutInvoicesTest(String selectService,  String selectAcct, String payerName, String payerEmail,
										String payerPhone, String amount2Pay, String paymentDesc, String positiveData ){
				
				this.selectService = selectService;
				this.selectAcct = selectAcct;
				this.payerName = payerName;
				this.payerEmail = payerEmail;
				this.payerPhone = payerPhone;
				this.amount2Pay = amount2Pay;
				this.paymentDesc = paymentDesc;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			System.out.println("Initializing the SendOutInvoicesTest Test");
			initialize();
			
			// xlsx file
			if(TestUtility.isSkip("SendOutInvoicesTest"))
				Assume.assumeTrue(false);
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void sendOutInvoicesTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[4]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Send Out Invoices']"));
			act.moveToElement(submenuPayment).click().perform();
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
					
			
			
			/*
			new FluentWait<WebDriver>(driver)
			.withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainContentArea']/div[2]/div[1]/iframe/html/body/div[2]/label/input[@id='standOrderRadioId']")));
			
			*/
			
			
			new Select(getObjectById("drd_ServiceType")).selectByValue(selectService);
			new Select(getObjectById("drd_SelectAcct")).selectByValue(selectAcct);
			getObjectById("txt_PayerName").sendKeys(payerName);
			getObjectById("txt_PayerEmial").sendKeys(payerEmail);
			getObjectById("txt_PayerPhone").sendKeys(payerPhone);
			getObjectById("txt_Amount2Pay").sendKeys(amount2Pay);
			getObjectById("txt_PaymentDesc").sendKeys(paymentDesc);
			getObjectById("btn_SendOutInvoice").click();
			
		/*
			String bodyText = driver.findElement(By.tagName("body")).getText();
			Assert.assertTrue("Text not found!", bodyText.contains(text));
			*/
			String actualText = driver.findElement(By.xpath("html/body/div[2]")).getText();
			//System.out.println(actualText);
			
			String expectedText = "Bill Payment has been successfully generated and sent to your payer(s)";
			//Assert.assertEquals(expectedText, actualText);
			Assert.assertEquals("Send Out Invoice was unsuccessful", expectedText, actualText);
			//Assert.assertTrue("Send Out Invoice was unsuccessful", expectedText = actualText);
			
			
					
		
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
				System.out.println("Invoice was sent to Client successfully");
				Assert.assertTrue("Invoice was sent to Client successfully", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Send Invoice Out Test");
				System.out.println("Invoice was unsuccessful");
				Assert.assertTrue("Invoice was unsuccessful", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("SendOutInvoicesTest");
					return Arrays.asList(data);
			
					
		}
		
		
}


