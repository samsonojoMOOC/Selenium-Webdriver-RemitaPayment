package com.remita.tests.epayment;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.tests.epayment.SmokeTest.ManageStaffRecordTest;




@RunWith(Suite.class)
@SuiteClasses({

	
LoginPayrollTest.class,
ManageStaffRecordTest.class,

})

public class SuiteRunnerPayroll {
	


}

