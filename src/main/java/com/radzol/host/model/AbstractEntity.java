/**
 * 
 */
package com.radzol.host.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author pradeepan
 *
 */
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class, UuidEntityListener.class })
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @CreatedBy
    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @CreatedDate
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_timestamp", updatable = false)
    private LocalDateTime createdTimestamp;

    @LastModifiedBy
    @NotNull
    @Size(max = 50)
    @Column(name = "modified_by")
    private String modifiedBy;

    @LastModifiedDate
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_timestamp")
    private LocalDateTime modifiedTimestamp;

    @NotNull
    @NaturalId
    private UUID uuid;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getVersion() {
	return version;
    }

    public void setVersion(int version) {
	this.version = version;
    }

    public LocalDateTime getCreatedTimestamp() {
	return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
	this.createdTimestamp = createdTimestamp;
    }

    public LocalDateTime getModifiedTimestamp() {
	return modifiedTimestamp;
    }

    public void setModifiedTimestamp(LocalDateTime modifiedTimestamp) {
	this.modifiedTimestamp = modifiedTimestamp;
    }

    public UUID getUuid() {
	return uuid;
    }

    public void setUuid(UUID uuid) {
	if (this.uuid != null && !this.uuid.equals(uuid)) {
	    throw new IllegalArgumentException("UUID is immutabled and cannot be updated");
	}
	this.uuid = uuid;
    }

}
