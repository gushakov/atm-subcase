package com.github.atmsubcase.core.usecase.withdraw;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountNumber;
import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.port.distributor.CashDistributorOperationsOutputPort;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountResultCallback;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountSubcaseInputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WithdrawCashUseCase implements WithdrawCashInputPort {

    WithdrawCashPresenterOutputPort presenter;
    VerifyAccountSubcaseInputPort verifyAccountSubcase;
    PersistenceOperationsOutputPort persistenceOps;
    CashDistributorOperationsOutputPort cashDistributorOps;

    @Transactional
    @Override
    public void withdrawCash(String accountNumberArg, BigDecimal amount) {

        Account accountAfterWithdrawal;
        boolean rollback = false;
        try {

            // create a valid value object representing an account number
            AccountNumber accountNumber = AccountNumber.of(accountNumberArg);

            // call the subcase to load the account and verify user's permissions
            Account sourceAccount = verifyAccount(accountNumber);

            // delegate to the "Account" aggregate to actually perform
            // the logic of the withdrawal
            accountAfterWithdrawal = sourceAccount.withdraw(amount);

            // save the state of the account after the withdrawal
            persistenceOps.saveAccount(accountAfterWithdrawal);

            // distribute cash
            cashDistributorOps.distribute(amount);

        }
        catch (Exception e){
            presenter.presentError(e);
            rollback = true;
            return;
        }
        finally {
            if (rollback){
                persistenceOps.rollback();
            }
        }

        /*
            Here we are calling the presenter for the use case so that
            it can present the overall result of "withdraw cash" use case,
            possibly by showing the resulting amount or other account information.
         */
        presenter.presentResultOfSuccessfulCashWithdrawal(accountAfterWithdrawal);

    }

    private Account verifyAccount(AccountNumber accountNumber) {
        final AtomicReference<Account> sourceAccountRef = new AtomicReference<>();
        verifyAccountSubcase.loadAccountAndVerify(accountNumber, new VerifyAccountResultCallback() {
            @Override
            public void doWithVerifiedAccount(Account verifiedAccount) {
                sourceAccountRef.set(verifiedAccount);
            }
        });
        return sourceAccountRef.get();
    }
}
