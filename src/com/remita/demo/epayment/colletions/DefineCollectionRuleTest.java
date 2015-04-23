package com.remita.demo.epayment.colletions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.After;
//import junit.framework.Assert;
import org.junit.Assert;
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

	public class DefineCollectionRuleTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String serviceName;
			public String paymentAcct;
			public String amount2Pay;
			public String whoPaysProccessFee;
			public String customField1;
			public String customField1Type;
			public String customField1Length;
			public String addVat;
			public String status;
			public String positiveData;
				
			// 3rd Step
			public DefineCollectionRuleTest(String serviceName,  String paymentAcct,  String amount2Pay, String whoPaysProccessFee, 
											String customField1, String customField1Type, String customField1Length,  
											String addVat, String status, String positiveData ){
				
				this.serviceName = serviceName;
				this.paymentAcct = paymentAcct;
				this.amount2Pay = amount2Pay;
				this.whoPaysProccessFee = whoPaysProccessFee;
				this.customField1 = customField1;  
				this.customField1Type = customField1Type;
				this.customField1Length = customField1Length;
				this.addVat = addVat;
				this.status = status;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			
			ApplicationLogs.debug("Initializing the DefineCollectionRule Test");
			
			// xlsx file
			if(TestUtility.isSkip("DefineCollectionRuleTest"))
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
		public void defineCollectionRuleTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[4]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Define Collection Rule Module: Moved to the Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Define Collection Rules']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Define Collection Rule Module:: Clicked the Define Collection Rules Menu");
			
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
			
			getObjectByName("txt_CollectRule_ServiceName").sendKeys(serviceName);
			ApplicationLogs.debug("Define Collection Rule Module: Inserted Service Name");			
			new Select(getObjectByName("drd_CollectRule_PaymentAcct")).selectByValue(paymentAcct);
			ApplicationLogs.debug("Define Collection Rule Module: Selected Payment Account");
			new Select(getObjectByName("drd_CollectRule_Amount2Pay")).selectByValue(amount2Pay);
			ApplicationLogs.debug("Define Collection Rule Module: Selected Amount to Pay");
			new Select(getObjectByName("drd_CollectRule_WhoPaysProccessFee")).selectByValue(whoPaysProccessFee);
			ApplicationLogs.debug("Define Collection Rule Module: Selected Who Pays Processing Fee");
			getObjectByName("txt_CollectRule_CustomField1").sendKeys(customField1);
			ApplicationLogs.debug("Define Collection Rule Module: Inserted into the Custom Field");
			new Select(getObjectByName("drd_CollectRule_CustomField1Type")).selectByValue(customField1Type);
			ApplicationLogs.debug("Define Collection Rule Module: Selected Custom Field Type");
			getObjectByName("txt_CollectRule_CustomField1Length").sendKeys(customField1Length);
			ApplicationLogs.debug("Define Collection Rule Module: Inserted Custom Filed Length");
			new Select(getObjectByName("drd_CollectRule_AddVat")).selectByValue(addVat);
			ApplicationLogs.debug("Define Collection Rule Module: Selected Add Vat");
			new Select(getObjectByName("drd_CollectRule_Status")).selectByValue(status);
			ApplicationLogs.debug("Define Collection Rule Module: Selected Status");
			getObjectByName("btn_CollectRule_Save").click();
			ApplicationLogs.debug("Define Collection Rule Module: Clicked Save button");
			
			
			String actualText = driver.findElement(By.xpath("html/body/div[2]")).getText();
			
			
			String expectedText = "Saved Successfully";
			Assert.assertEquals("Define Collection Rule was unsuccessful", expectedText, actualText);
			//Assert.assertTrue("Define Collection Rule was unsuccessful", expectedText = actualText);
			ApplicationLogs.debug("Define Collection Rule Module: We verified that Collection Rule has been successful");
			
					
		
			driver.switchTo().defaultContent();
			

			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Define Collection Rule was successfully");
				Assert.assertTrue("Define Collection Rule was successfully", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("DefineCollectionRule Test");
				System.out.println("Define Collection Rule was unsuccessful");
				Assert.assertTrue("Define Collection Rule was unsuccessful", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("DefineCollectionRuleTest");
					return Arrays.asList(data);
			
					
		}
		
		
}


