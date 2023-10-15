package com.github.atmsubcase.infrastructure.adapters.db;

import com.github.atmsubcase.core.port.db.PersistenceOperationsOutputPort;
import org.springframework.stereotype.Service;

/**
 * Secondary adapter. Persists aggregates into database.
 */
@Service
public class PersistenceGateway implements PersistenceOperationsOutputPort {

}
