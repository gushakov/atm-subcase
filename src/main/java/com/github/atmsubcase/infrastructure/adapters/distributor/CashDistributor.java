package com.github.atmsubcase.infrastructure.adapters.distributor;

import com.github.atmsubcase.core.port.distributor.CashDistributorOperationsOutputPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Secondary adapter. Controls cash distribution.
 */
@Service
public class CashDistributor implements CashDistributorOperationsOutputPort {
    @Override
    public void distribute(BigDecimal amount) {
        // details are omitted
    }
}
