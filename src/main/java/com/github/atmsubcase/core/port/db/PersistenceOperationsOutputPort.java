package com.github.atmsubcase.core.port.db;

import com.github.atmsubcase.core.model.Account;
import com.github.atmsubcase.core.model.AccountNumber;

/**
 * Output port for persistence operations.
 */
public interface PersistenceOperationsOutputPort {

    /**
     * Rolls back current transaction.
     */
    void rollback();

    /**
     * Loads the {@code Account} with matching number from the database.
     */
    Account loadAccount(AccountNumber accountNumber);

    /**
     * Persists the account into the database.
     */
    void saveAccount(Account account);
}
