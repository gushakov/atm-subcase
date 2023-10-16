package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountNumber;
import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.port.security.SecurityOperationsOutputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * This is a subcase: it specified a procedure for loading an {@code Account}
 * from the database and verifying that the currently authenticated user
 * has a permission to access the account. This subcase will be used by
 * two different parent use cases: "withdraw cash" and "wire transfer".
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VerifyAccountSubcase implements VerifyAccountSubcaseInputPort {

    VerifyAccountPresenterOutputPort presenter;
    PersistenceOperationsOutputPort persistenceOps;
    SecurityOperationsOutputPort securityOps;

    @Override
    public void loadAccountAndVerify(AccountNumber accountNumber, VerifyAccountResultCallback resultCallback) {

        Account account;
        try {

            account = persistenceOps.loadAccount(accountNumber);

            String userPersonId = securityOps.getAuthenticatedUserPersonId();

            if (!account.canAccess(userPersonId)) {
                presenter.presentErrorWhenUserDoesNotPermissionToAccessAccount(userPersonId, account);
                return;
            }

        } catch (Exception e) {
            presenter.presentError(e);
            return;
        }

        resultCallback.doWithVerifiedAccount(account);

    }
}
