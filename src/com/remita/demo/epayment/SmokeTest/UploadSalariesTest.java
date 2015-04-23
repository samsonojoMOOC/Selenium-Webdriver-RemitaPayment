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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testsDemo.TestBase;
import utilDemo.TestUtility;



	//1st step to parameterization
	@RunWith(Parameterized.class)

	public class UploadSalariesTest extends TestBase {
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		// 2nd Step to parameterization
	
			public String pensionMonth;
			public String pensionYear;
			public String briefDesc;
			public String detailedDesc;
			public String FundAcct;
			public String positiveData;
				
			// 3rd Step
			public UploadSalariesTest(String pensionMonth,  String pensionYear, String briefDesc, String detailedDesc, String FundAcct, String positiveData ){
				
				this.pensionMonth=pensionMonth;
				this.pensionYear = pensionYear;
				this.briefDesc = briefDesc;
				this.detailedDesc = detailedDesc;
				this.FundAcct = FundAcct;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			ApplicationLogs.debug("Initializing the UploadSalaries Test");
			
			
			// xlsx file
			if(TestUtility.isSkip("UploadSalariesTest"))
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
		public void uploadSalariesTest() throws IOException, InterruptedException{
			
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions act = new Actions(driver);
			
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));

			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Upload Salaries Module: Moved mouse to the Main Menu Link");
			
			
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Upload Salaries from External Payroll']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Upload Salaries Module: Clicked the Upload Salaries Menu");
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			ApplicationLogs.debug("Upload Salaries Module: Switch into the Content iFrame");
				
			
			new Select(getObjectById("drd_PensionMonth")).selectByValue(pensionMonth);
			ApplicationLogs.debug("Upload Salaries Module: Selected Salary Month");
			
			new Select(getObjectById("drd_PensionYear")).selectByValue(pensionYear);
			ApplicationLogs.debug("Upload Salaries Module: Selected Salary Year");
			
			getObjectByName("btn_Browser").click();
			ApplicationLogs.debug("Upload Salaries Module: Clicked Browse Button");
			
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\uploadfolder\\SalaryOnly_100.csv", "Open").start();
			ApplicationLogs.debug("Upload Salaries Module: Selected and Attached the Salaries CSV File");
			
			if(isAlertPresent()){
				driver.switchTo().alert().accept();
			}
	
			getObjectById("txt_BriefDesc").sendKeys(briefDesc);
			ApplicationLogs.debug("Upload Salaries Module: Inserted Brief Description");
	
			getObjectByName("txt_DetailDesc").sendKeys(detailedDesc);
			ApplicationLogs.debug("Upload Salaries Module: Inserted Detailed Description");
			
						
			getObjectByName("btn_Preview").click();
			ApplicationLogs.debug("Upload Salaries Module: Clicked Preview Button");
			
			Alert al = driver.switchTo().alert();
			
			System.out.println(al.getText());
			al.accept();
			
			
			//wait for the preview screen
			getObjectByName("btn_Submit").click();	
			ApplicationLogs.debug("Upload Salaries Module: Clicked the Submit button");
			
			//wait for the Choose Paying Account
			System.out.println("***************3nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);

			//Validate that there is response
					
			String actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			Assert.assertEquals("Your File has not been successfully uploaded. Pls, try again", "Your File has been successfully uploaded. Please Select Funding Bank", actualText);
			ApplicationLogs.debug("Upload Salaries Module: Your File has been successfully uploaded.");
			ApplicationLogs.debug(actualText);
			
			getObjectByXpath("lnk_ProcessTransaction").click();
			ApplicationLogs.debug("Upload Salaries Module: Clicked to process transactions");
			
			new Select(driver.findElement(By.id("fundingbank1"))).selectByValue(FundAcct);
			new Select(driver.findElement(By.id("fundingbank2"))).selectByValue(FundAcct);
			new Select(driver.findElement(By.id("fundingbank3"))).selectByValue(FundAcct);
			ApplicationLogs.debug("Upload Salaries Module: Selected Funding banks");
			
			getObjectByName("btn_SubmitPay").click();
			ApplicationLogs.debug("Upload Salaries Module: Clicked Submit Payment Button");
			
			//Validate that there is response
			
			//String actualText = "has been forwarded to APPROVER1 for approval";
			
			actualText = getObjectByXpath("val_ForwardApproval").getText(); 
			System.out.println(actualText);
			Assert.assertTrue("Unable to Successfully add Funding bank(s)", actualText.startsWith("Salary Remittance (Batch"));
			ApplicationLogs.debug(actualText);
			
			
			driver.switchTo().defaultContent();
			
			
			//End of first Iteration
			

			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Pension Payment Transaction(s) was successfully carried out");
				Assert.assertTrue("Pension Payment Transaction(s) was successfully carried out", true);
				ApplicationLogs.debug("Pension Payment Transaction(s) was successfully carried out");
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Pension Payment Transaction(s) was unsuccessfully carried out");
				Assert.assertTrue("Pension Payment Transaction(s) was unsuccessfully carried out", false);
				ApplicationLogs.debug("Pension Payment Transaction(s) was unsuccessfully carried out");
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("UploadSalariesTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

