package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import tests.TestBase;
import Util.TestUtility;

public class ReportAuditHTMLTest extends TestBase{
	
	@Before
	public void beforeTest() throws IOException{
		System.out.println("Initializing the system");
		initialize();
		
		// xlsx file
		if(TestUtility.isSkip("ReportAuditHTMLTest"))
			Assume.assumeTrue(false);
	}
	
	
	@Test
	public void reportAllPaymentMadeHTMLTest(){
		// implement
		
		
Actions act = new Actions(driver);
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[7]/a"));
act.moveToElement(menuPayment).click().build().perform();

WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Audit Reports ']"));
act.moveToElement(submenuPayment).click().perform();

submenuPayment = driver.findElement(By.xpath("//a[text()='Audit Trail']"));
act.moveToElement(submenuPayment).click().perform();

int size = driver.findElements(By.tagName("iframe")).size();
System.out.println("Total frames in page- "+size);

driver.switchTo().frame(0);
int allElement = driver.findElements(By.tagName("input")).size();
System.out.println("Total number of buttons in page - "+ allElement);


driver.findElement(By.name("transDateFrom")).click();
driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[3]/a")).click();
driver.findElement(By.name("transDateTo")).click();
driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[5]/a")).click();

new Select(driver.findElement(By.name("action"))).selectByValue("Modified Record");

new Select(driver.findElement(By.name("user"))).selectByVisibleText("All Users");

driver.findElement(By.xpath("html/body/div[3]/div/form/div/div/div/div[6]/input")).click();
/*

String winHandleBefore = driver.getWindowHandle();

for(String winHandle : driver.getWindowHandles()){
	driver.switchTo().window(winHandle);
}

String actualReport = driver.findElement(By.xpath("html/body/table/tbody/tr/td[2]/table/tbody/tr[5]/td[3]/p/span")).getText();
Assert.assertEquals("The Report has not been generated successfully", "Payment History", actualReport);

driver.close();

driver.switchTo().window(winHandleBefore);
*/

driver.switchTo().defaultContent();

		
	}

}



