package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.model.AccountNumber;

public interface VerifyAccountSubcaseInputPort {

    void loadAccountAndVerify(AccountNumber accountNumber, VerifyAccountResultCallback resultCallback);

}
