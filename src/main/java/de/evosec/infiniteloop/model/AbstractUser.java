package de.evosec.infiniteloop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractUser<USER extends AbstractUser<USER, ROLE>, ROLE extends AbstractRole<USER>> {

	private Set<ROLE> roles = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	public Set<ROLE> getRoles() {
		return roles;
	}

	public void setRoles(Set<ROLE> roles) {
		this.roles = roles;
	}

}
