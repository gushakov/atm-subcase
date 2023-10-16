package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.Account;

/**
 * This callback will be implemented ad-hoc with a lambda function
 * during the call to the subcase from a parent usecase. It will
 * the called by "verify account" subcase upon its successful
 * completion.
 */
public interface VerifyAccountResultCallback {
    void doWithVerifiedAccount(Account verifiedAccount);
}
