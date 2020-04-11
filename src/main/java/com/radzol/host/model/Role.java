/**
 * 
 */
package com.radzol.host.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalIdCache;

import com.radzol.host.security.Permission;

/**
 * @author pradeepan
 *
 */
@Entity
@Table(name = "role")
@Cacheable(true)
@NaturalIdCache
public class Role extends AbstractMultitenantEntity {

    @NotBlank
    @Size(max = 45)
    private String name;

    @NotNull
    @OneToMany(mappedBy = "role", cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RolePermission> memberships = new HashSet<RolePermission>();

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Permission> getMemberships() {
	return memberships.stream().map(n -> n.getPermission()).collect(Collectors.toList());
    }

}
