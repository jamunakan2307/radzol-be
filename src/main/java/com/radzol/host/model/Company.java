/**
 * 
 */
package com.radzol.host.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author pradeepan
 *
 */
@Entity
@Table(name = "company")
public class Company extends AbstractEntity {

	@NotBlank
	private String name;

	@NotBlank
	@Size(max = 100)
	@Column(name = "time_zone")
	private String timeZone;

	@NotBlank
	@Size(max = 3)
	private String currency;

	@NotBlank
	@Column(name = "tenant_alias")
	private String tenantAlias;

	@Column(name = "service_account")
	private String serviceAccount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTenantAlias() {
		return tenantAlias;
	}

	public void setTenantAlias(String tenantAlias) {
		this.tenantAlias = tenantAlias;
	}

	public String getServiceAccount() {
		return serviceAccount;
	}

	public void setServiceAccount(String serviceAccount) {
		this.serviceAccount = serviceAccount;
	}

}
