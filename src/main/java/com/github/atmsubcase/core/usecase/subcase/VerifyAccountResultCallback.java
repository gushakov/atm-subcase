package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.Account;

public interface VerifyAccountResultCallback {

    void proceedAfterAccountVerification(Account verifiedAccount);

}
