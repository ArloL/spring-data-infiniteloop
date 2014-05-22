package de.evosec.infiniteloop.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractRole<USER extends AbstractUser<USER, ? extends AbstractRole<USER>>>
        extends AuditingEntity<USER> {

	private String name;

	@Column(unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
