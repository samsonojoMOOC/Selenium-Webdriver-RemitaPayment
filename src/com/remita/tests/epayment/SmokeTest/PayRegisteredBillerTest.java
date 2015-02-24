package com.remita.tests.epayment.SmokeTest;

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

	public class PayRegisteredBillerTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String Account2PayFrom;
			public String whoToPay;
			public String serviceType;
			public String amount2Pay;
			public String premium;
			public String standard;
			public String positiveData;
				
			// 3rd Step
			public PayRegisteredBillerTest(String Account2PayFrom, String whoToPay,  String serviceType, String amount2Pay, String premium, String standard, String positiveData ){
				
				this.Account2PayFrom =Account2PayFrom;
				this.whoToPay =whoToPay;
				this.serviceType = serviceType;
				this.amount2Pay = amount2Pay;
				this.premium = premium;
				this.standard = standard; 
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the PayRegisteredBiller Test");
			
			// xlsx file
			if(TestUtility.isSkip("PayRegisteredBillerTest"))
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
		public void payRegisteredBillerTest() throws InterruptedException {
			
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Pay Registered Biller Module: Moved Mouse on the Main Menu");
									
			WebElement submenuPayment = driver.findElement(By.xpath("//*[@id='cpayeeregistration']"));
			//driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			act.moveToElement(submenuPayment).click().perform();
						
			ApplicationLogs.debug("Pay Registered Biller Module: Moved Mouse on the Sub Menu");			
			
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			ApplicationLogs.debug("Pay Registered Biller Module: Switched to iFrame for Application Content");
			//Fetching data from XLS file
			
			new Select(getObjectById("drd_Account2PayFrom")).selectByValue(Account2PayFrom);
			ApplicationLogs.debug("Pay Registered Biller Module: Selected Account to Pay From");
			
			getObjectByName("txt_Who2Pay").sendKeys(whoToPay);
			ApplicationLogs.debug("Pay Registered Biller Module: Inputed Who to Pay details");
			
			new FluentWait<WebDriver> (driver)
			.withTimeout(50, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/li[@class='ui-menu-item']/a[starts-with(@id, 'ui-id-')]")));
			
			driver.findElement(By.xpath("//*[@id='ui-id-1']/li[@class='ui-menu-item']/a[starts-with(@id, 'ui-id-')]")).click();
			ApplicationLogs.debug("Pay Registered Biller Module: Selected Who to Pay details from autosuggest box");
			
			
			new Select(getObjectById("drd_ServiceType")).selectByVisibleText(serviceType);
			ApplicationLogs.debug("Pay Registered Biller Module: Selected the Service Type from drop down");
			
			getObjectByName("txt_Amount2Pay").sendKeys(amount2Pay);
			ApplicationLogs.debug("Pay Registered Biller Module: Inputed Amount to Pay");
			
			getObjectById("txt_Premium").sendKeys(premium);
			ApplicationLogs.debug("Pay Registered Biller Module: Inputed Premium Value");
			
			getObjectById("txt_Standard").sendKeys(standard);
			ApplicationLogs.debug("Pay Registered Biller Module: Inputed Standard value");
						
			getObjectById("btn_SendForApproval").click(); 
			ApplicationLogs.debug("Pay Registered Biller Module: Clicked the Send for Approval button");
			
			//Validate that there is response
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertEquals("Unable to send Payment for Approval", "Vendors/Billers Payment has been forwarded to APPROVER1 for approval.", actualText);
			ApplicationLogs.debug("Pay Registered Biller Module: Payment has been sent successfully for Approval");
			

			
			driver.switchTo().defaultContent();

			
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("loginError_1");
				Assert.assertTrue("Registered Biller Transaction was successfully carried out", true);
				ApplicationLogs.debug("Pay Registered Biller Module: Registered Biller Transaction was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("loginError_2");
				Assert.assertTrue("Registered Biller Transaction was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pay Registered Biller Module: Registered Biller Transaction was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data from PayRegisteredBiller Test ");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayRegisteredBillerTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

