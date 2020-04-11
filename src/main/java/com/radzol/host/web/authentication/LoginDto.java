/**
 * 
 */
package com.radzol.host.web.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author pradeepan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto {

    private String username;
    private String password;
    private String tenantAlias;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getTenantAlias() {
	return tenantAlias;
    }

    public void setTenantAlias(String tenantAlias) {
	this.tenantAlias = tenantAlias;
    }

}
