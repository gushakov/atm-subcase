package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountNumber;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This is a subcase: it specified a procedure for loading an {@code Account}
 * from the database and verifying that the currently authenticated user
 * has a permission to access the account. This subcase will be used by
 * two different parent use cases: "withdraw cash" and "transfer funds".
 */
public interface VerifyAccountSubcaseInputPort {

    /**
     * Calls {@linkplain VerifyAccountSubcase} subcase with ad-hoc implementation
     * of {@linkplain VerifyAccountResultCallback} which will record the callback
     * argument (verified account) and return it.
     *
     * @see #loadAccountAndVerify(AccountNumber, VerifyAccountResultCallback)
     */
    default Account verifyAccount(AccountNumber accountNumber) {
        final AtomicReference<Account> sourceAccountRef = new AtomicReference<>();
        loadAccountAndVerify(accountNumber, new VerifyAccountResultCallback() {
            @Override
            public void doWithVerifiedAccount(Account verifiedAccount) {
                sourceAccountRef.set(verifiedAccount);
            }
        });
        return sourceAccountRef.get();
    }

    /**
     * This method of is the actual procedure containing business logic of the subcase.
     *
     * @see VerifyAccountSubcase
     */
    void loadAccountAndVerify(AccountNumber accountNumber, VerifyAccountResultCallback resultCallback);

}
