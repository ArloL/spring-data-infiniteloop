package de.evosec.infiniteloop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends AbstractUser<User, Role> {

	private long id;

	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
