package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.port.ErrorHandlingPresenterOutputPort;

public interface VerifyAccountPresenterOutputPort extends ErrorHandlingPresenterOutputPort {

    void presentErrorWhenUserDoesNotPermissionToAccessAccount(String userPersonId, Account account);
}
