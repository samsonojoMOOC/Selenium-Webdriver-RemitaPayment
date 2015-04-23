package com.remita.demo.epayment;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.demo.epayment.BankBranchOps.PaySalaryViaRRRApprovalTest;
import com.remita.demo.epayment.BankBranchOps.StandingOrderApprovalTest;




@RunWith(Suite.class)
@SuiteClasses({

	
LoginTellerApprovalTest.class,
StandingOrderApprovalTest.class,
//PaySalaryViaRRRApprovalTest.class,

LogoutTest.class

})

public class SuiteRunnerTellerApproval {
	


}

