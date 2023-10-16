package com.github.atmsubcase.core.usecase.transfer;

import java.math.BigDecimal;

/**
 * Another parent use case. Transfer of funds between accounts.
 */
public interface TransferFundsInputPort  {

    void transferFunds(String sourceAccountNumberArg, String targetAccountNumberArg, BigDecimal amount);

}
