package com.remita.tests.epayment.SmokeTest;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.tests.epayment.LoginApprovalTest;
import com.remita.tests.epayment.LogoutTest;
import com.remita.tests.epayment.MassApprovalPaymentTest;
import com.remita.tests.epayment.MassRejectPaymentApprovalTest;
import com.remita.tests.epayment.PayVendorApprovalTest;
import com.remita.tests.epayment.RejectPaymentApprovalTest;




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

