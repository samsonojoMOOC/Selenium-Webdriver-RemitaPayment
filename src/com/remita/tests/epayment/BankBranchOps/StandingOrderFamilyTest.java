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

	public class StandingOrderFamilyTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String benefitInst;
			public String commercialBank;
			public String benefitAcctNo;
			public String benefitName;
			public String email;
			public String phone;
			public String amount2Pay;
			public String payerAcctNo;
			public String payerName;
			public String payerEmail;
			public String payerPhone;
			public String frequency;
			public String paymentDesc;
			public String startMonth;
			public String startYear;
			public String startMonth1;
			public String startYr;
			public String positiveData;
				
			// 3rd Step
			public StandingOrderFamilyTest(String benefitInst, String commercialBank, String benefitAcctNo, String benefitName,
											String email, String phone, String amount2Pay, String payerAcctNo, String payerName,
											String payerEmail, String payerPhone, String frequency, String paymentDesc,
											String startMonth, String startYear, String startMonth1, String startYr, String positiveData ){
				
				this.benefitInst = benefitInst;
				this.commercialBank = commercialBank;
				this.benefitAcctNo = benefitAcctNo;
				this.benefitName = benefitName;
				this.email = email;
				this.phone = phone;
				this.amount2Pay = amount2Pay;
				this.payerAcctNo = payerAcctNo;
				this.payerName = payerName;
				this.payerEmail = payerEmail;
				this.payerPhone = payerPhone;
				this.frequency = frequency;
				this.paymentDesc = paymentDesc;
				this.startMonth = startMonth;
				this.startYear = startYear;
				this.startMonth1 = startMonth1;
				this.startYr = startYr;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the StandingOrderFamily Test");
			
			// xlsx file
			if(TestUtility.isSkip("StandingOrderFamilyTest"))
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
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[3]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Standing Order Family Module: Moved Mouse on the Main Menu");
									
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Process RRR']"));
			//driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			act.moveToElement(submenuPayment).click().perform();
						
			ApplicationLogs.debug("Standing Order Family Module: Moved Mouse on the Sub Menu");			
			
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			ApplicationLogs.debug("Standing Order Family Module: Switched to iFrame for Application Content");
			//Fetching data from XLS file
			
			getObjectByXpath("lnk_PayBiller_ClickRRR").click();
			ApplicationLogs.debug("Standing Order Family Module: Clicked the RRR link");
			
			getObjectById("lnk_DirectDebit_StandingOrder").click();
			ApplicationLogs.debug("Standing Order Family Module: Inputed Who to Pay details");
			
			getObjectByXpath("lnk_StandingFam_StdOrdLink").click();
			ApplicationLogs.debug("Standing Order Family Module: Clicked the Standing Order Link");
			
			driver.switchTo().frame(0);
			
			new Select(getObjectByName("drd_StandingFam_BenefitInst")).selectByValue(benefitInst);
			ApplicationLogs.debug("Standing Order Family Module: Selected Benefitiary Institution");
			
			new Select(getObjectByName("drd_StandingFam_CommercialBank")).selectByValue(commercialBank);
			ApplicationLogs.debug("Standing Order Family Module: Selected Commercial Bank");
			
			getObjectByName("txt_StandingFam_BenefitAcctNo").sendKeys(benefitAcctNo);
			ApplicationLogs.debug("Standing Order Family Module: Inserted the Benefitiary Acct No");
			
			getObjectByName("txt_StandingFam_BenefitName").sendKeys(benefitName);
			ApplicationLogs.debug("Standing Order Family Module: Inserted the Benefitiary Acct No");
			
			getObjectByName("txt_StandingFam_Email").sendKeys(email);
			ApplicationLogs.debug("Standing Order Family Module: Inserted Benefitiary Email");
			
			getObjectByName("txt_StandingFam_Phone").sendKeys(phone);
			ApplicationLogs.debug("Standing Order Family Module: Inserted Benefitiary Phone No");
			
			getObjectByName("txt_StandingFam_Amount2Pay").sendKeys(amount2Pay);
			ApplicationLogs.debug("Standing Order Family Module: Inserted Amount to Pay");
			
			getObjectByName("txt_StandingFam_PayerAcctNo").sendKeys(payerAcctNo);
			ApplicationLogs.debug("Standing Order Family Module: Inserted Payer Acct No");
			
			getObjectByName("txt_StandingFam_PayerName").sendKeys(payerName);
			ApplicationLogs.debug("Standing Order Family Module: Inserted Payer Name");
			
			getObjectByName("txt_StandingFam_PayerEmail").sendKeys(payerEmail);
			ApplicationLogs.debug("Standing Order Family Module: Inserted Payer Email");
			
			getObjectByName("txt_StandingFam_PayerPhone").sendKeys(payerPhone);
			ApplicationLogs.debug("Standing Order Family Module: Inserted Payer Phone");
			
			new Select(getObjectByName("drd_StandingFam_Frequency")).selectByValue(frequency);
			ApplicationLogs.debug("Standing Order Family Module: Selected the Frequency");
			
			getObjectByName("txt_StandingFam_StartDate").click();
			ApplicationLogs.debug("Standing Order Family Module: Clicked the Start Date");
			
			new Select(getObjectByXpath("drd_StandingFam_Month")).selectByValue(startMonth);
			ApplicationLogs.debug("Standing Order Family Module: Selected the Start Month");
			
			new Select(getObjectByXpath("drd_StandingFam_Year")).selectByValue(startYear);
			ApplicationLogs.debug("Standing Order Family Module: Selected the Start Year");
			
			getObjectByXpath("lnk_StandingFam_March10").click();
			ApplicationLogs.debug("Standing Order Family Module: Clicked the Date");
			
			getObjectByName("txt_StandingFam_EndDate").click();
			ApplicationLogs.debug("Standing Order Family Module: Clicked the End Date");
			
			new Select(getObjectByXpath("drd_StandingFam_Month")).selectByValue(startMonth1);
			ApplicationLogs.debug("Standing Order Family Module: Selected the End Month");
			
			new Select(getObjectByXpath("drd_StandingFam_Year")).selectByValue(startYr);
			ApplicationLogs.debug("Standing Order Family Module: Selected the End Year");
			
			getObjectByXpath("lnk_StandingFam_March9").click();
			ApplicationLogs.debug("Standing Order Family Module: Clicked the other date");
			getObjectByName("txt_StandingFam_PaymentDesc").sendKeys(paymentDesc);
			ApplicationLogs.debug("Standing Order Family Module: Inserted the Payment Description");
			getObjectById("btn_StanidngFam_Send4Approval").click();
			
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText();
			System.out.println(actualText);
			Assert.assertTrue("The Standing Order has not been forwarded for approval successfully", actualText.startsWith("Mandate "));
			ApplicationLogs.debug("Standing Order RRR Module: Validated that transaction was successful");
			
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
			System.out.println("Collecting data from StandingOrderDate Test ");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("StandingOrderFamilyTest");
					return Arrays.asList(data);
			
					
		}
		
		
}


