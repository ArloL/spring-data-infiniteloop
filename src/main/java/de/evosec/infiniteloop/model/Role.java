package de.evosec.infiniteloop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role extends AbstractRole<User> {

	private long id;

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
