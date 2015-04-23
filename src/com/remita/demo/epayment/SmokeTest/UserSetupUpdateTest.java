package com.remita.demo.epayment.SmokeTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import testsDemo.TestBase;
import utilDemo.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class UserSetupUpdateTest extends TestBase {
		// 2nd Step to parameterization
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			public String selectUser;
			public String surname;
			public String firstName;
			public String otherNames;
			public String email;
			public String mobileNo;
			public String selectedUser;
			public String positiveData;
				
			// 3rd Step
			public UserSetupUpdateTest(String selectUser, String surname, String firstName, String otherNames, String email, String mobileNo, String selectedUser, String positiveData ){
				this.selectUser = selectUser;
				this.surname = surname;
				this.firstName = firstName;
				this.otherNames = otherNames;
				this.email = email;
				this.mobileNo = mobileNo;
				this.selectedUser = selectedUser;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the UserSetupUpdate Test");
			
			// xlsx file
			if(TestUtility.isSkip("UserSetupUpdateTest"))
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
		public void userSetupTest() throws InterruptedException {
			
			
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[7]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			ApplicationLogs.debug("User Setup Module: Moved to the Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Manage Users']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("User Setup Module: Clicked the Manage Users Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			getObjectsByName("rdo_UserSetup_NewUser").get(1).click();
			ApplicationLogs.debug("User Setup Module: Selected the Edit Existing User Radio");
			
			new Select(getObjectByName("drd_UserSetup")).selectByValue(selectUser);
			
			getObjectById("txt_UserSetup_Surname").clear();
			getObjectById("txt_UserSetup_Surname").sendKeys(surname);
			ApplicationLogs.debug("User Setup Module: Inserted Surname");
			
			getObjectById("txt_UserSetup_Firstname").clear();
			getObjectById("txt_UserSetup_Firstname").sendKeys(firstName);
			ApplicationLogs.debug("User Setup Module: Inserted First Name");
			
			getObjectById("txt_UserSetup_Othername").clear();
			getObjectById("txt_UserSetup_Othername").sendKeys(otherNames);
			ApplicationLogs.debug("User Setup Module: Inserted Other Names");
						
			getObjectsByName("boo_UserSetup_Alert").get(0).click();
			ApplicationLogs.debug("User Setup Module: Selected Yes");
			
			getObjectById("txt_UserSetup_Email").clear();
			getObjectById("txt_UserSetup_Email").sendKeys(email);
			ApplicationLogs.debug("User Setup Module: Inserted Email address");
			
			getObjectById("txt_UserSetup_PhoneNo").clear();
			getObjectById("txt_UserSetup_PhoneNo").sendKeys(mobileNo);
			ApplicationLogs.debug("User Setup Module: Inserted Mobile Number");
			
			getObjectsByName("rdo_UserSetup_AssignMenu").get(0).click();
			ApplicationLogs.debug("User Setup Module: Selected Assigned Menu Similar to Existing Staff");
			
			new Select(getObjectByName("drd_UserSetup_SelectUser")).selectByValue(selectedUser);
			ApplicationLogs.debug("User Setup Module: Selected Assigned Menu Similar to Existing Staff");
			
					
			if(isAlertPresent()){
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
			
			getObjectByName("btn_UserSetup_Save").click();
			ApplicationLogs.debug("User Setup Module: Clicked Save button");
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText();
			Assert.assertEquals("User Setup Test was not Successfully", "User Record Saved", actualText);
			ApplicationLogs.debug("We asserted that the User Setup was carried out successfully");
			
			getObjectByName("btn_UserSetup_Send4Approval").click();
			ApplicationLogs.debug("User Setup Module: Clicked Send for Approval button");
			
			actualText = getObjectByXpath("val_ForwardApproval").getText();
			Assert.assertEquals("User Setup Test did not send for approval Successfully", "User Setup has been forwarded to APPROVER1 for approval.", actualText);
			ApplicationLogs.debug("We asserted that the User Setup was successfully sent to Approval");
			
			
			driver.switchTo().defaultContent();
						  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("User Setup was successfully carried out");
				Assert.assertTrue("User Setup was successfully carried out", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("User Setup was unsuccessfully carried out");
				Assert.assertTrue("User Setup was unsuccessfully carried out", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("UserSetupUpdateTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

