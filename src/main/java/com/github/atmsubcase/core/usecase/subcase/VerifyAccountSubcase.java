package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountNumber;
import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.port.security.SecurityOperationsOutputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
