package com.github.atmsubcase.infrastructure.adapters.web.transfer;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.usecase.transfer.TransferFundsPresenterOutputPort;
import com.github.atmsubcase.infrastructure.adapters.web.AbstractWebPresenter;
import org.springframework.stereotype.Service;

@Service
public class TransferFundsPresenter extends AbstractWebPresenter implements TransferFundsPresenterOutputPort {
    @Override
    public void presentErrorWhenUserDoesNotPermissionToAccessAccount(String userPersonId, Account account) {

    }

    @Override
    public void presentResultOfSuccessfulTransferOfFunds(Account sourceAccountAfterTransfer, Account targetAccountAfterTransfer) {

    }
}
