package org.jfl110.she;

import java.util.function.Consumer;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SimpleHibernateExecutor {

	/**
	 * Execute an action against the database in a single transaction with a
	 * return value. If an exception occurs, roll back the transaction.
	 */
	public <T> T execute(SessionFactory sessionFactory, Function<Session, T> action) {
		Session session = sessionFactory.openSession();

		Transaction txn = session.beginTransaction();

		Exception exception = null;
		T result = null;

		try {
			result = action.apply(session);
			txn.commit();
		} catch (Exception e) {
			exception = e;
			txn.rollback();
		} finally {
			session.close();
		}

		if (exception != null) {
			throw new RuntimeException(exception);
		}

		return result;
	}

	/**
	 * Execute an action against a database that has no return value.
	 */
	public void executeNoReturn(SessionFactory sessionFactory, Consumer<Session> action) {
		execute(sessionFactory, session -> {
			action.accept(session);
			return null;
		});
	}
}
