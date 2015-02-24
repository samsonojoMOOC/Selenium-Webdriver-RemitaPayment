
package com.remita.tests.epayment.SmokeTest;


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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.TestBase;
import Util.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class UploadSalariesPensionTest extends TestBase {
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		// 2nd Step to parameterization
	
			public String pensionMonth;
			public String pensionYear;
			public String briefDesc;
			public String detailedDesc;
			public String FundAcct;
			public String PensionAcct;
			public String positiveData;
				
			// 3rd Step
			public UploadSalariesPensionTest(String pensionMonth,  String pensionYear, String briefDesc, String detailedDesc, String FundAcct, String PensionAcct, String positiveData ){
				
				this.pensionMonth=pensionMonth;
				this.pensionYear = pensionYear;
				this.briefDesc = briefDesc;
				this.detailedDesc = detailedDesc;
				this.FundAcct = FundAcct;
				this.PensionAcct = PensionAcct;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the UploadSalariesPension Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("UploadSalariesPensionTest"))
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
		public void uloadSalariesPensionTest() throws IOException, InterruptedException{
			
								
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions act = new Actions(driver);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Pay Pension Module: Moved mouse to the Main Menu Link");
			
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Upload Salaries from External Payroll']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Pay Pension Module: Clicked the Pay Pension Menu");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			ApplicationLogs.debug("Pay Pension Module: Switch into the Content iFrame");
			
					
			driver.findElements(By.xpath("//input[@name='salOption']")).get(1).click();
			
						
			new Select(getObjectById("drd_PensionMonth")).selectByValue(pensionMonth);
			ApplicationLogs.debug("Pay Pension Module: Selected Pension Month");
			
			new Select(getObjectById("drd_PensionYear")).selectByValue(pensionYear);
			ApplicationLogs.debug("Pay Pension Module: Selected Pension Year");
			
			getObjectByName("btn_Browser").click();
			ApplicationLogs.debug("Pay Pension Module: Clicked Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\pensionOnly (1)_MULTI.csv", "Open").start();
			ApplicationLogs.debug("Pay Pension Module: Selected and Attached the Pension CSV File");
			
	
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
			getObjectById("txt_BriefDesc").sendKeys(briefDesc);
			ApplicationLogs.debug("Pay Pension Module: Inserted Brief Description");
			getObjectByName("txt_DetailDesc").sendKeys(detailedDesc);
			ApplicationLogs.debug("Pay Pension Module: Inserted Detailed Description");
			getObjectByName("btn_Preview").click();
			ApplicationLogs.debug("Pay Pension Module: Clicked Preview Button");
			
			
			Alert al = driver.switchTo().alert();
			
			System.out.println(al.getText());
			al.accept();
			
			//wait for the preview screen
			getObjectByName("btn_Submit").click();	
			
			//wait for the Choose Paying Account
			System.out.println("***************3nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);

			
			//Validate that there is response
						
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			Assert.assertEquals("Your File has not been successfully uploaded. Pls, try again", "Your File has been successfully uploaded. Please Select Funding Bank", actualText);
			ApplicationLogs.debug("Pay Pension Module: Your File has been successfully uploaded.");
			ApplicationLogs.debug(actualText);
			
			getObjectByXpath("lnk_ProcessTransaction").click();
			ApplicationLogs.debug("Pay Pension Module: Clicked to process transactions");
			
			new Select(getObjectByName("drd_FundingBank")).selectByValue(FundAcct);
			new Select(getObjectByName("drd_PrePensionAccount")).selectByValue(PensionAcct);
			
			ApplicationLogs.debug("Pay Pension Module: Selected Funding banks and Pension Account");
			
			getObjectByName("btn_SubmitPension").click();
			ApplicationLogs.debug("Pay Pension Module: Clicked Submit Payment to Pension Button");
			
			
			actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			Assert.assertTrue("Unable to send Payment to Pension for Approval", actualText.startsWith("Pensions Remittance (Batch"));
			ApplicationLogs.debug(actualText);

			
			//Fetching data from XLS file
			
			driver.switchTo().defaultContent();
			
			

			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				Assert.assertTrue("Pension Payment Transaction(s) was successfully carried out", true);
				ApplicationLogs.debug("Pension Payment Transaction(s) was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Pension Test");
				Assert.assertTrue("Pension Payment Transaction(s) was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pension Payment Transaction(s) was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("UploadSalariesPensionTest");
					return Arrays.asList(data);
			
					
		}
		
		
}


