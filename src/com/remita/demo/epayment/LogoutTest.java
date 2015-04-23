package com.remita.demo.epayment;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import utilDemo.TestUtility;
import testsDemo.TestBase;

public class LogoutTest extends TestBase {
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		
		@Before
		public void beforeTest() throws IOException{
			
			System.out.println("Initializing the Login system");
			initialize();
			ApplicationLogs.debug("Loging Out...");
			
			
			
			// xlsx file
			if(TestUtility.isSkip("LogoutTest"))
				Assume.assumeTrue(false);
		}
		
		@Test
		public void logOutTest(){
			TestUtility.logout();
			ApplicationLogs.debug("Logged Out");
		}

}
