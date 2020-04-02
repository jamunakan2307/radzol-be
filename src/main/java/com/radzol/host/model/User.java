/**
 * 
 */
package com.radzol.host.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author pradeepan
 *
 */
@Entity
@Table(name = "user")
public class User extends AbstractMultitenantEntity {

	@NotBlank
	@Column(name = "email", updatable = false)
	private String email;

	@NotBlank
	@Size(max = 80)
	private String password;

	@NotBlank
	@Size(max = 100)
	@Column(name = "first_name")
	private String firstName;

	@NotBlank
	@Size(max = 100)
	@Column(name = "last_name")
	private String lastName;

	private boolean verified;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<UserRole> userRoles = new HashSet<UserRole>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public List<Role> getRoles() {
		return userRoles.stream().map(r -> r.getRole()).collect(Collectors.toList());
	}

	public List<UserRole> getUserRoles() {
		return userRoles.stream().collect(Collectors.toList());
	}
}
