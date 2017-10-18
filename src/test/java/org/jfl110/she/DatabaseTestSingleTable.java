package org.jfl110.she;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;

import com.google.common.collect.Sets;

/**
 * Simple unit test that: 
 * - Creates an in memory H2 database instance with one table, ExampleTableOne 
 * - Creates/queries/deletes from that table.
 *
 * @author JFL110
 */
public class DatabaseTestSingleTable {

	/** Create a link to the database that has knowledge of the tables. For an
	 empty database, this will create the tables. **/
	SessionFactory sessionFactory = new H2SessionFactoryFactory().buildFactory(null,
			Sets.newHashSet(ExampleTableOne.class));
	SimpleHibernateExecutor simpleHibernateExecutor = new SimpleHibernateExecutor();

	@SuppressWarnings("unchecked")
	@Test
	public void testSaveSelectDelete() {

		// Create an entity
		ExampleTableOne entityOne = new ExampleTableOne();
		entityOne.setSomeStringValue("Hello");

		// Save it
		simpleHibernateExecutor.executeNoReturn(sessionFactory, s -> s.save(entityOne));

		// Select all entites of that type
		List<ExampleTableOne> allRecords = simpleHibernateExecutor.execute(sessionFactory,
				s -> (List<ExampleTableOne>) s.createCriteria(ExampleTableOne.class).list());
		
		// Check the returned entities
		assertEquals(1, allRecords.size());
		assertEquals("Hello", allRecords.get(0).getSomeStringValue());

		// Create another
		ExampleTableOne entityTwo = new ExampleTableOne();
		entityTwo.setSomeStringValue("Goodbye");

		simpleHibernateExecutor.executeNoReturn(sessionFactory, s -> s.save(entityTwo));

		// Update the first entity
		entityOne.setSomeStringValue("Goodbye");
		simpleHibernateExecutor.executeNoReturn(sessionFactory, s -> s.saveOrUpdate(entityOne));

		// HQL query
		List<ExampleTableOne> goodbyeEntities = simpleHibernateExecutor.execute(sessionFactory,
				s -> (List<ExampleTableOne>) s.createQuery("FROM ExampleTableOne WHERE someStringValue = 'Goodbye'")
						.list());
		
		// Check the query results
		assertEquals(2, goodbyeEntities.size());

		// Delete one entity
		simpleHibernateExecutor.executeNoReturn(sessionFactory, s -> s.delete(entityOne));

		// Check only one entity remains
		allRecords = simpleHibernateExecutor.execute(sessionFactory,
				s -> (List<ExampleTableOne>) s.createCriteria(ExampleTableOne.class).list());
		assertEquals(1, allRecords.size());
	}
}