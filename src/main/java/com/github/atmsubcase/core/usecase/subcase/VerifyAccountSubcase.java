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
            /*
                Perform any necessary steps to implement the scenario
                of the subcase. This is the shared logic which we
                only write once and reuse in different use cases.
             */

            account = persistenceOps.loadAccount(accountNumber);

            String userPersonId = securityOps.getAuthenticatedUserPersonId();

            if (!account.canAccess(userPersonId)) {
                // perform error handling specific to the logic of the subcase
                presenter.presentErrorWhenUserDoesNotPermissionToAccessAccount(userPersonId, account);
                return;
            }

        } catch (Exception e) {
            presenter.presentError(e);
            return;
        }

        // execute callback to pass results of the subcase back to the parent use case
        resultCallback.doWithVerifiedAccount(account);

    }
}
