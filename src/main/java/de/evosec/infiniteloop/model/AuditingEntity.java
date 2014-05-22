package de.evosec.infiniteloop.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AuditingEntity<USER extends AbstractUser<USER, ?>> {

	private USER createdBy;
	private USER lastModifiedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", updatable = false)
	public USER getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(USER createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_modified_by")
	public USER getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(USER lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
