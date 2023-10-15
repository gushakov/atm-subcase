package com.github.atmsubcase.core.usecase.withdraw;

import java.math.BigDecimal;

public interface WithdrawCashInputPort {

    void withdrawCash(String accountNumber, BigDecimal amount);

}
