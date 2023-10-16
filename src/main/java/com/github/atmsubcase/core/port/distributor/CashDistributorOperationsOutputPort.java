package com.github.atmsubcase.core.port.distributor;

import java.math.BigDecimal;

/**
 * Output port for cash distribution operations.
 */
public interface CashDistributorOperationsOutputPort {
    void distribute(BigDecimal amount);
}
