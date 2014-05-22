package de.evosec.infiniteloop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import de.evosec.infiniteloop.repositories.UserRepository;

public final class App {

	private App() {
	}

	public static void main(String[] args) {
		try (AbstractApplicationContext context =
		        new AnnotationConfigApplicationContext(AppConfiguration.class)) {
			UserRepository bean = context.getBean(UserRepository.class);
			bean.findAll();
		}
	}
}
