package com.remita.demo.epayment;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

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

	public class PayVendorBulk1Test extends TestBase {
		// 2nd Step to parameterization
		
		//ePayment details here
			
			
			public String UploadType;
			public String positiveData;
				
			// 3rd Step
			public PayVendorBulk1Test(String UploadType,  String positiveData ){
				
				this.UploadType=UploadType;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			System.out.println("Initializing the PayVendorBulk Test");
			initialize();
			
			// xlsx file
			if(TestUtility.isSkip("PayVendorBulkTest"))
				Assume.assumeTrue(false);
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void payVendorBulkTest() throws IOException, InterruptedException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[5]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			Thread.sleep(5000);
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Pay Vendors, Suppliers & Others']"));
			act.moveToElement(submenuPayment).click().perform();
			
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
			getObjectById("rdo_BulkUpload").click();
			
			
			System.out.println("***************2nd Chechk**********");
			size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			/*
			new FluentWait<WebDriver>(driver)
			.withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mainContentArea']/div[2]/div[1]/iframe/html/body/div[2]/label/input[@id='standOrderRadioId']")));
			
			*/
			
			new Select(getObjectById("drd_SelectUploadType")).selectByValue(UploadType);
			getObjectByName("btn_Browser").click();
			Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\config\\file_upload6.exe",
								"C:\\Users\\USER\\Documents\\normalechequeupload01.csv", "Open").start();
			/*
			   WebDriverWait wait = new WebDriverWait(driver, 15, 100);
			    wait.until(ExpectedConditions.alertIsPresent());
			*/
			Alert al = driver.switchTo().alert();
			System.out.println(al.getText());
			al.accept();
			//al.dismiss();
			//driver.switchTo().defaultContent();
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			
			getObjectByName("btn_Preview").click();
			System.out.println("clicked Preview button");
			
			
			driver.switchTo().defaultContent();
			
			//Assert.assertEquals(message, expected, actual);
			//Assert.assertEquals(expected, actual);
			
			//End of first Iteration
			

			  
			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Payment to Vendor Transaction(s) was successfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction(s) was successfully carried out", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Payment to Vendor Transaction(s) was unsuccessfully carried out");
				Assert.assertTrue("Payment to Vendor Transaction(s) was unsuccessfully carried out", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("PayVendorBulkTest");
					return Arrays.asList(data);
			
					
		}
		
		
}

