/**
 * 
 */
package com.radzol.host.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author pradeepan
 *
 */
public enum Permission implements GrantedAuthority {
	SYSTEM_COMPANY, SYSTEM_PROJECT, SYSTEM_TASK, SYSTEM_USER, ADMIN_USER, ADMIN_PROJECT, ADMIN_TASK, USER_PROJECT,
	USER_TASK;

	@Override
	public String getAuthority() {
		return name();
	}

}
