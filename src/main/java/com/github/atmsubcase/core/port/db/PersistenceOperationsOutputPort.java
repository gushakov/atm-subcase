package com.github.atmsubcase.core.port.db;

/**
 * Output port for persistence operations.
 */
public interface PersistenceOperationsOutputPort {

    /**
     * Rolls back current transaction.
     */
    void rollback();
}
