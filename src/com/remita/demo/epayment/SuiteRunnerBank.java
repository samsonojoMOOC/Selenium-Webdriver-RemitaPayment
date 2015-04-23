package com.remita.demo.epayment;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.demo.epayment.SmokeTest.BankBranchSetupTest;
import com.remita.demo.epayment.SmokeTest.ReverseWrongTransactionTest;




@RunWith(Suite.class)
@SuiteClasses({

LoginBankTest.class,
ReverseWrongTransactionTest.class,
BankBranchSetupTest.class,
LogoutTest.class

})

public class SuiteRunnerBank {
	


}

