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
    SERVICE_SYSTEM, 
    SERVICE_TENANT,
    MANAGE_USER,
    MANAGE_PROJECT,
    MANAGE_TASK;

    @Override
    public String getAuthority() {
	return name();
    }

}
