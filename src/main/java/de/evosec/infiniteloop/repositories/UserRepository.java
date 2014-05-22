package de.evosec.infiniteloop.repositories;

import org.springframework.data.repository.CrudRepository;

import de.evosec.infiniteloop.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
