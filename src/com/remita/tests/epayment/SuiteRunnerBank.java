package com.remita.tests.epayment;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.tests.epayment.SmokeTest.BankBranchSetupTest;
import com.remita.tests.epayment.SmokeTest.ReverseWrongTransactionTest;




@RunWith(Suite.class)
@SuiteClasses({

LoginBankTest.class,
ReverseWrongTransactionTest.class,
BankBranchSetupTest.class,
LogoutTest.class

})

public class SuiteRunnerBank {
	


}

