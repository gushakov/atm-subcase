package com.github.atmsubcase.infrastructure.adapters.security;

import com.github.atmsubcase.core.port.security.SecurityOperationsOutputPort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Secondary adapter. Uses Spring Security to access authentication and
 * authorizations of the user.
 */
@Service
public class SpringSecurityAdapter implements SecurityOperationsOutputPort {
    @Override
    public String getAuthenticatedUserPersonId() {

        // This is just for illustration, the actual implementation will vary
        // depending on the authentication schema used.
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
