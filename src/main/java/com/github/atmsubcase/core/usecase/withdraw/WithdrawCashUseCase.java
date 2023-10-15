package com.github.atmsubcase.core.usecase.withdraw;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import com.github.atmsubcase.core.usecase.subcase.VerifyAccountSubcase;
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
    PersistenceOperationsOutputPort persistenceOps;


    @Transactional
    @Override
    public void withdrawCash(String accountNumber, BigDecimal amount) {

        boolean rollback = false;
        try {

            Account sourceAccount = verifySourceAccount(accountNumber);



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

    }

    private Account verifySourceAccount(String accountNumber) {
        final AtomicReference<Account> sourceAccountRef = new AtomicReference<>();
        new VerifyAccountSubcase(presenter, sourceAccountRef::set)
                .loadAccountAndVerify(accountNumber);
        return sourceAccountRef.get();
    }
}
