/**
 * 
 */
package com.radzol.host.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.radzol.host.web.authentication.LoginDto;

/**
 * @author pradeepan
 *
 */
@Service
public class WebUserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	if (!supports(authentication.getClass())) {
	    throw new IllegalArgumentException(
		    "Only WebLoginAuthenticationToken is supported, " + authentication.getClass() + " was attempted");
	}

	LoginDto credentials = (LoginDto) authentication.getCredentials();
	return authenticationService.authenticate(credentials.getUsername(), credentials.getPassword(),
		credentials.getTenantAlias());
    }

    /**
     * WebLoginAuthenticationToken is the only supported token.
     *
     * @param aClass class to check for support
     * @return true if class is of type WebLoginAuthenticationToken
     */
    @Override
    public boolean supports(Class<?> aClass) {
	return WebUserAuthenticationToken.class.isAssignableFrom(aClass);
    }

}
