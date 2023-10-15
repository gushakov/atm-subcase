package com.github.atmsubcase.infrastructure.adapters.wire;

import com.github.atmsubcase.core.port.wire.WireTransferOperationsOutputPort;
import org.springframework.stereotype.Service;

/**
 * Secondary adapter. Allows for transfer of funds via a wire.
 */
@Service
public class WireTransferService implements WireTransferOperationsOutputPort {
}
