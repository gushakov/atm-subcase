package com.github.atmsubcase.infrastructure.adapters.security;

import com.github.atmsubcase.core.port.security.SecurityOperationsOutputPort;
import org.springframework.stereotype.Service;

/**
 * Secondary adapter. Uses Spring Security to check roles and permissions
 * of the currently authenticated user.
 */
@Service
public class SpringSecurityAdapter implements SecurityOperationsOutputPort {
}
