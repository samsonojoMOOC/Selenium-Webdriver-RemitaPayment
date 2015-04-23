package com.remita.demo.epayment.SmokeTest;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.demo.epayment.LoginApprovalTest;
import com.remita.demo.epayment.LogoutTest;



@RunWith(Suite.class)
@SuiteClasses({

	
LoginApprovalTest.class,
UserSetupApprovalTest.class,
/*
RejectPaymentApprovalTest.class,
MassApprovalPaymentTest.class,
MassRejectPaymentApprovalTest.class,
PayVendorApprovalTest.class,
StandingOrderApprovalTest.class,
UserSetupApprovalTest.class,
*/
LogoutTest.class

})

public class SuiteRunnerSmokeApproval {
	


}

