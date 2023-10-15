package com.github.atmsubcase.core.usecase.subcase;

import com.github.atmsubcase.core.port.ErrorHandlingPresenterOutputPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VerifyAccountSubcase implements VerifyAccountSubcaseInputPort {

    VerifyAccountPresenterOutputPort presenter;
    VerifyAccountResultCallback resultCallback;

    @Override
    public void loadAccountAndVerify(String accountNumber) {



    }
}
