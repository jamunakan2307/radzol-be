/**
 * 
 */
package com.radzol.host.model;

import java.util.UUID;

import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

/**
 * @author pradeepan
 *
 */
@Component
public class UuidEntityListener {

    @PrePersist
    public void setUuid(AbstractEntity target) {
	if (target.getUuid() == null) {
	    // TODO: use another one from jackson
	    target.setUuid(UUID.randomUUID());
	}
    }

}
