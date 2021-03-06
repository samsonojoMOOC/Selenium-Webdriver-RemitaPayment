package com.remita.demo.epayment.SmokeTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.log4j.Logger;
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

	public class UserSetupInitiatorTest extends TestBase {
		// 2nd Step to parameterization
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			public String surname;
			public String firstName;
			public String otherNames;
			public String userName;
			public String email;
			public String mobileNo;
			public String positiveData;
				
			// 3rd Step
			public UserSetupInitiatorTest(String surname, String firstName, String otherNames, String userName, String email, String mobileNo,  String positiveData ){
				
				this.surname = surname;
				this.firstName = firstName;
				this.otherNames = otherNames;
				this.userName = userName;
				this.email = email;
				this.mobileNo = mobileNo;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the UserSetupInitiator Test");
			
			// xlsx file
			if(TestUtility.isSkip("UserSetupInitiatorTest"))
				Assume.assumeTrue(false);
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void payVendorTest() throws InterruptedException {
			
			
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
			
			getObjectsByName("rdo_UserSetup_NewUser").get(0).click();
			ApplicationLogs.debug("User Setup Module: Selected the New User Radio");
			
			getObjectById("txt_UserSetup_Surname").sendKeys(surname);
			ApplicationLogs.debug("User Setup Module: Inserted Surname");
			
			getObjectById("txt_UserSetup_Firstname").sendKeys(firstName);
			ApplicationLogs.debug("User Setup Module: Inserted First Name");
			
			getObjectById("txt_UserSetup_Othername").sendKeys(otherNames);
			ApplicationLogs.debug("User Setup Module: Inserted Other Names");
			
			getObjectById("txt_UserSetup_Username").sendKeys(userName);
			ApplicationLogs.debug("User Setup Module: Inserted User Name");
			
			getObjectsByName("boo_UserSetup_Alert").get(0).click();
			ApplicationLogs.debug("User Setup Module: Selected Yes");
			
			getObjectById("txt_UserSetup_Email").sendKeys(email);
			ApplicationLogs.debug("User Setup Module: Inserted Email address");
			
			getObjectById("txt_UserSetup_PhoneNo").sendKeys(mobileNo);
			ApplicationLogs.debug("User Setup Module: Inserted Mobile Number");
			
			getObjectsByName("rdo_UserSetup_AssignMenu").get(0).click();
			ApplicationLogs.debug("User Setup Module: Selected Assigned Menu Similar to Existing Staff");
			
			getObjectById("chk_UserSetup_Initiator").click();
			ApplicationLogs.debug("User Setup Module: Selected Initiator");
			
			getObjectByName("btn_UserSetup_AddMoreUsers").click();
			ApplicationLogs.debug("User Setup Module: Clicked Add More Users");
			
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
			String actualText = getObjectByXpath("val_ForwardApproval").getText();
			Assert.assertEquals("User Setup Test was not Successfully", "User Record Saved", actualText);
			ApplicationLogs.debug("We asserted that the User Setup was carried out successfully");
			
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
					Object[][] data = TestUtility.getDataFromExcelSheet("UserSetupInitiatorTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

