package com.remita.tests.epayment.BankBranchOps;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;




import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class PayRegisteredBillerTellerTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String Account2PayFrom;
			public String whoToPay;
			public String serviceType;
			public String amount2Pay;
			public String payerName;
			public String payerEmail;
			public String payerPhone;
			public String premium;
			public String standard;
			public String tellerNo;
			public String positiveData;
				
			// 3rd Step
			public PayRegisteredBillerTellerTest(String Account2PayFrom, String whoToPay,  String serviceType, 
												String amount2Pay, String payerName, String payerEmail, 
												String payerPhone, String premium, String standard, 
												String tellerNo, String positiveData ){
				
				this.Account2PayFrom =Account2PayFrom;
				this.whoToPay =whoToPay;
				this.serviceType = serviceType;
				this.amount2Pay = amount2Pay;
				this.payerName = payerName;
				this.payerEmail = payerEmail;
				this.payerPhone = payerPhone;
				this.premium = premium;
				this.standard = standard;
				this.tellerNo = tellerNo;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the PayRegisteredBillerTeller Test");
			
			// xlsx file
			if(TestUtility.isSkip("PayRegisteredBillerTellerTest"))
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
		
		@Test
		public void payRegisteredBillerTellerTest() throws InterruptedException {
			
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[3]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Moved Mouse on the Main Menu");
									
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Process RRR']"));
			//driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			act.moveToElement(submenuPayment).click().perform();
						
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Moved Mouse on the Sub Menu");			
			
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Switched to iFrame for Application Content");
			//Fetching data from XLS file
			

			getObjectByXpath("lnk_PayBiller_ClickRRR").click();
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Clicked the RRR link");
			
			getObjectById("lnk_PayBiller_PayPension").click();
			
			getObjectByName("txt_Who2Pay").sendKeys(whoToPay);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Who to Pay details");
			
						
			new FluentWait<WebDriver> (driver)
			.withTimeout(50, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/li[@class='ui-menu-item']/a[starts-with(@id, 'ui-id-')]")));
			
			driver.findElement(By.xpath("//*[@id='ui-id-1']/li[@class='ui-menu-item']/a[starts-with(@id, 'ui-id-')]")).click();
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Selected Who to Pay details from autosuggest box");
			
			
			new Select(getObjectById("drd_ServiceType")).selectByVisibleText(serviceType);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Selected the Service Type from drop down");
			
			getObjectByName("txt_Amount2Pay").sendKeys(amount2Pay);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Amount to Pay");
			
			getObjectByName("txt_PayBiller_PayerName").sendKeys(payerName);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Payer's Name");
			
			getObjectByName("txt_PayBiller_PayerEmail").sendKeys(payerEmail);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Payer's Email");
			
			getObjectByName("txt_PayBiller_PayerPhone").sendKeys(payerPhone);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Payer's Phone Number");
			
			getObjectById("txt_Premium").sendKeys(premium);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Premium Value");
			
			getObjectById("txt_Standard").sendKeys(standard);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Standard value");
						
			getObjectById("btn_PayBiller_Submit").click(); 
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Clicked the Send for Approval button");
			
			getObjectByName("txt_PayBiller_TellerNo").sendKeys(tellerNo);
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Inputed Teller Number");
			
			getObjectByName("btn_PayBiller_Send4Approval").click();
			ApplicationLogs.debug("Pay Registered Biller Teller Module: Send for Approval");
			Alert al = driver.switchTo().alert();
			al.accept();
			
			//Validate that there is response
			
			String actualText = getObjectByXpath("validate_RRR").getText();
			Assert.assertEquals("The RRR has not been processed successfully", "Awaiting Approval/Print View", actualText);
			ApplicationLogs.debug("Pay Registered Biller via RRR Module: Validated that transaction successful");
			

			
			driver.switchTo().defaultContent();

			
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("loginError_1");
				Assert.assertTrue("Registered Biller Transaction was successfully carried out", true);
				ApplicationLogs.debug("Pay Registered Biller Module: Registered Biller Transaction was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("PayRegisteredBiller");
				Assert.assertTrue("Registered Biller Transaction was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pay Registered Biller Module: Registered Biller Transaction was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data from PayRegisteredBiller Test ");
			
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayRegisteredBillerTellerTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

