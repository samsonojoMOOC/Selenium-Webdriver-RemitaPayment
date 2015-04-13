package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
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
			public String frequency;
			public String startMonth;
			public String startYear;
			public String startMonth1;
			public String startYr;
			public String descOfPayment;
			public String positiveData;
				
			// 3rd Step
			public StandingOrderOthersTest(String selectAccount, String beneficiaryName, String beneficiaryInst,
											String commercialBank, String beneficiaryAcctNo, String email, 
											String phone, String amount2Pay, String frequency, String startMonth,
											String startYear, String startMonth1, String startYr, 
											String descOfPayment, String positiveData ){
				
				this.selectAccount =selectAccount;
				this.beneficiaryName =beneficiaryName;
				this.beneficiaryInst = beneficiaryInst;
				this.commercialBank = commercialBank;
				this.beneficiaryAcctNo = beneficiaryAcctNo;
				this.email = email;
				this.phone = phone;
				this.amount2Pay = amount2Pay;
				this.frequency = frequency;
				this.startMonth = startMonth;
				this.startYear = startYear;
				this.startMonth1 = startMonth1;
				this.startYr = startYr;
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
		public void standingOrderOtherTest() throws InterruptedException {
			
						
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Standing Order Other Module: Moved Mouse on the Main Menu");
									
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Issue Standing Order/Direct Debit ']"));
			act.moveToElement(submenuPayment).click().perform();
						
			ApplicationLogs.debug("Standing Order Other Module: Moved Mouse on the Sub Menu");		
			
			submenuPayment = driver.findElement(By.xpath("//a[text()='Others']"));
			act.moveToElement(submenuPayment).click().perform();
			
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			
			ApplicationLogs.debug("Payment Vendor Module: Switching to the iFrame to click Radio button");
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
						
			new Select(getObjectByName("drd_StandingOO_Account2PayFrom")).selectByValue(selectAccount);
			ApplicationLogs.debug("Standing Order Other Module: Selected Account Number");
			
			getObjectByName("txt_StandingOO_BenefitName").sendKeys(beneficiaryName);
			ApplicationLogs.debug("Standing Order Other Module: Inputed Beneficiary Name");
			
			new Select(getObjectByName("drd_StandingOO_BenefitInst")).selectByValue(beneficiaryInst);
			ApplicationLogs.debug("Standing Order Other Module: Selected Beneficiary Institution");
			
			new Select(getObjectByName("drd_StandingOO_CommercialBank")).selectByValue(commercialBank);
			ApplicationLogs.debug("Standing Order Other Module: Selected Commercial Bank");
			
			getObjectByName("txt_StandingOO_BenefitAcctNo").sendKeys(beneficiaryAcctNo);
			ApplicationLogs.debug("Standing Order Other Module: Inputed Beneficiary Acct Number");
			
			getObjectByName("txt_StandingOO_Email").sendKeys(email);
			ApplicationLogs.debug("Standing Order Other Module: Inputed Email");
			
			getObjectByName("txt_StandingOO_Phone").sendKeys(phone);
			ApplicationLogs.debug("Standing Order Other Module: Inputed Phone");
			
			getObjectByName("txt_StandingOO_Amount2Pay").sendKeys(amount2Pay);
			ApplicationLogs.debug("Standing Order Other Module: Inputed Amount to Pay");
			
			new Select(getObjectByName("drd_StandingOO_Frequency")).selectByValue(frequency);
			ApplicationLogs.debug("Standing Order Other Module: Selected the Frequency of Standing Order");
			
			getObjectByName("txt_StandingOO_StartDate").click();
			ApplicationLogs.debug("Standing Order Other Module: Clicked the Date");
			
			new Select(getObjectByXpath("drd_StandingOO_Month")).selectByValue(startMonth);
			ApplicationLogs.debug("Standing Order Other Module: Selected Beginning Month");
			
			new Select(getObjectByXpath("drd_StandingOO_Year")).selectByValue(startYear);
			ApplicationLogs.debug("Standing Order Other Module: Selected Beginning Year");
			
			getObjectByXpath("lnk_StandingOO_March10").click();
			ApplicationLogs.debug("Standing Order Other Module: Clicked the Day beginning");
			
			getObjectByName("txt_StandingOO_EndDate").click();
			ApplicationLogs.debug("Standing Order Other Module: Clicked the Date");
			
			new Select(getObjectByXpath("drd_StandingOO_Month")).selectByValue(startMonth1);
			ApplicationLogs.debug("Standing Order Other Module: Selected Beginning Month");
			
			new Select(getObjectByXpath("drd_StandingOO_Year")).selectByValue(startYr);
			ApplicationLogs.debug("Standing Order Other Module: Selected Beginning Year");
			
			getObjectByXpath("lnk_StandingOO_March9").click();
			ApplicationLogs.debug("Standing Order Other Module: Clicked the Day ending");
			
			getObjectByName("txt_StandingOO_PaymentDesc").sendKeys(descOfPayment);
			ApplicationLogs.debug("Standing Order Other Module: Inputed Payment Description");
			
			new Select(getObjectByName("drd_StandingOO_Frequency")).selectByValue(frequency);
			ApplicationLogs.debug("Standing Order Other Module: Selected the Frequency of Standing Order");
			
			getObjectById("btn_StanidngOO_Send4Approval").click();
			ApplicationLogs.debug("Standing Order Other Module: Clicked the Send for Approval button");
			
			//Validate that there is response
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Standing Order has not been successfully forwarded for Approval", actualText.startsWith("STANDING ORDER/DIRECT DEBIT (Batch"));
			ApplicationLogs.debug("Standing Order Other Module: Standing Order has been sent successfully for Approval");
			
			
			driver.switchTo().defaultContent();
			ApplicationLogs.debug("Standing Order Other Module: Switched back to the default Content");
			
	
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Standing Order Transaction(s) was successfully carried out");
				Assert.assertTrue("Standing Order Transaction(s) was successfully carried out", true);
				ApplicationLogs.debug("Standing Order Other Module: Payment to Vendor Transaction(s) was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Standing Order Transaction(s) was unsuccessfully carried out");
				Assert.assertTrue("Standing Order Transaction(s) was unsuccessfully carried out", false);
				ApplicationLogs.debug("Standing Order Other Module: Payment to Vendor Transaction(s) was unsuccessfully carried out");
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

