package com.github.atmsubcase.core.usecase.withdraw;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountPresenterOutputPort;

/**
 * Port for the presenter of the (parent) use case: "withdraw cash". It inherits
 * from the port for the presenter of the subcase. This way, any presentation calls,
 * typically to present any errors occurred during the execution of the subcase,
 * are handled by the main presenter of the use case (which has access to the view
 * of the view-model).
 */
public interface WithdrawCashPresenterOutputPort extends VerifyAccountPresenterOutputPort {
    void presentResultOfSuccessfulCashWithdrawal(Account accountAfterWithdrawal);
}
