package com.remita.demo.epayment.SmokeTest;

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

import testsDemo.TestBase;
import utilDemo.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class StandingOrderDDDTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String acct2PayFrom;
			public String whoToPay;
			public String serviceType;
			public String amount2Pay;
			public String premium;
			public String standard;
			public String frequency;
			public String startMonth;
			public String startYear;
			public String noOfTime;
			public String endDate;
			public String positiveData;
				
			// 3rd Step
			public StandingOrderDDDTest(String acct2PayFrom, String whoToPay,  String serviceType, String amount2Pay, 
										String premium, String standard, String frequency, 
										String startMonth, String startYear, String noOfTime, String endDate, 
										String positiveData ){
				
				this.acct2PayFrom =acct2PayFrom;
				this.whoToPay =whoToPay;
				this.serviceType = serviceType;
				this.amount2Pay = amount2Pay;
				this.premium = premium;
				this.standard = standard;
				this.frequency = frequency;
				this.startMonth = startMonth;
				this.startYear = startYear;
				this.noOfTime = noOfTime;
				this.endDate = endDate; 
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the StandingOrderDDD Test");
			
			// xlsx file
			if(TestUtility.isSkip("StandingOrderDDDTest"))
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
		public void standingOrderDDDTest() throws InterruptedException {
			
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Standing Order Module: Moved Mouse on the Main Menu");
									
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Issue Standing Order/Direct Debit ']"));
			act.moveToElement(submenuPayment).click().perform();
						
			ApplicationLogs.debug("Standing Order Module: Moved Mouse on the Sub Menu");		
			
			submenuPayment = driver.findElement(By.xpath("//a[text()='Billers']"));
			act.moveToElement(submenuPayment).click().perform();
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			ApplicationLogs.debug("Standing Order Module: Switched to iFrame for Application Content");
			//Fetching data from XLS file
			
			new Select(getObjectByName("drd_StandingO_Account2PayFrom")).selectByValue(acct2PayFrom);
			ApplicationLogs.debug("Standing Order Module: Selected Account to Pay From");
			
			getObjectByName("txt_StandingO_Who2Pay").sendKeys(whoToPay);
			ApplicationLogs.debug("Standing Order Module: Inputed Who to Pay details");
			
			new FluentWait<WebDriver> (driver)
			.withTimeout(50, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/li[@class='ui-menu-item']/a[starts-with(@id, 'ui-id-')]")));
			
			driver.findElement(By.xpath("//*[@id='ui-id-1']/li[@class='ui-menu-item']/a[starts-with(@id, 'ui-id-')]")).click();
			ApplicationLogs.debug("Standing Order Module: Selected Who to Pay details from autosuggest box");
			

			new Select(getObjectByXpath("drd_StandingO_ServiceType")).selectByVisibleText(serviceType);
			ApplicationLogs.debug("Standing Order Module: Selected the Service Type from drop down");
			
			getObjectByName("txt_StandingO_Amount2Pay").sendKeys(amount2Pay);
			ApplicationLogs.debug("Standing Order Module: Inputed Amount to Pay");
			
			getObjectByName("txt_StandingO_Premium").sendKeys(premium);
			ApplicationLogs.debug("Standing Order Module: Inputed Premium value");
			
			getObjectByName("txt_StandingO_Standard").sendKeys(standard);
			ApplicationLogs.debug("Standing Order Module: Inputed Standard Value");
			
			new Select(getObjectByName("drd_StandingO_Frequency")).selectByValue(frequency);
			ApplicationLogs.debug("Standing Order Module: Selected the Frequency of Transaction");
			
			getObjectByName("txt_StandingO_StartDate").click();
			ApplicationLogs.debug("Standing Order Module: Clicked start date");
			
			new Select(getObjectByXpath("drd_StandingO_Month")).selectByValue(startMonth);
			ApplicationLogs.debug("Standing Order Module: Selected start month");
			
			new Select(getObjectByXpath("drd_StandingO_Year")).selectByValue(startYear);
			ApplicationLogs.debug("Standing Order Module: Selected End month");
			
			getObjectByXpath("lnk_StandingO_March10").click();
			ApplicationLogs.debug("Standing Order Module: Clicked the March 10");
			
			getObjectByName("txt_StandingO_NoOfTime").clear();
			getObjectByName("txt_StandingO_NoOfTime").sendKeys(noOfTime);
			ApplicationLogs.debug("Standing Order Module: Inserted No of Time");
			
			getObjectById("btn_StandingO_Send4Approval").click(); 
			ApplicationLogs.debug("Standing Order Module: Clicked the Send for Approval button");
			
			//Validate that there is response
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Standing Order has not been successfully forwarded for Approval", actualText.startsWith("STANDING ORDER/DIRECT DEBIT (Batch"));
			ApplicationLogs.debug("Standing Order Module: Standing Order has been sent successfully for Approval");
					
			driver.switchTo().defaultContent();

			
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("loginError_1");
				Assert.assertTrue("Standing Order was successfully carried out", true);
				ApplicationLogs.debug("Standing Order Module: Standing Order was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("StandingOrder");
				Assert.assertTrue("Standing Order was unsuccessfully carried out", false);
				ApplicationLogs.debug("Standing Order Module: Standing Order was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data from StandingOrderDDD Test ");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("StandingOrderDDDTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

