package org.jfl110.she;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // Marks this class as representing a table
class ExampleTableOne {

	/**
	 * The ID column, which should be automatically generated (hence no setter)
	 */
	@Id // It's the id
	@GeneratedValue // It's auto-generated
	@Column // It's a column
	private long id; // The name of the column

	/**
	 * Some other column
	 */
	@Column(length = 20) // It's a column
	private String someStringValue; // The name of the column

	long getId() {
		return id;
	}

	String getSomeStringValue() {
		return someStringValue;
	}

	void setSomeStringValue(String someStringValue) {
		this.someStringValue = someStringValue;
	}
}