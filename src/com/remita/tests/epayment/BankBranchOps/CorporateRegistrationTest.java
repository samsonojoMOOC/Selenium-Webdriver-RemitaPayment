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

	public class CorporateRegistrationTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			public String corpID;
			public String cacRegNo;
			public String empPencomId;
			public String taxNo;
			public String introducedBy;
			public String corpName;
			public String address;
			public String institutionType;
			public String iniUserID;
			public String iniSurname;
			public String iniOthername;
			public String iniMobileNo;
			public String iniEmail;
			public String apprUserId;
			public String apprSurname;
			public String apprOthername;
			public String apprMobileNo;
			public String apprEmail;
			public String financialInst;
			public String bank;
			public String branch;
			public String account;
			public String transactionLimit;
			public String positiveData;
				
			// 3rd Step
			public CorporateRegistrationTest(String corpID, String cacRegNo, String empPencomId, String taxNo,
											String introducedBy, String corpName, String address, String institutionType,
											String iniUserID, String iniSurname, String iniOthername, String iniMobileNo,
											String iniEmail, String apprUserId, String apprSurname, String apprOthername,
											String apprMobileNo, String apprEmail, String financialInst, String bank,
											String branch, String account, String transactionLimit, String positiveData ){
				
				this.corpID	= corpID;
				this.cacRegNo = cacRegNo;
				this.empPencomId = empPencomId;
				this.taxNo = taxNo;
				this.introducedBy = introducedBy;
				this.corpName = corpName;
				this.address = address;
				this.institutionType = institutionType;
				this.iniUserID = iniUserID;
				this.iniSurname = iniSurname;
				this.iniOthername = iniOthername;
				this.iniMobileNo = iniMobileNo;
				this.iniEmail = iniEmail;
				this.apprUserId = apprUserId;
				this.apprSurname = apprSurname;
				this.apprOthername = apprOthername;
				this.apprMobileNo = apprMobileNo;
				this.apprEmail = apprEmail;
				this.financialInst = financialInst;
				this.bank = bank;
				this.branch = branch;
				this.account = account;
				this.transactionLimit = transactionLimit;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the CorporateRegistration Test");
			
			// xlsx file
			if(TestUtility.isSkip("CorporateRegistrationTest"))
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
		public void corporateRegistrationTest() throws InterruptedException {
			
			
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
			
			getObjectById("lnk_CorpReg_Register").click();
			ApplicationLogs.debug("Standing Order Family Module: Inputed Who to Pay details");
			
			driver.switchTo().frame(1);
			getObjectByName("txt_CorpReg_CorpId").sendKeys(corpID);
			
			getObjectById("btn_CorpReg_SubmitInitial").click();
			
			getObjectByName("txt_CorpReg_CACRegNo").sendKeys(cacRegNo);
			
			getObjectByName("txt_CorpReg_EmpPencomId").sendKeys(empPencomId);
			
			getObjectByName("txt_CorpReg_TaxNo").sendKeys(taxNo);
			
			new Select(getObjectByName("drd_CorpReg_IntroducedBy")).selectByValue(introducedBy);
			
			getObjectByName("txt_CorpReg_CorpName").sendKeys(corpName);
			
			getObjectByName("txt_CorpReg_Address").sendKeys(address);
			
			new Select(getObjectByName("drd_CorpReg_InstitutionType")).selectByValue(institutionType);
			
			getObjectByName("txt_CorpReg_IniUserID").sendKeys(iniUserID);
			
			getObjectByName("txt_CorpReg_IniSurname").sendKeys(iniSurname);
			
			getObjectByName("txt_CorpReg_IniOthername").sendKeys(iniOthername);
			
			getObjectByName("txt_CorpReg_IniMobileNo").sendKeys(iniMobileNo);
			
			getObjectByName("txt_CorpReg_IniEmail").sendKeys(iniEmail);
			
			getObjectByName("txt_CorpReg_ApprUserId").sendKeys(apprUserId);
			
			getObjectByName("txt_CorpReg_ApprSurname").sendKeys(apprSurname);
			
			getObjectByName("txt_CorpReg_ApprOthername").sendKeys(apprOthername);
			
			getObjectByName("txt_CorpReg_ApprMobileNo").sendKeys(apprMobileNo);
			
			getObjectByName("txt_CorpReg_ApprEmail").sendKeys(apprEmail);
			
			new Select(getObjectByName("drd_CorpReg_FinancialInst")).selectByValue(financialInst);
			
			new Select(getObjectByName("drd_CorpReg_Bank")).selectByValue(bank);
			
			new Select(getObjectByName("drd_CorpReg_Branch")).selectByValue(branch);
			
			getObjectByName("txt_CorpReg_Account").sendKeys(account);
			
			getObjectByName("txt_CorpReg_TransactionLimit").sendKeys(transactionLimit);
			
			getObjectByXpath("btn_CorpReg_Submit").click();
			
			//driver.switchTo().frame(2);
			String actualText = getObjectByXpath("val_CorpReg_Validation").getText();
			System.out.println(actualText);
			Assert.assertEquals("Corporate Registration was not successful", corpName.toUpperCase(), actualText);
			ApplicationLogs.debug("Corporate Registration Module: Validated that transaction was successful");
			
			driver.switchTo().defaultContent();

			
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("loginError_1");
				Assert.assertTrue("Corporate Registration was successfully carried out", true);
				ApplicationLogs.debug("Corporate Registration Module: Corporate Registration was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("CorporateRegistration");
				Assert.assertTrue("Corporate Registration was unsuccessfully carried out", false);
				ApplicationLogs.debug("Corporate Registration Module: Standing Order was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data from CorporateRegistration Test ");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("CorporateRegistrationTest");
					return Arrays.asList(data);
			
					
		}
		
		
}


