package com.github.atmsubcase.core.usecase.transfer;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountNumber;
import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.port.wire.WireTransferOperationsOutputPort;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountSubcaseInputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TransferFundsUseCase implements TransferFundsInputPort {

    TransferFundsPresenterOutputPort presenter;
    // here is the input port for our subcase
    VerifyAccountSubcaseInputPort verifyAccountSubcase;
    PersistenceOperationsOutputPort persistenceOps;
    WireTransferOperationsOutputPort wireTransferOps;


    @Transactional
    @Override
    public void transferFunds(String sourceAccountNumberArg, String targetAccountNumberArg, BigDecimal amount) {

        Account sourceAccountAfterTransfer;
        Account targetAccountAfterTransfer;
        boolean rollback = false;
        try {

            AccountNumber sourceAccountNumber = AccountNumber.of(sourceAccountNumberArg);
            AccountNumber targetAccountNumber = AccountNumber.of(targetAccountNumberArg);

            /*
                This is an example of reusing a subcase (twice) in a (parent) use case.
                Load and verify source and target accounts.
             */
            Account sourceAccount = verifyAccountSubcase.verifyAccount(sourceAccountNumber);
            Account targetAccount = verifyAccountSubcase.verifyAccount(targetAccountNumber);

            // debit source account
            sourceAccountAfterTransfer = sourceAccount.withdraw(amount);

            // credit target account
            targetAccountAfterTransfer = targetAccount.credit(amount);

            // persist changes
            persistenceOps.saveAccount(sourceAccountAfterTransfer);
            persistenceOps.saveAccount(targetAccountAfterTransfer);

        } catch (Exception e) {
            presenter.presentError(e);
            rollback = true;
            return;
        } finally {
            if (rollback) {
                persistenceOps.rollback();
            }
        }

        presenter.presentResultOfSuccessfulTransferOfFunds(sourceAccountAfterTransfer, targetAccountAfterTransfer);
    }
}
