package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.port.ErrorHandlingPresenterOutputPort;

/**
 * Port for the presenter of the subcase "verify account". Will be used
 * to present any errors specific to the logic of the subcase. The concrete
 * implementation of this interface will be one of the presenters of one of
 * the specific parent use cases.
 */
public interface VerifyAccountPresenterOutputPort extends ErrorHandlingPresenterOutputPort {

    void presentErrorWhenUserDoesNotPermissionToAccessAccount(String userPersonId, Account account);
}
