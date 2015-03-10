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
import org.openqa.selenium.By;
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

	public class BankBranchSetupTest extends TestBase {
		// 2nd Step to parameterization
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			

			public String branchCode;
			public String branchName;
			public String incAcctNo;
			public String maxApprovalLimit;
			public String authType;
			public String surName;
			public String firstName;
			public String otherName;
			public String userName;
			public String email;
			public String mobileNo;
			public String fundHoldingAcct;
			public String approvalAuthType;
			public String surNameApproval;
			public String firstNameApproval;
			public String otherNameApproval;
			public String userNameApproval;
			public String emailApproval;
			public String mobileNoApproval;
			public String positiveData;
				
			// 3rd Step
			public BankBranchSetupTest(String branchCode, String branchName, String incAcctNo, String maxApprovalLimit, String authType, String surName, 
										String firstName, String otherName, String userName, String email, String mobileNo, String fundHoldingAcct, 
										String approvalAuthType, String surNameApproval, String firstNameApproval, String otherNameApproval,
										String userNameApproval, String emailApproval, String mobileNoApproval, String positiveData ){
				
				this.branchCode = branchCode;
				this.branchName = branchName;
				this.incAcctNo = incAcctNo;
				this.maxApprovalLimit = maxApprovalLimit;
				this.authType = authType;
				this.surName = surName;
				this.firstName = firstName;
				this.otherName = otherName;
				this.userName = userName;
				this.email = email;
				this.mobileNo = mobileNo;
				this.fundHoldingAcct = fundHoldingAcct;
				this.approvalAuthType = approvalAuthType;
				this.surNameApproval = surNameApproval;
				this.firstNameApproval = firstNameApproval;
				this.otherNameApproval = otherNameApproval;
				this.userNameApproval = userNameApproval;
				this.emailApproval = emailApproval;
				this.mobileNoApproval = mobileNoApproval;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the BankBranchSetup Test");
			
			// xlsx file
			if(TestUtility.isSkip("BankBranchSetupTest"))
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
		public void bankBranchSetupTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[9]/a"));
		
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("Bank Branch Setup Module: Moved to the Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Administration ']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked the Sub Menu");
			
			WebElement subSubmenuPayment = driver.findElement(By.xpath("//a[text()='Setup Branch & Teller Approval Route']"));
			act.moveToElement(subSubmenuPayment).click().perform();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked the Bank Branch Setup Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			getObjectById("btn_BranchSetup_CreateNew").click();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked the Create New User Button");
			
			getObjectByName("txt_BranchSetup_BranchCode").sendKeys(branchCode);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted the Bank Branch Code");
						
			getObjectByXpath("txt_BranchSetup_BranchName").sendKeys(branchName);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted the Bank Branch Name");
			
			getObjectByXpath("txt_BranchSetup_IncAcctNo").sendKeys(incAcctNo);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted the Bank Income Account Number");
			
			getObjectByXpath("txt_BranchSetup_MaxApprovalLimit").clear();
			getObjectByXpath("txt_BranchSetup_MaxApprovalLimit").sendKeys(maxApprovalLimit);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Maximum Approval Limit");
			
			new Select(getObjectByXpath("drd_BranchSetup_TellerAuthType")).selectByValue(authType);
			ApplicationLogs.debug("Bank Branch Setup Module: Selected Authentication Type");
			
			//Adding New Teller User
			
			getObjectByXpath("btn_BranchSetup_AddNewUser").click();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked the Add New User");
			
			getObjectByName("txt_BranchSetup_Surname").sendKeys(surName);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Teller's Surname");
			
			getObjectByName("txt_BranchSetup_Firstname").sendKeys(firstName);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted the Teller's First Name");
			
			getObjectByName("txt_BranchSetup_Othername").sendKeys(otherName);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Teller's Other Name");
			
			getObjectByName("txt_BranchSetup_Username").sendKeys(userName);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Teller's User Name");
			
			getObjectByName("txt_BranchSetup_Email").sendKeys(email);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Teller's Email");
			
			getObjectByName("txt_BranchSetup_PhoneNo").sendKeys(mobileNo);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Teller's Mobile Number");
			
			getObjectByXpath("btn+BranchSetup_Save").click();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked the Save button");
			
			//The Approval Section
			
			getObjectByXpath("txt_BranchSetup_FundHoldingAcct").sendKeys(fundHoldingAcct);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted the Fund Holding Account");
			
			new Select(getObjectByXpath("drd_BranchSetup_ApproverAuthType")).selectByValue(approvalAuthType);
			ApplicationLogs.debug("Bank Branch Setup Module: Selected the Approval's Authentication Type");
			
			getObjectByXpath("btn_BranchSetup_ApprovalAddNewUser").click();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked Add New User Button");
			
			//Add the New Approval User
			
			getObjectByName("txt_BranchSetup_Surname").sendKeys(surNameApproval);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Approval's Surname");
			
			getObjectByName("txt_BranchSetup_Firstname").sendKeys(firstNameApproval);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted the Approval's First Name");
			
			getObjectByName("txt_BranchSetup_Othername").sendKeys(otherNameApproval);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Approval's Other Name");
			
			getObjectByName("txt_BranchSetup_Username").sendKeys(userNameApproval);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Approval's User Name");
			
			getObjectByName("txt_BranchSetup_Email").sendKeys(emailApproval);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Approval's Email");
			
			getObjectByName("txt_BranchSetup_PhoneNo").sendKeys(mobileNoApproval);
			ApplicationLogs.debug("Bank Branch Setup Module: Inserted Approval's Mobile Number");
			
			getObjectByXpath("btn+BranchSetup_Save").click();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked the Save button");
			
			getObjectById("btn_BranchSetup_Submit").click();
			ApplicationLogs.debug("Bank Branch Setup Module: Clicked the Submit button");
			
			new FluentWait <WebDriver> (driver)
			.withTimeout(160, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[2]")));
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText();
			Assert.assertEquals("Bank Branch Setup was unsuccessful",  "Branch Setup has been forwarded to ACHOGU U. for approval.", actualText);
			ApplicationLogs.debug("Bank Branch Setup Module: We verified that Bank Branch Setup has been successful");
			
	
			driver.switchTo().defaultContent();
			  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				Assert.assertTrue("Bank Branch Setup was successfully", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("BankBranchSetup Test");
				Assert.assertTrue("Bank Branch Setup was not successfully carried out", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name for example LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("BankBranchSetupTest");
					return Arrays.asList(data);
			
					
		}
		
		
}



