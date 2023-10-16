package com.github.atmsubcase.infrastructure.adapters.web.withdraw;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.usecase.withdraw.WithdrawCashPresenterOutputPort;
import com.github.atmsubcase.infrastructure.adapters.web.AbstractWebPresenter;

public class WithdrawCashPresenter extends AbstractWebPresenter implements WithdrawCashPresenterOutputPort {
    @Override
    public void presentErrorWhenUserDoesNotPermissionToAccessAccount(String userPersonId, Account account) {
        // show relevant error message to the user
    }

    @Override
    public void presentResultOfSuccessfulCashWithdrawal(Account accountAfterWithdrawal) {
        // show relevant feedback to the user
    }
}
