package com.remita.demo.epayment;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import utilDemo.TestUtility;
import testsDemo.TestBase;

public class PostAddTest extends TestBase{
	
	@Before
	public void beforeTest() throws IOException{
		System.out.println("Initializing the system");
		initialize();
		
		// xlsx file
		if(TestUtility.isSkip("PostAddTest"))
			Assume.assumeTrue(false);
	}
	
	@Test
	public void postAddTest(){
		// implement
		
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Total frames in page- "+size);
		
	}

}
