package org.jfl110.she;

import java.util.Set;

import org.hibernate.SessionFactory;

/**
 * Creates session factories to connect Hibernate to a database.
 *
 * @author JFL110
 */
public interface SessionFactoryFactory {

	/**
	 * Connects to a schema
	 * 
	 * @param databaseConnectionDetails details of the schema.
	 * @param tables a set of classes that represent the tables in the schema.
	 * @return a SessionFactory to use for operations against the schema.
	 */
	SessionFactory buildFactory(DatabaseConnectionDetails databaseConnectionDetails, Set<Class<?>> tables);
}