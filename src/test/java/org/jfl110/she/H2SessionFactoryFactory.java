package org.jfl110.she;

import java.util.Properties;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.jfl110.she.DatabaseConnectionDetails;
import org.jfl110.she.SessionFactoryFactory;

/**
 * A factory for creating SessionFactory to connect to an in-memory H2 database.
 *
 * @author JFL110
 */
class H2SessionFactoryFactory implements SessionFactoryFactory {

	@Override
	public SessionFactory buildFactory(DatabaseConnectionDetails databaseConnectionDetails, Set<Class<?>> tables) {
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		Configuration configuration = new Configuration().addProperties(prop);

		for (Class<?> table : tables) {
			configuration.addAnnotatedClass(table);
		}

		return configuration.buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
	}
}