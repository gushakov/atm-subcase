package com.github.atmsubcase.core.usecase.withdraw;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WithdrawCashUseCase implements WithdrawCashInputPort {

    WithdrawCashPresenterOutputPort presenter;

    @Override
    public void withdrawCash(String accountNumber, BigDecimal amount) {

    }
}
