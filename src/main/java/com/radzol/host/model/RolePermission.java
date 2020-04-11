/**
 * 
 */
package com.radzol.host.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalIdCache;

import com.radzol.host.security.Permission;

/**
 * @author pradeepan
 *
 */
@Entity
@Table(name = "role_permission")
@Cacheable(true)
@NaturalIdCache
public class RolePermission extends AbstractMultitenantEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", updatable = false)
    private Role role;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Permission permission;

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public Permission getPermission() {
	return permission;
    }

    public void setPermissions(Permission permission) {
	this.permission = permission;
    }

}
