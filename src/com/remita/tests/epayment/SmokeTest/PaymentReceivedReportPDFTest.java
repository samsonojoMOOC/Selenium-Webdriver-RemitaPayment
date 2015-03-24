package com.remita.tests.epayment.SmokeTest;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import tests.TestBase;
import Util.TestUtility;

public class PaymentReceivedReportPDFTest extends TestBase{
	
	@Before
	public void beforeTest() throws IOException{
		System.out.println("Initializing the system");
		initialize();
		
		// xlsx file
		if(TestUtility.isSkip("PaymentReceivedReportPDFTest"))
			Assume.assumeTrue(false);
	}
	
	
	@Test
	public void paymentReceivedReportPDFTest() throws IOException{
		// implement
		
		
Actions act = new Actions(driver);
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[4]/a"));
act.moveToElement(menuPayment).click().build().perform();


WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Reports ']"));
act.moveToElement(submenuPayment).click().perform();

submenuPayment = driver.findElement(By.xpath("//a[text()='Payments Received']"));
act.moveToElement(submenuPayment).click().perform();

int size = driver.findElements(By.tagName("iframe")).size();
System.out.println("Total frames in page- "+size);

driver.switchTo().frame(0);
int allElement = driver.findElements(By.tagName("input")).size();
System.out.println("Total number of buttons in page - "+ allElement);

new Select(driver.findElement(By.xpath("html/body/div[3]/div/form/div[13]/div/select"))).selectByValue("pdf");

driver.findElement(By.name("newWinn")).click();



	
	URL TestURL = new URL("http://192.9.200.11/report/jreports/exportjreport?[starts-with(@_params, '*')]");

	BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
	PDFParser TestPDF = new PDFParser(TestFile);
	TestPDF.parse();
	String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
	
	Assert.assertTrue("The PDF Document does not contain PDF Report", TestText.contains("Payments Received Report"));


	


driver.switchTo().defaultContent();

		
	}

}

