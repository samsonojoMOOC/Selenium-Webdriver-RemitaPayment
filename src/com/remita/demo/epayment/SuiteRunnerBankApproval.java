package com.remita.demo.epayment;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.demo.epayment.SmokeTest.BankBranchSetupApprovalTest;
import com.remita.demo.epayment.SmokeTest.ReverseWrongTransApprovalTest;




@RunWith(Suite.class)
@SuiteClasses({

	
LoginBankApprovalTest.class,
BankBranchSetupApprovalTest.class,
ReverseWrongTransApprovalTest.class,
LogoutTest.class

})

public class SuiteRunnerBankApproval {
	


}

