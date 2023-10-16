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

    default Account verifyAccount(AccountNumber accountNumber) {

        /*
            Calling subcase with ad-hoc implementation of "VerifyAccountResultCallback".
            This should actually be done more elegantly with a lambda, but we
            are leaving it here for illustration.
         */

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
     * What differentiates a method of a subcase, it is that it takes a result callback.
     * This call back will be called by the implementation of the subcase to signal
     * the end of successful processing of the subcase, passing any results to back
     * the parent use case.
     */
    void loadAccountAndVerify(AccountNumber accountNumber, VerifyAccountResultCallback resultCallback);

}
