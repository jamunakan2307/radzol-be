/**
 * 
 */
package com.radzol.host.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.radzol.host.model.Company;
import com.radzol.host.model.User;
import com.radzol.host.service.CompanyService;
import com.radzol.host.service.UserService;

/**
 * @author pradeepan
 *
 */
@Service
public class AuthenticationService {

    private final UserService userService;
    
    private final CompanyService companyService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthenticationService(UserService userService, CompanyService companyService) {
	this.userService = userService;
	this.companyService = companyService;
    }

    public UserAuthentication authenticate(String username, String password, String tenantAlias) {

	Company company = companyService.findByTenantAlias(tenantAlias);
	if (company == null) {
	    throw new BadCredentialsException("login.error.invalidCredentials");
	}
	// NOTE: we reveal as little as possible to hackers by not indicating which
	// argument is invalid
	User user = userService.findByUsername(username);
	if (user == null) {
	    throw new BadCredentialsException("login.error.invalidCredentials");
	}

	// check password
	if (password == null) {
	    throw new BadCredentialsException("login.error.invalidCredentials");
	}
	// use BCryptPasswordEncoder to encrypt password
	if (!passwordEncoder.matches(password, user.getPassword())) {
	    throw new BadCredentialsException("login.error.invalidCredentials");
	}

	return new UserAuthentication(user, tenantAlias);
    }

    public String getCurrentUsername() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if (!(authentication instanceof UserAuthentication)) {
	    return null;
	}
	UserAuthentication userAuthentication = (UserAuthentication) authentication;
	return userAuthentication.getName();
    }

    public boolean isAuthenticated() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if (authentication == null) {
	    return false;
	}
	if (authentication instanceof UserAuthentication) {
	    UserAuthentication userAuthentication = (UserAuthentication) authentication;
	    return userAuthentication.isAuthenticated();
	}

	// Spring's AnonymousAuthenticationFilter creates an anonymous user
	// deal with possible Anonymous user by checking for the ROLE_ANONYMOUS
	// authority
	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	for (GrantedAuthority authority : authorities) {
	    if (authority.getAuthority().equals("ROLE_ANONYMOUS")) {
		return false;
	    }
	}
	return true;
    }

    public void clearAuthentication() {
	SecurityContextHolder.getContext().setAuthentication(null);
    }

}
