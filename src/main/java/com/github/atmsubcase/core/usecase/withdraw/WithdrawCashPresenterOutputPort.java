package com.github.atmsubcase.core.usecase.withdraw;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountPresenterOutputPort;

public interface WithdrawCashPresenterOutputPort extends VerifyAccountPresenterOutputPort {
    void presentResultOfSuccessfulCashWithdrawal(Account accountAfterWithdrawal);
}
