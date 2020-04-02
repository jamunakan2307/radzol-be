/**
 * 
 */
package com.radzol.host.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * @author pradeepan
 *
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	private final AuthenticationService authenticationService;

	@Autowired
	public SpringSecurityAuditorAware(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	public Optional<String> getCurrentAuditor() {
		String username = authenticationService.getCurrentUsername();
		return Optional.of((username != null ? username : "system"));
	}

}
