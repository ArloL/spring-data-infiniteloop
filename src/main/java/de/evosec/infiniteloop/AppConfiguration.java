package de.evosec.infiniteloop;

import java.beans.PropertyVetoException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import de.evosec.infiniteloop.repositories.UserRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class AppConfiguration {

	@Bean
	public DataSource dataSource(Environment environment) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		try {
			dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new IllegalArgumentException("Wrong driver class", e);
		}
		dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	        JpaVendorAdapter vendorAdapter, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory =
		        new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("de.evosec.infiniteloop.model");
		factory.setPersistenceUnitName("infiniteloop");
		factory.getJpaPropertyMap().put("hibernate.id.new_generator_mappings",
		        "true");
		factory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create");
		return factory;
	}

	@Bean
	public JpaVendorAdapter vendorAdapter(Environment environment) {
		HibernateJpaVendorAdapter vendorAdapter =
		        new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform(environment
		        .getProperty("hibernate.databasePlatform"));
		return vendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
	        EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
