/**
 * 
 */
package com.radzol.host.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.radzol.host.model.Role;
import com.radzol.host.model.User;

/**
 * @author pradeepan
 *
 */
public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;

	private String username;
	private String credentials;
	private boolean authenticated;
	private Collection<GrantedAuthority> authorities;
	private String tenantAlias;
	private String firstName;
	private String lastName;

	public UserAuthentication(User user) {
		this.username = user.getEmail();
		this.credentials = user.getPassword();
		this.tenantAlias = "System"; // TODO: pass in the company
		this.authenticated = true;
		this.authorities = computeAuthorities(user);
	}

	@Override
	public String getName() {
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getCredentials() {
		return credentials;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return username;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException("Cannot set this object to authenticated again");
		}
		this.authenticated = isAuthenticated;
	}

	private List<GrantedAuthority> computeAuthorities(User user) {
		if (user != null) {
			ArrayList<GrantedAuthority> temp = new ArrayList<>();
			for (Role role : user.getRoles()) {
				temp.addAll(role.getMemberships());
			}
			return Collections.unmodifiableList(temp);
		}
		throw new IllegalArgumentException("Invalid User");
	}

	public String getTenantAlias() {
		return tenantAlias;
	}

	public void setTenantAlias(String tenantAlias) {
		this.tenantAlias = tenantAlias;
	}
	
	

}
