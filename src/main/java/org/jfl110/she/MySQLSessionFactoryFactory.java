package org.jfl110.she;

import java.util.Properties;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * A factory for creating SessionFactory to connect to a MySQL database.
 *
 * @author JFL110
 */
public class MySQLSessionFactoryFactory implements SessionFactoryFactory {

	@Override
	public SessionFactory buildFactory(DatabaseConnectionDetails databaseConnectionDetails, Set<Class<?>> tables) {
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", getDatabaseConnectionString(databaseConnectionDetails));
		prop.setProperty("hibernate.connection.username", databaseConnectionDetails.getUserName());
		prop.setProperty("hibernate.connection.password", databaseConnectionDetails.getPassword());
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		Configuration configuration = new Configuration().addProperties(prop);

		for (Class<?> table : tables) {
			configuration.addAnnotatedClass(table);
		}

		return configuration.buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
	}

	private String getDatabaseConnectionString(DatabaseConnectionDetails databaseConnectionDetails) {
		return "jdbc:mysql://" + databaseConnectionDetails.getHostName() + ":" + databaseConnectionDetails.getPort()
				+ "/" + databaseConnectionDetails.getDatabaseName();
	}
}