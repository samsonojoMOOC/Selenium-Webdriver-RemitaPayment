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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class ReverseWrongTransactionTest extends TestBase {
		// 2nd Step to parameterization
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			

			public String amount;
			public String transRefNo;
			public String positiveData;
				
			// 3rd Step
			public ReverseWrongTransactionTest(String amount, String transRefNo, String positiveData ){
				
				this.amount = amount;
				this.transRefNo = transRefNo;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the ReverseWrongTransaction Test");
			// xlsx file
			if(TestUtility.isSkip("ReverseWrongTransactionTest"))
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
		public void reverseWrongTransactionTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[4]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Reverse Wrong Transaction Module: Moved to the Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()=' Reversal Of Wrong Payment']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Reverse Wrong Transaction Module: Clicked the Reversal Of Wrong Payment Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			
			getObjectByName("txt_ReversalTrans_Amount").sendKeys(amount);
			ApplicationLogs.debug("Reverse Wrong Transaction Module: Inserted Amount to Reverse");
			
			getObjectByName("txt_ReversalTrans_TransRef").sendKeys(transRefNo);
			ApplicationLogs.debug("Reverse Wrong Transaction Module: Inserted Trans Ref Number to Reverse");
			getObjectByXpath("btn_ReversalTrans_Submit").click();
			ApplicationLogs.debug("Reverse Wrong Transaction Module: Clicked the Submit button");
			
			//Wait a while for the WebTable to be visible
			
			getObjectByName("btn_ReversalTrans_Confirm").click();
			ApplicationLogs.debug("Reverse Wrong Transaction Module: Clicked the Confirm button");
			
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
			
			getObjectByXpath("btn_ReversalTrans_Send4Approval").click();
			ApplicationLogs.debug("Reverse Wrong Transaction Module: Send for Approval");
			
			alert.accept();
			
			
			driver.switchTo().defaultContent();
			  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				Assert.assertTrue("Echeque Reversal was sent for Approval successfully", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Reverse Wrong Transaction Test");
				Assert.assertTrue("Unsuccessfully sent Echeque Reversal for Approval", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name for example LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("ReverseWrongTransactionTest");
					return Arrays.asList(data);
			
					
		}
		
		
}



