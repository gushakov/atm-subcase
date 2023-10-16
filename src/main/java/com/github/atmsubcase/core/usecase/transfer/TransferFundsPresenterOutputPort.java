package com.github.atmsubcase.core.usecase.transfer;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountPresenterOutputPort;

/**
 * Port for the presenter of the (parent) use case: "transfer funds".
 */
public interface TransferFundsPresenterOutputPort extends VerifyAccountPresenterOutputPort {

    void presentResultOfSuccessfulTransferOfFunds(Account sourceAccountAfterTransfer, Account targetAccountAfterTransfer);

}
