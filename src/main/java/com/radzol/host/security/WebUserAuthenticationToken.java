/**
 * 
 */
package com.radzol.host.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.radzol.host.web.authentication.LoginDto;

/**
 * @author pradeepan
 *
 */
public class WebUserAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private final String principal; // email address
	private final LoginDto credentials;

	public WebUserAuthenticationToken(String principal, LoginDto credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
	}

	@Override
	public LoginDto getCredentials() {
		return credentials;
	}

	@Override
	public String getPrincipal() {
		return principal;
	}

}
