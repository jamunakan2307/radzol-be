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
    @Size(max = 40)
    @Column(name="username", updatable = false)
    private String username;

    @NotBlank
    @Size(max = 80)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 80)
    private String password;

    private boolean verified;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
    Set<UserRole> userRoles = new HashSet<UserRole>();

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
