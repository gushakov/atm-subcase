package com.github.atmsubcase.infrastructure.adapters.db;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountNumber;
import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import org.springframework.stereotype.Service;

/**
 * Secondary adapter. Persists aggregates into database.
 */
@Service
public class PersistenceGateway implements PersistenceOperationsOutputPort {

    @Override
    public void rollback() {
        // implementation details omitted
    }

    @Override
    public Account loadAccount(AccountNumber accountNumber) {
        // implementation details omitted
        return null;
    }

    @Override
    public void saveAccount(Account account) {
        // implementation details omitted
    }
}
