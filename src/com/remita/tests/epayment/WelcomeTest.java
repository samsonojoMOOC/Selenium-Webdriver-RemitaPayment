
package com.remita.tests.epayment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.junit.Assert;


import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Util.TestUtility;
import tests.TestBase;

public class WelcomeTest extends TestBase{
	
	@Before
	public void beforeTest() throws IOException{
		System.out.println("Initializing the system");
		initialize();
		
		// xlsx file
		if(TestUtility.isSkip("WelcomeTest"))
			Assume.assumeTrue(false);
	}
	
	
	@Test
	public void welcomeTest(){
		// implement
		
		
Actions act = new Actions(driver);
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[2]/a"));
act.moveToElement(menuPayment).click().build().perform();


WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Welcome Note']"));
act.moveToElement(submenuPayment).click().perform();


int size = driver.findElements(By.tagName("iframe")).size();
System.out.println("Total frames in page- "+size);

driver.switchTo().frame(0);
int allElement = driver.findElements(By.tagName("input")).size();
System.out.println("Total number of buttons in page - "+ allElement);

String actualText = "Welcome to Remita";
String expectedText = driver.findElement(By.xpath("html/body/p[1]/strong")).getText();
System.out.println(expectedText);
Assert.assertEquals("Welcome to Remita is Unsuccessful", expectedText, actualText);






driver.switchTo().defaultContent();

		
	}

}

